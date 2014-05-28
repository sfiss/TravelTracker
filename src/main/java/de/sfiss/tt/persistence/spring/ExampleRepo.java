package de.sfiss.tt.persistence.spring;

import org.springframework.data.repository.CrudRepository;

import de.sfiss.tt.model.Example;

public interface ExampleRepo extends CrudRepository<Example, String> {

}
