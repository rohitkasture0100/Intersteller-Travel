package za.co.assignment.beans;

public class Client {
	private String source;
	private String destination;
	private String run;

	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Client(String source, String destination, String run) {
		super();
		this.source = source;
		this.destination = destination;
		this.run = run;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getRun() {
		return run;
	}

	public void setRun(String run) {
		this.run = run;
	}

	@Override
	public String toString() {
		return "Client [source=" + source + ", destination=" + destination + ", run=" + run + "]";
	}

}
