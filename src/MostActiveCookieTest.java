// Written by Olsen Budanur

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * 
 */

/**
 * The test class for the MostActiveCookie class.
 * 
 * It is important to note that the test cases are not comprehensive, and does not cover all cases.
 * With that in mind, the test cases could/should be improved.
 * 
 */
class MostActiveCookieTest {

	private String fileOne;

	/**
	 * Set up the tests.
	 * 
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		fileOne = "cookie_log.csv";
	}

	

	/**
	 * Test the MostActiveCookie with the input cookie_log.csv and date 2018-12-09.
	 * Single most active cookie.
	 * 
	 */
	@Test
	void singleMostActiveCookie() {
		//
		// Create our MostActiveCookie object.
		MostActiveCookie mostActiveCookie = new MostActiveCookie();
		//
		// Let the MostActiveCookie process the data in the cookie log
		mostActiveCookie.processCookies(fileOne);
		
		//
		// Get the most active cookie(s) after processing the cookie log.
		ArrayList<String> mostActiveCookies = mostActiveCookie.getMostActiveCookie("-d", "2018-12-09");
		
		//
		// Check if the answer is correct.
		assertEquals(mostActiveCookies.size(), 1);
		assertEquals(mostActiveCookies.get(0), "AtY0laUfhglK3lC7");
	}
	
	/**
	 * Test the MostActiveCookie with the input cookie_log.csv and date 2018-12-07.
	 * Single most active cookie.
	 * 
	 */
	@Test
	void singleMostActiveCookieTwo() {
		//
		// Create our MostActiveCookie object.
		MostActiveCookie mostActiveCookie = new MostActiveCookie();
		//
		// Let the MostActiveCookie process the data in the cookie log
		mostActiveCookie.processCookies(fileOne);
		
		//
		// Get the most active cookie(s) after processing the cookie log.
		ArrayList<String> mostActiveCookies = mostActiveCookie.getMostActiveCookie("-d", "2018-12-07");
		
		//
		// Check if the answer is correct.
		assertEquals(mostActiveCookies.size(), 1);
		assertEquals(mostActiveCookies.get(0), "4sMM2LxV07bPJzwf");
	}
	
	
	/**
	 * Test the MostActiveCookie with the input cookie_log.csv and date 2018-12-08.
	 * Multiple most active cookies.
	 * 
	 */
	@Test
	void multipleMostActiveCookie() {
		//
		// Create our MostActiveCookie object.
		MostActiveCookie mostActiveCookie = new MostActiveCookie();
		//
		// Let the MostActiveCookie process the data in the cookie log
		mostActiveCookie.processCookies(fileOne);
		
		//
		// Get the most active cookie(s) after processing the cookie log.
		ArrayList<String> mostActiveCookies = mostActiveCookie.getMostActiveCookie("-d", "2018-12-08");
		
		//
		// Check if the answer is correct.
		assertEquals(mostActiveCookies.size(), 3);
		assertEquals(mostActiveCookies.get(0), "SAZuXPGUrfbcn5UA");
		assertEquals(mostActiveCookies.get(1), "4sMM2LxV07bPJzwf");
		assertEquals(mostActiveCookies.get(2), "fbcn5UAVanZf6UtG");
	}
	
	
	/**
	 * Test the MostActiveCookie with the input cookie_log.csv with a date outside not included
	 * in the cookie log.
	 */
	@Test
	void nonExistentDate() {
		//
		// Create our MostActiveCookie object.
		MostActiveCookie mostActiveCookie = new MostActiveCookie();
		//
		// Let the MostActiveCookie process the data in the cookie log
		mostActiveCookie.processCookies(fileOne);
		
		//
		// Get the most active cookie(s) after processing the cookie log.
		ArrayList<String> mostActiveCookies = mostActiveCookie.getMostActiveCookie("-d", "2018-12-10");
		
		//
		// Check if the answer is correct.
		assertEquals(mostActiveCookies.size(), 0);
	}
	
	/**
	 * Test the MostActiveCookie with the input cookie_log.csv with an incorrect date format
	 * 
	 */
	@Test
	void wrongDateFormat() {
		//
		// Create our MostActiveCookie object.
		MostActiveCookie mostActiveCookie = new MostActiveCookie();
		//
		// Let the MostActiveCookie process the data in the cookie log
		mostActiveCookie.processCookies(fileOne);
		
		//
		// Get the most active cookie(s) after processing the cookie log.
		ArrayList<String> mostActiveCookies = mostActiveCookie.getMostActiveCookie("-d", "20181208");
		
		//
		// Check if the answer is correct.
		assertEquals(mostActiveCookies.size(), 0);
	}
	
	
	
	/**
	 * Test the MostActiveCookie with a non-existent file
	 */
	@Test
	void wrongFileName() {
		//
		// Create our MostActiveCookie object.
		MostActiveCookie mostActiveCookie = new MostActiveCookie();
		//
		// Let the MostActiveCookie process the data in the cookie log
		mostActiveCookie.processCookies("THISFILEDOESNTEXIST");
		
		//
		// Get the most active cookie(s) after processing the cookie log.
		ArrayList<String> mostActiveCookies = mostActiveCookie.getMostActiveCookie("-d", "2018-12-10");
		
		//
		// Check if the answer is correct.
		assertEquals(mostActiveCookies.size(), 0);
	}
	
	

}
