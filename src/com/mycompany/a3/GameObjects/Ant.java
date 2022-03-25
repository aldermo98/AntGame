package com.mycompany.a3.GameObjects;

import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;
import com.mycompany.a3.GameWorld;
import com.mycompany.a3.ISteerable;

public class Ant extends Movable implements ISteerable {
	private static Ant ant;
	private int maxSpeed = 50;
	private int foodLevel = 10000;
	private int foodConsumptionRate = 1;
	private int healthLevel = 10;
	private int lastFlagReached = 1;

	private Ant(int size, int color, Point location) {
		super(size, color, location);		
		setHeading(10);
		setSpeed(15);
	}
	public static Ant initAnt(int antSize, int antColor, Point location) {
		ant =  new Ant(antSize, antColor, location);
		return ant;
	}
	public static Ant getAnt() {
		if (ant == null)
			return null;
		return ant;
	}
	
	public int getMaxSpeed() {return maxSpeed;}
	public void setMaxSpeed(int maxSpeed) { this.maxSpeed = maxSpeed; }
	public int getFoodLevel() {return foodLevel;}
	public void setFoodLevel(int foodLevel) {this.foodLevel = foodLevel;}
	public int getFoodConsumptionRate() {return foodConsumptionRate;}
	public void setFoodConsumptionRate(int foodConsumptionRate) {this.foodConsumptionRate = foodConsumptionRate;}
	public int getHealthLevel() {return healthLevel;}
	public void setHealthLevel(int healthLevel) {this.healthLevel = healthLevel;}
	public int getLastFlagReached() {return lastFlagReached;}
	public void setLastFlagReached(int lastFlagReached) {this.lastFlagReached = lastFlagReached;}

	public void draw(Graphics g, Point pCmpRelPrnt) {
		g.setColor(getColor());
		g.fillArc((int)(pCmpRelPrnt.getX()+getLocation().getX()-getSize()/2), (int)(pCmpRelPrnt.getY()+getLocation().getY()-getSize()/2), getSize(), getSize(), 0, 360);
	}
	
	public void changeHeading(int newHeading) {
		setHeading(newHeading);
	}
	
	public String toString() {
		String ret = "Ant        : " + super.toString() + " maxSpeed=" + maxSpeed + " foodConsumptionRate=" + foodConsumptionRate;
		return ret;
	}
	
	@Override
	public boolean collidesWith(GameObject otherObject) {
		boolean result = false;
		int thisCenterX = (int)getLocation().getX() + (getSize()/2);
		int thisCenterY = (int)getLocation().getY() + (getSize()/2);
		int otherCenterX = (int)otherObject.getLocation().getX() + (otherObject.getSize()/2);
		int otherCenterY = (int)otherObject.getLocation().getY() + (otherObject.getSize()/2);
		int dx = thisCenterX - otherCenterX;
		int dy = thisCenterY - otherCenterY;
		int distBetweenCentersSqr = (dx*dx + dy*dy);
		int thisRadius = getSize()/2;
		int otherRadius = otherObject.getSize()/2;
		int radiiSqr = (thisRadius*thisRadius + 2*thisRadius*otherRadius + otherRadius*otherRadius);
		if (distBetweenCentersSqr <= radiiSqr) { result = true ; }
		return result;
	}
	@Override
	public void handleCollision(GameObject otherObject) {
		setHeading(-getHeading());
	}
	
}
