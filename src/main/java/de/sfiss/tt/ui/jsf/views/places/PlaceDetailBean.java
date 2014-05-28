package de.sfiss.tt.ui.jsf.views.places;

import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import de.sfiss.tt.model.Place;
import de.sfiss.tt.services.TravelService;
import de.sfiss.tt.services.WikiService.HtmlCleaning;
import de.sfiss.tt.services.WikiServiceImpl;
import de.sfiss.tt.ui.jsf.base.AbstractEntityDetailViewBean;

@Named
@RequestScoped
@Log
public class PlaceDetailBean extends AbstractEntityDetailViewBean<Place>
		implements PlaceDetail {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3825810429504126113L;

	public PlaceDetailBean() {
		log.info("Instantiated " + this);
	}

	@Getter
	@Setter
	private Place place;

	@Getter
	private String wikiHtml;

	@Inject
	private WikiServiceImpl service;

	@Override
	public void update(Place entity) {
		log.info("Update " + entity);
		this.place = entity;
		String page = service.getPage(place.getLocation());
		wikiHtml = service.getArticleHtml(page, 0, HtmlCleaning.P_ONLY);
	}
}
