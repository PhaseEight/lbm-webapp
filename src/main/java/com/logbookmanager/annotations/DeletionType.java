package com.logbookmanager.annotations;

import java.util.PriorityQueue;

public enum DeletionType {
	PhysicalDelete {
		@Override
		public String toString() {
			return "Physical Delete";
		}
	},
	LogicalDelete {
		@Override
		public String toString() {
			return "Logical Delete";
		}
	};
	PriorityQueue<Integer> p = new PriorityQueue<Integer>();

}