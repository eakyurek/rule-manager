package core;

import core.Rule;
import core.output.RuleOutput;

public class DummyRule extends Rule {

	private String name;
	private boolean valid;

	public DummyRule(String name, boolean valid) {
		this.name = name;
		this.valid = valid;
	}

	@Override
	protected RuleOutput runner() {
		// System.out.println(name + ": " + valid);
		return new RuleOutput(valid);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	@Override
	public String toString() {
		return "DummyRule [name=" + name + "]";
	}
}
