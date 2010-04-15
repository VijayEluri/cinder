package org.art_core.dev.cinder.model;

/**
 * ItemSource for CINDER IItems
 * @author Florian Anderiasch
 *
 */
public enum ItemSource {
	PADAWAN("PADAWAN"),
	PHPMD("PHPMD"),
	CHECKSTYLE("CHECKSTYLE"),
	CPD("CPD");
	
	private final String sName;
	
	ItemSource(String sName) {
		this.sName = sName;
	}
	
	public String toString() {
		return this.sName;
	}
}
