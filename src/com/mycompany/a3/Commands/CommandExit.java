package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class CommandExit extends Command{
	private GameWorld gw;
	
	public CommandExit(GameWorld gw) {
		super("Exit");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		gw.exit();
		
		Command c_ok = new Command("Ok");
		Command c_cancel = new Command("Cancel");
		Command [] c_args = new Command[] {c_cancel, c_ok};
		Label exitPrompt = new Label("Are you sure you want to quit?");
		Command c = Dialog.show("Exit", exitPrompt, c_args);
		
		if(c == c_ok)
			gw.yes();
		else if(c == c_cancel)
			gw.no();
	}
}
