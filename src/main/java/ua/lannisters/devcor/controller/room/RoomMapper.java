package com.natalia;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * RoomMapper class
 * @author Natalia
 *
 */

public class RoomMapper implements RowMapper{

	@Override
	public Room mapRow(ResultSet resSet, int rowNumb) throws SQLException {
		Room theRoom = new Room();
		theRoom.setRoomId(resSet.getInt("Id"));
		theRoom.setRoomNumber(resSet.getString("RoomNumber"));
		theRoom.setDevice(resSet.getString("Device"));
		theRoom.setTechnician("Technician");
		return theRoom;
	}
}