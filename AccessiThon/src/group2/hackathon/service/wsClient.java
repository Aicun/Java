package group2.hackathon.service;

import group2.hachathon.po.Temperature;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.DecimalFormat;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

enum HistoryType {
	UNKNOWN, TICK, HOUR, DAY
}

public class wsClient {
	static private final String APPID = "87f49b0f8484f94607052f5056b87238";

	private String baseUrl;

	private HttpClient httpClient;

	public wsClient(String baseUrl) {
		this.baseUrl = baseUrl;
		this.httpClient = new DefaultHttpClient();
	}

	public wsClient(HttpClient httpClient) {
		if (httpClient == null)
			throw new IllegalArgumentException(
					"Can't construct a OwmClient with a null HttpClient");
		this.httpClient = httpClient;
	}
	
	public double getDistance(String original, String destination)
			throws JSONException, ClientProtocolException, IOException,
			URISyntaxException {
		JSONObject json = doQueryDistance(original, destination);
		JSONArray jsonArray= json.getJSONArray("rows");
		json = (JSONObject) jsonArray.get(0);
		jsonArray= json.getJSONArray("elements");
		json = (JSONObject) jsonArray.get(0);
		json = json.getJSONObject("distance");
		String dis = json.getString("text");
		double distance=Double.valueOf(dis.substring(0, dis.length()-2).trim());
		return distance;
	}

	public Temperature getCurrentTemperature(String location)
			throws JSONException, IOException, URISyntaxException {
		JSONObject json = doQueryWeather(location);
		json = (JSONObject) json.get("main");
		float temp = (float) json.getDouble("temp");
		float pressure = (float) json.getDouble("pressure");
		float humidity = (float) json.getDouble("humidity");
		float min = (float) json.getDouble("temp_min");
		float max = (float) json.getDouble("temp_max");
		Temperature temperature = new Temperature(temp, min, max,pressure, humidity
				);
		return temperature;
	}
	
	private JSONObject doQueryDistance(String original,String destination) throws ClientProtocolException, IOException, JSONException, URISyntaxException{
		String url = this.baseUrl + original + "&destinations="+destination;
		url = getUri(url);
		return doQuery(url);
	}

	private JSONObject doQueryWeather(String location) throws JSONException,
			IOException, URISyntaxException {
		String url = this.baseUrl + location + "&mode=json&units=metric"
				+ "&appid=" + APPID;
		url = getUri(url);
		return doQuery(url);
	}
	
	private JSONObject doQuery(String url) throws ClientProtocolException, IOException, JSONException{
		String responseBody = null;
		HttpGet httpget = new HttpGet(url);

		HttpResponse response = this.httpClient.execute(httpget);
		InputStream contentStream = null;
		try {
			StatusLine statusLine = response.getStatusLine();
			if (statusLine == null) {
				throw new IOException(
						String.format("Unable to get a response from OWM server"));
			}
			int statusCode = statusLine.getStatusCode();
			if (statusCode < 200 && statusCode >= 300) {
				throw new IOException(String.format(
						"OWM server responded with status code %d: %s",
						statusCode, statusLine));
			}
			/* Read the response content */
			HttpEntity responseEntity = response.getEntity();
			contentStream = responseEntity.getContent();
			Reader isReader = new InputStreamReader(contentStream);
			int contentSize = (int) responseEntity.getContentLength();
			if (contentSize < 0)
				contentSize = 8 * 1024;
			StringWriter strWriter = new StringWriter(contentSize);
			char[] buffer = new char[8 * 1024];
			int n = 0;
			while ((n = isReader.read(buffer)) != -1) {
				strWriter.write(buffer, 0, n);
			}
			responseBody = strWriter.toString();
			contentStream.close();
		} catch (IOException e) {
			throw e;
		} catch (RuntimeException re) {
			httpget.abort();
			throw re;
		} finally {
			if (contentStream != null)
				contentStream.close();
		}
		return new JSONObject(responseBody);
	}
	
	private String getUri(String urlStr) throws MalformedURLException, URISyntaxException{
		URL url = new URL(urlStr);
	    String nullFragment = null;
	    URI uri = new URI(url.getProtocol(), url.getHost(), url.getPath(), url.getQuery(), nullFragment);
	    return uri.toString();
	}
}
