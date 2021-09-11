// --== CS400 File Header Information ==--
// Name: Joseph O'Connell
// Email: jpoconnell2@wisc.edu
// Team: FB
// Role: Data Wrangler 2
// TA: Abhay Kumar
// Lecturer: Gary Dahl
// Notes to Grader:
import java.util.LinkedList;

/**
 * Binary Search Tree implementation with a Node inner class for representing the nodes within a
 * binary search tree. You can use this class' insert method to build a binary search tree, and its
 * toString method to display the level order (breadth first) traversal of values in that tree.
 */
public class RedBlackTree<T extends Comparable<T>> {

  /**
   * This class represents a node holding a single value within a binary tree the parent, left, and
   * right child references are always be maintained.
   */
  protected static class Node<T> {
    public T data;
    public boolean isBlack;
    public Node<T> parent; // null for root node
    public Node<T> leftChild;
    public Node<T> rightChild;

    public Node(T data) {
      this.data = data;
      this.isBlack = false; // red by default
    }

    /**
     * @return true when this node has a parent and is the left child of that parent, otherwise
     *         return false
     */
    public boolean isLeftChild() {
      return parent != null && parent.leftChild == this;
    }

    /**
     * This method performs a level order traversal of the tree rooted at the current node. The
     * string representations of each data value within this tree are assembled into a comma
     * separated string within brackets (similar to many implementations of java.util.Collection).
     * 
     * @return string containing the values of this tree in level order
     */
    @Override
    public String toString() { // display subtree in order traversal
      String output = "[";
      LinkedList<Node<T>> q = new LinkedList<>();
      q.add(this);
      while (!q.isEmpty()) {
        Node<T> next = q.removeFirst();
        if (next.leftChild != null)
          q.add(next.leftChild);
        if (next.rightChild != null)
          q.add(next.rightChild);
        output += next.data.toString();
        if (!q.isEmpty())
          output += ", ";
      }
      return output + "]";
    }
  }

  protected Node<T> root; // reference to root node of tree, null when empty

  /**
   * Performs a naive insertion into a binary search tree: adding the input data value to a new node
   * in a leaf position within the tree. After this insertion, no attempt is made to restructure or
   * balance the tree. This tree will not hold null references, nor duplicate data values.
   * 
   * @param data to be added into this binary search tree
   * @throws NullPointerException     when the provided data argument is null
   * @throws IllegalArgumentException when the tree already contains data
   */
  public void insert(T data) throws NullPointerException, IllegalArgumentException {
    // null references cannot be stored within this tree
    if (data == null)
      throw new NullPointerException("This RedBlackTree cannot store null references.");

    Node<T> newNode = new Node<>(data);
    if (root == null) {
      root = newNode;
    } // add first node to an empty tree
    else
      insertHelper(newNode, root); // recursively insert into subtree

    // set root equal to black
    root.isBlack = true;
  }

  /**
   * Recursive helper method to find the subtree with a null reference in the position that the
   * newNode should be inserted, and then extend this tree by the newNode in that position.
   * 
   * @param newNode is the new node that is being added to this tree
   * @param subtree is the reference to a node within this tree which the newNode should be inserted
   *                as a descenedent beneath
   * @throws IllegalArgumentException when the newNode and subtree contain equal data references (as
   *                                  defined by Comparable.compareTo())
   */
  private void insertHelper(Node<T> newNode, Node<T> subtree) {
    int compare = newNode.data.compareTo(subtree.data);
    // do not allow duplicate values to be stored within this tree
    if (compare == 0)
      throw new IllegalArgumentException("This RedBlackTree already contains that value.");

    // store newNode within left subtree of subtree
    else if (compare < 0) {
      if (subtree.leftChild == null) { // left subtree empty, add here
        subtree.leftChild = newNode;
        newNode.parent = subtree;
        // otherwise continue recursive search for location to insert
      } else
        insertHelper(newNode, subtree.leftChild);
    }

    // store newNode within the right subtree of subtree
    else {
      if (subtree.rightChild == null) { // right subtree empty, add here
        subtree.rightChild = newNode;
        newNode.parent = subtree;
        // otherwise continue recursive search for location to insert
      } else
        insertHelper(newNode, subtree.rightChild);
    }
    // enforce the RBT properties
    enforceRBTreePropertiesAfterInsert(newNode);
  }

