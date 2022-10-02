package test;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameter;

import assets.Network;
import assets.Station;
import service.SvcGetRealTimeTraffic;

public class SvcGetRealTimeTrafficTest {
	@Parameter
	Network network;

	@Parameter 
	Station testStation;
	@Before
    public void setUp() throws Exception {
		network = Network.getInstance();
		network.initializationStations();
		testStation=network.searchStationByName("Kowloon Tong");
    }
	@Test
	public void testExecuteInvalidInput() throws Exception{
		setOutput();
		String instruction="Invalid Input!\n";
		ByteArrayInputStream in = new ByteArrayInputStream("FestivalWalk\n".getBytes());
		System.setIn(in); 
		SvcGetRealTimeTraffic realTimeService;
		realTimeService = new SvcGetRealTimeTraffic();
		realTimeService.execute();
		assertEquals("Please enter a station: \n"+
		instruction, getOutput());
	}
	@Test
	public void testExecuteLevel1() throws Exception{
		
		testStation.updatePeople(0);
		setOutput();
		String instruction="Real time trafic in Kowloon Tong is Less than 30\n";
		//System.out.println(preStoredTrafficLevel);
		ByteArrayInputStream in = new ByteArrayInputStream("Kowloon Tong\n".getBytes());
		System.setIn(in); 
		SvcGetRealTimeTraffic realTimeService;
		realTimeService = new SvcGetRealTimeTraffic();
		realTimeService.execute();
		assertEquals("Please enter a station: \n"+
		instruction, getOutput());
	}
	@Test
	public void testExecuteLevel2() throws Exception{
		
		testStation.updatePeople(40);
		setOutput();
		String instruction="Real time trafic in Kowloon Tong is 30-60\n";
		//System.out.println(preStoredTrafficLevel);
		ByteArrayInputStream in = new ByteArrayInputStream("Kowloon Tong\n".getBytes());
		System.setIn(in); 
		SvcGetRealTimeTraffic realTimeService;
		realTimeService = new SvcGetRealTimeTraffic();
		realTimeService.execute();
		assertEquals("Please enter a station: \n"+
		instruction, getOutput());
	}
	@Test
	public void testExecuteLevel3() throws Exception{
		
		testStation.updatePeople(70);
		setOutput();
		String instruction="Real time trafic in Kowloon Tong is 60-100\n";
		//System.out.println(preStoredTrafficLevel);
		ByteArrayInputStream in = new ByteArrayInputStream("Kowloon Tong\n".getBytes());
		System.setIn(in); 
		SvcGetRealTimeTraffic realTimeService;
		realTimeService = new SvcGetRealTimeTraffic();
		realTimeService.execute();
		assertEquals("Please enter a station: \n"+
		instruction, getOutput());
	}
	@Test
	public void testExecuteLevel4() throws Exception{
		
		testStation.updatePeople(100);
		setOutput();
		String instruction="Real time trafic in Kowloon Tong is More than 100\n";
		//System.out.println(preStoredTrafficLevel);
		ByteArrayInputStream in = new ByteArrayInputStream("Kowloon Tong\n".getBytes());
		System.setIn(in); 
		SvcGetRealTimeTraffic realTimeService;
		realTimeService = new SvcGetRealTimeTraffic();
		realTimeService.execute();
		assertEquals("Please enter a station: \n"+
		instruction, getOutput());
	}
	@Test
	public void testGetRealTimeTrafficClassToString() throws Exception{
		ByteArrayInputStream in = new ByteArrayInputStream("University\n".getBytes());
		System.setIn(in); 
		SvcGetRealTimeTraffic realTimeService;
		realTimeService = new SvcGetRealTimeTraffic();
		realTimeService.execute();
		assertEquals("Get Real Time trafic at University", realTimeService.toString());
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
