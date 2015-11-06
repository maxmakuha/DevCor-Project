package ua.lannisters.devcor.controller.player.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import ua.lannisters.devcor.controller.player.Player;
import ua.lannisters.devcor.controller.player.PlayerRowMapper;

@Repository("playerDao")
public class PlayerDaoImpl implements PlayerDao {

	private static final String CREATE_SQL = "INSERT INTO PLAYER ( PLAYER_ID, PLAYER_EMAIL, FIRST_NAME, LAST_NAME, PASSWORD, PHONE_NUMBER, ROLE_ID) "
			+ "VALUES (PLAYERS_SEQ.NEXTVAL, :playerId, :playerEmail, :firstName, "
			+ ":lastName, :password, :phoneNumber, :roleId)";

	private static final String GET_ALL_SQL = "SELECT PLAYER_ID, PLAYER_EMAIL, FIRST_NAME, LAST_NAME, PASSWORD, PHONE_NUMBER, ROLE_ID "
			+ "FROM PLAYERS";

	private static final String GET_SQL = "SELECT PLAYER_ID, PLAYER_EMAIL, FIRST_NAME, LAST_NAME, PASSWORD, PHONE_NUMBER, ROLE_ID "
			+ "FROM PLAYERS WHERE PLAYER_ID = :playerId";

	private static final String DELETE_SQL = "DELETE PLAYERS WHERE PLAYER_ID = :locationId";

	private static final String UPDATE_SQL = "UPDATE PLAYERS SET PlAYER_ID = :playerId, PLAYER_EMAIL= :playerEmail, FIRST_NAME= :firsName, LAST_NAME= :lastName, PASSWORD= :password, PHONE_NUMBER= :phoneNumber, ROLE_ID = :roleId"
			+ "WHERE PLAYER_ID = :playerId";
	
	private static final String GET_SQL_BY_ID = "SELECT PLAYER_ID, PLAYER_EMAIL, FIRST_NAME, LAST_NAME, PASSWORD, PHONE_NUMBER, ROLE_ID "
			+ "FROM PLAYERS WHERE ROLE_ID = :roleId";
	
	private NamedParameterJdbcOperations jdbcTemplate;
	private PlayerRowMapper playerRowMapper;

	public void createPlayer(Player player) {
		// TODO Auto-generated method stub
		SqlParameterSource params = new MapSqlParameterSource().addValue("playerEmail", player.getPlayerEmail())
				.addValue("firstName", player.getFirstName()).addValue("lastName", player.getLastName())
				.addValue("password", player.getPassword())
		.addValue("phoneNumber", player.getPhoneNumber())
		.addValue("roleId", player.getRoleId())
		;
		jdbcTemplate.update(CREATE_SQL, params);
	}

	public List<Player> getPlayers() {
		// TODO Auto-generated method stub
		return jdbcTemplate.query(GET_ALL_SQL, new HashMap<String, Object>(), playerRowMapper);
	}

	public Player getPlayer(Integer playerId) {
		// TODO Auto-generated method stub
		SqlParameterSource params = new MapSqlParameterSource().addValue("playerId", playerId);
		List<Player> players = jdbcTemplate.query(GET_SQL, params, playerRowMapper);
		return players.isEmpty() ? null : players.get(0);
	}

	public void updatePlayer(Player player) {
		// TODO Auto-generated method stub
		SqlParameterSource params = new MapSqlParameterSource().addValue("playerId", player.getPlayerId())
				.addValue("playerEmail", player.getPlayerEmail()).addValue("firstName", player.getFirstName())
				.addValue("lastName", player.getLastName()).addValue("password", player.getPassword())
		 .addValue("phoneNumber", player.getPhoneNumber())
		 .addValue("roleId", player.getRoleId())
		;
		jdbcTemplate.update(UPDATE_SQL, params);
	}

	public void deletePlayer(Integer playerId) {
		// TODO Auto-generated method stub
		jdbcTemplate.update(DELETE_SQL, new MapSqlParameterSource("playerId", playerId));
	}

	public Player getPlayerByRole(Integer roleId) {
		// TODO Auto-generated method stub
		SqlParameterSource params = new MapSqlParameterSource().addValue("roleId", roleId);
		List<Player> players = jdbcTemplate.query(GET_SQL_BY_ID, params, playerRowMapper);
		return players.isEmpty() ? null : players.get(0);
	}

}