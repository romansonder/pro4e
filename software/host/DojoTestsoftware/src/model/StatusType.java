package model;

public enum StatusType {
	RUNNING("Anwendung geladen."), PREFERENCESTRANSMITTINGFAILURE(
			"Fehler beim �bertragen von Einstellungen."), PREFERENCESTRANSMITTINGSUCCESSFUL(
					"Einstellungen wurde erfolgreich �bertragen."), DATATRANSMITTINGSUCCESSFUL(
							"Ausstellung wurde erfolgreich �bertragen."), TRANSMITTINGDATA(
									"Einstellungen werden �bertragen "), TRANSMITTINGPREFERENCES(
											"Ausstellung wird �bertragen "), DOJODRIVENOTFOUND(
													"Dojo wurde nicht gefunden."), DATAFILENOTFOUND(
															"Folgende Datei wurde nicht gefunden: "), NODATATOTRANSMIT(
																	"Keine Ausstellungsobjekte vorhanden."), DATATRANSMITTINGFAILURE(
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