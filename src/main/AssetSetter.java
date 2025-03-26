package main;

import object.OBJ_Door;
import object.OBJ_Key;

public class AssetSetter {
	
	GamePanel gp ;
	
	public AssetSetter (GamePanel gp) {
			this.gp = gp;
	}
	public void setObject () {
		
		gp.obj[0] = new OBJ_Key();
		gp.obj[0].x = 1 * gp.tileSize ;
		gp.obj[0].y = 13 * gp.tileSize;
		
		gp.obj[1] = new OBJ_Key();
		gp.obj[1].x = 3 * gp.tileSize;
		gp.obj[1].y = 3 *gp.tileSize;
		
		gp.obj[2] = new OBJ_Key();
		gp.obj[2].x = 7 * gp.tileSize;
		gp.obj[2].y = 13 * gp.tileSize;
		
		gp.obj[3] = new OBJ_Key();
		gp.obj[3].x = 9 * gp.tileSize;
		gp.obj[3].y = 9 * gp.tileSize;
		
		gp.obj[4] = new OBJ_Key();
		gp.obj[4].x = 10 * gp.tileSize;
		gp.obj[4].y = 4 * gp.tileSize;
		
		gp.obj[5] = new OBJ_Door();
		gp.obj[5].x = 15 * gp.tileSize ;
		gp.obj[5].y = 14 * gp.tileSize;
		
		
	}

}
