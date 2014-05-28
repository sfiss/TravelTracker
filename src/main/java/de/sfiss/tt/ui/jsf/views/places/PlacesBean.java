package de.sfiss.tt.ui.jsf.views.places;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import de.sfiss.tt.model.Place;
import de.sfiss.tt.services.TravelService;
import de.sfiss.tt.ui.jsf.base.AbstractEntityListViewBean;

@Named
@ViewScoped
@Scope("view")
@Log
public class PlacesBean extends AbstractEntityListViewBean<Place> implements
		Places {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2770819429447919084L;

	@Getter
	@Setter
	private Place place = new Place();

	@Inject
	private TravelService service;

	@Inject
	private PlaceDetail detailBean;

	public PlacesBean() {
		log.info("Instantiated " + this);
	}

	@Override
	public void create() {
		place = new Place();
		log.info("Create " + place);
	}

	@Override
	public void edit() {
		place = dataModel.getRowData();
		log.info("Edit " + place);
	}

	@Override
	public void delete() {
		place = dataModel.getRowData();
		// TODO: remove place
		log.info("Delete " + place);
		update();
	}

	@Override
	public String save() {
		log.info("Save " + place);
		service.savePlace(place);
		update();
		return null;
	}

	@Override
	public String detail() {
		place = dataModel.getRowData();
		detailBean.update("places", place);
		return "placeDetail";
	}

	@Override
	public void update() {
		log.info("Update");
		List<Place> list = new ArrayList<>();
		list.addAll(service.getPlaces());
		Collections.sort(list, new Comparator<Place>() {

			@Override
			public int compare(Place o1, Place o2) {
				return o1.getShortName().compareTo(o2.getShortName());
			}
		});
		dataModel = new ListDataModel<>(list);
	}

	public String getPopupHeader() {
		return place.getId() == 0 ? "Create Place" : place.getShortName();
	}

}
