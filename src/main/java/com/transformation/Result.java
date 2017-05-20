package com.transformation;

import java.util.HashMap;
import java.util.Map;

public class Result {
	
	public static final int PASS = 999;
	public static final int AUTOBOTS_WIN = 0;
	public static final int DECEPTIONS_WIN = 1;
	public static final int BOTH_TEAM_DESTORIED = 2;
	public static final int OPIMUS_PRIME_WIN = 3;
	public static final int PREDAKING_WIN = 4;
	public static final int ERROR_SAME_TEAM = 5;
	public static final int ERROR_WRONG_TEAM = 6;
	public static final int TIE = 7;
	
	
	private static Map<Integer, String> resultMap = new HashMap<Integer, String>(){{
		put (PASS, "Pass!");
		put (AUTOBOTS_WIN, "Robot in Autobots team win!");
		put (DECEPTIONS_WIN, "Robot in Decepticons team win!");
		put (BOTH_TEAM_DESTORIED, "Both team destoried!");
		put (OPIMUS_PRIME_WIN, "Autobots team win! All Decepticons team destoried!");
		put (PREDAKING_WIN, "Decepticons team win! All Autobots team destoried!");
		put (ERROR_SAME_TEAM, "Error! Two robots are on same team!");
		put (ERROR_WRONG_TEAM, "Error! Either robots is on wrong team!");
		put (TIE, "Tie! Both robots are destoried!");
	}};
	
	public static String getMessage(int key){
		return resultMap.get(key);
	}
}
