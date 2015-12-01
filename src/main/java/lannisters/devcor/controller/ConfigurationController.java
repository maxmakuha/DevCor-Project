package lannisters.devcor.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lannisters.devcor.entity.DeviceType;
import lannisters.devcor.entity.ProblemType;
import lannisters.devcor.entity.UrgencyStatus;
import lannisters.devcor.service.DeviceTypesService;
import lannisters.devcor.service.DevicesService;
import lannisters.devcor.service.OrdersService;
import lannisters.devcor.service.ProblemTypesService;
import lannisters.devcor.service.UrgencyStatusesService;

@Controller
public class ConfigurationController {

	@Autowired
	private DevicesService devicesService;

	@Autowired
	private DeviceTypesService deviceTypesService;

	@Autowired
	private OrdersService ordersService;

	@Autowired
	private ProblemTypesService problemTypesService;

	@Autowired
	private UrgencyStatusesService urgencyStatusesService;

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
	public String saveEditedProblemType(@ModelAttribute("problem") ProblemType problemType, @PathVariable("id") int id,
			RedirectAttributes redirectAttributes) throws SQLException {
		String page = null;
		if (problemTypesService.checkProblemTypeExistence(problemType)
				&& problemTypesService.getProblemTypeByTitle(problemType.getProblemType())
						.getProblemTypeId() != problemType.getProblemTypeId()) {
			redirectAttributes.addFlashAttribute("unique", "Problem type with this title exist!");
			page = "redirect:/configuration/problemType/edit/id/" + id;
		} else {
			problemTypesService.updateProblemType(problemType);
			redirectAttributes.addFlashAttribute("message", "Problem type edited successfully!");
			page = "redirect:/configuration";
		}
		return page;
	}

	@RequestMapping(value = "/configuration/problemType/add", method = RequestMethod.GET)
	public String addProblemType(Model model) {
		model.addAttribute("action", "addProblemType");
		model.addAttribute("problem", new ProblemType());
		return "configurationPanel";
	}

	@RequestMapping(value = "/configuration/problemType/add", method = RequestMethod.POST)
	public String saveProblemType(@ModelAttribute("problem") ProblemType problemType,
			RedirectAttributes redirectAttributes) throws SQLException {
		String page = null;
		if (problemTypesService.checkProblemTypeExistence(problemType)) {
			redirectAttributes.addFlashAttribute("unique", "Problem type with this title exist!");
			page = "redirect:/configuration/problemType/add";
		} else {
			problemTypesService.addProblemType(problemType);
			redirectAttributes.addFlashAttribute("message", "Problem type added successfully!");
			page = "redirect:/configuration";
		}
		return page;
	}

	@RequestMapping(value = "/configuration/problemType/delete/id/{id}", method = RequestMethod.GET)
	public String deleteProblemType(@PathVariable("id") int id, RedirectAttributes redirectAttributes)
			throws SQLException {
		if (ordersService.checkProblemTypeOrders(id))
			redirectAttributes.addFlashAttribute("error", "Unable to delete. Orders with this problem type exist!");
		else {
			problemTypesService.deleteProblemType(id);
			redirectAttributes.addFlashAttribute("message", "Problem type deleted successfully!");
		}
		return "redirect:/configuration";
	}

	@RequestMapping(value = "/configuration/deviceType/edit/id/{id}", method = RequestMethod.GET)
	public String editDeviceType(@PathVariable("id") int id, Model model) {
		model.addAttribute("action", "editDeviceType");
		model.addAttribute("deviceType", deviceTypesService.getDeviceTypeById(id));
		return "configurationPanel";
	}

	@RequestMapping(value = "/configuration/deviceType/edit/id/{id}", method = RequestMethod.POST)
	public String saveEditedDeviceType(@ModelAttribute("deviceType") DeviceType deviceType, @PathVariable("id") int id,
			RedirectAttributes redirectAttributes) throws SQLException {
		String page = null;
		if (deviceTypesService.checkDeviceTypeExistence(deviceType) && deviceTypesService
				.getDeviceTypeByTitle(deviceType.getDeviceType()).getDeviceTypeId() != deviceType.getDeviceTypeId()) {
			redirectAttributes.addFlashAttribute("unique", "Device type with this title exist!");
			page = "redirect:/configuration/deviceType/edit/id/" + id;
		} else {
			deviceTypesService.updateDeviceType(deviceType);
			redirectAttributes.addFlashAttribute("message", "Device type edited successfully!");
			page = "redirect:/configuration";
		}
		return page;
	}

