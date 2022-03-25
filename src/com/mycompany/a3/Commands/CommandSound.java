package com.mycompany.a3.Commands;

import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.Game;
import com.mycompany.a3.GameWorld;

public class CommandSound extends Command{
	private Game g;
	private GameWorld gw;
	
	public CommandSound(Game g, GameWorld gw) {
		super("Sound");
		this.g = g;
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if ( ((CheckBox)e.getComponent()).isSelected()) {
			//getComponent() returns the component //that generated the event
			gw.setSound(true);
			if(!g.isPaused())
				gw.getBgSound().play();
		}else {
			gw.setSound(false);
			gw.getBgSound().pause();
		}
		
		g.getToolbar().closeSideMenu();
		
	}
}
