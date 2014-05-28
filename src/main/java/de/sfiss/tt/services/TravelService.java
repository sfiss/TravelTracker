package de.sfiss.tt.services;

import java.util.Set;

import de.sfiss.tt.model.Place;

public interface TravelService {
	void savePlace(Place place);

	Set<Place> getPlaces();
}
