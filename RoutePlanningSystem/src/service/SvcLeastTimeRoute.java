package service;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import assets.Edge;
import assets.Line;
import assets.Network;
import assets.Station;

public class SvcLeastTimeRoute extends RecordedService {

	private Station start;
	private Station end;

	public void execute() {
		System.out.println("Please type in the start station and end station");
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			Network network = Network.getInstance();
			System.out.println("Please enter start station: ");
			String src = in.readLine();
			this.start = network.searchStationByName(src);
			if (this.start == null) {
				throw new Exception();
			}
			System.out.println("Please enter end station: ");
			String des = in.readLine();
			this.end = network.searchStationByName(des);
			if(start.getId()==end.getId())
			{
				System.out.println("You are already here.");
				return;
			}
			System.out.println("From " + src + " to " + des);
			addToHistory(this);
			searchByTime(network, start, end);
		} catch (Exception e) {
			if(Network.getInstance().getLines().size()==0||Network.getInstance().getStations().size()==0)
			{
				System.out.println("NullPointerException");
			}
			else 
				System.out.println("Invalid Input!");
		}
	}
	public void searchByTime(Network network, Station start, Station end) {
		List<Line> lines = network.getLines();
		ArrayList<Edge> edges = new ArrayList<Edge>();
		for (int i = 0; i < lines.size(); i++) {
			edges.addAll(lines.get(i).getEdges());
		}
		int num = network.getStationNum();
		float time[] = new float[num + 1];
		int path[] = new int[num + 1];
		boolean isVis[] = new boolean[num + 1];
		for (int i = 0; i < num; i++) {
			time[i] = 9999;
			path[i] = -1;
			isVis[i] = false;
		}
		float[][] time_cost = new float[num + 1][num + 1];
		for (int i = 0; i < num; i++) {
			for (int j = 0; j < num; j++) {
				time_cost[i][j] = 9999;// 9999 means no edge or this edge is closed
			}
		}
		for (int i = 0; i < edges.size(); i++) {
			int n1 = edges.get(i).getStationAID();
			int n2 = edges.get(i).getStationBID();
			float t = edges.get(i).getRealTime();

			time_cost[n1][n2] = Math.min(time_cost[n1][n2], t);
			time_cost[n2][n1] = Math.min(time_cost[n2][n1], t);

		}
		int s = start.getId();
		int e = end.getId();
		// Dijkstra
		time[s] = 0;
		System.out.println("Searching for the path with least time...");
		// u is the node that is currently selected
		while (true) {
			int u = -1;
			float t = 9999;
			for (int i = 0; i < num; i++) {
				if (time[i] < t && isVis[i] == false) {
					t = time[i];
					u = i;
				}
			}
			if (u == -1) {
				break;
			}
			isVis[u] = true;
			for (int v = 0; v < num; v++) {
				if (time[v] > time[u] + time_cost[u][v] && time_cost[u][v] != 9999) {
					time[v] = time[u] + time_cost[u][v];
					path[v] = u;
				}
			}
		}
		System.out.println("Shortest Path:");
		int v = e;
		ArrayList<Integer> route = new ArrayList<Integer>();
		while (v != s) {
			route.add(v);
			v = path[v];
		}
		route.add(s);
		for (int i = route.size() - 1; i > 0; i--) {
			{
				Station temp = network.searchStation(route.get(i));
				System.out.print(temp.getName() + "->");
			}
		}
		System.out.println(end.getName());
		System.out.println("Least Time:");
		System.out.println(time[e]);
		return;
	}

	public String toString() {
		return "Least time route from " + start.getName() + " to " + end.getName();
	}

	
}