package main;

import java.io.UnsupportedEncodingException;

public class Main {
	public static void main(String[] args) throws UnsupportedEncodingException {
		System.out.println("♟︎  🧟   \u2654");
				
		printTenTimes("🦁");    //1F981	= \uD83E\uDD81
		printTenTimes("🐰");    //1F430 = \uD83D\uDC30
		printTenTimes("🦓");    //\u1F993 = \uD83E\uDD93
		printTenTimes("  ");   
		printTenTimes("🐺");    //1F43A	= \uD83D\uDC3A
		printTenTimes("🥕");    //1F955	= \uD83E\uDD55
		printTenTimes("..");   
		printTenTimes("🥬");    //1F96C	= \uD83E\uDD6C
		printTenTimes("🪨");    //1FAA8	= \uD83E\uDEA8
		printTenTimes("🌲");    //1F332	= \uD83C\uDF32

		Simulation simulation = new Simulation();
		simulation.start();
		
	}

	public static void printTenTimes(String str) {
		for (int i = 0; i < 10; i++) {
			System.out.print(str);		
		}
		System.out.print("\n");
	}
		
	
	

}
