import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

public class Login {

	private static DefaultHttpClient hc = new DefaultHttpClient();

	/**
	 * 
	 * @param args
	 */

	public static void main(String[] args) {
		HttpClientParams.setCookiePolicy(hc.getParams(),CookiePolicy.BROWSER_COMPATIBILITY);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("uid", "luac@ultimatech.cn"));
		params.add(new BasicNameValuePair("password", "lac1988"));
		params.add(new BasicNameValuePair("saveUsername", "1"));
		String url = "http://mail.ultimatech.cn/";
		String body = post(url, params);
		//System.out.println(body);
	}

	/**
	 * // Post请求
	 * @param url
	 * @param params
	 * @return
	 */
	public static String post(String url, List<NameValuePair> params) {
		String body = null;
		CookieStore cookieStore = new BasicCookieStore();
		HttpContext httpContext = new BasicHttpContext();
		httpContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
		try {
			// Post请求
			HttpPost httppost = new HttpPost(url);
			// 设置参数
			httppost.setEntity(new UrlEncodedFormEntity(params));
			// 发送请求
			HttpResponse httpresponse = hc.execute(httppost);
			// 获取返回数据
			System.out.println("状态"+httpresponse.getStatusLine() );
			//System.out.println("sessionID"+httpresponse.getFirstHeader("session_token").getValue());
			//System.out.println(cookieStore.getCookies().get(0).getValue());
			cookieStore=(CookieStore) httpContext.getAttribute(ClientContext.COOKIE_STORE);
			System.out.println(cookieStore.getCookies().get(0).getValue());
			HttpEntity entity = httpresponse.getEntity();
			body = EntityUtils.toString(entity);
			System.out.println("内容"+entity.getContent() );

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return body;
	}
}
