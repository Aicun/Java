import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

public class ChinaSoftware extends HttpClient {
	
	@Override
	public Object service() throws Exception {

		//this.setBaseUrl("http://localhost:8080/WebTest/welcome.jsp");
		//this.charSet="gb2312";
		
		//this.url="GGsearch.asp?txtSearch="+URLEncoder.encode(this.getKey(),this.charSet);
		
		this.doc=this.getHttpContext("", "测试"+this.getKey());
		Element e = doc.getElementById("3244409746083481");
		Elements ee = e.getElementsByTag("tr");
		List<List<String>> lists = new ArrayList<List<String>>();
		for(int i=1;i<ee.size();i++){
			Element el = ee.get(i);
			List<Node> node = el.childNodes();
			List<String> values = new ArrayList<String>();
			for(int k=5;k<node.size();k++){
				Node n = node.get(k);
				if(n.nodeName().equals("td")){
					String ss;
					if(n.childNodeSize()!=0){
						ss = n.childNode(0).toString();
						ss.trim();
					}else{
						ss="";
					}
					values.add(ss);
				}
			}
			lists.add(values);
		}
		
		/*Element el = ee.get(i);
		Elements ele = el.getAllElements();
		for(Element aaa:ele){
			Elements bbb = aaa.getElementsByTag("td");
			for(int j=2;j<bbb.size();j++){
				Element v = bbb.get(j);
				List<Node> node = v.childNodes();
				for(int k=0;k<node.size();k++){
					Node nn = node.get(k);
					String nnv = nn.toString();
				}
			}
		}*/
		
		System.out.println("##############"+lists.size());
	
		return null;
	}
	
	public String service2()throws Exception{
		this.setBaseUrl("http://localhost:8080/WebTest/");
		this.charSet="gb2312";
		
		//this.url="GGsearch.asp?txtSearch="+URLEncoder.encode(this.getKey(),this.charSet);
		Map<String,String> map = new HashMap<String,String>();
		map.put("username", "abc");
		map.put("password", "abc");
		String cookie = this.getHttpPostResponse(this.url,"",map);
		System.out.println(cookie);
		return cookie;
	}
	
	public static void main(String args[]){
		try {
			ChinaSoftware ss= new ChinaSoftware();
			//String cookie = ss.service2();
			ss.service();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
