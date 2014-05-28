package de.sfiss.tt.services.web;

import org.codehaus.jackson.type.TypeReference;
import org.springframework.http.HttpMethod;

public interface WebClientService {
	<T> T request(HttpMethod method, String url, Object argument,
			Class<T> resultClass);

	@Deprecated
	Object request(HttpMethod method, String url, Object argument);

	<T> T request(HttpMethod method, String url, Object argument,
			TypeReference<T> type);
}
