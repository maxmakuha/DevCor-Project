package com.natalia;

import java.util.ArrayList;

import javax.sql.DataSource;

public interface RoomDAO {
	
	public void setDataSource(DataSource ds);
	public void createRoom(String aBuilding, String aDevice);
	public Room getRoom(String aNumber);
	public ArrayList<Room> roomList();
	public void deleteCurrentRoom(Room aRoom);
	public void deleteDevice(String aDevice); // will be Device aDevice
	public void deleteAllDevices();
	public void update(String aRoom, String aDevice);
	public void selectTechnician(String aTechnician); // will be Technician aDevice
	public void deleteTechnician(String aTechnician);
}
