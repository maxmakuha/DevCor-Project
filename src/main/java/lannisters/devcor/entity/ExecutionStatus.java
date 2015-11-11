package lannisters.devcor.entity;

public class ExecutionStatus {

	private int executionStatusId;
	private String executionStatus;

	public ExecutionStatus() {
	}

	public ExecutionStatus(int executionStatusId, String executionStatus) {
		this.executionStatusId = executionStatusId;
		this.executionStatus = executionStatus;
	}

	public int getExecutionStatusId() {
		return executionStatusId;
	}

	public void setExecutionStatusId(int executionStatusId) {
		this.executionStatusId = executionStatusId;
	}

	public String getExecutionStatus() {
		return executionStatus;
	}

	public void setExecutionStatus(String executionStatus) {
		this.executionStatus = executionStatus;
	}

	@Override
	public String toString() {
		return "ExecutionStatus [executionStatusId=" + executionStatusId
				+ ", executionStatus=" + executionStatus + "]";
	}
}