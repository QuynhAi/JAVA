// Ai Quynh Nguyen
// Assignment #3
// CSE143
// TA:Aaron Johnston
//
// This program takes in a random list of stalking and killing
// each person is assigned to a target that they are trying to assassinate.
// People going to be killed and put in the grave yard
// until there is only one person left(winner).

import java.util.*;
public class AssassinManager {
   private AssassinNode frontKillRing;
   private AssassinNode frontGraveYard;
   
   // Taken in string names from a chosen file
   // pre: the file is not empty,shold have at least one name
   // throw IllegalArgumentException if empty
   // build a kill ring with all the names
   // assign the first person stalk the next person and so on
   public AssassinManager(List<String> names) {
      if(names == null){
         throw new IllegalArgumentException();
      }
      frontKillRing = new AssassinNode(names.get(0));
      AssassinNode current = frontKillRing;
      for(int i = 1; i <= names.size() - 1; i++){
         String name = names.get(i);
         current.next = new AssassinNode(name,null);
         current = current.next;
      }
      current.next = frontKillRing;    
   }
   
   // post: Report the person is stalking themselves if no one else is in the killRing
   // Report the first person is stalking the next person in the killRing list
   // and so on
   public  void printKillRing() {
      AssassinNode current = frontKillRing;
      if(current.next == null){
         System.out.println("    " + current.name + " is stalking " + current.name);
      }
      System.out.println("    " + current.name + " is stalking " + current.next.name);
      current = current.next;
      while(current != frontKillRing){
         System.out.println("    " + current.name + " is stalking " + current.next.name);
         current = current.next;
      }
   }
   
   // Report who the victim was killed by
   // If the grave yard is empty, no output
   // Report the names in reverse kill order
   // Report most recently killed first and so on
   public  void printGraveyard() {
      AssassinNode ghost = frontGraveYard;
      if(ghost == null){
          return;   
      }
      if(ghost.next == ghost){
         System.out.println("    " + ghost.name + " was killed by " + ghost.killer);
      }else{
         String dieName = ghost.name;
         String terminalName = ghost.name;
         while(true ){
            while(ghost.next.name != dieName){
               ghost = ghost.next;
            }
            System.out.println("    " + ghost.name + " was killed by " + ghost.killer);
            dieName = ghost.name;
            ghost = frontGraveYard;
            if(dieName == terminalName){
               break;
            }
         }
      }  
   }
   
   // return false if game not over, more than one person
   // return true if game over
   public  boolean gameOver() {
      return (frontKillRing.next == frontKillRing);
   }
   
   // if game is not over, return null
   // else, return the game's winner
   // the last person on the kill ring is the winner
   public  String winner() {
      if(!gameOver()){
         return null;
      }
      return (frontKillRing.name);
   }
   
   // Taken in a string name
   // return true if the given string name
   // is in the current grave yard
   // return false if not
   public boolean graveyardContains(String name) {
      return(containsCheck(name,frontGraveYard));
   }
   
   // Taken in a string name
   // return true if the given string name
   // is in the current killRing
   // return false if not
   public  boolean killRingContains(String name) {
      return(containsCheck(name,frontKillRing));
   }
   
   // Taken in a given string and the AssassinNode
   // check to see if the given string name is in list
   // return true if it is there
   // return false if can't find the name in the list
   private  boolean containsCheck(String name, AssassinNode front){
      AssassinNode current = front;
      if(front != null){
         if(current == front){
            if((current.name.equalsIgnoreCase(name))){
               return true;
            }
            current = current.next;
         }
         while(current != front){
            if((current.name.equalsIgnoreCase(name))){
               return true;
            }
            current = current.next;
         }
      }
      return false;
   }
   
   // Taken in a string name of the victim
   // Record the killer of the victim
   // Move the victim from kill ring to grave yard
   // pre: the victim name is in the kill ring list
   // if not, throw IllegalArgumentException
   // pre: the game is not over yet
   // throw IllegalStateException if it is over
   public void kill(String name) {
      if(!killRingContains(name)){
         throw new IllegalArgumentException();
      }
      if(gameOver()){
         throw new IllegalStateException();
      }
      AssassinNode theVictim = frontKillRing;
      AssassinNode theKiller = frontKillRing;
      AssassinNode newGhost = frontGraveYard;
      
      while(!theVictim.name.equalsIgnoreCase(name)){
         theVictim = theVictim.next;
      }
      while(theKiller.next.name != theVictim.name){
         theKiller = theKiller.next;
      }
      
      theVictim.killer = theKiller.name;  // store killer name
      theKiller.next = theKiller.next.next;  // change the killer
      frontKillRing = theVictim.next;
      theVictim.next = null;  
       
      if(frontGraveYard == null){   // started empty
         frontGraveYard = theVictim;   // move the victim to grave yard
         theVictim.next = theVictim;      
      }else if(newGhost.next == frontGraveYard){
         newGhost.next = theVictim;
         theVictim.next = frontGraveYard;               
      }else{
         while(newGhost.next != frontGraveYard){
            newGhost = newGhost.next;
         }  
         newGhost.next = theVictim;
         theVictim.next = frontGraveYard;
      }  
   }
}
