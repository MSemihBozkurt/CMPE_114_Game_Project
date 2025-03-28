package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import object.SuperObject;
import entity.Player;
import tile.TileManager;



public class GamePanel extends JPanel implements Runnable {
	
	final int originalTileSize= 16;
	final int scale = 3;
	
	public final int tileSize =originalTileSize*scale;
    public final int maxScreenCol= 16;
    public final int maxScreenRow= 16;
    public final int screenWidth = tileSize*maxScreenCol;
    public final int screenHeight = tileSize*maxScreenRow;
    
    int FPS = 60;
    
  
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public CollisionChecker cChecker= new CollisionChecker(this);
    public AssetSetter aSetter  = new AssetSetter(this);
    public UI ui = new UI(this);
    
    Player player = new Player (this, keyH);
    public SuperObject obj[] = new SuperObject[10];
    
    
   
    
    public GamePanel() {
    	
    	this.setPreferredSize(new Dimension (screenHeight ,screenWidth));
    	this.setBackground(Color.black);
    	this.setDoubleBuffered(true);
    	this.addKeyListener(keyH);
    	this.setFocusable(true);
    	
    }
    
    public void setUpGame() {
    	aSetter.setObject();
    }
    
    
    
    public void startGameThread() {
    	gameThread = new Thread(this);
    	gameThread.start();
    	
    	
    }

	public void run() {
		
		double drawInterval = 1000000000/FPS;
		double nextDrawTime=System.nanoTime()+ drawInterval;
		while (gameThread!= null) {
			update();
			repaint();
			
			
			
			try { 
				
				double remainingTime= nextDrawTime -System.nanoTime();
				remainingTime= remainingTime/1000000;
				
				if ( remainingTime<0) {
					remainingTime=0;
					
				}
				Thread.sleep((long) remainingTime);
				
				nextDrawTime+= drawInterval;
				
			} 
			catch (InterruptedException e) {
				
				
				e.printStackTrace();
			}
		}
		
	}
	public void update () {
		
		player.update();
		
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		// Tile
		tileM.draw(g2);
		
	    // Object
		for(int i =0; i<obj.length; i++) {
			if(obj[i]!=null) {
				obj[i].draw(g2, this);
			}
		}
		
		
		
		// Player
		player.draw(g2);
		
		// ui
		ui.draw(g2);
		
		
		g2.dispose();
		}
	}
