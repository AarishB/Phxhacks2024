import java.awt.*;
import java.awt.image.*;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.geom.*;

public class Track {
	private double direction;

	private double speed;
	public int x;
	public int y;
	private int width = 1000;
	private int height = 1000;
	private static int maxSpeed = 10;

	public Track(int x, int y) {
		this.x = x;
		this.y = y;
		this.direction = Math.PI / 2;
	}

	public void draw(Graphics g, String picture) {
		ImageIcon image = new ImageIcon(picture + ".png");
		Graphics2D g2d = (Graphics2D) g;
		//Make a backup so that we can reset our graphics object after using it.
		AffineTransform backup = g2d.getTransform();
		//rx is the x coordinate for rotation, ry is the y coordinate for rotation, and angle
		//is the angle to rotate the image. If you want to rotate around the center of an image,
		//use the image's center x and y coordinates for rx and ry.
		AffineTransform a = AffineTransform.getRotateInstance(direction - Math.PI / 2, x + width / 2, y + height / 2);
		//Set our Graphics2D object to the transform
		g2d.setTransform(a);
		//Draw our image like normal
		g2d.drawImage(image.getImage(), x, y, width, height, null);
		//Reset our graphics object so we can draw with it again.
		g2d.setTransform(backup);
		g2d.setColor(Color.white);
	}

	public void rotate(double angle){
		direction += Math.toRadians(angle);		
		if(direction > 2 * Math.PI)  direction = direction % (2 * Math.PI);
		else if (direction < 0) {
			direction += 2*Math.PI;
		}
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
		speed+=change;
		if(speed > maxSpeed){
			speed = maxSpeed;
		}
		if(speed < -maxSpeed) {
			speed = -maxSpeed;
		}

	}
	public double findDistanceFrom(double x, double y, int edge) {
		double distance1 = Math.abs(Math.sqrt(Math.pow(edge - x, 2) +
				Math.pow(edge - y, 2)));
		return distance1;
	}

}