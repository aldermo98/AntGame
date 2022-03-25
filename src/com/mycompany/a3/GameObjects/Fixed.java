package com.mycompany.a3.GameObjects;

import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;
import com.mycompany.a3.ISelectable;

public abstract class Fixed extends GameObject implements ISelectable{
	private boolean isSelected;
	
	public Fixed(int size, int color, Point location) {
		super(size, color, location);
	}
	
	public void setSelected(boolean b) { isSelected = b; }
	public boolean isSelected() { return isSelected; }
	public abstract void draw(Graphics g, Point pCmpRelPrnt);
	public abstract boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt);
	
	@Override
	//public void setLocation(Point location) {}
	public String toString() {
		return super.toString();
	}
}
