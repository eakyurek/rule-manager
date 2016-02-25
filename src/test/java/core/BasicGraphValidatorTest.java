package core;

import java.util.HashSet;

import org.junit.Assert;
import org.junit.Test;

import core.output.GraphOutput;
import core.validator.BasicGraphValidator;

public class BasicGraphValidatorTest {
	@Test(expected=IllegalArgumentException.class)
	public void emptyGraph() {
		BasicGraphValidator validator = new BasicGraphValidator();
		validator.validateGraph(new HashSet<Rule>());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void nullGraph() {
		BasicGraphValidator validator = new BasicGraphValidator();
		validator.validateGraph(null);
	}
	
	@Test
	public void semiComplexGraph() {
		RuleGraph graph = new RuleGraph(new BasicGraphValidator());
		
		Rule A = new DummyRule("A", true);
		Rule B = new DummyRule("B", true);
		Rule C = new DummyRule("C", true);
		Rule D = new DummyRule("D", false);
		Rule E = new DummyRule("E", true);
		Rule F = new DummyRule("F", true);
		Rule G = new DummyRule("G", true);
		
		graph.addRule(A);
		graph.addRule(B);
		graph.addRule(C);
		graph.addRule(D);
		graph.addRule(E);
		graph.addRule(F);
		graph.addRule(G);
		
		graph.addRelation(A, C);
		graph.addRelation(A, D);
		graph.addRelation(B, D);
		graph.addRelation(C, E);
		graph.addRelation(C, F);
		graph.addRelation(D, F);
		graph.addRelation(E, G);
		graph.addRelation(F, G);
		
		GraphOutput output = graph.validateGraph();
		
		Assert.assertEquals(output.isValid(), false);
		Assert.assertEquals(A.getStatus(), RuleStatus.VALID);
		Assert.assertEquals(B.getStatus(), RuleStatus.VALID);
		Assert.assertEquals(C.getStatus(), RuleStatus.VALID);
		Assert.assertEquals(D.getStatus(), RuleStatus.INVALID);
		Assert.assertEquals(E.getStatus(), RuleStatus.VALID);
		Assert.assertEquals(F.getStatus(), RuleStatus.SKIPPED);
		Assert.assertEquals(G.getStatus(), RuleStatus.SKIPPED);
	}
}
