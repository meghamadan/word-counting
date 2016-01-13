package proj5;

/**
 * Representation of a node in a Reference based (specifically, sorted) Linked List
 * Contains reference to next node and the data held in each node
 * Also contains count field
 * 
 * @author Joanna Klukowska & Megha Madan
 *
 * @param <T>
 * 		a reference type that implements Comparable<T> interface 
 */
public class LLNode <T extends Comparable <T> > 
							implements Comparable <LLNode <T> >{
	//reference to the next node
	private LLNode <T> next;
	//data item stored in the node
	private T data;
	//count field that is specific for MostFrequentWords implementation 
	private int count;
	
	
	/**
	 * Default constructor creates an empty node.
	 */
	public LLNode () {
		data = null;
		next = null;
		count = 1;
	}

	/**
	 * Creates a node with specified data item.
	 * @param data
	 *    data item to store in the node
	 */
	public LLNode ( T data ) {
		if (data != null ){
			this.data = data;
			count = 1;
		}
	}

	/**
	 * @return the reference to the next node in the linked list
	 */
	public LLNode<T> getNext() {
		return next;
	}

	/**
	 * @param next - the next node to be set
	 */
	public void setNext(LLNode<T> next) {
		this.next = next;
	}

	/**
	 * @return the data held in a node
	 */
	public T getData() {
		return data;
	}

	/**
	 * @param data - the data to be set
	 */
	public void setData(T data) {
		this.data = data;
	}
	
	/**
	 * @return the count of the node
	 */
	public int getCount( ){
		return count;
	}
	
	/**
	 * method that increases the count by one
	 */
	public void incrementCount(){
		count++;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(LLNode<T> other) {
		return this.data.compareTo(other.data);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return (String) data;
	}
}
