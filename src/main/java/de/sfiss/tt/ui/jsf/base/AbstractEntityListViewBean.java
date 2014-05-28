package de.sfiss.tt.ui.jsf.base;

import javax.faces.model.ListDataModel;

import de.sfiss.tt.model.EntityBase;

@SuppressWarnings("serial")
public abstract class AbstractEntityListViewBean<E extends EntityBase> implements
		EntityListView<E> {
	protected ListDataModel<E> dataModel;
	
	@Override
	public ListDataModel<E> getDataModel() {
		if (dataModel == null) {
			update();
		}
		return dataModel;
	}
}
