package com.lac.spider.parser;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.lac.spider.model.Book;

public class BookParser {

	public static List<Book> parseBook(String html) {
		List<Book> bookList = new ArrayList<Book>();
		Document doc = Jsoup.parse(html);
		Elements elements=doc.select("ul[class=gl-warp clearfix]").select("li[class=gl-item]");
        for (Element ele:elements) {
            String bookID=ele.attr("data-sku");
            String bookPrice=ele.select("div[class=p-price]").select("strong").select("i").text();
            String bookName=ele.select("div[class=p-name]").select("em").text();
            Book book=new Book();
            book.setId(bookID);
            book.setName(bookName);
            book.setPrice(bookPrice);
            bookList.add(book);
        }
        return bookList;
	}
}
