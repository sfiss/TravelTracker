package de.sfiss.tt.persistence.hibernate;

import java.util.Collection;

import javax.inject.Inject;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import de.sfiss.tt.model.Example;
import de.sfiss.tt.persistence.ExampleDAO;

@Repository
public class ExampleDAOImpl implements ExampleDAO {

	@Inject
	private SessionFactory sessionFactory;

	@Override
	public void save(Example entity) {
		sessionFactory.getCurrentSession().save(entity);
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Collection<Example> findAll() {
		Collection<Example> result = sessionFactory.getCurrentSession()
				.createQuery("from Example p").list();
		return result;
	}

}
