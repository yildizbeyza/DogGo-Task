import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;
/**
 * This class contains three methods and their implementation. First method generates specified number of random dates between specified dates.
 * Second method generates a Hash Map from these dates, and the third method prints a (String,int) type Hash Map.
 * @author BeyzanurYildiz
 * @version 1.0.0
 * @since 2022-06-07
 */
public class Task2 {

	public static void main(String[] args) {
		LocalDate end = LocalDate.now();//take todays date
		LocalDate start = end.minusYears(2L);//substract 2 years from today
		LocalDate[] randDates = randomDateGenerator(start, end, 20);//call randDates to create random date array
		HashMap<String, Integer> dateMap = yearMonthGrouper(randDates);//call dateMap to create Hash Map of dates
		printMap(dateMap);//print the date Hash Map
	}

	/**
	 * randomDateGenerator creates a specified number of random dates in a specified range
	 * @param start start date of the random
	 * @param end end date of the random
	 * @param number number of dates we want to create
	 * @return out Random dates put into array
	 */
	public static LocalDate[] randomDateGenerator(LocalDate start, LocalDate end, int number) {
		long min = start.toEpochDay(); //change the dates into long (Epoch timestamp)
		long max = end.toEpochDay(); 
		LocalDate[] out = new LocalDate[number]; //create a Date array
		for(int i=0;i<number;i++) {
			long randomDay = ThreadLocalRandom.current().nextLong(min, max); //create a random date as epoch timestamp
			out[i] = LocalDate.ofEpochDay(randomDay); //change the timestamp into Date format
		}
		return out;
	}

	/**
	 * yearMonthGrouper takes an array of random dates and puts them in a Hash Map
	 * @param randDates array of random dates we want to map
	 * @return dateMap Hash Map of dates as MMM-yy, number of dates in that month
	 */
	public static HashMap<String, Integer> yearMonthGrouper(LocalDate[] randDates){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM-yy"); //create formatter for Dates
		HashMap<String, Integer> dateMap = new HashMap<>(); //create Hash Map
		for(int i=0;i<randDates.length;i++) {
			String date = randDates[i].format(formatter); //format the date
			if(dateMap.containsKey(date)) //if date is already in the hashmap
				dateMap.put(date, dateMap.get(date)+1); //increase value
			else
				dateMap.put(date, 1); //put the date in hashmap with value 1
		}
		return dateMap;
	}
	/**
	 * printMap method prints a Hash Map of type (String, Integer)
	 * @param map HashMap we want to print
	 */
	public static void printMap(HashMap<String, Integer> map) {
		for (String i : map.keySet()) { //print each key and value
		      System.out.println("Date: " + i + " number: " + map.get(i));
		    }
	}
}
