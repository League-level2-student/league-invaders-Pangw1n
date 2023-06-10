import java.awt.Rectangle;

public class GameObject {
	int x;
	int y;
	int width;
	int height;
	
	int speed;
	boolean isActive;
	
	Rectangle collisionBox;
	
	public GameObject(int x, int y, int width, int height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.isActive = true;
		collisionBox = new Rectangle(x - width / 2, y - height / 2, width, height);
	}
	
	public void update()
	{
		collisionBox.setBounds(x - width / 2, y - height / 2, width, height);
	}
}
