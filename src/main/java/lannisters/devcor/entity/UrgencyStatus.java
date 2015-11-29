package lannisters.devcor.entity;

public class UrgencyStatus {
	private int urgencyStatusId;
	private String urgencyStatus;
	private int days;

	public UrgencyStatus() {
		
	}
	
	
	public UrgencyStatus(int urgencyStatusId, String urgencyStatus,int days) {
		this.urgencyStatusId = urgencyStatusId;
		this.urgencyStatus = urgencyStatus;
		this.days =days;
	}
	
	
	public int getUrgencyStatusId() {
		return urgencyStatusId;
	}

	public void setUrgencyStatusId(int urgencyStatusId) {
		this.urgencyStatusId = urgencyStatusId;
	}

	public String getUrgencyStatus() {
		return urgencyStatus;
	}

	public void setUrgencyStatus(String urgencyStatus) {
		this.urgencyStatus = urgencyStatus;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	@Override
	public String toString() {
		return "UrgencyStatus [urgencyStatusId=" + urgencyStatusId + ", urgencyStatus=" + urgencyStatus + "]";
	}
}