package com.mycompany.a3.GameObjects;

import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.mycompany.a3.IIterator;

public class Spider extends Movable{
	public Spider(int size, int color, Point location, int speed, int heading) {
		super( size, color, location);
		setSpeed(speed);
		setHeading(heading);
	}
	
	public void move(Point oldLocation, int mapEdge_x, int mapEdge_y) {
		double deltaX = Math.cos(Math.toRadians( 90-getHeading() ))*getSpeed();
		double deltaY = Math.sin(Math.toRadians( 90-getHeading() ))*getSpeed();
		double newX = oldLocation.getX() + deltaX;
		double newY = oldLocation.getY() + deltaY;
		
		if(newX <= 0) {
			double wall_y = oldLocation.getX()*Math.tan(Math.toRadians( 90-getHeading()));
			setLocation( new Point(0, (int)wall_y) );
			setHeading( new Random().nextInt(180) );
			/*
			double restOf_dist = (0-newX)/Math.cos(Math.toRadians( 90-getHeading()));
			setHeading( new Random().nextInt(180) );
			double reboundX = Math.cos(Math.toRadians( 90-getHeading() ))*restOf_dist;
			double reboundY = Math.sin(Math.toRadians( 90-getHeading() ))*restOf_dist;
			setLocation( new Point((int)reboundX,(int)reboundY ));
			*/
		}else if(newX >= mapEdge_x) {
			double wall_y = (mapEdge_x-oldLocation.getX())*Math.tan(Math.toRadians( 90-getHeading()));
			setLocation(new Point(mapEdge_x, (int)wall_y ));
			setHeading( new Random().nextInt((359-180)+1)+180 );
			/*
			double restOf_dist = (newX-1000)/Math.cos(Math.toRadians( 90-getHeading()));
			setHeading( new Random().nextInt((359-180)+1)+180 );
			double reboundX = Math.cos(Math.toRadians( 90-getHeading() ))*restOf_dist;
			double reboundY = Math.sin(Math.toRadians( 90-getHeading() ))*restOf_dist;
			setLocation(new Point((int)reboundX, (int)reboundY));
			*/
		}
		
		if(newY <= 0) { 
			double wall_x = oldLocation.getY()*Math.tan(Math.toRadians( 90-getHeading()));
			setLocation(new Point((int)wall_x, 0));
			int direction = new Random().nextInt(1);	//value 0 represents 0-90 degrees, value 1 represents 270-359 degrees
			if(direction == 0)
				setHeading( new Random().nextInt(90) );
			else
				setHeading( new Random().nextInt((359-270)+1)+270 );
			/*
			double restOf_dist = (0-newY)/Math.cos(Math.toRadians( 90-getHeading()));
			int direction = new Random().nextInt(1);	//value 0 represents 0-90 degrees, value 1 represents 270-359 degrees
			if(direction == 0)
				setHeading( new Random().nextInt(90) );
			else
				setHeading( new Random().nextInt((359-270)+1)+270 );
			double reboundX = Math.cos(Math.toRadians( 90-getHeading() ))*restOf_dist;
			double reboundY = Math.sin(Math.toRadians( 90-getHeading() ))*restOf_dist;
			setLocation(new Point((int)reboundX, (int)reboundY));
			*/
		}else if(newY >= mapEdge_y) {
			double wall_x = (mapEdge_y-oldLocation.getY())*Math.tan(Math.toRadians( 90-getHeading()));
			setLocation(new Point((int)wall_x,mapEdge_y));
			setHeading( new Random().nextInt((270-90)+1)+90 );
			/*
			double restOf_dist = (newX-1000)/Math.cos(Math.toRadians( 90-getHeading()));
			setHeading( new Random().nextInt((270-90)+1)+90 );
			double reboundX = Math.cos(Math.toRadians( 90-getHeading() ))*restOf_dist;
			double reboundY = Math.sin(Math.toRadians( 90-getHeading() ))*restOf_dist;
			setLocation(new Point((int)reboundX,(int)reboundY));
			*/
		}
		
		setLocation( new Point((int)newX,(int)newY) );
	}
	
	public void draw(Graphics g, Point pCmpRelPrnt) {
		g.setColor(getColor());
		int [] xPoints = {(int)(pCmpRelPrnt.getX()+getLocation().getX()-getSize()/2), (int)(pCmpRelPrnt.getX()+getLocation().getX()+getSize()/2), (int)(pCmpRelPrnt.getX()+getLocation().getX())};
		int [] yPoints = {(int)(pCmpRelPrnt.getY()+getLocation().getY()-getSize()/2), (int)(pCmpRelPrnt.getY()+getLocation().getY()-getSize()/2), (int)(pCmpRelPrnt.getY()+getLocation().getY()+getSize()/2)};
		g.fillPolygon(xPoints, yPoints, 3);
	}
	
	@Override
	public void setColor(int color) {}
	public String toString() {
		String ret = "Spider     : " + super.toString();
		return ret;
	}

	@Override
	public boolean collidesWith(GameObject otherObject) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void handleCollision(GameObject otherObject) {
	
	}
}
