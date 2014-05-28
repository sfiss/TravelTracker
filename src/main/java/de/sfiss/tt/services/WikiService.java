package de.sfiss.tt.services;

import java.util.List;

public interface WikiService {
	public static enum HtmlCleaning {
		NONE, ALL, P_ONLY;
	}

	String test();

	List<String> getPages(String search, int limit);

	String getPage(String search);

	String getArticleHtml(String page, Integer section, HtmlCleaning clean);
}
