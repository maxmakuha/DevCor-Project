package ua.lannisters.devcor.db.model;

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
	private boolean overdue;
	
	public int getOrderId() {
		return orderId;
	}
	
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	public ProblemType getProblemType() {
		return problemType;
	}

	public void setProblemType(ProblemType problemType) {
		this.problemType = problemType;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Room getRoom() {
		return room;
	}
	
	public void setRoom(Room room) {
		this.room = room;
	}
	
	public Device getDevice() {
		return device;
	}
	
	public void setDevice(Device device) {
		this.device = device;
	}
	
	public ExecutionStatus getExecutionStatus() {
		return executionStatus;
	}
	
	public void setExecutionStatus(ExecutionStatus executionStatus) {
		this.executionStatus = executionStatus;
	}
	
	public UrgencyStatus getUrgencyStatus() {
		return urgencyStatus;
	}
	
	public void setUrgencyStatus(UrgencyStatus urgencyStatus) {
		this.urgencyStatus = urgencyStatus;
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
	
	public Player getAuthor() {
		return author;
	}
	
	public void setAuthor(Player author) {
		this.author = author;
	}
	
	public Player getTechnician() {
		return technician;
	}
	
	public void setTechnician(Player technician) {
		this.technician = technician;
	}
	
	public boolean isOverdue() {
		return overdue;
	}
	
	public void setOverdue(boolean overdue) {
		this.overdue = overdue;
	}
	
	@Override
    public int hashCode() {
		return new Integer(orderId).hashCode();
	}
	
	@Override
	public String toString() {
		return "Order {orderId=" + orderId + ", problemType=" + problemType + ", description=" + description + ", room="
				+ room + ", device=" + device + ", executionStatus=" + executionStatus + ", urgencyStatus="
				+ urgencyStatus + ", creationDate=" + creationDate + ", dueDate=" + dueDate + ", author=" + author
				+ ", technician=" + technician + ", overdue=" + overdue + "}";
	}
}
