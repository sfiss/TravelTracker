package de.sfiss.tt.ui.jsf.base;

import de.sfiss.tt.model.EntityBase;

public interface EntityDetailView<E extends EntityBase> extends BaseView {
	String back();
	
	void update(String parentView, E entity);
}
