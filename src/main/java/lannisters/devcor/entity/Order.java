package lannisters.devcor.entity;

/**
 * 
 * @author Maxim
 * @version 1.0
 *
 */

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
		problemType = new ProblemType();
		room = new Room();
		device = new Device();
		executionStatus = new ExecutionStatus();
		urgencyStatus = new UrgencyStatus();
		author = new Player();
		technician = new Player();
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
		this.problemType.setProblemTypeId(problemTypeId);
	}

	public String getProblemType() {
		return problemType.getProblemType();
	}

	public void setProblemType(String type) {
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
		this.room.setRoomId(roomId);
	}

	public String getRoomNumber() {
		return room.getRoomNumber();
	}

	public void setRoomNumber(String roomNumber) {
		this.room.setRoomNumber(roomNumber);
	}

	public int getDeviceId() {
		return device.getDeviceId();
	}

	public void setDeviceId(int deviceId) {
		this.device.setDeviceId(deviceId);
	}
	
	public String getDeviceSerialId() {
		return device.getDeviceSerialId();
	}

	public void setDeviceSerialId(String deviceSerialId) {
		this.device.setDeviceSerialId(deviceSerialId);
	}

	public int getExecutionStatusId() {
		return executionStatus.getExecutionStatusId();
	}

	public void setExecutionStatusId(int executionStatusId) {
		this.executionStatus.setExecutionStatusId(executionStatusId);
	}
	
	public String getExecutionStatus() {
		return executionStatus.getExecutionStatus();
	}

	public void setExecutionStatus(String executionStatus) {
		this.executionStatus.setExecutionStatus(executionStatus);
	}

	public int getUrgencyStatusId() {
		return urgencyStatus.getUrgencyStatusId();
	}

	public void setUrgencyStatusId(int urgencyStatusId) {
		this.urgencyStatus.setUrgencyStatusId(urgencyStatusId);
	}
	
	public String getUrgencyStatus() {
		return urgencyStatus.getUrgencyStatus();
	}

	public void setUrgencyStatus(String urgencyStatus) {
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
		this.author.setPlayerId(authorId);
	}
	
	public String getAuthorName() {
		return author.getFirstName();
	}

	public void setAuthorName(String authorName) {
		this.author.setFirstName(authorName);
	}
	
	public String getAuthorSurname() {
		return author.getLastName();
	}

	public void setAuthorSurname(String authorSurname) {
		this.author.setLastName(authorSurname);
	}

	public int getTechnicianId() {
		return technician.getPlayerId();
	}

	public void setTechnicianId(int technicianId) {
		this.technician.setPlayerId(technicianId);
	}
	
	public String getTechnicianName() {
		return technician.getFirstName();
	}

	public void setTechnicianName(String technicianName) {
		this.technician.setFirstName(technicianName);
	}
	
	public String getTechnicianSurname() {
		return technician.getLastName();
	}

	public void setTechnicianSurname(String technicianSurname) {
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