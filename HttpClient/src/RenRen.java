import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class RenRen {

	// The configuration items

	private static String userName = "abc";

	private static String password = "abc";

	// http://weibo.com/2902478835/profile?topnav=1&wvr=3.6

	// private static String redirectURL =
	// "http://blog.renren.com/blog/304317577/449470467";

	//private static String redirectURL = "http://weibo.com";

	// Don't change the following URL

	// http://weibo.com

	 private static String renRenLoginURL = "http://www.renren.com/PLogin.do";

	//private static String renRenLoginURL = "http://weibo.com";
	//private static String renRenLoginURL = "http://localhost:8080/WebTest/";

	// The HttpClient is used in one session

	private HttpResponse response;

	private CloseableHttpClient httpclient = HttpClients.createDefault();

	private boolean login() {
		HttpPost httpost = new HttpPost("http://localhost:8080/WebTest/");
		// All the parameters post to the web site
		ArrayList<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("username", userName));
		nvps.add(new BasicNameValuePair("password", password));
		nvps.add(new BasicNameValuePair("submit", "Submit"));
		try {
			HttpEntity entity = new UrlEncodedFormEntity(nvps,Consts.UTF_8);
			httpost.setEntity(entity);
			System.out.println("fuck"+httpost.getRequestLine());
			response = httpclient.execute(httpost);
			
			HttpEntity respEntity = response.getEntity();
			  //响应状态
            System.out.println("Login form get: " + response.getStatusLine());
            //EntityUtils.consume(entity);
            //获取响应内容
            System.out.println(EntityUtils.toString(respEntity,Charset.forName("utf-8")));
            //销毁
            EntityUtils.consume(respEntity);


		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			httpost.abort();
		}
		return true;
	}

	private String getRedirectLocation() {

		Header locationHeader = response.getFirstHeader("Location");

		if (locationHeader == null) {
			return null;
		}
		return locationHeader.getValue();
	}

	private String getText(String redirectLocation) {

		HttpGet httpget = new HttpGet(redirectLocation);

		// Create a response handler

		ResponseHandler<String> responseHandler = new BasicResponseHandler();

		String responseBody = "";

		try {

			responseBody = httpclient.execute(httpget, responseHandler);

		} catch (Exception e) {

			e.printStackTrace();

			responseBody = null;

			System.out.print("123");

		} finally {

			httpget.abort();

			httpclient.getConnectionManager().shutdown();

		}

		return responseBody;

	}

	public void printText() {

		if (login()) {

			String redirectLocation = getRedirectLocation();

			if (redirectLocation != null) {

				System.out.println(getText(redirectLocation));
			}
		}
	}

	public static void main(String[] args) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		try{
		//post请求的url地址
		HttpPost httpPost = new HttpPost("http://localhost:8080/WebTest/httpClient");
		List <NameValuePair> nvps = new ArrayList <NameValuePair>();
		//传递2个参数  name和password
		nvps.add(new BasicNameValuePair("username","王五"));
		nvps.add(new BasicNameValuePair("password","12345"));
		//转码  封装成请求实体
		HttpEntity reqEntity = new UrlEncodedFormEntity(nvps,Consts.UTF_8);

		httpPost.setEntity(reqEntity);

		System.out.println("请求url地址"+httpPost.getURI());
		//提交表单请求   response是表单的响应
		CloseableHttpResponse response = httpClient.execute(httpPost);
		            try {
		                HttpEntity respEntity = response.getEntity();
		                //响应状态
		                System.out.println("Login form get: " + response.getStatusLine());
		                //EntityUtils.consume(entity);
		                //获取响应内容
		                System.out.println(EntityUtils.toString(respEntity,Charset.forName("utf-8")));
		                //销毁
		                EntityUtils.consume(respEntity);
		            } finally {
		                response.close();
		            }
		}finally{
		httpClient.close();
		}


	}
}
