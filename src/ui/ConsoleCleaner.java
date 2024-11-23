package ui;

public class ConsoleCleaner {
	private static int cleaningMode = 0; //not clean
//	private final static int NOT_CLEANING_MODE = 0;
	
	public static int getCleaningMode() {
		return cleaningMode;
	}

	public static void setCleaningMode(int cleaningMode) {
		if (cleaningMode >= 0 && cleaningMode <= 4) {
			ConsoleCleaner.cleaningMode = cleaningMode;
			return;
		}
		System.out.println("Invalid cleaning mode");
    	return;
		
	}
	
	public static void clean() {
		switch(cleaningMode) {
		    case 0:
		    	return;
		    case 1:
		    	cleanEscapeCode1();
		    	return;
		    case 2:
		    	cleanEscapeCode2();
		    	return;
		    case 3:
		    	cleanforWindows();
		    	return;
		    case 4:
		    	cleanForLinuxMac();
		    	return;
		    default:
		    	System.out.println("Invalid cleaning mode");
		    	return;
		}		
	}	
	
	private static void cleanEscapeCode1() {
		System.out.print("\033[H\033[2J"); 
		System.out.flush();
	}

	private static void cleanEscapeCode2() {
		System.out.print("\033\143"); 
	}
	
	private static void cleanforWindows() {
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
