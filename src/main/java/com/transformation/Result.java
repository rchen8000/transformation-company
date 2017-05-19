package com.transformation;

import java.util.HashMap;
import java.util.Map;

public class Result {
	
	private static Map<Integer, String> resultMap = new HashMap<Integer, String>(){{
		put (999, "Pass!");
		put (0, "Robot in Autobots team win!");
		put (1, "Robot in Decepticons team win!");
		put (2, "Both team destoried!");
		put (3, "Autobots team win! All Decepticons team destoried!");
		put (4, "Decepticons team win! All Autobots team destoried!");
		put (5, "Error! Two robots are on same team!");
		put (6, "Error! Either robots is on wrong team!");
		put (7, "Tie! Both robots are destoried!");
	}};
	
	public static String getMessage(int key){
		return resultMap.get(key);
	}
}