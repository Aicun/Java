package group2.hachathon.test;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import group2.hackathon.BatteryDistance;
import group2.hackathon.PlaceDistance;

public class DistanceTest {

	public static void main(String[] args) {
		BatteryDistance c = new BatteryDistance();
		PlaceDistance dis = new PlaceDistance();
		double battery = batteryDistance(c);
		double location = locationDistance(dis);
		System.out.println("the data your wheelchair is "+ c.getChair());
		System.out.println("the current temperature data is "+ c.getTemp());
		System.out.println("the original and destination is: "+ dis.getPlaces());
		System.out.println("the distance your wheelchair can cover is "+ battery);
		System.out.println("The distance between that two place is "+location);
		boolean ok = battery - location >0 ? true: false;
		System.out.println(ok?"Your wheelchair can take you there":"Your wheelchair cannot take you there");
	}
	
	public static double batteryDistance(BatteryDistance c){
		double distance = 0;
		try {
			distance = c.calculateDistance("Kitchener", "ca");
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return distance;
	}
	
	public static double locationDistance(PlaceDistance dis){
		
		double distance = 0;
		try {
			distance = dis.locationDistance();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return distance;
	}
}
