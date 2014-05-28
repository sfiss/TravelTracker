package de.sfiss.tt.ui.cmd;

import lombok.extern.java.Log;
import net.dharwin.common.tools.cli.api.EntryPoint;
import net.dharwin.common.tools.cli.api.exceptions.CLIInitException;

@Log
public class CLIStarter extends EntryPoint {

	public static void main(String[] args) {
		TravelTrackerCLI cmd;
		try {
			log.info("Starting CLI...");
			cmd = new TravelTrackerCLI();
			cmd.start();
		} catch (CLIInitException e) {
			log.severe("Error starting CLI");
			e.printStackTrace();
		}
	}

}
