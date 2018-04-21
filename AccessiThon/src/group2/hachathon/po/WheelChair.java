package group2.hachathon.po;

public class WheelChair {
	private double ampeHour;
	private double voltage;
	private double enginePower;
	private double chairWeight;
	private double batteryPercentage;
	private double userWeight;
	private double maxSpeed;
	
	public WheelChair(double ampeHour, double voltage, double enginePower,
			double chairWeight, double batteryPercentage, double userWeight,
			double maxSpeed) {
		this.ampeHour = ampeHour;
		this.voltage = voltage;
		this.enginePower = enginePower;
		this.chairWeight = chairWeight;
		this.batteryPercentage = batteryPercentage;
		this.userWeight = userWeight;
		this.maxSpeed = maxSpeed;
	}
	
	public double getAmpeHour() {
		return ampeHour;
	}
	public void setAmpeHour(double ampeHour) {
		this.ampeHour = ampeHour;
	}
	public double getVoltage() {
		return voltage;
	}
	public void setVoltage(double voltage) {
		this.voltage = voltage;
	}
	public double getEnginePower() {
		return enginePower;
	}
	public void setEnginePower(double enginePower) {
		this.enginePower = enginePower;
	}
	public double getChairWeight() {
		return chairWeight;
	}
	public void setChairWeight(double chairWeight) {
		this.chairWeight = chairWeight;
	}
	public double getBatteryPercentage() {
		return batteryPercentage;
	}
	public void setBatteryPercentage(double batteryPercentage) {
		this.batteryPercentage = batteryPercentage;
	}
	public double getUserWeight() {
		return userWeight;
	}
	public void setUserWeight(double userWeight) {
		this.userWeight = userWeight;
	}
	public double getMaxSpeed() {
		return maxSpeed;
	}
	public void setMaxSpeed(double maxSpeed) {
		this.maxSpeed = maxSpeed;
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("ampeHour: ")
		.append(ampeHour)
		.append(" voltage: ")
		.append(voltage)
		.append(" enginePower: ")
		.append(enginePower)
		.append(" chairWeight ")
		.append(chairWeight)
		.append(" batteryPercentage: ")
		.append(batteryPercentage)
		.append(" userWeight: ")
		.append(userWeight)
		.append(" maxSpeed: ")
		.append(maxSpeed);
		return sb.toString();
	}
}
