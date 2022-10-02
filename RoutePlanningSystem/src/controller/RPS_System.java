package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import assets.Network;

public class RPS_System {

	private static final RPS_System instance = new RPS_System();
	private Network network;

	// private constructor to avoid client applications to use constructor
	private RPS_System() {
		try {
			network=Network.getInstance();
			network.initializationStations();
			network.initializationLines();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static RPS_System getInstance() {
		return instance;
	}

	private String title = "Route Planning System";
	public void setMenu(Object[][] menu) {
		if (menu != null) {
		}
	}

	public String getChoice(String choices, BufferedReader _stdin) throws IOException {
		while (true) {
			System.out.print("> ");
			System.out.flush();
			String line = _stdin.readLine().trim();
			if (line.length() == 1) {
				int choice = Character.toUpperCase(line.charAt(0));
				int index = choices.indexOf(choice);
				if (index != -1)
					return choices.substring(index, index + 1);
			}
			System.out.println("\n*** Must be one of the choices: " + choices);
		}
	}

	// 12Q,12BQ
	public void doMenu(String pTitle, Object[][] menu) {
		BufferedReader _stdin = new BufferedReader(new InputStreamReader(System.in));
		String pTitle_previous = pTitle;
		Object[][] menu_previous = menu;
		String pTitle_cur = pTitle;
		Object[][] menu_cur = menu;
		while (true) {
			try {
				StringBuffer sb = new StringBuffer(menu_cur.length);
				for (int i = 0; i < menu_cur.length; i++) {
					sb.append(menu_cur[i][0]);
				}
				String choices = sb.toString();
				String choice;
				System.out.println("\n---   " + pTitle_cur + "   ---");
				System.out.println("\nPlease choose menu item:");
				for (int i = 0; i < menu_cur.length; i++)
					System.out.println("[" + menu_cur[i][0] + "]  " + menu_cur[i][1]);
				// Get the user's selection.
				choice = getChoice(choices, _stdin);
				// System.out.println("choice = " + choice);

				for (int i = 0; i < menu_cur.length; i++) {
					Object[] entry = menu_cur[i];

					if (entry[0].equals(choice)) {
						Object action = menu_cur[i][2];

						if (action == null) {
							pTitle_cur = pTitle_previous;
							menu_cur = menu_previous;

						} else if (action instanceof String) {
							// Methods in ROUTE_MENU
							if (!action.equals("quit")) {
								@SuppressWarnings("deprecation")
								CmdService svc = (CmdService) Class
										.forName("service." + (String) action).newInstance();
								svc.execute();
							} else {
								System.out.println("Bye!");
								return;
							}

						} else {
							pTitle_cur = (String) entry[1];
							menu_cur = (Object[][]) action;
						}
					}
				}

			} catch (Exception e) {
				Throwable t = e;
				System.out.println("\nException: " + t);
			}
		} // while end
	}

	public void run() {
		Object[][] ROUTE_MENU = { // cmd, desc, class
				{ "1", "SearchByTime", "SvcLeastTimeRoute" }, { "2", "SearchByLeastTrans", "SvcLeastTransferTime" },
				{ "B", "Back to Main Menu", null }, { "Q", "Quit", "quit" } };

		Object[][] GETINFO_MENU = { { "1", "Search a facility", "SvcSearchFacility" },
				{ "2", "See availible facilities", "SvcGetFacilities" }, { "B", "Back to Main Menu", null },
				{ "Q", "Quit", "quit" } };

		// main menu
		Object[][] MENU = { // cmd, desc, method
				{ "1", "GetRoute", ROUTE_MENU }, { "2", "Get facility info", GETINFO_MENU },
				{ "3","GetRealTimeTraffic","SvcGetRealTimeTraffic"},
				{"4","ShowHistory","SvcShowHistory"},
				{ "Q", "Quit", "quit" } };
		this.setMenu(MENU);
		this.doMenu(title, MENU);
	}

}