package ui;

import action.turn.CountStatisticsAction;
import main.WorldMap;

public class DisplayInfo {
	
	private static final String ANSI_RESET = "\033[0m"; 
	private static final String ANSI_RED_BACKGROUND = "\033[41m"; 
	private static final String ANSI_GREEN_BACKGROUND = "\033[42m"; 
	private static final String SPRITE_OF_PREDATOR = "🐺";
	
	public static void printStartMessage() {
		System.out.println("\nWELCOME TO SIMULATION\n");
		System.out.println("Coloured sells mean:");
		System.out.print(ANSI_GREEN_BACKGROUND + SPRITE_OF_PREDATOR + ANSI_RESET + " - new Animal,  ");
		System.out.println(ANSI_RED_BACKGROUND + SPRITE_OF_PREDATOR + ANSI_RESET + " - close to death from hunger Animal");
		System.out.printf("\nPress B for the beginning Simulation with default map size (%d x %d)\n", WorldMap.DEFAULT_HEIGHT, WorldMap.DEFAULT_WIDTH);
		System.out.println("Press S to set size of map");
	}
	
	public static void printTurnMessage(){
		System.out.println("Commands (ignore case): P - Pause, Q - Quit, S - Settings\n");
	}
	
	public static void printMainMenu(){
		System.out.println("\nCommands (ignore case): C - Continue, Q - Quit, S - Settings\n");
	}
	
	public static void printPauseMessage(){
		System.out.println("Simulation is PAUSED");
	}
	
	public static void printQuitMessage(){
		System.out.println("You QUIT the Simulation");
	}
	
	public static void printFinishMessage(){
		System.out.println("\nThe Simulation is finished");
	}
	
	public static void printSettingsMenu(){

		System.out.println("\nSETTINGS:"
				+ "\nC - change Cleaning screen mode"
				+ "\nF - change Frequency of map displaying");
		System.out.println("\nPress M for Simulation Menu");
	}
	
	public static void printCleaningModeMenu(){
		System.out.printf("\nChoose Cleaning screen mode. \nCurrent cleaning mode: %d \n", Integer.parseInt(ConsoleCleaner.getCleaningMode().getNumber()));
		System.out.println("Pay attention: It may or may not work on your console, you can try different options. It doesn’t work in IDE consoles\n");
		System.out.println("0 - standard mode without cleaning\n"
				+ "1 - shows map on upper left corner of the console every move, you can scroll up to see other moves\n"
				+ "2 - shows map on upper left corner of the console every move, clears the hole screen above\n"
				+ "3 - shows map on upper left corner of the console every move, clears the hole screen above (only for Windows)\n"
				+ "4 - shows map on upper left corner of the console every move, clears the hole screen above (only for Linux/Mac)\n");
	}
	
	public static void printDisplayingFrequencyMenu(){
		System.out.println("Choose Frequency of map displaying\n"
				+ "1 - slow\n"
				+ "2 - normal(default)\n"
				+ "3 - fast\n"
				+ "4 - very fast\n");
	}

	public static void printStatistics(CountStatisticsAction countStatistics){
		System.out.printf("Moves made: %d\n", countStatistics.getMovesCounter());
		System.out.printf("🥕 eaten: %d;  🐰 eaten: %d; 🐰 dead from hunger: %d; 🐺 dead from hunger: %d\n", 
				countStatistics.getEatenHerbCounter(), countStatistics.getEatenHerbivoresCounter(), 
				countStatistics.getHungerDeadHerbivoresCounter(), countStatistics.getHungerDeadPredatorsCounter());
			
	}
}
