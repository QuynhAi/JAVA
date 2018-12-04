//Ai Quynh Nguyen
//10/7/2017
//CSE 142
//TA:Daniel Sullivan
//Assignment #2
//
// This program is an ASCII image of a Christmas tree. 
public class AsciiArt{
   public static void main(String[] args){
      topPart();
      middlePart();
      theEnd();
   }
   
   //The first layer of the Christmas tree.
   //Triangle shape of stars within 5 lines
   public static void topPart(){
      for( int line = 1; line <= 5; line++){
         for( int spaces = 1; spaces <= -1 * line + 8; spaces++){
            System.out.print(" ");
         }
         for( int stars = 1; stars <= 2 * line - 1; stars++){
            System.out.print("*");
         }
         System.out.println();
      }
   }
   
   //The second layer of the Christmas tree.
   //Draw triangle shape of stars from line 3 to line 7
   public static void middlePart(){
      for( int line = 3; line <= 7; line++){
         for( int spaces = 1; spaces <= -1 * line + 8; spaces++){
            System.out.print(" ");
         }
         for( int stars = 1; stars <= 2 * line - 1; stars++){
            System.out.print("*");
         }
         System.out.println();
      }
   }
   
   //This is drawing the body of the Christmas tree.
   //Draw rectangle shape of the # sign.
   public static void theEnd(){
      for (int line = 1; line <= 5; line++){
         for (int spaces = 1; spaces <= 6; spaces++){
            System.out.print(" ");
         }
         for( int a = 1; a <= 3; a++){
            System.out.print("#");
         }
         System.out.println();
      }
   }
}