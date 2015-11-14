package lannisters.devcor.controller;

import java.security.Principal;
import java.sql.SQLException;

import lannisters.devcor.entity.Order;
import lannisters.devcor.service.CommentsService;
import lannisters.devcor.service.DevicesService;
import lannisters.devcor.service.OrdersService;
import lannisters.devcor.service.PlayersService;
import lannisters.devcor.service.RoomsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

	@Autowired
	private OrdersService ordersService;

	@Autowired
	private CommentsService commentsService;

	@Autowired
	private RoomsService roomsService;

	@Autowired
	private DevicesService devicesService;

	@Autowired
	private PlayersService playersService;

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
	public String dashboardPage(Model model, Principal principal) throws SQLException {
		model.addAttribute("role", SecurityContextHolder.getContext().getAuthentication().getAuthorities().iterator().next().getAuthority());
		switch(SecurityContextHolder.getContext().getAuthentication().getAuthorities().iterator().next().getAuthority()){
			case "ROLE_USER":
				model.addAttribute("orders", ordersService.getALlOrdersOfUser(principal.getName()));
				break;
			case "ROLE_TECHNICIAN":
				model.addAttribute("orders", ordersService.getAllOrdersOfTechnician(principal.getName()));
				break;
			case "ROLE_ADMIN":
				model.addAttribute("orders", ordersService.getFirstOrders(100));
				break;
			default:
				break;
		}
		return "dashboard";
	}
	
	@RequestMapping(value = "/dashboard", method = RequestMethod.POST)
	public String dashboardPagePost(Model model, Order order, Principal principal) throws SQLException {
		model.addAttribute("role", SecurityContextHolder.getContext().getAuthentication().getAuthorities().iterator().next().getAuthority());
		ordersService.updateOrder(order);
		model.addAttribute("message", "Your changes were succesfuully applied");
		switch(SecurityContextHolder.getContext().getAuthentication().getAuthorities().iterator().next().getAuthority()){
		case "ROLE_USER":
			model.addAttribute("orders", ordersService.getALlOrdersOfUser(principal.getName()));
			break;
		case "ROLE_TECHNICIAN":
			model.addAttribute("orders", ordersService.getAllOrdersOfTechnician(principal.getName()));
			break;
		case "ROLE_ADMIN":
			model.addAttribute("orders", ordersService.getFirstOrders(100));
			break;
		default:
			break;
	}
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
	public String techniciansPage(Model model) {
		model.addAttribute("technicians", playersService.getAllPlayers());
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

	@RequestMapping(value = "/devices", method = RequestMethod.GET)
	public String devicesPage() {
		return "devices";
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