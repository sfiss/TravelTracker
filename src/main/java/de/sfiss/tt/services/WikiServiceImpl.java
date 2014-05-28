package de.sfiss.tt.services;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import lombok.extern.java.Log;

import org.codehaus.jackson.type.TypeReference;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.HttpMethod;

import de.sfiss.tt.services.web.WebClientService;
import de.sfiss.tt.services.wiki.WikiOpenSearchResult;
import de.sfiss.tt.services.wiki.WikiParseResult;

@Named
@Log
public class WikiServiceImpl implements WikiService {

	private final String wiki_base = "http://en.wikipedia.org";
	private final String wiki_api = wiki_base
			+ "/w/api.php?format=json&action={action}";

	private String getURL(String action, Map<String, Object> params)
			throws UnsupportedEncodingException {
		StringBuilder sb = new StringBuilder(wiki_api.replace("{action}",
				action));
		for (Map.Entry<String, Object> param : params.entrySet()) {
			sb.append("&");
			sb.append(URLEncoder.encode(param.getKey(), "UTF-8"));
			sb.append("=");
			sb.append(URLEncoder.encode(String.valueOf(param.getValue()),
					"UTF-8"));
		}
		return sb.toString();
	}

	private String clean(String html, HtmlCleaning clean) {
		StringBuilder cleanHtml = new StringBuilder();
		Document doc = Jsoup.parseBodyFragment(html, wiki_base);

		Elements urls = doc.select("a[href]");
		for (Element urlElement : urls) {
			urlElement.attr("href", urlElement.absUrl("href"));
			urlElement.attr("target", "_blank");
		}

		doc.select("sup").remove();

		doc.select(".error").remove();

		doc.select("*").attr("class", "");
		switch (clean) {
		case ALL:
			cleanHtml.append(doc.body().html());
			break;
		case P_ONLY:

			Elements paragraphs = doc.select("p");
			for (Element p : paragraphs) {
				cleanHtml.append(p.outerHtml() + "\n");
			}
			break;
		case NONE:
		default:
			cleanHtml.append(html);
		}
		return cleanHtml.toString();
	}

	private String denormalizePage(String page) {
		return page.replace(" ", "_");
	}

	@Inject
	private WebClientService webClient;

	public String test() {
		return getArticleHtml(getPage("Amherst, MA"), 0, HtmlCleaning.P_ONLY);
	}

	@Override
	public List<String> getPages(String search, int limit) {
		log.info("GetPages " + search);
		List<String> result;

		HttpMethod method = HttpMethod.GET;
		Object argument = null;

		String url = "";
		try {
			Map<String, Object> params = new HashMap<>();
			params.put("search", search);
			params.put("limit", limit);
			url = getURL("opensearch", params);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		WikiOpenSearchResult response = webClient.request(method, url,
				argument, new TypeReference<WikiOpenSearchResult>() {
				});
		result = response.getPages();
		log.info("GetPages returned " + result.size());
		return result;
	}

	@Override
	public String getPage(String search) {
		log.info("GetPage " + search);
		List<String> pages = getPages(search, 1);
		while (pages.size() < 1 && search.contains(",")) {
			search = search.substring(0, search.lastIndexOf(","));
			pages = getPages(search, 1);
		}

		if (pages.size() < 1) {
			// TODO
			throw new RuntimeException();
		}

		log.info("GetPage returned " + pages.get(0));
		return denormalizePage(pages.get(0));
	}

	@Override
	public String getArticleHtml(String page, Integer section,
			HtmlCleaning clean) {
		log.info("GetArticleHtml " + page);
		String html;

		HttpMethod method = HttpMethod.GET;
		Object argument = null;

		String url = "";
		try {
			Map<String, Object> params = new HashMap<>();
			params.put("page", page);
			params.put("prop", "text");
			if (section != null) {
				params.put("section", section);
			}
			url = getURL("parse", params);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		WikiParseResult response = webClient.request(method, url, argument,
				WikiParseResult.class);
		html = response.getHtml();
		log.info("GetArticleHtml returned");
		return clean(html, clean);
	}
}
