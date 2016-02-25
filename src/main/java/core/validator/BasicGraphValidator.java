package core.validator;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Set;

import core.Rule;
import core.RuleStatus;
import core.output.GraphOutput;
import core.output.RuleOutput;

/**
 * Traverses the graph from top down.<br>
 * If a node has one or more parents, postpones its validation until all parent
 * validates and only validates if all of them are valid.<br>
 * <br>
 * Returns all {@link RuleOutput} objects that fails.
 */
public class BasicGraphValidator implements GraphValidator {

	public GraphOutput validateGraph(Set<Rule> ruleSet) {
		if (ruleSet == null || ruleSet.isEmpty()) {
			throw new IllegalArgumentException("Graph should  not be empty or null");
		}

		GraphOutput result = new GraphOutput();
		Deque<Rule> queue = new ArrayDeque<Rule>();

		// Add all rules that have no parent
		for (Rule rule : ruleSet) {
			if (rule.getParentList().isEmpty()) {
				queue.addLast(rule);
			}
		}

		// Traverse the graph
		while (!queue.isEmpty()) {
			Rule currentRule = queue.pollFirst();
			boolean allParentsValid = true;

			for (Rule parent : currentRule.getParentList()) {
				if (parent.getStatus() == RuleStatus.VALID) {
					continue;
				} else {
					if (parent.getStatus() == RuleStatus.UNVISITED) {
						queue.addLast(currentRule);
					}
					currentRule.setStatus(RuleStatus.SKIPPED);
					allParentsValid = false;
					break;
				}
			}

			if (allParentsValid) {
				RuleOutput currentResult = currentRule.validate();
				if (currentResult.isValid()) {
					for (Rule child : currentRule.getChildList()) {
						if (child.getStatus() == RuleStatus.UNVISITED && !queue.contains(child)) {
							queue.addLast(child);
						}
					}
				} else {
					result.addRuleOutput(currentResult);
					for (Rule child : currentRule.getChildList()) {
						queue.remove(child);
						child.setStatus(RuleStatus.SKIPPED);
					}
				}
			}
		}

		return result;
	}

}
