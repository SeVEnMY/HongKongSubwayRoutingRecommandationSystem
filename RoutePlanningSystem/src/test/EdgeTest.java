package test;

import static org.junit.Assert.*;
import org.junit.Test;

import assets.Edge;
import assets.Facilities;
import assets.Station;


public class EdgeTest {
	
	@Test
	public void test_Edge_01() throws Exception {
		Facilities f_a = new Facilities(false,true,true,false,true,false);
		Facilities f_b = new Facilities(true,true,true,false,true,false);
		Station a = new Station("Central",0,f_a);
		Station b = new Station("Admiralty",1,f_b);
		Edge edge = new Edge(1,a,b,3);
		assertEquals(3.0, edge.getRealTime(), 0.001);
	}
	
	@Test
	public void test_Edge_02() throws Exception {
		Facilities f_a = new Facilities(false,true,true,false,true,false);
		Facilities f_b = new Facilities(true,true,true,false,true,false);
		Station a = new Station("Central",0,f_a);
		Station b = new Station("Admiralty",1,f_b);
		Edge edge = new Edge(10,a,b,3);
		int id =edge.getId();
		assertEquals(10,id);
	}
	
	@Test
	public void test_Edge_03() throws Exception {
		Facilities f_a = new Facilities(false,true,true,false,true,false);
		Facilities f_b = new Facilities(true,true,true,false,true,false);
		Station a = new Station("Central",0,f_a);
		Station b = new Station("Admiralty",1,f_b);
		Edge edge = new Edge(10,a,b,3);
		assertEquals(0,edge.getStationAID());
	}
	
	@Test
	public void test_Edge_04() throws Exception {
		Facilities f_a = new Facilities(false,true,true,false,true,false);
		Facilities f_b = new Facilities(true,true,true,false,true,false);
		Station a = new Station("Central",0,f_a);
		Station b = new Station("Admiralty",1,f_b);
		Edge edge = new Edge(10,a,b,3);
		assertEquals(1,edge.getStationBID());
	}
	
	@Test
	public void test_Edge_05() throws Exception {
		Facilities f_a = new Facilities(false,true,true,false,true,false);
		Facilities f_b = new Facilities(true,true,true,false,true,false);
		Station a = new Station("Central",0,f_a);
		Station b = new Station("Admiralty",1,f_b);
		Edge edge = new Edge(10,a,b,3);
		assertEquals(true,edge.stationInEdge(a));
	}
	
	@Test
	public void test_Edge_06() throws Exception {
		Facilities f_a = new Facilities(false,true,true,false,true,false);
		Facilities f_b = new Facilities(true,true,true,false,true,false);
		Station a = new Station("Central",0,f_a);
		Station b = new Station("Admiralty",1,f_b);
		Edge edge = new Edge(10,a,b,3);
		assertEquals(true,edge.stationInEdge(b));
	}
	
	@Test
	public void test_Edge_07() throws Exception {
		Facilities f_a = new Facilities(false,true,true,false,true,false);
		Facilities f_b = new Facilities(true,true,true,false,true,false);
		Station a = new Station("Central",1,f_a);
		Station b = new Station("Admiralty",2,f_b);
		Edge edge = new Edge(10,a,b,3);
		
		Facilities f_c = new Facilities(true,false,true,true,true,false);
		Station c = new Station("Yau Ma Tei",4,f_c);
		assertEquals(false,edge.stationInEdge(c));
	}
}
