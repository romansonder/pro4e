package model;

public enum StatusType {
	RUNNING("Anwendung geladen."), DATATRANSMITTINGSUCCESSFUL(
			"Ausstellung wurde erfolgreich �bertragen."), DOJODRIVENOTFOUND(
					"Dojo wurde nicht gefunden."), DATAFILENOTFOUND(
							"Folgende Datei wurde nicht gefunden: "), DATATRANSMITTINGFAILURE(
									"Fehler beim �bertragen von Ausstellung."), READOBJECTSFAILURE(
											"Fehler beim laden der Austellung."), NOOPENCONNECTION(
													"Keine serielle Verbindung."), USERPREFERENCESTRANSMITTINGFAILURE(
															"Fehler beim �bertragen von Benutzereinstellungen: "), OPENPORTFAILURE(
																	"Fehler beim Verbindung aufbauen: "), ClOSEPORTFAILURE(
																			"Fehler beim schliessen des COM-Ports: "), OPENEDCONNECTION(
																					"Verbidung wurde ge�ffnet: "), EXTRACTDOJOINFORMATIONFAILURE(
																							"Fehler beim Dojo auswerten aufgetreten."), PORTCLOSED(
																									"Verbidung wurde erfolgreich geschlossen: ");

	private final String statusMessage;

	private StatusType(final String statusMessage) {
		this.statusMessage = statusMessage;
	}

	@Override
	public String toString() {
		return statusMessage;
	}
}