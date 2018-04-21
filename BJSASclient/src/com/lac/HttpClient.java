package com.lac;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public abstract class HttpClient {

	public CloseableHttpClient  client = null;


	public String host = null;

	public String url = "";
	public String html = "";
	public Document doc = null;

	public String charSet = "GBK";


	public SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss");

	public Logger log = Logger.getLogger(this.getClass());

	private String clientFlag = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:27.0) Gecko/20100101 Firefox/27.0";

	public HttpClient() {
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();  
        //HttpClient  
        this.client = httpClientBuilder.build();  
	}

	// 根据url取得内容
	public HttpResponse getHttpPostResponse(String url,List nvps) {

		long start = System.currentTimeMillis(); // 计时
		HttpPost post = new HttpPost(url);
		/*post.addHeader("User-Agent",clientFlag);
		post.addHeader("Referer","http://10.128.192.100:7780/apex/f?p=100:1000");
		post.addHeader("Host","10.128.192.100:7780");
		post.addHeader("Connection","keep-alive");
		post.addHeader("Accept-Language","zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
		post.addHeader("Accept-Encoding","gzip, deflate");*/
		//post.addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		//String rs = "";
		try {
			HttpEntity entity = new UrlEncodedFormEntity(nvps,Consts.UTF_8);
			post.setEntity(entity);
			HttpResponse resp = this.client.execute(post);
			//Header h[]=resp.getHeaders("Location");
			//Header head = h[0];
			//rs = head.getValue();//location
			return resp;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return null;
	}

	public String getHttpResponse(String url) throws MalformedURLException, URISyntaxException {
		long start = System.currentTimeMillis(); // 计时

		HttpGet get = new HttpGet(url);
		get.addHeader("Content-Length","90");
		get.addHeader("Date","20150507090000");
		String rs = "";
		try {
			HttpResponse resp = this.client.execute(get);
			if (resp.getStatusLine().getStatusCode() == 200) {
				rs = EntityUtils.toString(resp.getEntity(), charSet);
				return rs;
			}else{
				throw new Exception("错误,resp.getStatusLine().getStatusCode()="+resp.getStatusLine().getStatusCode());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			HttpResponse resp;
			try {
				resp = this.client.execute(get);
				if (resp.getStatusLine().getStatusCode() == 200) {
					EntityUtils.toString(resp.getEntity());
				}
				rs = EntityUtils.toString(resp.getEntity(), charSet);
				return rs;
			} catch (Exception e1) {
			}

		} finally {
		}
		return null;
	}

	public Document getHttpContext(String url, String info,
			Map<String, String> param) throws Exception {

		StringBuffer sb = new StringBuffer();
		sb.append(url);
		if (param != null && param.size() > 0) {
			sb.append("?");
			Iterator<Entry<String, String>> it = param.entrySet().iterator();
			while (it.hasNext()) {
				Entry<String, String> e = it.next();
				sb.append(e.getKey() + "=");
				if (this.isChinese(e.getValue())) {
					sb.append(URLEncoder.encode(e.getValue(), this.charSet)); // 中文的处理
				} else {
					sb.append(e.getValue());
				}
				sb.append("&");
			}
			sb.deleteCharAt(sb.length() - 1);
		}
		url = sb.toString();

		return this.getHttpContext(url);
	}

	public Document getHttpContext(String url) throws Exception {

		try {
			this.html = this.getHttpResponse(url);

			return Jsoup.parse(html);
		} catch (Exception e) {
			log.error("解析http请求出错," + "(" + url + ")," + e.getMessage());
			throw e;
		}
	}

	public boolean isChinese(String str) {

		for (int i = 0; i < str.length(); i++) {
			boolean b = str.substring(i, i + 1).matches("[\\u4e00-\\u9fbb]+");
			if (b) {
				return b;
			}
		}

		return false;
	}


	public String readFile(File temp) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream(temp)));
		String line = null;
		StringBuffer html_temp = new StringBuffer();
		while ((line = br.readLine()) != null) {
			html_temp.append(line);
		}
		br.close();
		return html_temp.toString();
	}


}

