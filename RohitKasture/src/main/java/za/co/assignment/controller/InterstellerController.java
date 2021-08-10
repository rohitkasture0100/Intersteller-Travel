package za.co.assignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import za.co.assignment.beans.Client;
import za.co.assignment.helper.ConstructGraph;
import za.co.assignment.helper.ExcelHelper;
import za.co.assignment.message.ResponseMessage;
import za.co.assignment.model.Planets;
import za.co.assignment.model.Routes;
import za.co.assignment.model.Traffic;
import za.co.assignment.service.ComputeService;
import za.co.assignment.service.ExcelService;

@RestController
public class InterstellerController {

	@Autowired
	ExcelService fileService;

	@Autowired
	ComputeService computeService;

	@PostMapping(value = "/calculate", consumes = "application/json", produces = "application/json")
	public String calculate(@RequestBody Client client) throws JsonProcessingException {
		List<Traffic> traffics = computeService.getAllTraffics();
		List<Routes> routes = computeService.getAllRoutes();
		List<Planets> planets = computeService.getAllPlanets();
		ResponseMessage responseMessage = new ConstructGraph().construct(planets, traffics, routes,
				client.getDestination(),client.getRun());

		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(responseMessage);
	}

	@PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<ResponseMessage> uploadFile(@RequestParam MultipartFile file) {
		String message = "";

		if (ExcelHelper.hasExcelFormat(file)) {
			try {
				fileService.save(file);

				message = "Uploaded the file successfully: " + file.getOriginalFilename();
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
			} catch (Exception e) {
				message = "Could not upload the file: " + file.getOriginalFilename() + "!";
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
			}
		}

		message = "Please upload an excel file!";
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));

	}
}