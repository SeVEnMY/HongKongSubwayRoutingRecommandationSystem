
package test;

import static org.junit.Assert.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameter;

import assets.Network;
import service.SvcSearchFacility;

public class SvcSearchFacilityTest {
	@Parameter
	SvcSearchFacility ssf = new SvcSearchFacility();
	@Parameter
	Network network;

	@Before
    public void setUp() throws Exception {
		network = Network.getInstance();
		network.initializationStations();
		network.initializationLines();
    }
	
	@Test
	public void test01_PrintMenu() throws Exception {
		setOutput();
		Object[][] FACILITY_MENU = {
				{"1", "WIFI"},
				{"2", "Mobile Charging Service"},
				{"3", "Toilet"},
				{"4", "Mall"},
				{"5", "Tourist Service"},
				{"6", "Breast Feeding Area"},
		};
		ssf.printMenu(FACILITY_MENU);
		assertEquals("[1]  WIFI\n" + 
				"[2]  Mobile Charging Service\n" + 
				"[3]  Toilet\n" + 
				"[4]  Mall\n" + 
				"[5]  Tourist Service\n" + 
				"[6]  Breast Feeding Area\n",getOutput());	
	}
	
	@Test
	public void test02_PrintMenu() throws Exception {
		setOutput();
		Object[][] GETINFO_MENU = {
		    	{"1", "Search a facility", "SearchFacility"},
				{"2", "See availible facilities", "AllFacilities"},
		        {"B", "Back to Main Menu",null},
		        {"Q", "Quit","quit"}
		};
		ssf.printMenu(GETINFO_MENU);
		assertEquals("[1]  Search a facility\n" + 
				"[2]  See availible facilities\n" + 
				"[B]  Back to Main Menu\n" + 
				"[Q]  Quit\n",getOutput());	
	}
	
	@Test
	public void test01_GetChoice() throws IOException {
		String input = "1";
		Object[][] FACILITY_MENU = {
				{"1", "WIFI"},
				{"2", "Mobile Charging Service"},
				{"3", "Toilet"},
				{"4", "Mall"},
				{"5", "Tourist Service"},
				{"6", "Breast Feeding Area"},
		};
		String result = ssf.getChoice(FACILITY_MENU,input);
		assertEquals("WIFI",result);
	}
	
	@Test
	public void test02_GetChoice() throws IOException {
		String input = "3";
		Object[][] FACILITY_MENU = {
				{"1", "WIFI"},
				{"2", "Mobile Charging Service"},
				{"3", "Toilet"},
				{"4", "Mall"},
				{"5", "Tourist Service"},
				{"6", "Breast Feeding Area"},
		};
		String result = ssf.getChoice(FACILITY_MENU,input);
		assertEquals("Toilet",result);
	}
	@Test
	public void test03_GetChoice() throws IOException {
		String input = "7";
		Object[][] FACILITY_MENU = {
				{"1", "WIFI"},
				{"2", "Mobile Charging Service"},
				{"3", "Toilet"},
				{"4", "Mall"},
				{"5", "Tourist Service"},
				{"6", "Breast Feeding Area"},
		};
		String result = ssf.getChoice(FACILITY_MENU,input);
		assertEquals("Wrong",result);
	}
	
	@Test
	public void test01_Execute() throws Exception {
		ByteArrayInputStream in = new ByteArrayInputStream("Kowloon Tong\n1\n".getBytes());
		System.setIn(in);
		setOutput();
		ssf.execute();
		assertEquals("Please type in the station name and the facility type\n" + 
				"Please enter station name: \n" + 
				"Please enter facility type: \n" + 
				"[1]  WIFI\n" + 
				"[2]  Mobile Charging Service\n" + 
				"[3]  Toilet\n" + 
				"[4]  Mall\n" + 
				"[5]  Tourist Service\n" + 
				"[6]  Breast Feeding Area\n" + 
				"Station Kowloon Tong has WIFI\n",getOutput());
		
	}
	@Test
	public void test02_Execute() throws Exception {
		ByteArrayInputStream in = new ByteArrayInputStream("Kowloon Tong\n4\n1\n".getBytes());
		System.setIn(in);
		setOutput();
		ssf.execute();
		assertEquals("Please type in the station name and the facility type\n" + 
				"Please enter station name: \n" + 
				"Please enter facility type: \n" + 
				"[1]  WIFI\n" + 
				"[2]  Mobile Charging Service\n" + 
				"[3]  Toilet\n" + 
				"[4]  Mall\n" + 
				"[5]  Tourist Service\n" + 
				"[6]  Breast Feeding Area\n" + 
				"Sorry, station Kowloon Tong does not have Mall\n" + 
				"Do you want to search nearby stations?\n" + 
				"[1]  Yes\n" + 
				"[2]  No, back to the main menu.\n" + 
				"Sorry, there is no station near Kowloon Tong with Mall\n",getOutput());
	}
	
