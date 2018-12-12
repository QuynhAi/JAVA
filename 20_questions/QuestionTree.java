// Ai Quynh Nguyen
// CSE 143
// 3/1/18
// TA:Aaron Johnston
// Assisgnment #7
//
// this program construct a binary tree where eacch leaf has name
// of the object and each branch node has yes/no question that 
// distinguishes the object. It grows more intelligent each wrong answer
import java.io.*;
import java.util.*;
public class QuestionTree {
   private static QuestionNode overallRoot;
   private Scanner console;
   
   // Create a new question tree with
   // With one leaf node represent the object computer
   public QuestionTree(){
      overallRoot = new QuestionNode("computer");
      console = new Scanner(System.in);
   }
   
   // Use current tree to ask  user yes/ no questions until
   // guess their object correctly or until fail
   // if fail, expanse the tree to include their object
   // and new question to distinguish their object from others
   public void askQuestions() {
      overallRoot = askQuestionHelper(overallRoot);
   }
   
   // Taken in the questionNode overallRoot
   // check if it is the correct answer
   // if correct, print "got it right"
   // if wrong, create new object, and question
   // if not an answer, continue down the tree
   // return the overallRoot
   private QuestionNode askQuestionHelper(QuestionNode overallRoot){
      if(overallRoot != null){
         if(overallRoot.left == null && overallRoot.right == null){ // answer
            String sentence = "Would your object happen to be " + overallRoot.data + "?";
            if(yesTo(sentence)){
               System.out.println("Great, I got it right!");
            }else{
               overallRoot = answerHelper(overallRoot);
            }
         }else{ // not answer
            if(yesTo(overallRoot.data)){
               overallRoot.left = askQuestionHelper(overallRoot.left);
            }else{
               overallRoot.right = askQuestionHelper(overallRoot.right);
            }
         }
      }
      return overallRoot;
   }
   
   // Taken in the given QuestionNode overallRoot
   // When the computer guessed the wrong object
   // ask the user for the new object and ask yes/no question to distinguishes the object
   private QuestionNode answerHelper(QuestionNode overallRoot){
      System.out.print("What is the name of your object? " );
      String name = console.nextLine();
      QuestionNode nameNode = new QuestionNode(name);
      System.out.println("Please give me a yes/no question that");
      System.out.println("distinguishes between your object");
      System.out.print("and mine--> ");
      String question = console.nextLine();
      String sentence = "And what is the answer for your object?";
      QuestionNode data = new QuestionNode(question);
      if(yesTo(sentence)){ // question, new object, old data
         data = new QuestionNode(question, nameNode, overallRoot);
      }else{   // question, old data, new object
         data = new QuestionNode(question, overallRoot, nameNode);
      }
      return data;
   }
   // Taken in Scanner input (file to read)
   // replace the current tree by reading another tree from a file
   public static void read(Scanner input) {
      overallRoot = readHelper(input);
   }
   
   // Taken in scanner input(file to read)
   // replace the current tree by reading another tree from a file
   // return the root
   private static QuestionNode readHelper(Scanner input) {
      QuestionNode root = null;
      String type = input.nextLine();
      String data = input.nextLine();
      if(input.hasNextLine()){
         if(type.equals("A:")){
            root = new QuestionNode(data);
         }else{
            root = new QuestionNode(data, readHelper(input), readHelper(input));
         }
      }
      return root;
   }
   
   // Taken in given printStream output
   // store the current tree to an output file
   public  void write(PrintStream output) {
      overallRoot = writeHelper(output, overallRoot); 
   }
   
   // Taken in given printStream output
   // store the current tree to an output file
   private QuestionNode writeHelper(PrintStream output, QuestionNode overallRoot) {
      if(overallRoot != null){
         overallRoot.left = writeHelper(output, overallRoot.left);
         overallRoot.right = writeHelper(output, overallRoot.right);
         if(overallRoot.left == null & overallRoot.right == null){
            output.println("A:\n" + overallRoot.data);
         }else{
            output.println("Q:\n" + overallRoot.data);
         }
      }
      return overallRoot;
   }
   
   // post: asks the user a question, forcing an answer of "y" or "n";
   //       returns true if the answer was yes, returns false otherwise
   public boolean yesTo(String prompt) {
      System.out.print(prompt + " (y/n)? ");
      String response = console.nextLine().trim().toLowerCase();
      while (!response.equals("y") && !response.equals("n")) {
         System.out.println("Please answer y or n.");
         System.out.print(prompt + " (y/n)? ");
         response = console.nextLine().trim().toLowerCase();
      }
      return response.equals("y");
   }
}