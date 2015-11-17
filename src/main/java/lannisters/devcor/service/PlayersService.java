package lannisters.devcor.service;

import java.sql.SQLException;
import java.util.List;

import lannisters.devcor.entity.Player;

public interface PlayersService {

	public List<Player> getAllPlayers();

	public Player getPlayerById(int playerId);

	public void addPlayer(Player player);

	public void updatePlayer(Player player);

	public void deletePlayer(int playerId);

	public Player getPlayerByEmail(String email);

	public int getPlayerIdByEmail(String email) throws SQLException;

	public List<Player> getAllUsers();

	public List<Player> getAllTechnicians();
}