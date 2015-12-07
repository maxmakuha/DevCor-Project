package lannisters.devcor.controller;

import java.util.Locale;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(Model model, @RequestParam(value = "error", required = false) String error,
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
		Locale.setDefault(Locale.ENGLISH);
		return "main";
	}

	@RequestMapping(value = { "/about" }, method = RequestMethod.GET)
	public String aboutPage() {
		return "about";
	}

	@RequestMapping(value = { "/templates" }, method = RequestMethod.GET)
	public String about2Page() {
		return "templates";
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