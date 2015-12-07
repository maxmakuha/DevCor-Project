package lannisters.devcor.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import lannisters.devcor.entity.Room;
import lannisters.devcor.service.RoomsService;

@Component
public class RoomFormValidator implements Validator {

	@Autowired
	RoomsService roomsService;

	@Override
	public boolean supports(Class<?> clazz) {
		return Room.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		Room room = (Room) target;
		room.setRoomNumber(room.getRoomNumber().replaceAll(" ", ""));

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "roomNumber", "NotEmpty.roomForm.roomNumber");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "playerId", "NotEmpty.roomForm.playerId");

		if (room.getPlayerId() == 0) {
			errors.rejectValue("playerId", "NotEmpty.roomForm.playerId");
		}

		if (roomsService.checkRoomNumberExistence(room)
				&& roomsService.getRoomByNumber(room.getRoomNumber()).getRoomId() != room.getRoomId()) {
			errors.rejectValue("roomNumber", "Unique.roomForm.roomNumber");
		}

		if (room.getRoomNumber().length() > 16) {
			errors.rejectValue("roomNumber", "Size.roomForm.roomNumber");
		}
	}
}