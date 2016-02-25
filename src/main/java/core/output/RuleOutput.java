package core.output;

/**
 * This class contains the result of a rule validation.
 */
public class RuleOutput {

	private boolean valid;
	private String message;

	/**
	 * Sets valid to true and initializes message.
	 */
	public RuleOutput() {
		this.valid = false;
		this.message = "";
	}

	/**
	 * Sets valid to the given parameter and initializes message
	 * @param valid
	 */
	public RuleOutput(boolean valid) {
		this();
		this.valid = valid;
	}

	/**
	 * Sets valid and message as given parameters
	 * @param valid
	 * @param message
	 */
	public RuleOutput(boolean valid, String message) {
		this(valid);
		this.message = message;
	}

	 /**
	  * Is the rule valid
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
	 * Gets message
	 * @return message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets message
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "RuleOutput [valid=" + valid + ", message=" + message + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((message == null) ? 0 : message.hashCode());
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
		RuleOutput other = (RuleOutput) obj;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (valid != other.valid)
			return false;
		return true;
	}

}
