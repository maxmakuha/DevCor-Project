package com.natalia;

 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
 
/**
 * this class implements RoomDao interface
 * @author Natalia
 *
 */

public class RoomDAOImplementation implements RoomDAO {
 
	private DataSource dataSourse;
	private JdbcTemplate jdbcTemplate;
 
	/**
	 * in this method we install our database
	 * @param ds
	 */
	
	@Override
	public void setDataSource(DataSource ds) {
		this.dataSourse = ds;
		jdbcTemplate = new JdbcTemplate(dataSourse);
	}

	/**
	 * this method insert objects into Room table
	 * @param aRoom
	 */
	
	@Override
	public void createRoom(Room aRoom) {
		String insertIntoRoom = "insert into Room (location, device) values(?, ?)";
		jdbcTemplate.update(insertIntoRoom, new Object[] {
			aRoom.getRoomNumber(), aRoom.getDevice()	
		});
	}
	
	/**
	 * this method returns all Rooms, which are in our Room database
	 * @return
	 */

	@Override
	public ArrayList<Room> getAllRooms() {
		String getAllRooms = "select * from Room";
		ArrayList<Room> roomList = new ArrayList<Room>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(getAllRooms);
		for (Map row : rows) {
			Room currentRoom = new Room();
			currentRoom.setRoomId(Integer.parseInt(String.valueOf(row.get("Id"))));
			currentRoom.setRoomNumber((String) row.get("RoomNumber"));
			currentRoom.setDevice((String) row.get("Device"));
			currentRoom.setTechnician((String) row.get("Technician"));
			roomList.add(currentRoom);
		}
		return roomList;
	}
	
	/**
	 * this method returns current room by roomNumber
	 * @param aRoomNumber
	 * @return
	 */

	@Override
	public Room getRoom(String aRoomNumber) {
		if (aRoomNumber != null) {
			String getRoomSQL = "select * from Room where RoomNumber = ?";
			Room currentRoom = jdbcTemplate.queryForObject(getRoomSQL, new Object[] {
					aRoomNumber}, new RoomMapper());
			return currentRoom;
		} else {
			return null;
		}
	}
	
	/**
	 * this method returns all technicians, which are assigned to specific room
	 * @param aRoom
	 * @return
	 */

	@Override
	public ArrayList<String> getAllTechnicianFromCurrentRoom(Room aRoom) {
		if (aRoom != null) {
			String getTechnicialSQL = "select * from Room where ";  // edit sql request
			ArrayList<String> techniciansList = jdbcTemplate.queryForObject(getTechnicialSQL, new RoomMapper());
			return techniciansList;
		} else {
			return null;
		}
		
	}
	
	/**
	 * this method returns all devices, which are assigned to specific room
	 * @param aRoom
	 * @return
	 */

	@Override
	public ArrayList<String> getAllDevicesFromCurrentRoom(Room aRoom) {
		if (aRoom != null) {
			String getDevicesSQL = "select * from Room where ";  // edit sql request
			ArrayList<String> devicesList = jdbcTemplate.queryForObject(getDevicesSQL, new RoomMapper());
			return devicesList;
		} else {
			return null;
		}
	}
	
	/**
	 * this method allows to edit some information in our Room database. We can change 
	 * - a technician
	 * - a device
	 * @param aRoom
	 * @param aTechnician
	 * @param aDevice
	 * @return
	 */

	@Override
	public boolean editRoom(Room aRoom, String aTechnician, String aDevice) {
		if (aRoom != null && aTechnician != null && aDevice != null) {
			String updateRoom = "update Room set Technician = ?, Device = ? where RoomNumber = ?";
			jdbcTemplate.update(updateRoom, aTechnician, aDevice, aRoom.getRoomNumber());
			return true;
		} else {
			return false;
		}
		
	}

	/**
	 * this method allows to edit a device in our Room database.
	 * @param aRoom
	 * @param aDevice
	 * @return
	 */
	
	@Override
	public boolean editRoomByDevice(Room aRoom, String aDevice) {
		if (aRoom != null && aDevice != null) {
			String updateRoom = "update Room set Device = ? where RoomNumber = ?";
			jdbcTemplate.update(updateRoom, aDevice, aRoom.getRoomNumber());
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * this method allows to edit a technician in our Room database.
	 * @param aRoom
	 * @param aTechnician
	 * @return
	 */

	@Override
	public boolean editRoomByTechnician(Room aRoom, String aTechnician) {
		if (aRoom != null && aTechnician != null) {
			String updateRoom = "update Room set Technician = ? where RoomNumber = ?";
			jdbcTemplate.update(updateRoom, aTechnician, aRoom.getRoomNumber());
			return true;
		} else {
			return false;
		}
	}

	/**
	 * this method allows delete a specific room from Room database
	 * @param aRoom
	 * @return
	 */
	
	@Override
	public boolean deleteRoom(Room aRoom) {
		if (aRoom != null)  {
			String deleteRoom = "delete * from Room";
			jdbcTemplate.update(deleteRoom);
			return true;
		} else {
			return false;
		}	
	}

	/**
	 * this method allows delete a technician from Room database
	 * @param aRoom
	 * @param aTechnician
	 * @return
	 */
	
	@Override
	public boolean deleteTechnicianFromRoom(Room aRoom, String aTechnician) {
		if (aRoom != null && aTechnician != null)  {
			String deleteRoom = "delete Technician from Room where Technician = ";
			jdbcTemplate.update(deleteRoom, aTechnician);
			return true;
		} else {
			return false;
		}	
	}

	/**
	 * this method allows delete a device from Room database
	 * @param aRoom
	 * @param aDevice
	 * @return
	 */
	
	@Override
	public boolean deleteDeviceFromRoom(Room aRoom, String aDevice) {
		if (aRoom != null && aDevice != null)  {
			String deleteRoom = "delete Device from Room where Device = ";
			jdbcTemplate.update(deleteRoom, aDevice);
			return true;
		} else {
			return false;
		}	
	}

	/**
	 * this method allows delete all technicians from Room database
	 * @param aRoom
	 * @return
	 */
	
	@Override
	public boolean deleteAllTechniciansFromRoom(Room aRoom) {
		if (aRoom != null)  {
			String deleteRoom = "delete Technician from Room";
			jdbcTemplate.update(deleteRoom);
			return true;
		} else {
			return false;
		}	
	}

	/**
	 * this method allows delete all devices from Room database
	 * @param aRoom
	 * @return
	 */
	
	@Override
	public boolean deleteAllDevicesFromRoom(Room aRoom) {
		if (aRoom != null)  {
			String deleteRoom = "delete Device from Room";
			jdbcTemplate.update(deleteRoom);
			return true;
		} else {
			return false;
		}
	}
}