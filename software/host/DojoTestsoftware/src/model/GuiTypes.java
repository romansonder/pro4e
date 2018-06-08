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
package model;

public class GuiTypes {

	public enum AccessRightsTypes {
		LEVEL1("Level 1"), LEVEL2("Level 2"), LEVEL3("Level 3"), LEVEL4("Level 4"), LEVEL5("Level 5");

		private final String accessRights;

		private AccessRightsTypes(final String accessRights) {
			this.accessRights = accessRights;
		}

		@Override
		public String toString() {
			return accessRights;
		}
	}

	public enum LanguagesTypes {
		GERMAN("Deutsch"), ENGLISH("Englisch"), FRENCH("Französisch");

		private final String languageName;

		private LanguagesTypes(final String languageName) {
			this.languageName = languageName;
		}

		@Override
		public String toString() {
			return languageName;
		}
	}
}
