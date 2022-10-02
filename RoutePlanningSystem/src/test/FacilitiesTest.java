package test;

import org.junit.Test;

import assets.Facilities;

import static org.junit.Assert.*;

import static org.junit.Assert.assertArrayEquals;

import java.util.List;

public class FacilitiesTest {
	
	@Test
	public void test01_listFacilities() {
		Facilities facilities = new Facilities(false,true,true,false,true,false);
		int result = facilities.listFacilities().size();
		assertEquals(3, result);
	}
	@Test
	public void test02_listFacilities() {
		Facilities facilities = new Facilities(true,true,true,false,true,false);
		int result = facilities.listFacilities().size();
		assertEquals(4, result);
	}
	@Test
	public void test03_listFacilities() {
		Facilities facilities = new Facilities(false,false,false,false,false,false);
		int result = facilities.listFacilities().size();
		assertEquals(0, result);
	}
	@Test
	public void test04_listFacilities() {
		Facilities facilities = new Facilities(false,true,true,false,true,false);
		List<String> list = facilities.listFacilities();
		String[] result = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			result[i] = list.get(i);
		}
		String[] expected = new String[3];
		expected[0] = "Tourist Service";
		expected[1] = "Mobile Charging Service";
		expected[2] = "Toilet";
		assertArrayEquals(expected, result);
	}
	
	@Test
	public void test05_listFacilities() {
		Facilities facilities = new Facilities(false,false,false,false,false,false);
		assertEquals(0, facilities.listFacilities().size());
	}
	
	@Test
	public void test06_listFacilities() {
		Facilities facilities = new Facilities(true,true,true,true,true,true);
		List<String> list = facilities.listFacilities();
		String[] result = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			result[i] = list.get(i);
		}
		String[] expected = new String[6];
		expected[0] = "Mall";
		expected[1] = "Tourist Service";
		expected[2] = "Breast Feeding Area";
		expected[3] = "Mobile Charging Service";
		expected[4] = "Toilet";
		expected[5] = "WIFI";
		assertArrayEquals(expected, result);
	}
	
	@Test
	public void test01_getAvailability() {
		Facilities facilities = new Facilities(false,true,true,false,true,false);
		boolean result = facilities.getAvailability("WIFI");
		assertEquals(false, result);
	}

	
	
	@Test
	public void test02_getAvailability() {
		Facilities facilities = new Facilities(false,true,true,false,true,false);
		boolean result = facilities.getAvailability("Tourist Service");
		assertEquals(true, result);
	}
	
	@Test
	public void test03_getAvailability() {
		Facilities facilities = new Facilities(false,true,true,false,true,false);
		boolean result = facilities.getAvailability("Breast Feeding Area");
		assertEquals(false, result);
	}
	
	@Test
	public void test04_getAvailability() {
		Facilities facilities = new Facilities(false,true,true,false,true,false);
		boolean result = facilities.getAvailability("Mobile Charging Service");
		assertEquals(true, result);
	}
	
	@Test
	public void test05_getAvailability() {
		Facilities facilities = new Facilities(false,true,true,false,true,false);
		boolean result = facilities.getAvailability("Mall");
		assertEquals(false, result);
	}
	
	@Test
	public void test06_getAvailability() {
		Facilities facilities = new Facilities(false,true,true,false,true,false);
		boolean result = facilities.getAvailability("Toilet");
		assertEquals(true, result);
	}
}
