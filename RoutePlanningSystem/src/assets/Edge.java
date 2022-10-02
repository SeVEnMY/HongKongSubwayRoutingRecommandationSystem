package assets;

public class Edge {
	private int id;
	private Station a;
	private Station b;
	private float time;

	public Edge(int _id, Station _a, Station _b, float _time) {
		this.id = _id;
		this.a = _a;
		this.b = _b;
		this.time = _time;
	}

	public float getRealTime() {
		return this.time;
	}

	public int getId() {
		return this.id;
	}

	public int getStationAID() {
		return this.a.getId();
	}
	
	public int getStationBID() {
		return this.b.getId();
	}

	public boolean stationInEdge(Station s) {
		if (Station.isSameStation(a, s) || Station.isSameStation(b, s))
			return true;
		return false;
	}
}
