package com.lac;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class McnUserInfo {
		
	public String  getMCNUserInfoJson(String urlStr) throws IOException{
		URL url = new URL(urlStr);
		URLConnection urlConnection = url.openConnection();
		HttpURLConnection httpUrlConnection  = (HttpURLConnection)urlConnection;
		httpUrlConnection.setDoOutput(true); 
		httpUrlConnection.setDoInput(true); 
		httpUrlConnection.setUseCaches(false); 
		httpUrlConnection.setRequestProperty("Content-type", "application/json;charset=utf-8");
		httpUrlConnection.setRequestProperty("Accept", "application/json");
		httpUrlConnection.setRequestMethod("POST");
		httpUrlConnection.setConnectTimeout(30000);
		httpUrlConnection.setReadTimeout(30000);
		OutputStream os = httpUrlConnection.getOutputStream();
		String json = getMCNUserInfoJson("10000000131");
		os.write(json.getBytes());
		InputStream response = httpUrlConnection.getInputStream();
		String line = "";
		BufferedReader in = new BufferedReader(new InputStreamReader(response));
		while(((line = in.readLine()) != null)){
			line +=line;
		}
		os.close();
		response.close();
		in.close();
		return line;
	}
	
	public String buildRequestPara(String svrNum){
		String json = "{\"additional\" : {\"channel\":\" example\","
				+ "\"secretKey\":\"96E79218965EB72C92A549DD5A330112\"},"
	  		+ "\"serviceCode\" : \"so.getUserInfo\","
	  		+ "\"params\" : [\""+svrNum+"\"]}";
		return json;
	}
	
	public static void main(String args[]){
		McnUserInfo info = new McnUserInfo();
		try {
			String userinfo = info.getMCNUserInfoJson("");
			System.out.println(userinfo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
