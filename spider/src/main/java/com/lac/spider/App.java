package com.lac.spider;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.lac.spider.db.DbManager;
import com.lac.spider.model.Book;
import com.lac.spider.utils.URLFetcher;

/**
 * Hello world!
 *
 */
public class App 
{
	static final Log logger = LogFactory.getLog(App.class);
    public static void main( String[] args )
    {
        HttpClient httpClient = HttpClientBuilder.create().build();
        
        String url="http://search.jd.com/Search?keyword=Python&enc=utf-8&book=y&wq=Python&pvid=33xo9lni.p4a1qb";
        
        try {
			List<Book> bookList = URLFetcher.URLParser(httpClient, url);
			
			for(Book book : bookList) {
	            logger.info("bookID:"+book.getId()+"\t"+"bookPrice:"+book.getPrice()+"\t"+"bookName:"+book.getName());
			}
			//DbManager.executeInsert(bookList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
