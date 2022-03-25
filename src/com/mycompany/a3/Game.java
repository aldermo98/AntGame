package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.UITimer;
import com.mycompany.a3.Commands.CommandAbout;
import com.mycompany.a3.Commands.CommandAccelerate;
import com.mycompany.a3.Commands.CommandBrake;
import com.mycompany.a3.Commands.CommandExit;
import com.mycompany.a3.Commands.CommandHelp;
import com.mycompany.a3.Commands.CommandLeft;
import com.mycompany.a3.Commands.CommandPause;
import com.mycompany.a3.Commands.CommandPosition;
import com.mycompany.a3.Commands.CommandRight;
import com.mycompany.a3.Commands.CommandSound;
import com.mycompany.a3.Observers.MapView;
import com.mycompany.a3.Observers.ScoreView;

public class Game extends Form implements Runnable{
	private GameWorld gw;
	private ScoreView sv;
	private MapView mv;
	private boolean paused = false;
	private boolean position = false;
	private int timeMillis;

	public Game() {
		gw = new GameWorld(); 
		sv = new ScoreView();
		mv = new MapView(gw, this);
		gw.addObserver(sv);
		gw.addObserver(mv);		
		
		this.setLayout(new BorderLayout());
		
		Container top = sv;
		Container left = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		Container right = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		Container bottom = new Container(new BoxLayout(BoxLayout.X_AXIS));
		Container center = mv;
		                
		top.setLayout(new FlowLayout(Component.CENTER));
		top.getUnselectedStyle().setBorder(Border.createLineBorder(1, ColorUtil.BLACK));
		left.getUnselectedStyle().setBorder(Border.createLineBorder(1, ColorUtil.BLACK));
		left.getAllStyles().setPadding(Component.TOP, 100);
		right.getUnselectedStyle().setBorder(Border.createLineBorder(1, ColorUtil.BLACK));
		right.getAllStyles().setPadding(Component.TOP, 100);
		bottom.setLayout(new FlowLayout(Component.CENTER));
		bottom.getUnselectedStyle().setBorder(Border.createLineBorder(1, ColorUtil.BLACK));
		center.getUnselectedStyle().setBorder(Border.createLineBorder(1, ColorUtil.rgb(255, 0, 0)));
		
		CommandBrake c_brake = new CommandBrake(gw);
		CommandAccelerate c_accelerate = new CommandAccelerate(gw);
		CommandRight c_right = new CommandRight(gw);
		CommandLeft c_left = new CommandLeft(gw);
		CommandPause c_pause = new CommandPause(this,gw);
		CommandPosition c_position = new CommandPosition(gw,this);
		CommandExit c_exit = new CommandExit(gw);
		CommandSound c_sound = new CommandSound(this,gw);
		CommandAbout c_about = new CommandAbout();
		CommandHelp c_help = new CommandHelp();
		
		Toolbar tb = new Toolbar();
		setToolbar(tb);
		this.setTitle("The Path Game");
		
		CheckBox b_sound = new CheckBox("Sound");
		b_sound.getAllStyles().setBgTransparency(255);
		b_sound.getAllStyles().setBgColor(ColorUtil.LTGRAY);
		b_sound.setCommand(c_sound);
		
		tb.addComponentToSideMenu(b_sound);
		tb.addCommandToSideMenu(c_accelerate);
		tb.addCommandToSideMenu(c_about);
		tb.addCommandToSideMenu(c_help);
		tb.addCommandToSideMenu(c_exit);
		tb.addCommandToRightBar(c_help);
		
		Button b_brake = new Button("Brake");
		b_brake.getUnselectedStyle().setBgTransparency(255);
		b_brake.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		b_brake.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		b_brake.getUnselectedStyle().setBorder(Border.createLineBorder(1, ColorUtil.BLACK));
		b_brake.getAllStyles().setPadding(Component.TOP, 4);
		b_brake.getAllStyles().setPadding(Component.BOTTOM, 4);
		b_brake.setCommand(c_brake);
		addKeyListener('b', c_brake);
		
		Button b_right = new Button("Right");
		b_right.getUnselectedStyle().setBgTransparency(255);
		b_right.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		b_right.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		b_right.getUnselectedStyle().setBorder(Border.createLineBorder(1, ColorUtil.BLACK));
		b_right.getAllStyles().setPadding(Component.TOP, 4);
		b_right.getAllStyles().setPadding(Component.BOTTOM, 4);
		b_right.setCommand(c_right);
		addKeyListener('r', c_right);
		
		Button b_accelerate = new Button("Accelerate");
		b_accelerate.getUnselectedStyle().setBgTransparency(255);
		b_accelerate.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		b_accelerate.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		b_accelerate.getUnselectedStyle().setBorder(Border.createLineBorder(1, ColorUtil.BLACK));
		b_accelerate.getAllStyles().setPadding(Component.TOP, 4);
		b_accelerate.getAllStyles().setPadding(Component.BOTTOM, 4);
		b_accelerate.setCommand(c_accelerate);
		addKeyListener('a', c_accelerate);
		
		Button b_left = new Button("Left");
		b_left.getUnselectedStyle().setBgTransparency(255);
		b_left.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		b_left.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		b_left.getUnselectedStyle().setBorder(Border.createLineBorder(1, ColorUtil.BLACK));
		b_left.getAllStyles().setPadding(Component.TOP, 4);
		b_left.getAllStyles().setPadding(Component.BOTTOM, 4);
		b_left.setCommand(c_left);
		addKeyListener('l', c_left);
		
		Button b_pause = new Button("Play/Pause");
		b_pause.getUnselectedStyle().setBgTransparency(255);
		b_pause.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		b_pause.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		b_pause.getUnselectedStyle().setBorder(Border.createLineBorder(1, ColorUtil.BLACK));
		b_pause.getAllStyles().setPadding(Component.TOP, 4);
		b_pause.getAllStyles().setPadding(Component.BOTTOM, 4);
		b_pause.setCommand(c_pause);
		
		Button b_position = new Button("Position");
		b_position.getUnselectedStyle().setBgTransparency(255);
		b_position.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		b_position.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		b_position.getUnselectedStyle().setBorder(Border.createLineBorder(1, ColorUtil.BLACK));
		b_position.getAllStyles().setPadding(Component.TOP, 4);
		b_position.getAllStyles().setPadding(Component.BOTTOM, 4);
		b_position.setCommand(c_position);
		
		left.add(b_accelerate);
		left.add(b_left);
		right.add(b_brake);
		right.add(b_right);
		bottom.add(b_position);
		bottom.add(b_pause);
		
				
		this.add(BorderLayout.NORTH, top);
		this.add(BorderLayout.EAST, right);
		this.add(BorderLayout.WEST, left);
		this.add(BorderLayout.SOUTH, bottom);
		this.add(BorderLayout.CENTER, center);
		
		this.show();
		gw.setSize(mv.getWidth(), mv.getHeight());
		gw.init();
		gw.createSounds();
		this.revalidate();
		
		UITimer timer = new UITimer(this);
		timeMillis = 20;
		timer.schedule(timeMillis, true, this);	
	}
	
	public boolean isPaused() { return paused; }
	public void setPaused(boolean paused) { this.paused = paused; }
	public boolean isPosition() { return position; }
	public void setPosition(boolean position) { this.position = position; }
	
	@Override
	public void run() {
		if(!paused)
			gw.tick(timeMillis);
	}  
}
