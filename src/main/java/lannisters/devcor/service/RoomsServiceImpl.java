package lannisters.devcor.service;

import java.sql.SQLException;
import java.util.List;

import lannisters.devcor.dao.RoomsDAO;
import lannisters.devcor.entity.Room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class RoomsServiceImpl implements RoomsService {

	@Autowired
	private RoomsDAO roomsDao;

	@Override
	public List<Room> getAllRooms() {
		return roomsDao.getAllRooms();
	}

	@Override
	public Room getRoomById(int roomId) {
		return roomsDao.getRoomById(roomId);
	}

	@Override
	public void addRoom(Room room) throws SQLException {
		roomsDao.addRoom(room);
	}

	@Override
	public void updateRoom(Room room) throws SQLException {
		roomsDao.updateRoom(room);
	}

	@Override
	public void deleteRoom(int roomId) throws SQLException {
		roomsDao.deleteRoom(roomId);
	}

	@Override
	public int getTechnicianIdByRoomId(int roomId) {
		return roomsDao.getTechnicianIdByRoomId(roomId);
	}

	@Override
	public List<Room> getPlayerRooms(int playerId) {
		return roomsDao.getPlayerRooms(playerId);
	}

	@Override
	public boolean checkPlayerRooms(int playerId) {
		boolean existence;
		List<Room> rooms = roomsDao.getPlayerRooms(playerId);
		if (rooms.isEmpty())
			existence = false;
		else
			existence = true;
		return existence;
	}

	@Override
	public Room getRoomByNumber(String roomNumber) {
		return roomsDao.getRoomByNumber(roomNumber);
	}

	@Override
	public boolean checkRoomNumberExistence(Room room) {
		boolean existence;
		try {
			roomsDao.getRoomByNumber(room.getRoomNumber());
			existence = true;
		} catch (EmptyResultDataAccessException e) {
			existence = false;
		}
		return existence;
	}
}