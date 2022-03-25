package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;

public class CommandHelp extends Command {
	public CommandHelp() {
		super("Help");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Command cOk = new Command("Ok");
		TextArea helpInfo = new TextArea("In addition to on-screen buttons, you can also press these keys to perform actions: \n\n"
								 + "\ta: accelerate \n"
								 + "\tb: brake \n"
								 + "\tl: turn left \n"
								 + "\tr: turn right \n"
								 + "\tf: collide with a food station \n"
								 + "\ts: collide with a spider \n"
								 + "\tt: tick \n");
		helpInfo.setEditable(false);
		Dialog.show("Help", helpInfo, cOk);
	}
}
