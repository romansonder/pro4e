package protocol;

public enum JavaBleCommunication {
	COMMANDOENDING("\n"), ACCESSLEVEL1("a1"), ACCESSLEVEL2("a2"), ACCESSLEVEL3("a3"), ACCESSLEVEL4("a4"), ACCESSLEVEL5(
			"a5"), LANGUAGEGERMAN("lg"), LANGUAGEENGLISH("le"), LANGUAGEFRENCH("lf"), REQUESTALIVE("ra"), AKNOWLEDGE(
					"ak"), SENDACCESSRIGHT("ar"), SENDLANGUAGE("ar"), REQUESTLANGUAGE(
							"rl"), REQUESTACCESSLEVEL("real"), REQUESTEVALUATION("id"), UNKNOWNCOMMAND("unknown");

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