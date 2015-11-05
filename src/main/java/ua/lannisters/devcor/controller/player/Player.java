
package ua.lannisters.devcor.controller.player;

public class Player {

	private Integer playerId;
	private String playerEmail;
	private String firstName;
	private String lastName;
	private String password;
	private Integer phoneNumber;
	private Integer roleId;

	public Player(Integer playerId, String playerEmail, String firstName, String lastName, String password,
			Integer phoneNumber, Integer roleId) {
		this.playerId = playerId;
		this.playerEmail = playerEmail;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.roleId = roleId;
	}

	public Integer getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Integer playerId) {
		this.playerId = playerId;
	}

	public String getPlayerEmail() {
		return playerEmail;
	}

	public void setPlayerEmail(String playerEmail) {
		this.playerEmail = playerEmail;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Integer phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	  public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	@Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;

	        Player location = (Player) o;

	        if (!playerId.equals(location.playerId)) return false;

	        return true;
	    }
	@Override
	public int hashCode() {
		return playerId.hashCode();
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("Player");
		sb.append("{playerId=").append(playerId);
		sb.append(", firstName='").append(firstName).append('\'');
		sb.append(", lastName='").append(lastName).append('\'');
		sb.append(", playerEmail='").append(playerEmail).append('\'');
		sb.append(", password='").append(password).append('\'');
		sb.append(", phoneNumber=").append(phoneNumber).append('\'');
		sb.append(", roleId=").append(roleId);
		sb.append('}');
		return sb.toString();
	}
}