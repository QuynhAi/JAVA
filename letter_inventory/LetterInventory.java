// Ai Quynh Nguyen
// CSE 143
// 1/10/17
// TA:Aaron Johnston
// Assisgnment #1
//
// This program implement a class that used to keep track the inventory of letters of the alphabet

public class LetterInventory {
   private int size;
   private int[]count;
   private static final int ALPHABETIC_LETTER = 26;
   
   // Take in the given string and construct a count of alphabetic letters
   // save the sum of all the counts
   public LetterInventory(String data) {
      count = new int [ALPHABETIC_LETTER];
      data = data.toLowerCase();
      for (int i = 0; i < data.length(); i++) {
         char ch = data.charAt(i);
         if(ch >= 'a' && ch <= 'z') {
            count[ch - 'a']++;
            size++;
         }
      }
   }
   
   // taken in the given LetterInventory
   // return null if count is negative
   // Subtract the count from both LetterInventory objects
   // post: return a new invetory from the substraction result of two objects
   public LetterInventory subtract (LetterInventory other){
      LetterInventory subtractionResult = new LetterInventory("");
      for (int i = 0; i < ALPHABETIC_LETTER; i++){
         subtractionResult.count[i] = count[i] - other.count[i];
         if(subtractionResult.count[i] < 0){
            return null;  
         } 
      }
      subtractionResult.size = size - other.size;
      return subtractionResult;
   }
   
   // taken in the given LetteInventory
   // Add the count from both LetterInventory objects
   // post: return a new invetory from the sum of two objects
   public LetterInventory add (LetterInventory other){
      LetterInventory sumResult = new LetterInventory("");
      for (int i = 0; i < ALPHABETIC_LETTER; i++){
         sumResult.count[i] = count[i] + other.count[i];
      }
      sumResult.size = size + other.size;
      return sumResult; 
   }
   
   // taken in char letter and int value
   // pre: value is not negative and character is alphabetic
   // throw IllegalArgumentException if not
   // post: set the count of the given letter to the given value
   public void set(char letter, int value){
      letter = Character.toLowerCase(letter);
      if(value < 0 || letter < 'a' || letter > 'z'){
         throw new IllegalArgumentException();
      }
      size -=  count[letter - 'a'] - value;
      count[letter - 'a'] = value;
   }
   
   // Occurrences of each letter match its count
   // post: return the string result of all the better in sorted order
   public String toString() {
      String result = "[";
      for(int i = 0; i <= ALPHABETIC_LETTER - 1; i++) {
         for (int j = 0; j < count[i]; j++) {
            result += (char)('a' + i);
         }
      }
      return result + "]";
   }
   
   // take in char letter
   // pre: z > letter > a; letter needs to be between 'a' and 'z'
   // throw IllegalArgumentException if not
   // post: return count of times of the letter
   public int get(char letter) {
      letter = Character.toLowerCase(letter);
      if(letter < 'a' || letter > 'z') {
         throw new IllegalArgumentException();
      }
      return count [letter - 'a'];
   }
   
   // post: return the side of the list
   public int size() {
      return size;
   }
   
   // post: if the side is equal to zero, return true. Else, return false
   public boolean isEmpty() {
      return (size == 0);
   }
}
