import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Timer;

public class ObjectManager implements ActionListener{
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
		rocketShip.update();
		for(Alien a : aliens)
		{
			a.update();
			if (a.y > LeagueInvaders.HEIGHT)
			{
				a.isActive = false;
			}
		}
		
		for(Projectile p : projectiles)
		{
			p.update();
			if (p.y < 0)
			{
				p.isActive = false;
			}
		}
		checkCollision();
		purgeObjects();
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
	
	void checkCollision()
	{
		for (Alien a : aliens)
		{
			if (rocketShip.collisionBox.intersects(a.collisionBox))
			{
				rocketShip.isActive = false;
			}
			for (Projectile p : projectiles)
			{
				if (p.collisionBox.intersects(a.collisionBox))
				{
					p.isActive = false;
					a.isActive = false;
				}
			}
		}
		
	}
	
	void purgeObjects()
	{
		for(int i = aliens.size() - 1; i >= 0; i--)
		{
			if (!aliens.get(i).isActive)
			{
				aliens.remove(i);
			}
		}
		for(int i = projectiles.size() - 1; i >= 0; i--)
		{
			if (!projectiles.get(i).isActive)
			{
				projectiles.remove(i);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		AddAlien();
	}
}
