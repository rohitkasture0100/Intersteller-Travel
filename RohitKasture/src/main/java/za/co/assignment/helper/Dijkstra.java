package za.co.assignment.helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

import za.co.assignment.message.ResponseMessage;
import za.co.assignment.model.Planets;
import za.co.assignment.model.Routes;
import za.co.assignment.model.Traffic;

class Vertex implements Comparable<Vertex> {
	public final String name;
	public Edge[] adjacencies;
	public double minDistance = Double.POSITIVE_INFINITY;
	public Vertex previous;

	public Vertex(String argName) {
		name = argName;
	}

	public String toString() {
		return name;
	}

	public int compareTo(Vertex other) {
		return Double.compare(minDistance, other.minDistance);
	}

}

class Edge {
	public final Vertex target;
	public final double weight;

	public Edge(Vertex argTarget, double argWeight) {
		target = argTarget;
		weight = argWeight;
	}
}

public class Dijkstra {
	public static void computePaths(Vertex source) {
		source.minDistance = 0;
		PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
		vertexQueue.add(source);

		while (!vertexQueue.isEmpty()) {
			Vertex u = vertexQueue.poll();
			if (u != null)
				// Visit each edge exiting u
				for (Edge e : u.adjacencies) {
					if (e != null) {
						Vertex v = e.target;
						double weight = e.weight;
						double distanceThroughU = u.minDistance + weight;
						if (v != null)
							if (distanceThroughU < v.minDistance) {
								vertexQueue.remove(v);

								v.minDistance = distanceThroughU;
								v.previous = u;
								vertexQueue.add(v);
							}
					}
				}
		}
	}

	public static List<Vertex> getShortestPathTo(Vertex target) {
		List<Vertex> path = new ArrayList<Vertex>();
		for (Vertex vertex = target; vertex != null; vertex = vertex.previous)
			path.add(vertex);

		Collections.reverse(path);
		return path;
	}

	public ResponseMessage generate(List<Planets> planets, List<Traffic> traffics, List<Routes> routes, String des,
			String traffic) {
		Vertex[] v = new Vertex[planets.size()];
		// Create a new list holding distance betweeen node with delay
		List<Routes> routesWithTraffic = routes.stream().collect(Collectors.toList());

		if (traffic.equalsIgnoreCase("With Traffic")) {
			int c = 0;
			for (Routes r : routesWithTraffic) {
				Routes rr = routes.get(c);
				rr.setDistance(traffics.get(c).getDelay() + rr.getDistance());
				routesWithTraffic.set(c, rr);
				c++;
			}
		}
		String destination = des;
		for (Planets p : planets) {
			if (p.getName().equalsIgnoreCase(destination)) {
				destination = p.getNode();
			}
		}

		Map<String, Vertex> nodeAndVertex = new HashMap<String, Vertex>();
		Map<Vertex, String> nodePlanetMap = new HashMap<Vertex, String>();
		List<String> planetNames = new ArrayList<>();
		int i = 0, j = 0;

		// mark all the vertices
		for (Planets p : planets) {
			String node = p.getNode();
			v[i] = new Vertex(node);
			nodeAndVertex.put(node, v[i]);
			nodePlanetMap.put(v[i], p.getName());
			i++;
		}

		// set the edges and weight
		for (Planets p : planets) {
			Edge[] e = new Edge[1000];
			if (traffic.equalsIgnoreCase("With Traffic Same Nodes")) {
				for (Routes r : routes) {
					if (p.getNode() == r.getOrigin()) {
						double delay = traffics.get((int) r.getId() - 1).getDelay();
						e[j] = new Edge(nodeAndVertex.get(r.getDestination()), r.getDistance() + delay);
						j++;
					}
				}
			} else if (traffic.equalsIgnoreCase("With Traffic")) {
				for (Routes r : routesWithTraffic) {
					if (p.getNode() == r.getOrigin()) {
						e[j] = new Edge(nodeAndVertex.get(r.getDestination()), r.getDistance());
						j++;
					}
				}
			} else {
				for (Routes r : routes) {
					if (p.getNode() == r.getOrigin()) {
						e[j] = new Edge(nodeAndVertex.get(r.getDestination()), r.getDistance());
						j++;
					}
				}
			}
			nodeAndVertex.get(p.getNode()).adjacencies = e;
		}

		computePaths(nodeAndVertex.get("A")); // run Dijkstra
		String message = "";
		if (traffic.equalsIgnoreCase("Without Traffic"))
			message = "Minimum Distance Between Earth To " + des + " Without Traffic Is : ";
		else
			message = "Minimum Distance Between Earth To " + des + " With Traffic Is : ";

		System.out.println(message + nodeAndVertex.get(destination).minDistance);

		List<Vertex> path = getShortestPathTo(nodeAndVertex.get(destination));
		System.out.println("Path By Nodes :" + path);
		System.out.print("Path By Planet Names : ");

		path.forEach((s) -> {
			planetNames.add(nodePlanetMap.get(s));
		});
		System.out.print(planetNames.stream().collect(Collectors.joining(" -> ")));
		ResponseMessage responseMessage = new ResponseMessage(message, nodeAndVertex.get(destination).minDistance,
				planetNames);

		return responseMessage;
	}

}