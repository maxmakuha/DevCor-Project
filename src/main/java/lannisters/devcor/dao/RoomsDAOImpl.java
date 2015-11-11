package lannisters.devcor.dao;

import java.util.List;

import lannisters.devcor.entity.Room;
import lannisters.devcor.orm.RoomMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RoomsDAOImpl implements RoomsDAO {

	private static final String SQL_SELECT_ALL_ROOMS = "SELECT room.room_id, room.room_number, player.first_name, player.last_name FROM room INNER JOIN player ON room.player_id = player.player_id";
	private static final String SQL_SELECT_ROOM_BY_ID = "SELECT room.room_id, room.room_number, player.first_name, player.last_name FROM room INNER JOIN player ON room.player_id = player.player_id WHERE room.room_id=?";
	private static final String SQL_INSERT_ROOM = "INSERT INTO room(room_number, player_id) VALUES (?, ?)";
	private static final String SQL_DELETE_ROOM = "DELETE room WHERE room_id=?";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Room> getAllRooms() {
		return jdbcTemplate.query(SQL_SELECT_ALL_ROOMS, new RoomMapper());
	}

	public Room getRoomById(int roomId) {
		return jdbcTemplate.queryForObject(SQL_SELECT_ROOM_BY_ID,
				new RoomMapper(), roomId);
	}

	public void addRoom(Room room) {
		jdbcTemplate.update(SQL_INSERT_ROOM, room);
	}

	public void deleteRoom(int roomId) {
		jdbcTemplate.update(SQL_DELETE_ROOM, roomId);
	}
}