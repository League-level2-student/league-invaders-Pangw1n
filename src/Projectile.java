
public class Projectile extends GameObject{

	public Projectile(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
		speed = 10;
	}
	
	void update()
	{
		Up();
	}
	
	void Up()
	{
		y -= speed;
	}
}
