package lannisters.devcor.dao;

import java.sql.SQLException;
import java.util.List;

import lannisters.devcor.entity.Player;

public interface PlayersDAO {

	public List<Player> getAllPlayers();

	public Player getPlayerById(int playerId);

	public void addPlayer(Player player) throws SQLException;

	public void updatePlayer(Player player) throws SQLException;

	public void deletePlayer(int playerId) throws SQLException;

	public Player getPlayerByEmail(String email);

	public int getPlayerIdByEmail(String email);

	public List<Player> getAllUsers();

	public List<Player> getAllTechnicians();

	List<Player> getAdmin();

}