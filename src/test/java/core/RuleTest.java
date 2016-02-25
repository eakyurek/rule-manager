package core;

import org.junit.Assert;
import org.junit.Test;


public class RuleTest {
	@Test
	public void statusAfterValidate() {
		Rule validTestRule = new DummyRule("validTestRule", true);
		Rule invalidTestRule = new DummyRule("invalidTestRule", false);
		
		Assert.assertEquals(validTestRule.getStatus(), RuleStatus.UNVISITED);
		Assert.assertEquals(invalidTestRule.getStatus(), RuleStatus.UNVISITED);
		
		validTestRule.validate();
		invalidTestRule.validate();
		
		Assert.assertEquals(validTestRule.getStatus(), RuleStatus.VALID);
		Assert.assertEquals(invalidTestRule.getStatus(), RuleStatus.INVALID);
	}
}
