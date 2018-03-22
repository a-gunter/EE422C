package assignment4;
/* CRITTERS Critter4.java
 * EE422C Project 4 submission by
 * <Matthew Davis>
 * <mqd224>
 * <15510>
 * <Austin Gunter>
 * <asg2523>
 * <15510>
 * Spring 2018
 */


/**
 * Unique Critter 4
 * Doesn't reproduce in order to conserve energy
 * Likes to save energy for fights, so only move 50% of the time. Lets the other critters come to him.
 * Can't walk, can only run or stand still.
 * Always accepts a fight
 * @author Austin
 *
 */
public class Critter4 extends Critter{
	
	private int dir;
	private static int numFights = 0; // number of fights that have been picked by all Critter4s.
	
	/**
	 * Constructor which sets direction
	 */
	public Critter4() {
		dir = Critter.getRandomInt(8);
	}
	
	/**
	 * Only moves sometimes, and when he does move, he runs.
	 */
	@Override
	public void doTimeStep() {
		int shouldRun = Critter.getRandomInt(2);
		if(shouldRun == 1) {
			run(dir);
			dir = Critter.getRandomInt(8);
		}
	}

	/**
	 * Always accepts a fight
	 */
	@Override
	public boolean fight(String opponent) {
		numFights++;
		return true;
	}
	
	/**
	 * 
	 * @param critters List of Critter4s
	 */
	public static void runStats(java.util.List<Critter> critter4s) {
		System.out.print("" + critter4s.size() + " total Critter4s, and ");
		System.out.println(numFights + " fights have been picked");
	}
	
	/**
	 * Returns String "4" as specified in documentation
	 */
	public String toString() {
		return "4";
	}
	
}