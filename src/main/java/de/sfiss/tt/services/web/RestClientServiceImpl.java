package de.sfiss.tt.services.web;

import java.net.URI;
import java.net.URISyntaxException;

import javax.inject.Inject;
import javax.inject.Named;

import lombok.extern.java.Log;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Log
@Named
public class RestClientServiceImpl extends AbstractWebClientServiceImpl
		implements WebClientService {

	@Inject
	protected RestTemplate restTemplate;

	@Override
	public <T> T request(HttpMethod method, String url, Object argument,
			Class<T> resultClass) {
		log.info(String.format("request(%s, %s,%s,%s", method.toString(), url,
				String.valueOf(argument), resultClass.getSimpleName()));
		T response = null;
		URI uri = null;
		try {
			uri = new URI(url);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		switch (method) {
		case GET:
			// TODO: status, exception, etc.
			ResponseEntity<T> responseEntity = restTemplate.getForEntity(uri,
					resultClass);
			response = responseEntity.getBody();
			break;

		default:
			break;
		}
		return response;
	}

}
