package lannisters.devcor.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lannisters.devcor.entity.Device;
import lannisters.devcor.service.DeviceTypesService;
import lannisters.devcor.service.DevicesService;
import lannisters.devcor.service.RoomsService;
import lannisters.devcor.validator.DeviceFormValidator;

@Controller
public class DeviceController {

	@Autowired
	private DevicesService devicesService;

	@Autowired
	private RoomsService roomsService;

	@Autowired
	private DeviceTypesService deviceTypesService;

	@Autowired
	private DeviceFormValidator deviceFormValidator;

	@InitBinder("deviceForm")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(deviceFormValidator);
	}

	@RequestMapping(value = "/devices", method = RequestMethod.GET)
	public String devicesPage(Model model) {
		model.addAttribute("devices", devicesService.getAllDevices());
		return "devices";
	}

	@RequestMapping(value = "/devices/add", method = RequestMethod.GET)
	public String addDevice(Model model) {
		model.addAttribute("deviceForm", new Device());
		model.addAttribute("deviceTypes", deviceTypesService.getAllDeviceTypes());
		model.addAttribute("rooms", roomsService.getAllRooms());
		return "addDevice";
	}

	@RequestMapping(value = "/devices/add", method = RequestMethod.POST)
	public String saveDevice(@ModelAttribute("deviceForm") @Validated Device device, BindingResult result,
			RedirectAttributes redirectAttributes, Model model) throws SQLException {
		if (result.hasErrors()) {
			model.addAttribute("deviceTypes", deviceTypesService.getAllDeviceTypes());
			model.addAttribute("rooms", roomsService.getAllRooms());
			return "addDevice";
		}
		devicesService.addDevice(device);
		redirectAttributes.addFlashAttribute("device", "Device created successfully!");
		return "redirect:/devices";
	}

	@RequestMapping(value = { "/devices/edit/id/{device_id}",
			"/rooms/edit/id/{room_id}/devices/edit/id/{device_id}" }, method = RequestMethod.GET)
	public String editDevice(@PathVariable("device_id") int id, Model model, HttpServletRequest request) {
		model.addAttribute("deviceForm", devicesService.getDeviceById(id));
		model.addAttribute("deviceTypes", deviceTypesService.getAllDeviceTypes());
		model.addAttribute("rooms", roomsService.getAllRooms());
		model.addAttribute("page", request.getServletPath());
		return "editDevice";
	}

	@RequestMapping(value = "/devices/edit/id/{id}", method = RequestMethod.POST)
	public String saveEditedDevice(@ModelAttribute("deviceForm") @Validated Device device, BindingResult result,
			@PathVariable("id") int id, RedirectAttributes redirectAttributes, Model model, HttpServletRequest request)
					throws SQLException {
		if (result.hasErrors()) {
			model.addAttribute("deviceTypes", deviceTypesService.getAllDeviceTypes());
			model.addAttribute("rooms", roomsService.getAllRooms());
			model.addAttribute("page", request.getServletPath());
			return "editDevice";
		}
		devicesService.updateDevice(device);
		redirectAttributes.addFlashAttribute("device", "Device edited successfully!");
		return "redirect:/devices";
	}

	@RequestMapping(value = { "/devices/delete/id/{device_id}",
			"/rooms/edit/id/{room_id}/devices/delete/id/{device_id}" }, method = RequestMethod.GET)
	public String deleteDevice(@PathVariable("device_id") int id, HttpServletRequest request,
			RedirectAttributes redirectAttributes) throws SQLException {
		devicesService.deleteDevice(id);
		String referer = request.getHeader("Referer");
		redirectAttributes.addFlashAttribute("device", "Device deleted successfully!");
		return "redirect:" + referer;
	}

	@RequestMapping(value = "/rooms/edit/id/{id}/device/add", method = RequestMethod.GET)
	public String addDeviceOfRoom(@PathVariable("id") int id, Model model) {
		model.addAttribute("deviceForm", new Device());
		model.addAttribute("room", roomsService.getRoomById(id));
		model.addAttribute("deviceTypes", deviceTypesService.getAllDeviceTypes());
		return "addDeviceOfRoom";
	}

	@RequestMapping(value = "/rooms/edit/id/{room_id}/device/add", method = RequestMethod.POST)
	public String processDeviceOfRoom(@ModelAttribute("deviceForm") @Validated Device device, BindingResult result,
			@PathVariable("room_id") int id, RedirectAttributes redirectAttributes, Model model) throws SQLException {
		if (result.hasErrors()) {
			model.addAttribute("deviceTypes", deviceTypesService.getAllDeviceTypes());
			model.addAttribute("room", roomsService.getRoomById(id));
			return "addDeviceOfRoom";
		}
		devicesService.addDevice(device);
		redirectAttributes.addFlashAttribute("device", "Device created successfully!");
		return "redirect:/rooms/edit/id/" + id;
	}

	@RequestMapping(value = "/rooms/edit/id/{room_id}/devices/edit/id/{device_id}", method = RequestMethod.POST)
	public String saveEditedRoomDevice(@ModelAttribute("deviceForm") @Validated Device device, BindingResult result,
			@PathVariable("room_id") int rid, @PathVariable("device_id") int did, RedirectAttributes redirectAttributes,
			Model model, HttpServletRequest request) throws SQLException {
		if (result.hasErrors()) {
			model.addAttribute("deviceTypes", deviceTypesService.getAllDeviceTypes());
			model.addAttribute("rooms", roomsService.getAllRooms());
			model.addAttribute("page", request.getServletPath());
			return "editDevice";
		}
		devicesService.updateDevice(device);
		redirectAttributes.addFlashAttribute("device", "Device edited successfully!");
		return "redirect:/rooms/edit/id/" + rid;
	}
}