package za.co.assignment.helper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import za.co.assignment.model.Planets;
import za.co.assignment.model.Routes;
import za.co.assignment.model.Traffic;

public class ExcelHelper {
	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	static String[] HEADERs = { "routeId", "planet_Origin", "planet_Destination", "traffic_Delay", "planet_Node",
			"planet_Name", "Distance" };

	static String SHEET3 = "Traffic";
	static String SHEET2 = "Routes";
	static String SHEET1 = "Planet Names";
	
	public static boolean hasExcelFormat(MultipartFile file) {

		if (!TYPE.equals(file.getContentType())) {
			return false;
		}

		return true;
	}

	public static List<Traffic> excelToTraffic(InputStream is) {
		try {
			Workbook workbook = new XSSFWorkbook(is);

			Sheet sheet = workbook.getSheet(SHEET3);
			Iterator<Row> rows = sheet.iterator();

			List<Traffic> traffics = new ArrayList<Traffic>();

			int rowNumber = 0;
			while (rows.hasNext()) {
				Row currentRow = rows.next();

				// skip header
				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}

				Iterator<Cell> cellsInRow = currentRow.iterator();

				Traffic traffic = new Traffic();

				int cellIdx = 0;
				while (cellsInRow.hasNext()) {
					Cell currentCell = cellsInRow.next();

					switch (cellIdx) {

					case 0:
						traffic.setId((long) currentCell.getNumericCellValue());
						break;

					case 1:
						traffic.setOrigin(currentCell.getStringCellValue());
						break;

					case 2:
						traffic.setDestination(currentCell.getStringCellValue());
						break;

					case 3:
						final DataFormatter dataFormatter = new DataFormatter();
						String cellValueAsString = dataFormatter.formatCellValue(currentCell);
						System.out.println(currentCell instanceof XSSFCell);
						traffic.setDelay((double) currentCell.getNumericCellValue());
						break;

					default:
						break;
					}

					cellIdx++;
				}

				traffics.add(traffic);
			}

			workbook.close();

			return traffics;
		} catch (IOException e) {
			throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
		}
	}

	// Planets
	public static List<Planets> excelToPlanets(InputStream is) {
		try {
			Workbook workbook = new XSSFWorkbook(is);

			Sheet sheet = workbook.getSheet(SHEET1);
			Iterator<Row> rows = sheet.iterator();

			List<Planets> planets = new ArrayList<Planets>();

			int rowNumber = 0;
			while (rows.hasNext()) {
				Row currentRow = rows.next();

				// skip header
				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}

				Iterator<Cell> cellsInRow = currentRow.iterator();

				Planets planet = new Planets();

				int cellIdx = 0;
				while (cellsInRow.hasNext()) {
					Cell currentCell = cellsInRow.next();

					switch (cellIdx) {

					case 0:
					
						planet.setNode(currentCell.getStringCellValue());
						break;

					case 1:
						planet.setName(currentCell.getStringCellValue());
						break;

					default:
						break;
					}

					cellIdx++;
				}

				planets.add(planet);
			}

			workbook.close();

			return planets;
		} catch (IOException e) {
			throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
		}
	}

	// routes
	public static List<Routes> excelToRoutes(InputStream is) {
		try {
			Workbook workbook = new XSSFWorkbook(is);

			Sheet sheet = workbook.getSheet(SHEET2);
			Iterator<Row> rows = sheet.iterator();

			List<Routes> routes = new ArrayList<Routes>();

			int rowNumber = 0;
			while (rows.hasNext()) {
				Row currentRow = rows.next();

				// skip header
				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}

				Iterator<Cell> cellsInRow = currentRow.iterator();

				Routes route = new Routes();

				int cellIdx = 0;
				while (cellsInRow.hasNext()) {
					Cell currentCell = cellsInRow.next();

					switch (cellIdx) {

					case 0:
						route.setId((long) currentCell.getNumericCellValue());
						break;

					case 1:
						route.setOrigin(currentCell.getStringCellValue());
						break;

					case 2:
						route.setDestination(currentCell.getStringCellValue());
						break;

					case 3:
						final DataFormatter dataFormatter = new DataFormatter();
						String cellValueAsString = dataFormatter.formatCellValue(currentCell);
						System.out.println(currentCell instanceof XSSFCell);
						route.setDistance((double) currentCell.getNumericCellValue());
						break;

					default:
						break;
					}

					cellIdx++;
				}

				routes.add(route);
			}

			workbook.close();

			return routes;
		} catch (IOException e) {
			throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
		}
	}
}
