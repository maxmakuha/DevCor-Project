package com.natalia;

import java.util.ArrayList;

import javax.sql.DataSource;

/**
 * this interface shows all the required methods which needs our Room object
 * @author Natalia
 *
 */

public interface RoomDAO {
	
	public void setDataSource(DataSource ds);
	
	public void createRoom(Room aRoom);
	
	public Room getRoom(String aRoomNumber); // ?????
	public ArrayList<Room> getAllRooms();
	public ArrayList<String> getAllDevicesFromCurrentRoom(Room aRoom);  
	public ArrayList<String> getAllTechnicianFromCurrentRoom(Room aRoom); 

	public boolean editRoom(Room aRoom, String aTechnician, String aDevice); // will be Technician aTechnician, Device aDevice
	public boolean editRoomByDevice(Room aRoom, String aDevice);  // will be Device aDevice
	public boolean editRoomByTechnician(Room aRoom, String aTechnician); // will be Technician aTechnician
	
	public boolean deleteRoom(Room aRoom);
	public boolean deleteDeviceFromRoom(Room aRoom, String aDevice); // will be Room aRoom,  Device aDevice
	public boolean deleteTechnicianFromRoom(Room aRoom, String aTechnician); // will be Room aRoom, Technician aTechnician
	public boolean deleteAllDevicesFromRoom(Room aRoom);
	public boolean deleteAllTechniciansFromRoom(Room aRoom);

}
