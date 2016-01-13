package proj5;

import java.io.*;
import java.util.*;

/**
 * 
 * MostFrequentWords class finds the most frequent words in a text file according to a certain value
 * Runs the main method for implementation of Binary Search Tree and Sorted Linked List
 * Takes in three command line arguments: name of an input text file, the name of an output file, and a cutoff 
 * value of words to be printed to the output file
 * Uses FileParser class to extract an ArrayList of words from a text file
 * Uses the two data structures to index all of the unique words in alphabetical order and then removes any of words 
 * whose counts are below the cutoff value
 * 
 * @author Megha Madan
 * May 1, 2015
 * 
 */
public class MostFrequentWords{
	
	/**
	 * Method that extracts a list of words from a given text file name 
	 * @param textFileName - the name of the .txt file that will be passed into FileParser class method
	 * @return an ArrayList of all the words in the file
	 */
	public static ArrayList<String> readFile (String textFileName){
		
		//create ArrayList to hold the list returned from FileParser class method GetAllWords
		ArrayList<String> myListOfWords = new ArrayList<String>();
		
		//try-catch block to pick up any errors thrown by FileParser class
		try {
			//create FileParser object to use the getAllWords method
			FileParser parsingFile = new FileParser(textFileName);
			myListOfWords = parsingFile.getAllWords();
		} 
		//catch and print the errors the FileParer class throws
		catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return myListOfWords;
	}

	
	/**
	 * Method that stores unique words from a list in a Binary Search Tree by iterating an ArrayList
	 * @param myList - the ArrayList of words, String objects that are inserted as nodes into the BST
	 * @return a Binary Search Tree that is filled with the unique words from the list 
	 */
	public static BST<String> populateBST (ArrayList<String> myList){
		
		//Create an Iterator object and a BST object with String parameter
		Iterator<String> iterator = myList.iterator();
		BST<String> myBSTOfWords = new BST<String>();
		
		//use iterator to go through the ArrayList of string objects as long as there is a next word
		while(iterator.hasNext() ) {
			
			//set nextWord from our list of words from the input file
			String nextWord = iterator.next();
			
			//use nextWord to search for same word in our BST using 'get' method
			//as long as the word is not already in our BST (null is returned), create and insert a new node into BST
			//if a duplicate word is found, the if statement is avoided and no new node is added...
			//**when the get method is used on an existing node, the counter is incremented**
			if(myBSTOfWords.get(nextWord) == null ){
				myBSTOfWords.insert(nextWord);
			}
		}
		return myBSTOfWords;
	}
	
