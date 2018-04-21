package group2.hackathon.stimulate;

import group2.hachathon.po.Places;
import group2.hachathon.po.WheelChair;

import java.util.Random;

public class DataStimulation {

	private static double[] amperHour = new double[]{12,20,14,25,15};
	private static double[] voltage = new double[]{48,60,56,80,54};
	private static double[] enginePower = new double[]{150,180,165,200,170};
	private static double[] chairWeight = new double[]{30,20,32,40,45};
	private static double[] batteryPercentage = new double[]{0.12,0.20,0.80,0.50,0.70};
	private static double[] userWeight = new double[]{80,60,48,50,90};
	private static double[] maxSpeed = new double[]{12,15,20,10,8};
	
	private static String[] original = new String[]{
		"16 cedarwoods crescent,Kitchener,ON",
		"655 Fairway Rd S, Kitchener, ON",
		"Fairway Plaza, 385 Fairway Rd S, Kitchener, ON",
		"801 Trillium Industrial Park, Kitchener,ON",
		"158 Greendale Crescent Kitchener,ON"
	};
	
	private static String[] destination = new String[]{
		"3570 King St E, Kitchener, ON",
		"64 Brant Ave, Guelph, ON",
		"Kitchener Market",
		"costco,kitchener",
		"RR 1 Lcd Royal City Mail, Guelph, ON"
	};

	public static WheelChair generateWheelData(){
		Random random = new Random();
		int index = random.nextInt(5);
		
		return new WheelChair(amperHour[index],voltage[index],enginePower[index],
				chairWeight[index],batteryPercentage[index],userWeight[index],maxSpeed[index]);
		
	}
	
	public static Places generatePlacesData(){
		Random random = new Random();
		int index = random.nextInt(5);
		return new Places(original[index],destination[index]);
	}
}
