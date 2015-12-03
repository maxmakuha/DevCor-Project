package lannisters.devcor.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import lannisters.devcor.entity.DeviceType;
import lannisters.devcor.service.DeviceTypesService;

@Component
public class DeviceTypeFormValidator implements Validator {

	@Autowired
	DeviceTypesService deviceTypesService;

	@Override
	public boolean supports(Class<?> clazz) {
		return DeviceType.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		DeviceType deviceType = (DeviceType) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "deviceType", "NotEmpty.deviceTypeForm.deviceType");

		if (deviceType.getDeviceType().length() > 32) {
			errors.rejectValue("deviceType", "Size.deviceTypeForm.deviceType");
		}
	}
}