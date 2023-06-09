import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class RocketShip extends GameObject {

	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	
	public RocketShip(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
		speed = 6;
		
		if (needImage) {
		    loadImage ("rocket.png");
		}
	}
	
	public void draw(Graphics g)
	{
		if (gotImage) {
			g.drawImage(image, x - width / 2, y - height / 2, width, height, null);
		} else {
			g.setColor(Color.BLUE);
			g.fillRect(x - width / 2, y - height / 2, width, height);
		}
	}
	
	public void up() {
        y-=speed;
        CheckEdge();
    }
	public void down() {
        y+=speed;
        CheckEdge();
    }
	public void left() {
        x-=speed;
        CheckEdge();
    }
	public void right() {
        x+=speed;
        CheckEdge();
    }
	
	void CheckEdge()
	{
		if (x > LeagueInvaders.WIDTH)
		{
			left();
		}
		if (x < 0)
		{
			right();
		}
		if (y > LeagueInvaders.HEIGHT)
		{
			up();
		}
		if (y < 0)
		{
			down();
		}
	}
	
	public void update()
	{
		super.update();
	}
	
	public Projectile getProjectile() {
		return new Projectile(x, y, 10, 10);
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
