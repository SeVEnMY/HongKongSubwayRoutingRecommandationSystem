package test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import assets.Facilities;
import assets.Network;
import assets.Station;


public class StationTest {

	@Test
	public void test01_getId() {
		Facilities f = new Facilities(false,true,true,false,true,false);
		Station station = new Station("Central", 0, f);
		int id = station.getId();
		assertEquals(0, id);
	}
	
	@Test
	public void test01_getName() {
		Facilities f = new Facilities(false,true,true,false,true,false);
		Station station = new Station("Central", 0, f);
		String name = station.getName();
		assertEquals("Central", name);
	}
	
	@Test
	public void test01_getAdjStations() throws IOException {
		Network network = Network.getInstance();
		network.initializationStations();
		network.initializationLines();
		List<Station> stations = network.searchStationByName("Tung Chung").getAdjStations();
		String[] result = new String[stations.size()];
		for (int i = 0; i < stations.size(); i++) {
			System.out.println(stations.get(i).getName());
			result[i] = stations.get(i).getName();
		}
		String[] expected = new String[1];
		expected[0] = "Sunny Bay";
		assertArrayEquals(expected, result);
	}
	
	@Test
	public void test02_getAdjStations() throws IOException {
		Network network = Network.getInstance();
		network.initializationStations();
		network.initializationLines();
		List<Station> stations = network.getStations().get(0).getAdjStations();
		String[] result = new String[stations.size()];
		for (int i = 0; i < stations.size(); i++) {
			System.out.println(stations.get(i).getName());
			result[i] = stations.get(i).getName();
		}
		String[] expected = new String[2];
		expected[0] = "Sheung Wan";
		expected[1] = "Admiralty";
		assertArrayEquals(expected, result);
	}
	
	@Test
	public void test03_getAdjStations() throws IOException {
		Network network = Network.getInstance();
		network.initializationStations();
		network.initializationLines();
		List<Station> stations = network.searchStationByName("Tai Wai").getAdjStations();
		String[] result = new String[stations.size()];
		for (int i = 0; i < stations.size(); i++) {
			System.out.println(stations.get(i).getName());
			result[i] = stations.get(i).getName();
		}
		String[] expected = new String[3];
		expected[0] = "Kowloon Tong";
		expected[1] = "Sha Tin";
		expected[2] = "Che Kung Temple";
		assertArrayEquals(expected, result);
	}
	
	@Test
	public void test04_getAdjStations() throws IOException {
		Network network = Network.getInstance();
		network.initializationStations();
		network.initializationLines();
		List<Station> stations = network.searchStationByName("Mei Foo").getAdjStations();
		String[] result = new String[stations.size()];
		for (int i = 0; i < stations.size(); i++) {
			System.out.println(stations.get(i).getName());
			result[i] = stations.get(i).getName();
		}
		String[] expected = new String[4];
		expected[0] = "Lai Chi Kok";
		expected[1] = "Lai King";
		expected[2] = "Nam Cheong";
		expected[3] = "Tsuen Wan West";
		assertArrayEquals(expected, result);
	}
	
	@Test
	public void test01_setAdjStations() throws IOException {
		Network network = Network.getInstance();
		network.initializationStations();
		network.initializationLines();
		Station station = network.getStations().get(0);
		List<Station> origList = station.getAdjStations();
		int origSize = origList.size();
		Facilities f = new Facilities(false,true,true,false,true,false);
		Station testStation = new Station("Test Station", 92, f);
		origList.add(testStation);
		station.setAdjStations(origList);
		assertEquals(origSize + 1, station.getAdjStations().size());
	}
	
	
	@Test
	public void test01_getFacilities() throws IOException {
		Facilities f = new Facilities(false,true,true,false,true,false);
		Station station = new Station("Central", 0, f);
		List<String> facilities = station.getFacilities();
		int result = facilities.size();
		assertEquals(3, result);
	}
	
	@Test
	public void test01_isSameStation() {
		Facilities f_a = new Facilities(false,true,true,false,true,false);
		Facilities f_b = new Facilities(true,true,true,false,true,false);
		Station a = new Station("Central",0,f_a);
		Station b = new Station("Admiralty",1,f_b);
		boolean result = Station.isSameStation(a, b);
		assertEquals(false, result);
	}
	
	@Test
	public void test02_isSameStation() {
		Facilities f_a = new Facilities(false,true,true,false,true,false);
		Station a = new Station("Central",0,f_a);
		boolean result = Station.isSameStation(a, a);
		assertEquals(true, result);
	}
	
	@Test
	public void test01_searchFacilityInStation() throws IOException {
		Network network = Network.getInstance();
		network.initializationStations();
		network.initializationLines();
		Station station = network.searchStationByName("Shek Kip Mei");
		boolean result = station.searchFacilityInStation("WIFI");
		assertEquals(true, result);
	}
	