	/**
	 * Method that stores unique words from a list in a Sorted Linked List by iterating a given ArrayList
	 * @param myList - the ArrayList of words - String objects that are inserted as nodes into the Linked List
	 * @return a Sorted Linked List that is filled with every unique word from the list 
	 */
	public static SLL<String> populateSLL (ArrayList<String> myList){
		
		//Create a SLL object with String parameter and an iterator object
		SLL<String> listOfUniqueWords = new SLL<String>();
		Iterator<String> iterator = myList.iterator();
		
		//use iterator to go through the ArrayList of string objects as long as there is a next word
		while(iterator.hasNext() ) {
			
			//set nextWord from our list of words
			String nextWord = iterator.next();
			
			//if the list does not contain the word we are trying to insert, we will insert a new node according to given data
			//if the list does contain the word we are trying to insert, the contains method will automatically 
			//increment the counter for the word when it finds that word in the list and nothing else is done
			if(!listOfUniqueWords.contains(nextWord))
				listOfUniqueWords.insert(nextWord);
			
		}
		return listOfUniqueWords;
	}
	
	
	public static void main (String [] args) throws FileNotFoundException{
		
		//First check if three command line arguments are provided
		if(args.length !=  3){
			//if not, print an error message and terminate program
			System.err.printf("ERROR: The command line arguments are not correct \n\n");
			System.exit(1);
		}
		
		//Then extract info from the input arguments:
		//input file
		String inputTextFileName = new String (args[0]);
		
		//output file
		String outputFileName = args[2];
		
		//if full name of output file is provided, remove the file extension and dot
		if (outputFileName.contains("."))
			outputFileName = outputFileName.substring(0, outputFileName.lastIndexOf("."));
		
		//cutoff value
		int cutoffValue = Integer.parseInt(args[1]);
		
		
		
		
		
		
		
		//Reading File portion - printing info to console
		//invoke the garbage collector and time how long the reading method takes
		System.gc();
		long start1 = System.nanoTime();
		ArrayList<String> myList =  readFile(inputTextFileName);
		long end1 = System.nanoTime();
		//convert nanoseconds to milliseconds
		long duration1 = (end1 - start1) / 1000000;
		
		System.out.printf("INFO: Reading file took %-2d ms (~ %2.3f seconds ).", duration1, duration1 / 1000.0  ) ;
		System.out.printf("\nINFO: %-2d words read.", myList.size() ) ;
		
		
		
		
		
		
		//Sorted Linked List portion
		System.out.print("\n\nProcessing using Sorted Linked List");
		
		//invoke the garbage collector and time the populate method for Sorted Linked List
		System.gc();
		long start2 = System.nanoTime();
		SLL<String> SLLWords = populateSLL(myList);
		long end2 = System.nanoTime();
		long populateDuration1 = (end2 - start2) / 1000000;
		
		System.out.printf("\nINFO: Creating index took %-2d ms (- %2.3f seconds). ", populateDuration1, populateDuration1 / 1000.0);
		System.out.printf("\nINFO: %-2d words stored in index.", SLLWords.size()  ) ;
		
		
		//invoke the garbage collector and time the prune method for Sorted Linked List
		System.gc();
		long start3 = System.nanoTime();
		//Use prune method from BST class so that the counts of the nodes can be accessed
		SLLWords.pruneList(SLLWords.getHead(), cutoffValue);
		long end3 = System.nanoTime();
		long pruneDuration1 = (end3 - start3) / 1000000;
		
		
		System.out.printf("\nINFO: Pruning index took %-2d ms (- %2.3f seconds). ", pruneDuration1, pruneDuration1 / 1000.0);
		System.out.printf("\nINFO: %-2d words remaining after pruning.", SLLWords.size() ) ;
		
		
		
		
		
		
		
		
		//Binary Search Tree portion
		System.out.print("\n\nProcessing using Binary Search Tree");
		
		//invoke the garbage collector and time the populate method for Binary Search Tree
		System.gc();
		long start4 = System.nanoTime();
		BST<String> BSTWords = populateBST(myList);
		long end4 = System.nanoTime();
		long populateDuration2 = (end4 - start4) / 1000000;
		
		System.out.printf("\nINFO: Creating index took %-2d ms (- %2.3f seconds). ", populateDuration2, populateDuration2 / 1000.0);
		System.out.printf("\nINFO: %-2d words stored in index.", BSTWords.size()  ) ;
		
		
		//invoke the garbage collector and time the prune method for BST
		System.gc();
		long start5 = System.nanoTime();
		//Use prune method from BST class so that the counts of the nodes can be accessed
		BSTWords.pruneTree(BSTWords.getRoot(), cutoffValue);
		long end5 = System.nanoTime();
		long pruneDuration2 = (end5 - start5) / 1000000;
		
		System.out.printf("\nINFO: Pruning index took %-2d ms (- %2.3f seconds). ", pruneDuration2, pruneDuration2 / 1000.0);
		System.out.printf("\nINFO: %-2d words remaining after pruning.", BSTWords.size()  ) ;
		
		//Printing edited list to output file
		//create output file object with "fileName" and PrintWriter object to print on the output file
		File outputInfoFile = new File (outputFileName + ".out");
		PrintWriter printOut = new PrintWriter(outputInfoFile);
		
		
		//BSTWords is now modified with only the words whose count is above the cutoff value, so print BSTWords to the output file - 
		//the toString method prints the words and counts with inOrder traversal so they will be in alphabetical order 
		printOut.print(SLLWords);
		printOut.println();

		printOut.close();
		
	}
}


