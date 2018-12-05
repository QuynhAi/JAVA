// Ai Quynh Nguyen
// CSE 142
// 10/13/17
// TA:Daniel Sullivan
// Assisgnment #3
//
// This program is using graphics to draw a house with a tree.

import java.awt.*;

public class Doodle{
   public static void main(String[]args){
   
      // Set up a panel and set background color
      DrawingPanel panel = new DrawingPanel(250, 150);
      panel.setBackground(Color.YELLOW);
      Graphics g = panel.getGraphics();
      
      // Draw the body of the house and tree in blue rectangle.
      g.setColor(Color.BLUE);
      g.fillRect(40, 75, 65, 90);
      g.fillRect(185, 120, 10, 50);
      
      // Draw the door and window of the house in red.
      g.setColor(Color.RED);
      g.fillOval(45, 80, 20, 20);
      g.fillRect(60, 120, 20, 30);
      
      
      // Draws the roof of the house in black lines.
      g.setColor(Color.BLACK);
      g.drawLine(30, 75, 115, 75);
      g.drawLine(30, 75, 70, 20);
      g.drawLine(70, 20, 115, 75);
      
      // Draw the top of the tree in black lines.
      g.drawLine(175, 120, 190, 80);
      g.drawLine(205, 120, 190, 80);
      g.drawLine(175, 120, 205, 120);
   }
}