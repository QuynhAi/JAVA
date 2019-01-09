// Ai Quynh Nguyen
// Assignment #5
// CSE143
// TA:Aaron Johnston
//
// This program manipulates the grammar that specified as a sequence of strings
// each represents the rules for nonterminal symbol. Then it generate into
// sentences based on the rule.
import java.util.*;
public class GrammarSolver {
   private SortedMap<String, String[]> map;
   
   // Taken in list of string
   // pre: the grammar list is not empty
   // throw illegalArgumentException if empty
   // pre: there is only entry in grammar for same nonterminal
   // throw illegalArgumentException if more than 1 
   // get everything before "::=" is the nonterminal and after is terminal  
   public GrammarSolver(List<String> grammar) {
      map = new TreeMap<String, String[]>();
      if(grammar.isEmpty()) {
         throw new IllegalArgumentException();
      }
      for(int i = 0; i < grammar.size(); i++) {
         String getGrammar = grammar.get(i);
         String[] parts = getGrammar.split("::=");
         String grammarKey = parts[0].trim();
         if(map.containsKey(grammarKey)) { // only one entry 
            throw new IllegalArgumentException();
         }
         String[] grammarValue = parts[1].trim().split("[|]");
         map.put(grammarKey, grammarValue);
      }
  }
   
   // Taken in string symbol
   // return true if given symbol is nonterminal key of grammar
   // return false if not
   public boolean grammarContains(String symbol) {
      return (map.containsKey(symbol));
   }
   
   // Taken in a string symbol and number of times
   // pre: nonterminal symbol need to be in the grammar list
   // and times is greater than 0
   // throw illegalArgumentException if not
   // generate the symbol as many times as user want
   // return the result as an array of string
   public String[] generate(String symbol, int times) {
      String sentence = "";
      if(!map.containsKey(symbol)|| times < 0){
         throw new IllegalArgumentException();
      }
      String[] result = new String[times];
      for(int i = 0; i < times; i++){
         result[i] = generating(sentence, symbol);
      }
      return result;
   }
   
   // Taken in string sentence and string symbol
   // Radomly generate the given number of occurrences of given symbol
   // Result the random chosen pick as string
   private String generating(String sentence, String symbol){
      Random random = new Random();
      if(!map.containsKey(symbol)){ // base case
         sentence = symbol;
      }else{
         String[] symbolsGetting = map.get(symbol);
         int randomNum = random.nextInt(symbolsGetting.length);
         String[] pick = symbolsGetting[randomNum].trim().split("[ \t]+");         
         for(int i = 0; i < pick.length; i++){
            sentence = generating(sentence, pick[i]);
         }
      }
      return sentence;
   }
   
   // Return the string representation of the nonterminal symbols
   // from the grammar as sorted, comma-separated list between square brackets
   public String getSymbols() {
      Set<String> getting = map.keySet();
      return (getting.toString());
   }
}