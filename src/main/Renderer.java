package main;

public class Renderer {	
	
	private final WorldMap worldMap;
	
	public Renderer(WorldMap worldMap){
		this.worldMap = worldMap;
	}

	
	public void render() {
		final int heightOfMap = worldMap.getHeight();
		final int weightOfMap = worldMap.getWidth();
		
		
		for(int y = 0; y < heightOfMap; y++) {
			String mapLine = "";
			for (int x = 0; x < weightOfMap; x ++) {
				Coordinates coordinates = new Coordinates(x, y);
				if (worldMap.isCellEmty(coordinates)){ 
	//				mapLine += ANSI_CYAN_BACKGROUND + "  " + ANSI_RESET;
					mapLine += "🏻";
				}	
				else {
	//				mapLine += ANSI_GREEN_BACKGROUND + this.worldMap.getEntity(coordinates) + ANSI_RESET;
					mapLine += this.worldMap.getEntity(coordinates);
				}
				
			}
			System.out.println(mapLine);
		}
		
	}

}
