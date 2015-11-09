package lannisters.devcor.entity;

/**
 * 
 * @author Maxim
 * @version 1.0
 *
 */

public class ProblemType {

	private int problemTypeId;
	private String problemType;

	public ProblemType() {
	}

	public ProblemType(int problemTypeId, String problemType) {
		this.problemTypeId = problemTypeId;
		this.problemType = problemType;
	}

	public int getProblemTypeId() {
		return problemTypeId;
	}

	public void setProblemTypeId(int problemTypeId) {
		this.problemTypeId = problemTypeId;
	}

	public String getProblemType() {
		return problemType;
	}

	public void setProblemType(String problemType) {
		this.problemType = problemType;
	}

	@Override
	public String toString() {
		return "ProblemType [problemTypeId=" + problemTypeId + ", problemType="
				+ problemType + "]";
	}
}