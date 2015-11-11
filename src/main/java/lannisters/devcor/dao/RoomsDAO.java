package lannisters.devcor.dao;

import java.util.List;
import lannisters.devcor.entity.Room;

public interface RoomsDAO {

	public List<Room> getAllRooms();

	public Room getRoomById(int roomId);

	public void addRoom(Room room);

	public void deleteRoom(int roomId);
}