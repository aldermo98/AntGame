package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.Game;
import com.mycompany.a3.GameWorld;

public class CommandPosition extends Command{
	private GameWorld gw;
	private Game g;
	
	public CommandPosition(GameWorld gw, Game g) {
		super("Position");
		this.gw = gw;
		this.g = g;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		g.setPosition(!g.isPosition());
	}
	
}
