package protocol;

public enum JavaBleCommunication {
	COMMANDOENDING("\n"), REQUESTALIVE("ra"), ALIVE("a"), SENDACCESSRIGHT("ar"), SENDLANGUAGE("l"), UNKNOWNCOMMAND(
			"unknown");

	private final String commandString;

	private JavaBleCommunication(final String commandString) {
		this.commandString = commandString;
	}

	@Override
	public String toString() {
		return commandString;
	}

	public String toCommand() {
		return commandString + COMMANDOENDING;
	}

	public static JavaBleCommunication convertCommandStringToCommandType(String commandString) {
		for (JavaBleCommunication commandType : JavaBleCommunication.values()) {
			if (commandType.toString().equals(commandString)) {
				return commandType;
			}
		}

		return UNKNOWNCOMMAND;
	}
}