package test;

import org.junit.Test;
import org.junit.runners.Parameterized.Parameter;

import controller.RPS_System;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;

public class MainTest {

	@Parameter
	RPS_System testSystem;
	Object[][] menu1 = { { "1", "SearchByTime", "SearchByTime" }, { "2", "SearchByLeastTrans", "SearchByLeastTrans" },
			{ "B", "Back to Main Menu", null }, { "Q", "Quit", "quit" } };
	Object[][] menu2 = { { "1", "Search a facility", "SearchFacility" },
			{ "2", "See availible facilities", "AllFacilities" }, { "B", "Back to Main Menu", null },
			{ "Q", "Quit", "quit" } };
	Object[][] menu3 = { { "1", "GetRoute", menu1 }, { "2", "Get facility info", menu1 },
			{ "Q", "Quit", "quit" } };
	Object[][] menu4 = null;
	
	@Before
	public void setUp() {
		testSystem = RPS_System.getInstance();

	}
	
	@Test
	public void test01_setMenuTest() {
		testSystem.setMenu(menu4);
		testSystem.setMenu(menu1);
	}
	/*@Test
	public void test01_getChoice() throws IOException {
		ByteArrayInputStream in = new ByteArrayInputStream("1\r\n".getBytes());
		System.setIn(in);
		testSystem.getChoice("123BQ");
    }*/
	
	PrintStream oldPrintStream;
	ByteArrayOutputStream bos;
	
//	private void setOutput() throws Exception {
//		oldPrintStream = System.out;
//		bos = new ByteArrayOutputStream();
//		System.setOut(new PrintStream(bos));
//	}
//
//	private String getOutput() { // throws Exception
//		System.setOut(oldPrintStream);
//		return bos.toString();
//	}
}
