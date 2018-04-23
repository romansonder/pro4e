package model;

public enum StatusType {
	RUNNING("Anwendung geladen."), NOOPENCONNECTION("Keine Bluetooth Verbindung."), OPENPORTFAILURE(
			"Fehler beim Verbindung aufbauen: "), ClOSEPORTFAILURE(
					"Fehler beim schliessen des COM-Ports: "), OPENEDCONNECTION(
							"Verbidung wurde geöffnet: "), PORTCLOSED("Verbidung wurde erfolgreich geschlossen: ");

	private final String statusMitteilung;

	private StatusType(final String statusMitteilung) {
		this.statusMitteilung = statusMitteilung;
	}

	@Override
	public String toString() {
		return statusMitteilung;
	}
}