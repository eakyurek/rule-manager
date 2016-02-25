package core;

import java.util.ArrayList;
import java.util.List;

import core.output.RuleOutput;

/**
 * This is the abstract class for creating Rules.<br>
 * By extending this class you can add it to the {@link RuleGraph} and validate it.
 */
public abstract class Rule {

	private RuleStatus status;
	private List<Rule> parentList;
	private List<Rule> childList;

	/**
	 * Sets the status as RuleStatus.UNVISITED and initializes the variables.
	 */
	protected Rule() {
		status = RuleStatus.UNVISITED;
		parentList = new ArrayList<Rule>();
		childList = new ArrayList<Rule>();
	}

	/**
	 * The validation logic should be in the method.
	 * @return A RuleOutput object containing whether the rule passed or not
	 */
	protected abstract RuleOutput runner();

	/**
	 * Does validation of the rule.
	 * @return A RuleOutput object containing whether the rule passed or not
	 */
	public RuleOutput validate() {
		// TODO log runtime etc.
		RuleOutput result = runner();
		
		if (result.isValid()) {
			status = RuleStatus.VALID;
		} else {
			status = RuleStatus.INVALID;
		}

		return result;
	}

	/**
	 * Adds parent rule for this rule.<br>
	 * Child rules are generally not executed if the parent is invalid.
	 * @param parentRule
	 * @return 
	 */
	boolean addParent(Rule parentRule) {
		return parentList.add(parentRule);
	}

	/**
	 * Adds child rule for this rule.
	 * @param childRule
	 * @return
	 */
	boolean addChild(Rule childRule) {
		return childList.add(childRule);
	}

	/**
	 * Gets the status of the rule.
	 * @return A RuleStatus object
	 */
	public RuleStatus getStatus() {
		return status;
	}

	/**
	 * Sets the status of the rule.
	 * @param status
	 */
	public void setStatus(RuleStatus status) {
		this.status = status;
	}

	/**
	 * Gets the list of parent rules.<br>
	 * This is the actual list not a copy.
	 * @return
	 */
	public List<Rule> getParentList() {
		return parentList;
	}

	/**
	 * Gets the list of child rules.<br>
	 * This is the actual list not a copy.
	 * @return
	 */
	public List<Rule> getChildList() {
		return childList;
	}

}
