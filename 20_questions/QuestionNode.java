// Ai Quynh Nguyen
// CSE 143
// 2/25/18
// TA:Aaron Johnston
// Assisgnment #7
//
// This program construct QuestionNode for QuestionTree binary tree search

public class QuestionNode{
   public String data; // data store in this node
   public QuestionNode left; // reference to left subtree
   public QuestionNode right; // reference to right subtree
   
   // construct a leaf node with given data
   public QuestionNode(String data){
      this(data, null, null);
   }
      
   // constructs a branch node with given data and links
   public QuestionNode(String data, QuestionNode left, QuestionNode right){
      this.data = data;
      this.left = left;
      this.right = right;
   }
}