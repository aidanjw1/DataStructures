// AI
// Class for learing AI Player
// Author: Aidan White

public class AI implements Player
{
   // Instance variables
   public String name;
   private int totalSticks;
   private int [] hatsArray;
   private int [] picksArray;
   
   
   // AI
   // Constructor method
   // Takes name of player and total number of sticks as parameters   
   public AI(String name, int totalSticks)
   {
      this.name = name;
      this.totalSticks = totalSticks;
      // Initialize arrays:
      // Hats array will store the balls, each group of three is a hat
      // Picks array stores index of the ball picked each move. Resets each game  
      this.hatsArray = new int [this.totalSticks * 3];
      this.picksArray = new int [this.totalSticks * 3];
      for(int i = 0; i < this.hatsArray.length; i++)
      {
         this.hatsArray[i] = 1;
      }    
   }
   
   
   // move
   // Allows AI player to take 1-3 sticks from the board
   // Takes number of remaining sticks as a parameter
   // Returns number of remaining sticks after move is taken
   public int move(int numSticks)
   { 
      if (SticksGame.print)
      {
         System.out.println();
         System.out.println("There are "+numSticks+" stick(s) on the board.");
      }
      // Get first index of hat (group of 3) corresponding to remaining sticks
      int hatIndex = (numSticks - 1) * 3;
      int taken;
      int moveIndex;
      // Pick one of the three ball positions in the hat at hatIndex
      // Will give higher probablilty to positions with more balls
      // by defining a range for each option within 0 to 1, 
      // picking a random number 0 to 1 and seeing which range it falls into.
      // Referenced: http://stackoverflow.com/questions/9330394/how-to-pick-an-item-by-its-probability 
      int totalBalls = hatsArray[hatIndex] + hatsArray[hatIndex + 1] + hatsArray[hatIndex + 2];
      double probOf1 = (double)(hatsArray[hatIndex]) / totalBalls;
      double probOf2 = (double)(hatsArray[hatIndex+1]) / totalBalls;
      double probOf3 = (double)(hatsArray[hatIndex+2]) / totalBalls;
      double rand = Math.random();
      if (rand >= 0 && rand < probOf1)
      {
         taken = 1;
         moveIndex = hatIndex; 
      } else if( rand >= probOf1 && rand < (probOf1 + probOf2)) 
      {
         taken = 2;
         moveIndex = hatIndex + 1; 
      } else
      {
         taken = 3;
         moveIndex = hatIndex + 2;
      }
      // Makes sure that AI won't take more sticks than are left
      while(taken > numSticks)
      {
         taken --;
      }
      // Add one to picksArray at the index of the ball chosen
      this.picksArray[moveIndex]++;
      if (SticksGame.print)
      {
         System.out.println(this.name + " selects " + taken + " stick(s).");
      }
      return numSticks - taken;
   }
   
   
   // startGame
   // Prints start of game text if print is turned on
   public void startGame()
   {
      // Don't print while AI is training (while print variable is set false) 
      if(SticksGame.print)
         {
            System.out.println(this.name + ": Good Luck!");
         }
   }
   
   
   // endGame
   // Prints end of game text if print is turned on
   // Updates hatsArray depending on win or loss
   // Takes win as a parameter to know if player instance won game
   public void endGame(boolean win)
   {
      if(!win)
      {
         // Don't print while AI is training (while print variable is set false) 
         if(SticksGame.print)
         {
            System.out.println(this.name + ": You win!");
         }
         // If AI wins, add picksArray to hatsArray
         for(int i = 0; i < this.hatsArray.length; i++)
         {
            this.hatsArray[i] += this.picksArray[i];
         }
      }else
      {
         // Don't print while AI is training (while print variable is set false)
         if (SticksGame.print)
         {
            System.out.println(this.name + ": You Lose.");
         }
         // If AI loses, subtract picksArray from hatsArray
         for(int i = 0; i < this.hatsArray.length; i++)
         {
            this.hatsArray[i] -= this.picksArray[i];
            // Balls cannot go less than 1
            if(this.hatsArray[i] < 1)
            {
               this.hatsArray[i] = 1;
            }
         }
      }
      // Print contents of hats after each game while not training AI
      if (SticksGame.print)
      {
         System.out.print("Hats: ");
         for(int i = 0; i < this.hatsArray.length; i++)
         {
            System.out.print(this.hatsArray[i] + " ");
            if(i % 3 == 2)
            {
               System.out.print("  ");
            }
         }
      }
      // Reset picksArray to zeros
      for(int i = 0; i < this.picksArray.length; i++)
      {
         this.picksArray[i] = 0;
      }
   }
}