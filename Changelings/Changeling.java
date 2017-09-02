// Changelings
// Class to find a changelings path between two words given as command line
// arguments. Implemented using a graph of words and a breadth first seach to 
// find the shortest path between the two words.

import java.util.Scanner;
import java.util.HashSet;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;

public class Changeling{
    
    public static void main(String[] args) throws IOException{
        HashGraph graph = new HashGraph();
        if(args.length > 3){
            System.out.println("Error: Too many arguments.");
        } else if (args.length < 3){
            System.out.println("Error: Please enter the word list and two words as arguments.");
        } else {
            String filename = args[0];
            String word1 = args[1];
            String word2 = args[2];
            // If the words are the same length and both words are present in the
            // file, find the shortest path between them.
            if(word1.length() != word2.length()){
                System.out.println("Error: The words must be the same length.");
            } else {
                if(loadGraph(graph, filename, word1, word2))
                    graph.findShortestPath(word1, word2);
            }
        }
    }
    
    // loadGraph
    // Loads the words from the text file into the graph.
    // Takes the file and the two words as arguments.
    // Returns true if both words are in the file, and false if at least one is not.
    private static boolean loadGraph(HashGraph graph, String filename, 
                                     String word1, String word2) 
    throws IOException{
        // Add words from the file of the same size as the changelings words 
        // into a list to more easily work with them.
        ArrayList<String> words = new ArrayList<String>();
        Scanner input = new Scanner(new File(filename));
        while(input.hasNext()){
            String word = input.next();
            if(word.length() == word1.length())
                words.add(word);
        }
        if(words.contains(word1) && words.contains(word2)){
            // For each word in the list, add all other words that differ by one 
            // letter to its list of connections.
            for(int i = 0; i < words.size(); i++){
                HashSet<String> connections = new HashSet<String>();
                String key = words.get(i);
                for(int j = 0; j < words.size(); j ++){
                    String edge = words.get(j);
                    int lettersOff = 0;
                    boolean oneOff = true;
                    // Loop through characters of the edge comparing them to the
                    // key. If there is more than one different character, do not
                    // add the edge to the list of connections.
                    for(int k = 0; k < edge.length(); k ++){
                        if(edge.charAt(k) != key.charAt(k)){
                            lettersOff ++;
                        }
                        if(lettersOff > 1){
                            oneOff = false;
                            break;
                        }
                    }
                    if(oneOff){
                        connections.add(edge);
                    }
                }
                // Add the key and its list of connections to the graph.
                graph.add(key, connections);
            }
            return true;
        // Return false if one of the words was not present in the file.
        } else {
            System.out.println("Error: One of your words is invalid.");
            return false;
        }    
    }
}