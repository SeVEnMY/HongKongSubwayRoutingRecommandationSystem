package assets;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Network {
	private List<Station> stations;
	private static Network instance = new Network();
	
	/**
	 * Use the station table to generate all stations
	 * 
	 * @return
	 */
	private Network() {
		stations = new ArrayList<Station>();
		allLines = new ArrayList<Line>();
	}
	public static Network getInstance() {
		return instance;
	}
	public void initializationStations() throws IOException {
		File file = new File("./data/mtr_stations.csv");
		Scanner scanner = new Scanner(file);
		Scanner dataScanner = null;
		List<Station> _stations = new ArrayList<Station>();
		scanner.nextLine();
		while (scanner.hasNextLine()) {
			dataScanner = new Scanner(scanner.nextLine());
			dataScanner.useDelimiter(",");

			int index = 0;
			int id = 0;
			String name = "";
			boolean hasWIFI = false;
			boolean hasMobileCharging = false;
			boolean hasToilet = false;
			boolean hasMall = false;
			boolean hasTouristService = false;
			boolean hasFeedingArea = false;
			while (dataScanner.hasNext()) {
				String data = dataScanner.next().trim();
				if (index == 0)
				{
					id = Integer.parseInt(data);
				}
				else if (index == 1)
				{
					name = data;
				}
				else if (index == 2) {
					hasWIFI = Integer.parseInt(data) == 1;
				}
				else if (index == 3)
					hasMobileCharging = Integer.parseInt(data) == 1;
				else if (index == 4)
					hasToilet = Integer.parseInt(data) == 1;
				else if (index == 5)
					hasMall = Integer.parseInt(data) == 1;
				else if (index == 6)
					hasTouristService = Integer.parseInt(data) == 1;
				else
					hasFeedingArea = Integer.parseInt(data) == 1;
				index++;
			}
			Facilities facilities = new Facilities(hasWIFI, hasMobileCharging, hasToilet, hasMall, hasTouristService,
					hasFeedingArea);
			Station station = new Station(name, id, facilities);
			_stations.add(station);
			
		}
		this.stations = _stations;
		updateRealTime();
		scanner.close();
	};
	
	public List<Station> getStations() {
		return this.stations;
	}

	public int getStationNum() {
		return this.stations.size();
	}
	
	public void addStation(Station newStation) {
		this.stations.add(newStation);	
	}
	
	public Station searchStation(int id) {
		return this.stations.get(id);
	}

	public Station searchStationByName(String name){
		for (Station s : stations) {
			if (s.getName().equals(name))
				return s;
		}
		return null;
	}

	public void updateRealTime() {
		for (Station s : stations) {
			int people=(int)(Math.random()*120);
			s.updatePeople(people);
		}
	}
	private List<Line> allLines;

	public void initializationLines() throws FileNotFoundException {
		File file = new File("./data/lines.csv");
		allLines.clear();
		Scanner scanner = new Scanner(file);
		Scanner dataScanner = null;
		ArrayList<Edge> crtEdges = new ArrayList<Edge>();
		int lineID = 0;
		int edgeID = 0;
		String lineName = null;
		scanner.nextLine();
		while (scanner.hasNextLine()) {
			dataScanner = new Scanner(scanner.nextLine());
			dataScanner.useDelimiter(",");
			int stationID1 = Integer.parseInt(dataScanner.next());
			int stationID2 = Integer.parseInt(dataScanner.next());
			Station station1 = this.searchStation(stationID1);
			Station station2 = this.searchStation(stationID2);
			if(!station1.checkIfAdjStationExist(station2)) {
				station1.addAdjStation(station2);
				station2.addAdjStation(station1);
			}
			Float time = Float.parseFloat(dataScanner.next());
			String name = dataScanner.next();
			if (!name.equals(lineName)) {
				if (lineName != null) {
					this.allLines.add(new Line(lineID, lineName, crtEdges));
					crtEdges = new ArrayList<Edge>();
					lineID++;
				}
				lineName = name;
			}
			crtEdges.add(new Edge(edgeID, station1, station2, time));
			edgeID++;
		}
		scanner.close();
		this.allLines.add(new Line(lineID, lineName, crtEdges));
	}

	public int searchEdgeInWhichLine(int id) {
		for (int i = 0; i < allLines.size(); i++) {
			Line l = allLines.get(i);
			if (l.hasEdge(id)) {
				return l.getId();
			}
		}
		return -1;
	}

	public List<Line> getLines() {
		// TODO Auto-generated method stub
		return allLines;
	}

	public Line findLine(int id) {
		// TODO Auto-generated method stub
		for (int i = 0; i < allLines.size(); i++) {
			if (allLines.get(i).getId() == id) {
				return allLines.get(i);
			}
		}
		return null;
	}
}
