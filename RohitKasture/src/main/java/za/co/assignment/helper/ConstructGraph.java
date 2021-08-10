package za.co.assignment.helper;

import java.util.List;

import za.co.assignment.message.ResponseMessage;
import za.co.assignment.model.Planets;
import za.co.assignment.model.Routes;
import za.co.assignment.model.Traffic;

public class ConstructGraph {
	public ResponseMessage construct(List<Planets> planets, List<Traffic> traffics, List<Routes> routes,
			String destination, String traffic) {
		return new Dijkstra().generate(planets, traffics, routes, destination,traffic);
	}
}
