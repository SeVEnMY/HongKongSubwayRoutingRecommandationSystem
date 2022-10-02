package test;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameter;

import assets.Network;
import assets.Station;
import service.SvcLeastTransferTime;

public class SvcLeastTransferTimeTest {
	@Parameter
	Network network;

	@Before
    public void setUp() throws Exception {
		network = Network.getInstance();
		network.initializationStations();
		network.initializationLines();
    }
	@Test //complex route(4 transfer,multiple routes)
	public void testComplexRoute1() throws Exception{
		setOutput();
		Station start= network.searchStationByName("Kowloon Tong"); 
		Station end=network.searchStationByName("Airport");
		SvcLeastTransferTime leastTransferTime;
		leastTransferTime = new SvcLeastTransferTime();
		leastTransferTime.searchByLeastTrans(network, start, end);
		assertEquals("Searching for the path with least transfer...\n" + 
				"Least Transfer Time:4\n" + 
				"Route 1:\n" + 
				"Kowloon Tong->(Take KTL Line to)Yau Ma Tei or Mong Kok or Prince Edward->(Take TWL Line to)Lai King->(Take TCL Line to)Hong Kong or Kowloon or Tsing Yi->(Take AEL Line to)Airport\n" + 
				"Route 2:\n" +
				"Kowloon Tong->(Take EAL Line to)Hung Hom->(Take WRL Line to)Nam Cheong->(Take TCL Line to)Hong Kong or Kowloon or Tsing Yi->(Take AEL Line to)Airport\n" + 
				"\n", getOutput());
	}
	@Test //complex route(3 transfer,moutiple routes)
	public void testComplexRoute2() throws Exception{
		setOutput();
		Station start= network.searchStationByName("Airport"); 
		Station end=network.searchStationByName("Mei Foo");
		SvcLeastTransferTime leastTransferTime;
		leastTransferTime = new SvcLeastTransferTime();
		leastTransferTime.searchByLeastTrans(network, start, end);
		assertEquals("Searching for the path with least transfer...\n" + 
				"Least Transfer Time:3\n" + 
				"Route 1:\n" + 
				"Airport->(Take AEL Line to)Hong Kong or Kowloon or Tsing Yi->(Take TCL Line to)Lai King->(Take TWL Line to)Mei Foo\n" + 
				"Route 2:\n" + 
				"Airport->(Take AEL Line to)Hong Kong or Kowloon or Tsing Yi->(Take TCL Line to)Nam Cheong->(Take WRL Line to)Mei Foo\n"+
				"\n", getOutput());
	}
	@Test //complex route(5 transfer,1 routes)
	public void testComplexRoute3() throws Exception{
		setOutput();
		Station start= network.searchStationByName("Disneyland Resort"); 
		Station end=network.searchStationByName("Ma On Shan");
		SvcLeastTransferTime leastTransferTime;
		leastTransferTime = new SvcLeastTransferTime();
		leastTransferTime.searchByLeastTrans(network, start, end);
		assertEquals("Searching for the path with least transfer...\n" + 
				"Least Transfer Time:5\n" + 
				"Route 1:\n" + 
				"Disneyland Resort->(Take DRL Line to)Sunny Bay->(Take TCL Line to)Nam Cheong->(Take WRL Line to)Hung Hom->(Take EAL Line to)Tai Wai->(Take MOL Line to)Ma On Shan\n"
				+"\n", getOutput());
	}
	@Test //complex route(5 transfer,1 routes)
	public void testComplexRoute4() throws Exception{
		setOutput();
		Station start= network.searchStationByName("Ocean Park"); 
		Station end=network.searchStationByName("Tiu Keng Leng");
		SvcLeastTransferTime leastTransferTime;
		leastTransferTime = new SvcLeastTransferTime();
		leastTransferTime.searchByLeastTrans(network, start, end);
		assertEquals("Searching for the path with least transfer...\n" + 
				"Least Transfer Time:3\n" + 
				"Route 1:\n" + 
				"Ocean Park->(Take SIL Line to)Admiralty->(Take TWL Line to)Yau Ma Tei or Mong Kok or Prince Edward->(Take KTL Line to)Tiu Keng Leng\n" + 
				"Route 2:\n" + 
				"Ocean Park->(Take SIL Line to)Admiralty->(Take ISL Line to)North Point or Quarry Bay->(Take TKL Line to)Tiu Keng Leng\n"
				+ "\n", getOutput());
	}
	@Test //complex route(5 transfer,1 routes)
	public void testComplexRoute5() throws Exception{
		setOutput();
		Station start= network.searchStationByName("Sha Tin"); 
		Station end=network.searchStationByName("Olympic");
		SvcLeastTransferTime leastTransferTime;
		leastTransferTime = new SvcLeastTransferTime();
		leastTransferTime.searchByLeastTrans(network, start, end);
		assertEquals("Searching for the path with least transfer...\n" + 
				"Least Transfer Time:3\n" + 
				"Route 1:\n" + 
				"Sha Tin->(Take EAL Line to)Hung Hom->(Take WRL Line to)Nam Cheong->(Take TCL Line to)Olympic\n"
				+ "\n", getOutput());
	}
	@Test //simple route(0 transfer)
	public void testSimpleRoute1() throws Exception{
		setOutput();
		Station start= network.searchStationByName("Kwun Tong"); 
		Station end=network.searchStationByName("Ho Man Tin");
		SvcLeastTransferTime leastTransferTime;
		leastTransferTime = new SvcLeastTransferTime();
		leastTransferTime.searchByLeastTrans(network, start, end);
		assertEquals("Searching for the path with least transfer...\n" + 
				"Least Transfer Time:1\n" + 
				"Route 1:\n" + 
				"Kwun Tong->(Take KTL Line to)Ho Man Tin\n"+
				"\n", getOutput());
	}
	@Test //simple route(0 transfer)
	public void testSimpleRoute2() throws Exception{
		setOutput();
		Station start= network.searchStationByName("HKU"); 
		Station end=network.searchStationByName("Ocean Park");
		SvcLeastTransferTime leastTransferTime;
		leastTransferTime = new SvcLeastTransferTime();
		leastTransferTime.searchByLeastTrans(network, start, end);
		assertEquals("Searching for the path with least transfer...\n" + 
				"Least Transfer Time:2\n" + 
				"Route 1:\n" + 
				"HKU->(Take ISL Line to)Admiralty->(Take SIL Line to)Ocean Park\n" + 
		
				"\n", getOutput());
	}
	@Test //simple route(0 transfer)
	public void testSimpleRoute3() throws Exception{
		setOutput();
		Station start= network.searchStationByName("HKU"); 
		Station end=network.searchStationByName("North Point");
		SvcLeastTransferTime leastTransferTime;
		leastTransferTime = new SvcLeastTransferTime();
		leastTransferTime.searchByLeastTrans(network, start, end);
		assertEquals("Searching for the path with least transfer...\n" + 
				"Least Transfer Time:1\n" + 
				"Route 1:\n" + 
				"HKU->(Take ISL Line to)North Point\n" + 
				"\n", getOutput());
	}
	@Test //simple route(0 transfer)
	public void testSimpleRoute4() throws Exception{
		setOutput();
		Station start= network.searchStationByName("Kennedy Town"); 
		Station end=network.searchStationByName("HKU");
		SvcLeastTransferTime leastTransferTime;
		leastTransferTime = new SvcLeastTransferTime();
		leastTransferTime.searchByLeastTrans(network, start, end);
		assertEquals("Searching for the path with least transfer...\n" + 
				"Least Transfer Time:1\n" + 
				"Route 1:\n" + 
				"Kennedy Town->(Take ISL Line to)HKU\n" + 
				"\n", getOutput());
	}
	@Test //self to self
	public void testSelfToSelf() throws Exception{
		setOutput();
		ByteArrayInputStream in = new ByteArrayInputStream("Central\nCentral\n".getBytes());
		System.setIn(in);
		
		SvcLeastTransferTime leastTransferTime;
		leastTransferTime = new SvcLeastTransferTime();
		leastTransferTime.execute();
		assertEquals("Please type in the start station and end station\n" + 
				"Please enter start station: \n" + 
				"Please enter end station: \n" + 
				"You are already here.\n", getOutput());
	}
	@Test //test execute
	public void testExecute1() throws Exception {
		setOutput();
		ByteArrayInputStream in = new ByteArrayInputStream("Kowloon Tong\nTai Wai\n".getBytes());
		System.setIn(in);
		SvcLeastTransferTime leastTransferTime;
		leastTransferTime = new SvcLeastTransferTime();
		leastTransferTime.execute();
		assertEquals("Please type in the start station and end station\n" + 
				"Please enter start station: \n" + 
				"Please enter end station: \n" + 
				"From Kowloon Tong to Tai Wai\n" + 
				"Searching for the path with least transfer...\n" + 
				"Least Transfer Time:1\n" + 
				"Route 1:\n" + 
				"Kowloon Tong->(Take EAL Line to)Tai Wai\n"+
				"\n", getOutput());
	}
	@Test //test execute
	public void testExecute2() throws Exception {
		setOutput();
		ByteArrayInputStream in = new ByteArrayInputStream("Whampo\nShek Mun\n".getBytes());
		System.setIn(in);
		SvcLeastTransferTime leastTransferTime;
		leastTransferTime = new SvcLeastTransferTime();
		leastTransferTime.execute();
		assertEquals("Please type in the start station and end station\n" + 
				"Please enter start station: \n" + 
				"Please enter end station: \n" + 
				"From Whampo to Shek Mun\n" + 
				"Searching for the path with least transfer...\n" + 
				"Least Transfer Time:3\n" + 
				"Route 1:\n" + 
				"Whampo->(Take KTL Line to)Kowloon Tong->(Take EAL Line to)Tai Wai->(Take MOL Line to)Shek Mun\n"+
				"\n", getOutput());
	}
	@Test //test execute input=null
	public void testExecuteInvalidInput1() throws Exception {
		setOutput();
		ByteArrayInputStream in = new ByteArrayInputStream("Kowloon Tong\nChineseU\n".getBytes());
		System.setIn(in);
		//SvcLeastTimeRoute leastTimeRoute=new SvcLeastTimeRoute();
		SvcLeastTransferTime leastTransferTime;
		leastTransferTime = new SvcLeastTransferTime();
		leastTransferTime.execute();
		assertEquals("Please type in the start station and end station\n" + 
				"Please enter start station: \n" + 
				"Please enter end station: \n" + 
				"Invalid Input!\n", getOutput());
	}
	@Test //test execute input=null
	public void testExecuteInvalidInput2() throws Exception {
		setOutput();
		ByteArrayInputStream in = new ByteArrayInputStream("CityU\nUniversity\n".getBytes());
		System.setIn(in);
		//SvcLeastTimeRoute leastTimeRoute=new SvcLeastTimeRoute();
		SvcLeastTransferTime leastTransferTime;
		leastTransferTime = new SvcLeastTransferTime();
		leastTransferTime.execute();
		assertEquals("Please type in the start station and end station\n" + 
				"Please enter start station: \n" + 
				"Invalid Input!\n", getOutput());
	}
	@Test //test execute input=null
	public void testExecuteInvalidInput3() throws Exception {
		setOutput();
		ByteArrayInputStream in = new ByteArrayInputStream("\nUniversity\n".getBytes());
		System.setIn(in);
		//SvcLeastTimeRoute leastTimeRoute=new SvcLeastTimeRoute();
		SvcLeastTransferTime leastTransferTime;
		leastTransferTime = new SvcLeastTransferTime();
		leastTransferTime.execute();
		assertEquals("Please type in the start station and end station\n" + 
				"Please enter start station: \n" + 
				"Invalid Input!\n", getOutput());
	}
	@Test //test toString
	public void testLeastTransferTimeClassToString() throws Exception {
		ByteArrayInputStream in = new ByteArrayInputStream("Kowloon Tong\nTai Wai\n".getBytes());
		System.setIn(in);
		//SvcLeastTimeRoute leastTimeRoute=new SvcLeastTimeRoute();
		SvcLeastTransferTime leastTransferTime;
		leastTransferTime = new SvcLeastTransferTime();
		leastTransferTime.execute();
		assertEquals("Least transfer route from Kowloon Tong to Tai Wai",leastTransferTime.toString());
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