	@Test
	public void test03_Execute() throws Exception {
		ByteArrayInputStream in = new ByteArrayInputStream("Kowloon Tong\n6\n1\n".getBytes());
		System.setIn(in);
		setOutput();
		ssf.execute();
		assertEquals("Please type in the station name and the facility type\n" + 
				"Please enter station name: \n" + 
				"Please enter facility type: \n" + 
				"[1]  WIFI\n" + 
				"[2]  Mobile Charging Service\n" + 
				"[3]  Toilet\n" + 
				"[4]  Mall\n" + 
				"[5]  Tourist Service\n" + 
				"[6]  Breast Feeding Area\n" + 
				"Sorry, station Kowloon Tong does not have Breast Feeding Area\n" + 
				"Do you want to search nearby stations?\n" + 
				"[1]  Yes\n" + 
				"[2]  No, back to the main menu.\n" + 
				"Mong Kok East\n" ,getOutput());
	}
	
	@Test
	public void test04_Execute() throws Exception {
		ByteArrayInputStream in = new ByteArrayInputStream("Kowloon\n6\n1\n".getBytes());
		System.setIn(in);
		setOutput();
		ssf.execute();
		assertEquals("Please type in the station name and the facility type\n" + 
				"Please enter station name: \n" + 
				"Please enter facility type: \n" + 
				"[1]  WIFI\n" + 
				"[2]  Mobile Charging Service\n" + 
				"[3]  Toilet\n" + 
				"[4]  Mall\n" + 
				"[5]  Tourist Service\n" + 
				"[6]  Breast Feeding Area\n" + 
				"Sorry, station Kowloon does not have Breast Feeding Area\n" + 
				"Do you want to search nearby stations?\n" + 
				"[1]  Yes\n" + 
				"[2]  No, back to the main menu.\n" + 
				"Lai King\n" +
				"Sunny Bay\n",getOutput());
	}
	
	@Test
	public void test05_Execute() throws Exception {
		ByteArrayInputStream in = new ByteArrayInputStream("Kowloon Tong\n12\n".getBytes());
		System.setIn(in);
		setOutput();
		ssf.execute();
		assertEquals("Please type in the station name and the facility type\n" + 
				"Please enter station name: \n" + 
				"Please enter facility type: \n" + 
				"[1]  WIFI\n" + 
				"[2]  Mobile Charging Service\n" + 
				"[3]  Toilet\n" + 
				"[4]  Mall\n" + 
				"[5]  Tourist Service\n" + 
				"[6]  Breast Feeding Area\n" + 
				"*** Must be one of the choices: 123456\n",getOutput());
	}
	@Test 
	public void testSvcSearchFacilityClassToString() throws Exception {
		ByteArrayInputStream in = new ByteArrayInputStream("Kowloon Tong\n1\n".getBytes());
		System.setIn(in);
		//SvcLeastTimeRoute leastTimeRoute=new SvcLeastTimeRoute();
		SvcSearchFacility searchFacility;
		searchFacility = new SvcSearchFacility();
		searchFacility.execute();
		assertEquals("Search facilities [WIFI] at Kowloon Tong",searchFacility.toString());
	}
	PrintStream oldPrintStream;
	ByteArrayOutputStream bos;
	
	
	private void setOutput() throws Exception {
		oldPrintStream = System.out;
		bos = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bos));
	}

	private String getOutput() { // throws Exception
		System.setOut(oldPrintStream);
		return bos.toString();
	}
	

}