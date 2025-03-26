package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {
	
	GamePanel gp;
	KeyHandler keyH;
	public int hasKey = 0;
	
	public Player (GamePanel gp , KeyHandler keyH) {
		
		this.gp=gp;
		this.keyH=keyH;
		
		solidArea= new Rectangle ();
		solidArea.x=8;
		solidArea.y=16;
		solidAreaDefaultX= solidArea.x;
		solidAreaDefaultY=solidArea.y;
		solidArea.width=32;
		solidArea.height=32;
		
		setDefaultValues();
		getPlayerImage();
		
		
	}
	
	public void setDefaultValues() {
		
		x=48;
		y=96;
		speed=4;
		direction = ("down");
		
	}
	public void getPlayerImage () {
		
		try {
			up = ImageIO.read(getClass().getResourceAsStream("/player/up.png"));
			down = ImageIO.read(getClass().getResourceAsStream("/player/down.png"));
			right = ImageIO.read(getClass().getResourceAsStream("/player/right.png"));
			left = ImageIO.read(getClass().getResourceAsStream("/player/left.png"));
			
		
			
		
		
		
		}catch(IOException e) 
		
		{
			e.printStackTrace();
		}
	}
		
	
	
	public void update () {
	    if (keyH.upPressed== true) {
			
			direction=("up");
			
			
			
		}
		else if (keyH.downPressed== true) {
			
			direction=("down");
			
			
		}
		else if (keyH.leftPressed== true) {
			
			direction=("left");
			
		
		}
		else if (keyH.rightPressed== true) {
		   
			direction=("right");
            			
			 }
		
		// Check Tile Collision 
		
		collisionOn = false;
		gp.cChecker.checkTile(this);
		int objIndex = gp.cChecker.checkObject(this, true);
		pickUpObject(objIndex);
		
		// If collision is false, player can move
		if(collisionOn == false) {
			switch (direction) {
			case "up":
				if (keyH.upPressed== true)
				y -= speed;
				break;
			case "down":
				if (keyH.downPressed== true)
				y += speed;
			  break;
			case "left":
				if (keyH.leftPressed== true)
				x -= speed;
		      break;
			case "right":
				if (keyH.rightPressed== true)
				x += speed;
			  break;
			}
		}

		
	}
	
	public void pickUpObject (int i) {
		if (i != 999) {
			String objectName = gp.obj[i].name;
			
			switch (objectName) {
			case "Key" :
				hasKey++;
				gp.obj[i]=null;
				System.out.println("Key: " +hasKey);
				break;
				
			case "Door":
				if (hasKey >0) {
					
					gp.obj[i]=null;
					hasKey--;
					gp.ui.gameFinished = true;
				}
				System.out.println("Key: " +hasKey);
				
				
				break;
			}
		}
		
		
		
	}
	public void draw (Graphics2D g2) {
		
		BufferedImage image=null;
		 
		  switch(direction) {
		  
		  case "up":
			  image=up;
			  break;
		  case"down":
			  image=down;
			  break;
		  case"left":
			  image=left;
			  break;
		  case"right":
			  image=right;
		  }
		  g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
			  
		}
		}
