package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.Game;
import com.mycompany.a3.GameWorld;
import com.mycompany.a3.IIterator;
import com.mycompany.a3.GameObjects.Fixed;
import com.mycompany.a3.GameObjects.GameObject;

public class CommandPause extends Command {
	private Game g;
	private GameWorld gw;
	
	public CommandPause(Game g, GameWorld gw) {
		super("Pause");
		this.g = g;
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		g.setPaused(!g.isPaused());
		
		if(g.isPaused()) {
			this.setCommandName("Play");
			gw.getBgSound().pause();
		}else {
			this.setCommandName("Pause");
			IIterator elements = gw.getWorldObjects().getIterator();
			while(elements.hasNext()) {  
				GameObject obj = elements.getNext();
				if(obj instanceof Fixed) {
					((Fixed) obj).setSelected(false);
					g.setPosition(false);
				}
			}
			if(gw.isSound())
				gw.getBgSound().play();
		}
	}
}
