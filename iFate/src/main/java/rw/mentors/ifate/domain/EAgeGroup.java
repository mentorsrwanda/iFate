package rw.mentors.ifate.domain;

public enum EAgeGroup {

	teenager("15-25"), adult("26-45"), old("46+");

	private final String range;
	

	EAgeGroup(String ranges) {
		range = ranges;
	}

	public String getRange() {
		return range;
	}

}
