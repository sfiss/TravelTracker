package de.sfiss.tt.services.wiki;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;

public class WikiOpenSearchResult extends ArrayList<Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1964075331519131188L;

	@SuppressWarnings("unchecked")
	@JsonIgnore
	public List<String> getPages() {
		return (List<String>) get(1);
	}
}
