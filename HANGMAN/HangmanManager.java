// Ai Quynh Nguyen
// Assignment #4
// CSE143
// TA:Aaron Johnston
//
// This program is a different from normal hangman
// the computer is going to delay picking a word until it forced to
// At some given point, there will be a set of words that currently
// being used by computer. Each of those words will have the same pattern
// displayed to the user. 

import java.util.*;
public class HangmanManager {
   private int wordLength; // target word length
   private int wordMax; // max number of wrong guesses
   private Collection<String> newWord;
   private SortedSet<Character> letterGuessed;
   private String currentPattern;
   
   // Taken in a collection of string that contain all the words
   // Taken in the length is what user selected
   // and the maximum guesses number the user has
   // pre: the length > 1 or max > 0
   // throw illegalArgumentException if not
   // goes through the collection string to find words that match the same length
   // Display a dash and a space for each letter in the word
   public HangmanManager(Collection<String> dictionary, int length, int max) {
      this.wordLength = length;
      this.wordMax = max;
      currentPattern = "";
      newWord = new TreeSet<String>();
      letterGuessed = new TreeSet<Character>();
      if (wordLength < 1 || wordMax < 0){
         throw new IllegalArgumentException();
      }
      for(String word : dictionary) {
         if(word.length() == wordLength) {
            newWord.add(spacing(word));
         }
      }
      for(int j = 0; j < wordLength; j++){
         currentPattern += "- ";
      }
   }
   
   // Return the current set of words being considered by the hangman manager
   public Set<String> words() {
      Set<String> newWords = new TreeSet<String>();
      for(String theWord : newWord){
         newWords.add(theWord.replaceAll(" ", ""));
      }
      return newWords;
   }
   
   // Return current set of letters that guessed by the player
   public SortedSet<Character> guesses() {
      return letterGuessed;
   }
   
   // Return the number of guesses the player has left
   public int guessesLeft() {
      return wordMax;
   }
   
   // pre: the set of words is not empty
   // throw IllegalStateException if empty
   // Return the current pattern
   public String pattern() {
      if(newWord.isEmpty()){
         throw new IllegalStateException();
      }
      return currentPattern;
   }
   
   // taken in a character that is the current user's guess
   // pre: number of guesses left is more than 1 or the set words is empty
   // throw illegalStateException if not
   // pre:the character passed it in is not same as character already guessed
   // throw illegalStateException if it is the same
   // Record the next guess made by user
   // Return the number of occurrences of the guessed letter
   // update the number of guesses left
   public int record(char guess) {
      int number = 0;
      String theChoosenPattern = "";
      if(wordMax < 1 || newWord.isEmpty()){
         throw new IllegalStateException();
      }
      if(!newWord.isEmpty() && letterGuessed.contains(guess)){
         throw new IllegalArgumentException();
      }
      letterGuessed.add(guess);
      Map<String, Set<String>> patternList = patternListing(newWord, guess);
      Map<Integer, Set<String>> sizeList = mapOfSize(patternList);
      int maxSize = chosenMax(sizeList);
      currentPattern = chosenPattern(maxSize, sizeList);
      newWord = patternList.get(currentPattern);
      number = pickOccurrences(currentPattern,guess);
      if(number == 0){
         wordMax--;
      }
      return number;
   }
   
   // create space between letters to match with the space that comes after the dash
   // Return those words after being spaced
   private String spacing(String word){
      String theWord = "";
      for(int i = 0; i < word.length();i++){
         theWord += word.charAt(i) + " ";
      }
      return theWord;
   }
   
   // Taken in the spaced word, and char letter
   // if there is already a char letter at the pattern, then don't change it
   // if the character at the pattern matched with the passed in character letter
   // then the pattern would change it to that letter
   // if the character pattern doesn't match with passed char letter
   // then, pattern is the dash
   // return the pattern
   private String updatedPattern(String spacedWord, char letter){
      String thePattern = "";
      for(int i = 0; i < currentPattern.length();i+=2){
         if(currentPattern.charAt(i) != '-'){
            thePattern += currentPattern.charAt(i) + " ";
         }else if(spacedWord.charAt(i) == letter){
            thePattern += letter + " ";
         }else{
            thePattern += "- ";
         }
      }
      return thePattern;
   }
   
   // Taken in the collection of string and character guess
   // Goes through each word to get simiar of pattern with given char guess together
   // return the map of the pattern and the set of words
   private Map<String, Set<String>> patternListing (Collection<String> list, char guess){
      Map<String, Set<String>> map = new TreeMap<String, Set<String>>();
      for(String theWord : list){
         String thePattern = updatedPattern(theWord, guess);
         if(!map.containsKey(thePattern)){ // pattern doesn't exist, create new one
            Set<String> theSet = new TreeSet<String>();
            map.put(thePattern, theSet);
         }
         map.get(thePattern).add(theWord); // get the pattern, and add the word
      }
      return map;
   }
   
   // Taken in Map with String, and set of string
   // Get the size of each pattern in the map
   // return the map of size with set<string>
   private Map<Integer, Set<String>> mapOfSize (Map<String, Set<String>> map){
      int number = 0;
      Map<Integer, Set<String>> keySize = new TreeMap<Integer, Set<String>>();
      for(String pattern : map.keySet()){
         number = map.get(pattern).size();
         if(!keySize.containsKey(number)){// size of the key doesn't exist, create new one
            Set<String> moreSet = new TreeSet<String>();
            keySize.put(number, moreSet);
         }
         keySize.get(number).add(pattern);
      }
      return keySize;
   }
   
   // Taken in the map of integer and set<String>
   // Goes through the whole map to find the biggest size of the key
   // return the maximum
   private int chosenMax (Map<Integer, Set<String>> keySize){
      int max = 0;
      Set<Integer> list = keySize.keySet();
      Iterator i = list.iterator();
      while(i.hasNext()){
         max =(int)i.next();
      }
      return max;
   }
   
   // Taken in the int max, map of integer and set of string
   // if two of word pattern are of equal size
   // Pick the first word in the map
   // return it
   private String chosenPattern (int max, Map<Integer, Set<String>> keySize){
      Set<String> patterns = keySize.get(max);
      String pattern ="";
      Iterator i = patterns.iterator();
      if(!i.hasNext()){
         throw new IllegalArgumentException("Empty");
      }
      pattern = (String)i.next();
      return pattern;
   }
   
   // Taken in the patter and the guess character
   // return the number of times the char guess appeal in the pattern
   private int pickOccurrences(String pattern, char guess){
      int count = 0;
      for(int i = 0; i < pattern.length();i++){
         if(pattern.charAt(i) == guess){
            count++;
         }
      }
      return count;  
   }
}
