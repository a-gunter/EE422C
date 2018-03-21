package assignment4;
/* CRITTERS Critter.java
 * EE422C Project 4 submission by
 * Replace <...> with your actual data.
 * <Student1 Name>
 * <Student1 EID>
 * <Student1 5-digit Unique No.>
 * <Student2 Name>
 * <Student2 EID>
 * <Student2 5-digit Unique No.>
 * Slip days used: <0>
 * Fall 2016
 */


import java.util.List;

/* see the PDF for descriptions of the methods and fields in this class
 * you may add fields, methods or inner classes to Critter ONLY if you make your additions private
 * no new public, protected or default-package code or data can be added to Critter
 */


public abstract class Critter {
	private static String myPackage;
	private	static List<Critter> population = new java.util.ArrayList<Critter>();
	private static List<Critter> babies = new java.util.ArrayList<Critter>();

	// Gets the package name.  This assumes that Critter and its subclasses are all in the same package.
	static {
		myPackage = Critter.class.getPackage().toString().split(" ")[1];
	}
	
	
	//Random functionality
	private static java.util.Random rand = new java.util.Random();
	public static int getRandomInt(int max) {
		return rand.nextInt(max);
	}
	
	public static void setSeed(long new_seed) {
		rand = new java.util.Random(new_seed);
	}
	
	
	/* a one-character long string that visually depicts your critter in the ASCII interface */
	public String toString() { return ""; }
	
	private int energy = 0;
	protected int getEnergy() { return energy; }
	
	private int x_coord;
	private int y_coord;
	
	protected final void walk(int direction) {
		move(direction);
		this.energy -= Params.walk_energy_cost;
	}
	
	protected final void run(int direction) {
//		move(direction);
//		move(direction);
//		this.energy -= Params.run_energy_cost;
	}
	
	private void move(int direction) {
		switch(direction) {
			case 0:
				moveRight();
				break;
			case 1:
				moveRight();
				moveUp();
				break;
			case 2:
				moveUp();
				break;
			case 3:
				moveUp();
				moveLeft();
				break;
			case 4:
				moveLeft();
				break;
			case 5:
				moveLeft();
				moveDown();
				break;
			case 6:
				moveDown();
				break;
			case 7:
				moveDown();
				moveRight();
				break;
			default:
		}
	}
	
	private void moveRight() {
		if(this.x_coord >= Params.world_width - 1)
			x_coord = 0;
		else
			x_coord++;
	}
	private void moveLeft() {
		if(this.x_coord <= 0)
			x_coord = Params.world_width - 1;
		else
			x_coord--;
	}
	private void moveUp() {
		if(this.y_coord <= 0)
			y_coord = Params.world_height - 1;
		else
			y_coord--;
	}
	private void moveDown() {
		if(this.y_coord >= Params.world_height - 1)
			y_coord = 0;
		else
			y_coord++;
	}
	
	protected final void reproduce(Critter offspring, int direction) {
	}

	public abstract void doTimeStep();
	public abstract boolean fight(String oponent);
	
