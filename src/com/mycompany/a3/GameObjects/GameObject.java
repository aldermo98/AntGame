package com.mycompany.a3.GameObjects;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.mycompany.a3.ICollider;
import com.mycompany.a3.IDrawable;

public abstract class GameObject implements IDrawable, ICollider{
	private int size;
	private int color;
	private Point location;
	
	public GameObject(int size, int color, Point location) {
		this.size = size;
		this.color = color;
		this.location = location;
	}
	
	public int getSize() {
		return size;
	}
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public Point getLocation() {
		return location;
	}
	public void setLocation(Point location) {
		this.location = location;
	}
	
	public String toString(int color) {
		String ret = "[" + ColorUtil.red(getColor()) + "," + 
					 ColorUtil.green(getColor()) + "," + 
					 ColorUtil.blue(getColor()) + "]";
		return ret;
	}
	public String toString(Point location) {
		String ret = location.getX() + "," + location.getY();
		return ret;
	}
	public String toString() {
		String ret = "loc=" + toString(location) + " size=" + size + " color=" + toString(color);
		return ret;
	}
	
}
