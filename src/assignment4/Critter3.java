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

import java.util.ArrayList;
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
	private List<Integer> forbiddenDir = new ArrayList<Integer>();
	private static int numBabies = 0; // number of Babies that have been created by all Critter3s
	
	/**
	 * Constructor which sets direction
	 */
	public Critter3() {
		dir = Critter.getRandomInt(8);
	}
	
	/**
	 * Does time step for Critter3.
	 * Reproduces if possible, and won't move if he reproduced during the timestep.
	 * If moving, choose a direction that is not forbidden, and update direction/forbiddenDir
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
//				if(forbiddenDir == null) {
//					dir = tryDirection;
//					break;
//				} else {
//					if(!forbiddenDir.contains(tryDirection)) {
//						dir = tryDirection;
//						break;
//					}
//				}
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
	 * Only accepts fights if his energy is >= start_energy / 4.
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
	 * Prints out the total number of Critter3s, and gives a stat on how many times Critter3 has reproduced.
	 * @param critters List of Critter3's
	 */
	public static void runStats(java.util.List<Critter> critter3s) {
		System.out.print("" + critter3s.size() + " total Critter3s, and ");
		System.out.println(numBabies + " babies have been made");
	}
	
	/**
	 * Returns String "3" as specified in documentation
	 */
	public String toString() {
		return "3";
	}
	
}