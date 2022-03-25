package com.mycompany.a3.GameObjects;

import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.ui.geom.Dimension;

public abstract class Movable extends GameObject {
	private int heading;
	private int speed;
	
	public Movable(int size, int color, Point location) {
		super(size, color, location);
	}
	
	public int getHeading() { return heading; }
	public void setHeading(int heading) { this.heading = heading; }
	public int getSpeed() { return speed; }
	public void setSpeed(int speed) { this.speed = speed; }

	public void move(Dimension dCmpSize, int time) {
		double deltaX = Math.cos(Math.toRadians( 90-getHeading() ))*getSpeed()*time/100;	
		double deltaY = Math.sin(Math.toRadians( 90-getHeading() ))*getSpeed()*time/100;
		if((deltaX+getLocation().getX() >= dCmpSize.getWidth())){
			deltaX = -deltaX;
			setHeading(90 + new Random().nextInt(180));
		}else if(deltaX+getLocation().getX() < 0){
			deltaX = -deltaX;
			int direction = new Random().nextInt(2);	//value 0 represents 0-90 degrees, value 1 represents 270-359 degrees
			if(direction == 0)
				setHeading( new Random().nextInt(90) );
			else
				setHeading(270 + new Random().nextInt(90));
		}
		if ((deltaY+getLocation().getY() >= dCmpSize.getHeight())){
			deltaY = -deltaY;
			setHeading(new Random().nextInt(180));
		}else if(deltaY+getLocation().getY() < 0){
			deltaY = -deltaY;
			int direction = new Random().nextInt(2);	//value 0 represents 0-90 degrees, value 1 represents 270-359 degrees
			if(direction == 0)
				setHeading(180 + new Random().nextInt(90) );
			else
				setHeading(270 + new Random().nextInt(90));
		}
		double newX = getLocation().getX() + deltaX;
		double newY = getLocation().getY() + deltaY;
		setLocation(new Point((int)newX,(int)newY));
	}
	
	public String toString() {
		String ret = super.toString() + " heading=" + heading + " speed=" + speed;
		return ret;
	}
}
