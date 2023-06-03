import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {
	RocketShip rocketShip;
	ArrayList<Projectile> projectiles;
	ArrayList<Alien> aliens;
	
	public ObjectManager(RocketShip rocket)
	{
		rocketShip = rocket;
		projectiles = new ArrayList<Projectile>();
		aliens = new ArrayList<Alien>();
	}
	
	public void AddProjectile(Projectile projectile)
	{
		projectiles.add(projectile);
	}
	
	public void AddAlien()
	{
		Random random = new Random();
		aliens.add(new Alien(random.nextInt(LeagueInvaders.WIDTH),0,50,50));
	}
	
	public void update()
	{
		for(Alien a : aliens)
		{
			if (a.y > LeagueInvaders.HEIGHT)
			{
				a.isActive = false;
			}
		}
		
		for(Projectile p : projectiles)
		{
			if (p.y < 0)
			{
				p.isActive = false;
			}
		}
	}
	
	public void draw(Graphics g)
	{
		rocketShip.draw(g);
		
		for(Alien a : aliens)
		{
			a.draw(g);
		}
		
		for(Projectile p : projectiles)
		{
			p.draw(g);
		}
	}
	
	void purgeObjects()
	{
		for(Alien a : aliens)
		{
			if (!a.isActive)
			{
				aliens.remove(a);
			}
		}
		for(Projectile p : projectiles)
		{
			if (!p.isActive)
			{
				projectiles.remove(p);
			}
		}
	}
}
