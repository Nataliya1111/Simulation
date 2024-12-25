package ui;

public class ConsoleCleaner {
	
	public enum CleaningMode{
		ZERO("0"),
		ONE("1"), 
		TWO("2"),
		THREE("3"),
		FOUR("4");
		
		private final String mode;
		
		CleaningMode(String mode){
			this.mode = mode;
		}
		public String getNumber() {
			return mode;
		}
	}
	
	private static final CleaningMode DEFAULT_CLEANING_MODE = CleaningMode.ONE; 
	private static CleaningMode cleaningMode = DEFAULT_CLEANING_MODE; 
	
	public static CleaningMode getCleaningMode() {
		return cleaningMode;
	}

	public static void setCleaningMode(CleaningMode cleaningMode) {
		ConsoleCleaner.cleaningMode = cleaningMode;
	}
	
	public static void clean() {
		switch(cleaningMode) {
		    case ZERO:       //not clean
		    	return;
		    case ONE:
		    	cleanEscapeCode1();
		    	return;
		    case TWO:
		    	cleanEscapeCode2();
		    	return;
		    case THREE:
		    	cleanForWindows();
		    	return;
		    case FOUR:
		    	cleanForLinuxMac();
		}
	}	
	
	private static void cleanEscapeCode1() {
		System.out.print("\033[H\033[2J"); 
		System.out.flush();
	}

	private static void cleanEscapeCode2() {
		System.out.print("\033\143"); 
	}
	
	private static void cleanForWindows() {
	    try {
	        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	    } catch (Exception E) {
	        System.out.println(E);
	    }
	}

	private static void cleanForLinuxMac() {
	    try {
	    	new ProcessBuilder("/bin/bash", "-c", "clear").inheritIO().start().waitFor();
	    } catch (Exception E) {
	        System.out.println(E);
	    }
	}

}
