package lannisters.devcor.entity;

import java.util.Date;

public class Report {

	private int reportId;
	private Date creatingDate;
	private Date dueDate;
	private String problemType;
	private String problemDescription;
	private String roomNumber;
	private String serialNumber;
	private String executionStatus;
	private String urgencyStatus;
	private String author;
	private String overdue;
	private String technician;
	

	private String deviseType;
	private String orderQuantity;
	
	
	private String coutOfopenOrders;
	private String countOfinprogressOrders;
	private String countOfunsolvableOrders;
	private String countOfincorrectOrders;
	private String countOffinishedOrders;
	private String countOffinishedwithOverdueOrders;
	private String countOfnotfinishedwithOverdueOrders;
	private String totalOrders;
	
	public Report() { 
	}
	
	
	public void setCoutOfopenOrders(String coutOfopenOrders) {
		this.coutOfopenOrders = coutOfopenOrders;
	}
	public String getCoutOfopenOrders() {
		return coutOfopenOrders;
	}
	
	public void setCountOfinprogressOrders(String countOfinprogressOrders) {
		this.countOfinprogressOrders = countOfinprogressOrders;
	}
	public String getCountOfinprogressOrders() {
		return countOfinprogressOrders;
	}
	
	public void setCountOfunsolvableOrders(String countOfunsolvableOrders) {
		this.countOfunsolvableOrders = countOfunsolvableOrders;
	}
	public String getCountOfunsolvableOrders() {
		return countOfunsolvableOrders;
	}
	
	public void setCountOfincorrectOrders(String countOfincorrectOrders) {
		this.countOfincorrectOrders = countOfincorrectOrders;
	}
	public String getCountOfincorrectOrders() {
		return countOfincorrectOrders;
	}
	
	public void setCountOffinishedOrders(String countOffinishedOrders) {
		this.countOffinishedOrders = countOffinishedOrders;
	}
	public String getCountOffinishedOrders() {
		return countOffinishedOrders;
	}
	
	public void setCountOffinishedwithOverdueOrders(String countOffinishedwithOverdueOrders) {
		this.countOffinishedwithOverdueOrders = countOffinishedwithOverdueOrders;
	}
	public String getCountOffinishedwithOverdueOrders() {
		return countOffinishedwithOverdueOrders;
	}
	
	public void setCountOfnotfinishedwithOverdueOrders(String countOfnotfinishedwithOverdueOrders) {
		this.countOfnotfinishedwithOverdueOrders = countOfnotfinishedwithOverdueOrders;
	}
	public String getCountOfnotfinishedwithOverdueOrders() {
		return countOfnotfinishedwithOverdueOrders;
	}
	
	public void setTotalOrders(String totalOrders) {
		this.totalOrders = totalOrders;
	}
	public String getTotalOrders() {
		return totalOrders;
	}
	
	
	

	public void setDeviseType(String deviseType) {
		this.deviseType =deviseType;
	}
	public String getDeviseType() {
		return deviseType;
	}
	
	public void setOrderQuantity(String orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
	public String getOrderQuantity() {
		return orderQuantity;
	}
	
	public void setReportId(int orderId) {
		this.reportId = orderId;
	}
	public int getReportId() {
		return reportId;
	}
	
	public void setCreatingDate(Date creatingDate) {
		this.creatingDate = creatingDate;
	}
	public Date getCreatingDate() {
		return creatingDate;
	}
	
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public Date getDueDate() {
		return dueDate;
	}

	public void setProblemType(String problemType) {
		this.problemType = problemType;
	}
	public String getProblemType() {
		return problemType;
	}

	public void setProblemDescription(String problemDescription) {
		this.problemDescription = problemDescription;
	}
	public String getProblemDescription() {
		return problemDescription;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}
	public String getRoomNumber() {
		return roomNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getSerialNumber() {
		return serialNumber;
	}

	public void setExecutionStatus(String executionStatus) {
		this.executionStatus = executionStatus;
	}
	public String getExecutionStatus() {
		return executionStatus;
	}

	public void setUrgencyStatus(String urgencyStatus) {
		this.urgencyStatus = urgencyStatus;
	}
	public String getUrgencyStatus() {
		return urgencyStatus;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	public String getAuthor() {
		return author;
	}

	public void setOverdue(String overdue) {
		this.overdue = overdue;
	}
	public String getOverdue() {
		return overdue;
	}

	public void setTechnician(String technician) {
		this.technician = technician;
	}
	public String getTechnician() {
		return technician;
	}
}