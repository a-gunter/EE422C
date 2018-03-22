package assignment4;
/* CRITTERS Critter3.java
 * EE422C Project 4 submission by
 * <Matthew Davis>
 * <mqd224>
 * <15510>
 * <Austin Gunter>
 * <asg2523>
 * <15510>
 * Spring 2018
 */

import java.util.List;

/**
 * Unique Critter 3
 * He's not an ambiturner, so he can't turn left.
 * Reproduces whenever possible, but won't move in the same turn of reproduction.
 * Fights if he has >= 1/4 of starting energy.
 * @author Austin
 *
 */
public class Critter3 extends Critter{
	
	private int dir;
	private List<Integer> forbiddenDir;
	private static int numBabies = 0; // number of Babies that have been created by all Critter3s
	
	/**
	 * Constructor which sets direction
	 */
	public Critter3() {
		dir = Critter.getRandomInt(8);
	}
	
	/**
	 * 
	 */
	@Override
	public void doTimeStep() {
		// check if can reproduce
		if(getEnergy() >= Params.min_reproduce_energy) {
			Critter3 child = new Critter3();
			reproduce(child, Critter.getRandomInt(8));
			numBabies++;
		} else {
			walk(dir);
			while(true) {
				int tryDirection = Critter.getRandomInt(8);
				if(!forbiddenDir.contains(tryDirection)) {
					dir = tryDirection;
					break;
				}
			}
			if(!forbiddenDir.isEmpty()) {
				forbiddenDir.clear();
			}
			for(int i = 1; i < 4; i++) {	// update forbidden directions (can't turn left). Critter is assumed to be facing in the direction that he last moved.
				forbiddenDir.add((dir + i) % 8);
			}
		}
	}

	/**
	 * 
	 */
	@Override
	public boolean fight(String opponent) {
		if(getEnergy() >= (Params.start_energy / 4)) {
			return true;
		} else {
			run(dir);
			return false;
		}
	}
	
	/**
	 * 
	 * @param critters List of Critter1's
	 */
	public static void runStats(java.util.List<Critter> critter3s) {
		System.out.print("" + critter3s.size() + " total Critter3s, and ");
		System.out.print(numBabies + " babies have been made");
	}
	
	/**
	 * Returns String "3" as specified in documentation
	 */
	public String toString() {
		return "3";
	}
	
}