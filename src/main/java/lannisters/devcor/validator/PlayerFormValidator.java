package lannisters.devcor.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import lannisters.devcor.entity.Player;
import lannisters.devcor.service.PlayersService;

@Component
public class PlayerFormValidator implements Validator {

	@Autowired
	@Qualifier("emailValidator")
	EmailValidator emailValidator;

	@Autowired
	PlayersService playersService;

	@Override
	public boolean supports(Class<?> clazz) {
		return Player.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		Player user = (Player) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty.playerForm.firstName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty.playerForm.lastName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "playerEmail", "NotEmpty.playerForm.playerEmail");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.playerForm.password");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneNumber", "NotEmpty.playerForm.phoneNumber");

		if (!emailValidator.valid(user.getPlayerEmail())) {
			errors.rejectValue("playerEmail", "Pattern.playerForm.playerEmail");
		}

		if (user.getConfirmPassword() != null && !user.getNewPassword().equals(user.getConfirmPassword())) {
			errors.rejectValue("confirmPassword", "Diff.playerForm.confirmPassword");
		}

		if (user.getPassword().length() < 6 || user.getPassword().length() > 64) {
			errors.rejectValue("password", "Size.playerForm.password");
		}

		if (user.getNewPassword() != ""
				&& (user.getNewPassword().length() < 6 || user.getNewPassword().length() > 64)) {
			errors.rejectValue("newPassword", "Size.playerForm.newPassword");
		}

		if (playersService.checkEmailExistence(user)
				&& playersService.getPlayerIdByEmail(user.getPlayerEmail()) != user.getPlayerId()) {
			errors.rejectValue("playerEmail", "Unique.playerForm.playerEmail");
		}
	}
}