package lannisters.devcor.service;

import java.sql.SQLException;
import java.util.List;

import lannisters.devcor.entity.Room;

public interface RoomsService {

	public List<Room> getAllRooms();

	public Room getRoomById(int roomId);

	public void addRoom(Room room);

	public void updateRoom(Room room);

	public void deleteRoom(int roomId);

	public int getTechnicianIdByRoomId(int roomId) throws SQLException;

	public List<Room> getPlayerRooms(int playerId);

	public boolean checkPlayerRooms(int playerId);
	
	public Room getRoomByNumber(String roomNumber);
	
	public boolean checkRoomNumberExistence(Room room);
}