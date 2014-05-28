package de.sfiss.tt.services.web;

import java.lang.reflect.Type;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.http.HttpMethod;

public abstract class AbstractWebClientServiceImpl implements WebClientService {
	@Override
	@Deprecated
	public Object request(HttpMethod method, String url, Object argument) {
		return request(method, url, argument, Object.class);
	}

	@Override
	public <T> T request(HttpMethod method, String url, Object argument,
			final Class<T> resultClass) {
		return request(method, url, argument, new TypeReference<T>() {
			@Override
			public Type getType() {
				return resultClass;
			}
		});
	}

	@Override
	public <T> T request(HttpMethod method, String url, Object argument,
			TypeReference<T> type) {
		ObjectMapper mapper = new ObjectMapper();
		@SuppressWarnings("unchecked")
		Class<T> returnClass = (Class<T>) mapper.getTypeFactory()
				.constructType(type).getRawClass();
		return request(method, url, argument, returnClass);
	}
}
