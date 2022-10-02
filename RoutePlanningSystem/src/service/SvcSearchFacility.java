
package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import assets.Network;
import assets.Station;

public class SvcSearchFacility extends RecordedService {
	private Station curStation;
	private String facility;
	private Object[][] FACILITY_MENU = {
			{"1", "WIFI"},
			{"2", "Mobile Charging Service"},
			{"3", "Toilet"},
			{"4", "Mall"},
			{"5", "Tourist Service"},
			{"6", "Breast Feeding Area"},
	};
	private Object[][] YESNO_MENU = {
			{"1", "Yes"},
			{"2", "No, back to the main menu."},
	};
	private BufferedReader _stdin;
	public SvcSearchFacility(){
	}
	
	public void execute() {
		System.out.println("Please type in the station name and the facility type");

			synchronized (System.in) {

            	try {

    				Network  network = Network.getInstance();
    				
    				System.out.println("Please enter station name: ");
    				_stdin = new BufferedReader(new InputStreamReader(System.in));
    				String sta = _stdin.readLine();
    				curStation = network.searchStationByName(sta);
    				if(curStation==null)
    				{
    					throw new Exception();
    				} 				
    				System.out.println("Please enter facility type: ");
    				this.printMenu(this.FACILITY_MENU);
    				String choice = _stdin.readLine();
    				facility = getChoice(this.FACILITY_MENU,choice);
    				if(facility.equals("Wrong"))
    					return;
    				Boolean availInStation = curStation.searchFacilityInStation(facility);
    				addToHistory(this);
    				if (availInStation) {
    					System.out.println("Station " + sta + " has " + facility);
    				} else {
    					System.out.println("Sorry, station " + sta + " does not have " + facility);
    					System.out.println("Do you want to search nearby stations?");
    					this.printMenu(this.YESNO_MENU);
    				    String line = _stdin.readLine();
    					String ynChoice = getChoice(this.YESNO_MENU,line);
    					if (ynChoice == "Yes") {
    						List<Station> nearbyStations = curStation.searchFacilityNearStation(facility);
    						if (nearbyStations.size() == 0) {
    							System.out.println("Sorry, there is no station near "+ sta + " with " + facility);
    						} else {
	    						for (int i = 0; i < nearbyStations.size(); i++) {
	    							System.out.println(nearbyStations.get(i).getName());
	    						}
    						}
    					}
    				}
    				
            	} catch (Exception e) {
            		System.out.println("Invalid Input!");
                }
			}
	}
	
	public String getChoice(Object[][] menu,String line) {
        StringBuffer sb = new StringBuffer(menu.length);
        for (int i = 0; i < menu.length; i++) {
            sb.append(menu[i][0]);
        }
        String choices = sb.toString();
        	
        if (line.length() == 1) {
            int choice = Character.toUpperCase(line.charAt(0));
            int index = choices.indexOf(choice);
            if (index != -1) {
                String input = choices.substring(index, index + 1);
            	for (int i = 0; i < menu.length; i++) {
            		Object[] entry = menu[i];
            		if (entry[0].equals(input)) return entry[1].toString();
            	}
            }
        }
    	System.out.println("*** Must be one of the choices: " + choices);
    	return "Wrong";
	}
	
	public void printMenu(Object[][] menu) throws IOException {
		for (int i = 0; i < menu.length; i++)
			System.out.println("[" + menu[i][0] + "]  " + menu[i][1]);
	}
	
	public String toString() {
		return "Search facilities [" + facility + "] at "+ curStation.getName();
	}
	
}