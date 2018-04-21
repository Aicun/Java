import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class PostSend {
	public static void main(String args[]) {
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
		for(int i=0;i<100;i++){
			
			HttpPost httpPost = new HttpPost(
					"http://tutor.sturgeon.mopaas.com/tutor/search");
			List<NameValuePair> formparams = new ArrayList<NameValuePair>();
			formparams.add(new BasicNameValuePair("searchText", "英语"));
			
			UrlEncodedFormEntity entity;
			try {
				entity = new UrlEncodedFormEntity(formparams, "UTF-8");
				httpPost.setEntity(entity);
				
				HttpResponse httpResponse;
				// post请求
				httpResponse = closeableHttpClient.execute(httpPost);
				
				// getEntity()
				HttpEntity httpEntity = httpResponse.getEntity();
				if (httpEntity != null) {
					// 打印响应内容
					System.out.println("response:"+ i);
				}
				// 释放资源
				//closeableHttpClient.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