	@Test
	public void test2_searchFacilityInStation() throws IOException {
		Network network = Network.getInstance();
		network.initializationStations();
		network.initializationLines();
		Station station = network.searchStationByName("Shek Kip Mei");
		boolean result = station.searchFacilityInStation("Mobile Charging Service");
		assertEquals(false, result);
	}
	
	@Test
	public void test3_searchFacilityInStation() throws IOException {
		Network network = Network.getInstance();
		network.initializationStations();
		network.initializationLines();
		Station station = network.searchStationByName("Shek Kip Mei");
		boolean result = station.searchFacilityInStation("Toilet");
		assertEquals(true, result);
	}
	
	@Test
	public void test4_searchFacilityInStation() throws IOException {
		Network network = Network.getInstance();
		network.initializationStations();
		network.initializationLines();
		Station station = network.searchStationByName("Shek Kip Mei");
		boolean result = station.searchFacilityInStation("Breast Feeding Area");
		assertEquals(false, result);
	}
	
	@Test
	public void test5_searchFacilityInStation() throws IOException {
		Network network = Network.getInstance();
		network.initializationStations();
		network.initializationLines();
		Station station = network.searchStationByName("Shek Kip Mei");
		boolean result = station.searchFacilityInStation("Mall");
		assertEquals(false, result);
	}
	
	@Test
	public void test6_searchFacilityInStation() throws IOException {
		Network network = Network.getInstance();
		network.initializationStations();
		network.initializationLines();
		Station station = network.searchStationByName("Shek Kip Mei");
		boolean result = station.searchFacilityInStation("Tourist Service");
		assertEquals(true, result);
	}
	
	@Test
	public void test01_searchFacilityNearStation() throws IOException {
		Network network = Network.getInstance();
		network.initializationStations();
		network.initializationLines();
		Station station = network.getStations().get(74);
		List<Station> stationAvail = station.searchFacilityNearStation("WIFI");
		int result = stationAvail.size();
		assertEquals(1, result);
	}
	 
	@Test
	public void test02_searchFacilityNearStation() throws IOException {
		Network network = Network.getInstance();
		network.initializationStations();
		network.initializationLines();
		Station station = network.searchStationByName("Wong Chuk Hang");
		List<Station> stationAvail = station.searchFacilityNearStation("WIFI");
		int result = stationAvail.size();
		assertEquals(2, result);
	}
	
	@Test
	public void test03_searchFacilityNearStation() throws IOException {
		Network network = Network.getInstance();
		network.initializationStations();
		network.initializationLines();
		Station station = network.searchStationByName("Sha Tin");
		List<Station> stationAvail = station.searchFacilityNearStation("Toilet");
		int result = stationAvail.size();
		assertEquals(2, result);
	}
	
	@Test
	public void test01_setandgetPeopleLevel() throws IOException
	{
		Network network = Network.getInstance();
		network.initializationStations();
		Station station = network.getStations().get(7);
		station.updatePeople(10);
		int level=station.getPeopleLevel();
		assertEquals(1, level);
	}
	@Test
	public void test02_setandgetPeopleLevel() throws IOException
	{
		Network network = Network.getInstance();
		network.initializationStations();
		Station station =network.getStations().get(7);
		station.updatePeople(40);
		int level=station.getPeopleLevel();
		assertEquals(2, level);
	}
	@Test
	public void test03_setandgetPeopleLevel() throws IOException
	{
		Network network = Network.getInstance();
		network.initializationStations();
		Station station = network.getStations().get(7);
		station.updatePeople(70);
		int level=station.getPeopleLevel();
		assertEquals(3, level);
	}
	@Test
	public void test04_setandgetPeopleLevel() throws IOException
	{
		Network network = Network.getInstance();
		network.initializationStations();
		Station station = network.getStations().get(7);
		station.updatePeople(100);
		int level=station.getPeopleLevel();
		assertEquals(4, level);
	}
	@Test
	public void test01_isDuplicateInStationList() throws IOException
	{
		Network network = Network.getInstance();
		network.initializationStations();
		Station station1 = network.getStations().get(7);
		Station station2 = network.getStations().get(7);
		List<Station> stations = new ArrayList<Station>();
		stations.add(station1);
		stations.add(station2);
		assertEquals(true,Station.isDuplicateInStationList(stations,station1));
	}
	@Test
	public void test02_isDuplicateInStationList() throws IOException
	{
		Network network = Network.getInstance();
		network.initializationStations();
		Station station1 = network.getStations().get(7);
		Station station2 = network.getStations().get(8);
		Station station3 = network.getStations().get(9);
		List<Station> stations = new ArrayList<Station>();
		System.out.print(stations.size());
		stations.add(station1);
		stations.add(station2);
		assertEquals(false,Station.isDuplicateInStationList(stations,station3));
	}

}
