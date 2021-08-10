package za.co.assignment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Routes")
public class Routes {

	@Id
	@Column(name = "routeId")
	private long id;

	@Column(name = "planet_Origin")
	private String origin;

	@Column(name = "planet_Destination")
	private String destination;

	@Column(name = "Distance")
	private double distance;

	public Routes() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Routes(long id, String origin, String destination, double distance) {
		super();
		this.id = id;
		this.origin = origin;
		this.destination = destination;
		this.distance = distance;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	@Override
	public String toString() {
		return "Routes [id=" + id + ", origin=" + origin + ", destination=" + destination + ", distance=" + distance
				+ "]";
	}

}
