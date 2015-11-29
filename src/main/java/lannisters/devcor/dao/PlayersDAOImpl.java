package lannisters.devcor.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import lannisters.devcor.entity.Player;
import lannisters.devcor.orm.PlayerMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PlayersDAOImpl implements PlayersDAO {

	private static final String SQL_SELECT_ALL_PLAYERS = "SELECT player.player_id, player.player_email, player.first_name, player.last_name, player.password, player.phone_number, player.role_id, role.role FROM player INNER JOIN role ON player.role_id = role.role_id";
	private static final String SQL_SELECT_PLAYER_BY_ID = "SELECT player.player_id, player.player_email, player.first_name, player.last_name, player.password, player.phone_number, player.role_id, role.role FROM player INNER JOIN role ON player.role_id = role.role_id WHERE player.player_id=?";
	private static final String SQL_INSERT_PLAYER = "INSERT INTO player(player_email, first_name, last_name, password, phone_number, role_id) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String SQL_UPDATE_PLAYER = "UPDATE player SET player_email = ?, first_name = ?, last_name = ?, password = ?, phone_number = ?, role_id = ? WHERE player_id = ?";
	private static final String SQL_DELETE_PLAYER = "DELETE player WHERE player_id=?";
	private static final String SQL_GET_PLAYER_BY_EMAIL = "SELECT player.player_id, player.player_email, player.first_name, player.last_name, player.password, player.phone_number, player.role_id, role.role FROM player INNER JOIN role ON player.role_id = role.role_id WHERE player.player_email=?";
	private static final String SQL_GET_PLAYER_ID_BY_EMAIL = "SELECT player.player_id FROM player WHERE player.player_email = ?";
	private static final String SQL_SELECT_ALL_USERS = SQL_SELECT_ALL_PLAYERS + " WHERE player.role_id = 3";
	private static final String SQL_SELECT_ALL_TECHNICIANS = SQL_SELECT_ALL_PLAYERS + " WHERE player.role_id = 2";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Player> getAllPlayers() {
		return jdbcTemplate.query(SQL_SELECT_ALL_PLAYERS, new PlayerMapper());
	}

	@Override
	public Player getPlayerById(int playerId) {
		return jdbcTemplate.queryForObject(SQL_SELECT_PLAYER_BY_ID, new PlayerMapper(), playerId);
	}

	@Override
	public void addPlayer(Player player) throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = jdbcTemplate.getDataSource().getConnection().prepareStatement(SQL_INSERT_PLAYER);
			ps.setString(1, player.getPlayerEmail());
			ps.setString(2, player.getFirstName());
			ps.setString(3, player.getLastName());
			ps.setString(4, player.getPassword());
			ps.setString(5, player.getPhoneNumber());
			ps.setInt(6, player.getRoleId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (ps != null)
				ps.close();
		}
	}

	@Override
	public void updatePlayer(Player player) throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = jdbcTemplate.getDataSource().getConnection().prepareStatement(SQL_UPDATE_PLAYER);
			ps.setString(1, player.getPlayerEmail());
			ps.setString(2, player.getFirstName());
			ps.setString(3, player.getLastName());
			ps.setString(4, player.getPassword());
			ps.setString(5, player.getPhoneNumber());
			ps.setInt(6, player.getRoleId());
			ps.setInt(7, player.getPlayerId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (ps != null)
				ps.close();
		}
	}

	@Override
	public void deletePlayer(int playerId) throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = jdbcTemplate.getDataSource().getConnection().prepareStatement(SQL_DELETE_PLAYER);
			ps.setInt(1, playerId);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (ps != null)
				ps.close();
		}
	}

	@Override
	public Player getPlayerByEmail(String email) {
		return jdbcTemplate.queryForObject(SQL_GET_PLAYER_BY_EMAIL, new PlayerMapper(), email);
	}

	@Override
	public int getPlayerIdByEmail(String email) {
		return jdbcTemplate.queryForInt(SQL_GET_PLAYER_ID_BY_EMAIL, email);
	}

	@Override
	public List<Player> getAllUsers() {
		return jdbcTemplate.query(SQL_SELECT_ALL_USERS, new PlayerMapper());
	}

	@Override
	public List<Player> getAllTechnicians() {
		return jdbcTemplate.query(SQL_SELECT_ALL_TECHNICIANS, new PlayerMapper());
	}
}