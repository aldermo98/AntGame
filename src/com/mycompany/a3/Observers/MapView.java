package com.mycompany.a3.Observers;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.plaf.Border;
import com.mycompany.a3.Game;
import com.mycompany.a3.GameWorld;
import com.mycompany.a3.IIterator;
import com.mycompany.a3.GameObjects.Ant;
import com.mycompany.a3.GameObjects.Fixed;
import com.mycompany.a3.GameObjects.Flag;
import com.mycompany.a3.GameObjects.FoodStation;
import com.mycompany.a3.GameObjects.GameObject;
import com.mycompany.a3.GameObjects.Spider;

public class MapView extends Container implements Observer{
	private GameWorld gw;
	private Game g;
	
	public MapView(GameWorld gw, Game g) {
		getUnselectedStyle().setBorder(Border.createLineBorder(1, ColorUtil.rgb(255,0,0)));
		this.gw = gw;
		this.g = g;
	}
	
	public void pointerPressed(int x, int y) {
		if(g.isPaused()) {
			x = x - getParent().getAbsoluteX();
			y = y - getParent().getAbsoluteY();
			Point pPtrRelPrnt = new Point(x, y); 
			Point pCmpRelPrnt = new Point(getX(), getY());
			IIterator elements = gw.getWorldObjects().getIterator();
			
			if(g.isPosition()) {
				x-=getX();
				y-=getY();
				pPtrRelPrnt = new Point(x, y); 
				elements = gw.getWorldObjects().getIterator();
				while(elements.hasNext()) {  
					GameObject obj = elements.getNext();
					if(obj instanceof Fixed) {
						if(((Fixed) obj).isSelected()) {
							obj.setLocation(pPtrRelPrnt);
						}
					}
				}
			}
			
			while(elements.hasNext()) {  
				GameObject obj = elements.getNext();
				if(obj instanceof Fixed) {
					if(((Fixed) obj).contains(pPtrRelPrnt, pCmpRelPrnt)) {
						((Fixed) obj).setSelected(true);
					}else {
						((Fixed) obj).setSelected(false);
					}
				}
			}
			
			
			
			repaint(); 
		}
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		IIterator elements = gw.getWorldObjects().getIterator();
		while(elements.hasNext()) {  
			elements.getNext().draw(g, new Point(getX(),getY()));
		}
	}
	
	@Override
	public void update(Observable observable, Object data) {
		repaint();
		gw.displayMap();
	}
}
