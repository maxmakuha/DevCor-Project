package lannisters.devcor.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import lannisters.devcor.entity.ProblemType;
import lannisters.devcor.service.ProblemTypesService;

@Component
public class ProblemTypeFormValidator implements Validator {

	@Autowired
	ProblemTypesService problemTypesService;

	@Override
	public boolean supports(Class<?> clazz) {
		return ProblemType.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		ProblemType problemType = (ProblemType) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "problemType", "NotEmpty.problemTypeForm.problemType");

		if (problemType.getProblemType().length() > 20) {
			errors.rejectValue("problemType", "Size.problemTypeForm.problemType");
		}
	}
}