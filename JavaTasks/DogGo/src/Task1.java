import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * This class contains two methods and their implementation, First method reads a file into a String and second method counts repeated words and cleans up the string.
 * @author BeyzanurYildiz
 * @version 1.0.0
 * @since 2022-06-06
 */
public class Task1 {

	public static void main(String[] args){
		// TODO Auto-generated method stub
		File file = new File("test.txt");//get file
		
		String input = "";
		try {
			input = readFile(file);
		} catch (FileNotFoundException e) {
			System.out.println("File name incorrect!");
		}//read file to get input
		singleWordConverter(input); //Call function using input
	}
	
	/**
	 * readFile method reads a given file and returns it as a String.
	 * @param file File we want to read.
	 * @return out Contents of the file as String
	 * @throws FileNotFoundException if file is not found throws an exception
	 */
	public static String readFile(File file) throws FileNotFoundException {
		Scanner in = new Scanner(file);
		String out = "";
		while(in.hasNext()) {
			out= out+in.nextLine()+"\n";
		}
		in.close();
		return out;
	}
	
	/**
	 * singleWordConverter method takes a String as input and deletes repeated words, displaying how many times a word was repeated.
	 * @param input String we want to clean up and count.
	 */
	public static void singleWordConverter(String input) {
		Scanner in = new Scanner(input); //Create a scanner to take inputs
		ArrayList<String> cleanSentence = new ArrayList<String>();//to store clean sentences
		ArrayList<String> uniqueWords = new ArrayList<String>(); //to store unique words
		ArrayList<Integer> counts = new ArrayList<Integer>(); //to store number of words
		System.out.println("Input:");
		while(in.hasNext()) {
			String line = in.nextLine(); //Take line as input
			System.out.println(line);
			String[] words = line.split(" "); //Take the input word by word
			for(int i=0;i<words.length;i++) { //Scan the words
				String word = words[i].toLowerCase(); //change everything to lowercase because .equals is case sensitive
				if(uniqueWords.contains(word)) { //if program read the word before
					int index = uniqueWords.indexOf(word); //get the index of that word
					counts.set(index, counts.get(index)+1); //increase count of that word
				}else { //if this is the first time encountering this word
					uniqueWords.add(word); //add word to word list
					counts.add(1); //make word count 1
					cleanSentence.add(words[i]);//add the word to the clean version
				}
			}
			cleanSentence.add("\n");//add new line character to clean version
		}
		in.close();
		System.out.println("Output:");
		for(String s:cleanSentence) { //to print trimmed version of input
			if(s.equals("\n"))
				System.out.print(s);
			else
				System.out.print(s+" "); //print and put space after
		}
		System.out.println("\n*******************"); //line breaker
		for(int i=0;i<uniqueWords.size();i++) { //to print unique words and counts
			System.out.println(uniqueWords.get(i)+" "+counts.get(i)); //print the unique words and counts
		}
	}
	
}
