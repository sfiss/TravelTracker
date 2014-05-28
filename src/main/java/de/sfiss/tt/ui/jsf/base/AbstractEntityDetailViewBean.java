package de.sfiss.tt.ui.jsf.base;

import de.sfiss.tt.model.EntityBase;

@SuppressWarnings("serial")
public abstract class AbstractEntityDetailViewBean<E extends EntityBase>
		implements EntityDetailView<E> {
	protected String parentView;

	@Override
	public String back() {
		return parentView;
	}

	@Override
	public void update(String parentView, E entity) {
		this.parentView = parentView;
		update(entity);
	}

	public abstract void update(E entity);
}
