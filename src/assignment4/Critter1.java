package assignment4;
/* CRITTERS Main.java
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

public class Critter1 extends Critter{

	private int flee;
	private int dir;
	private boolean flip;
	private boolean zealous;
	private static int fightsPicked = 0;
	
	public Critter1() {
		flee = Critter.getRandomInt(8);
		dir = Critter.getRandomInt(8);
		if(Critter.getRandomInt(2) == 1) {
			flip = true;
			zealous = false;
		} else {
			flip = false;
			zealous = true;
		}
	}
	
	@Override
	public void doTimeStep() {
		walk(dir);
		
		if(flip) {
			dir = (dir + 2) % 8;
		} else {
			dir = (dir + 5) % 8;
		}
		
		flip = !flip;
		
		if(dir % 2 == 0) {
			zealous = true; //only likes to reproduce when its about to walk in cardinal directions
		}
	}

	@Override
	public boolean fight(String opponent) {
		if (getEnergy() > 25) {	
			if(zealous) {
				Critter1 child = new Critter1();
				reproduce(child, (dir + 4) % 8); //gets a little frisky when frightened, kid goes in opposite direction
			}
			fightsPicked++;
			return true;
		}
		run(flee);
		return false;
	}
	
	public static void runStats(java.util.List<Critter> critters) {
		System.out.print("" + critters.size() + " total Critter1s, and ");
		System.out.println("" + fightsPicked + " fights have been picked");
	}
	
	public String toString() {
		return "1";
	}
	
}
