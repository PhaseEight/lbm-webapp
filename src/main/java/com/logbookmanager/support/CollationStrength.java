package com.logbookmanager.support;

import java.text.Collator;

public enum CollationStrength {
	Primary(Collator.PRIMARY), // base char
	Secondary(Collator.SECONDARY), // base char + accent
	Tertiary(Collator.TERTIARY), // base char + accent + case
	Identical(Collator.IDENTICAL); // base char + accent + case + bits

	public int getStrength() {
		return strength;
	}

	private int strength;

	private CollationStrength(int aStrength) {
		strength = aStrength;
	}
}
