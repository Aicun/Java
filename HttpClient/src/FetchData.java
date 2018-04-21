
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CountDownLatch;

public class FetchData{
	
	private HttpClient2 httpClient;
	
	private int start;
	private int end;
	private CountDownLatch threadsSignal; 
	
	private static int THREAD_NUM=10;//�߳���
	
	private static Vector<String> phoneNos;//�ӵ�һ��ҳ���ȡ�ĵ绰����
	private String location;//��ת��ַ
	
	public FetchData(int start,int end,CountDownLatch threadsSignal,HttpClient2 httpClient){
		this.start = start;
		this.end = end;
		this.threadsSignal=threadsSignal;
		this.httpClient = httpClient;
	}
	
	public FetchData(){}
	
	
	//����ҳ����ת
	private static String replaceIndex(int index, String res, String str) {
		return res.substring(0, index) + str + res.substring(index + 1);
	}
	
	public void fetchData() throws Exception{
		httpClient = new HttpClient2();

		location = httpClient.login();
		phoneNos = new Vector<String>();
		if(location!=null){
			phoneNos = httpClient.firstPage(location);
		}
		System.out.println("finished");
	}
	
	public static void main(String args[]){
		FetchData f = new FetchData();
		try {
			f.fetchData();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
