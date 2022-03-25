package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;

public class CommandAbout extends Command{
	public CommandAbout() {
		super("About");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Command cOk = new Command("Ok");
		Label aboutInfo = new Label("Created by Alder Moreno.	"
								  + "Course: CSC133");
		Dialog.show("About", aboutInfo, cOk);
	}
}
