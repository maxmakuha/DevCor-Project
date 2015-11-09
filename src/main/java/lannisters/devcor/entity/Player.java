package lannisters.devcor.entity;

/**
 * 
 * @author Maxim
 * @version 1.0
 *
 */

public class Player {

	private int playerId;
	private String playerEmail;
	private String firstName;
	private String lastName;
	private String password;
	private String phoneNumber;
	private Role role;

	public Player() {
		role = new Role();
	}

	public Player(int playerId, String playerEmail, String firstName,
			String lastName, String password, String phoneNumber, Role role) {
		this.playerId = playerId;
		this.playerEmail = playerEmail;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.role = role;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getRoleId() {
		return role.getRoleId();
	}

	public void setRoleId(int roleId) {
		this.role.setRoleId(roleId);
	}

	public String getRole() {
		return role.getRole();
	}

	public void setRole(String role) {
		this.role.setRole(role);
	}

	@Override
	public String toString() {
		return "Player [playerId=" + playerId + ", playerEmail=" + playerEmail
				+ ", firstName=" + firstName + ", lastName=" + lastName
				+ ", password=" + password + ", phoneNumber=" + phoneNumber
				+ ", role=" + role + "]";
	}
}