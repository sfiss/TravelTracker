package de.sfiss.tt.persistence.spring;

import java.util.Collection;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;

import de.sfiss.tt.model.Example;
import de.sfiss.tt.persistence.ExampleDAO;

@Repository
public class ExampleDAOImpl implements ExampleDAO {

	@Inject
	private ExampleRepo repo;

	@Override
	public void save(Example entity) {
		repo.save(entity);
	}

	@Override
	public Collection<Example> findAll() {
		return Lists.newArrayList(repo.findAll());
	}
}
