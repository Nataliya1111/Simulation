package ui;

import java.util.Scanner;

import main.Simulation;

public class UserCommandsExecutor{
	
	private Simulation simulation;	
	
	private enum UserCommand {
		PAUSE("P"), 
		CONTINUE("C"),
		QUIT("Q");
		
		private String key;
		
		UserCommand(String key){
			this.key = key;
		}

		public String getKey() {
			return key;
		}
	}
	
	private static final Scanner scanner = new Scanner(System.in);
	private UserCommand currentCommand;
	
	
	public UserCommandsExecutor(Simulation simulation) {
		this.simulation = simulation;
	}	

	
	public void launch() {
		
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {				
				while (true) {					
					currentCommand = getCommandByUserInput();
					switch(currentCommand) {
					    case PAUSE:
					    	System.out.println("PAUSE");
					    	simulation.setPaused();
					    	break;
					    case CONTINUE:
					    	System.out.println("CONTINUE");
					    	simulation.setContinued();
					    	break;
					    case QUIT:
					    	System.out.println("QUIT");
					    	simulation.finish();
						    break;						
				
					}
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}				
			}			
		});
		
		thread.setDaemon(true);
		thread.start();
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
			if (userInput.equalsIgnoreCase(UserCommand.QUIT.getKey())) {
				return UserCommand.QUIT;
			}
		}		
	}
}
