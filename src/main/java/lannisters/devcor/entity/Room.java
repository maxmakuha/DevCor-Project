package lannisters.devcor.entity;

/**
 * 
 * @author Maxim
 * @version 1.0
 *
 */

public class Room {

	private int roomId;
	private String roomNumber;
	private Player player;

	public Room() {
		player = new Player();
	}

	public Room(int roomId, String roomNumber, Player player) {
		this.roomId = roomId;
		this.roomNumber = roomNumber;
		this.player = player;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public int getPlayerId() {
		return player.getPlayerId();
	}

	public void setPlayerId(int playerId) {
		this.player.setPlayerId(playerId);
	}

	public String getPlayerName() {
		return player.getFirstName();
	}

	public void setPlayerName(String playerName) {
		this.player.setFirstName(playerName);
	}

	public String getPlayerSurname() {
		return player.getLastName();
	}

	public void setPlayerSurname(String playerSurname) {
		this.player.setLastName(playerSurname);
	}

	@Override
	public String toString() {
		return "Room [roomId=" + roomId + ", roomNumber=" + roomNumber
				+ ", player=" + player + "]";
	}
}