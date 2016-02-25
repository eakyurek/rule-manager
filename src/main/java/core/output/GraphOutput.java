package core.output;

import java.util.ArrayList;
import java.util.List;

/**
 * This class contains the result of graph validation.
 */
public class GraphOutput {

	private boolean valid;
	private List<RuleOutput> ruleOutputList;

	/**
	 * Sets valid to true and initializes ruleOutputList.
	 */
	public GraphOutput() {
		valid = true;
		ruleOutputList = new ArrayList<RuleOutput>();
	}

	/**
	 * Adds a RuleOutput object to the ruleOutputList.<br>
	 * If it is not valid sets valid to false.
	 * @param ruleOutput
	 */
	public void addRuleOutput(RuleOutput ruleOutput) {
		valid = valid & ruleOutput.isValid();
		ruleOutputList.add(ruleOutput);
	}

	/**
	 * Gets the messages in rule outputs as list.
	 * @return A list of messages
	 */
	public List<String> getMessages() {
		List<String> messages = new ArrayList<String>();
		for (RuleOutput ruleOutput : ruleOutputList) {
			messages.add(ruleOutput.getMessage());
		}
		return messages;
	}

	/**
	 * Is the graph valid
	 * @return valid
	 */
	public boolean isValid() {
		return valid;
	}

	/**
	 * Sets valid
	 * @param valid
	 */
	public void setValid(boolean valid) {
		this.valid = valid;
	}

	/**
	 * Gets the list of rule outputs.<br>
	 * This is the actual list not a copy of it.
	 * @return The list of rule outputs
	 */
	public List<RuleOutput> getRuleOutputList() {
		return ruleOutputList;
	}

	@Override
	public String toString() {
		return "GraphOutput [valid=" + valid + ", ruleOutputList=" + ruleOutputList + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ruleOutputList == null) ? 0 : ruleOutputList.hashCode());
		result = prime * result + (valid ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GraphOutput other = (GraphOutput) obj;
		if (ruleOutputList == null) {
			if (other.ruleOutputList != null)
				return false;
		} else if (!ruleOutputList.equals(other.ruleOutputList))
			return false;
		if (valid != other.valid)
			return false;
		return true;
	}

}
