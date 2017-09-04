/*
    SticksGame

    Main game class for game of sticks

    Player can play against either another human player, a
    "dumb" AI player or a trained AI player, which plays against itself
    several times before the game and stores the winning moves to play
    with a higher probablity in the future. Players take turns taking 1 to
    3 sticks from the "pile," and the player to take the last stick loses.
    Assignment for CS201 Data Structures with Java.

    Author: Aidan White

*/

import java.util.Scanner;

public class SticksGame
{
   // Variable print to turn off print statements while training AI
   static boolean print = true; 

   
   // playGameOnce
   // Play the game of sticks once:
   // Game starts, players take turns taking 1-3 sticks until none remain
   // Takes two Player objects and the total number of beginning sitcks as parameters 
   public static void playGameOnce(Player player1, Player player2, int totalSticks)
   {
		 player1.startGame();
		 player2.startGame();
       boolean win = false;
		 // Players take turns making moves until 0 sticks left
       while (totalSticks > 0)
		   {
			   totalSticks = player1.move(totalSticks);
			   if (totalSticks == 0)
			   {
				   win =	true;
					break;
			   }
			   totalSticks = player2.move(totalSticks);
         }
		   player1.endGame(win);
		   win = !win;
		   player2.endGame(win);
    }
   
   
   // main
   // Runs the game of sticks
   public static void main(String[] args)
   {
      System.out.println("Welcome to the game of sticks!");
      System.out.print("How many sticks are there on the table initally? (10-100)? ");
      Scanner scanner = new Scanner(System.in);
      int totalSticks = scanner.nextInt();
      while (totalSticks < 10 || totalSticks > 100)
      {
         System.out.println("Please enter a number between 10 and 100.");
         System.out.print("How many sticks are there on the table initally? (10-100)? ");
         totalSticks = scanner.nextInt();
      }      
      System.out.println("Options:");
      System.out.println("Play against a friend (1)");
      System.out.println("Play against the computer (2)");
      System.out.println("Play against trained AI (3)");
      System.out.print("Which option do you take (1-3)? ");
      int option = scanner.nextInt();
      
      if (option == 1)
      {
         Player player1 = new Human("Player 1");
         Player player2 = new Human("Player 2");
         playGameOnce(player1, player2, totalSticks);         
      }
      else if (option == 2)
      {
         Player player1 = new Human("Player 1");
         Player player2 = new AI("Player 2", totalSticks);
         // Allow player to choose to play again at end of game
         boolean playAgain = true; 
         while(playAgain)
         {
            playGameOnce(player1, player2, totalSticks);
            if (SticksGame.print)
            {
               System.out.println();
            }
            System.out.print("Would you like to play again? (y/n) ");
            Scanner input = new Scanner(System.in);
            String choice = input.next();
            while((!choice.equals("y")) && (!choice.equals("n")))
            {
               System.out.println("Enter 'y' to play again or 'n' to quit.");
               choice = input.next();
               System.out.println(choice);
            }
            if (choice.equals("n"))
            {
               playAgain = false;
            }
         }
      }    
      else if (option == 3)
      {
         Player player1 = new AI("Player 1", totalSticks);
         Player player2 = new AI("Player 2", totalSticks);
         System.out.println("Training AI, please wait...");
         // Turn print statements off while training AI
         print = false;
         // Have AIs play 1,000,000 games to train
         for(int i = 0; i < 1000000; i++)
         {
            playGameOnce(player1, player2, totalSticks);
         }
         // Turn print statemtns back on for game against human
         print = true;
         Player player3 = new Human("Player 1");
         // Allow player to choose to play again at end of game
         boolean playAgain = true;
         while (playAgain)
         {
            playGameOnce(player3, player2, totalSticks);
            System.out.println();
            System.out.print("Would you like to play again? (y/n) ");
            Scanner input = new Scanner(System.in);
            String choice = input.next();
            while((!choice.equals("y")) && (!choice.equals("n")))
            {
               System.out.println("Enter 'y' to play again or 'n' to quit.");
               choice = input.next();
               System.out.println(choice);
            }
            if (choice.equals("n"))
            {
               playAgain = false;
            }
         }
      }else{
         System.out.println("Bad choice.");
      }   
   }
}