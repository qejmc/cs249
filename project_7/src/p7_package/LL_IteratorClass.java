package p7_package;

/**
 * Description: Simple iterator class that manages integers using a linked list 
 * engine
 * 
 * @author William Rogers
 *
 */
public class LL_IteratorClass 
{
	/**
	 * reference to current node
	 */
	private NodeClass currentRef;
	
	/**
	 * reference to current node
	 */
	private NodeClass headRef;
	
	/**
	 * reference to current node
	 */
	private static final char LEFT_BRACKET = '[';
	
	/**
	 * reference to current node
	 */
	private static final int NOT_FOUND = -1;
	
	/**
	 * reference to current node
	 */
	private static final char RIGHT_BRACKET = ']';
	
	/**
	 * constant use for space
	 */
	private static final char SPACE = ' ';
	
	/**
	 * Internal/Nested Node class for storing integers
	 *
	 */
	private class NodeClass
	{
		/**
		 * NodeClass data
		 */
		int data;
		
		/**
		 * NodeClass next reference
		 */
		NodeClass nextRef;
		
		/**
		 * NodeClass default constructor
		 */
		@SuppressWarnings("unused")
		public NodeClass()
		{
			this(0);
		}
		
		/**
		 * NodeClass initialization constructor
		 * 
		 * @param value integer value for initialization
		 */
		public NodeClass(int value)
		{
			data = value;
			nextRef = null;
		}
		
		/**
		 * NodeClass copy constructor
		 * 
		 * @param copied NodeClass object to be copied
		 */
		public NodeClass(NodeClass copied)
		{
			data = copied.data;
			nextRef = null;
		}
	}
	
	/**
	 * Default constructor, initializes references to null
	 */
	public LL_IteratorClass()
	{
		headRef = null;
		currentRef = null;
	}
	
	/**
	 * Copy constructor
	 * 
	 * @param copied LL_IteratorClass object to be copied
	 */
	public LL_IteratorClass(LL_IteratorClass copied)
	{
		headRef = null;

		if(copied.headRef != null)
		{
			headRef = new NodeClass(copied.headRef);
			
			currentRef = new NodeClass(copied.currentRef);
			
			while(copied.currentRef != null)
			{
				currentRef.nextRef = new NodeClass(copied.currentRef);
				
				currentRef = currentRef.nextRef;
				
				copied.currentRef = copied.currentRef.nextRef;
			}
		}
	}
	
	/**
	 * Clears iterator of all valid values by setting head reference to null
	 */
	public void clear()
	{
		headRef = null;
	}
	
	/**
	 * Description: shows iterator from beginning to end with brackets around 
	 * current index
	 * <p>
	 * Displays "Empty" if list is empty
	 */
	public void displayIterator()
	{
		NodeClass wkgRef;
		
		if(!isEmpty())
		{
			wkgRef = headRef;
			
			System.out.print("\nIterator: ");
			
			while(hasNext())
			{
				if(wkgRef == currentRef)
				{
					System.out.format("%s%d%s", LEFT_BRACKET, wkgRef.data, 
							RIGHT_BRACKET);
				}
				
				else
				{
					System.out.print(wkgRef.data);
				}
				
				System.out.print(SPACE);
			}
			
		}
		
	}
	
	/**
	 * returns reference prior to current reference
	 * <p>
	 * Note: Returns null if current reference is at head
	 * 
	 * @return NodeClass object found at current
	 */
	public NodeClass getCurrentPriorRef()
	{
		NodeClass wkgRef;
		
		if(currentRef == headRef || isEmpty())
		{
			return null;
		}
		
		wkgRef = headRef;
		
		while(wkgRef.nextRef != currentRef)
		{
			wkgRef = wkgRef.nextRef;
		}
		
		return wkgRef;
	}
	
	/**
	 * returns value at current location
	 * 
	 * @return integer value at current location
	 */
	public int getValueAtCurrent()
	{
		return currentRef.data;
	}
	
