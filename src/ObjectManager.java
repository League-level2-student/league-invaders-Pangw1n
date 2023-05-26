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
	
	void AddProjectile(Projectile projectile)
	{
		projectiles.add(projectile);
	}
	
	void AddAlien()
	{
		aliens.add(new Alien(Random.nextInt(LeagueInvaders.WIDTH),0,50,50));
	}
}
