package za.co.assignment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.co.assignment.model.Planets;
import za.co.assignment.model.Routes;
import za.co.assignment.model.Traffic;
import za.co.assignment.repository.PlanetRepository;
import za.co.assignment.repository.RouteRepository;
import za.co.assignment.repository.TrafficRepository;

@Service
public class ComputeService {
	@Autowired
	TrafficRepository trafficRepository;
	
	@Autowired
	RouteRepository routeRepository;
	
	@Autowired
	PlanetRepository planetRepository;
	
	public List<Traffic> getAllTraffics() {
		return trafficRepository.findAll();
	}
	
	public List<Routes> getAllRoutes() {
		return routeRepository.findAll();
	}
	
	public List<Planets> getAllPlanets() {
		return planetRepository.findAll();
	}
	
}
