
package service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import assets.Edge;
import assets.Line;
import assets.Network;
import assets.Station;

public class SvcLeastTransferTime extends RecordedService {

	private Station start;
	private Station end;
	int cnt;

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
			searchByLeastTrans(network, start, end);
		}catch (Exception e) {
			if(Network.getInstance().getLines().size()==0||Network.getInstance().getStations().size()==0)
			{
				System.out.println("NullPointerException");
			}
			else 
				System.out.println("Invalid Input!");
		}
	}

	public void dfs(ArrayList<List<Integer>> former,Network network, int id, Station start,String str) {
		int former_id = former.get(id).get(0);
		if (former_id == 0) {
			System.out.println("Route "+cnt+":");
			cnt+=1;
			System.out.println(start.getName() + "->"+str);
		}
		else {
			for(int i=0;i<former.get(id).size();i++)
			{
				former_id=former.get(id).get(i);
				Line line1 = network.findLine(former_id-1);
				Line line2 = network.findLine(id-1);
				String str1="(Take "+line1.getName()+" Line to)"+Line.getPointsOfIntersection(line1, line2, network)+"->"+str;
				dfs(former, network, former_id, start,str1);
			}
		}
	}
	public void searchByLeastTrans(Network network, Station start, Station end) {
		List<Line> lines = network.getLines();
		int num_lines = lines.size();
		ArrayList<Edge> edges = new ArrayList<Edge>();
		for (int i = 0; i < lines.size(); i++) {
			edges.addAll(lines.get(i).getEdges());
		}

		
		int[][] intersection = new int[num_lines + 2][num_lines + 2];// we need to add another 2 points in order to run
																		// shortest path algorithm
		for (int i = 0; i < num_lines; i++) {
			for (int j = i + 1; j < num_lines; j++) {
				Line line1 = lines.get(i);
				Line line2 = lines.get(j);
				if (Line.isIntersected(line1, line2) == true) {
					intersection[line1.getId() + 1][line2.getId() + 1] = 1;
					intersection[line2.getId() + 1][line1.getId() + 1] = 1;
				}
			}
		}
		int num_edges = edges.size();
		int s = start.getId();
		int e = end.getId();
		// we set line-node 0 as source point, line-node num_lines+1 as target point
		for (int i = 0; i < num_edges; i++) {
			if (edges.get(i).getStationAID() == s || edges.get(i).getStationBID() == s) {
				int line_id = network.searchEdgeInWhichLine((edges.get(i).getId()));
				intersection[0][line_id + 1] = 1;
				intersection[line_id + 1][0] = 1;
			}
			if (edges.get(i).getStationAID() == e || edges.get(i).getStationBID() == e) {
				int line_id = network.searchEdgeInWhichLine(edges.get(i).getId());
				intersection[num_lines + 1][line_id + 1] = 1;
				intersection[line_id + 1][num_lines + 1] = 1;
			}
		}
		System.out.println("Searching for the path with least transfer...");
		// u is the node that is currently selected
		int[] distance = new int[num_lines + 3];
		boolean[] isVis = new boolean[num_lines + 3];

		ArrayList<List<Integer>> former = new ArrayList<List<Integer>>();
		for (int i = 0; i <num_lines+3; ++i) {
			List<Integer> tempArray = new ArrayList<Integer>();
			former.add(tempArray);
		}
		distance[0] = 0;
		for (int i = 1; i <= num_lines + 1; i++) {
			distance[i] = 9999;
		}
		while (true) {
			int u = -1;
			int t = Integer.MAX_VALUE;
			for (int i = 0; i <= num_lines + 1; i++) {
				if (distance[i] < t && isVis[i] == false) {
					t = distance[i];
					u = i;
				}
			}
			if (u == -1) {
				break;
			}
			isVis[u] = true;
			for (int v = 0; v <= num_lines + 1; v++) {
				if (intersection[u][v] != 0 && distance[v] > distance[u] + 1) {
					distance[v] = distance[u] + 1;
					former.get(v).clear();
					former.get(v).add(u);
				} else if (intersection[u][v] != 0 && distance[v] == distance[u] + 1) {
					former.get(v).add(u);
				}
			}
		}
		System.out.print("Least Transfer Time:");
		System.out.println(distance[num_lines + 1] - 1);
		cnt=1;
		for(int i=0;i<former.get(num_lines+1).size();i++)
		{
			int former_id=former.get(num_lines+1).get(i);
			Line line1 =network.findLine(former_id-1);	
			String str1="(Take "+line1.getName()+" Line to)"+end.getName();
			dfs(former, network, former_id, start, str1);
		}
		System.out.println("");
	}
	
	public String toString() {
		return "Least transfer route from " + start.getName() + " to " + end.getName();
	}
}