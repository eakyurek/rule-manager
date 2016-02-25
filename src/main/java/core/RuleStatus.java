package core;

/**
 * Status of the rules.
 */
public enum RuleStatus {
	UNVISITED(1), VALID(2), INVALID(3), SKIPPED(4);

	int status;

	private RuleStatus(int status) {
		this.status = status;
	}

	public int getStatus() {
		return status;
	}
}
