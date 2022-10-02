
package service;

import java.util.*;

import controller.CmdService;

public abstract class RecordedService implements CmdService {

	private static ArrayList<RecordedService> history = new ArrayList<>();
	
	protected static void addToHistory(RecordedService cmd) {
		history.add(cmd);
	}
	public static void showHistory() {
		for (RecordedService cmd : history) {
			System.out.println(cmd.toString());
		}
	}
	public static void resetHistory() {
		history.clear();
	}

}