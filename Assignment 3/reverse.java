import java.io.*;
import java.util.*;

public class reverse {
	public static void main(String[] args) throws FileNotFoundException {
		ArrayList<String> list = new ArrayList<String>();
		Scanner console = new Scanner(System.in);	
		File theInputFile = inputFile(console);
		method(list, theInputFile);
	}
	
	public static void method(ArrayList<String> list, File theInputFile) throws FileNotFoundException {
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