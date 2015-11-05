package com.natalia;

 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;
 
public class RoomJDBC implements RoomDAO {
 
	private DataSource dataSourse;
 
	@Override
	public void setDataSource(DataSource ds) {
		this.dataSourse = ds;
	}

	@Override
	public void createRoom(String aBuilding, String aDevice) {
		String insertIntoRoom = "insert into Room (location, device) values(?,?)";
	}

	@Override
	public Room getRoom(String aNumber) {
		String getRoom = "select * from Room where location = ?";
		return null;
	}

	@Override
	public ArrayList<Room> roomList() {
		String getAllRooms = "select * from Room";
		return null;
	}

	@Override
	public void update(String aRoom, String aDevice) {
		String update = "update Room set device = ? where location = ?";
	}

		@Override
	public void deleteDevice(String aDevice) {
		String deleteCurrentDevice = "delete from Room where location, device  = (?,?)";

	}

	public void deleteAllDevices() {
		String deleteAllDevices = "delete * from Room";
		
	}

	@Override
	public void deleteCurrentRoom(Room aRoom) {
		String deleteCurrentRoom = "delete from Room where location, device  = (?,?)";

	}
	@Override
	public void selectTechnician(String aTechnician) {
		String getTechnician = "select from Room where technician = ?";
	} 
	
	@Override
	public void deleteTechnician(String aTechnician) {
		String deleteTechnician = "delete from Room where aTechnician  = ?";

	}
}