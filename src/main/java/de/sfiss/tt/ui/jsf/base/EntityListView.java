package de.sfiss.tt.ui.jsf.base;

import de.sfiss.tt.model.EntityBase;

public interface EntityListView<E extends EntityBase> extends DataModelView<E>,
		BaseView {
	void create();

	void edit();

	void delete();

	String detail();

	String save();
}
