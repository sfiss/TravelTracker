package de.sfiss.tt.ui.cmd;

import javax.inject.Inject;

import de.sfiss.tt.services.TravelService;
import de.sfiss.tt.services.WikiService;
import de.sfiss.tt.ui.cmd.base.CLICustomContext;

public class TravelTrackerContext extends CLICustomContext {

	@Inject
	private TravelService travelService;

	@Inject
	private WikiService wikiService;

	public TravelTrackerContext(TravelTrackerCLI app) {
		super(app);
		TravelTrackerCLIApplication.ctx.getAutowireCapableBeanFactory()
				.autowireBean(this);
	}

	public TravelService getTravelService() {
		return travelService;
	}

	public WikiService getWikiService() {
		return wikiService;
	}
}
