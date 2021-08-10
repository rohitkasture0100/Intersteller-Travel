package za.co.assignment.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import za.co.assignment.helper.ExcelHelper;
import za.co.assignment.model.Planets;
import za.co.assignment.model.Routes;
import za.co.assignment.model.Traffic;
import za.co.assignment.repository.PlanetRepository;
import za.co.assignment.repository.RouteRepository;
import za.co.assignment.repository.TrafficRepository;

@Service
public class ExcelService {
	@Autowired
	TrafficRepository trafficRepository;
	
	@Autowired
	RouteRepository routeRepository;
	
	@Autowired
	PlanetRepository planetRepository;

	public void save(MultipartFile file) {
		try {
			List<Traffic> traffics = ExcelHelper.excelToTraffic(file.getInputStream());
			List<Routes> routes = ExcelHelper.excelToRoutes(file.getInputStream());
			List<Planets> planets = ExcelHelper.excelToPlanets(file.getInputStream());

			trafficRepository.saveAll(traffics);
			
			routeRepository.saveAll(routes);
			planetRepository.saveAll(planets);

		} catch (IOException e) {
			throw new RuntimeException("fail to store excel data: " + e.getMessage());
		}
	}
}