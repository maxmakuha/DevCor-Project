package lannisters.devcor.service;

import java.sql.SQLException;
import java.util.List;

import lannisters.devcor.dao.PlayersDAO;
import lannisters.devcor.entity.Player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayersServiceImpl implements PlayersService {

	@Autowired
	private PlayersDAO playersDao;

	public List<Player> getAllPlayers() {
		return playersDao.getAllPlayers();
	}

	public Player getPlayerById(int playerId) {
		return playersDao.getPlayerById(playerId);
	}

	public void addPlayer(Player player) throws SQLException {
		playersDao.addPlayer(player);
	}

	public void updatePlayer(Player player) throws SQLException {
		playersDao.updatePlayer(player);
	}

	public void deletePlayer(int playerId) throws SQLException {
		playersDao.deletePlayer(playerId);
	}
}