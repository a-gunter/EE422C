package assignment4;

/**
 * Only every other iteration can walk, reproduces before it fights itself,
 *  likes to fight but doesn't like Critter3 or Critter4
 * @author Matt
 *
 */
public class Critter2 extends Critter{

	private int dir;
	private int iteration;
	private static int total2 = 0;
	
	public Critter2() {
		dir = Critter.getRandomInt(8);
		total2++;
		iteration = 1;
	}
	
	/**
	 * Only every other generation runs
	 * Has a child if has half of start energy, kid goes 90 degrees counter clockwise
	 */
	@Override
	public void doTimeStep() {
		if(iteration % 2 == 0) walk(dir);
		else run(dir);
		dir = Critter.getRandomInt(8);
		
		if(this.getEnergy() >= Params.start_energy) {
			Critter2 kid = new Critter2();
			kid.iteration = this.iteration + 1;
			reproduce(kid, (dir + 2) % 8);
		}
	}

	/**
	 * If Critter1 then fights, if Critter2 then reproduces and fights,
	 *  if 3 then tries to walk away, if Critter4 then tries to run.
	 *  Default is to fight
	 */
	@Override
	public boolean fight(String opponent) {
		switch(opponent) {
		case "1":
			return true;
		case "2":
			Critter2 kid = new Critter2();
			kid.iteration = this.iteration + 1;
			reproduce(kid, (dir + 2) % 8);
			return true;
		case "3":
			walk(dir);
			return false;
		case "4":
			run((dir + 4) % 8);
			return false;
		default:
			return true;
		}
	}
	
	/**
	 * toString Method
	 */
	public String toString() {
		return "2";
	}
	
	/**
	 * Prints Critters on the screen and total critter2s created
	 * @param critters
	 */
	public static void runStats(java.util.List<Critter> critters) {
		System.out.print("" + critters.size() + " total Critter2s, and ");
		System.out.println("" + total2 + " Critter2s have been birthed in total");
	}
	
}
