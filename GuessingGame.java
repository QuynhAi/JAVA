// Ai Quynh Nguyen
// 10/28/2017
// CSE142
// TA:Daniel Sullivan
// Assisgnment #5
//
// This program is a game of a random interger and accepts guesses
// Player guesses until the number is correct

import java.util.*;
import java.util.Random;
public class GuessingGame{
   
   public static final int MAX = 100;
   public static void main (String[] args){
      Scanner console = new Scanner(System.in);
      intro();
      
      int totalGuesses = 0;
      int totalGames = 0;
      String answer = "";
      int bestGame = 0;
      
      do {
         
         // Store returned guessNum value inside guess
         int guesses = game(console);
         bestGame = Math.min(guesses, guesses); 
                  
         // Store and update the guesses values into  totalGuesses
         totalGuesses += guesses;
         totalGames++; // inscrease totalGames after each run
         
         // use string and scanner to get answer from player
         System.out.print("Do you want to play again?");
         answer = console.next();
         System.out.println();
         
      }while(answer.equalsIgnoreCase("Y")|| answer.equalsIgnoreCase("YES"));
      
      // If player does not want to play again then show result
      results(totalGames, totalGuesses, bestGame);
   }
   
   // Use random generator to pick a number
   // Then use scanner and while loop until the player guess the correct value
   public static int game (Scanner console){
      Random r = new Random();
      int guessesNum = 0;
      int playerGuess = 0;
      
      int randomValue = r.nextInt(MAX) + 1;
      System.out.println("I'm thinking of a number between 1 and 100...");
      
      while ( playerGuess != randomValue){
         System.out.print("Your guess? ");
         playerGuess = console.nextInt();
         guessesNum++;
         if (playerGuess > randomValue){
            System.out.println("It's lower.");
         }else if ( playerGuess == randomValue){
            System.out.println("You got it right in " + guessesNum + " guesses!");
         }else {
            System.out.println("It's higher.");
         }
         
      }
      // int parameter and returned guessesNum 
      return guessesNum;
   }
   
   // Print the overall result using totalGames, totalGuesses, bestGame
   public static void results(int totalGames, int totalGuesses,int bestGame){
      System.out.println("Overall results:");
      System.out.println("Total games = " + totalGames);
      System.out.println("Total guesses = " + totalGuesses);
      System.out.println("Guesses/game  = " + totalGuesses/(double)totalGames);
      System.out.println("Best game  = " + bestGame);
   }
   
   // Haiku poem introduction message
   public static void intro(){
      System.out.println("Let play guessing game");
      System.out.println("Can you guess the right number?");
      System.out.println("It is will be fun");
      System.out.println();
      
   }
}