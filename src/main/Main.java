package main;

import ui.DisplayInfo;
import ui.UserCommandsExecutor;

public class Main {
	
	public static void main(String[] args) {
		
		DisplayInfo.printStartMessage();	
	
		Simulation simulation = new Simulation();
		
		UserCommandsExecutor commandsExecutor = new UserCommandsExecutor(simulation);
		commandsExecutor.waitBeginCommand();
		commandsExecutor.launchInBackground();		
		simulation.start();
		DisplayInfo.printFinishMessage();			
	}

}
