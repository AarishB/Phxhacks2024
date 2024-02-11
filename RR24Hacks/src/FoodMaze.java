//RockRidge Hackathaon 2024 Theme: Food
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;

import javax.swing.*;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")

public class FoodMaze extends JPanel {

	private static int WIDTH = 5600;
	private static int HEIGHT = 5000;
	private static Color BACKGROUND = new Color(246, 220, 189);
	private BufferedImage image;
	private Graphics g;
	private Timer timer;
	private ArrayList<Kart> karts = new ArrayList<Kart> ();
	
	private ArrayList<Integer> keys;
	private ArrayList<Title> title = new ArrayList<Title>();
	private ArrayList<Prize> prize = new ArrayList<Prize>();
	Racetrack racetrack = new Racetrack(WIDTH, HEIGHT);

	public FoodMaze() {
		// set up Buffered Image and Graphics objects
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = image.getGraphics();
		keys = new ArrayList<Integer>();

		karts.add(new Kart(2800, 745, 10));
		karts.add(new Kart(3100, 750, 10));
		
		title.add(new Title(2950, 1000));
		prize.add(new Prize(4630, -4600));

		// set up and start the Timer
		timer = new Timer(10, new TimerListener());
		addKeyListener(new Keyboard());
		setFocusable(true);
		timer.start();

	}
	//
	// TimerListener class that is called repeatedly by the timer
	private class TimerListener implements ActionListener {
		@Override

		public void actionPerformed(ActionEvent e) {
			g.setColor(BACKGROUND);
			g.fillRect(0, 0, WIDTH, HEIGHT);
			int rotation;
			for (int key : keys) {
				switch(key) {
				case KeyEvent.VK_UP:

					karts.get(0).changeSpeed(2);
					racetrack.changeSpeed(-2);
					prize.get(0).changeSpeed(-2);
					break;
				case KeyEvent.VK_RIGHT:
					
					karts.get(0).rotate(5);
					break;
				case KeyEvent.VK_LEFT:
					karts.get(0).rotate(-5);
					racetrack.rotateAllTracks(5);
					break;
				case KeyEvent.VK_DOWN:
					title.get(0).changeSpeed(2);
					racetrack.changeSpeed(2);
					prize.get(0).changeSpeed(2);
					karts.get(0).changeSpeed(-2);
					title.get(0).changeSpeed(2);
					break;


				case KeyEvent.VK_S:
					racetrack.changeSpeed(-2);
					karts.get(1).changeSpeed(2);
					prize.get(0).changeSpeed(-2);
					break;
				case KeyEvent.VK_D:
					racetrack.rotateAllTracks(5);
					karts.get(1).rotate(5);
					break;
				case KeyEvent.VK_A:
					racetrack.rotateAllTracks(-5);
					karts.get(1).rotate(-5);
					break;
				case KeyEvent.VK_W:
					racetrack.rotateAllTracks(2);
					prize.get(0).changeSpeed(2);
					title.get(0).changeSpeed(2);
					karts.get(1).changeSpeed(-2);
					break;
				}
			}


			racetrack.draw(g);
			prize.get(0).move();
			prize.get(0).draw(g, "jackpot");
			karts.get(0).move();
			karts.get(0).draw(g, "meat_kart");
			karts.get(1).move();
			karts.get(1).draw(g, "fruit_kart");
			title.get(0).move();
			title.get(0).draw(g, "foodmazetitle");
			
			repaint();
		}
	}

	private class Keyboard implements KeyListener {
		public void keyPressed(KeyEvent e) {
			int x = e.getKeyCode();
			if (!keys.contains(x)) {
				keys.add(x);
			}
		}

		public void keyReleased(KeyEvent e) {
			keys.remove((Integer) e.getKeyCode());
		}

		public void keyTyped(KeyEvent e) {
		}
	}


	public void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
	}

	// main method with standard graphics code
	public static void main(String[] args) {
		JFrame frame = new JFrame("Animation Shell");
		frame.setSize(WIDTH, HEIGHT);
		frame.setLocation(0, 0);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new FoodMaze());
		frame.setVisible(true);
	}

}