package de.sfiss.tt.services.wiki;

import java.io.Serializable;
import java.util.Map;

import lombok.Data;
import lombok.ToString;

import org.codehaus.jackson.annotate.JsonIgnore;

@Data
@ToString
public class WikiParseResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3988347245103130653L;

	private Parse parse;

	@JsonIgnore
	public String getHtml() {
		return parse.getText().get("*");
	}

	@Data
	@ToString
	public class Parse {
		private String title;

		private Map<String, String> text;
	}
}
