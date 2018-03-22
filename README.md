# EE422C
Software Design II Labs

-----------------------------------------------------
Work Load:

Stage 1 - Matt
Stage 2 - Austin
Stage 3 - Split
Critter1 - Matt
Critter2 - Matt
Critter3 - Austin
Critter4 - Austin

-----------------------------------------------------
Classes:

Critter1:
	i. Fields
		int flee - direction to flee in
		int dir - direction to move during timestep
		bool flip - used to change dir, alternates
		bool zealous - reproduce before fight, alternates
		static int fightsPicked - num times a Critter1 tries to fight
	ii. Methods:
		Contructor
		doTimeStep()
		fight()
		runStats()
		toString()

Critter2:
	i. Fields
		
	
	ii. Methods:
	
Critter3:
	i. Fields
		int dir - direction to move during timestep
		List<Integer> forbiddenDir - directions that Critter3 CANNOT move in
		static int numBabies - number of offspring that all Critter3s have produced
	ii. Methods:
		Constructor
		doTimeStep()
		fight()
		runStats()
		toString()

Critter4:
	i. Fields
		int dir - direction to move during timestep
		static int numFights - number of fights that all Critter4s have accepted
	ii. Methods:
		Constructor
		doTimeStep()
		fight()
		runStats()
		toString()
