package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameter;

import assets.Edge;
import assets.Line;
import assets.Network;
import assets.Station;

public class NetworkTest {
	@Parameter
	Network network;
	
	@Before
    public void setUp() throws Exception {
		network = Network.getInstance();
		network.initializationStations();
		network.initializationLines();
    }
	@Test
    public void test01_GetInstance() {
		Network temp = Network.getInstance();
		assertNotNull(temp);
    }
	/*@Test
	public void testExecuteInvalidInput4() throws Exception {
		ByteArrayInputStream in = new ByteArrayInputStream("Kowloon Tong\r\nTai Wai\r\n".getBytes());
		System.setIn(in);
		//SvcLeastTimeRoute leastTimeRoute=new SvcLeastTimeRoute();
		SvcLeastTimeRoute leastTimeRoute;
		leastTimeRoute= new SvcLeastTimeRoute();
		leastTimeRoute.execute();
		assertEquals("Please type in the start station and end station\r\n" + 
				"Please enter start station: \r\n" + 
				"Please enter end station: \r\n" + 
				"Invalid Input!\r\n", getOutput());
	}*/
	@Test
	public void test01_InitializationStations() throws IOException{
		int result = network.getStationNum();
		assertEquals(92, result);
	}
	
	@Test
	public void test02_InitializationStations() throws IOException{
		List<Station> allStations = network.getStations();
		String result = allStations.get(0).getName();
		assertEquals("Central", result);
	}
	
	@Test
	public void test01_SearchStation() throws IOException{
		String result = network.searchStation(0).getName();
		assertEquals("Central", result);
	}
	@Test
	public void test02_SearchStation() throws IOException{
		String result = network.searchStation(6).getName();
		assertEquals("Shek Kip Mei", result);
	}
	@Test
	public void test03_SearchStation() throws IOException{
		String result = network.searchStation(17).getName();
		assertEquals("Cheung Sha Wan", result);
	}
	@Test(expected = IndexOutOfBoundsException.class)
	public void test04_SearchStation() throws IOException{
		Station result = network.searchStation(100);
		assertNull(result);
	}
	@Test
	public void test01_searchStationByName() throws IOException{
		String result = network.searchStationByName("Central").getName();
		assertEquals("Central", result);
	}
	@Test
	public void test02_searchStationByName() throws IOException{
		String result = network.searchStationByName("Tsuen Wan").getName();
		assertEquals("Tsuen Wan", result);
	}
	@Test
	public void test03_searchStationByName() throws IOException{
		Station result = network.searchStationByName("NOTREAL");
		assertNull(result);
	}
	
	@Test
	public void test04_searchStationByName() throws IOException{
		String result = network.searchStationByName("Kwai Hing").getName();
		assertEquals("Kwai Hing", result);
	}
	
	
	@Test
    public void test01_GetLines() {
		assertNotNull(network.getLines());
    }
	@Test
	public void test01_FindLines() {
		Line result=network.findLine(2);
		assertNotNull(result);		
	}
	@Test
	public void test02_FindLines() {
		Line line=network.findLine(2);
		String result = line.getName();
		assertEquals("EAL",result);	
	}
	@Test
	public void test03_FindLines() {
		Line line=network.findLine(0);
		String result = line.getName();
		assertEquals("AEL",result);	
	}
	@Test
	public void test04_FindLines() {
		Line line=network.findLine(999);
		assertEquals(null,line);	
	}
	
	@Test
	public void test01_InitializationLines() throws FileNotFoundException {
		List<Line> allLines = network.getLines();
		int result = allLines.size();
		assertEquals(11,result);
	}
	
	@Test
	public void test02_InitializationLines() throws FileNotFoundException {
		List<Line> allLines = network.getLines();
		Line A = allLines.get(0);
		assertEquals("AEL",A.getName());
	}
	
	@Test
	public void test03_InitializationLines() throws FileNotFoundException {
		List<Line> allLines =network.getLines();
		Line A = allLines.get(0);
		assertEquals(4,A.getEdgeCount());
	}
	
	@Test
	public void test04_InitializationLines() throws FileNotFoundException {
		List<Line> allLines =network.getLines();
		for(Line l:allLines)
			System.out.println(l.getName());
		Line A = allLines.get(0);
		List<Edge> B = A.getEdges();
		int ID = B.get(0).getStationAID();
		assertEquals(38, ID);
	}
	@Test
	public void test05_InitializationLines() throws FileNotFoundException {
		List<Line> allLines = network.getLines();
		Line A = allLines.get(3);
		assertEquals(16,A.getEdgeCount());
	}
	@Test
	public void test06_InitializationLines() throws FileNotFoundException {
		List<Line> allLines = network.getLines();
		Line A = allLines.get(10);
		assertEquals("SIL",A.getName());
	}
	@Test
	public void test07_InitializationLines() throws FileNotFoundException {
		List<Line> allLines = network.getLines();
		Line A = allLines.get(5);
		assertEquals("MOL",A.getName());
	}
	@Test
	public void test01_searchEdgeInWhichLine() throws FileNotFoundException {
		int result = network.searchEdgeInWhichLine(4);
		assertEquals(1,result);
	}
	
	@Test
	public void test02_searchEdgeInWhichLine() throws FileNotFoundException {
		int result = network.searchEdgeInWhichLine(22);
		assertEquals(3,result);
	}
	@Test
	public void test03_searchEdgeInWhichLine() throws FileNotFoundException {
		int result = network.searchEdgeInWhichLine(98);
		assertEquals(10,result);
	}
	@Test
	public void test04_searchEdgeInWhichLine() throws FileNotFoundException {
		int result = network.searchEdgeInWhichLine(77);
		assertEquals(8,result);
	}
	@Test
	public void test05_searchEdgeInWhichLine() {
		int result = network.searchEdgeInWhichLine(999);
		assertEquals(-1,result);
	}
}
