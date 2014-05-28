package de.sfiss.tt.services.web;

import javax.inject.Named;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Named
public class WebUtil {
	@Bean
	public HttpClient getHttpClient() {
		return HttpClientBuilder.create().build();
	}

	@Bean
	public RestTemplate getRestTemplate() {
		RestTemplate restTemplate = new RestTemplate();

		ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(
				getHttpClient());
		restTemplate.setRequestFactory(requestFactory);

		return restTemplate;
	}
}
