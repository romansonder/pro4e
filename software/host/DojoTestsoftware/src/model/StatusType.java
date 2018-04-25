package model;

public enum StatusType {
	RUNNING("Anwendung geladen."), PREFERENCESTRANSMITTINGFAILURE(
			"Fehler beim Übertragen von Einstellungen."), PREFERENCESTRANSMITTINGSUCCESSFUL(
					"Einstellungen wurde erfolgreich übertragen."), DATATRANSMITTINGSUCCESSFUL(
							"Ausstellung wurde erfolgreich übertragen."), TRANSMITTINGDATA(
									"Einstellungen werden übertragen "), TRANSMITTINGPREFERENCES(
											"Ausstellung wird übertragen "), DOJODRIVENOTFOUND(
													"Dojo wurde nicht gefunden."), DATAFILENOTFOUND(
															"Folgende Datei wurde nicht gefunden: "), NODATATOTRANSMIT(
																	"Keine Ausstellungsobjekte vorhanden."), DATATRANSMITTINGFAILURE(
																			"Fehler beim Übertragen von Ausstellung."), READOBJECTSFAILURE(
																					"Fehler beim laden der Austellung."), NOOPENCONNECTION(
																							"Keine serielle Verbindung."), USERPREFERENCESTRANSMITTINGFAILURE(
																									"Fehler beim Übertragen von Benutzereinstellungen: "), OPENPORTFAILURE(
																											"Fehler beim Verbindung aufbauen: "), ClOSEPORTFAILURE(
																													"Fehler beim schliessen des COM-Ports: "), OPENEDCONNECTION(
																															"Verbidung wurde geöffnet: "), EXTRACTDOJOINFORMATIONFAILURE(
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