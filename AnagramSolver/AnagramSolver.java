// Ai Quynh Nguyen
// CSE 143
// 2/22/18
// TA:Aaron Johnston
// Assisgnment #6
//
// This program uses a prefixtionary to find all combinations of words
// that have same letters as given phrase
import java.util.*;
public class AnagramSolver {
   private List<String> theDictionary;
   private Map<String, List<String>> map;
   
   // Taken in the given list of string
   // which is the representation of words in the dictionary
   // create the map with combination of words from dictionary
   public AnagramSolver(List<String> list) {
      this.theDictionary = list;
      map = new HashMap<String, List<String>>();
      constructMap(list);
   }
   
   // Taken in the given list of string
   // Store all the combination of words from dictionary
   // into the map
   private void constructMap(List<String> theDictionary){
      for(String s : theDictionary){
         LetterInventory key = new LetterInventory(s);
         String theKey = key.toString();
         if(!map.containsKey(theKey)){ // if key not exist, create new one
            List<String> value = new ArrayList<String>();
            map.put(theKey, value);
         }
         map.get(theKey).add(s); // if key already exist, then add
      }
   }
   
   // Taken in string to search for anagram,and maximum number of words
   // pre: the max words is not less than 0
   // throw illegalArgumentException is less than 0
   // Find combinations of words that have same letters as given string
   // Print out all combinations of words that are anagrams of s
   // print words between [] and comma between each word
   public void print(String s, int max) {
      boolean isUnlimited = false;
      String prefix = "";
      if(max < 0){
         throw new IllegalArgumentException();
      }
      List<String> result = new ArrayList<String>();
      //Set<String> result = new TreeSet<String>();
      LetterInventory current = new LetterInventory(s);
      if(max == 0){ // if no restricted max number of words
         isUnlimited = true;
      }
      helper(current, max, result, prefix, isUnlimited);
      Collections.sort(result);
      for(String chosenWord : result){ // print out result
         System.out.println("[" + chosenWord + "]");
      }
   }
   
   // taken in the given letter inventory current, given integer max
   // Given list of string result, given string prefic, and boolean unlimited
   // find the anagram by going through each word
   // if there is left over, then unchoose
   // then try new pathway
   private void helper(LetterInventory current, int max, List<String> result,
   String prefix, boolean isUnlimited){
      if(current.toString().equals("[]")){ // when nothing left, just []
         if (!result.contains(new String(prefix))) {
            result.add(new String(prefix));     // add into the result
         }
      }
      if(!isUnlimited && max == 0){ // base case with restriction
         return;
      }
      for(String word : this.theDictionary){
         
         LetterInventory choice = new LetterInventory(word);
         if(choice.size()<= current.size()){ 
            LetterInventory check = current.subtract(choice); // get left over
            if(check != null){   // still have left over
               for(String key : map.get(choice.toString())){
                  String temp = new String(prefix);
                  temp += key;
                  if(!check.toString().equals("[]")){ // print out the comma between word
                     temp += ", ";
                  }
                  helper(check, max - 1, result, temp, isUnlimited);
               }
            }
         }
      }
      
   }
}