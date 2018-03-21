package assignment4;
/* CRITTERS Main.java
 * EE422C Project 4 submission by
 * <Matthew Davis>
 * <mqd224>
 * <15510>
 * <Austin Gunter>
 * <Student2 EID> fill this 
 * <15510>
 * Spring 2018
 */

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;


/*
 * Usage: java <pkgname>.Main <input file> test
 * input file is optional.  If input file is specified, the word 'test' is optional.
 * May not use 'test' argument without specifying input file.
 */
public class Main {

    static Scanner kb;	// scanner connected to keyboard input, or input file
    private static String inputFile;	// input file, used instead of keyboard input if specified
    static ByteArrayOutputStream testOutputString;	// if test specified, holds all console output
    private static String myPackage;	// package of Critter file.  Critter cannot be in default pkg.
    private static boolean DEBUG = false; // Use it or not, as you wish!
    static PrintStream old = System.out;	// if you want to restore output to console


    // Gets the package name.  The usage assumes that Critter and its subclasses are all in the same package.
    static {
        myPackage = Critter.class.getPackage().toString().split(" ")[1];
    }

    /**
     * Main method.
     * @param args args can be empty.  If not empty, provide two parameters -- the first is a file name, 
     * and the second is test (for test output, where all output to be directed to a String), or nothing.
     */
    public static void main(String[] args) { 
        if (args.length != 0) {
            try {
                inputFile = args[0];
                kb = new Scanner(new File(inputFile));			
            } catch (FileNotFoundException e) {
                System.out.println("USAGE: java Main OR java Main <input file> <test output>");
                e.printStackTrace();
            } catch (NullPointerException e) {
                System.out.println("USAGE: java Main OR java Main <input file>  <test output>");
            }
            if (args.length >= 2) {
                if (args[1].equals("test")) { // if the word "test" is the second argument to java
                    // Create a stream to hold the output
                    testOutputString = new ByteArrayOutputStream();
                    PrintStream ps = new PrintStream(testOutputString);
                    // Save the old System.out.
                    old = System.out;
                    // Tell Java to use the special stream; all console output will be redirected here from now
                    System.setOut(ps);
                }
            }
        } else { // if no arguments to main
            kb = new Scanner(System.in); // use keyboard and console
        }

        /* Do not alter the code above for your submission. */
        /* Write your code below. */
        mainLoop(parseInput(kb));
        
        /* Write your code above */
        System.out.flush();

    }
    
    private static ArrayList<String> parseInput(Scanner kb){
    	//prompt user
    	System.out.println("Commands: quit, show, step [count], seed <num>, make <critter> [count], stats <critter>");
        System.out.print("Input command: ");
        
        String in = kb.nextLine();
        String[] ins = in.split(" ");
        ArrayList<String> ret = new ArrayList<String>(Arrays.asList(ins));
        return ret;
    }
    
    private static void mainLoop(ArrayList<String> input) {
    	//go until quit is typed
    	while(!input.get(0).equals("quit")) {	
        	switch(input.get(0)) {
        		case "make":
        			if(input.size() == 2) {
        				try {
        					Critter.makeCritter(input.get(1));
        				} catch (InvalidCritterException e) {
        					System.out.println(e);
        				}
        			} else if(input.size() == 3) {
        				try {
        					for(int i = 0;i < Integer.parseInt(input.get(2));i++)
        						Critter.makeCritter(input.get(1));
        				} catch (InvalidCritterException e) {
        					System.out.println(e);
        				}
        			}
        			break;
        		case "show":
        			Critter.displayWorld();
        			break;
        		case "step":
        			if(input.size() == 1) Critter.worldTimeStep();
        			else if(input.size() == 2) {
        				try {
        					for(int i = 0;i < Integer.parseInt(input.get(1));i++) {
        						Critter.worldTimeStep();
        					}
        				} catch (Exception e) {
        					System.out.println(e);
        				}
        			}
        			break;
        		case "seed":
        			if(input.size() == 2) {
        				try {
        					Critter.setSeed(Integer.parseInt(input.get(1)));
        				} catch(Exception e) {
        					System.out.println(e);
        				}
        			}
        			break;
        		case "stats":
        			//stage 3
        			System.out.println("Stats");
        			break;
        	}
        	//get next input
        	input = parseInput(kb);
        }
    }
}

