package ua.lannisters.devcor.controller.player.dao;

import java.util.List;

import ua.lannisters.devcor.controller.player.Player;

public interface PlayerDao {

	public void createPlayer(Player player);
    public List<Player> getPlayers();
    public Player getPlayer(Integer playerId);
    public void updatePlayer(Player player);
    public void deletePlayer(Integer playerId);
    public Player getPlayerByRole(Integer roleId);
}