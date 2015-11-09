package lannisters.devcor.entity;

/**
 * 
 * @author Maxim
 * @version 1.0
 *
 */

public class UrgencyStatus {

	private int urgencyStatusId;
	private String urgencyStatus;

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

	@Override
	public String toString() {
		return "UrgencyStatus [urgencyStatusId=" + urgencyStatusId
				+ ", urgencyStatus=" + urgencyStatus + "]";
	}
}