package lannisters.devcor.controller;

import java.security.Principal;
import lannisters.devcor.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 
 * @author Maxim
 * @version 1.0
 *
 */

@Controller
public class MainController {

	@Autowired
	private OrdersService ordersService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(Model model,
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {
		if (error != null) {
			model.addAttribute("error", "Invalid username or password!");
		}
		if (logout != null) {
			model.addAttribute("logout", "You've been logged out successfully.");
		}
		return "login";
	}

	@RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
	public String mainPage() {
		return "main";
	}

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String dashboardPage(Model model, Principal principal) {
		model.addAttribute("title", "Orders Info");
		model.addAttribute("message", ordersService.getAllOrders());
		return "dashboard";
	}

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String profilePage(Model model, Principal principal) {
		model.addAttribute("title", "Profile Info");
		model.addAttribute("message",
				"Profile Page - Hello " + principal.getName());
		return "profile";
	}

	@RequestMapping(value = { "/about" }, method = RequestMethod.GET)
	public String aboutPage() {
		return "about";
	}

	@RequestMapping(value = { "/templates" }, method = RequestMethod.GET)
	public String about2Page() {
		return "templates";
	}

	@RequestMapping(value = "/technicians", method = RequestMethod.GET)
	public String techniciansPage() {
		return "technicians";
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String usersPage() {
		return "users";
	}

	@RequestMapping(value = "/rooms", method = RequestMethod.GET)
	public String roomsPage() {
		return "rooms";
	}

	@RequestMapping(value = "/reports", method = RequestMethod.GET)
	public String reportsPage() {
		return "reports";
	}

	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String accessDenied() {
		return "403Page";
	}

	@RequestMapping(value = "/404", method = RequestMethod.GET)
	public String notFound() {
		return "404Page";
	}
}