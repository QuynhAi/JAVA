// Ai Nguyen Trieu
// C SCI 143
// Assignment 3, ArrayList
// 7/26/18
//
// This program is use to reverse the lines of a file and reverse the words plus 
// the letter's of each word within the each line using ArrayList

import java.io.*;
import java.util.*;

public class assignment3 {
	public static void main(String[] args) throws FileNotFoundException {
		ArrayList<String> list = new ArrayList<String>();
		Scanner console = new Scanner(System.in);	
		File theInputFile = inputFile(console);
		method(list, theInputFile);
	}
	
   // Parameter: Arralist of String and the File that user typed in
   // Reverse the all the line in the file and reverse each individual letter
   // The last line will now become the first line
   // And the last letter will now become the first letter
	public static void method(ArrayList<String> list, File theInputFile) 
                                                   throws FileNotFoundException {
		Scanner input = new Scanner(theInputFile);
		while(input.hasNextLine()) {
	         String word = input.nextLine();
	         list.add(word);
	      }
		for(int i = list.size() - 1; i >= 0; i--) {
	         String temp = list.get(i);
	         for(int j = temp.length()-1; j>=0; j--){
	            System.out.print(temp.charAt(j));
	         }
	         System.out.println();
	      }
	}
	
   
   // Scanner console as parameter
   // Use file object to check the info on the file
   // Check the file does exists then return it
   // If does not exists, ask user to try again
	public static File inputFile(Scanner console){
	      System.out.print("Input file name: ");
	      String inputName = console.nextLine();
	      File fileName = new File(inputName); 
	      
	      while (!fileName.exists()){
	         System.out.print("File not found. Try again: ");
	         inputName = console.nextLine();
	         fileName = new File(inputName);
	      }
	      return fileName;
	   }
}