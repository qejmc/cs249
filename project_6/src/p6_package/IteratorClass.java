package p6_package;

/**
 * Description: Simple iterator class that manages integers
 * <p>
 * Note: Automatically resizes as needed
 * 
 * @author William Rogers
 *
 */
public class IteratorClass 
{
	/**
	 * index of current index of iterator
	 */
	private int currentIndex;
	
	/**
	 * constant for default size of iterator
	 */
	private static int DEFAULT_CAPACITY = 10;
	
	/**
	 * array holding items
	 */
	private int[] iteratorArray;
	
	/**
	 * constant for default size of iterator
	 */
	private int iteratorCapacity;
	
	/**
	 * iterator size and capacity
	 */
	private int iteratorSize;
	
	/**
	 * constant use for left bracket
	 */
	private static char LEFT_BRACKET = '[';
	
	/**
	 * constant use for not found
	 */
	private static int NOT_FOUND = -1;
	
	/**
	 * constant use for right bracket
	 */
	private static char RIGHT_BRACKET = ']';
	
	/**
	 * constant use for right bracket
	 */
	private static char SPACE = ' ';
	
	/**
	 * Default constructor, initializes iterator to default capacity (10)
	 */
	public IteratorClass()
	{
		this(DEFAULT_CAPACITY);
	}
	
	/**
	 * Initializing constructor, initializes iterator to specified capacity
	 * 
	 * @param capacity initial capacity specification for the iterator
	 */
	public IteratorClass(int capacity)
	{
		iteratorCapacity = capacity;
		iteratorSize = 0;
		currentIndex = 0;
		
		iteratorArray = new int[capacity];
	}
	
	/**
	 * Clears iterator of all valid values by setting array size and current 
	 * index to zero, values remain in array but are not accessible
	 */
	public void clear()
	{
		iteratorSize = 0;
		currentIndex = 0;
	}
	
	/**
	 * Description: shows iterator from beginning to end with brackets around 
	 * current index
	 * <p>
	 * Displays "Empty" if iterator is empty
	 */
	public void displayIterator()
	{
		int index;
		
		System.out.print("Iterator: ");
		
		for(index = 0; index < iteratorSize; index++)
		{
			if(index == currentIndex)
				System.out.format("%s%d%s", LEFT_BRACKET, iteratorArray[index], 
						RIGHT_BRACKET);
			else
				System.out.print(iteratorArray[index]);
			
			System.out.print(SPACE);
		}
		
		if(iteratorSize == 0)
			System.out.print("Empty");
		
		System.out.print("\n");
	}
	
	/**
	 * returns value at current index
	 * 
	 * @return integer value at current index
	 */
	public int getValueAtCurrent()
	{
		return iteratorArray[currentIndex];
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
		return currentIndex < iteratorSize - 1;
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
		return currentIndex != 0;
	}
	
	/**
	 * inserts item into iterator at current index
	 * <p>
	 * Note: Must check for resize after operation
	 * 
	 * @param newVal integer value to be inserted
	 */
	public void insertAtCurrent(int newVal)
	{
		int index;
		
		for(index = iteratorSize; index > currentIndex; index--)
		{
			iteratorArray[index] = iteratorArray[index - 1];
		}
		
		iteratorArray[currentIndex] = newVal;
		
		iteratorSize++;
		
		if(iteratorSize >= iteratorCapacity)
			resize();
		
	}
	
	/**
	 * inserts item into iterator at end of iterator
	 * <p>
	 * Note: Does not affect current index
	 * <p>
	 * Note: Must check for resize after operation
	 * 
	 * @param newVal integer value to be inserted
	 */
	public void insertAtEnd(int newVal)
	{
		if(hasNext())
			iteratorArray[iteratorSize] = newVal;
		
		iteratorSize++;
		
		if(iteratorSize >= iteratorCapacity)
			resize();
	}
	
	/**
	 * inserts item into iterator at beginning of iterator
	 * <p>
	 * Note: Does not affect current index
	 * <p>
	 * Note: Must check for resize after operation
	 * 
	 * @param newVal integer value to be inserted
	 */
	public void insertAtFront(int newVal)
	{
		int index;
		
		for(index = iteratorSize; index > 0; index--)
		{
			iteratorArray[index] = iteratorArray[index - 1];
		}
		
		iteratorArray[0] = newVal;
		
		iteratorSize++;
		
		if(iteratorSize >= iteratorCapacity)
			resize();
	}
	
	/**
	 * Tests for size of array equal to zero, no valid values stored in array
	 * 
	 * @return Boolean result of test for empty
	 */
	public boolean isEmpty()
	{
		return iteratorSize == 0;
	}
	
	/**
	 * moves current index to the right, if not at end
	 * 
	 * @return Boolean result of action
	 */
	public boolean moveNext()
	{
		if(hasNext())
		{
			currentIndex++;
			
			return true;
		}
		
		return false;
	}
	
	/**
	 * moves current index to the left, if not at beginning
	 * 
	 * @return Boolean result of action
	 */
	public boolean movePrev()
	{
		if(hasPrev())
		{
			currentIndex--;
			
			return true;
		}
		
		return false;
	}
	
	/**
	 * removes item from iterator at current index
	 * <p>
	 * Must resolve condition if last item removed
	 * 
	 * @return integer value if successful, NOT_FOUND if not
	 */
	public int removeAtCurrent()
	{
		int index;
		
		if(currentIndex >= iteratorSize)
			return NOT_FOUND;
		
		for(index = currentIndex; index < iteratorSize; index++)
		{
			iteratorArray[index] = iteratorArray[index + 1];
		}
		
		iteratorSize--;
		
		if(!hasNext())
			currentIndex--;
		
		return iteratorArray[currentIndex];
	}
	
	/**
	 * Description: Resets array capacity to twice the current capacity only as 
	 * needed
	 */
	public void resize()
	{
		int index;
		int[] tempArray;
		
		iteratorCapacity *= 2;
		
		tempArray = new int[iteratorCapacity];
		
		for(index = 0; index < iteratorSize; index++)
		{
			tempArray[index] = iteratorArray[index];
		}
		
		iteratorArray = tempArray;
	}
	
	/**
	 * sets current index to beginning
	 * 
	 * @return Boolean result of action
	 */
	public boolean setToFirst()
	{
		currentIndex = 0;
		
		return true;
	}
	
	/**
	 * sets current index to end
	 * 
	 * @return Boolean result of action
	 */
	public boolean setToLast()
	{
		currentIndex = iteratorSize - 1;
		
		return true;
	}
	
}
