package de.sfiss.tt.persistence.jpa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.ConstraintViolationException;

import lombok.extern.java.Log;
import net.jodah.typetools.TypeResolver;

import org.springframework.transaction.annotation.Transactional;

import de.sfiss.tt.model.EntityBase;
import de.sfiss.tt.persistence.EntityDAO;
import de.sfiss.tt.persistence.exception.DataStorageException;
import de.sfiss.tt.persistence.jpa.util.CriteriaFinder;

@Log
public abstract class EntityDAOBase<E extends EntityBase> implements
		EntityDAO<E> {

	// TODO: add ordering and grouping

	protected Class<E> entityClass;

	@PersistenceContext
	protected EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public EntityDAOBase() {
		Class<?>[] typeArguments = TypeResolver.resolveRawArguments(
				EntityDAOBase.class, getClass());
		this.entityClass = (Class<E>) typeArguments[0];
	}

	@Override
	@Transactional
	public E save(E entity) throws ConstraintViolationException,
			DataStorageException {
		if (entity.getId() == 0) {
			insert(entity);
		} else {
			entity = update(entity);
		}
		return entity;
	}

	@Transactional
	public E insert(E entity) {
		log.info("Insert " + entity);
		entityManager.persist(entity);
		return entity;
	}

	@Transactional
	public E update(E entity) {
		log.info("Update " + entity);
		entity = entityManager.merge(entity);
		return entity;
	}

	@Transactional
	public void delete(E entity) {
		log.info("Remove " + entity);
		entityManager.merge(entity);
		entityManager.remove(entity);
	}

	@Override
	@Transactional(readOnly = true)
	@CriteriaFinder
	public Collection<E> findAll() {
		return null;
	}

	@Transactional(readOnly = true)
	public E find(int id) {
		return entityManager.find(entityClass, id);
	}

	public Object findByCriteria(Map<String, Object> predicates, boolean single) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<E> criteria = builder.createQuery(entityClass);
		Root<E> root = criteria.from(entityClass);
		criteria.select(root);
		List<Predicate> condition = new ArrayList<>(predicates.size());
		for (Map.Entry<String, Object> p : predicates.entrySet()) {
			Object value = p.getValue();
			String field = p.getKey();
			condition.add(builder.equal(root.get(field), value));
		}
		criteria.where(condition.toArray(new Predicate[predicates.size()]));
		TypedQuery<E> query = entityManager.createQuery(criteria);
		if (single) {
			try {
				return query.getSingleResult();
			} catch (NoResultException e) {
				return null;
			}
		}
		return query.getResultList();
	}

	public Object findByQuery(String namedQuery, boolean single) {
		TypedQuery<E> query = entityManager.createNamedQuery(namedQuery,
				entityClass);
		if (single) {
			try {
				return query.getSingleResult();
			} catch (NoResultException e) {
				return null;
			}
		}
		return query.getResultList();
	}

}
