package lannisters.devcor.entity;

import java.sql.Timestamp;

public class Order {

	private int orderId;
	private ProblemType problemType;
	private String description;
	private Room room;
	private Device device;
	private ExecutionStatus executionStatus;
	private UrgencyStatus urgencyStatus;
	private Timestamp creationDate;
	private Timestamp dueDate;
	private Player author;
	private Player technician;
	private boolean overdue;

	public Order() {
		problemType = new ProblemType();
		room = new Room();
		device = new Device();
		urgencyStatus = new UrgencyStatus();
		author = new Player();
		technician = new Player();
	}

	public Order(int orderId, ProblemType problemType, String description,
			Room room, Device device, ExecutionStatus executionStatus,
			UrgencyStatus urgencyStatus, Timestamp creationDate, Timestamp dueDate,
			Player author, Player technician, boolean overdue) {
		this.orderId = orderId;
		this.problemType = problemType;
		this.description = description;
		this.room = room;
		this.device = device;
		this.executionStatus = executionStatus;
		this.urgencyStatus = urgencyStatus;
		this.creationDate = creationDate;
		this.dueDate = dueDate;
		this.author = author;
		this.technician = technician;
		this.overdue = overdue;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public ProblemType getProblemTypeObj() {
		return this.problemType;
	}
	
	public int getProblemTypeId() {
		return problemType.getProblemTypeId();
	}

	public void setProblemTypeId(int problemTypeId) {
		if (this.problemType == null)
			this.problemType = new ProblemType();
		this.problemType.setProblemTypeId(problemTypeId);
	}

	public String getProblemType() {
		return problemType.getProblemType();
	}

	public void setProblemType(String type) {
		if (this.problemType == null)
			this.problemType = new ProblemType();
		this.problemType.setProblemType(type);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Room getRoomObj() {
		return this.room;
	}
	
	public int getRoomId() {
		return room.getRoomId();
	}

	public void setRoomId(int roomId) {
		if (this.room == null)
			this.room = new Room();
		this.room.setRoomId(roomId);
	}

	public String getRoomNumber() {
		return room.getRoomNumber();
	}

	public void setRoomNumber(String roomNumber) {
		if (this.room == null)
			this.room = new Room();
		this.room.setRoomNumber(roomNumber);
	}

	public Device getDeviceObj() {
		return this.device;
	}
	
	public int getDeviceId() {
		return device.getDeviceId();
	}

	public void setDeviceId(int deviceId) {
		if (this.device == null)
			this.device = new Device();
		this.device.setDeviceId(deviceId);
	}

	public String getDeviceSerialId() {
		return device.getDeviceSerialId();
	}

	public void setDeviceSerialId(String deviceSerialId) {
		if (this.device == null)
			this.device = new Device();
		this.device.setDeviceSerialId(deviceSerialId);
	}

	public ExecutionStatus getExecutionStatusObj() {
		return this.executionStatus;
	}
	
	public void removeDevice(){
		this.device = null;
	}

	public int getExecutionStatusId() {
		return executionStatus.getExecutionStatusId();
	}

	public void setExecutionStatusId(int executionStatusId) {
		if (this.executionStatus == null)
			this.executionStatus = new ExecutionStatus();
		this.executionStatus.setExecutionStatusId(executionStatusId);
	}

	public String getExecutionStatus() {
		return executionStatus.getExecutionStatus();
	}

	public void setExecutionStatus(String executionStatus) {
		if (this.executionStatus == null)
			this.executionStatus = new ExecutionStatus();
		this.executionStatus.setExecutionStatus(executionStatus);
	}

	public UrgencyStatus getUrgencyStatusObj() {
		return this.urgencyStatus;
	}
	
	public int getUrgencyStatusId() {
		return urgencyStatus.getUrgencyStatusId();
	}

	public void setUrgencyStatusId(int urgencyStatusId) {
		if (this.urgencyStatus == null)
			this.urgencyStatus = new UrgencyStatus();
		this.urgencyStatus.setUrgencyStatusId(urgencyStatusId);
	}

	public String getUrgencyStatus() {
		return urgencyStatus.getUrgencyStatus();
	}

	public void setUrgencyStatus(String urgencyStatus) {
		if (this.urgencyStatus == null)
			this.urgencyStatus = new UrgencyStatus();
		this.urgencyStatus.setUrgencyStatus(urgencyStatus);
	}

	public Timestamp getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	public Timestamp getDueDate() {
		return dueDate;
	}

	public void setDueDate(Timestamp dueDate) {
		this.dueDate = dueDate;
	}

	public Player getAuthorObj() {
		return this.author;
	}
	
	public int getAuthorId() {
		return author.getPlayerId();
	}

	public void setAuthorId(int authorId) {
		if (this.author == null)
			this.author = new Player();
		this.author.setPlayerId(authorId);
	}
	
	public String getAuthorEmail() {
		return this.author.getPlayerEmail();
	}

	public void setAuthorEmail(String email) {
		if (this.author == null)
			this.author = new Player();
		this.author.setPlayerEmail(email);
	}
	
	public String getAuthorName() {
		return author.getFirstName();
	}

	public void setAuthorName(String authorName) {
		if (this.author == null)
			this.author = new Player();
		this.author.setFirstName(authorName);
	}

	public String getAuthorSurname() {
		return author.getLastName();
	}

	public void setAuthorSurname(String authorSurname) {
		if (this.author == null)
			this.author = new Player();
		this.author.setLastName(authorSurname);
	}

	public Player getTechnicianObj() {
		return this.technician;
	}
	
	public int getTechnicianId() {
		return technician.getPlayerId();
	}

	public void setTechnicianId(int technicianId) {
		if (this.technician == null)
			this.technician = new Player();
		this.technician.setPlayerId(technicianId);
	}
	
	public String getTechnicianEmail() {
		if (this.technician == null)
			this.technician = new Player();
		return this.technician.getPlayerEmail();
	}
	
	public void setTechnicianEmail(String email) {
		this.technician.setPlayerEmail(email);
	}

	public String getTechnicianName() {
		return technician.getFirstName();
	}

	public void setTechnicianName(String technicianName) {
		if (this.technician == null)
			this.technician = new Player();
		this.technician.setFirstName(technicianName);
	}

	public String getTechnicianSurname() {
		return technician.getLastName();
	}

	public void setTechnicianSurname(String technicianSurname) {
		if (this.technician == null)
			this.technician = new Player();
		this.technician.setLastName(technicianSurname);
	}

	public boolean getOverdue() {
		return overdue;
	}

	public void setOverdue(boolean overdue) {
		this.overdue = overdue;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", problemType=" + problemType
				+ ", description=" + description + ", room=" + room
				+ ", device=" + device + ", executionStatus=" + executionStatus
				+ ", urgencyStatus=" + urgencyStatus + ", creationDate="
				+ creationDate + ", dueDate=" + dueDate + ", author=" + author
				+ ", technician=" + technician + ", overdue=" + overdue + "]";
	}
}