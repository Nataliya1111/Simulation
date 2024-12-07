package ui;

import java.util.Scanner;

import main.Simulation;
import ui.ConsoleCleaner.CleaningMode;

public class UserCommandsExecutor{	
	
	private Simulation simulation;	
	
	private enum UserCommand {
		BEGIN("B"),
		SET_MAP_SIZE("S"),
		PAUSE("P"), 
		CONTINUE("C"),
		SETTINGS("S"),
		QUIT("Q");
		
		private String key;
		
		UserCommand(String key){
			this.key = key;
		}
		public String getKey() {
			return key;
		}
	}
	
	private enum SettingsCommand {
		MENU("M"),
		CLEANING_SCREEN_MODE("C"),
		DISPLAYING_FREQUENCY("F");
		
		private final String key;
		
		SettingsCommand(String key){
			this.key = key;
		}
		public String getKey() {
			return key;
		}
	}
	
	private enum DisplayingFrequency {
		SLOW("1", 2000),
		NORMAL("2", 1000),
		FAST("3", 500), 
		VERY_FAST("4", 200);
		
		private final String key;
		private final int sleepDuration;
		
		DisplayingFrequency(String key, int speed){
			this.key = key;
			this.sleepDuration = speed;
		}
		public String getKey() {
			return key;
		}
		public int getSleepDuration() {
			return sleepDuration;
		}
	}
	
	private static final Scanner scanner = new Scanner(System.in);
	private UserCommand currentCommand;
	
	
	public UserCommandsExecutor(Simulation simulation) {
		this.simulation = simulation;
	}	
	
	public void waitBeginCommand() {
		String input = "";
		while(true) {
			input = scanner.nextLine();
			if(input.equalsIgnoreCase(UserCommand.BEGIN.getKey())) {
				return;
			}
			if(input.equalsIgnoreCase(UserCommand.SET_MAP_SIZE.getKey())) {
				setWorldmap();				
				return;
			}
		}		
	}
	
