package lannisters.devcor.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lannisters.devcor.entity.Device;
import lannisters.devcor.entity.DeviceType;
import lannisters.devcor.entity.Player;
import lannisters.devcor.entity.ProblemType;
import lannisters.devcor.entity.Room;
import lannisters.devcor.entity.UrgencyStatus;
import lannisters.devcor.mail.MailService;
import lannisters.devcor.service.DeviceTypesService;
import lannisters.devcor.service.DevicesService;
import lannisters.devcor.service.PlayersService;
import lannisters.devcor.service.ProblemTypesService;
import lannisters.devcor.service.ReportService;
import lannisters.devcor.service.RoomsService;
import lannisters.devcor.service.UrgencyStatusesService;
import lannisters.devcor.view.OrdersReport;
import lannisters.devcor.view.TechniciansReport;
import lannisters.devcor.view.DevicesReport;

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

	@Autowired
	private ProblemTypesService problemTypesService;

	@Autowired
	private UrgencyStatusesService urgencyStatusesService;

	@Autowired
	private MailService mail;

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
	public String saveTechnician(@ModelAttribute("user") Player user, RedirectAttributes redirectAttributes) {
		String page = null;
		if (playersService.checkEmailExistence(user)) {
			page = "redirect:/technicians/add";
			redirectAttributes.addFlashAttribute("unique", "User with this email exist!");
		} else {
			user.setRoleId(2);
			playersService.addPlayer(user);
			page = "redirect:/technicians";
			mail.registrationEmail(user);
			redirectAttributes.addFlashAttribute("tech", "Technician created successfully!");
		}
		return page;
	}

	@RequestMapping(value = "/users/add", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("user") Player user, RedirectAttributes redirectAttributes) {
		String page = null;
		if (playersService.checkEmailExistence(user)) {
			page = "redirect:/users/add";
			redirectAttributes.addFlashAttribute("unique", "User with this email exist!");
		} else {
			user.setRoleId(3);
			playersService.addPlayer(user);
			page = "redirect:/users";
			mail.registrationEmail(user);
			redirectAttributes.addFlashAttribute("user", "User created successfully!");
		}
		return page;
	}

	@RequestMapping(value = { "/technicians/edit/id/{id}", "/users/edit/id/{id}" }, method = RequestMethod.GET)
	public String editUser(@PathVariable("id") int id, Model model) {
		model.addAttribute("user", playersService.getPlayerById(id));
		return "editUser";
	}

	@RequestMapping(value = { "/technicians/edit/id/{id}", "/users/edit/id/{id}" }, method = RequestMethod.POST)
	public String saveEditedUser(@ModelAttribute("user") Player user, @PathVariable("id") int id,
			RedirectAttributes redirectAttributes) {
		String page = null;
		if (playersService.checkEmailExistence(user)
				&& playersService.getPlayerIdByEmail(user.getPlayerEmail()) != user.getPlayerId()) {
			if (user.getRoleId() == 2)
				page = "redirect:/technicians/edit/id/" + id;
			else
				page = "redirect:/users/edit/id/" + id;
			redirectAttributes.addFlashAttribute("unique", "User with this email exist!");
		} else {
			playersService.updatePlayer(user);
			if (user.getRoleId() == 2) {
				page = "redirect:/technicians";
				redirectAttributes.addFlashAttribute("tech", "Technician edited successfully!");
			} else {
				page = "redirect:/users";
				redirectAttributes.addFlashAttribute("user", "User edited successfully!");
			}
		}
		return page;
	}

	@RequestMapping(value = "/technicians/delete/id/{id}", method = RequestMethod.GET)
	public String deleteTechnician(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
		playersService.deletePlayer(id);
		redirectAttributes.addFlashAttribute("tech", "Technician deleted successfully!");
		return "redirect:/technicians";
	}

	@RequestMapping(value = "/users/delete/id/{id}", method = RequestMethod.GET)
	public String deleteUser(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
		playersService.deletePlayer(id);
		redirectAttributes.addFlashAttribute("user", "User deleted successfully!");
		return "redirect:/users";
	}

	@RequestMapping(value = "/rooms", method = RequestMethod.GET)
	public String roomsPage(Model model) {
		model.addAttribute("rooms", roomsService.getAllRooms());
		return "rooms";
	}

	@RequestMapping(value = "/rooms/add", method = RequestMethod.GET)
	public String addRoom(Model model) {
		model.addAttribute("room", new Room());
		model.addAttribute("technicians", playersService.getAllTechnicians());
		return "addRoom";
	}

	@RequestMapping(value = "/rooms/add", method = RequestMethod.POST)
	public String saveRoom(@ModelAttribute("room") Room room, RedirectAttributes redirectAttributes) {
		roomsService.addRoom(room);
		redirectAttributes.addFlashAttribute("room", "Room created successfully!");
		return "redirect:/rooms";
	}

	@RequestMapping(value = "/rooms/edit/id/{id}", method = RequestMethod.GET)
	public String editRoom(@PathVariable("id") int id, Model model) {
		model.addAttribute("room", roomsService.getRoomById(id));
		model.addAttribute("technicians", playersService.getAllTechnicians());
		model.addAttribute("roomDevices", devicesService.getAllDevicesOfRoom(id));
		return "editRoom";
	}

	@RequestMapping(value = "/rooms/edit/id/{id}", method = RequestMethod.POST)
	public String saveEditedRoom(@ModelAttribute("room") Room room, RedirectAttributes redirectAttributes) {
		roomsService.updateRoom(room);
		redirectAttributes.addFlashAttribute("room", "Room edited successfully!");
		return "redirect:/rooms";
	}

	@RequestMapping(value = "/rooms/delete/id/{id}", method = RequestMethod.GET)
	public String deleteRoom(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
		roomsService.deleteRoom(id);
		redirectAttributes.addFlashAttribute("room", "Room deleted successfully!");
		return "redirect:/rooms";
	}

	@RequestMapping(value = "/rooms/edit/id/{id}/device/add", method = RequestMethod.GET)
	public String addDeviceOfRoom(@PathVariable("id") int id, Model model) {
		model.addAttribute("deviceOfRoom", new Device());
		model.addAttribute("room", roomsService.getRoomById(id));
		model.addAttribute("deviceTypes", deviceTypesService.getAllDeviceTypes());
		return "addDeviceOfRoom";
	}

	@RequestMapping(value = "/rooms/edit/id/{room_id}/device/add", method = RequestMethod.POST)
	public String processDeviceOfRoom(@ModelAttribute("deviceOfRoom") Device device, @PathVariable("room_id") int id,
			RedirectAttributes redirectAttributes) {
		devicesService.addDevice(device);
		redirectAttributes.addFlashAttribute("device", "Device created successfully!");
		return "redirect:/rooms/edit/id/" + id;
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
	public String saveDevice(@ModelAttribute("device") Device device, RedirectAttributes redirectAttributes) {
		devicesService.addDevice(device);
		redirectAttributes.addFlashAttribute("device", "Device created successfully!");
		return "redirect:/devices";
	}

	@RequestMapping(value = { "/devices/edit/id/{device_id}",
			"/rooms/edit/id/{room_id}/devices/edit/id/{device_id}" }, method = RequestMethod.GET)
	public String editDevice(@PathVariable("device_id") int id, Model model, HttpServletRequest request) {
		model.addAttribute("device", devicesService.getDeviceById(id));
		model.addAttribute("deviceTypes", deviceTypesService.getAllDeviceTypes());
		model.addAttribute("rooms", roomsService.getAllRooms());
		model.addAttribute("page", request.getServletPath());
		return "editDevice";
	}

	@RequestMapping(value = "/devices/edit/id/{id}", method = RequestMethod.POST)
	public String saveEditedDevice(@ModelAttribute("device") Device device, RedirectAttributes redirectAttributes) {
		devicesService.updateDevice(device);
		redirectAttributes.addFlashAttribute("device", "Device edited successfully!");
		return "redirect:/devices";
	}

	@RequestMapping(value = "/rooms/edit/id/{room_id}/devices/edit/id/{device_id}", method = RequestMethod.POST)
	public String saveEditedRoomDevice(@ModelAttribute("device") Device device, @PathVariable("room_id") int id,
			RedirectAttributes redirectAttributes) {
		devicesService.updateDevice(device);
		redirectAttributes.addFlashAttribute("device", "Device edited successfully!");
		return "redirect:/rooms/edit/id/" + id;
	}

	@RequestMapping(value = { "/devices/delete/id/{device_id}",
			"/rooms/edit/id/{room_id}/devices/delete/id/{device_id}" }, method = RequestMethod.GET)
	public String deleteDevice(@PathVariable("device_id") int id, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		devicesService.deleteDevice(id);
		String referer = request.getHeader("Referer");
		redirectAttributes.addFlashAttribute("device", "Device deleted successfully!");
		return "redirect:" + referer;
	}

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

	@RequestMapping(value = "/configuration", method = RequestMethod.GET)
	public String extra(Model model) {
		model.addAttribute("problemType", problemTypesService.getAllProblemTypes());
		model.addAttribute("deviceType", deviceTypesService.getAllDeviceTypes());
		model.addAttribute("urgStatus", urgencyStatusesService.getAllUrgencyStatuses());
		return "configurationPanel";
	}

	@RequestMapping(value = "/configuration/problemType/edit/id/{id}", method = RequestMethod.GET)
	public String editProblemType(@PathVariable("id") int id, Model model) {
		model.addAttribute("action", "editProblemType");
		model.addAttribute("problem", problemTypesService.getProblemTypeById(id));
		return "configurationPanel";
	}

	@RequestMapping(value = "/configuration/problemType/edit/id/{id}", method = RequestMethod.POST)
	public String saveEditedProblemType(@ModelAttribute("problem") ProblemType problemType) {
		problemTypesService.updateProblemType(problemType);
		return "redirect:/configuration";
	}

	@RequestMapping(value = "/configuration/problemType/add", method = RequestMethod.GET)
	public String addProblemType(Model model) {
		model.addAttribute("action", "addProblemType");
		model.addAttribute("problem", new ProblemType());
		return "configurationPanel";
	}

	@RequestMapping(value = "/configuration/problemType/add", method = RequestMethod.POST)
	public String saveProblemType(@ModelAttribute("problem") ProblemType problemType) {
		problemTypesService.addProblemType(problemType);
		return "redirect:/configuration";
	}

	@RequestMapping(value = "/configuration/problemType/delete/id/{id}", method = RequestMethod.GET)
	public String deleteProblemType(@PathVariable("id") int id) {
		problemTypesService.deleteProblemType(id);
		return "redirect:/configuration";
	}

	@RequestMapping(value = "/configuration/deviceType/edit/id/{id}", method = RequestMethod.GET)
	public String editDeviceType(@PathVariable("id") int id, Model model) {
		model.addAttribute("action", "editDeviceType");
		model.addAttribute("deviceType", deviceTypesService.getDeviceTypeById(id));
		return "configurationPanel";
	}

	@RequestMapping(value = "/configuration/deviceType/edit/id/{id}", method = RequestMethod.POST)
	public String saveEditedDeviceType(@ModelAttribute("deviceType") DeviceType deviceType) {
		deviceTypesService.updateDeviceType(deviceType);
		return "redirect:/configuration";
	}

	@RequestMapping(value = "/configuration/deviceType/add", method = RequestMethod.GET)
	public String addDeviceType(Model model) {
		model.addAttribute("action", "addDeviceType");
		model.addAttribute("deviceType", new DeviceType());
		return "configurationPanel";
	}

	@RequestMapping(value = "/configuration/deviceType/add", method = RequestMethod.POST)
	public String saveDeviceType(@ModelAttribute("deviceType") DeviceType deviceType) {
		deviceTypesService.addDeviceType(deviceType);
		return "redirect:/configuration";
	}

	@RequestMapping(value = "/configuration/deviceType/delete/id/{id}", method = RequestMethod.GET)
	public String deleteDeviceType(@PathVariable("id") int id) {
		deviceTypesService.deleteDeviceType(id);
		return "redirect:/configuration";
	}

	@RequestMapping(value = "/configuration/urgStatus/edit/id/{id}", method = RequestMethod.GET)
	public String editUrgStatus(@PathVariable("id") int id, Model model) {
		model.addAttribute("action", "editUrgStatus");
		model.addAttribute("urgStatus", urgencyStatusesService.getUrgencyStatusById(id));
		return "configurationPanel";
	}

	@RequestMapping(value = "/configuration/urgStatus/edit/id/{id}", method = RequestMethod.POST)
	public String saveEditedUrgStatus(@ModelAttribute("urgStatus") UrgencyStatus urgencyStatus) {
		urgencyStatusesService.updateUrgencyStatus(urgencyStatus);
		return "redirect:/configuration";
	}

	@RequestMapping(value = "/configuration/urgStatus/add", method = RequestMethod.GET)
	public String addUrgStatus(Model model) {
		model.addAttribute("action", "addUrgStatus");
		model.addAttribute("urgStatus", new UrgencyStatus());
		return "configurationPanel";
	}

	@RequestMapping(value = "/configuration/urgStatus/add", method = RequestMethod.POST)
	public String saveUrgStatus(@ModelAttribute("urgStatus") UrgencyStatus urgencyStatus) {
		urgencyStatusesService.addUrgencyStatus(urgencyStatus);
		return "redirect:/configuration";
	}

	@RequestMapping(value = "/configuration/urgStatus/delete/id/{id}", method = RequestMethod.GET)
	public String deleteUrgStatus(@PathVariable("id") int id) {
		urgencyStatusesService.deleteUrgencyStatus(id);
		return "redirect:/configuration";
	}
}