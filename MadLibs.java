// Ai Quynh Nguyen
// 11/14/2017
// CSE142
// TA:Daniel Sullivan
// Assisgnment #6
//
// This program is use file input/output and strings to create mad libs short story
// one person ask the other to fill in placeholder blank space
// after the placeholders are filled, show the person the result story

import java.io.*;
import java.util.*;

public class MadLibs{
   public static void main(String [] args) throws FileNotFoundException{
      Scanner console = new Scanner(System.in);
      String answer;
      introduction();
      
      do{
         System.out.print("(C)reate mad-lib, (V)iew mad-lib, (Q)uit? ");
         answer = console.nextLine();
         
         // Use if/else loop 
         if (answer.equalsIgnoreCase("C")){
         
            // inputting
            File theInputFile = inputFile(console);
            Scanner input = new Scanner(theInputFile);
            
            // outputting
            File theOutputFile = outputFile(console);
            PrintStream output = new PrintStream(theOutputFile);
            
            // create the mad-lib
            System.out.println();
            createMadLib(input, console, output);
            System.out.println();
         
         // When user type V as view
         // Catch the returned value from inputFile
         // and store it inside needToReadFile
         }else if(answer.equalsIgnoreCase("V")){
            File needToReadFile = inputFile(console);
            viewMadLib(needToReadFile, console);
            System.out.println();
         }
      
      // If the user type anything other than Q
      // Then it goes through the while loop
      // Anything not C, V, Q then reprompting user
      }while(!answer.equalsIgnoreCase("Q"));
     
   }
   
   // use simple system.out.println to write the introduction
   public static void introduction(){
      System.out.println("Welcome to the game of Mad Libs.");
      System.out.println("I will ask you to provide various words");
      System.out.println("and phrases to fill in a story.");
      System.out.println("The result will be written to an output file.");
      System.out.println();  
   }
   
   // Takes in scanner input, console and PrintStream as parameters
   // Print to output file using PrintSteam
   // use scanner to scan each line, each word
   // use if/else loop to find the placeHolder
   // Replace another word for the placeHolder that located between < > 
   public static void createMadLib(Scanner input, Scanner console, PrintStream output)
                                    throws FileNotFoundException{
      while(input.hasNextLine()){
         String line = input.nextLine();
         Scanner lineScanner = new Scanner(line);
         
         while (lineScanner.hasNext()){
            String word = lineScanner.next();
            
            if (word.startsWith("<")){
               String placeHolder = word.substring(1, word.length() - 1);
               String variableHolder = placeHolder.replaceAll("-", " ");
               
               System.out.print("Please type " );
               String vowels = vowels(placeHolder);
               System.out.print(variableHolder + ": ");
               
               String wordReplacement = console.nextLine();
               output.print(wordReplacement + " ");
               
            }else{
               output.print(word + " ");
            }              
         }
         output.println();  
      }
      System.out.println("Your mad-lib has been created!");
   }
   
   // Scanner console as parameter
   // Use file object to check the info on the file
   // Check the file does exists then return it
   // If does not exists, ask user to try again
   // Create new file from the name that user typed in
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
   
   // Scanner console as parameter
   // Create new file from the name that user typed in
   // Return the file
   public static File outputFile(Scanner console) {
	   System.out.print("Output file name: ");
	   String outputName = console.nextLine();
	   File fileName = new File(outputName);
	   
	   return fileName;   
   }
   
   // Scanner console and String outputName as parameter
   // Create new scanner sc to scan each line 
   // from the from output file that the person typed
   // Print them out
   public static void viewMadLib (File needToReadFile, Scanner console)
                                   throws FileNotFoundException {
      Scanner sc = new Scanner(needToReadFile);
      System.out.println();
      while(sc.hasNextLine()){
         String line = sc.nextLine();            
         System.out.print(line + " ");
         System.out.println();
         }
      }

	// pass placeHolder as a parameter
	// If the placeHolder starts with vowels then print out "an"
	// if not a vowel then print out "a"
	// return empty string if nothing work
	public static String vowels(String placeHolder){
	   if (placeHolder.startsWith("a") || placeHolder.startsWith("e")
	      ||placeHolder.startsWith("i")|| placeHolder.startsWith("o")
	      ||placeHolder.startsWith("u")){
	      
	      System.out.print("an ");
	      
	   }else {
	      System.out.print("a ");
	   }
	   return "";
	}
}