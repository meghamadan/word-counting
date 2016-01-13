package proj5;

/**
 * BSTNode class is used to represent nodes in a binary search tree.
 * It contains a data item that has to implement Comparable interface
 * and references to left and right subtrees. 
 * 
 * Credit: 
 * @author Joanna Klukowska
 * @version Mar 29, 2014
 * 
 * Modified by: Megha Madan
 *
 * @param <T> 
 *    a reference type that implements Comparable<T> interface 
 */
public class BSTNode <T extends Comparable <T> > 
					implements Comparable <BSTNode <T> > {
	//reference to the left subtree 
	private BSTNode <T> left;
	//reference to the right subtree
	private BSTNode <T> right;
	//data item stored in the node
	private T data;
	//count to keep track of how many times the data
	private int count;
	
	/**
	 * Constructs a BSTNode initializing the data part 
	 * according to the parameter and setting both 
	 * references to subtrees to null.
	 * @param newData
	 *    data to be stored in the node
	 */
	public BSTNode(T newData) {
		this.data = newData;
		left = null;
		right = null;
		
		count = 1;
	}

	/**
	 * Left subtree accessor. 
	 * @return 
	 *    reference to the left subtree of a node
	 */
	public BSTNode<T> getLeft() {
		return left;
	}
	
	/**
	 * Changes the reference to the left subtree to the one 
	 * specified in the parameter.
	 * @param 
	 *    reference to the new left subtree of the node.
	 */
	public void setLeft(BSTNode<T> left) {
		this.left = left;
	}
	
	/**
	 * Right subtree accessor. 
	 * @return 
	 *    reference to the right subtree of a node
	 */
	public BSTNode<T> getRight() {
		return right;
	}
	
	/**
	 * Changes the reference to the right subtree to the one 
	 * specified in the parameter.
	 * @param 
	 *    reference to the new right subtree of the node.
	 */
	public void setRight(BSTNode<T> right) {
		this.right = right;
	}
	
	/**
	 * Returns a reference to the data stored in the node. 
	 * @return 
	 *    reference to the data stored in the node
	 */
	public T getData() {
		return data;
	}
	
	/**
	 * Changes the data stored in the node to the one 
	 * specified in the parameter.
	 * @param 
	 *    reference to the new data of the node
	 */
	public void setData(T data) {
		this.data = data;
	}	
	
	/**
	 * increments the count of the specific node by 1
	 */
	public void incrementCount( ){
		count++;
	}
	
	/**
	 * @return the count stored in the node
	 */
	public int getCount( ){
		return count;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(BSTNode<T> other) {
		
		//comparing by the data of the node (the String word)
		return this.data.compareTo(other.data);
	} 

	
}
