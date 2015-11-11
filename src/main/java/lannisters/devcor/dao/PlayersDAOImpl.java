package lannisters.devcor.dao;

import java.util.List;

import lannisters.devcor.entity.Player;
import lannisters.devcor.orm.PlayerMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PlayersDAOImpl implements PlayersDAO {

	private static final String SQL_SELECT_ALL_PLAYERS = "SELECT player.player_id, player.player_email, player.first_name, player.last_name, player.password, player.phone_number, role.role FROM player INNER JOIN role ON player.role_id = role.role_id";
	private static final String SQL_SELECT_PLAYER_BY_ID = "SELECT player.player_id, player.player_email, player.first_name, player.last_name, player.password, player.phone_number, role.role FROM player INNER JOIN role ON player.role_id = role.role_id WHERE player.player_id=?";
	private static final String SQL_INSERT_PLAYER = "INSERT INTO player(player_email, first_name, last_name, password, phone_number, role_id) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String SQL_DELETE_PLAYER = "DELETE player WHERE player_id=?";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Player> getAllPlayers() {
		return jdbcTemplate.query(SQL_SELECT_ALL_PLAYERS, new PlayerMapper());
	}

	public Player getPlayerById(int playerId) {
		return jdbcTemplate.queryForObject(SQL_SELECT_PLAYER_BY_ID,
				new PlayerMapper(), playerId);
	}

	public void addPlayer(Player player) {
		jdbcTemplate.update(SQL_INSERT_PLAYER, player);
	}

	public void deletePlayer(int playerId) {
		jdbcTemplate.update(SQL_DELETE_PLAYER, playerId);
	}
}