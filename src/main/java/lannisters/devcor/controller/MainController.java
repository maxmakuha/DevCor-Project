package lannisters.devcor.controller;

import java.security.Principal;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lannisters.devcor.entity.Player;
import lannisters.devcor.service.PlayersService;

@Controller
public class MainController {

	@Autowired
	private PlayersService playersService;

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

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String profile(Model model, Principal principal) {
		model.addAttribute("profile", playersService.getPlayerByEmail(principal.getName()));
		return "profile";
	}

	@RequestMapping(value = "/profile", method = RequestMethod.POST)
	public String saveProfile(@ModelAttribute("profile") Player player, Principal principal, Model model,
			RedirectAttributes redirectAttributes) {
		String page = null;
		if (playersService.checkEmailExistence(player)
				&& playersService.getPlayerIdByEmail(player.getPlayerEmail()) != player.getPlayerId()) {
			page = "redirect:/profile";
			redirectAttributes.addFlashAttribute("unique", "User with this email exist!");
		} else {
			playersService.updatePlayer(player);
			if (principal.getName().equals(player.getPlayerEmail())) {
				page = "redirect:/dashboard";
				redirectAttributes.addFlashAttribute("profile", "Profile edited successfully!");
			} else {
				page = "redirect:/logout";
			}
		}
		return page;
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