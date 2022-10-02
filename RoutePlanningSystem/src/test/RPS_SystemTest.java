package test;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

import controller.RPS_System;

public class RPS_SystemTest {
	@Test
	public void test_doMenu_01() throws Exception {
		ByteArrayInputStream in = new ByteArrayInputStream("Q\n".getBytes());
		System.setIn(in);
		setOutput();
        Object[][] ROUTE_MENU = { // cmd, desc, class
                {"1", "SearchByTime", "SvcLeastTimeRoute"},
                {"2", "SearchByLeastTrans", "SvcLeastTransferRoute"},
                {"B", "Back to Main Menu",null},
                {"Q", "Quit","quit"}
        };
       
        Object[][] GETINFO_MENU = {
        		{"1", "Search a facility", "SvcSearchFacility"},
        		{"2", "See availible facilities", "SvcGetFacilities"},
                {"B", "Back to Main Menu",null},
                {"Q", "Quit","quit"}
        };

        // main menu
        Object[][] MENU = { // cmd, desc, method
                {"1", "GetRoute", ROUTE_MENU},
                { "2", "Get facility info", GETINFO_MENU },
                { "3","GetRealTimeTraffic","GetRealTimeTraffic"},
                {"Q","Quit","quit"}
        };
        String title = "Route Planning System";
        RPS_System system = RPS_System.getInstance();
        system.doMenu(title, MENU);
        assertEquals("\n" + 
        		"---   Route Planning System   ---\n" + 
        		"\n" + 
        		"Please choose menu item:\n" + 
        		"[1]  GetRoute\n" + 
        		"[2]  Get facility info\n" + 
        		"[3]  GetRealTimeTraffic\n" + 
        		"[Q]  Quit\n" + 
        		"> Bye!\n",getOutput());
	}
	
	@Test
	public void test_doMenu_02() throws Exception {
		ByteArrayInputStream in = new ByteArrayInputStream("1\nQ\n".getBytes());
		System.setIn(in);
		setOutput();
        Object[][] ROUTE_MENU = { // cmd, desc, class
                {"1", "SearchByTime", "SvcLeastTimeRoute"},
                {"2", "SearchByLeastTrans", "SvcLeastTransferRoute"},
                {"B", "Back to Main Menu",null},
                {"Q", "Quit","quit"}
        };
       
        Object[][] GETINFO_MENU = {
        		{"1", "Search a facility", "SvcSearchFacility"},
        		{"2", "See availible facilities", "SvcGetFacilities"},
                {"B", "Back to Main Menu",null},
                {"Q", "Quit","quit"}
        };

        // main menu
        Object[][] MENU = { // cmd, desc, method
                {"1", "GetRoute", ROUTE_MENU},
                { "2", "Get facility info", GETINFO_MENU },
                { "3","GetRealTimeTraffic","GetRealTimeTraffic"},
                {"Q","Quit","quit"}
        };
        String title = "Route Planning System";
        RPS_System system = RPS_System.getInstance();
        system.doMenu(title, MENU);
        assertEquals("\n" + 
        		"---   Route Planning System   ---\n" + 
        		"\n" + 
        		"Please choose menu item:\n" + 
        		"[1]  GetRoute\n" + 
        		"[2]  Get facility info\n" + 
        		"[3]  GetRealTimeTraffic\n" + 
        		"[Q]  Quit\n" + 
        		"> \n" + 
        		"---   GetRoute   ---\n" + 
        		"\n" + 
        		"Please choose menu item:\n" + 
        		"[1]  SearchByTime\n" + 
        		"[2]  SearchByLeastTrans\n" + 
        		"[B]  Back to Main Menu\n" + 
        		"[Q]  Quit\n" + 
        		"> Bye!\n",getOutput());
	}
	
