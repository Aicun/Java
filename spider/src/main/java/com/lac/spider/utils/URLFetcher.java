package com.lac.spider.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.util.EntityUtils;

import com.lac.spider.model.Book;
import com.lac.spider.parser.BookParser;

public class URLFetcher {
	public static List<Book> URLParser (HttpClient client, String url) throws Exception {
        List<Book> bookData = new ArrayList<Book>();
        HttpResponse response = HTTPUtils.getHttpResponse(client, url);      
        int StatusCode = response.getStatusLine().getStatusCode();
        if(StatusCode == 200){
            String entity = EntityUtils.toString (response.getEntity(),"utf-8");    
            bookData = BookParser.parseBook(entity);
            EntityUtils.consume(response.getEntity());
        }else {
            EntityUtils.consume(response.getEntity());
        }
        return bookData;
    }
}
