package com.lac;

import org.jsoup.nodes.Document;

public class OSBClient extends HttpClient{

	public static void main(String[] args) {
		OSBClient osbClient = new OSBClient();
		try {
			Document doc = osbClient.getHttpContext("http://localhost:8080/WebServiceForOSB/helloHttp");
			//Document doc = osbClient.getHttpContext("http://localhost:8080/ss-app/app/safety/ws/consumer/nearbys/901112115120530/50");
			doc.getAllElements();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
