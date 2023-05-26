import java.awt.Color;
import java.awt.Graphics;

public class RocketShip extends GameObject {

	public RocketShip(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
		speed = 6;
	}
	
	public void draw(Graphics g)
	{
		g.setColor(Color.BLUE);
		g.fillRect(x - width/2, y - height/2, width, height);
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
}
