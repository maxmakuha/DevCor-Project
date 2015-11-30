package lannisters.devcor.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import lannisters.devcor.entity.Room;
import lannisters.devcor.orm.RoomMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RoomsDAOImpl implements RoomsDAO {

	private static final String SQL_SELECT_ALL_ROOMS = "SELECT room.room_id, room.room_number, player.player_id, player.first_name, player.last_name FROM room INNER JOIN player ON room.player_id = player.player_id";
	private static final String SQL_SELECT_ROOM_BY_ID = "SELECT room.room_id, room.room_number, player.player_id, player.first_name, player.last_name FROM room INNER JOIN player ON room.player_id = player.player_id WHERE room.room_id=?";
	private static final String SQL_INSERT_ROOM = "INSERT INTO room(room_number, player_id) VALUES (?, ?)";
	private static final String SQL_UPDATE_ROOM = "UPDATE room SET room_number = ?, player_id = ? WHERE room_id = ?";
	private static final String SQL_DELETE_ROOM = "DELETE room WHERE room_id=?";
	private static final String SQL_GET_PLAYER_ID_BY_ROOM_ID = "SELECT room.player_id from room WHERE room.room_id = ?";
	private static final String SQL_GET_PLAYER_ROOMS = "SELECT room.room_id, room.room_number, player.player_id, player.first_name, player.last_name FROM room INNER JOIN player ON room.player_id = player.player_id WHERE room.player_id = ?";
	private static final String SQL_GET_ROOM_BY_NUMBER = "SELECT room.room_id, room.room_number, player.player_id, player.first_name, player.last_name FROM room INNER JOIN player ON room.player_id = player.player_id WHERE room.room_number = ?";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Room> getAllRooms() {
		return jdbcTemplate.query(SQL_SELECT_ALL_ROOMS, new RoomMapper());
	}

	@Override
	public Room getRoomById(int roomId) {
		return jdbcTemplate.queryForObject(SQL_SELECT_ROOM_BY_ID, new RoomMapper(), roomId);
	}

	@Override
	public void addRoom(Room room) throws SQLException {
		PreparedStatement ps = null;
		ps = jdbcTemplate.getDataSource().getConnection().prepareStatement(SQL_INSERT_ROOM);
		ps.setString(1, room.getRoomNumber());
		ps.setInt(2, room.getPlayerId());
		ps.executeUpdate();
		if (ps != null)
				ps.close();
	}

	@Override
	public void updateRoom(Room room) throws SQLException {
		PreparedStatement ps = null;
		ps = jdbcTemplate.getDataSource().getConnection().prepareStatement(SQL_UPDATE_ROOM);
		ps.setString(1, room.getRoomNumber());
		ps.setInt(2, room.getPlayerId());
		ps.setInt(3, room.getRoomId());
		ps.executeUpdate();
		if (ps != null)
				ps.close();
	}

	@Override
	public void deleteRoom(int roomId) throws SQLException {
		PreparedStatement ps = null;
		ps = jdbcTemplate.getDataSource().getConnection().prepareStatement(SQL_DELETE_ROOM);
		ps.setInt(1, roomId);
		ps.executeUpdate();
		if (ps != null)
				ps.close();
	}

	@Override
	public int getTechnicianIdByRoomId(int roomId) throws SQLException {
		return jdbcTemplate.queryForInt(SQL_GET_PLAYER_ID_BY_ROOM_ID, roomId);
	}

	@Override
	public List<Room> getPlayerRooms(int playerId) {
		return jdbcTemplate.query(SQL_GET_PLAYER_ROOMS, new RoomMapper(), playerId);
	}

	@Override
	public Room getRoomByNumber(String roomNumber) {
		return jdbcTemplate.queryForObject(SQL_GET_ROOM_BY_NUMBER, new RoomMapper(), roomNumber);
	}
}