
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

public class HttpClient2 {
	private CloseableHttpClient client;

	private String clientFlag = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:27.0) Gecko/20100101 Firefox/27.0";
	private String charSet = "UTF-8";
	private Document doc;

	public HttpClient2() {
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		client = httpClientBuilder.build();
	}

	//Post请求，获取返回结果
	public HttpResponse getHttpPostResponse(String url, List nvps)
			throws ClientProtocolException, IOException {
		HttpPost post = new HttpPost(url);
		post.addHeader("User-Agent", clientFlag);
		post.addHeader("Referer",
				"http://10.128.192.100:7780/apex/f?p=100:1000");
		post.addHeader("Host", "10.128.192.100:7780");
		post.addHeader("Connection", "keep-alive");
		post.addHeader("Accept-Language", "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
		post.addHeader("Accept-Encoding", "gzip, deflate");
		post.addHeader("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		HttpEntity entity = new UrlEncodedFormEntity(nvps, Consts.UTF_8);
		post.setEntity(entity);
		HttpResponse resp = this.client.execute(post);
		return resp;
	}
	
	/**
	 * get方式请求url
	 * @param url
	 * @return 
	 * @throws Exception
	 */
	public String getHttpResponse(String url) throws Exception{
		HttpGet get = new HttpGet(url);
		String rs = null;
		HttpResponse resp = this.client.execute(get);
		if (resp.getStatusLine().getStatusCode() == 200) {
				rs = EntityUtils.toString(resp.getEntity(), charSet);
		} else {
			throw new Exception("错误,resp.getStatusLine().getStatusCode()="+ resp.getStatusLine().getStatusCode());
		}
		return rs;
	}

	/**
	 * 解析返回的页面
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public Document getHttpContext(String url) throws Exception {
		String html = null;
		html = getHttpResponse(url);
		return Jsoup.parse(html);
	}

	/**
	 * 用户登录
	 * @return
	 * @throws Exception
	 */
	public String login() throws Exception {
		String html = getHttpResponse("http://10.128.192.100:7780/apex/f?p=100:1000");
		//String html = this.getHttpResponse("http://192.168.1.41:8080/BJSAS/index.jsp");
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

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("p_t04", ""));
		nvps.add(new BasicNameValuePair("p_t03", "1"));
		nvps.add(new BasicNameValuePair("p_t02", "HOST"));
		nvps.add(new BasicNameValuePair("p_t01", "host"));
		nvps.add(new BasicNameValuePair("p_request", "LOGIN"));
		nvps.add(new BasicNameValuePair("p_page_submission_id", submissionId));
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
		HttpResponse resp = getHttpPostResponse("http://10.128.192.100:7780/apex/wwv_flow.accept", nvps);
		//HttpResponse resp = getHttpPostResponse("http://192.168.1.41:8080/apex/wwv_flow.accept", nvps);
		Header h[] = resp.getHeaders("Location");
		Header head = h[0];
		String location = head.getValue();// location
		return location;
		//return "";
	}
	/**
	 * 访问第一个页面
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public Vector<String> firstPage(String url) throws Exception {
		//this.doc = this.getHttpContext("http://192.168.1.41:8080/BJSAS/bjjt.jsp");
		this.doc = this.getHttpContext("http://10.128.192.100:7780/apex/" + url);// 第一个页面
		Element x01 = doc.getElementById("apexir_WORKSHEET_ID");
		String post1 = x01.val();
		Element x02 = doc.getElementById("apexir_REPORT_ID");
		String post2 = x02.val();
		Element pInstance = doc.getElementById("pInstance");
		String p_instance = pInstance.val();
		Elements inputs = doc.getElementsByAttributeValue("title",
				"Enable/Disable");
		Element input = inputs.get(0);
		String post3 = input.attr("onclick");
		int from = post3.indexOf("'");
		int to = post3.lastIndexOf("'");
		post3 = post3.substring(from + 1, to);

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
		HttpEntity entity = resp.getEntity();
		String html = EntityUtils.toString(entity);
		this.doc = Jsoup.parse(html);

		Elements tables = doc.getElementsByTag("table");
		Element e = tables.get(3);
		Elements ee = e.getElementsByTag("tr");
		//List<List<String>> lists = new ArrayList<List<String>>();// 获取第一个页面所有的位置信息，存入lists中
		Vector<String> UEssBCnID = new Vector<String>();
		for (int i = 1; i < ee.size(); i++) {
			Element el = ee.get(i);
			List<Node> node = el.childNodes();
			//List<String> values = new ArrayList<String>();
			Node n = node.get(2);
			String ss;
			if (n.childNodeSize() != 0) {
				ss = n.childNode(0).toString();
				ss.trim();
				UEssBCnID.add(ss);
			}
		}
		return UEssBCnID;
	}
	
	/**
	 * 访问第二个页面，获取页面数据
	 * @param url
	 * @return
	 * @throws Exception
	 */
	/*public SafBjsasUe secondPage(String url) throws Exception {
		SafBjsasUe ue = new SafBjsasUe();
		List<String> ueValues = new ArrayList<String>();
		this.doc = this.getHttpContext(url);// 访问第二个页面，每个表的前两行数据输入到控制台
		//this.doc = this.getHttpContext("http://192.168.1.41:8080/BJSAS/dataDetail.jsp");
		Elements tables = doc.getElementsByTag("table");
		Element UE = tables.get(9);
		Element Registration = tables.get(12);
		Element GPS = tables.get(15);
		Element Position = tables.get(18);
		Element CN_Connection = tables.get(21);
		getData(UE,ueValues);
		getData(Registration,ueValues);
		getData(GPS,ueValues);
		getData(Position,ueValues);
		getData(CN_Connection,ueValues);
		setUeValues(ue,ueValues);
		return ue;

	}

	private void setUeValues(SafBjsasUe ue, List<String> ueValues) {
		ue.setUePermNasId(ueValues.get(0));
		ue.setInitUeId(ueValues.get(1));
		ue.setInitUeIdType(ueValues.get(2));
		ue.setUeClass(ueValues.get(3));
		ue.setLeaseGroup(ueValues.get(4));
		ue.setPermiumGroup(ueValues.get(5));
		ue.setAccessClass(ueValues.get(6));
		ue.setCsSafetySupport(ueValues.get(7));
		ue.setPsSafetySupport(ueValues.get(8));
		ue.setInterRnsHoSupport(ueValues.get(9));
		ue.setPreferredSas(ueValues.get(10));
		ue.setXlSupport(ueValues.get(11));
		ue.setHdrFwdSupport(ueValues.get(12));
		ue.setHdrRetSupport(ueValues.get(13));
		ue.setRegTime(ueValues.get(14));
		ue.setRegMode(ueValues.get(15));
		ue.setRegRef(ueValues.get(16));
		ue.setRegPolicy(ueValues.get(17));
		ue.setRegCause(ueValues.get(18));
		ue.setLaiVersion(ueValues.get(19));
		ue.setEncryptPolicy(ueValues.get(20));
		ue.setTiming(ueValues.get(21));
		ue.setRnsCNo(ueValues.get(22));
		ue.setGpsDatetime(ueValues.get(23)+" "+ueValues.get(24));
		String lat = ueValues.get(25);
		String lng = ueValues.get(26);
		ue.setLatitude(lat);
		ue.setLongitude(lng);
		BigDecimal latbd = coordinate(lat);
		BigDecimal lngbd = coordinate(lng);
		ue.setLatBd(latbd);
		ue.setLngBd(lngbd);
		ue.setFixQuality(ueValues.get(27));
		ue.setSatellites(ueValues.get(28));
		ue.setDilution(ueValues.get(29));
		ue.setLoaTime(ueValues.get(30));
		ue.setLac(ueValues.get(31));
		ue.setSac(ueValues.get(32));
		ue.setRanBcTId(ueValues.get(33));
		ue.setBeamType(ueValues.get(34));
		ue.setSpotId(ueValues.get(35));
		ue.setNarrowSpotId(ueValues.get(36));
		ue.setBcTRuid(ueValues.get(37));
		ue.setBcTRuidHex(ueValues.get(38));
		ue.setElevationBand(ueValues.get(39));
		ue.setSwiftBroadbandSat(ueValues.get(40));
		ue.setMcc(ueValues.get(41));
		ue.setMnc(ueValues.get(42));
		ue.setReportSac(ueValues.get(43));
		ue.setReportPos(ueValues.get(44));
		ue.setPsIuId(ueValues.get(45));
		ue.setCsIuId(ueValues.get(46));
	}*/

	/**
	 * 第二个页面每个表的数据
	 * 
	 * @param e
	 */
	private void getData(Element e,List<String> ueValues) {
		Elements ee = e.getElementsByTag("table");
		Element table = ee.get(1);
		Elements tr = table.getElementsByTag("tr");
		for (int i = 1; i < tr.size()-1; i++) {
			Element value = tr.get(i);
			List<Node> node = value.childNodes();
			for (int j = 0; j < node.size(); j++) {
				Node n = node.get(j);
				if (n.nodeName().equals("td")) {
					String nodeValue = "";
					if(n.childNodeSize()!=0){
						nodeValue = n.childNode(0).toString();
					}
					ueValues.add(nodeValue);
				}
			}
		}
		//System.out.println(e.nodeName() + "###############" + ee.size());
	}

	/**
	 * 将网页上抓取的经纬度转换成百度的经纬度表示方式
	 * @param coordinate
	 * @return
	 */
	private BigDecimal coordinate(String coordinate){
		boolean negative = false;
		if(coordinate.endsWith("S")||coordinate.endsWith("W")){
			negative = true;
		}
		int end = coordinate.length();
		coordinate = coordinate.substring(0,end-1);
		coordinate = coordinate.substring(0,end-1);
		int length = coordinate.length();
		int middle = length-5;
		coordinate = coordinate.substring(0,middle)+"."+coordinate.substring(middle, length);
		if(negative){
			coordinate = "-"+coordinate;
		}
		BigDecimal b = new BigDecimal(coordinate);
		return b;
	}
	
	public CloseableHttpClient getClient() {
		return client;
	}

	public void setClient(CloseableHttpClient client) {
		this.client = client;
	}

	
}
