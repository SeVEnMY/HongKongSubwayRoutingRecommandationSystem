
package service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import assets.Network;
import assets.Station;

public class SvcGetFacilities extends RecordedService {
	private BufferedReader _stdin;
	Station curStation;

	public void execute() {
		_stdin = new BufferedReader(new InputStreamReader(System.in));

		synchronized (System.in) {
			try {
				System.out.println("Please enter station name: ");
				String station_name = _stdin.readLine();
				Network network = Network.getInstance();
				curStation = network.searchStationByName(station_name);
				print_facilities(curStation);
				addToHistory(this);
			} catch (NullPointerException e) {
				System.out.println("Invalid output!");
			} catch (Exception e) {
				System.out.println("Exception: " + e);
			}

		}
	}
	
	public void print_facilities(Station station) {
		System.out.println("Station " + station.getName() + " has the following facilities:");
		List<String> facilities = station.getFacilities();
		for (int i = 0; i < facilities.size(); i++) {
			System.out.println(facilities.get(i));
		}
	}
	public String toString() {
		return "Get facilities at " + curStation.getName();
	}

}