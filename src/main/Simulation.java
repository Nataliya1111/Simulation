package main;

public class Simulation {
	
	public static final String ANSI_RESET = "\033[0m"; 
	public static final String RED = "\033[0;31m"; 
	public static final String ANSI_BLACK_BACKGROUND = "\033[40m"; 
	public static final String ANSI_RED_BACKGROUND = "\033[41m";   
	public static final String ANSI_GREEN_BACKGROUND = "\033[42m"; 
	public static final String ANSI_YELLOW_BACKGROUND = "\033[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\033[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\033[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\033[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\033[47m";
	
	private WorldMap worldMap = new WorldMap();
	//private WorldMap map1 = new WorldMap(20,30);
	//static Coordinates coord = new Coordinates(5,5);

	private Renderer renderer = new Renderer(worldMap);
	
	
	public void start() {
		worldMap.setEntity(new Coordinates(5,5),ANSI_GREEN_BACKGROUND + "🐰" +  ANSI_RESET);
		worldMap.setEntity(new Coordinates(8,3),ANSI_YELLOW_BACKGROUND + "🐰" +  ANSI_RESET);
		worldMap.setEntity(new Coordinates(3,2),ANSI_YELLOW_BACKGROUND + "🐺" +  ANSI_RESET);
		worldMap.setEntity(new Coordinates(1,2),ANSI_YELLOW_BACKGROUND + "🐺" +  ANSI_RESET);
		worldMap.setEntity(new Coordinates(1,1),ANSI_GREEN_BACKGROUND + "🐺" +  ANSI_RESET);
		worldMap.setEntity(new Coordinates(2,1),ANSI_RED_BACKGROUND + "🐺" +  ANSI_RESET);
		worldMap.setEntity(new Coordinates(4,5),ANSI_RED_BACKGROUND + "🐰" +  ANSI_RESET);
		worldMap.setEntity(new Coordinates(2,2),"🥕");
		worldMap.setEntity(new Coordinates(0,2),"🥕");
		worldMap.setEntity(new Coordinates(8,8),"🥬");
		worldMap.setEntity(new Coordinates(4,4),"🥬");
		worldMap.setEntity(new Coordinates(0,9),"🟦");


		renderer.render();
	}	
	

}
