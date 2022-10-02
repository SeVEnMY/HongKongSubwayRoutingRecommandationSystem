package test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameter;
import assets.Network;
import service.RecordedService;
import service.SvcGetFacilities;
import service.SvcLeastTimeRoute;
import service.SvcLeastTransferTime;
import service.SvcSearchFacility;

public class RecordedServiceTest {
	
	@Parameter
	Network network;

	@Before
    public void setUp() throws Exception {
		network = Network.getInstance();
		network.initializationStations();
		network.initializationLines();
    }
	@Test
	public void test01_ResetHistory() throws Exception {
		RecordedService.resetHistory();
		ByteArrayInputStream in = new ByteArrayInputStream("Kowloon Tong\nTai Wai\n".getBytes());
		System.setIn(in);
		new SvcLeastTimeRoute().execute();
		setOutput();	
		RecordedService.resetHistory();
		RecordedService.showHistory();		
		assertEquals("",getOutput());
	}
	
	@Test
	public void test01_ShowHistoryTest() throws Exception{
		RecordedService.resetHistory();
		ByteArrayInputStream in = new ByteArrayInputStream("Kowloon Tong\nTai Wai\n".getBytes());
		System.setIn(in);
		new SvcLeastTimeRoute().execute();
		setOutput();		
		RecordedService.showHistory();		
		assertEquals("Least time route from Kowloon Tong to Tai Wai\n",getOutput());
	}
	
	@Test
	public void test02_ShowHistoryTest() throws Exception{

		RecordedService.resetHistory();
		ByteArrayInputStream in = new ByteArrayInputStream("Kowloon Tong\nCentral\n".getBytes());
		System.setIn(in);
		new SvcLeastTransferTime().execute();
		setOutput();		
		RecordedService.showHistory();		
		assertEquals("Least transfer route from Kowloon Tong to Central\n",getOutput());
	}
	
	@Test
	public void test03_ShowHistoryTest() throws Exception{
		ByteArrayInputStream in = new ByteArrayInputStream("Tsuen Wan\n".getBytes());
		System.setIn(in);
		SvcGetFacilities getFacilities = new SvcGetFacilities();
		getFacilities.execute();
		setOutput();		
		RecordedService.showHistory();		
		assertEquals("Get facilities at Tsuen Wan\n",getOutput());
	}
	@Test
	public void test04_ShowHistoryTest() throws Exception{
		RecordedService.resetHistory();
		ByteArrayInputStream in = new ByteArrayInputStream("University\n1\n2\n".getBytes());
		System.setIn(in);
		new SvcSearchFacility().execute();
		setOutput();		
		RecordedService.showHistory();		
		assertEquals("Search facilities [WIFI] at University\n" ,getOutput());
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