package assets;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class Line {
	private int id;
	private String name;
	private ArrayList<Edge> edges;

	public Line(int _id, String _name, ArrayList<Edge> _edges) {
		this.id = _id;
		this.name = _name;
		this.edges = _edges;
	}

	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public ArrayList<Edge> getEdges() {
		return this.edges;
	}

	public int getEdgeCount() {
		return edges.size();
	}

	public boolean stationInLine(Station station) {
		for (int i = 0; i < edges.size(); i++) {
			if (edges.get(i).stationInEdge(station)) {
				return true;
			}
		}
		return false;
	}

	public static boolean isIntersected(Line lineA, Line lineB) {
		for (int i = 0; i < lineA.getEdgeCount(); i++) {
			for (int j = 0; j < lineB.getEdgeCount(); j++) {
				if (lineA.getEdges().get(i).getStationAID() == lineB.getEdges().get(j).getStationAID()) {
					return true;
				}
				if (lineA.getEdges().get(i).getStationBID() == lineB.getEdges().get(j).getStationAID()) {
					return true;
				}
				if (lineA.getEdges().get(i).getStationAID() == lineB.getEdges().get(j).getStationBID()) {
					return true;
				}
				if (lineA.getEdges().get(i).getStationBID() == lineB.getEdges().get(j).getStationBID()) {
					return true;
				}
			}
		}
		return false;
	}

	public static String getPointsOfIntersection(Line lineA, Line lineB, Network network) {
		String Points = "";
		HashSet<Integer> temp = new HashSet<Integer>();
		if (!isIntersected(lineA, lineB)) {
			return Points;
		} else {
			for (int i = 0; i < lineA.getEdgeCount(); i++) {
				for (int j = 0; j < lineB.getEdgeCount(); j++) {
					if (lineA.getEdges().get(i).getStationAID() == lineB.getEdges().get(j).getStationAID()) {
						temp.add(lineA.getEdges().get(i).getStationAID());
					}
					if (lineA.getEdges().get(i).getStationBID() == lineB.getEdges().get(j).getStationAID()) {
						temp.add(lineA.getEdges().get(i).getStationBID());
					}
					if (lineA.getEdges().get(i).getStationAID() == lineB.getEdges().get(j).getStationBID()) {
						temp.add(lineA.getEdges().get(i).getStationAID());
					}
					if (lineA.getEdges().get(i).getStationBID() == lineB.getEdges().get(j).getStationBID()) {
						temp.add(lineA.getEdges().get(i).getStationBID());
					}
				}
			}
			Iterator<Integer> it = temp.iterator();
			while (it.hasNext()) {
				Points += network.searchStation( it.next()).getName() + " or ";
			}
			Points.trim();
			Points = Points.substring(0, Points.length() - 4);
			return Points;
		}
	}

	public boolean hasEdge(int id) {
		for (int i = 0; i < edges.size(); i++) {
			if (edges.get(i).getId() == id)
				return true;
		}
		return false;
	}

}
