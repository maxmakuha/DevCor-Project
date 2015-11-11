package lannisters.devcor.orm;

import java.sql.ResultSet;
import java.sql.SQLException;
import lannisters.devcor.entity.Room;
import org.springframework.jdbc.core.RowMapper;

public class RoomMapper implements RowMapper<Room> {

	public Room mapRow(ResultSet rs, int rowNum) throws SQLException {
		Room room = new Room();
		room.setRoomId(rs.getInt("room_id"));
		room.setRoomNumber(rs.getString("room_number"));
		// room.setPlayerId(rs.getInt("player_id"));
		room.setPlayerName(rs.getString("first_name"));
		room.setPlayerSurname(rs.getString("last_name"));
		return room;
	}
}