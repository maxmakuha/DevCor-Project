package lannisters.devcor.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import lannisters.devcor.entity.Device;
import lannisters.devcor.service.DevicesService;

@Component
public class DeviceFormValidator implements Validator {

	@Autowired
	DevicesService devicesService;

	@Override
	public boolean supports(Class<?> clazz) {
		return Device.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		Device device = (Device) target;
		device.setDeviceSerialId(device.getDeviceSerialId().replaceAll(" ", ""));

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "deviceSerialId", "NotEmpty.deviceForm.deviceSerialId");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "deviceTypeId", "NotEmpty.deviceForm.deviceTypeId");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "roomId", "NotEmpty.deviceForm.roomId");

		if (device.getDeviceTypeId() == 0) {
			errors.rejectValue("deviceTypeId", "NotEmpty.deviceForm.deviceTypeId");
		}

		if (device.getRoomId() == 0) {
			errors.rejectValue("roomId", "NotEmpty.deviceForm.roomId");
		}

		if (devicesService.checkSerialExistence(device)
				&& devicesService.getDeviceBySerial(device.getDeviceSerialId()).getDeviceId() != device.getDeviceId()) {
			errors.rejectValue("deviceSerialId", "Unique.deviceForm.deviceSerialId");
		}

		if (device.getDeviceSerialId().length() > 15) {
			errors.rejectValue("deviceSerialId", "Size.deviceForm.deviceSerialId");
		}
	}
}