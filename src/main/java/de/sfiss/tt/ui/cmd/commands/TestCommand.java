package de.sfiss.tt.ui.cmd.commands;

import net.dharwin.common.tools.cli.api.Command;
import net.dharwin.common.tools.cli.api.CommandResult;
import net.dharwin.common.tools.cli.api.annotations.CLICommand;
import net.dharwin.common.tools.cli.api.console.Console;
import de.sfiss.tt.services.WikiService;
import de.sfiss.tt.ui.cmd.TravelTrackerContext;

@CLICommand(name = "test")
public class TestCommand extends Command<TravelTrackerContext> {
	@Override
	protected CommandResult innerExecute(TravelTrackerContext ctx) {
		WikiService service = ctx.getWikiService();
		Console.info("Test = " + service.test());
		return CommandResult.OK;
	}

}
