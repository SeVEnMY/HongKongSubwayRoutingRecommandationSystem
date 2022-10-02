package assets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Map;

/**
 * Class for the availability of facilities in a station
 * 
 * @author LI Shiying Cherine
 * @version 1.0, 13/10/2018
 */

public class Facilities {

	private Map<String, Boolean> facilities;

	/**
	 * @param _hasWIFI
	 * @param _hasMobileCharging
	 * @param _hasToilet
	 * @param _hasMall
	 * @param _hasTouristService
	 * @param _hasFeedingArea
	 */
	public Facilities(boolean _hasWIFI, boolean _hasMobileCharging, boolean _hasToilet, boolean _hasMall,
			boolean _hasTouristService, boolean _hasFeedingArea) {
		Map<String, Boolean> facilities = new HashMap<String, Boolean>();
		facilities.put("WIFI", _hasWIFI);
		facilities.put("Mobile Charging Service", _hasMobileCharging);
		facilities.put("Toilet", _hasToilet);
		facilities.put("Mall", _hasMall);
		facilities.put("Tourist Service", _hasTouristService);
		facilities.put("Breast Feeding Area", _hasFeedingArea);
		this.facilities = facilities;

	}

	/**
	 * list all facilities available inside a station
	 * 
	 * @return a string array of facilities
	 */
	public List<String> listFacilities() {
		List<String> facilityList = new ArrayList<>();
		for (Map.Entry<String, Boolean> entry : this.facilities.entrySet()) {
			if (entry.getValue()) {
				facilityList.add(entry.getKey());
			}
		}
		return facilityList;
	}

	public boolean getAvailability(String facility) {
		return this.facilities.get(facility);
	}
}