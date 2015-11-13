package lannisters.devcor.service;

import java.sql.SQLException;
import java.util.List;

import lannisters.devcor.dao.RoomsDAO;
import lannisters.devcor.entity.Room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomsServiceImpl implements RoomsService {

	@Autowired
	private RoomsDAO roomsDao;

	public List<Room> getAllRooms() {
		return roomsDao.getAllRooms();
	}

	public Room getRoomById(int roomId) {
		return roomsDao.getRoomById(roomId);
	}

	public void addRoom(Room room) throws SQLException {
		roomsDao.addRoom(room);
	}

	public void updateRoom(Room room) throws SQLException {
		roomsDao.updateRoom(room);
	}

	public void deleteRoom(int roomId) throws SQLException {
		roomsDao.deleteRoom(roomId);
	}
}