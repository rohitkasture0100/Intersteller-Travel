package za.co.assignment.message;

import java.util.List;

public class ResponseMessage {
	private String message;
	private double distance;
	private List<String> planetNames;
	

	public ResponseMessage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResponseMessage(String message, double distance, List<String> planetNames) {
		super();
		this.message = message;
		this.distance = distance;
		this.planetNames = planetNames;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public List<String> getPlanetNames() {
		return planetNames;
	}

	public void setPlanetNames(List<String> planetNames) {
		this.planetNames = planetNames;
	}

	public ResponseMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}