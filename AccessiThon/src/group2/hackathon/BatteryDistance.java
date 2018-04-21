package group2.hackathon;

import group2.hachathon.po.Temperature;
import group2.hachathon.po.WheelChair;
import group2.hackathon.service.wsClient;
import group2.hackathon.stimulate.DataStimulation;

import java.io.IOException;
import java.net.URISyntaxException;

import org.json.JSONException;

public class BatteryDistance {

	private WheelChair chair;
	private wsClient client;
	private double[] tempPercentList;
	private Temperature temp;

	public BatteryDistance() {
		chair = DataStimulation.generateWheelData();
		client = new wsClient(
				"http://api.openweathermap.org/data/2.5/weather?q=");
		tempPercentList = new double[] { 0, 0.4, 0.6, 0.7, 0.8, 1.0, 1.1, 1.2,
				0 };
	}

	public WheelChair getChair() {
		return chair;
	}

	public void setChair(WheelChair chair) {
		this.chair = chair;
	}

	public Temperature getTemp() {
		return temp;
	}

	public void setTemp(Temperature temp) {
		this.temp = temp;
	}

	public double calculateDistance(String city, String country)
			throws JSONException, IOException, URISyntaxException {
		double speed = chair.getMaxSpeed();
		double time = timeLast();
		double tempAffect = tempAffect(city, country);
		return speed * time * tempAffect;
	}

	private double batteryCapacity() {
		double ampe = chair.getAmpeHour();
		double voltage = chair.getVoltage();
		double percentage = chair.getBatteryPercentage();
		return ampe * voltage * percentage;
	}

	private double timeLast() {
		double capacity = batteryCapacity();
		double power = chair.getEnginePower();
		return capacity / power;
	}

	private double tempAffect(String city, String country)
			throws JSONException, IOException, URISyntaxException {
		String location = city + "," + country;
		temp = client.getCurrentTemperature(location);
		double temperature = temp.getTemp();
		double tempPercent = getPercentage(temperature);
		return tempPercent;
	}

	private double getPercentage(double temperature) {
		int index = -1;
		if (temperature <= -20) {
			index = 0;
		} else if (temperature > -20 && temperature <= -10) {
			index = 1;
		} else if (temperature > -10 && temperature <= 0) {
			index = 2;
		} else if (temperature > 0 && temperature <= 10) {
			index = 3;
		} else if (temperature > 10 && temperature <= 20) {
			index = 4;
		} else if (temperature > 20 && temperature <= 30) {
			index = 5;
		} else if (temperature > 30 && temperature <= 40) {
			index = 6;
		} else if (temperature > 40 && temperature <= 50) {
			index = 7;
		} else if (temperature > 50) {
			index = 8;
		}
		return tempPercentList[index];
	}
}