	public void launchInBackground() {		
		
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {	
				executeUserCommands();								
			}			
		});
		
		thread.setDaemon(true);
		thread.start();
	}
	
	private void setWorldmap() {
		String input = "";
		int width;
		int height;
		int minWidth = 10;
		int maxWidth = 30;
		int minHeight = 10;
		int maxHeight = 30;
		System.out.printf("Enter width of Map (from %d to %d)\n", minWidth, maxWidth);
		while(true) {
			input = scanner.nextLine();
			try {
				width = Integer.parseInt(input);
				if (width >= minWidth && width <= maxWidth) {
					break;
				}
				System.out.printf("Enter number from %d to %d\n", minWidth, maxWidth);
			} catch (NumberFormatException e) {
				System.out.println("Invalid input");
			}
		}
		System.out.printf("Enter height of Map (from %d to %d)\n", minHeight, maxHeight);
		while(true) {
			input = scanner.nextLine();
			try {
				height = Integer.parseInt(input);
				if (height >= minHeight && height <= maxHeight) {
					break;							
				}
				System.out.printf("Enter number from %d to %d\n", minHeight, maxHeight);
			} catch (NumberFormatException e) {
				System.out.println("Invalid input");
			}					
		}				
		simulation.setWorldMap(width, height);
	}
	
	private void executeUserCommands() {
		while (true) {					
			currentCommand = getCommandByUserInput();
			switch(currentCommand) {
			    case PAUSE:
			    	simulation.setPaused();
			    	DisplayInfo.printPauseMessage();
			    	DisplayInfo.printMainMenu();
			    	break;
			    case CONTINUE:
			    	simulation.setContinued();
			    	break;
			    case SETTINGS:
			    	simulation.setPaused();
			    	DisplayInfo.printSettingsMenu();
			    	SettingsCommand settingsCommand = getSettingsCommandByUserInput();
			    	switch(settingsCommand) {
					    case MENU:
					    	DisplayInfo.printMainMenu();
					    	break;
					    case CLEANING_SCREEN_MODE:
					    	DisplayInfo.printCleaningModeMenu();
					    	changeCleaningMode();	
					    	break;
					    case DISPLAYING_FREQUENCY:
					    	DisplayInfo.printDisplayingFrequencyMenu();
					    	changeDisplayingFrequency();
					    	break;
					    default:
							break;
			    	}
			    	break;
			    case QUIT:
			    	simulation.setFinished();
			    	DisplayInfo.printQuitMessage();
				    break;
				default:
					break;					
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private UserCommand getCommandByUserInput(){
		
		while(true) {
			String userInput = scanner.nextLine();
			if (userInput.equalsIgnoreCase(UserCommand.PAUSE.getKey())) {
				return UserCommand.PAUSE;
			}
			if (userInput.equalsIgnoreCase(UserCommand.CONTINUE.getKey())) {
				return UserCommand.CONTINUE;
			}
			if (userInput.equalsIgnoreCase(UserCommand.SETTINGS.getKey())) {
				return UserCommand.SETTINGS;
			}
			if (userInput.equalsIgnoreCase(UserCommand.QUIT.getKey())) {
				return UserCommand.QUIT;
			}			    	
			
		}		
	}
	
	private SettingsCommand getSettingsCommandByUserInput(){
		
		while(true) {
			String userInput = scanner.nextLine();
			if (userInput.equalsIgnoreCase(SettingsCommand.MENU.getKey())) {
				return SettingsCommand.MENU;
			}			
			if (userInput.equalsIgnoreCase(SettingsCommand.CLEANING_SCREEN_MODE.getKey())) {
				return SettingsCommand.CLEANING_SCREEN_MODE;
			}
			if (userInput.equalsIgnoreCase(SettingsCommand.DISPLAYING_FREQUENCY.getKey())) {
				return SettingsCommand.DISPLAYING_FREQUENCY;
			}
			System.out.println("Invalid command imput");
			DisplayInfo.printSettingsMenu();
			
		}		
	}
	
	private CleaningMode getCleaningModeByUserInput(){
		
		while(true) {
			String userInput = scanner.nextLine();
			if (userInput.equalsIgnoreCase(CleaningMode.ZERO.getNumber())) {
				return CleaningMode.ZERO;
			}
			if (userInput.equalsIgnoreCase(CleaningMode.ONE.getNumber())) {
				return CleaningMode.ONE;
			}
			if (userInput.equalsIgnoreCase(CleaningMode.TWO.getNumber())) {
				return CleaningMode.TWO;
			}
			if (userInput.equalsIgnoreCase(CleaningMode.THREE.getNumber())) {
				return CleaningMode.THREE;
			}
			if (userInput.equalsIgnoreCase(CleaningMode.FOUR.getNumber())) {
				return CleaningMode.FOUR;
			}			
			System.out.println("Invalid cleaning mode");
			
		}		
	}
	
	private void changeCleaningMode() {		
    	CleaningMode cleaningMode = getCleaningModeByUserInput();
    	if (cleaningMode.equals(ConsoleCleaner.getCleaningMode())) {
    		System.out.println("Cleaning Mode hasn't been changed");
    	}
    	else {
    		switch(cleaningMode) {
			    case ZERO:
			    	ConsoleCleaner.setCleaningMode(CleaningMode.ZERO);
			    	break;
			    case ONE:
			    	ConsoleCleaner.setCleaningMode(CleaningMode.ONE);
			    	break;
			    case TWO:
			    	ConsoleCleaner.setCleaningMode(CleaningMode.TWO);
			    	break;
			    case THREE:
			    	ConsoleCleaner.setCleaningMode(CleaningMode.THREE);
			    	break;
			    case FOUR:
			    	ConsoleCleaner.setCleaningMode(CleaningMode.FOUR);
			    	break;
    		}    
    		System.out.println("Cleaning Mode has been changed");							    		
    	}
    	
    	DisplayInfo.printMainMenu();
	}
	
	private void changeDisplayingFrequency() {
		String input = "";
		while(true) {
			input = scanner.nextLine();
			
			if (input.equals(DisplayingFrequency.SLOW.getKey())) {
				simulation.setSleepDuration(DisplayingFrequency.SLOW.getSleepDuration());
				break;
			}
			if (input.equals(DisplayingFrequency.NORMAL.getKey())) {
				simulation.setSleepDuration(DisplayingFrequency.NORMAL.getSleepDuration());
				break;
			}
			if (input.equals(DisplayingFrequency.FAST.getKey())) {
				simulation.setSleepDuration(DisplayingFrequency.FAST.getSleepDuration());
				break;
			}
			if (input.equals(DisplayingFrequency.VERY_FAST.getKey())) {
				simulation.setSleepDuration(DisplayingFrequency.VERY_FAST.getSleepDuration());
				break;
			}				
			System.out.println("Invalid input");						
		}
		System.out.println("Displaying Frequency has been changed");	
		DisplayInfo.printMainMenu();
	}
	
	
}