	/**
	 * create and initialize a Critter subclass.
	 * critter_class_name must be the unqualified name of a concrete subclass of Critter, if not,
	 * an InvalidCritterException must be thrown.
	 * (Java weirdness: Exception throwing does not work properly if the parameter has lower-case instead of
	 * upper. For example, if craig is supplied instead of Craig, an error is thrown instead of
	 * an Exception.)
	 * @param critter_class_name
	 * @throws InvalidCritterException
	 */
	public static void makeCritter(String critter_class_name) throws InvalidCritterException {
		try {
			//make critter
			Class c = Class.forName(myPackage + "." + critter_class_name); //must be assignment4.Class
			Critter crit = (Critter)c.newInstance();
			//initialize stats
			crit.x_coord = getRandomInt(Params.world_width);
			crit.y_coord = getRandomInt(Params.world_height);
			crit.energy = Params.start_energy;
			//add to pop
			population.add(crit); // I think it should be this not babies from the documentation
		} catch (Exception e) {
			System.out.println(e);
			throw new InvalidCritterException(critter_class_name);
		}
	}
	
	
	/**
	 * Gets a list of critters of a specific type.
	 * @param critter_class_name What kind of Critter is to be listed.  Unqualified class name.
	 * @return List of Critters.
	 * @throws InvalidCritterException
	 */
	public static List<Critter> getInstances(String critter_class_name) throws InvalidCritterException {
		List<Critter> result = new java.util.ArrayList<Critter>();
		for(Critter c: population) {
			try {
				if(c.getClass() == Class.forName(critter_class_name)) {
					result.add(c);
				}
			} catch (Exception e){
				throw new InvalidCritterException(critter_class_name);
			}
		}
		return result;
	}
	
	
	/**
	 * Prints out how many Critters of each type there are on the board.
	 * @param critters List of Critters.
	 */
	public static void runStats(List<Critter> critters) {
		System.out.print("" + critters.size() + " critters as follows -- ");
		java.util.Map<String, Integer> critter_count = new java.util.HashMap<String, Integer>();
		for (Critter crit : critters) {
			String crit_string = crit.toString();
			Integer old_count = critter_count.get(crit_string);
			if (old_count == null) {
				critter_count.put(crit_string,  1);
			} else {
				critter_count.put(crit_string, old_count.intValue() + 1);
			}
		}
		String prefix = "";
		for (String s : critter_count.keySet()) {
			System.out.print(prefix + s + ":" + critter_count.get(s));
			prefix = ", ";
		}
		System.out.println();		
	}
	
	
	/* the TestCritter class allows some critters to "cheat". If you want to 
	 * create tests of your Critter model, you can create subclasses of this class
	 * and then use the setter functions contained here. 
	 * 
	 * NOTE: you must make sure that the setter functions work with your implementation
	 * of Critter. That means, if you're recording the positions of your critters
	 * using some sort of external grid or some other data structure in addition
	 * to the x_coord and y_coord functions, then you MUST update these setter functions
	 * so that they correctly update your grid/data structure.
	 */
	static abstract class TestCritter extends Critter {
		protected void setEnergy(int new_energy_value) {
			super.energy = new_energy_value;
		}
		
		protected void setX_coord(int new_x_coord) {
			super.x_coord = new_x_coord;
		}
		
		protected void setY_coord(int new_y_coord) {
			super.y_coord = new_y_coord;
		}
		
		protected int getX_coord() {
			return super.x_coord;
		}
		
		protected int getY_coord() {
			return super.y_coord;
		}
		

		/*
		 * This method getPopulation has to be modified by you if you are not using the population
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.
		 */
		protected static List<Critter> getPopulation() {
			return population;
		}
		
		/*
		 * This method getBabies has to be modified by you if you are not using the babies
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.  Babies should be added to the general population 
		 * at either the beginning OR the end of every timestep.
		 */
		protected static List<Critter> getBabies() {
			return babies;
		}
	}

	/**
	 * Clear the world of all critters, dead and alive
	 */
	public static void clearWorld() {
		//little unclear on what exactly this should do but..
		population.clear();
		babies.clear();
	}
	
	/**
	 * Performs timestep, updating world
	 */
	public static void worldTimeStep() {
		// doTimeSteps for each critter
		for(Critter c: population) {
			c.doTimeStep();
		}
		// do fights ie encounters
		
		// generate algae genAlgae()
		
		// move babies to general population
		
		//rest energy stuff (maybe?)
		
		//remove dead critters
		for(Critter c: population) {
			if(c.energy <= 0) population.remove(c);
		}
	}
	
	/**
	 * Executed with show command, displays world and all critters
	 */
	public static void displayWorld() {
		String[][] grid = new String[Params.world_width + 2][Params.world_height + 2];
		
		//set corners
		grid[0][0] = "+";
		grid[0][Params.world_height + 1] = "+";
		grid[Params.world_width + 1][0] = "+";
		grid[Params.world_width + 1][Params.world_height + 1] = "+";
		
		//set border
		for(int col = 1;col < Params.world_width + 1;col++) grid[col][0] = "-";
		for(int col = 1;col < Params.world_width + 1;col++) grid[col][Params.world_height + 1] = "-";
		for(int row = 1;row < Params.world_height + 1;row++) grid[0][row] = "|";
		for(int row = 1;row < Params.world_height + 1;row++) grid[Params.world_width + 1][row] = "|";
		
		//put critters in
		for(Critter c: population) {
			try {
				grid[c.x_coord + 1][c.y_coord + 1] = c.toString(); //compensate for border
			} catch(Exception e) {
				System.out.println(c.x_coord + " " + c.y_coord);
				System.out.println(e);
			}
		}
		
		for(int row = 0;row < Params.world_height + 2;row++) {
			for(int col = 0;col < Params.world_width + 2;col++) {
				if(grid[col][row] == null) System.out.print( " ");
				else System.out.print(grid[col][row]);
			}
			System.out.println();
		}
		
	}
}
