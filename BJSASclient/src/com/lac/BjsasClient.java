package com.lac;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

public class BjsasClient extends HttpClient implements Runnable{
	
	//static List<String> ids = new ArrayList<String>();
	//static String location;
	
	private int start;
	private int end;
	private static String location;
	private static List<String> ids = new ArrayList<String>();
	
	public BjsasClient(int start,int end){
		this.start = start;
		this.end = end;
	}
	
	public BjsasClient(){}
	
	
	public List<String> service(String url) throws Exception {
		this.doc = this.getHttpContext("http://localhost:8080/BJSAS/bjjt.jsp");
		//this.doc = this.getHttpContext("http://10.128.192.100:7780/apex/" + url);// 第一个页面
		//this.doc = this.getHttpContext("http://10.128.192.100:7780/apex/wwv_flow.show");
		Element x01 = doc.getElementById("apexir_WORKSHEET_ID");
		String post1 = x01.val();
		Element x02 = doc.getElementById("apexir_REPORT_ID");
		String post2 = x02.val();
		Element pInstance = doc.getElementById("pInstance");
		String p_instance = pInstance.val();
		Elements inputs = doc.getElementsByAttributeValue("title", "Enable/Disable");
		Element input = inputs.get(0);
		String post3 = input.attr("onclick");
		int from = post3.indexOf("'");
		int to = post3.lastIndexOf("'");
		post3 = post3.substring(from+1, to);
		
		ArrayList<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("p_flow_id", "100"));
		nvps.add(new BasicNameValuePair("p_flow_step_id", "1000"));
		nvps.add(new BasicNameValuePair("p_instance", p_instance));
		nvps.add(new BasicNameValuePair("p_request", "APXWGT"));
		nvps.add(new BasicNameValuePair("p_widget_action", "FILTER_TOGGLE"));
		nvps.add(new BasicNameValuePair("p_widget_mod", "ACTION"));
		nvps.add(new BasicNameValuePair("p_widget_name", "worksheet"));
		nvps.add(new BasicNameValuePair("x01", post1));
		nvps.add(new BasicNameValuePair("x02", post2));
		nvps.add(new BasicNameValuePair("x03", post3));
		nvps.add(new BasicNameValuePair("x04", "N"));
		HttpResponse resp = this.getHttpPostResponse("http://10.128.192.100:7780/apex/wwv_flow.show", nvps);

