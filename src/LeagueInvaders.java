import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class LeagueInvaders {
	public static final int WIDTH = 500;
	public static final int HEIGHT = 800;
	JFrame window;
	GamePanel panel;
	
	public static void main(String[] args) {
		LeagueInvaders leagueInvaders = new LeagueInvaders();
		leagueInvaders.setup();
	}
	
	public LeagueInvaders()
	{
		window = new JFrame();
		panel = new GamePanel();
	}
	
	void setup()
	{
		window.add(panel);
		window.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.addKeyListener(panel);
		window.pack();
	}
}
