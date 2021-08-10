package za.co.assignment.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Traffic")
public class Traffic {

	@Id
	@Column(name = "routeId")
	private long id;

	@Column(name = "planet_Origin")
	private String origin;

	@Column(name = "planet_Destination")
	private String destination;

	@Column(name = "traffic_Delay")
	private double delay;

	public Traffic() {

	}

	public Traffic(String origin, String destination, double delay) {
		super();
		this.origin = origin;
		this.destination = destination;
		this.delay = delay;
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

	public double getDelay() {
		return delay;
	}

	public void setDelay(double d) {
		this.delay = d;
	}

	@Override
	public String toString() {
		return "Traffic [id=" + id + ", origin=" + origin + ", destination=" + destination + ", delay=" + delay + "]";
	}

}
