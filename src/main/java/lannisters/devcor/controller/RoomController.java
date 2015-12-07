package lannisters.devcor.controller;

import java.sql.SQLException;

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

import lannisters.devcor.entity.Room;
import lannisters.devcor.service.DevicesService;
import lannisters.devcor.service.PlayersService;
import lannisters.devcor.service.RoomsService;
import lannisters.devcor.validator.RoomFormValidator;

@Controller
public class RoomController {

	@Autowired
	private PlayersService playersService;

	@Autowired
	private DevicesService devicesService;

	@Autowired
	private RoomsService roomsService;

	@Autowired
	private RoomFormValidator roomFormValidator;

	@InitBinder("roomForm")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(roomFormValidator);
	}

	@RequestMapping(value = "/rooms", method = RequestMethod.GET)
	public String roomsPage(Model model) {
		model.addAttribute("rooms", roomsService.getAllRooms());
		return "rooms";
	}

	@RequestMapping(value = "/rooms/add", method = RequestMethod.GET)
	public String addRoom(Model model) {
		model.addAttribute("roomForm", new Room());
		model.addAttribute("technicians", playersService.getAllTechnicians());
		return "addRoom";
	}

	@RequestMapping(value = "/rooms/add", method = RequestMethod.POST)
	public String saveRoom(@ModelAttribute("roomForm") @Validated Room room, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) throws SQLException {
		if (result.hasErrors()) {
			model.addAttribute("technicians", playersService.getAllTechnicians());
			return "addRoom";
		}
		roomsService.addRoom(room);
		redirectAttributes.addFlashAttribute("room", "Room created successfully!");
		return "redirect:/rooms";
	}

	@RequestMapping(value = "/rooms/edit/id/{id}", method = RequestMethod.GET)
	public String editRoom(@PathVariable("id") int id, Model model) {
		model.addAttribute("roomForm", roomsService.getRoomById(id));
		model.addAttribute("technicians", playersService.getAllTechnicians());
		model.addAttribute("roomDevices", devicesService.getAllDevicesOfRoom(id));
		return "editRoom";
	}

	@RequestMapping(value = "/rooms/edit/id/{id}", method = RequestMethod.POST)
	public String saveEditedRoom(@ModelAttribute("roomForm") @Validated Room room, BindingResult result,
			@PathVariable("id") int id, RedirectAttributes redirectAttributes, Model model) throws SQLException {
		if (result.hasErrors()) {
			model.addAttribute("technicians", playersService.getAllTechnicians());
			model.addAttribute("roomDevices", devicesService.getAllDevicesOfRoom(id));
			return "editRoom";
		}
		roomsService.updateRoom(room);
		redirectAttributes.addFlashAttribute("room", "Room edited successfully!");
		return "redirect:/rooms";
	}

	@RequestMapping(value = "/rooms/delete/id/{id}", method = RequestMethod.GET)
	public String deleteRoom(@PathVariable("id") int id, RedirectAttributes redirectAttributes) throws SQLException {
		roomsService.deleteRoom(id);
		redirectAttributes.addFlashAttribute("room", "Room deleted successfully!");
		return "redirect:/rooms";
	}
}