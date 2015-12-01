package lannisters.devcor.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lannisters.devcor.entity.Device;
import lannisters.devcor.service.DeviceTypesService;
import lannisters.devcor.service.DevicesService;
import lannisters.devcor.service.RoomsService;

@Controller
public class DeviceController {

	@Autowired
	private DevicesService devicesService;

	@Autowired
	private RoomsService roomsService;

	@Autowired
	private DeviceTypesService deviceTypesService;

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
	public String saveDevice(@ModelAttribute("device") Device device, RedirectAttributes redirectAttributes)
			throws SQLException {
		String page = null;
		if (devicesService.checkSerialExistence(device)) {
			page = "redirect:/devices/add";
			redirectAttributes.addFlashAttribute("unique", "Device with this serial number exist!");
		} else {
			devicesService.addDevice(device);
			page = "redirect:/devices";
			redirectAttributes.addFlashAttribute("device", "Device created successfully!");
		}
		return page;
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
	public String saveEditedDevice(@ModelAttribute("device") Device device, @PathVariable("id") int id,
			RedirectAttributes redirectAttributes) throws SQLException {
		String page = null;
		if (devicesService.checkSerialExistence(device) && !devicesService.getDeviceById(device.getDeviceId())
				.getDeviceSerialId().equals(device.getDeviceSerialId())) {
			page = "redirect:/devices/edit/id/" + id;
			redirectAttributes.addFlashAttribute("unique", "Device with this serial number exist!");
		} else {
			devicesService.updateDevice(device);
			redirectAttributes.addFlashAttribute("device", "Device edited successfully!");
			page = "redirect:/devices";
		}
		return page;
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
}