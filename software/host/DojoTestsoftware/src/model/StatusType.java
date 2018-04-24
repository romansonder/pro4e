package model;

public enum StatusType {
	RUNNING("Anwendung geladen."), DATATRANSMITTINGSUCCESSFUL(
			"Ausstellung wurde erfolgreich �bertragen."), DOJODRIVENOTFOUND(
					"Dojo wurde nicht gefunden."), READOBJECTSFAILURE(
							"Fehler beim laden der Austellung."), NOOPENCONNECTION(
									"Keine Bluetooth Verbindung."), OPENPORTFAILURE(
											"Fehler beim Verbindung aufbauen: "), ClOSEPORTFAILURE(
													"Fehler beim schliessen des COM-Ports: "), OPENEDCONNECTION(
															"Verbidung wurde ge�ffnet: "), PORTCLOSED(
																	"Verbidung wurde erfolgreich geschlossen: ");

	private final String statusMitteilung;

	private StatusType(final String statusMitteilung) {
		this.statusMitteilung = statusMitteilung;
	}

	@Override
	public String toString() {
		return statusMitteilung;
	}
}