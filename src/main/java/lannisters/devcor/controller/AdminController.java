package lannisters.devcor.controller;

import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import lannisters.devcor.entity.Device;
import lannisters.devcor.entity.Player;
import lannisters.devcor.service.DeviceTypesService;
import lannisters.devcor.service.DevicesService;
import lannisters.devcor.service.PlayersService;
import lannisters.devcor.service.ReportService;
import lannisters.devcor.service.RoomsService;
import lannisters.devcor.view.ExcelReportView;
import lannisters.devcor.view.ExcelReportView2;
import lannisters.devcor.view.ExcelReportView3;

@Controller
public class AdminController {

	public String dateFromForReport;
	public String dateToForReport;

	@Autowired
	private PlayersService playersService;
	@Autowired
	private DevicesService devicesService;
	@Autowired
	private DeviceTypesService deviceTypesService;
	@Autowired
	private RoomsService roomsService;
	@Autowired
	private ReportService reportService;

	@RequestMapping(value = "/technicians", method = RequestMethod.GET)
	public String techniciansPage(Model model) {
		model.addAttribute("technicians", playersService.getAllTechnicians());
		return "technicians";
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String usersPage(Model model) {
		model.addAttribute("users", playersService.getAllUsers());
		return "users";
	}

	@RequestMapping(value = "/technicians/add", method = RequestMethod.GET)
	public String addTechnician(Model model) {
		model.addAttribute("user", new Player());
		model.addAttribute("role", 2);
		return "addUser";
	}

	@RequestMapping(value = "/users/add", method = RequestMethod.GET)
	public String addUser(Model model) {
		model.addAttribute("user", new Player());
		model.addAttribute("role", 3);
		return "addUser";
	}

	@RequestMapping(value = "/technicians/add", method = RequestMethod.POST)
	public String saveTechnician(@ModelAttribute("user") Player user) {
		user.setRoleId(2);
		playersService.addPlayer(user);
		return "redirect:/technicians";
	}

	@RequestMapping(value = "/users/add", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("user") Player user) {
		user.setRoleId(3);
		playersService.addPlayer(user);
		return "redirect:/users";
	}

	@RequestMapping(value = { "/technicians/edit/id/{id}", "/users/edit/id/{id}" }, method = RequestMethod.GET)
	public String editUser(@PathVariable("id") int id, Model model) {
		model.addAttribute("user", playersService.getPlayerById(id));
		return "editUser";
	}

	@RequestMapping(value = { "/technicians/edit/id/{id}", "/users/edit/id/{id}" }, method = RequestMethod.POST)
	public String saveEditedUser(@ModelAttribute("user") Player user, @PathVariable("id") int id, Model model) {
		playersService.updatePlayer(user);
		String page = null;
		if (user.getRoleId() == 2)
			page = "redirect:/technicians";
		else
			page = "redirect:/users";
		return page;
	}

	@RequestMapping(value = "/technicians/delete/id/{id}", method = RequestMethod.GET)
	public String deleteTechnician(@PathVariable("id") int id) {
		playersService.deletePlayer(id);
		return "redirect:/technicians";
	}

	@RequestMapping(value = "/users/delete/id/{id}", method = RequestMethod.GET)
	public String deleteUser(@PathVariable("id") int id) {
		playersService.deletePlayer(id);
		return "redirect:/users";
	}

	@RequestMapping(value = "/rooms", method = RequestMethod.GET)
	public String roomsPage() {
		return "rooms";
	}

	@RequestMapping(value = "/devices", method = RequestMethod.GET)
	public String devicesPage(Model model) {
		model.addAttribute("devices", devicesService.getAllDevices());
		return "devices";
	}

	@RequestMapping(value = "/devices/add", method = RequestMethod.GET)
	public String addDevice(Model model) {
		model.addAttribute("device", new Device());
		model.addAttribute("deviceTypes", deviceTypesService.getAllDeviceTypes());
		model.addAttribute("rooms", roomsService.getAllRooms());
		return "addDevice";
	}

	@RequestMapping(value = "/devices/add", method = RequestMethod.POST)
	public String saveDevice(@ModelAttribute("device") Device device) {
		devicesService.addDevice(device);
		return "redirect:/devices";
	}

	@RequestMapping(value = "/devices/edit/id/{id}", method = RequestMethod.GET)
	public String editDevice(@PathVariable("id") int id, Model model) {
		model.addAttribute("device", devicesService.getDeviceById(id));
		model.addAttribute("deviceTypes", deviceTypesService.getAllDeviceTypes());
		model.addAttribute("rooms", roomsService.getAllRooms());
		return "editDevice";
	}

	@RequestMapping(value = "/devices/edit/id/{id}", method = RequestMethod.POST)
	public String saveEditedDevice(@ModelAttribute("device") Device device, @PathVariable("id") int id, Model model) {
		devicesService.updateDevice(device);
		return "redirect:/devices";
	}

	@RequestMapping(value = "/devices/delete/id/{id}", method = RequestMethod.GET)
	public String deleteDevice(@PathVariable("id") int id) {
		devicesService.deleteDevice(id);
		return "redirect:/devices";
	}

	@RequestMapping(value = "/reports", method = RequestMethod.GET)
	public String reportsPage(Model model, Principal principal, HttpServletRequest request) {
		dateFromForReport = request.getParameter("date1");
		dateToForReport = request.getParameter("date2");
		String reportNum = request.getParameter("reportNum");
		if (reportNum != null) {
			if (reportNum.equals("Report1"))
				model.addAttribute("report", reportService.getReport1(dateFromForReport, dateToForReport));
			if (reportNum.equals("Report2"))
				model.addAttribute("report2", reportService.getReport2(dateFromForReport, dateToForReport));
			if (reportNum.equals("Report3"))
				model.addAttribute("report3", reportService.getReport3(dateFromForReport, dateToForReport));
		}
		return "reports";
	}

	@RequestMapping(value = "/DevCorReport", method = RequestMethod.GET)
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String output = ServletRequestUtils.getStringParameter(request, "exel");
		if ("EXCEL1".equals(output.toUpperCase())) {
			return new ModelAndView(new ExcelReportView(), "reportData",
					reportService.getReport1(dateFromForReport, dateToForReport));
		} else if ("EXCEL2".equals(output.toUpperCase())) {
			return new ModelAndView(new ExcelReportView2(), "reportData",
					reportService.getReport2(dateFromForReport, dateToForReport));
		} else if ("EXCEL3".equals(output.toUpperCase())) {
			return new ModelAndView(new ExcelReportView3(), "reportData",
					reportService.getReport1(dateFromForReport, dateToForReport));
		} else {
			return new ModelAndView("reports");
		}
	}
}