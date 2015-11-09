package lannisters.devcor.orm;

import java.sql.ResultSet;
import java.sql.SQLException;
import lannisters.devcor.entity.Player;
import org.springframework.jdbc.core.RowMapper;

/**
 * 
 * @author Maxim
 * @version 1.0
 *
 */

public class PlayerMapper implements RowMapper<Player> {

	/**
	 * Creates Player and fills fields with database values recieved from
	 * ResultSet.
	 * 
	 * @param rs
	 *            ResultSet - table which has all data recieved by query.
	 * @param rowNum
	 *            number of ResultSet rows.
	 * @return Player with fields: playerId, playerEmail, firstName, lastName,
	 *         password, phoneNumber, role.
	 */
	public Player mapRow(ResultSet rs, int rowNum) throws SQLException {
		Player player = new Player();
		player.setPlayerId(rs.getInt("player_id"));
		player.setPlayerEmail(rs.getString("player_email"));
		player.setFirstName(rs.getString("first_name"));
		player.setLastName(rs.getString("last_name"));
		player.setPassword(rs.getString("password"));
		player.setPhoneNumber(rs.getString("phone_number"));
		// player.setRoleId(rs.getInt("role_id"));
		player.setRole(rs.getString("role"));
		return player;
	}
}