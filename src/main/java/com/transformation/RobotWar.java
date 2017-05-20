package com.transformation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RobotWar implements War<Robot> {

	private static final String OPTIMUS_PRIME = "Optimus Prime";
	private static final String PREDAKING = "Predaking";

	private List<Robot> autobotsTeam;
	private List<Robot> decepticonsTeam;
	
	@Override
	public Optional<List<Robot>> getATeam() {
		return Optional.of(this.autobotsTeam);
	}

	@Override
	public Optional<List<Robot>> getBTeam() {
		return Optional.of(this.decepticonsTeam);
	}

	public RobotWar(List<Robot> robots) {

		this.autobotsTeam = Stream
				.concat(robots.stream().filter(r -> r.getName().equalsIgnoreCase(OPTIMUS_PRIME)),
						robots.stream().filter(robot -> robot.getTeam() == Team.A)
								.filter(robot -> !robot.getName().equalsIgnoreCase(OPTIMUS_PRIME)).sorted(
										(r1, r2) -> Integer.compare(r2.overallRating(), r1.overallRating())))
				.collect(Collectors.toList());

		this.decepticonsTeam = Stream
				.concat(robots.stream().filter(r -> r.getName().equalsIgnoreCase(PREDAKING)),
						robots.stream().filter(robot -> robot.getTeam() == Team.D)
								.filter(robot -> !robot.getName().equalsIgnoreCase(PREDAKING)).sorted(
										(r1, r2) -> Integer.compare(r2.overallRating(), r1.overallRating())))
				.collect(Collectors.toList());

	}

	private static BiFunction<Robot, Robot, Integer> fightRule(BiPredicate<Robot, Robot> bp, Integer result) {
		return (r1, r2) -> bp.test(r1, r2) ? result : 999;
	}

	private static List<BiFunction<Robot, Robot, Integer>> fightRules = Arrays.asList(
			fightRule((r1, r2) -> r1.getTeam().equals(r2.getTeam()), 5),
			fightRule((r1, r2) -> r1.getTeam().equals(Team.D) || r2.getTeam().equals(Team.A), 6),
			fightRule((r1, r2) -> r1.getName().equalsIgnoreCase(OPTIMUS_PRIME)
					&& r2.getName().equalsIgnoreCase(PREDAKING), 2),
			fightRule((r1, r2) -> r1.getName().equalsIgnoreCase(OPTIMUS_PRIME), 3),
			fightRule((r1, r2) -> r2.getName().equalsIgnoreCase(PREDAKING), 4),
			fightRule((r1, r2) -> r1.getCourage() - r2.getCourage() >= 4 && r1.getStrength() - r2.getStrength() >= 3,
					0),
			fightRule((r1, r2) -> r2.getCourage() - r1.getCourage() >= 4 && r2.getStrength() - r1.getStrength() >= 3,
					1),
			fightRule((r1, r2) -> r1.getSkill() - r2.getSkill() >= 3, 0),
			fightRule((r1, r2) -> r2.getSkill() - r1.getSkill() >= 3, 1),
			fightRule((r1, r2) -> r1.overallRating() == r2.overallRating(), 7),
			fightRule((r1, r2) -> r1.overallRating() > r2.overallRating(), 0),
			fightRule((r1, r2) -> r2.overallRating() > r1.overallRating(), 1));

	@Override
	public Optional<List<Integer>> battle() {

		List<Integer> results = new ArrayList<Integer>();
		Iterator<Robot> autobotsIterator = autobotsTeam.iterator();
		Iterator<Robot> decepticonsIterator = decepticonsTeam.iterator();

		while (autobotsIterator.hasNext() && decepticonsIterator.hasNext()) {
			Robot autobot = autobotsIterator.next();
			Robot deceticon = decepticonsIterator.next();

			int battleResult = fightRules.stream().flatMap(bf -> {
				Integer result = bf.apply(autobot, deceticon);
;
				return Stream.of(result);
			}).filter(value -> value != 999).findFirst().get();

			results.add(battleResult);
			System.out.printf("battle %d: %s(%s) vs %s(%s)!, %s\n", results.size(), autobot.getName(), autobot.getTeam(), deceticon.getName(), deceticon.getTeam(), Result.getMessage(battleResult));
			

			if (battleResult == 0) {
				decepticonsIterator.remove();
			} else if (battleResult == 1) {
				autobotsIterator.remove();
			} else if (battleResult == 7) {
				decepticonsIterator.remove();
				autobotsIterator.remove();
			} else {
				break;
			}

		}

		return Optional.of(results);
	}

	public static void main(String[] args) {
		List<Robot> robots = new ArrayList<Robot>() {
			{
				add(new Robot("Soundwave", Team.D, 8, 9, 2, 6, 7, 5, 6, 10));
				add(new Robot("Bluestreak", Team.A, 6, 6, 7, 9, 5, 2, 9, 7));
				add(new Robot("Hubcap", Team.A, 4, 4, 4, 4, 4, 4, 4, 4));
				//add(new Robot("Scrapper", Team.D, 2, 3, 2, 2, 2, 2, 2, 2));
				//add(new Robot(OPTIMUS_PRIME, Team.A, 4, 4, 4, 4, 4, 4, 4,
				//4));
				//add(new Robot(PREDAKING, Team.D, 8, 9, 2, 6, 7, 5, 6, 10));
			}
		};
		robots.stream().forEach(System.out::println);
		System.out.println();
		
		War<Robot> war = new RobotWar(robots);
		int autobotsTeamBeforeBattle = war.getATeam().map(List::size).orElse(0);
		int decepticonsTeamBeforeBattle = war.getBTeam().map(List::size).orElse(0);

		Optional<List<Integer>> results = war.battle();

		if (results.isPresent()) {
			System.out.printf("Total %d battle(s).\n", results.get().size());
			if (results.get().stream().filter(r -> r != 0 && r != 1 && r != 7).findAny().isPresent()) {
				results.get().stream().filter(r -> r != 0 && r != 1 && r != 7).forEach(r -> System.out.println(Result.getMessage(r)));
			} else {
				int autobotsTeamAfterBattle = war.getATeam().map(List::size).orElse(0);
				int decepticonsTeamAfterBattle = war.getBTeam().map(List::size).orElse(0);

				int autobotsTeamLost = autobotsTeamBeforeBattle - autobotsTeamAfterBattle;
				int decepticonsTeamLost = decepticonsTeamBeforeBattle - decepticonsTeamAfterBattle;
				if (autobotsTeamLost > decepticonsTeamLost) {
					System.out.println("Decepticons team win!");
					System.out.println("Survivors from losing team (Autobots)");
					war.getATeam().get().forEach(System.out::println);
				} else if (autobotsTeamLost < decepticonsTeamLost) {
					System.out.println("Autobots Team win!");
					System.out.println("Survivors from losing team (Decepticons)");
					war.getBTeam().get().forEach(System.out::println);
				} else {
					System.out.println("Tie!");
					System.out.println("Survivors from both team");
					war.getATeam().get().forEach(System.out::println);
					war.getBTeam().get().forEach(System.out::println);
				}

			}

		}

	}

}
