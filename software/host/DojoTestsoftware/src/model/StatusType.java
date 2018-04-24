package model;

public enum StatusType {
	RUNNING("Anwendung geladen."), DATATRANSMITTINGSUCCESSFUL(
			"Ausstellung wurde erfolgreich �bertragen."), DOJODRIVENOTFOUND(
					"Dojo wurde nicht gefunden."), READOBJECTSFAILURE(
							"Fehler beim laden der Austellung."), NOOPENCONNECTION(
									"Keine Bluetooth Verbindung."), USERPREFERENCESTRANSMITTINGFAILURE(
											"Fehler beim �bertragen von Benutzereinstellungen: "), OPENPORTFAILURE(
													"Fehler beim Verbindung aufbauen: "), ClOSEPORTFAILURE(
															"Fehler beim schliessen des COM-Ports: "), OPENEDCONNECTION(
																	"Verbidung wurde ge�ffnet: "), EXTRACTDOJOINFORMATIONFAILURE(
																			"Fehler beim Dojo auswerten aufgetreten."), PORTCLOSED(
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