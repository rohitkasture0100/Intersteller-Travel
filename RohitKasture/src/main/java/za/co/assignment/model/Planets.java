package za.co.assignment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Planets")
public class Planets {

	@Id
	@Column(name = "planet_Node")
	private String node;

	@Column(name = "planet_Name")
	private String name;

	public Planets() {

	}

	public Planets(String node, String name) {
		super();
		this.node = node;
		this.name = name;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Planets [node=" + node + ", name=" + name + "]";
	}

}
