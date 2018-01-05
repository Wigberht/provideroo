package com.dimbo.command;

/**
 * The Enum Commands.
 */
public enum Commands {
	
	/** The login. */
	LOGIN {
		public Command getCommand() {
			return new LoginCommand();
		}	
	};
	
	/**
	 * Gets the command.
	 *
	 * @return the command
	 */
	public abstract Command getCommand();
}
