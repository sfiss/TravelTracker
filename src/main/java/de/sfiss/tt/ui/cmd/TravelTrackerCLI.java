package de.sfiss.tt.ui.cmd;

import net.dharwin.common.tools.cli.api.CLIContext;
import net.dharwin.common.tools.cli.api.CommandLineApplication;
import net.dharwin.common.tools.cli.api.annotations.CLIEntry;
import net.dharwin.common.tools.cli.api.console.Console;
import net.dharwin.common.tools.cli.api.exceptions.CLIInitException;

@CLIEntry
public class TravelTrackerCLI extends
		CommandLineApplication<TravelTrackerContext> {

	public TravelTrackerCLI() throws CLIInitException {
		super();
	}

	@Override
	protected void shutdown() {
		Console.info("Exiting...");
	}

	@Override
	protected CLIContext createContext() {
		return new TravelTrackerContext(this);
	}

}
