package integration.persistence;

import javax.inject.Inject;

import org.junit.Test;

import de.sfiss.tt.model.Place;
import de.sfiss.tt.persistence.PlaceDAO;

public class PlaceDAOTest extends PersistenceTest {

	@Inject
	PlaceDAO dao;

	@Test
	public void testSimpleSave() {
		Place place = new Place("", "Bla");
		dao.save(place);
	}
	
	@Test
	public void testSimpleFindAll() {
		dao.findAll();
	}

}
