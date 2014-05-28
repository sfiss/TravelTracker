package de.sfiss.tt.ui.cmd.commands;

import net.dharwin.common.tools.cli.api.Command;
import net.dharwin.common.tools.cli.api.CommandResult;
import net.dharwin.common.tools.cli.api.annotations.CLICommand;
import net.dharwin.common.tools.cli.api.console.Console;

import com.beust.jcommander.Parameter;

import de.sfiss.tt.services.TravelService;
import de.sfiss.tt.ui.cmd.TravelTrackerContext;

@CLICommand(name = "saveplace")
public class SavePlaceCommand extends Command<TravelTrackerContext> {
	@Parameter(names = { "-n", "--name" }, description = "Name", required = true)
	private String name;

	@Override
	protected CommandResult innerExecute(TravelTrackerContext ctx) {
		TravelService service = ctx.getTravelService();
		// TODO: implement save place
		Console.info("Saved place");
		return CommandResult.OK;
	}

}
