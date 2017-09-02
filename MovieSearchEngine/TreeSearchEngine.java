// TreeSearchEngine
// Loads all movie titles and plots from the IMDB database into a map.
// Prints out the titles and plots of all movies in the database beginning
// with a prefix input by the user.

import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;

public class TreeSearchEngine{

	// Class variables.
	private static TreeMapForStrings treeMap = new TreeMapForStrings();
	private static ArrayList<String> movieList = new ArrayList<String>();
	private static long timeForAdding = 0;
	private static long timeToRead = 0;
	
	public static void main(String[] args) throws IOException{
		String filename = args[0];
		long time1 = getTime();
		loadMovies(filename);
		long time2 = getTime();
		// Find the total time taken to load the movies into the map.
		long time = time2 - time1;
		System.out.println("Total time taken: " + time + " milliseconds");
		System.out.println("Time taken to read: " + timeToRead + " milliseconds");
		System.out.println("Time taken for adding: " + timeForAdding + " milliseconds");
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Enter a movie prefix, or enter '####' to exit: ");
		String userIn = keyboard.nextLine();
		while(!userIn.equals("####")){
			find(userIn);
			System.out.print("Enter a movie prefix, or enter '####' to exit: ");
			userIn = keyboard.nextLine();
		}
	}
	
	// loadMovies
	// Loads the movie titles and plots into the dictionary
	// Takes the file name as a parameter.
	public static void loadMovies(String filename) throws IOException{
		Scanner input = new Scanner(new File(filename), "ISO-8859-1");
		int numMovies = 0;
		while(input.hasNextLine() && input.hasNext()){
		long readTime1 = getTime();
         // If the next line begins with "MV:", save it as the title.
         if(input.next().equals("MV:")){
            String title = input.nextLine().trim();
            // Save the following lines that begin with "PL:" as the plot.
            String plot = "";
            while(input.hasNextLine()){
               if(input.next().equals("PL:")){ 
                  plot += input.nextLine();
                  plot += "\n";
               } else
                  break; 
            }
            long readTime2 = getTime();
            // Calculate the time taken for each iteration of the while loop which
            // reads the file, and add it to total readTime.
            long readTime = readTime2 - readTime1;
            timeToRead += readTime;
            // Get the time taken for adding an entry to the tree each time, and
            // sum the times into timeForAdding.
            long addTime1 = getTime();
         	treeMap.put(title, plot);
         	long addTime2 = getTime();
         	long addTime = addTime2 - addTime1;
         	timeForAdding += addTime;
         	numMovies ++;
         	if(numMovies % 1000 == 0){
         		System.out.println(numMovies + " movies loaded...");
         	}
          }
        }
    }
    
    // find
    // Prints the title and plot for all movies beginning with the 
    // prefix input by the user.
    public static void find(String prefix){
    	// Add movies beginning with prefix into a list.
    	movieList = treeMap.getKeysForPrefix(prefix);
    	System.out.println();
    	for(int i = 0; i < movieList.size(); i ++){
    		System.out.println(movieList.get(i));
    		System.out.println();
    		System.out.println(treeMap.getValue(movieList.get(i)));
    	}
	}
	
	// getTime
	// Returns the current time.
	private static long getTime(){
      return System.currentTimeMillis();
   }
}