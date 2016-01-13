package proj5;

/**
 * Representation of a Sorted Linked List using the LLNode class as nodes in the list
 * 
 * 
 * Credit:
 * @author Joanna Klukowska
 * @version Mar 2, 2014 
 * 
 * Author of the insert method for sorted linked list: Dale, Joyce, Weems from "Object-Oriented
 * Data Structures using JAVA"
 * 
 * All code modified by:
 * Megha Madan 
 * May 1, 2015
 *
 */

public class SLL <T extends Comparable<T>>{

		//reference to the first node
		private LLNode<T> head;
		
		//number of items in the list
		private int numOfElements;
		
		/**
		 * Creates an empty list object. 
		 */
		public SLL ( ) {
			head = null;
			numOfElements = 0;
		}
		
		/**
		 * @return integer representing the number of elements in the list 
		 */
		public int size( ){
			return numOfElements;
		}
		
		/**
		 * method to return the reference to the first node of the list
		 * @return head reference
		 */
		public LLNode<T> getHead(){
			return head;
		}


		/**
		 * Taken from textbook
		 * Method that creates a new node according to given data and inserts it 
		 * into its appropriate location in a sorted list 
		 * @param item - generic type data that will be used to create new node
		 */
		public void insert(T item) {
			
			LLNode<T> prevLoc;
			LLNode<T> location;
			T listElement;
			
			//track the previous reference and the future location the node will be inserted
			location = head;
			prevLoc = null;
			
			//step through the list until the end (when reference = null)
			while(location != null){
				//extract data of current location to compare
				listElement = location.getData();
				//continue through list if the item being inserted is less than next item
				if(listElement.compareTo(item) < 0){
					prevLoc = location;
					location = location.getNext();
				}
				//if the next item is greater than the item to be inserted, the location has been found
				else
					break;
			}
			
			//create new node according to input data
			LLNode<T> newNode = new LLNode<T>(item);
			
			//special case if the item needs to be inserted before the first element in the list
			if(prevLoc == null){
				newNode.setNext(head);
				head = newNode;
			}
			//otherwise, insert the new node between previous reference and current location
			else{
				newNode.setNext(location);
				prevLoc.setNext(newNode);
			}
			//increase number of elements counter by 1
			numOfElements++;
			
		}

	
		/**
		 * Method that removes a node from a list according to the data held in the node
		 * @param item the String data used to determine what node will be removed
		 */
		public void remove(T item) {
			//if item is equal to null, there is nothing to do
			//if the list is empty, there is nothing to do
			// otherwise look for the item
			if (item != null && head != null ) {

				//if there is only one node in the list, we need to handle it
				//separately
				if (numOfElements == 1 ) {
					if ( item.equals( head.getData() ) ) {
						head = null;
						numOfElements--;
					}
				}
				//if there are more elements, but the one we need to remove is the first
				else if ( item.equals( head.getData() )) {
					head = head.getNext() ;
					numOfElements--;
				}
				else { 
					//create a current reference 
					LLNode<T>  current = head;
					//keep checking the data in nodes until either
					//a matching node is found or we reached the end of the list
					while ( current.getNext() != null 
							&& !item.equals((current.getNext()).getData() ) ) 
					    current = current.getNext();
					
					//if matching node found, disconnect it
					if (current.getNext() != null )  {
						current.setNext(current.getNext().getNext() );
						numOfElements--;
					}
				}
			}		
		}

		
		/**
		 * Method that checks if a certain String is already in a sorted list
		 * @param item - String data that we are looking for
		 * @return boolean value of true or false according to whether or not the data is in the list
		 */
		public boolean contains(T item) {
			//if item is equal to null, there is nothing to do
			//if the list is empty, there is nothing to do.
			// otherwise look for the item
			if (item != null && head != null ) {

				//if there is only one node in the list, we need to handle it
				//separately
				if (numOfElements == 1 ) {
					if ( item.equals( head.getData() ) ) {
						head.incrementCount();
						return true;
					}
					else return false;
				}
				//special case if item we are looking for is the first in the list
				else if(item.equals(head.getData())){
					head.incrementCount();
					return true;
				}
				else { 
					//create a current reference and advance to the second node
					LLNode<T>  current = head.getNext();
					//keep checking the data in nodes until either
					//a matching node is found or we reached the end of the list
					while ( current != null 
							&& !item.equals((current.getData() ) ) )
					    current = current.getNext();
					
					//if matching node found return true
					if (current != null )  {
						current.incrementCount();
						return true;
					}
				}
			}		
			return false;
		}
		
		/**
		 * Method that will remove ant items in the list with corresponding counts below a certain given value
		 * @param list the first node of the linked list to be traversed through
		 * @param cutoffValue the specific value that will be compared to
		 */
		public void pruneList(LLNode<T> list, int cutoffValue){
			//traverse the linked list
			while(list.getNext() != null){
				//if it is less than the value, remove it from the list
				if(list.getCount() < cutoffValue){
					remove(list.getData());
				}
				//contine through the list
				list = list.getNext();
				
			}
			//to use the while loop and getNext method correctly, we will assess the last node separately 
			if(list.getCount() < cutoffValue)
				remove(list.getData());
		}


		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			StringBuffer s = new StringBuffer();
			LLNode<T>  current = head;
			//traverse the list as long as next doesn't equal null
			while (current != null ) {
				//add the word and its count to StringBuffer object
				s.append( current.getData().toString() + " " + current.getCount() + "\r\n");
				current = current.getNext();
			}
			return s.toString();
		}
		
		
	}
