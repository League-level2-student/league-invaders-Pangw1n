import java.awt.Color;
import java.awt.Graphics;

public class Alien extends GameObject{

	public Alien(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
		speed = 1;
	}
	
	void update()
	{
		Down();
	}
	
	void Down()
	{
		y += speed;
	}
	
	public void draw(Graphics g)
	{
		g.setColor(Color.YELLOW);
        g.fillRect(x, y, width, height);
	}
}
