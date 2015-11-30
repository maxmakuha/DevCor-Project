package lannisters.devcor.service;

import java.sql.SQLException;
import java.util.List;

import lannisters.devcor.dao.PlayersDAO;
import lannisters.devcor.entity.Player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PlayersServiceImpl implements PlayersService {

	@Autowired
	private PlayersDAO playersDao;

	@Override
	public List<Player> getAllPlayers() {
		return playersDao.getAllPlayers();
	}

	@Override
	public Player getPlayerById(int playerId) {
		return playersDao.getPlayerById(playerId);
	}

	@Override
	public void addPlayer(Player player) throws SQLException {
		playersDao.addPlayer(player);
	}

	@Override
	public void updatePlayer(Player player) throws SQLException {
		playersDao.updatePlayer(player);
	}

	@Override
	public void deletePlayer(int playerId) throws SQLException {
		playersDao.deletePlayer(playerId);
	}

	@Override
	public Player getPlayerByEmail(String email) {
		return playersDao.getPlayerByEmail(email);
	}

	@Override
	public int getPlayerIdByEmail(String email) {
		return playersDao.getPlayerIdByEmail(email);
	}

	@Override
	public List<Player> getAllUsers() {
		return playersDao.getAllUsers();
	}

	@Override
	public List<Player> getAllTechnicians() {
		return playersDao.getAllTechnicians();
	}

	@Override
	public boolean checkEmailExistence(Player player) {
		boolean existence;
		try {
			playersDao.getPlayerByEmail(player.getPlayerEmail());
			existence = true;
		} catch (EmptyResultDataAccessException e) {
			existence = false;
		}
		return existence;
	}

	@Override
	public String encodePassword(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		return hashedPassword;
	}
}