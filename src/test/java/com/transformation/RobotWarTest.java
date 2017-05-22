package com.transformation;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

import com.transformation.Robot;
import com.transformation.RobotWar;
import com.transformation.Team;
import com.transformation.War;

public class RobotWarTest {

	private final String OPTIMUS_PRIME = "Optimus Prime";
	private final String PREDAKING = "Predaking";

	@Test
	public void testBattle() {

		// three robots
		List<Robot> robots = new ArrayList<Robot>() {
			{
				add(new Robot("Soundwave", Team.D, 8, 9, 2, 6, 7, 5, 6, 10));
				add(new Robot("Bluestreak", Team.A, 6, 6, 7, 9, 5, 2, 9, 7));
				add(new Robot("Hubcap", Team.A, 4, 4, 4, 4, 4, 4, 4, 4));
			}
		};
		War<Robot> war = new RobotWar(robots);
		Optional<List<Integer>> results = war.battle();
		assertTrue(results.map(List::size).orElse(0) == 1);
		assertTrue(results.get().get(0) == Result.DECEPTIONS_WIN);

		// No robots
		List<Robot> emptyRobots = new ArrayList<Robot>();
		War<Robot> noWar = new RobotWar(emptyRobots);
		Optional<List<Integer>> newResult = noWar.battle();
		assertTrue(newResult.map(List::size).orElse(0) == 0);

		// same ability robots
		List<Robot> tieRobots = new ArrayList<Robot>() {
			{
				add(new Robot("Soundwave", Team.D, 8, 9, 2, 6, 7, 5, 6, 10));
				add(new Robot("Bluestreak", Team.A, 8, 9, 2, 6, 7, 5, 6, 10));
			}
		};
		War<Robot> tieWar = new RobotWar(tieRobots);
		Optional<List<Integer>> tieResult = tieWar.battle();
		assertTrue(tieResult.map(List::size).orElse(0) == 1);
		assertTrue(tieResult.get().get(0) == Result.TIE);

		// optimus prime join battle
		List<Robot> optinusTeam = new ArrayList<Robot>() {
			{
				add(new Robot("Soundwave", Team.D, 8, 9, 2, 6, 7, 5, 6, 10));
				add(new Robot("Bluestreak", Team.A, 6, 6, 7, 9, 5, 2, 9, 7));
				add(new Robot("Hubcap", Team.A, 4, 4, 4, 4, 4, 4, 4, 4));
				add(new Robot(OPTIMUS_PRIME, Team.A, 4, 4, 4, 4, 4, 4, 4, 4));
			}
		};
		War<Robot> optimusPrimeWar = new RobotWar(optinusTeam);
		Optional<List<Integer>> optimusResult = optimusPrimeWar.battle();
		assertTrue(optimusResult.map(List::size).orElse(0) == 1);
		assertTrue(optimusResult.get().get(0) == Result.OPIMUS_PRIME_WIN);

		// predaking join robots
		List<Robot> predakingTeam = new ArrayList<Robot>() {
			{
				add(new Robot("Soundwave", Team.D, 8, 9, 2, 6, 7, 5, 6, 10));
				add(new Robot("Bluestreak", Team.A, 6, 6, 7, 9, 5, 2, 9, 7));
				add(new Robot("Hubcap", Team.A, 4, 4, 4, 4, 4, 4, 4, 4));
				add(new Robot(PREDAKING, Team.D, 4, 4, 4, 4, 4, 4, 4, 4));
			}
		};
		War<Robot> predakingWar = new RobotWar(predakingTeam);
		Optional<List<Integer>> predakingResult = predakingWar.battle();
		assertTrue(predakingResult.map(List::size).orElse(0) == 1);
		assertTrue(predakingResult.get().get(0) == Result.PREDAKING_WIN);
		
		
		// optimus prime vs predaking 
		List<Robot> predakingVsOptimusIn = new ArrayList<Robot>() {
			{
				add(new Robot("Soundwave", Team.D, 8, 9, 2, 6, 7, 5, 6, 10));
				add(new Robot(OPTIMUS_PRIME, Team.A, 6, 6, 7, 9, 5, 2, 9, 7));
				add(new Robot("Hubcap", Team.A, 4, 4, 4, 4, 4, 4, 4, 4));
				add(new Robot(PREDAKING, Team.D, 4, 4, 4, 4, 4, 4, 4, 4));
			}
		};
		War<Robot> predakingVsOptimusWar = new RobotWar(predakingVsOptimusIn);
		Optional<List<Integer>> predakingVsOptimusResult = predakingVsOptimusWar.battle();
		assertTrue(predakingVsOptimusResult.map(List::size).orElse(0) == 1);
		assertTrue(predakingVsOptimusResult.get().get(0) == Result.BOTH_TEAM_DESTORIED);
		
		// same team robots
		List<Robot> sameTeam = new ArrayList<Robot>() {
			{
				add(new Robot("Bluestreak", Team.A, 6, 6, 7, 9, 5, 2, 9, 7));
				add(new Robot("Hubcap", Team.A, 4, 4, 4, 4, 4, 4, 4, 4));
			}
		};
		War<Robot> sameTeamWar = new RobotWar(sameTeam);
		Optional<List<Integer>> sameTeamResult = sameTeamWar.battle();
		assertTrue(sameTeamResult.map(List::size).orElse(0) == 0);
		
		// two battles
				List<Robot> twoBattlesTeam = new ArrayList<Robot>() {
					{
						add(new Robot("Soundwave", Team.D, 8, 9, 2, 6, 7, 5, 6, 10));
						add(new Robot("Bluestreak", Team.A, 6, 6, 7, 9, 5, 2, 9, 7));
						add(new Robot("Hubcap", Team.A, 4, 4, 4, 4, 4, 4, 4, 4));
						add(new Robot("Sideswipe", Team.D, 2, 2, 2, 2, 2, 2, 2, 2));
					}
				};
				War<Robot> twoBattlsWar = new RobotWar(twoBattlesTeam);
				Optional<List<Integer>> twoBattlesResult = twoBattlsWar.battle();
				assertTrue(twoBattlesResult.map(List::size).orElse(0) == 2);
				assertTrue(twoBattlesResult.get().get(0) == Result.DECEPTIONS_WIN);
				assertTrue(twoBattlesResult.get().get(1) == Result.AUTOBOTS_WIN);
				assertTrue(twoBattlsWar.getATeam().map(List::size).orElse(0) == 1);
				assertTrue(twoBattlsWar.getBTeam().map(List::size).orElse(0) == 1);
				

	}
	
}
