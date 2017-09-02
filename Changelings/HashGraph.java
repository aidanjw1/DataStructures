// HashGraph
// Class for a graph implemented using a HashMap as an adjacency list
// with Strings for keys and Nodes for values.

import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayDeque;
import java.util.LinkedList;

public class HashGraph{
    
    // Node
    // Class for values in the HashMap
    // Contains a set of connected keys, a previous key and an indicator
    // of whether the key has been visited in a search.
    private class Node{
        public HashSet<String> values;
        public String prevKey;
        public boolean visited;
        
        public Node(HashSet<String> values){
            this.values = values;
            this.prevKey = null;
            this.visited = false;
        }
    }
    
    private Map<String, Node> map;
    
    public HashGraph(){
        map = new HashMap<String, Node>();
    }
    
    // add
    // Adds a key and its corresponding value (Node) to the map.
    // Takes the key and its set of connected keys as parameters.
    public void add(String key, HashSet<String> values){
        Node node = new Node(values);
        map.put(key, node);
    }
    
    // findShortestPath
    // Performs a breadth-first search on the graph to find the shortest path
    // between the two words. Prints the path.
    // Takes the two words as parameters.
    public void findShortestPath(String key1, String key2){
        ArrayDeque<String> queue = new ArrayDeque<String>();
        // List to represent the path between key1 and key2.
        List<String> path = new LinkedList<String>();
        queue.offer(key1);
        String subkey = "";
        while(!queue.isEmpty()){
            subkey = queue.removeFirst();
            // End the search once the second word has been found.
            if(subkey.equals(key2)){
                break;
            }
            // Set each the previous key for each key in subkey's connections  
            // set to the subkey to remember where it came from, set each key
            // to visited and offer it to the queue if it has not been visited.
            for(String key : map.get(subkey).values){
                if(!map.get(key).visited){
                    map.get(key).prevKey = subkey;
                    map.get(key).visited = true;
                    queue.offer(key);
                }
            }
        }
        map.get(key1).prevKey = null;
        // Starting with the second word, add each previous key to the 
        // beginnig of the path list.
        while(subkey != null){
            path.add(0, subkey);
            subkey = map.get(subkey).prevKey;
        }
        // A correct path has been found if the second key appears at the end
        // of the path list. In this case, print the path.
        if(path.get(path.size()-1).equals(key2)){
            for(String word : path){
                System.out.println(word);
            }
        } else {
            System.out.println("Sorry, no changeling could be found.");
        }
    }
 }