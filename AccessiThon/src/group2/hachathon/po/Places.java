package group2.hachathon.po;

public class Places {

	private String original;
	private String destination;
	
	public Places(String original, String destination) {
		super();
		this.original = original;
		this.destination = destination;
	}
	public String getOriginal() {
		return original;
	}
	public void setOriginal(String original) {
		this.original = original;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("original: ")
		.append(original)
		.append(" destination: ")
		.append(destination);
		return sb.toString();
	}
}
