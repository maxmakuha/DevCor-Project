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

	public void addRoom(Room room) {
		try {
			roomsDao.addRoom(room);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateRoom(Room room) {
		try {
			roomsDao.updateRoom(room);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteRoom(int roomId) {
		try {
			roomsDao.deleteRoom(roomId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getTechnicianIdByRoomId(int roomId) throws SQLException {
		return roomsDao.getTechnicianIdByRoomId(roomId);
	}
}