package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.Before;
import org.junit.runners.Parameterized.Parameter;

import assets.Edge;
import assets.Facilities;
import assets.Line;
import assets.Network;
import assets.Station;

public class LineTest {

	@Parameter
	Line line1;
	Line line2;
	Line line3;
	Line line4;
	Network network;
	ArrayList<Edge> edges1;
	ArrayList<Edge> edges2;
	ArrayList<Edge> edges3;
	ArrayList<Edge> edges4;
	ArrayList<Station> testStations1;
	ArrayList<Station> testStations2;
	ArrayList<Station> testStations3;
	ArrayList<Station> testStations4;

	@Before
	public void setUp() throws Exception {
		int id1 = 7;
		int id2 = 8;
		int id3 = 9;
		int id4 = 10;
		String name1 = "testLine1";
		String name2 = "testLine2";
		String name3 = "testLine3";
		String name4 = "testLine4";

		Facilities fac = new Facilities(true, true, true, true, true, true);
		testStations1 = new ArrayList<Station>();
		testStations2 = new ArrayList<Station>();
		testStations3 = new ArrayList<Station>();
		testStations4 = new ArrayList<Station>();
		edges1 = new ArrayList<Edge>();
		edges2 = new ArrayList<Edge>();
		edges3 = new ArrayList<Edge>();
		edges4 = new ArrayList<Edge>();
		network = Network.getInstance();
		network.getStations().clear();

		for (int i = 0; i < 10; i++) {
			Station s = new Station("testStation" + i, i, fac);
			testStations1.add(s);
		}

		for (int i = 9; i < 19; i++) {
			Station s = new Station("testStation" + i, i, fac);
			testStations2.add(s);
		}

		for (int i = 0; i < 9; i++) {
			Edge e = new Edge(i, testStations1.get(i), testStations1.get(i + 1), 4);
			edges1.add(e);
		}

		for (int i = 9; i < 18; i++) {
			Edge e = new Edge(i, testStations2.get(i - 9), testStations2.get(i - 8), 4);
			edges2.add(e);
		}

		for (int i = 0; i < 10; i++) {
			network.addStation(testStations1.get(i));
		}
		
		for(int i = 1;i<10;i++) {
			network.addStation(testStations2.get(i));
		}
		
		Station s1 = new Station("testStation100", 100, fac);
		Station s2 = new Station("testStation1", 1, fac);
		network.addStation(s1);
		Edge e = new Edge(100, s1, s2, 4);
		edges3.add(e);
		
		line1 = new Line(id1, name1, edges1);
		line2 = new Line(id2, name2, edges2);
		line3 = new Line(id3, name3, edges3);
		line4 = new Line(id4, name4, edges4);
	}

	@Test
	public void test01_GetIdTest() {
		int result = line1.getId();
		assertEquals(7, result);
	}

	@Test
	public void test02_GetNameTest() {
		String result = line1.getName();
		assertEquals("testLine1", result);
	}

	@Test
	public void test03_GetEdgesTest() {
		ArrayList<Edge> result = line1.getEdges();
		assertEquals(edges1, result);
	}

	@Test
	public void test04_GetEdgesCountTest() {
		int result = line1.getEdgeCount();
		assertEquals(9, result);
	}

	@Test
	public void test05_StationInline_01() {
		boolean result = line1.stationInLine(testStations1.get(0));
		assertEquals(true, result);
	}
	
	@Test
	public void test06_StationInline_02() {
		boolean result = line1.stationInLine(testStations1.get(9));
		assertEquals(true, result);
	}
	
	@Test
	public void test07_StationInline_03() {
		boolean result = line1.stationInLine(testStations2.get(9));
		assertEquals(false, result);
	}
	
	@Test
	public void test08_StationInline_04() {
		boolean result = line4.stationInLine(testStations1.get(0));
		assertEquals(false, result);
	}

	@Test
	public void test09_isIntersected_01() {
		boolean result = Line.isIntersected(line1, line2);
		assertEquals(true, result);
	}

	@Test
	public void test10_isIntersected_02() {
		boolean result = Line.isIntersected(line2, line1);
		assertEquals(true, result);
	}

	@Test
	public void test11_isIntersected_03() {
		boolean result = Line.isIntersected(line1, line1);
		assertEquals(true, result);
	}

	@Test
	public void test12_isIntersected_04() {
		boolean result = Line.isIntersected(line1, line3);
		assertEquals(true, result);
	}

	@Test
	public void test13_isIntersected_05() {
		boolean result = Line.isIntersected(line2, line3);
		assertEquals(false, result);
	}

	@Test
	public void test14_GetPointsOfIntersectionTest_01() {
		String result = Line.getPointsOfIntersection(line2, line3, network);
		assertEquals("", result);
	}

	@Test
	public void test15_GetPointsOfIntersectionTest_02() {
		String result = Line.getPointsOfIntersection(line1, line2, network);
		assertEquals("testStation9", result);
	}
	
	@Test
	public void test16_GetPointsOfIntersectionTest_03() {
		String result = Line.getPointsOfIntersection(line2, line1, network);
		assertEquals("testStation9", result);
	}
	
	@Test
	public void test17_GetPointsOfIntersectionTest_04() {
		String result = Line.getPointsOfIntersection(line1, line1, network);
		assertEquals("testStation0 or testStation1 or testStation2 or testStation3 or testStation4 or testStation5 or testStation6 or testStation7 or testStation8 or testStation9", result);
	}
	
	@Test
	public void test18_HasEdgeTest_01() {
		boolean result = line1.hasEdge(0);
		assertEquals(true, result);
	}
	
	@Test
	public void test19_HasEdgeTest_02() {
		boolean result = line1.hasEdge(10);
		assertEquals(false, result);
	}
	
	@Test
	public void test20_HasEdgeTest_03() {
		boolean result = line4.hasEdge(0);
		assertEquals(false, result);
	}
}
