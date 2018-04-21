
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public abstract class HttpClient {

	public String baseUrl = null;
	public CloseableHttpClient  client = null;

	public File baseDir = null;
	private File picturedir = null;

	private String key = "";
	private String key2 = "";

	public String host = null;

	public String url = "";
	public String html = "";
	public Document doc = null;

	public String charSet = "GBK";

	public boolean isDubug = false;

	public SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss");

	//public Logger log = Logger.getLogger(this.getClass());

	private String clientFlag = "Mozilla/4.0 (compatible; MSIE 8.0; "
			+ "Windows NT 6.1; WOW64; Trident/4.0; SLCC2; .NET CLR 2.0.50727; "
			+ ".NET CLR 3.5.30729; .NET CLR 3.0.30729; "
			+ "Media Center PC 6.0; .NET4.0C; .NET4.0E)";

	public HttpClient() {
		//HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();  
        //HttpClient  
        //this.client = httpClientBuilder.build();  
		this.client = HttpClients.createDefault();
		File base = new File(this.getClass().getResource("/").getFile());
		try {
			this.baseDir = new File(URLDecoder.decode(base.getAbsolutePath(),
					"UTF-8")).getParentFile().getParentFile();
		} catch (UnsupportedEncodingException e) {
			//log.error(e.getMessage());
		}
		this.picturedir = new File(this.baseDir, "images");
	}

	public void setBaseUrl(String baseUrl) throws UnsupportedEncodingException {

		this.baseUrl = baseUrl;
	}

	// 根据url取得内容
	public String getHttpPostResponse(String url, String info,
			Map<String, String> pa) {

		long start = System.currentTimeMillis(); // 计时
		//log.info("开始:" + info + ",key=" + this.key + " (" + this.baseUrl+url + ")");
		HttpPost post = new HttpPost("http://localhost:8080/WebTest/httpClient");
		post.addHeader("Accept", "*/*");
		post.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		post.addHeader("Accept-Language", "zh-CN");
		post.addHeader("User-Agent", clientFlag);
		if (host != null) {
			post.addHeader("Host", host);
		}
		
		
		// Post请求
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("username", "abc"));
		params.add(new BasicNameValuePair("password", "abc"));
		// 设置参数
		try {
			post.setEntity(new UrlEncodedFormEntity(params));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
					// 发送请求
		String rs = "";
		try {
			HttpResponse resp = this.client.execute(post);
			resp.getAllHeaders();
			resp.getStatusLine();
			Header h = resp.getFirstHeader("Set-Cookie");
			String cookie = h.getValue();
			rs = EntityUtils.toString(resp.getEntity());
			//System.out.println("response text is: ###############"+rs);
			return cookie;
		} catch (Exception e) {
			//log.error("http请求失败," + info + "(" + url + ")," + e.getMessage());
		} finally {
			/*log.info("结束:用时:" + (System.currentTimeMillis() - start)
					+ "ms,rs.len=" + rs.length() + "," + info + ",key=" + key
					+ " (" + url + ")");*/
		}
		return null;
	}

	private String getHttpResponse(String c, String info, boolean isRead) throws MalformedURLException, URISyntaxException {
		long start = System.currentTimeMillis();  // 计时
		//log.info("开始" + info + ",key=" + this.key + " (" + baseUrl + url+ ")");

		//URL u = new URL(this.baseUrl + url);
		//URI uri = new URI(u.getProtocol(), u.getHost(), u.getPath(), u.getQuery(), null);
		
		HttpGet get = new HttpGet("http://localhost:8080/WebTest/bjjt.jsp");
		get.addHeader("Accept", "*/*");
		if (host != null) {
			get.addHeader("Host", host);
		}
		// get.addHeader("Content-Type", "text/html; charset=GBK");
		get.addHeader("Accept-Language", "zh-CN");
		//get.addHeader("Cookie",c);
		// get.addHeader("User-Agent", clientFlag);
		String rs = "";
		try {
			HttpResponse resp = this.client.execute(get);
			if (resp.getStatusLine().getStatusCode() == 200) {
				if (isRead) {
					EntityUtils.toString(resp.getEntity());
				}
				rs = EntityUtils.toString(resp.getEntity(), charSet);
				//System.out.println("rsrsrsrs"+rs);
				return rs;
			}else{
				throw new Exception("错误,resp.getStatusLine().getStatusCode()="+resp.getStatusLine().getStatusCode());
			}
			
		} catch (Exception e) {
			//log.error("异常：" + info + ",key=" + this.key + " (" + url + ")"+ e.getMessage());
			e.printStackTrace();
			//log.info("重试，开始：" + info + ",key=" + this.key + " (" + url + ")");
			HttpResponse resp;
			try {
				resp = this.client.execute(get);
				if (resp.getStatusLine().getStatusCode() == 200) {
					if (isRead) {
						EntityUtils.toString(resp.getEntity());
					}
				}
				rs = EntityUtils.toString(resp.getEntity(), charSet);
				return rs;
			} catch (Exception e1) {
				//log.error("重试一次后再异常,开始:" + info + ",key=" + this.key + " ("+ url + ")" + e.getMessage());
			}

		} finally {
			/*log.info("结束:用时:" + (System.currentTimeMillis() - start)
					+ "ms,rs.len=" + rs.length() + "," + info + ",key=" + key
					+ " (" + url + ")");*/
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

		return this.getHttpContext(url, info);
	}

	public Document getHttpContext(String c, String info) throws Exception {

		try {
			this.html = this.getHttpResponse(c, info, false);

			return Jsoup.parse(html);
		} catch (Exception e) {
			//log.error("解析http请求出错," + info + "(" + url + ")," + e.getMessage());
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

	public File getPicture(String url) throws ClientProtocolException,
			IOException {

		try {
			HttpGet get = new HttpGet(this.baseUrl + url);
			get.addHeader("Accept", "*/*");
			if (host != null) {
				get.addHeader("Host", host);
			}
			// get.addHeader("Content-Type", "text/html; charset=GBK");
			get.addHeader("Accept-Language", "zh-CN");
			// get.addHeader("User-Agent", clientFlag);
			HttpResponse resp = this.client.execute(get);

			File picture = new File(this.picturedir, "p"
					+ new Random().nextInt(999) + ".jpg");

			byte[] bs = EntityUtils.toByteArray(resp.getEntity());
			resp.getEntity().getContent().close();
			FileOutputStream fos = new FileOutputStream(picture);
			fos.write(bs);
			fos.flush();
			fos.close();

			return picture;
		} catch (IOException e) {
			//log.error("取得图片错误," + url + "," + e.getMessage());
			throw e;
		}

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

	public String getKey() {
		return key;
	}

	public void setKey(String key) {

		this.key = key;
	}

	public String getKey2() {
		return key2;
	}

	public void setKey2(String key2) {

		this.key2 = key2;
	}

	public abstract Object service() throws Exception;
	
	public Object execute() throws Exception {
		
		try{
			return this.service();
		}finally{
			this.client.getConnectionManager().shutdown();
		}
	}
}