		Elements tables = doc.getElementsByTag("table");
		Element e = tables.get(14);
		Elements ee = e.getElementsByTag("tr");
		List<List<String>> lists = new ArrayList<List<String>>();// 获取第一个页面所有的位置信息，存入lists中
		List<String> UEssBCnID = new ArrayList<String>();
		for (int i = 1; i < ee.size(); i++) {
			Element el = ee.get(i);
			List<Node> node = el.childNodes();
			List<String> values = new ArrayList<String>();
			for (int k = 2; k < node.size(); k++) {
				Node n = node.get(k);
				if (n.nodeName().equals("td")) {
					String ss;
					if (n.childNodeSize() != 0) {
						ss = n.childNode(0).toString();
						ss.trim();
						if (k == 2) {
							UEssBCnID.add(ss);
						}
					} else {
						ss = "";
					}
					values.add(ss);
				}
			}
			lists.add(values);
		}
		return UEssBCnID;
	}

	public String login() {
		try {
			String html = this
					.getHttpResponse("http://10.128.192.100:7780/apex/f?p=100:1000");
			Document doc = Jsoup.parse(html);
			Element e1 = doc.getElementById("pInstance");
			Element e2 = doc.getElementById("pPageSubmissionId");
			Element e3 = doc.getElementById("P101_PAGE");
			Element e4 = e3.parent();
			Element e5 = e4.getAllElements().last();
			Element e6 = doc.getElementById("P101_PWD");
			Element e7 = e6.parent();
			Element e8 = e7.getAllElements().last();

			String instance = e1.attr("value");
			String submissionId = e2.attr("value");
			String p_value1 = e5.attr("value");
			String p_value2 = e8.attr("value");

			ArrayList<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("p_t04", ""));
			nvps.add(new BasicNameValuePair("p_t03", "1"));
			nvps.add(new BasicNameValuePair("p_t02", "HOST"));
			nvps.add(new BasicNameValuePair("p_t01", "host"));
			nvps.add(new BasicNameValuePair("p_request", "LOGIN"));
			nvps.add(new BasicNameValuePair("p_page_submission_id",
					submissionId));
			nvps.add(new BasicNameValuePair("p_md5_checksum", ""));
			nvps.add(new BasicNameValuePair("p_instance", instance));
			nvps.add(new BasicNameValuePair("p_flow_step_id", "101"));
			nvps.add(new BasicNameValuePair("p_flow_id", "100"));
			nvps.add(new BasicNameValuePair("p_arg_values", p_value1));
			nvps.add(new BasicNameValuePair("p_arg_values", p_value2));
			nvps.add(new BasicNameValuePair("p_arg_names", "1261203759075060"));
			nvps.add(new BasicNameValuePair("p_arg_names", "1261327770075061"));
			nvps.add(new BasicNameValuePair("p_arg_names", "2183012807550078"));
			nvps.add(new BasicNameValuePair("p_arg_names", "53065518115205654"));
			HttpResponse resp = this.getHttpPostResponse(
					"http://10.128.192.100:7780/apex/wwv_flow.accept", nvps);
			Header h[]=resp.getHeaders("Location");
			Header head = h[0];
			String location = head.getValue();//location
			return location;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	public String getLogoutUrl(String location){
		try {
			this.doc = this.getHttpContext("http://localhost:8080/BJSAS/bjjt.jsp");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		}
		Element div = doc.getElementById("t100NavBar");
		Elements hrefs = div.getElementsByTag("A");
		Element logout = hrefs.get(2);
		String logoutUrl = logout.attr("href");
		System.out.println(logoutUrl);
		return logoutUrl;
	}
	
	
	public void secondPage(String url) throws Exception {
		this.doc = this.getHttpContext(url);// 访问第二个页面，每个表的前两行数据输入到控制台
		Elements tables = doc.getElementsByTag("table");
		Element UE = tables.get(9);
		Element Registration = tables.get(12);
		Element GPS = tables.get(15);
		Element Position = tables.get(18);
		Element CN_Connection = tables.get(21);
		//getData(UE);
		//getData(Registration);
		getData(GPS);
		//getData(Position);
		//getData(CN_Connection);

	}

	/**
	 * 第二个页面每个表的数据
	 * 
	 * @param e
	 */
	private void getData(Element e) {
		Elements ee = e.getElementsByTag("table");
		Element table = ee.get(1);
		Elements tr = table.getElementsByTag("tr");
		for (int i = 1; i < tr.size(); i++) {
			Element value = tr.get(i);
			List<Node> node = value.childNodes();
			for (int j = 0; j < node.size(); j++) {
				Node n = node.get(j);
				//if (n.nodeName().equals("td") || n.nodeName().equals("th")) {
				if (n.nodeName().equals("td")) {
					String nodeValue = "";
					if(n.childNodeSize()!=0){
						nodeValue = n.childNode(0).toString();
					}
					System.out.println(nodeValue);
				}
			}
		}
		//System.out.println(e.nodeName() + "###############" + ee.size());
	}

	private static String replaceIndex(int index, String res, String str) {
		return res.substring(0, index) + str + res.substring(index + 1);
	}

	public static void main(String args[]) throws Exception {
		long starttime = System.currentTimeMillis(); // 计时
		BjsasClient client = new BjsasClient();
		/*location = client.login();
		if (location != null) {
			try {
				//ids = client.service(location);
				int size = ids.size();
				int interval = size/10;
				int start;
				int end;
				for (int i = 0; i < 1; i++) {
					String id = ids.get(i);
					location = BjsasClient.replaceIndex(11, location, "1");
					String url = "http://10.128.192.100:7780/apex/" + location
							+ "::NO::P1001_BCNID:" + id;
					client.secondPage("http://localhost:8080/BJSAS/dataDetail.jsp");
				}
				for(int i=0;i<10;i++){
					start = i*interval+1;
					if(i!=9){
						end = (i+1)*interval;
					}else{
						end = size;
					}
					BjsasClient c = new BjsasClient(start,end);
					Thread t = new Thread(c);
					t.start();
				}
			} catch (Exception e) {
			}
		}*/
/*		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("p_t02", "HOST"));
		nvps.add(new BasicNameValuePair("p_t01", "bjoper"));
		for(int i=0;i<100;i++){
			HttpResponse resp = client.getHttpPostResponse("http://localhost:8080/BJSAS/wwv_flow.accept", nvps);
			System.out.println("################第"+i+"次");
		}

		long endtime = System.currentTimeMillis(); // 计时
		System.out.println("总共用时："+(endtime-starttime));*/
		client.getLogoutUrl("");
	}

	@Override
	public void run() {	
		for(int i=start;i<=end;i++){
			String id = ids.get(i-1);
			String location2 = BjsasClient.replaceIndex(11, location, "1");
			String url = "http://10.128.192.100:7780/apex/" + location2
					+ "::NO::P1001_BCNID:" + id;
			try {
				secondPage(url);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
