package de.sfiss.tt.persistence.jpa;

import javax.inject.Named;

import de.sfiss.tt.model.Place;
import de.sfiss.tt.persistence.PlaceDAO;

@Named
public class PlaceDAOImpl extends EntityDAOBase<Place> implements PlaceDAO {
	
}
