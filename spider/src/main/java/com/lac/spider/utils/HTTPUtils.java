package com.lac.spider.utils;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.message.BasicHttpResponse;

public class HTTPUtils {
	public static HttpResponse getHttpResponse(HttpClient client, String url) {
		HttpGet getMethod = new HttpGet(url);
        HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1,HttpStatus.SC_OK, "OK");
        try {
            response = client.execute(getMethod);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return response;
	}
}
