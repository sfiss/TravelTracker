package de.sfiss.tt.ui.cmd.commands;

import net.dharwin.common.tools.cli.api.Command;
import net.dharwin.common.tools.cli.api.CommandResult;
import net.dharwin.common.tools.cli.api.annotations.CLICommand;
import net.dharwin.common.tools.cli.api.console.Console;
import de.sfiss.tt.model.Place;
import de.sfiss.tt.services.TravelService;
import de.sfiss.tt.ui.cmd.TravelTrackerContext;

@CLICommand(name = "showplaces")
public class ShowPlacesCommand extends Command<TravelTrackerContext> {

	@Override
	protected CommandResult innerExecute(TravelTrackerContext ctx) {
		Console.info("Places:");
		TravelService service = ctx.getTravelService();
		for(Place place : service.getPlaces()){
			Console.info(" - " + place.getName());
		}
		return CommandResult.OK;
	}

}
