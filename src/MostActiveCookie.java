// Written by Olsen Budanur

import java.io.*;
import java.util.*;
import java.time.LocalDateTime;

public class MostActiveCookie {
	/**
	 * HashMap 1 ----> KEY: TIME, VALUE: HASHMAP<COOKIE, OCCURRENCE>
	 *
	 * This HashMap stores the number of occurrence of each cookie for 
	 * a specific time.
	*/
	private HashMap<String, HashMap<String, Integer>> occurrences;

	/**
	 * HashMap 2 ----> KEY: TIME, VALUE: MAX OCCURRENCE NUM
	 *
	 * This HashMap stores the maximum number of cookie
	 * occurrences for a specific time.
	*/
	private HashMap<String, Integer> maxOccurrenceNum;

	/**
	 * HashMap 3 ----> KEY: TIME, VALUE: MAX OCCURRENCE COOKIE
	 *
	 * This HashMap stores the cookie/cookies that have the maximum
	 * occurrences for a given time.
	*/
	private HashMap<String, ArrayList<String>> maxOccurrenceCookies;
	
	/**
	 * This is the constructor to our MostActiveCookie class.
	 * Use it to initiate an instance of MostActiveCookie.
	 * 
	 */
	public MostActiveCookie() {
		occurrences = new HashMap<>();
		maxOccurrenceNum = new HashMap<>();
		maxOccurrenceCookies = new HashMap<>();
	}
	
	/**
	 * This method takes in the cookie log we'd like to process, then 
	 * goes through it to fill our internal data structures "occurrences"
	 * "maxOccurrenceNum", and "maxOccurrenceCookies". The format of our
	 * internal data structures will allow us to return the most used cookie
	 * per date later on...
	 * 
	 * @param fileName the file name of the cookie log.
	 * @throws IOException if reading the file failed after we opened it.
	 */
	public void processCookies(String cookieLog) {
		
		try (BufferedReader buffer = new BufferedReader(new FileReader(cookieLog))) {
			String line;

			while ((line = buffer.readLine()) != null) {
				//
				// Tokenize the line we are reading to cookie and time stamp
				String[] items = line.split(",");

				//
				// Make sure we don't try to process the header of the
				// .csv file.
				if (items[0].equals("cookie")) {
					continue;
				}

				//
				// Get the cookie, and the date.
				String cookie = items[0];
				LocalDateTime date = standardDate(items[1]);
				String finalDate = date.toString().substring(0, 10);

				//
				// If we have seen & processed this date before...
				if (occurrences.containsKey(finalDate)){
					//
					// Grab the HashMap that stores the occurrence number for every cookie
					// for this date. Then, increment the occurrences of the current cookie by 1.					
					HashMap<String, Integer> dateCookieOccurrences = occurrences.get(finalDate);
					dateCookieOccurrences.put(cookie, dateCookieOccurrences.getOrDefault(cookie, 0) + 1);

					//
					// Update the maximum if necessary
					int numOccCur = dateCookieOccurrences.get(cookie);

					//
					// If this has occurred more than the prior max occurrences
					if (numOccCur > maxOccurrenceNum.get(finalDate)){
						maxOccurrenceNum.put(finalDate, numOccCur);
						ArrayList<String> maxCookies = new ArrayList<>();
						maxCookies.add(cookie);
						maxOccurrenceCookies.put(finalDate, maxCookies);
					} // If it has occurred just as often as the maximum
					else if (numOccCur == maxOccurrenceNum.get(finalDate)){
						//
						// Add this to the max cookies array
						ArrayList<String> maxCookies = maxOccurrenceCookies.get(finalDate);
						maxCookies.add(cookie);
					}				
				}
				else { // If we haven't seen the date before...
					//
					// Create a HashMap to store the occurrence number for every cookie
					// for this date. Then, add this cookie with the occurrences number of 1. 					
					HashMap<String, Integer> dateCookieOccurrences = new HashMap<>();
					dateCookieOccurrences.put(cookie, 1);
					occurrences.put(finalDate, dateCookieOccurrences);

					//
					// Update the maximum
					maxOccurrenceNum.put(finalDate, 1);
					ArrayList<String> maxCookies = new ArrayList<>();
					maxCookies.add(cookie);
					maxOccurrenceCookies.put(finalDate, maxCookies);
				}
			}
		}
		catch (Exception e){
			System.out.println("There was an error opening your log file. "
					+ "Please make sure the log file is accessable");
			System.out.print(e);
		}
	}
	
	/**
	 * Returns the most active cookie for a given date. Adjusts the given date
	 * to a UTC given it's time zone.
	 * 
	 * @param timeZone the time zone of the given date
	 * @param date the date we'd like to get the most active cookies
	 * @return the most active cookies in a given date
	 */
	public ArrayList<String> getMostActiveCookie(String timeZone, String date){
		//
		// If we couldn't read the file...
		if (maxOccurrenceCookies.size() == 0) {
			return new ArrayList<>();
		}
		
		//
		// Hard coded the argument "-d" as the directions are not clear 
		// as to how this parameter should be used. My assumption was that
		// it tells you the time zone of the given date, in which case
		// we would convert the date to that time zone here. 
		if (!timeZone.equals("-d")) {
			System.out.println("Wrong format, please pass '-d' as your time zone flag.");
			return new ArrayList<>();
		}
		
		//
		// If the date format is not correct
		if (!date.matches("\\d{4}-\\d{2}-\\d{2}")) {
			System.out.println("The date format is incorrect. Please write your date in the format: YYYY-MM-DD");
			return new ArrayList<>();
		}
		//
		// If this date is not seen in our format.
		if (!maxOccurrenceCookies.containsKey(date)) {
			System.out.println("There are no cookies with this date.");
			return new ArrayList<>();
		}
		
		return maxOccurrenceCookies.get(date);
	}
	
	/**
	 * Given a standardized date format, convert it to UTC...
	 * 
	 * @param dateString the date to be standardized
	 * @return the given date in UTC
	 */
	private LocalDateTime standardDate(String dateString){
		//
		// Assuming that the input is in the format we expect it to be...
		String tempDateString = dateString.substring(0, 19);

		//
		// timeZoneModifier stores whether the time zone is a "+" or a "-"
		// timeZone stores the adjustment number
		char timeZoneModifier = dateString.charAt(19);
		String timeZone = dateString.substring(20, 25);		
		
		//
		// We calculate the hours and minutes we need to add to our time
		// in integer format. 
		int hours = timeZoneModifier == '+' ? Integer.parseInt(timeZone.substring(0, 2)) : -1 * Integer.parseInt(timeZone.substring(0, 2));
		int minutes = timeZoneModifier == '+' ? Integer.parseInt(timeZone.substring(3, 5)) : -1 * Integer.parseInt(timeZone.substring(3, 5));

		//
		// Since LocalDateTime is immutable, we are using temporary objects 
		// to change our hours and minutes to get a standard date
		LocalDateTime tempDate = LocalDateTime.parse(tempDateString);
		LocalDateTime tempDateAfterHours = tempDate.plusHours(hours);
		LocalDateTime finalDate = tempDateAfterHours.plusMinutes(minutes);

		return finalDate;
	}
}
