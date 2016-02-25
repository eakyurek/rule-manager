package core.validator;

import java.util.Set;

import core.Rule;
import core.RuleGraph;
import core.output.GraphOutput;

/**
 * This interface is used for validating {@link RuleGraph}.<br>
 */
public interface GraphValidator {

	/**
	 * Validates the graph.<br>
	 * @param ruleSet the nodes in the graph containing relations
	 * @return A {@link GraphOutput} object
	 */
	public GraphOutput validateGraph(Set<Rule> ruleSet);

}