	/**
	 * checks for next element available in iterator
	 * <p>
	 * Note: Uses one line of code
	 * 
	 * @return Boolean result of test
	 */
	public boolean hasNext()
	{
		return currentRef.nextRef != null;
	}
	
	/**
	 * checks for previous element available in iterator
	 * <p>
	 * Note: Uses one line of code
	 * 
	 * @return Boolean result of test
	 */
	public boolean hasPrev()
	{
		return getCurrentPriorRef() != null;
	}
	
	/**
	 * inserts item into iterator at current location
	 * <p>
	 * Note: Does not affect current location
	 * 
	 * @param newVal integer value to be inserted
	 */
	public void insertAtCurrent(int newVal)
	{
		NodeClass wkgRef;
		
		if(isEmpty())
		{
			headRef = new NodeClass(newVal);
		}
		
		else
		{
			wkgRef = currentRef;
			
			currentRef = new NodeClass(newVal);
			
			currentRef.nextRef = wkgRef;
		}
	}
	
	/**
	 * inserts item into iterator at end of iterator
	 * <p>
	 * Note: Does not affect current location
	 * 
	 * @param newVal integer value to be inserted
	 */
	public void insertAtEnd(int newVal)
	{
		NodeClass wkgRef;
		
		if(!isEmpty())
		{
			wkgRef = new NodeClass(headRef);
			
			while(hasNext())
			{
				wkgRef = wkgRef.nextRef;
			}
			
			wkgRef.nextRef = new NodeClass(newVal);
		}
		
		else
		{
			headRef = new NodeClass(newVal);
		}
	}
	
	/**
	 * inserts item into iterator at beginning of iterator
	 * <p>
	 * Note: Does not affect current location
	 * 
	 * @param newVal integer value to be inserted
	 */
	public void insertAtFront(int newVal)
	{
		NodeClass wkgRef;
		
		if(isEmpty())
		{
			headRef = new NodeClass(newVal);
		}
		
		else
		{
			wkgRef = headRef;
			
			headRef = new NodeClass(newVal);
			
			headRef.nextRef = wkgRef;
		}
		
	}
	
	/**
	 * Tests for empty linked list
	 * 
	 * @return Boolean result of test for empty
	 */
	public boolean isEmpty()
	{
		return headRef == null;
	}
	
	/**
	 * moves current location to the right, if not at end
	 * 
	 * @return Boolean result of action
	 */
	public boolean moveNext()
	{
		if(hasNext())
		{
			currentRef = currentRef.nextRef;
			
			return true;
		}
		
		return false;
	}
	
	/**
	 * moves current location to the left, if not at beginning
	 * 
	 * @return Boolean result of action
	 */
	public boolean movePrev()
	{
		if(hasPrev())
		{
			currentRef = getCurrentPriorRef();
			
			return true;
		}
		
		return false;
	}
	
	/**
	 * removes item from iterator at current location
	 * <p>
	 * Note: Must reset current location if last item removed
	 * <p>
	 * Note: Must set current reference to null if last item removed
	 * 
	 * @return integer value if successful, NOT_FOUND if not
	 */
	public int removeAtCurrent()
	{
		int data;
		NodeClass wkgRef;
		
		if(!isEmpty())
		{
			data = currentRef.data;
			
			if(!hasPrev())
			{
				headRef = headRef.nextRef;
				
				return data;
			}
			
			wkgRef = getCurrentPriorRef();
			
			wkgRef.nextRef = currentRef.nextRef;
			
			return data;
		}
		
		return NOT_FOUND;
	}
	
	/**
	 * sets current location to beginning
	 * 
	 * @return Boolean true if list is not empty, false otherwise
	 */
	public boolean setToFirst()
	{
		if(!isEmpty())
		{
			currentRef = headRef;
			
			return true;
		}
		
		return false;
	}
	
	/**
	 * sets current index to end
	 * 
	 * @return Boolean true if list is not empty, false otherwise
	 */
	public boolean setToLast()
	{
		if(!isEmpty())
		{
			while(hasNext())
			{
				moveNext();
			}
			
			return true;
		}
		
		return false;
	}
}
