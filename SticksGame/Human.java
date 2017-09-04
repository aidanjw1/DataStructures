// Human
// Class for Human Player in SticksGame
// Author: Aidan White

import java.util.Scanner;

public class Human implements Player
{
   // Instance variables
   public String name;
   
   
   // Human
   // Constructor Method
   // Takes name of player as parameter
   public Human(String name)
   {
      this.name = name;
   }
   
   
   // move
   // Allows Human player to take 1-3 sticks from the board
   // Takes number of remaining sticks as a parameter
   // Returns number of remaining sticks after move is taken
   public int move(int numSticks)
   {
      System.out.println();
      System.out.println("There are " + numSticks + " stick(s) on the board.");
      System.out.print(this.name + ": How many sticks would you like to take (1-3)? ");
      Scanner keyboard = new Scanner(System.in);
      int take = keyboard.nextInt();
      // Makes sure that user can only take 1-3 sticks
      // and that they cannot take more sticks than are left
      while(take < 1 || take > 3 || take > numSticks)
      {
         System.out.print("Invalid move: ");
         if(take < 1 || take > 3)
         {
            System.out.print("You must choose between 1-3 sticks. Try again: ");
         } else
         {  
            System.out.print("You cannot take more sticks than are left. Try again: ");
         }
         take = keyboard.nextInt();
         System.out.println();
      }
      return numSticks - take;
   }
   
   
   // startGame
   // Prints start of game text
   public void startGame()
   {
      System.out.println(this.name + ": Good Luck!");
   }
   
   
   // endGame
   // Prints end of game text
   // Takes win as a parameter to know if player instance won game   
   public void endGame(boolean win) 
   {
      if(!win)
      {
         System.out.println(this.name + ": You win!");
      } 
      else
      {
         System.out.println(this.name + ": You Lose."); 
      }    
   }
}  
