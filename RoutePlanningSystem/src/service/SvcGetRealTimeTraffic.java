package service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import assets.Network;
import assets.Station;

public class SvcGetRealTimeTraffic extends RecordedService{
	private Station station;
	String[] result= {"Less than 30","30-60","60-100","More than 100"};
	
	public void execute() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Please enter a station: ");
			String src = in.readLine();
			Network network = Network.getInstance();
			this.station = network.searchStationByName(src);
			if(station==null)
			{
				throw new Exception();
			} 
			int level=station.getPeopleLevel();
			System.out.println("Real time trafic in "+station.getName()+" is "+this.result[level-1]);
			addToHistory(this);
		} catch (Exception e) {
			if(Network.getInstance().getLines().size()==0||Network.getInstance().getStations().size()==0)
			{
				System.out.println("NullPointerException");
			}
			else 
				System.out.println("Invalid Input!");
		}
	}
	public String toString() {
		return "Get Real Time trafic at " + station.getName();
	} 
}
