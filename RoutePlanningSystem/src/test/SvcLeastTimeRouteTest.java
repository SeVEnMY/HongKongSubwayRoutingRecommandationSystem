package test;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameter;

import java.io.PrintStream;

import assets.Network;
import assets.Station;
import service.SvcLeastTimeRoute;

public class SvcLeastTimeRouteTest {
	@Parameter
	Network network;

	@Before
    public void setUp() throws Exception {
		network = Network.getInstance();
		network.initializationStations();
		network.initializationLines();
    }
	@Test //complex route
	public void testComplexRoute1() throws Exception{
		setOutput();
		Station start= network.searchStationByName("Lo Wu"); 
		Station end=network.searchStationByName("AsiWorld-Expo");
		SvcLeastTimeRoute leastTimeRoute;
		leastTimeRoute= new SvcLeastTimeRoute();
		leastTimeRoute.searchByTime(network, start, end);
		assertEquals("Searching for the path with least time...\n" +
				"Shortest Path:\n" + 
				"Lo Wu->Sheung Shui->Fanling->Tai Wo->Tai Po Market->University->Fo Tan->Sha Tin->Tai Wai->Kowloon Tong->Mong Kok East->Hung Hom->East Tsim Sha Tsui->Austin->Nam Cheong->Lai King->Tsing Yi->Airport->AsiWorld-Expo\n" + 
				"Least Time:\n" + 
				"74.16\n", getOutput());
	}
	@Test //complex route
	public void testComplexRoute2() throws Exception{
		setOutput();
		Station start= network.searchStationByName("Lok Fu"); 
		Station end=network.searchStationByName("Hong Kong");
		SvcLeastTimeRoute leastTimeRoute;
		leastTimeRoute= new SvcLeastTimeRoute();
		leastTimeRoute.searchByTime(network, start, end);
		assertEquals("Searching for the path with least time...\n" +
				"Shortest Path:\n" + 
				"Lok Fu->Kowloon Tong->Mong Kok East->Hung Hom->East Tsim Sha Tsui->Austin->Nam Cheong->Olympic->Kowloon->Hong Kong\n" + 
				"Least Time:\n" + 
				"37.309998\n", getOutput());
	}
	@Test //simple route
	public void testSimpleRoute1() throws Exception{
		setOutput();
		Station start= network.searchStationByName("Kowloon Tong"); 
		Station end=network.searchStationByName("Wong Tai Sin");
		SvcLeastTimeRoute leastTimeRoute;
		leastTimeRoute= new SvcLeastTimeRoute();
		leastTimeRoute.searchByTime(network, start, end);
		assertEquals("Searching for the path with least time...\n" +
				"Shortest Path:\n" + 
				"Kowloon Tong->Lok Fu->Wong Tai Sin\n" + 
				"Least Time:\n" + 
				"6.5\n", getOutput());
	}
	@Test //simple route
	public void testSimpleRoute2() throws Exception{
		setOutput();
		Station start= network.searchStationByName("Yau Tong"); 
		Station end=network.searchStationByName("Tseung Kwan O");
		SvcLeastTimeRoute leastTimeRoute;
		leastTimeRoute= new SvcLeastTimeRoute();
		leastTimeRoute.searchByTime(network, start, end);
		assertEquals("Searching for the path with least time...\n" + 
				"Shortest Path:\n" + 
				"Yau Tong->Tiu Keng Leng->Tseung Kwan O\n" + 
				"Least Time:\n" + 
				"6.6\n", getOutput());
	}
	@Test //self to self
	public void testSelfToSelf() throws Exception{
		setOutput();
		ByteArrayInputStream in = new ByteArrayInputStream("Central\nCentral\n".getBytes());
		System.setIn(in);
		
		SvcLeastTimeRoute leastTimeRoute;
		leastTimeRoute= new SvcLeastTimeRoute();
		leastTimeRoute.execute();
		assertEquals("Please type in the start station and end station\n" + 
				"Please enter start station: \n" + 
				"Please enter end station: \n" + 
				"You are already here.\n", getOutput());
	}
	@Test //test execute
	public void testExecute() throws Exception {
		setOutput();
		ByteArrayInputStream in = new ByteArrayInputStream("Kowloon Tong\nTai Wai\n".getBytes());
		System.setIn(in);
		//SvcLeastTimeRoute leastTimeRoute=new SvcLeastTimeRoute();
		SvcLeastTimeRoute leastTimeRoute;
		leastTimeRoute= new SvcLeastTimeRoute();
		leastTimeRoute.execute();
		assertEquals("Please type in the start station and end station\n" + 
				"Please enter start station: \n" + 
				"Please enter end station: \n" + 
				"From Kowloon Tong to Tai Wai\n" +
				"Searching for the path with least time...\n" +
				"Shortest Path:\n" + 
				"Kowloon Tong->Tai Wai\n" + 
				"Least Time:\n" + 
				"4.0\n", getOutput());
	}
	@Test //test execute null
	public void testExecuteInvalidInput1() throws Exception {
		setOutput();
		ByteArrayInputStream in = new ByteArrayInputStream("Kowloon Tong\nChineseU\n".getBytes());
		System.setIn(in);
		//SvcLeastTimeRoute leastTimeRoute=new SvcLeastTimeRoute();
		SvcLeastTimeRoute leastTimeRoute;
		leastTimeRoute= new SvcLeastTimeRoute();
		leastTimeRoute.execute();
		assertEquals("Please type in the start station and end station\n" + 
				"Please enter start station: \n" + 
				"Please enter end station: \n" + 
				"Invalid Input!\n", getOutput());
	}
	@Test
	public void testExecuteInvalidInput2() throws Exception {
		setOutput();
		ByteArrayInputStream in = new ByteArrayInputStream("CityU\nUniversity\n".getBytes());
		System.setIn(in);
		//SvcLeastTimeRoute leastTimeRoute=new SvcLeastTimeRoute();
		SvcLeastTimeRoute leastTimeRoute;
		leastTimeRoute= new SvcLeastTimeRoute();
		leastTimeRoute.execute();
		assertEquals("Please type in the start station and end station\n" + 
				"Please enter start station: \n" + 
				"Invalid Input!\n", getOutput());
	}
	@Test
	public void testExecuteInvalidInput3() throws Exception {
		setOutput();
		ByteArrayInputStream in = new ByteArrayInputStream("CityU\n \n".getBytes());
		System.setIn(in);
		//SvcLeastTimeRoute leastTimeRoute=new SvcLeastTimeRoute();
		SvcLeastTimeRoute leastTimeRoute;
		leastTimeRoute= new SvcLeastTimeRoute();
		leastTimeRoute.execute();
		assertEquals("Please type in the start station and end station\n" + 
				"Please enter start station: \n" + 
				"Invalid Input!\n", getOutput());
	}
	@Test //test toString
	public void testLeastTransferTimeClassToString() throws Exception {
		ByteArrayInputStream in = new ByteArrayInputStream("Kowloon Tong\nTai Wai\n".getBytes());
		System.setIn(in);
		//SvcLeastTimeRoute leastTimeRoute=new SvcLeastTimeRoute();
		SvcLeastTimeRoute leastTimeRoute;
		leastTimeRoute = new SvcLeastTimeRoute();
		leastTimeRoute.execute();
		assertEquals("Least time route from Kowloon Tong to Tai Wai",leastTimeRoute.toString());
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