	@RequestMapping(value = "/configuration/deviceType/add", method = RequestMethod.GET)
	public String addDeviceType(Model model) {
		model.addAttribute("action", "addDeviceType");
		model.addAttribute("deviceType", new DeviceType());
		return "configurationPanel";
	}

	@RequestMapping(value = "/configuration/deviceType/add", method = RequestMethod.POST)
	public String saveDeviceType(@ModelAttribute("deviceType") DeviceType deviceType,
			RedirectAttributes redirectAttributes) throws SQLException {
		String page = null;
		if (deviceTypesService.checkDeviceTypeExistence(deviceType)) {
			redirectAttributes.addFlashAttribute("unique", "Device type with this title exist!");
			page = "redirect:/configuration/deviceType/add";
		} else {
			deviceTypesService.addDeviceType(deviceType);
			redirectAttributes.addFlashAttribute("message", "Device type added successfully!");
			page = "redirect:/configuration";
		}
		return page;
	}

	@RequestMapping(value = "/configuration/deviceType/delete/id/{id}", method = RequestMethod.GET)
	public String deleteDeviceType(@PathVariable("id") int id, RedirectAttributes redirectAttributes)
			throws SQLException {
		if (devicesService.checkDeviceTypeDevices(id))
			redirectAttributes.addFlashAttribute("error", "Unable to delete. Devices with this device type exist!");
		else {
			deviceTypesService.deleteDeviceType(id);
			redirectAttributes.addFlashAttribute("message", "Device type deleted successfully!");
		}
		return "redirect:/configuration";
	}

	@RequestMapping(value = "/configuration/urgStatus/edit/id/{id}", method = RequestMethod.GET)
	public String editUrgStatus(@PathVariable("id") int id, Model model) {
		model.addAttribute("action", "editUrgStatus");
		model.addAttribute("urgStatus", urgencyStatusesService.getUrgencyStatusById(id));
		return "configurationPanel";
	}

	@RequestMapping(value = "/configuration/urgStatus/edit/id/{id}", method = RequestMethod.POST)
	public String saveEditedUrgStatus(@ModelAttribute("urgStatus") UrgencyStatus urgencyStatus,
			@PathVariable("id") int id, RedirectAttributes redirectAttributes) throws SQLException {
		String page = null;
		if (urgencyStatusesService.checkUrgencyStatusExistence(urgencyStatus)
				&& urgencyStatusesService.getUrgencyStatusByTitle(urgencyStatus.getUrgencyStatus())
						.getUrgencyStatusId() != urgencyStatus.getUrgencyStatusId()) {
			redirectAttributes.addFlashAttribute("unique", "Urgency status with this title exist!");
			page = "redirect:/configuration/urgStatus/edit/id/" + id;
		} else {
			urgencyStatusesService.updateUrgencyStatus(urgencyStatus);
			redirectAttributes.addFlashAttribute("message", "Urgency status edited successfully!");
			page = "redirect:/configuration";
		}
		return page;
	}

	@RequestMapping(value = "/configuration/urgStatus/add", method = RequestMethod.GET)
	public String addUrgStatus(Model model) {
		model.addAttribute("action", "addUrgStatus");
		model.addAttribute("urgStatus", new UrgencyStatus());
		return "configurationPanel";
	}

	@RequestMapping(value = "/configuration/urgStatus/add", method = RequestMethod.POST)
	public String saveUrgStatus(@ModelAttribute("urgStatus") UrgencyStatus urgencyStatus,
			RedirectAttributes redirectAttributes) throws SQLException {
		String page = null;
		if (urgencyStatusesService.checkUrgencyStatusExistence(urgencyStatus)) {
			redirectAttributes.addFlashAttribute("unique", "Urgency status with this title exist!");
			page = "redirect:/configuration/urgStatus/add";
		} else {
			urgencyStatusesService.addUrgencyStatus(urgencyStatus);
			redirectAttributes.addFlashAttribute("message", "Urgency status added successfully!");
			page = "redirect:/configuration";
		}
		return page;
	}

	@RequestMapping(value = "/configuration/urgStatus/delete/id/{id}", method = RequestMethod.GET)
	public String deleteUrgStatus(@PathVariable("id") int id, RedirectAttributes redirectAttributes)
			throws SQLException {
		if (ordersService.checkUrgencyStatusOrders(id))
			redirectAttributes.addFlashAttribute("error", "Unable to delete. Orders with this urgency status exist!");
		else {
			urgencyStatusesService.deleteUrgencyStatus(id);
			redirectAttributes.addFlashAttribute("message", "Urgency status deleted successfully!");
		}
		return "redirect:/configuration";
	}
}