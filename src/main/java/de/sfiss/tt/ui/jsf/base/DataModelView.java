package de.sfiss.tt.ui.jsf.base;

import javax.faces.model.ListDataModel;

public interface DataModelView<E> {
	void update();
	
	ListDataModel<E> getDataModel();
}
