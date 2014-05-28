package de.sfiss.tt.services;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import de.sfiss.tt.model.Place;
import de.sfiss.tt.persistence.PlaceDAO;

@Service
public class TravelServiceImpl implements TravelService {

	@Inject
	private PlaceDAO dao;

	@Override
	public Set<Place> getPlaces() {
		return new HashSet<>(dao.findAll());
	}

	@Override
	public void savePlace(Place place) {
		dao.save(place);
	}

}
