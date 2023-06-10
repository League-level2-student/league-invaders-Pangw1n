import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener{
	final int MENU = 0;
    final int GAME = 1;
    final int END = 2;
    int CurrentState = MENU;
    
    Font titleFont;
    Font subtitleFont;
    
    Timer frameDraw;
	Timer alienTimer;
    
    RocketShip rocketShip;
    boolean up;
    boolean down;
    boolean left;
    boolean right;
    
    ObjectManager objectManager;
    
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
    
    public GamePanel()
    {
    	titleFont = new Font("Arial", Font.PLAIN, 48);
    	subtitleFont = new Font("Arial", Font.PLAIN, 24);
    	
    	frameDraw = new Timer(1000/60, this);
    	frameDraw.start();
    	
    	rocketShip = new RocketShip(250, 700, 50, 50);
    	
    	objectManager = new ObjectManager(rocketShip);
    	
    	if (needImage) {
		    loadImage ("space.png");
		}
    }
    
	@Override
	public void paintComponent(Graphics g){
		if (CurrentState == MENU)
		{
		    drawMenuState(g);
		}
		else if(CurrentState == GAME)
		{
		    drawGameState(g);
		}
		else if(CurrentState == END)
		{
		    drawEndState(g);
		}
	}
	
	void updateMenuState()
	{
		
	}
	void updateGameState()
	{
		objectManager.update();
		if (rocketShip.isActive == false)
		{
			CurrentState = END;
		}
	}
	void updateEndState()
	{
		
	}
	
	void drawMenuState(Graphics g)
	{
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);

		g.setColor(Color.WHITE);
		g.setFont(titleFont);
		g.drawString("LEAGUE INVADERS", 22, 100);
		g.setFont(subtitleFont);
		g.drawString("Press ENTER to start", 135, 400);
		g.drawString("Press SPACE for instructions", 100, 500);
		
	}
	void drawGameState(Graphics g)
	{
		if (gotImage) {
			g.drawImage(image, 0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT, null);
		} else {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		}
		
		objectManager.draw(g);
		
		g.setColor(Color.GREEN);
		g.setFont(subtitleFont);
		g.drawString("SCORE: " + objectManager.getScore(), 10, 50);
	}
	void drawEndState(Graphics g)
	{
		g.setColor(Color.RED);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		
		g.setColor(Color.BLACK);
		g.setFont(titleFont);
		g.drawString("GAME OVER", 100, 100);
		g.setFont(subtitleFont);
		g.drawString("You Killed " + objectManager.getScore() + " enemies", 135, 400);
	}
	
	void moveRocketShip()
	{
		if (up)
		{
			rocketShip.up();
		}
		if (down)
		{
			rocketShip.down();
		}
		if (left)
		{
			rocketShip.left();
		}
		if (right)
		{
			rocketShip.right();
		}
	}
	
	void startGame()
	{
		alienTimer = new Timer(1000, objectManager);
		alienTimer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(CurrentState == MENU){
		    updateMenuState();
		}else if(CurrentState == GAME){
		    updateGameState();
			moveRocketShip();
		}else if(CurrentState == END){
		    updateEndState();
		}
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode()==KeyEvent.VK_ENTER) {
		    if (CurrentState == END) {
		        CurrentState = MENU;
		    } else {
		        CurrentState++;
		    }
		    
		    if (CurrentState == GAME)
		    {
		    	rocketShip = new RocketShip(250, 700, 50, 50);
		    	objectManager = new ObjectManager(rocketShip);
		    	startGame();
		    }
		    else if (CurrentState == END)
		    {
		    	alienTimer.stop();
		    }
		}
		
		if (CurrentState == GAME)
		{
			if (e.getKeyCode() == KeyEvent.VK_UP)
			{
				up = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN)
			{
				down = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT)
			{
				left = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			{
				right = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_SPACE)
			{
				objectManager.AddProjectile(rocketShip.getProjectile());
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (CurrentState == GAME)
		{
			if (e.getKeyCode() == KeyEvent.VK_UP)
			{
				up = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN)
			{
				down = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT)
			{
				left = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			{
				right = false;
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	void loadImage(String imageFile) {
	    if (needImage) {
	        try {
	            image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
		    gotImage = true;
	        } catch (Exception e) {
	            
	        }
	        needImage = false;
	    }
	}
}