  /**
   * Recursive (sometimes) helper method to reorder and recolor the RBT to follow these rules. 1.
   * the root of the tree is black (done at end of insert method) 2. red nodes have 0 or 2 black
   * children 3. from every node path, the number of black nodes is the same
   * 
   * The majority of this method resolves instances of red node insertion under a red node. The
   * three cases defined below restructure the tree.
   * 
   * @param newNode the new node that was added to the tree
   */
  private void enforceRBTreePropertiesAfterInsert(Node<T> newNode) {
    // If parent is null do nothing
    // this is the root, color is defined later
    if (newNode.parent == null) {
      return;
    }

    // If parent is black do nothing
    // children of root get removed here
    if (newNode.parent.isBlack) {
      return;
    }

    // If parent is red go here

    // Case 1: parent sibling is red

    // treat null references like black nodes so, skip case 1 if uncle is black

    // LOGIC (NOT(parent is left child and sibling of parent is null) OR
    // (parent is right and sibling of parent is null))
    // This is case 2, skip it.

    if (!((newNode.parent.isLeftChild() && newNode.parent.parent.rightChild == null)
        || (!newNode.parent.isLeftChild() && newNode.parent.parent.leftChild == null))) {

      // if we reach here, the sibling is defined but not necessarilly red

      // LOGIC parent is left child and sibling of parent is red or
      // parent is right child and sibling of parent is red

      if ((newNode.parent.isLeftChild() && !newNode.parent.parent.rightChild.isBlack)
          || (!newNode.parent.isLeftChild() && !newNode.parent.parent.leftChild.isBlack)) {

        // change parent and sibling to black
        newNode.parent.isBlack = true;
        if (newNode.parent.isLeftChild()) {
          newNode.parent.parent.rightChild.isBlack = true;
        } else {
          newNode.parent.parent.leftChild.isBlack = true;
        }

        // change grandparent node to red and recurse
        newNode.parent.parent.isBlack = false;
        enforceRBTreePropertiesAfterInsert(newNode.parent.parent);
        return;
      }
    }

    // Case 2: Parent sibling is black & on same side as new node
    // This is case 3 in the video but makes sense to put it here

    // LOGIC node is left child, parent is right child, and sibling of parent is null or black
    // OR node is right child, parent is left child, and sibling of parent is null or black
    if ((newNode.isLeftChild() && !newNode.parent.isLeftChild()
        && (newNode.parent.parent.leftChild == null || newNode.parent.parent.leftChild.isBlack))
        || (!newNode.isLeftChild() && newNode.parent.isLeftChild()
            && (newNode.parent.parent.rightChild == null
                || newNode.parent.parent.rightChild.isBlack))) {

      // Case 2 code is to rotate then call case 3
      rotate(newNode, newNode.parent);
    }

    // Case 3 is a catchall so do not need if statements

    // Recolor prior to rotating since rotating changes references
    newNode.parent.isBlack = true;
    newNode.parent.parent.isBlack = false;

    // rotate parent and grandparent
    rotate(newNode.parent, newNode.parent.parent);
  }

  /**
   * This method performs a level order traversal of the tree. The string representations of each
   * data value within this tree are assembled into a comma separated string within brackets
   * (similar to many implementations of java.util.Collection, like java.util.ArrayList, LinkedList,
   * etc).
   * 
   * @return string containing the values of this tree in level order
   */
  @Override
  public String toString() {
    return root.toString();
  }

  /**
   * Performs the rotation operation on the provided nodes within this BST. When the provided child
   * is a leftChild of the provided parent, this method will perform a right rotation (sometimes
   * called a left-right rotation). When the provided child is a rightChild of the provided parent,
   * this method will perform a left rotation (sometimes called a right-left rotation). When the
   * provided nodes are not related in one of these ways, this method will throw an
   * IllegalArgumentException.
   * 
   * @param child  is the node being rotated from child to parent position (between these two node
   *               arguments)
   * @param parent is the node being rotated from parent to child position (between these two node
   *               arguments)
   * @throws IllegalArgumentException when the provided child and parent node references are not
   *                                  initially (pre-rotation) related that way
   */
  private void rotate(Node<T> child, Node<T> parent) throws IllegalArgumentException {
    // account for bad input
    if (child == null || parent == null) {
      throw new IllegalArgumentException("can't rotate null references");
    }
    boolean isRoot = false; // keeps track if the root has been modified
    // Use left child
    if (child.isLeftChild()) {
      if (parent.leftChild == child) {
        // Rotate right
        if (parent.parent == null) {
          isRoot = true;
        }

        // parent is not root
        else {
          if (parent.isLeftChild()) {
            parent.parent.leftChild = child;
          } else {
            parent.parent.rightChild = child;
          }
        }
        // make temp node for right of child
        Node<T> temp = child.rightChild;
        Node<T> tempParent = parent.parent;
        // fix right reference to parent
        child.rightChild = parent;
        parent.parent = child;

        // fix left reference of parent
        parent.leftChild = temp;
        if (temp != null) {
          temp.parent = parent;
        }

        // fix parent reference of child
        child.parent = tempParent;

        // fix root if it was modified
        if (isRoot) {
          root = child;
        }
        return;
      } else {
        throw new IllegalArgumentException("child and parent are not related");
      }
    }

    // check for null status of right child
    if (parent.rightChild == null) {
      throw new IllegalArgumentException(
          "child and parent aren't related.  " + "Right reference null");
    }
    // check that right child matches given child
    if (parent.rightChild != child) {
      throw new IllegalArgumentException("child and parent are not related");
    }

    // rotate left
    if (parent.parent == null) {
      isRoot = true;
    }

    // parent is not root
    else {
      if (parent.isLeftChild()) {
        parent.parent.leftChild = child;
      } else {
        parent.parent.rightChild = child;
      }
    }

    // make temp node for left of child
    Node<T> temp = child.leftChild; // could be null
    Node<T> tempParent = parent.parent; // could be null if root

    // fix right reference to parent
    child.leftChild = parent;
    parent.parent = child;

    // fix left reference of parent
    parent.rightChild = temp;
    if (temp != null) {
      temp.parent = parent;
    }

    // fix parent reference of child
    child.parent = tempParent;

    // fix root if it was modified
    if (isRoot) {
      root = child;
    }
  }
}
