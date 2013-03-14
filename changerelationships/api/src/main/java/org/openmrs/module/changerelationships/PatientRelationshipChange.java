package org.openmrs.module.changerelationships;

public class PatientRelationshipChange {
	private String nameIn;
	private String relationshipType;
	private String nameOut;
	public String getNameIn() {
		return nameIn;
	}
	public void setNameIn(String nameIn) {
		this.nameIn = nameIn;
	}
	public String getRelationshipType() {
		return relationshipType;
	}
	public void setRelationshipType(String relationshipType) {
		this.relationshipType = relationshipType;
	}
	public String getNameOut() {
		return nameOut;
	}
	public void setNameOut(String nameOut) {
		this.nameOut = nameOut;
	}

}
