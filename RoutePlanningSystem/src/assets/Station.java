package assets;

import java.util.ArrayList;
import java.util.List;

public class Station {

	/**
	 * Station name.
	 */
	private String name;

	/**
	 * An array list of adjacent stations.
	 */
	private List<Station> adjStations;

	/**
	 * An array list of facilities in the station.
	 */
	private Facilities facilities;

	/**
	 * A unique id for each station.
	 */
	private int id;
	/**
	 * real time people 
	 */
	private int people;
	/**
	 * @param _name
	 * @param _id
	 * @param _facilities
	 */
	public Station(String _name, int _id, Facilities _facilities) {
		this.name = _name;
		this.id = _id;
		this.facilities = _facilities;
		this.adjStations = new ArrayList<Station>();
	}
	public void updatePeople(int people)
	{
		this.people=people;
	}
	public int getPeopleLevel()
	{
		if(people<30)
			return 1;
		if(people<60)
			return 2;
		if(people<100)
			return 3;
		return 4;
	}
	/**
	 * @return station id
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * @return station name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @return adjacent stations
	 */
	public List<Station> getAdjStations() {
		return this.adjStations;
	}
	
	public void setAdjStations(List<Station> _adjStations) {
		this.adjStations = _adjStations;
	}

	/**
	 * @return facilities in the station
	 */
	public List<String> getFacilities() {
		return this.facilities.listFacilities();
	}

	/**
	 * @param adjStation
	 */
	public void addAdjStation(Station adjStation) {
		this.adjStations.add(adjStation);
	}
	public boolean checkIfAdjStationExist(Station adjStation) {
		String name = adjStation.getName();
		for(Station s:adjStations)
			if(s.getName().equals(name))
				return true;
		return false;
	}

	/**
	 * @param a Station a
	 * @param b Station b
	 * @return true if the two stations are the same.
	 */
	public static boolean isSameStation(Station a, Station b) {

		if (a.getId() == b.getId()) {
			return true;
		}
		return false;
	}

	public boolean searchFacilityInStation(String facility) {
		return this.facilities.getAvailability(facility);
	}

	public List<Station> searchFacilityNearStation(String facility) {
		List<Station> stations = new ArrayList<Station>();
		// only search its adjacent stations
		for (int i = 0; i < this.adjStations.size(); i++) {
			Station s = this.adjStations.get(i);
			if (s.searchFacilityInStation(facility)) {
				stations.add(s);
			}
		}
		// if still no result, search the adjacent stations of the adjacent stations.
		if (stations.isEmpty()) {
			for (int i = 0; i < this.adjStations.size(); i++) {
				Station s = this.adjStations.get(i);
				for (int j = 0; j < s.adjStations.size(); j++) {
					Station adjs = s.adjStations.get(j);
					if (adjs.searchFacilityInStation(facility) && !isDuplicateInStationList(stations, adjs)) {
						stations.add(adjs);
					}
				}
			}
		}
		return stations;
	}
	
	public static boolean isDuplicateInStationList(List<Station> stations, Station station) {
		boolean isDuplicate = false;
		for (int i = 0; i < stations.size(); i++) {
			if (isSameStation(stations.get(i), station)) {
				isDuplicate = true;
				break;
			}
		}
		return isDuplicate;
	}

}
