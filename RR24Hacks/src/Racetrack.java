import java.awt.*;
import java.awt.image.*;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.geom.*;

public class Racetrack {
	private double direction;
	private double speed;
	private int x;
	private int y;
	private int width = 1000;
	private int height = 1000;
	private static int maxSpeed = 10;
	private ArrayList<Track> tracks = new ArrayList<Track>();

	public Racetrack(int x, int y) {
		
		this.x = x;
		this.y = y;
		this.direction = Math.PI / 2;
		tracks.add(new Track(2450, 0));
		tracks.add(new Track(2450, -1000));
		tracks.add(new Track(2450, -2000));
		tracks.add(new Track(2510, -3000));
		tracks.add(new Track(1510, -3050));
		tracks.add(new Track(510, -3050));
		tracks.add(new Track(-490, -2990));
		tracks.add(new Track(-680, -1990));
		tracks.add(new Track(3510, -3050));
		tracks.add(new Track(4510, -3175));
		tracks.add(new Track(4635, -4100));
	}

	public void draw(Graphics g) {
		tracks.get(0).move();
		tracks.get(0).draw(g, "trackOpenUpClosedAllElse"); //start image
		tracks.get(1).move();
		tracks.get(1).draw(g, "trackVertical2"); //start image again
		tracks.get(2).move();
		tracks.get(2).draw(g, "trackVertical2"); //start image again
		tracks.get(3).move();
		tracks.get(3).draw(g, "trackOpenAllElseClosedUp"); //intersection
		tracks.get(4).move();
		tracks.get(4).draw(g, "trackHorizontal2"); //left road
		tracks.get(5).move();
		tracks.get(5).draw(g, "trackHorizontal2"); //extended left road
		tracks.get(6).move();
		tracks.get(6).draw(g, "trackOpenRightDownClosedUpLeft2"); //
		tracks.get(7).move();
		tracks.get(7).draw(g, "trackOpenUpClosedAllElse");
		tracks.get(8).move();
		tracks.get(8).draw(g, "trackHorizontal2");
		tracks.get(9).move();
		tracks.get(9).draw(g, "trackOpenLeftUpClosedRightDown");
		tracks.get(10).move();
		tracks.get(10).draw(g, "trackvertical");
		
	}
	public void rotate(double angle){
		direction += Math.toRadians(angle);		
		if(direction > 2 * Math.PI)  direction = direction % (2 * Math.PI);
		else if (direction < 0) {
			direction += 2*Math.PI;
		}
	}
	public void rotateAllTracks(int rotation) {
	
	}
	public void move(){
		x -= (int)(speed * Math.cos(direction));
		y -= (int)(speed * Math.sin(direction));
		if(speed > 0) {
			speed--;
		}
		else if(speed < 0) {
			speed++;
		}
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getX() {
		return x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getY() {
		return y;
	}
	public void changeSpeed(int change){
		for(int i = 0; i < tracks.size(); i++) {
			tracks.get(i).changeSpeed(change);
		}

	}
	public double findDistanceFrom(double x, double y, int edge) {
		double distance1 = Math.abs(Math.sqrt(Math.pow(edge - x, 2) +
				Math.pow(edge - y, 2)));
		return distance1;
	}

}