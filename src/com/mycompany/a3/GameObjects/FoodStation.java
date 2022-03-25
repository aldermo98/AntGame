package com.mycompany.a3.GameObjects;

import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;

public class FoodStation extends Fixed{
	private int capacity;
	
	public FoodStation(int size, int color, Point location) {
		super( size, color, location );
		capacity = getSize();
	}

	public int getCapacity() {return capacity;}
	public void setCapacity(int capacity) {this.capacity = capacity;}
	
	public void draw(Graphics g, Point pCmpRelPrnt) {
		g.setColor(getColor());
		if(isSelected()) {
			g.drawRect((int)(pCmpRelPrnt.getX()+getLocation().getX()-getSize()/2), (int)(pCmpRelPrnt.getY()+getLocation().getY()-getSize()/2), getSize(), getSize());
		}else {
			g.fillRect((int)(pCmpRelPrnt.getX()+getLocation().getX()-getSize()/2), (int)(pCmpRelPrnt.getY()+getLocation().getY()-getSize()/2), getSize(), getSize());
		}
		g.drawString(String.valueOf(getCapacity()), (int)(pCmpRelPrnt.getX()+getLocation().getX()), (int)(pCmpRelPrnt.getY()+getLocation().getY()));
	}
	
	
	
	public String toString() {
		String ret = "FoodStation: " + super.toString() + " capacity=" + capacity;
		return ret;
	}
	
	@Override
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt){
		int px = (int) pPtrRelPrnt.getX(); 
		int py = (int) pPtrRelPrnt.getY(); 
		int xLoc = (int)(pCmpRelPrnt.getX() + (getLocation().getX()-getSize()/2));
		int yLoc = (int)(pCmpRelPrnt.getY()+ (getLocation().getY()-getSize()/2));
		if ( (px >= xLoc) && (px <= xLoc+getSize()) && (py >= yLoc) && (py <= yLoc+getSize()) ) {
			return true; 
		}else {
			return false;
		}
	}

	@Override
	public boolean collidesWith(GameObject otherObject) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void handleCollision(GameObject otherObject) {
		// TODO Auto-generated method stub
		
	}
	
}
