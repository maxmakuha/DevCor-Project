package lannisters.devcor.orm;

import java.sql.ResultSet;
import java.sql.SQLException;
import lannisters.devcor.entity.Player;
import org.springframework.jdbc.core.RowMapper;

public class PlayerMapper implements RowMapper<Player> {

	public Player mapRow(ResultSet rs, int rowNum) throws SQLException {
		Player player = new Player();
		player.setPlayerId(rs.getInt("player_id"));
		player.setPlayerEmail(rs.getString("player_email"));
		player.setFirstName(rs.getString("first_name"));
		player.setLastName(rs.getString("last_name"));
		player.setPassword(rs.getString("password"));
		player.setPhoneNumber(rs.getString("phone_number"));
		// player.setRoleId(rs.getInt("role_id"));
		//player.setRole(rs.getString("role"));
		return player;
	}
}