	@Test
	public void test_doMenu_03() throws Exception {
		ByteArrayInputStream in = new ByteArrayInputStream("2\nB\nQ\n".getBytes());
		System.setIn(in);
		setOutput();
        Object[][] ROUTE_MENU = { // cmd, desc, class
                {"1", "SearchByTime", "SvcLeastTimeRoute"},
                {"2", "SearchByLeastTrans", "SvcLeastTransferRoute"},
                {"B", "Back to Main Menu",null},
                {"Q", "Quit","quit"}
        };
       
        Object[][] GETINFO_MENU = {
        		{"1", "Search a facility", "SvcSearchFacility"},
        		{"2", "See availible facilities", "SvcGetFacilities"},
                {"B", "Back to Main Menu",null},
                {"Q", "Quit","quit"}
        };

        // main menu
        Object[][] MENU = { // cmd, desc, method
                {"1", "GetRoute", ROUTE_MENU},
                { "2", "Get facility info", GETINFO_MENU },
                { "3","GetRealTimeTraffic","GetRealTimeTraffic"},
                {"Q","Quit","quit"}
        };
        String title = "Route Planning System";
        RPS_System system = RPS_System.getInstance();
        system.doMenu(title, MENU);
        assertEquals("\n" + 
        		"---   Route Planning System   ---\n" + 
        		"\n" + 
        		"Please choose menu item:\n" + 
        		"[1]  GetRoute\n" + 
        		"[2]  Get facility info\n" + 
        		"[3]  GetRealTimeTraffic\n" + 
        		"[Q]  Quit\n" + 
        		"> \n" + 
        		"---   Get facility info   ---\n" + 
        		"\n" + 
        		"Please choose menu item:\n" + 
        		"[1]  Search a facility\n" + 
        		"[2]  See availible facilities\n" + 
        		"[B]  Back to Main Menu\n" + 
        		"[Q]  Quit\n" + 
        		"> \n" + 
        		"---   Route Planning System   ---\n" + 
        		"\n" + 
        		"Please choose menu item:\n" + 
        		"[1]  GetRoute\n" + 
        		"[2]  Get facility info\n" + 
        		"[3]  GetRealTimeTraffic\n" + 
        		"[Q]  Quit\n" + 
        		"> Bye!\n",getOutput());
	}
	
	@Test
	public void test_doMenu_04() throws Exception {
		ByteArrayInputStream in = new ByteArrayInputStream("5\nQ\n".getBytes());
		System.setIn(in);
		setOutput();
        Object[][] ROUTE_MENU = { // cmd, desc, class
                {"1", "SearchByTime", "SvcLeastTimeRoute"},
                {"2", "SearchByLeastTrans", "SvcLeastTransferRoute"},
                {"B", "Back to Main Menu",null},
                {"Q", "Quit","quit"}
        };
       
        Object[][] GETINFO_MENU = {
        		{"1", "Search a facility", "SvcSearchFacility"},
        		{"2", "See availible facilities", "SvcGetFacilities"},
                {"B", "Back to Main Menu",null},
                {"Q", "Quit","quit"}
        };

        // main menu
        Object[][] MENU = { // cmd, desc, method
                {"1", "GetRoute", ROUTE_MENU},
                { "2", "Get facility info", GETINFO_MENU },
                { "3","GetRealTimeTraffic","GetRealTimeTraffic"},
                {"Q","Quit","quit"}
        };
        String title = "Route Planning System";
        RPS_System system = RPS_System.getInstance();
        system.doMenu(title, MENU);
        assertEquals("\n" + 
        		"---   Route Planning System   ---\n" + 
        		"\n" + 
        		"Please choose menu item:\n" + 
        		"[1]  GetRoute\n" + 
        		"[2]  Get facility info\n" + 
        		"[3]  GetRealTimeTraffic\n" + 
        		"[Q]  Quit\n" + 
        		"> \n" + 
        		"*** Must be one of the choices: 123Q\n" + 
        		"> Bye!\n" ,getOutput());
	}
	
	@Test
	public void test_run_01() throws Exception {
		ByteArrayInputStream in = new ByteArrayInputStream("1\nB\nQ\n".getBytes());
		System.setIn(in);
		setOutput();
        RPS_System system = RPS_System.getInstance();
        system.run();
        assertEquals("\n" + 
        		"---   Route Planning System   ---\n" + 
        		"\n"+
        		"Please choose menu item:\n" + 
        		"[1]  GetRoute\n" + 
        		"[2]  Get facility info\n" + 
        		"[3]  GetRealTimeTraffic\n" + 
        		"[4]  ShowHistory\n" + 
        		"[Q]  Quit\n" + 
        		"> \n" + 
        		"---   GetRoute   ---\n" + 
        		"\n" + 
        		"Please choose menu item:\n" + 
        		"[1]  SearchByTime\n" + 
        		"[2]  SearchByLeastTrans\n" + 
        		"[B]  Back to Main Menu\n" + 
        		"[Q]  Quit\n" + 
        		"> \n" + 
        		"---   Route Planning System   ---\n" + 
        		"\n" + 
        		"Please choose menu item:\n" + 
        		"[1]  GetRoute\n" + 
        		"[2]  Get facility info\n" + 
        		"[3]  GetRealTimeTraffic\n" + 
        		"[4]  ShowHistory\n" + 
        		"[Q]  Quit\n" + 
        		"> Bye!\n" ,getOutput());
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
