package de.sfiss.tt.ui.cmd.base;

import java.io.File;

import de.sfiss.tt.ui.cmd.TravelTrackerCLI;

import net.dharwin.common.tools.cli.api.CLIContext;

public class CLICustomContext extends CLIContext {

	public CLICustomContext(TravelTrackerCLI app) {
		super(app);
	}

	@Override
	protected String getEmbeddedPropertiesFilename() {
		return "cli/cmd.properties";
	}

	@Override
	protected File getExternalPropertiesFile() {
		return new File("src/main/resources/cli/cmd.properties");
	}

}
