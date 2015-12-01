package lannisters.devcor.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
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
	public String reportsPage(Model model, Principal principal, HttpServletRequest request) {
		dateFromForReport = request.getParameter("date1");
		dateToForReport = request.getParameter("date2");
		String reportNum = request.getParameter("reportNum");
		if (reportNum != null) {
			if (reportNum.equals("OrdersReport"))
				model.addAttribute("report", reportService.getOrdersReport(dateFromForReport, dateToForReport));
			if (reportNum.equals("TechniciansReport"))
				model.addAttribute("report2", reportService.getTechniciansReport(dateFromForReport, dateToForReport));
			if (reportNum.equals("DevicesReport"))
				model.addAttribute("report3", reportService.getDevicesReport(dateFromForReport, dateToForReport));
		}
		return "reports";
	}

	@RequestMapping(value = "/DevCorReport", method = RequestMethod.GET)
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String output = ServletRequestUtils.getStringParameter(request, "exel");
		System.out.println(output);
		if ("OrdersReport".equals(output)) {
			return new ModelAndView(new OrdersReport(), "reportData",
					reportService.getOrdersReport(dateFromForReport, dateToForReport));
		} else if ("TechniciansReport".equals(output)) {
			return new ModelAndView(new TechniciansReport(), "reportData",
					reportService.getTechniciansReport(dateFromForReport, dateToForReport));
		} else if ("DevicesReport".equals(output)) {
			return new ModelAndView(new DevicesReport(), "reportData",
					reportService.getDevicesReport(dateFromForReport, dateToForReport));
		} else {
			return new ModelAndView("reports");
		}
	}
}