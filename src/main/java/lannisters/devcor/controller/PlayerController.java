package lannisters.devcor.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.RandomStringUtils;
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

import lannisters.devcor.entity.Player;
import lannisters.devcor.mail.MailService;
import lannisters.devcor.service.PlayersService;
import lannisters.devcor.service.RoomsService;
import lannisters.devcor.validator.PlayerFormValidator;

@Controller
public class PlayerController {

	@Autowired
	private PlayersService playersService;

	@Autowired
	private RoomsService roomsService;

	@Autowired
	private MailService mail;

	@Autowired
	private PlayerFormValidator playerFormValidator;

	@InitBinder("playerForm")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(playerFormValidator);
	}

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

	@RequestMapping(value = { "/technicians/add", "/users/add" }, method = RequestMethod.GET)
	public String addUser(Model model) {
		Player user = new Player();
		user.setPassword(RandomStringUtils.randomAlphanumeric(15));
		model.addAttribute("playerForm", user);
		return "addUser";
	}

	@RequestMapping(value = { "/technicians/add", "/users/add" }, method = RequestMethod.POST)
	public String saveTechnician(@ModelAttribute("playerForm") @Validated Player user, BindingResult result,
			RedirectAttributes redirectAttributes, HttpServletRequest request) throws SQLException {
		String page = null;
		if (result.hasErrors()) {
			return "addUser";
		} else {
			if (request.getRequestURL().toString().contains("technicians")) {
				user.setRoleId(2);
				page = "redirect:/technicians";
				redirectAttributes.addFlashAttribute("tech", "Technician created successfully!");
			} else {
				user.setRoleId(3);
				page = "redirect:/users";
				redirectAttributes.addFlashAttribute("user", "User created successfully!");
			}
			mail.registrationEmail(user);
			user.setPassword(playersService.encodePassword(user.getPassword()));
			playersService.addPlayer(user);
		}
		return page;
	}

	@RequestMapping(value = { "/technicians/edit/id/{id}", "/users/edit/id/{id}" }, method = RequestMethod.GET)
	public String editUser(@PathVariable("id") int id, Model model) {
		model.addAttribute("playerForm", playersService.getPlayerById(id));
		return "editUser";
	}

	@RequestMapping(value = { "/technicians/edit/id/{id}", "/users/edit/id/{id}" }, method = RequestMethod.POST)
	public String saveEditedUser(@ModelAttribute("playerForm") @Validated Player user, BindingResult result,
			@PathVariable("id") int id, RedirectAttributes redirectAttributes) throws SQLException {
		String page = null;
		if (result.hasErrors()) {
			return "editUser";
		} else {
			if (!user.getNewPassword().equals(""))
				user.setPassword(playersService.encodePassword(user.getNewPassword()));
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
	public String deleteTechnician(@PathVariable("id") int id, RedirectAttributes redirectAttributes)
			throws SQLException {
		if (roomsService.checkPlayerRooms(id))
			redirectAttributes.addFlashAttribute("error", "Unable to delete. Technician attached to some room!");
		else {
			playersService.deletePlayer(id);
			redirectAttributes.addFlashAttribute("tech", "Technician deleted successfully!");
		}
		return "redirect:/technicians";
	}

	@RequestMapping(value = "/users/delete/id/{id}", method = RequestMethod.GET)
	public String deleteUser(@PathVariable("id") int id, RedirectAttributes redirectAttributes) throws SQLException {
		playersService.deletePlayer(id);
		redirectAttributes.addFlashAttribute("user", "User deleted successfully!");
		return "redirect:/users";
	}
}