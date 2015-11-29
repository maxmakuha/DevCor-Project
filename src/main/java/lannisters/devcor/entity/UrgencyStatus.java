package lannisters.devcor.entity;

public class UrgencyStatus {
	private int urgencyStatusId;
	private String urgencyStatus;
	private int minutes;

	public UrgencyStatus() {
		
	}
	
	public UrgencyStatus(int urgencyStatusId, String urgencyStatus,int minutes) {
		this.urgencyStatusId = urgencyStatusId;
		this.urgencyStatus = urgencyStatus;
		this.minutes = minutes;
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

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	@Override
	public String toString() {
		return "UrgencyStatus [urgencyStatusId=" + urgencyStatusId + ", urgencyStatus=" + urgencyStatus +  ", minutes=" + minutes +"]";
	}
}