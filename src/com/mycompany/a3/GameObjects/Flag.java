package com.mycompany.a3.GameObjects;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.plaf.Style;

public class Flag extends Fixed{
	private int sequenceNumber;
	private static int highestFlag;
	
	public Flag(int size, int color, Point location) {
		super(size, color, location);
		sequenceNumber = ++highestFlag;
	}
	
	public int getSequenceNumber() { return sequenceNumber; }
	public static int getHighestFlag() { return highestFlag; };
	public static void setHighestFlag(int num) {highestFlag = num;}
	
	public void draw(Graphics g, Point pCmpRelPrnt) {
		g.setColor(getColor());
		int [] xPoints = {(int)(pCmpRelPrnt.getX()+getLocation().getX()-getSize()/2), (int)(pCmpRelPrnt.getX()+getLocation().getX()+getSize()-getSize()/2), (int)(pCmpRelPrnt.getX()+getLocation().getX())};
		int [] yPoints = {(int)(pCmpRelPrnt.getY()+getLocation().getY()-getSize()/2), (int)(pCmpRelPrnt.getY()+getLocation().getY()-getSize()/2), (int)(pCmpRelPrnt.getY()+getLocation().getY()+getSize()-getSize()/2)};
		
		if(isSelected())
			g.fillPolygon(xPoints, yPoints, 3);
		else
			g.drawPolygon(xPoints, yPoints, 3);

		g.drawString(String.valueOf(getSequenceNumber()), (int)(pCmpRelPrnt.getX()+getLocation().getX()), (int)(pCmpRelPrnt.getY()+getLocation().getY()));
	}
	
	@Override
	public void setColor(int color) {}
	public String toString() {
		String ret = "Flag " + sequenceNumber + "     : " + super.toString();
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
