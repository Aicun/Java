package group2.hachathon.po;


public class Temperature {

	private float temp;
	private float tempMin;
	private float tempMax;
	private float pressure;
	private float humidity;
	
	public Temperature(float temp, float tempMin, float tempMax,
			float pressure, float humidity) {
		this.temp = temp;
		this.tempMin = tempMin;
		this.tempMax = tempMax;
		this.pressure = pressure;
		this.humidity = humidity;
	}
	
	public float getTemp() {
		return temp;
	}
	public void setTemp(float temp) {
		this.temp = temp;
	}
	public float getTempMin() {
		return tempMin;
	}
	public void setTempMin(float tempMin) {
		this.tempMin = tempMin;
	}
	public float getTempMax() {
		return tempMax;
	}
	public void setTempMax(float tempMax) {
		this.tempMax = tempMax;
	}
	public float getPressure() {
		return pressure;
	}
	public void setPressure(float pressure) {
		this.pressure = pressure;
	}
	public float getHumidity() {
		return humidity;
	}
	public void setHumidity(float humidity) {
		this.humidity = humidity;
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("temp: ")
		.append(temp)
		.append(" pressure: ")
		.append(pressure)
		.append(" humidity: ")
		.append(humidity)
		.append(" tempmax ")
		.append(tempMax)
		.append(" tempmin: ")
		.append(tempMin);
		return sb.toString();
	}
}