package lannisters.devcor.dao;

import java.util.List;
import lannisters.devcor.entity.Player;

/**
 * 
 * @author Maxim
 * @version 1.0
 *
 */

public interface PlayersDAO {

	public List<Player> getAllPlayers();

	public Player getPlayerById(int playerId);

	public void addPlayer(Player player);

	public void deletePlayer(int playerId);
}