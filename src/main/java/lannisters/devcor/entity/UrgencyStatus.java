package lannisters.devcor.entity;

public class UrgencyStatus {

	private int urgencyStatusId;
	private String urgencyStatus;
	private String days = "7";

	public UrgencyStatus() {
	}

	public UrgencyStatus(int urgencyStatusId, String urgencyStatus) {
		this.urgencyStatusId = urgencyStatusId;
		this.urgencyStatus = urgencyStatus;
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

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	@Override
	public String toString() {
		return "UrgencyStatus [urgencyStatusId=" + urgencyStatusId + ", urgencyStatus=" + urgencyStatus + "]";
	}
}