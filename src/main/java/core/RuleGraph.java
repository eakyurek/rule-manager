package core;

import java.util.HashSet;
import java.util.Set;

import core.output.GraphOutput;
import core.validator.BasicGraphValidator;
import core.validator.GraphValidator;

/**
 * This is the Graph that contains all the rules and manages their relations.
 * <br>
 * The graph can be validated using a GraphValidator given in creation.
 */
public class RuleGraph {

	private GraphValidator validator;
	private Set<Rule> ruleSet;

	/**
	 * Sets the GraphValidator as {@link BasicGraphValidator} and initializes
	 * variables.
	 */
	public RuleGraph() {
		this(new BasicGraphValidator());
	}

	/**
	 * Sets the GraphValidator with the given validator and initializes
	 * variables.
	 * 
	 * @param validator
	 */
	public RuleGraph(GraphValidator validator) {
		this.validator = validator;
		ruleSet = new HashSet<Rule>();
	}

	/**
	 * Adds rule as a node to the graph.<br>
	 * Should not add the same object twice.
	 * 
	 * @param rule
	 * @return
	 */
	public boolean addRule(Rule rule) {
		if(ruleSet.contains(rule)) {
			throw new IllegalArgumentException("A rule object can not be added more than once");
		}
		return ruleSet.add(rule);
	}

	/**
	 * Adds relation between two given rules and prevents cycles in the graph.
	 * <br>
	 * The rules should exist in the graph beforehand.<br>
	 * Parent and child should not be the same object.
	 * 
	 * @param parent
	 * @param child
	 */
	public void addRelation(Rule parent, Rule child) {
		if (!ruleSet.contains(parent) || !ruleSet.contains(child)) {
			throw new IllegalArgumentException("Parent and child rules should be added in the graph beforehand");
		} else if(parent == child) {
			throw new IllegalArgumentException("Parent and child rules can not be the same object");
		}
		// TODO check cycle
		child.addParent(parent);
		parent.addChild(child);
	}

	/**
	 * Validates the graph with the {@link GraphValidator} set in creation.<br>
	 * Multiple calls does not effect each other.
	 * 
	 * @return A {@link GraphOutput} object
	 */
	public GraphOutput validateGraph() {
		for (Rule rule : ruleSet) {
			rule.setStatus(RuleStatus.UNVISITED);
		}
		return validator.validateGraph(ruleSet);
	}

}
