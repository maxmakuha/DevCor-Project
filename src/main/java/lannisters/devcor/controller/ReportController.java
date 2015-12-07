package lannisters.devcor.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import lannisters.devcor.service.ReportService;
import lannisters.devcor.view.DevicesReport;
import lannisters.devcor.view.OrdersReport;
import lannisters.devcor.view.TechniciansReport;

@Controller
public class ReportController {

	public String dateFromForReport;
	public String dateToForReport;

	@Autowired
	private ReportService reportService;

	@RequestMapping(value = "/reports", method = RequestMethod.GET)
	public String reportsPage(Model model, HttpServletRequest request) {
		dateFromForReport = request.getParameter("date1");
		dateToForReport = request.getParameter("date2");
		String report = request.getParameter("reportNum");
		if (report != null) {
			if (report.equals("OrdersReport"))
				model.addAttribute("OrdersReport", reportService.getOrdersReport(dateFromForReport, dateToForReport));
			if (report.equals("TechniciansReport"))
				model.addAttribute("TechniciansReport", reportService.getTechniciansReport(dateFromForReport, dateToForReport));
			if (report.equals("DevicesReport"))
				model.addAttribute("DevicesReport", reportService.getDevicesReport(dateFromForReport, dateToForReport));
		}
		return "reports";
	}
	
	@RequestMapping(value = { "/reports/diagrams/{id}"}, method = RequestMethod.GET)
	public String showDiagram(@PathVariable("id") int id, Model model) {
		model.addAttribute("report", reportService.getTechniciansReportById(dateFromForReport, dateToForReport,id));
		return "diagrams";
	}

	@RequestMapping(value = "/DevCorReport", method = RequestMethod.GET)
	public ModelAndView handleRequestInternal(HttpServletRequest request)throws Exception {
		String output = request.getParameter("exel");
		if (output.equals("OrdersReport")) {
			return new ModelAndView(new OrdersReport(), "reportData",
					reportService.getOrdersReport(dateFromForReport, dateToForReport));
		} else if (output.equals("TechniciansReport")) {
			return new ModelAndView(new TechniciansReport(), "reportData",
					reportService.getTechniciansReport(dateFromForReport, dateToForReport));
		} else if (output.equals("DevicesReport")) {
			return new ModelAndView(new DevicesReport(), "reportData",
					reportService.getDevicesReport(dateFromForReport, dateToForReport));
		} else {
			return new ModelAndView("reports");
		}
	}
}