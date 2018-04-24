package protocol;

public enum JavaBle {
	COMMANDOENDING("\n"), REQUESTALIVE("ra"), ALIVE("a"), SENDACCESSRIGHT("ar"), SENDLANGUAGE("l"), UNKNOWNCOMMAND(
			"unknown");

	private final String commandString;

	private JavaBle(final String commandString) {
		this.commandString = commandString;
	}

	@Override
	public String toString() {
		return commandString;
	}

	public String toCommand() {
		return commandString + COMMANDOENDING;
	}

	public static JavaBle convert(String commandString) {
		for (JavaBle commandType : JavaBle.values()) {
			if (commandType.toString().equals(commandString)) {
				return commandType;
			}
		}

		return UNKNOWNCOMMAND;
	}
}