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

public enum StatusType {
	RUNNING("Anwendung geladen."), DOJONOTRESPONDING("Dojo antwortet in gewünschter Zeit nicht."), EVALUATIONSUCCESSFUL(
			"Auswertung erfolgreich abgeschlossen."), PREFERENCESTRANSMITTINGFAILURE(
					"Fehler beim Übertragen von Einstellungen."), PREFERENCESTRANSMITTINGSUCCESSFUL(
							"Einstellungen wurde erfolgreich übertragen."), DATATRANSMITTINGSUCCESSFUL(
									"Ausstellung wurde erfolgreich übertragen."), FILLOUTALLFIELDS(
											"Bitte alle Felder ausfüllen und einen Pfad auswählen."), LANGUAGEDUPLICATE(
													"Ausstellungsobjekt bereits vorhanden: "), MUSEUMSOBJECTSUCCESSFULLCREATED(
															"Ausstellungsobjekt erfolgreich erstellt."), LANGUAGEMISSING(
																	"Ausstellungsobjekt nicht vorhanden: "), TRANSMITTINGDATA(
																			"Ausstellung wird übertragen "), TRANSMITTINGPREFERENCES(
																					"Dojo wird konfiguriert "), TRANSMITTINGDOJOEVALUATION(
																							"Dojo wird ausgewertet "), DOJODRIVENOTFOUND(
																									"Dojo wurde nicht gefunden."), DATAFILENOTFOUND(
																											"Folgende Datei wurde nicht gefunden: "), NODATATOTRANSMIT(
																													"Keine Ausstellungsobjekte vorhanden."), DATATRANSMITTINGFAILURE(
																															"Fehler beim Übertragen von Ausstellung."), READOBJECTSFAILURE(
																																	"Fehler beim laden der Austellung."), NOOPENCONNECTION(
																																			"Keine serielle Verbindung."), TRANSMITTINGDATAFAILURE(
																																					"Fehler beim Übertragen von Daten: "), OPENPORTFAILURE(
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
