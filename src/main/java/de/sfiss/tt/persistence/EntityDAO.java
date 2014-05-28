package de.sfiss.tt.persistence;

import java.util.Collection;

public interface EntityDAO<E> {
	E save(E entity);
	
	E insert(E e);

	void delete(E entity);

	Collection<E> findAll();
}
