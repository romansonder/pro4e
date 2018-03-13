package model;

public class GuiTypes {
	public enum StatusType {
		RUNNING("Anwendung geladen.");

		private final String statusMitteilung;

		private StatusType(final String statusMitteilung) {
			this.statusMitteilung = statusMitteilung;
		}

		@Override
		public String toString() {
			return statusMitteilung;
		}
	}

	public enum AccessRightsTypes {
		LEVEL1("Level 1"), LEVEL2("Level 2"), LEVEL3("Level 3");

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