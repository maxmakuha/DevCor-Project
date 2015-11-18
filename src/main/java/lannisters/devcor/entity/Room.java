package lannisters.devcor.entity;

public class Room {

	private int roomId;
	private String roomNumber;
	private Player player;

	public Room() {
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

	public Player getPlayerObj() {
		return this.player;
	}

	public int getPlayerId() {
		if (this.player == null)
			this.player = new Player();
		return player.getPlayerId();
	}

	public void setPlayerId(int playerId) {
		if (this.player == null)
			this.player = new Player();
		this.player.setPlayerId(playerId);
	}

	public String getPlayerName() {
		return player.getFirstName();
	}

	public void setPlayerName(String playerName) {
		if (this.player == null)
			this.player = new Player();
		this.player.setFirstName(playerName);
	}

	public String getPlayerSurname() {
		return player.getLastName();
	}

	public void setPlayerSurname(String playerSurname) {
		if (this.player == null)
			this.player = new Player();
		this.player.setLastName(playerSurname);
	}

	@Override
	public String toString() {
		return "Room [roomId=" + roomId + ", roomNumber=" + roomNumber + ", player=" + player + "]";
	}
}