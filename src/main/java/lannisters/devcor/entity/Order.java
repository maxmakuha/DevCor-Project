package lannisters.devcor.entity;

import java.util.Date;

public class Order {

	private int orderId;
	private ProblemType problemType;
	private String description;
	private Room room;
	private Device device;
	private ExecutionStatus executionStatus;
	private UrgencyStatus urgencyStatus;
	private Date creationDate;
	private Date dueDate;
	private Player author;
	private Player technician;
	private String overdue;

	public Order() {
	}

	public Order(int orderId, ProblemType problemType, String description,
			Room room, Device device, ExecutionStatus executionStatus,
			UrgencyStatus urgencyStatus, Date creationDate, Date dueDate,
			Player author, Player technician, String overdue) {
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

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public int getAuthorId() {
		return author.getPlayerId();
	}

	public void setAuthorId(int authorId) {
		if (this.author == null)
			this.author = new Player();
		this.author.setPlayerId(authorId);
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

	public int getTechnicianId() {
		return technician.getPlayerId();
	}

	public void setTechnicianId(int technicianId) {
		if (this.technician == null)
			this.technician = new Player();
		this.technician.setPlayerId(technicianId);
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

	public String getOverdue() {
		return overdue;
	}

	public void setOverdue(String overdue) {
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