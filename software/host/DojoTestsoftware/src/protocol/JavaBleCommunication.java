/*******************************************************************************
 * Copyright (C) 2018  FHNW Pro4E FS18 Team 3
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
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
