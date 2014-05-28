package de.sfiss.tt.services.web;

import java.io.IOException;

import javax.inject.Inject;

import lombok.extern.java.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.http.HttpMethod;

@Log
public class HttpClientServiceImpl extends AbstractWebClientServiceImpl
		implements WebClientService {

	@Inject
	protected HttpClient httpClient;

	@Override
	public <T> T request(HttpMethod method, String url, Object argument,
			TypeReference<T> type) {
		log.info(String.format("request(%s, %s,%s,%s", method.toString(), url,
				String.valueOf(argument), type.getType().toString()));
		HttpResponse response = doRequest(method, url, argument);
		ObjectMapper mapper = new ObjectMapper();

		try {
			T value = mapper.readValue(response.getEntity().getContent(), type);
			return value;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	protected HttpResponse doRequest(HttpMethod method, String url,
			Object argument) {
		HttpUriRequest request;

		switch (method) {
		case GET:
			request = new HttpGet(url);
			break;

		default:
			// TODO: exception
			throw new IllegalArgumentException();
		}

		try {
			// TODO: status, exception, etc
			HttpResponse response = httpClient.execute(request);
			return response;
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}
