// Written by Olsen Budanur

import java.util.*;

public class MostActiveCookieRunner {

	
	/**
	 * This is the main function of the Most Active Cookie program.
	 * 
	 * @param args the cookie log file, time zone flag, and the date
	 */
	public static void main(String[] args) {
		//
		// Enforce correct usage.
		if (args.length != 3) {
			System.out.println("Incorrect usage, use the following arguments: 'COOKIELOGFILE' 'TIMEZONEFLAG' 'DATE'");
			System.exit(1);
		}
		
		//
		// Create our MostActiveCookie object.
		MostActiveCookie mostActiveCookie = new MostActiveCookie();
		
		//
		// Let the MostActiveCookie process the data in the cookie log
		mostActiveCookie.processCookies(args[0]);
		
		//
		// Get the most active cookie(s) after processing the cookie log.
		ArrayList<String> mostActiveCookies = mostActiveCookie.getMostActiveCookie(args[1], args[2]);
		
		
		//
		// Print out the most active cookie(s)
		for (int i = 0; i < mostActiveCookies.size(); i++) {
			System.out.println(mostActiveCookies.get(i));
		}
		
	}
	
	

	
	
}
