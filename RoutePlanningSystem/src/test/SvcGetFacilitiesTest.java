
package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameter;

import java.io.PrintStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import assets.Facilities;
import assets.Network;
import assets.Station;
import service.SvcGetFacilities;

public class SvcGetFacilitiesTest {
	@Parameter
	Network network;

	@Before
    public void setUp() throws Exception {
		network = Network.getInstance();
		network.initializationStations();
    }

	@Test
	public void test01_print_facilities() throws Exception {
		setOutput();
		Facilities f = new Facilities(false,true,true,false,true,false);
		Station s = new Station("Central",0,f);
		SvcGetFacilities getFacilities = new SvcGetFacilities();
		getFacilities.print_facilities(s);
		assertEquals("Station Central has the following facilities:\n" + 
				"Tourist Service\n" + 
				"Mobile Charging Service\n" + 
				"Toilet\n",getOutput());
	}
	@Test
	public void test02_print_facilities() throws Exception {
		setOutput();
		Facilities f = new Facilities(false,false,false,false,false,false);
		Station s = new Station("Central",0,f);
		SvcGetFacilities getFacilities = new SvcGetFacilities();
		getFacilities.print_facilities(s);
		assertEquals("Station Central has the following facilities:\n",getOutput());
	}
	@Test
	public void test03_print_facilities() throws Exception {
		setOutput();
		Facilities f = new Facilities(true,true,true,true,true,true);
		Station s = new Station("Lai Chi Kok",0,f);
		SvcGetFacilities getFacilities = new SvcGetFacilities();
		getFacilities.print_facilities(s);
		assertEquals("Station Lai Chi Kok has the following facilities:\n"
				+ "Mall\n" + 
				"Tourist Service\n" + 
				"Breast Feeding Area\n" + 
				"Mobile Charging Service\n" + 
				"Toilet\n" + 
				"WIFI\n",getOutput());
	}
	@Test
	public void test01_execute() throws Exception {
		setOutput();
		ByteArrayInputStream in = new ByteArrayInputStream("Kowloon Tong\n".getBytes());
		System.setIn(in);
		SvcGetFacilities getFacilities = new SvcGetFacilities();
		getFacilities.execute();
		assertEquals("Please enter station name: \n" + 
				"Station Kowloon Tong has the following facilities:\n" + 
				"Tourist Service\n" + 
				"Mobile Charging Service\n" + 
				"Toilet\n" + 
				"WIFI\n",getOutput());
	}
	@Test
	public void test02_execute() throws Exception {
		setOutput();
		ByteArrayInputStream in = new ByteArrayInputStream("Lo Wu\n".getBytes());
		System.setIn(in);
		SvcGetFacilities getFacilities = new SvcGetFacilities();
		getFacilities.execute();
		assertEquals("Please enter station name: \n" + 
				"Station Lo Wu has the following facilities:\n" + 
				"Tourist Service\n" + 
				"Toilet\n",getOutput());
	}
	@Test
	public void test03_execute() throws Exception {
		setOutput();
		ByteArrayInputStream in = new ByteArrayInputStream("Central\n".getBytes());
		System.setIn(in);
		SvcGetFacilities getFacilities = new SvcGetFacilities();
		getFacilities.execute();
		assertEquals("Please enter station name: \n"+
				"Station Central has the following facilities:\n" + 
				"Mall\n" + 
				"Tourist Service\n" + 
				"Breast Feeding Area\n" + 
				"Mobile Charging Service\n" + 
				"Toilet\n" + 
				"WIFI\n",getOutput());
	}
	@Test
	public void test04_execute() throws Exception{
		setOutput();
		ByteArrayInputStream in = new ByteArrayInputStream("KowLoon\n".getBytes());
		System.setIn(in);
		SvcGetFacilities getFacilities = new SvcGetFacilities();
		getFacilities.execute();
		assertEquals("Please enter station name: \nInvalid output!\n", getOutput());
	}
	@Test
	public void testSvcGetFacilitiesClassToString() throws Exception{
		ByteArrayInputStream in = new ByteArrayInputStream("University\n".getBytes());
		System.setIn(in); 
		SvcGetFacilities getFacilities = new SvcGetFacilities();
		getFacilities.execute();
		assertEquals("Get facilities at University", getFacilities.toString());
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