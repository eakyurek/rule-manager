package core;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import core.output.GraphOutput;

public class RuleGraphTest {
	RuleGraph graph;
	Rule validTestRule;
	Rule invalidTestRule;
	
	@Before
	public void setup() {
		graph = new RuleGraph();
		validTestRule = new DummyRule("validTestRule", true);
		invalidTestRule = new DummyRule("invalidTestRule", false);
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void addNonExistingChildRelation() {
		graph.addRule(validTestRule);
		graph.addRelation(validTestRule, invalidTestRule);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void addNonExistingParentRelation() {
		graph.addRule(invalidTestRule);
		graph.addRelation(validTestRule, invalidTestRule);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void addRelationToSelf() {
		graph.addRule(validTestRule);
		graph.addRelation(validTestRule, validTestRule);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void addRuleObjectTwice() {
		graph.addRule(invalidTestRule);
		graph.addRule(invalidTestRule);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void cycleWith2Nodes() {
		graph.addRule(validTestRule);
		graph.addRule(invalidTestRule);
		graph.addRelation(validTestRule, invalidTestRule);
		graph.addRelation(invalidTestRule, validTestRule);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void cycleWithMultiNodes() {
		Rule thirdRule = new DummyRule("thirdRule", true);
		
		graph.addRule(validTestRule);
		graph.addRule(invalidTestRule);
		graph.addRule(thirdRule);

		graph.addRelation(validTestRule, invalidTestRule);
		graph.addRelation(invalidTestRule, thirdRule);
		graph.addRelation(thirdRule, validTestRule);
	}
	
	@Test
	public void nonCycle() {
		Rule thirdRule = new DummyRule("thirdRule", true);
		Rule fourthRule = new DummyRule("fourthRule", true);
		
		graph.addRule(validTestRule);
		graph.addRule(invalidTestRule);
		graph.addRule(thirdRule);
		graph.addRule(fourthRule);

		graph.addRelation(validTestRule, invalidTestRule);
		graph.addRelation(invalidTestRule, fourthRule);
		graph.addRelation(thirdRule, fourthRule);
		graph.addRelation(thirdRule, validTestRule);
	}
	
	@Test
	public void runMoreThanOnce() {
		graph.addRule(validTestRule);
		graph.addRule(invalidTestRule);
		graph.addRelation(validTestRule, invalidTestRule);
		
		GraphOutput output1 = graph.validateGraph();
		Assert.assertEquals(output1.isValid(), false);
		
		((DummyRule)invalidTestRule).setValid(true);
		GraphOutput output2 = graph.validateGraph();
		Assert.assertEquals(output2.isValid(), true);
	}
}
