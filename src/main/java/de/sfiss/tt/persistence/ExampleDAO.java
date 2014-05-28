package de.sfiss.tt.persistence;

import java.util.Collection;

import de.sfiss.tt.model.Example;


public interface ExampleDAO {
	void save(Example o);
	
	Collection<Example> findAll();
}
