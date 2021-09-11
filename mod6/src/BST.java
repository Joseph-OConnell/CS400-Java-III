
/**
 * This class implements a basic Binary Search Tree that supports insert and get operations.
 * In addition, the implementation supports traversing the tree in pre-, post-, and in-order.
 * Each node of the tree stores a key, value pair.
 * 
 * @param <K> The key type for this tree. Instances of this type are used to look up key, value pairs in the tree.
 * @param <V> The value type for this tree. An instance of this type is stored in every node, along with a key.
 */
public class BST<K extends Comparable<K>, V> {
  
  /**
   * Interface for 
   * @param <K> the key type that the traverser works with
   * @param <V> the value type that the traverser works with
   */
  public static interface Traverser<K,V> {

      /**
       * Method that is called for every visited node.
       * @param key the key stored in the visited node
       * @param value the value stored in the visited node
       */
      public void visit(K key, V value);

  }
  
  /**
   * A tree traverser that prints all values stored in the visited nodes.
   */
  private static class IntegerValuePrintTraverser implements Traverser<Integer, Integer> {

      /**
       * This method is called for every visited node, printing the value stored in the node.
       */
      public void visit(Integer key, Integer value) {
          System.out.println(value);
      }

  }

    /**
     * Nested node class for the tree. Stores a key, value pair and has references to left and right child.
     */
    private class Node {

        public K key = null;
        public V value = null;

        public Node left = null;
        public Node right = null;

        /**
         * Construct a tree node for a give key, value pair.
         * @param key The key stored in the node. This determines where in the tree the node will be inserted.
         * @param value The value stored in the node alongside the key.
         */
        public Node(K key, V value) {
        this.key = key;
        this.value = value;
        }

    }

    // the root node reference for the tree; is null is tree is empty
    private Node root = null;
    // number of nodes stored in the tree
    private int size;

    /**
     * Insert a new key, value pair into the tree.
     * @param key
     * @param value
     * @throws IllegalArgumentException if key is null or if key is already present in tree
     */
    public void insert(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("null key not allowed");
        }
        this.root = this.insertRecursive(this.root, key, value);
        this.size++;
    }

    /**
     * Recursive helper method for the public insert method. Recursively traverses the tree and find the spot
     * to insert given key.
     * @param node node that is currently visited
     * @param key the key to insert
     * @param value the value to insert
     * @return return the node that is visits to (re-)attach it to the tree
     */
    private Node insertRecursive(Node node, K key, V value) {

        if (node == null) {
            return new Node(key, value);
        } else if (node.key.equals(key)) {
            throw new IllegalArgumentException("no duplicate keys allowed");
        } else if (node.key.compareTo(key) < 0) {
            // new key is larger than current key
            node.right = insertRecursive(node.right, key, value);
            return node;
        } else {
            // new key is smaller then current key
            node.left = insertRecursive(node.left, key, value);
            return node;
        }

    }

    /**
     * Retrieve value that is stored for given key in the tree.
     * @param key the key to look for in the tree
     * @throws IllegalArgumentException if key is null
     * @return value for key or null if key does not exist in tree
     */
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("null key not allowed");
        }
        Node n = getNodeForKey(this.root, key);
        if (n == null) return null;
        return n.value;
    }

    /**
     * Recursive helper method for the public insert method. Recursively traverses the tree and find the spot
     * to insert given key.
     * @param node node that is currently visited
     * @param key the key to insert
     * @param value the value to insert
     * @return return the node that is visits to (re-)attach it to the tree
     */
    private Node getNodeForKey(Node n, K key) {
        if (n == null) {
            return null;
        } else if (n.key.equals(key)) {
            return n;
        } else if (n.key.compareTo(key) < 0) {
            return getNodeForKey(n.right, key);
        } else {
            return getNodeForKey(n.left, key);
        }
    }

    /**
     * Starts a postorder traversal of the tree.
     * @param t the traverser to do the traversal with
     */
    public void postOrderTraversal(Traverser<K,V> t) {
        this.postOrderTraversal(this.root, t);
    }

    /**
     * Helper method that traverses the tree recursively in postorder and calls the traverser
     * for every node.
     * @param n the node that is currently visited
     * @param t the traverser to do the traversal with
     */
    private void postOrderTraversal(Node n, Traverser<K,V> t) {
        if (n != null) {
            postOrderTraversal(n.left, t);
            postOrderTraversal(n.right, t);
            t.visit(n.key, n.value);
        }
    }

    /**
     * Starts a preorder traversal of the tree.
     * @param t the traverser to do the traversal with
     */
    public void preOrderTraversal(Traverser<K,V> t) {
        this.preOrderTraversal(this.root, t);
    }

    /**
     * Helper method that traverses the tree recursively in preorder and calls the traverser
     * for every node.
     * @param n the node that is currently visited
     * @param t the traverser to do the traversal with
     */
    private void preOrderTraversal(Node n, Traverser<K,V> t) {
        if (n != null) {
            t.visit(n.key, n.value);
            preOrderTraversal(n.left, t);
            preOrderTraversal(n.right, t);
        }
    }

    /**
     * Starts an inorder traversal of the tree.
     * @param t the traverser to do the traversal with
     */
    public void inOrderTraversal(Traverser<K,V> t) {
        this.inOrderTraversal(this.root, t);
    }

    /**
     * Helper method that traverses the tree recursively in inorder and calls the traverser
     * for every node.
     * @param n the node that is currently visited
     * @param t the traverser to do the traversal with
     */
    private void inOrderTraversal(Node n, Traverser<K,V> t) {
        if (n != null) {
            inOrderTraversal(n.left, t);
            t.visit(n.key, n.value);
            inOrderTraversal(n.right, t);
        }
    }

    /**
     * Main method with a demo for the tree traverser.
     * @param args the command line arguments passed when running the program
     */
    public static void main(String args[]) {
        BST<Integer, Integer> bst = new BST<Integer, Integer>();
        // map keys to their squares (values)
        bst.insert(7, 49);
        bst.insert(2, 4);
        bst.insert(10, 100);
        bst.insert(1, 1);
        bst.insert(8, 64);
        System.out.println("pre order traversal:");
        bst.preOrderTraversal(new IntegerValuePrintTraverser());
        System.out.println("in order traversal:");
        // TODO: replace this in order traversal's IntegerValuePrintTraverser object with
        // the object of an anonymous class that only prints the keys (not the values)
        // in the visited nodes
        bst.inOrderTraversal(new Traverser<Integer,Integer>() {
         public void visit(Integer key, Integer value) {
              System.out.println(key);
         }
        });
        System.out.println("post order traversal:");
        // TODO: replace this post order traversal's IntegerValuePrintTraverser object with
        // the object of a lambda expression that prints both the key and value in the
        // visited nodes
        bst.postOrderTraversal((key, value) -> System.out.println(key + ", " + value));
    }

}

