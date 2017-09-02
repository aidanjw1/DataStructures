// TreeMapForStrings
// Dictionary/Map implemented using a a binary search tree
// implemented recursively.

import java.util.ArrayList;

public class TreeMapForStrings{
	
	// Node
	// Class for each node of the tree
	// Contains a key and a value as Strings, as well as Nodes for 
	// its left and right children in the tree.
	private static class Node{
		public String key;
		public String value;
		public Node left;
		public Node right;
		
		// Node class constructor.
		public Node(String key, String value, Node left, Node right){
			this.key = key;
			this.value = value;
			this.left = left;
			this.right = right;
		}
	}
	
	// Class Variables
	private static Node root = null;
	private static ArrayList<String> movies = new ArrayList<String>();
	
	// put
	// Initiates the recursive put method on the root
	// Takes the key and value Strings as parameters.
	public static void put(String key, String value){
		root = put(key, value, root);
	}
	
	// put
	// Adds a new Node to the binary search tree in the correct position.
	// Takes the Strings key and value, and the Node subroot as parameters.
	private static Node put(String key, String value, Node subroot){
		if(subroot == null){
			subroot = new Node(key, value, null, null);
		// Move down the tree to the correct position to add the new Node.
		} else if (key.compareTo(subroot.key) < 0) {
			 subroot.left = put(key, value, subroot.left);
		} else if (key.compareTo(subroot.key) > 0) {
			subroot.right = put(key, value, subroot.right);
		} else {
			// Update the value if the key is already in the map.
			subroot.value = value;
		}
		return subroot;
	}
	
	// getValue
	// Initiates the recursive getValue method.
	// Takes the key as a parameter.
	// Returns the value of that key.
	public static String getValue(String key){
		Node movie = getValue(key, root); 
		if(movie != null)
			return movie.value;
		return null;
	}
		
	// getValue
	// Searches the binary search tree for the value of a given key.
	// Takes the String key as a parameter.
	// Returns the key's value.
	private static Node getValue(String key, Node subroot){
		if(subroot == null){
			return null;
		// Move down the tree based on if the key is larger or smaller than the root.
		} else if (subroot.key.compareTo(key) > 0 ){
			subroot = getValue(key, subroot.left);
		} else if (subroot.key.compareTo(key) < 0 ){
			subroot = getValue(key, subroot.right);
		} 
		return subroot;
	}
	
	// getKeysForPrefix
	// Initiates the recursive getKeysForPrefix method.
	// Returns an ArrayList of all the keys beginning with a prefix word.
	// Takes the prefix as a parameter.
	public static ArrayList<String> getKeysForPrefix(String prefix){
		movies = getKeysForPrefix(prefix, root);
		return movies; 
	}
	
	// getKeysForPrefix
	// Searches the tree until a key beginning with the prefix is found.
	// Once found, searches children of this Node for keys beginning with the prefix
	// Takes the prefix and the next Node in the tree subroot as parameters.
	// Returns an ArrayList with movies beginning with the prefix added.
	private static ArrayList<String> getKeysForPrefix(String prefix, Node subroot){
		if(subroot == null){
			return movies;
		}
      // If the key starts with the prefix, add it to the list and run the 
      // method on its children.
		if(subroot.key.startsWith(prefix)){
         movies.add(subroot.key);
			movies = getKeysForPrefix(prefix, subroot.left);
			movies = getKeysForPrefix(prefix, subroot.right);
        // Run the method on the correct subtree depending on whether the key is 
        // greater or less than the prefix.
      } else if (subroot.key.compareTo(prefix) > 0){
			movies = getKeysForPrefix(prefix, subroot.left);
		} else if (subroot.key.compareTo(prefix) < 0){
			movies = getKeysForPrefix(prefix, subroot.right);
      }
		return movies;
	}	
	

	// Main method for testing.
	public static void main(String args[]){
		TreeMapForStrings treeMap = new TreeMapForStrings();
		treeMap.put("Fish", "Shark");
		System.out.println(treeMap.getValue("Fish"));
		treeMap.put("Tree", "Leaf");
		System.out.println(treeMap.getValue("Tree"));
		treeMap.put("What's", "Up?");
		System.out.println(treeMap.getValue("What's"));
		System.out.println(treeMap.getValue("blue"));
		treeMap.put("Fish", "Changed");
		treeMap.put("Fish and other", "Fishes");
		System.out.println(treeMap.getValue("Fish"));
		System.out.println(treeMap.getKeysForPrefix("Fish"));
	}
}