package lannisters.devcor.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import lannisters.devcor.entity.UrgencyStatus;
import lannisters.devcor.service.UrgencyStatusesService;

@Component
public class UrgencyStatusFormValidator implements Validator {

	@Autowired
	UrgencyStatusesService UrgencyStatusesService;

	@Override
	public boolean supports(Class<?> clazz) {
		return UrgencyStatus.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		UrgencyStatus urgencyStatus = (UrgencyStatus) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "urgencyStatus", "NotEmpty.urgencyStatusForm.urgencyStatus");

		if (urgencyStatus.getUrgencyStatus().length() > 20) {
			errors.rejectValue("urgencyStatus", "Size.urgencyStatusForm.urgencyStatus");
		}
	}
}