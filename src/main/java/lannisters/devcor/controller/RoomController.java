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

import lannisters.devcor.entity.Device;
import lannisters.devcor.entity.Room;
import lannisters.devcor.service.DeviceTypesService;
import lannisters.devcor.service.DevicesService;
import lannisters.devcor.service.PlayersService;
import lannisters.devcor.service.RoomsService;

@Controller
public class RoomController {

	@Autowired
	private PlayersService playersService;

	@Autowired
	private DevicesService devicesService;

	@Autowired
	private DeviceTypesService deviceTypesService;

	@Autowired
	private RoomsService roomsService;

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
	public String saveRoom(@ModelAttribute("room") Room room, RedirectAttributes redirectAttributes)
			throws SQLException {
		String page = null;
		if (roomsService.checkRoomNumberExistence(room)) {
			redirectAttributes.addFlashAttribute("unique", "Room with this number exist!");
			page = "redirect:/rooms/add";
		} else {
			roomsService.addRoom(room);
			redirectAttributes.addFlashAttribute("room", "Room created successfully!");
			page = "redirect:/rooms";
		}
		return page;
	}

	@RequestMapping(value = "/rooms/edit/id/{id}", method = RequestMethod.GET)
	public String editRoom(@PathVariable("id") int id, Model model) {
		model.addAttribute("room", roomsService.getRoomById(id));
		model.addAttribute("technicians", playersService.getAllTechnicians());
		model.addAttribute("roomDevices", devicesService.getAllDevicesOfRoom(id));
		return "editRoom";
	}

	@RequestMapping(value = "/rooms/edit/id/{id}", method = RequestMethod.POST)
	public String saveEditedRoom(@ModelAttribute("room") Room room, @PathVariable("id") int id,
			RedirectAttributes redirectAttributes) throws SQLException {
		String page = null;
		if (roomsService.checkRoomNumberExistence(room)) {
			redirectAttributes.addFlashAttribute("unique", "Room with this number exist!");
			page = "redirect:/rooms/edit/id/" + id;
		} else {
			roomsService.updateRoom(room);
			redirectAttributes.addFlashAttribute("room", "Room edited successfully!");
			page = "redirect:/rooms";
		}
		return page;
	}

	@RequestMapping(value = "/rooms/delete/id/{id}", method = RequestMethod.GET)
	public String deleteRoom(@PathVariable("id") int id, RedirectAttributes redirectAttributes) throws SQLException {
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
			RedirectAttributes redirectAttributes) throws SQLException {
		String page = null;
		if (devicesService.checkSerialExistence(device)) {
			page = "redirect:/rooms/edit/id/" + id + "/device/add";
			redirectAttributes.addFlashAttribute("unique", "Device with this serial number exist!");
		} else {
			devicesService.addDevice(device);
			page = "redirect:/rooms/edit/id/" + id;
			redirectAttributes.addFlashAttribute("device", "Device created successfully!");
		}
		return page;
	}
	
	@RequestMapping(value = "/rooms/edit/id/{room_id}/devices/edit/id/{device_id}", method = RequestMethod.POST)
	public String saveEditedRoomDevice(@ModelAttribute("device") Device device, @PathVariable("room_id") int rid,
			@PathVariable("device_id") int did, RedirectAttributes redirectAttributes) throws SQLException {
		String page = null;
		if (devicesService.checkSerialExistence(device) && !devicesService.getDeviceById(device.getDeviceId())
				.getDeviceSerialId().equals(device.getDeviceSerialId())) {
			page = "redirect:/rooms/edit/id/" + rid + "/devices/edit/id/" + did;
			redirectAttributes.addFlashAttribute("unique", "Device with this serial number exist!");
		} else {
			devicesService.updateDevice(device);
			redirectAttributes.addFlashAttribute("device", "Device edited successfully!");
			page = "redirect:/rooms/edit/id/" + rid;
		}
		return page;
	}
}