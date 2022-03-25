package com.mycompany.a3.Observers;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.plaf.Border;
import com.mycompany.a3.GameWorld;
import com.mycompany.a3.GameObjects.Ant;

public class ScoreView extends Container implements Observer{
	private GameWorld gw;
	private Ant ant;
	private Label l_lives;
	private Label l_time;
	private Label l_highestFlag;
	private Label l_foodLevel;
	private Label l_healthLevel;
	private Label l_sound;
	private Label val_lives = new Label();
	private Label val_time = new Label();
	private Label val_highestFlag = new Label();
	private Label val_foodLevel = new Label();
	private Label val_healthLevel = new Label();
	private Label val_sound = new Label();
	
	public ScoreView() {
		getUnselectedStyle().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));
		
		l_sound = new Label("Sound: ");
		l_lives = new Label("Lives: ");
		l_time = new Label("Time: ");
		l_highestFlag = new Label("Highest Flag: ");
		l_foodLevel = new Label("Food Level: ");
		l_healthLevel = new Label("Health Level: ");
		
		l_lives.getUnselectedStyle().setFgColor(ColorUtil.BLUE);
		l_time.getUnselectedStyle().setFgColor(ColorUtil.BLUE);
		l_highestFlag.getUnselectedStyle().setFgColor(ColorUtil.BLUE);
		l_foodLevel.getUnselectedStyle().setFgColor(ColorUtil.BLUE);
		l_healthLevel.getUnselectedStyle().setFgColor(ColorUtil.BLUE);
		l_sound.getUnselectedStyle().setFgColor(ColorUtil.BLUE);
		val_lives.getUnselectedStyle().setFgColor(ColorUtil.BLUE);
		val_time.getUnselectedStyle().setFgColor(ColorUtil.BLUE);
		val_highestFlag.getUnselectedStyle().setFgColor(ColorUtil.BLUE);
		val_foodLevel.getUnselectedStyle().setFgColor(ColorUtil.BLUE);
		val_healthLevel.getUnselectedStyle().setFgColor(ColorUtil.BLUE);
		val_sound.getUnselectedStyle().setFgColor(ColorUtil.BLUE);
		
		this.add(l_lives);			this.add(val_lives);
		this.add(l_time);			this.add(val_time);
		this.add(l_highestFlag);	this.add(val_highestFlag);
		this.add(l_foodLevel);		this.add(val_foodLevel);
		this.add(l_healthLevel);	this.add(val_healthLevel);
		this.add(l_sound);			this.add(val_sound);
	}
	
	@Override
	public void update(Observable observable, Object data) {
		gw = (GameWorld)observable;
		if(data != null) {
			ant = (Ant)data;
			val_lives.setText("" + gw.getLives());
			val_time.setText("" + gw.getTime());
			val_highestFlag.setText("" + ant.getLastFlagReached());
			val_foodLevel.setText("" + ant.getFoodLevel());
			val_healthLevel.setText("" + ant.getHealthLevel());
		}
		if(gw.isSound())
			val_sound.setText("ON");
		else
			val_sound.setText("OFF");
		revalidate();
	}
}
