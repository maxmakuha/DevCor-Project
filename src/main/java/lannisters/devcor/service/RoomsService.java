package lannisters.devcor.service;

import java.util.List;
import lannisters.devcor.entity.Room;

/**
 * 
 * @author Maxim
 * @version 1.0
 *
 */

public interface RoomsService {

	public List<Room> getAllRooms();
	
	public Room getRoomById(int roomId);
	
	public void addRoom(Room room);

	public void deleteRoom(int roomId);
}