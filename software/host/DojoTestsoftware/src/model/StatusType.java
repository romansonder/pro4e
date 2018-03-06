package model;

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