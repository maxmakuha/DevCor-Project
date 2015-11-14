package lannisters.devcor.dao;

import java.sql.SQLException;
import java.util.List;

import lannisters.devcor.entity.Room;

public interface RoomsDAO {

	public List<Room> getAllRooms();

	public Room getRoomById(int roomId);

	public void addRoom(Room room) throws SQLException;

	public void updateRoom(Room room) throws SQLException;

	public void deleteRoom(int roomId) throws SQLException;
	
	public int getTechnicianIdByRoomId(int roomId) throws SQLException;
}