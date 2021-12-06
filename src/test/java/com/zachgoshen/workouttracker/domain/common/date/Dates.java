package com.zachgoshen.workouttracker.domain.common.date;

import java.util.Date;

public class Dates {
	
	private static final int MILLISECONDS_IN_ONE_HOUR = 3600 * 1000;
	
	public static Date rightNow() {
		return new Date();
	}
	
	public static Date oneHourAgo() {
		return new Date(System.currentTimeMillis() - MILLISECONDS_IN_ONE_HOUR);
	}
	
	public static Date oneHourFromNow() {
		return new Date(System.currentTimeMillis() + MILLISECONDS_IN_ONE_HOUR);
	}

}
