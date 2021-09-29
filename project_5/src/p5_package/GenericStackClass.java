package p5_package;

/**
 * Description: Class wrapper for a Java stack of generic data (classes)
 * <p>
 * Note: Only works with class that extends Comparable, as shown in class 
 * declaration
 * <p>
 * Note: Maintains a capacity value for maximum number of items that can be 
 * stored, and a size value for the number of valid or viable data items in the 
 * stack
 * 
 * @author William Rogers
 *
 * @param <GenericData> Generic object class
 */
public class GenericStackClass<GenericData extends Comparable<GenericData>> 
{

	/**
	 * constant for default size of stack
	 */
	private static int DEFAULT_CAPACITY = 10;
	
	/**
	 * constant use for space
	 */
	private static char SPACE = ' ';
	
	/**
	 * array holding stack items
	 */
	private Object[] stackArray;
	
	/**
	 * stack size and capacity
	 */
	private int stackCapacity;
	
	/**
	 * stack size and capacity
	 */
	private int stackSize;
	
	/**
	 * Default constructor, initializes stack to default capacity (10)
	 */
	public GenericStackClass()
	{
		this(DEFAULT_CAPACITY);
	}
	
	/**
	 * Initializing constructor, initializes stack to specified capacity
	 * 
	 * @param capacity maximum capacity specification for the stack
	 */
	public GenericStackClass(int capacity)
	{
		stackCapacity = capacity;
		
		stackArray = new Object[stackCapacity];
		
		stackSize = 0;
	}
	
	/**
	 * Clears stack of all valid values by setting array size to zero, values 
	 * remain in array but are not accessible
	 */
	public void clear()
	{
		stackSize = 0;
	}
	
	/**
	 * Description: shows stack from top to bottom
	 * <p>
	 * No display if stack is empty
	 */
	public void displayStack()
	{
		int index, numSpaces = 11;
		
		if(!isEmpty())
		{
			for(index = stackSize - 1; index >= 0; index--)
			{
				if(index == stackSize - 1)
					System.out.print("Stack Top: ");
				
				else
					printChars(numSpaces, SPACE);

				if(index == 0)
					System.out.print("Stack Bottom: ");

				System.out.print(stackArray[index].toString());
				
				System.out.println();
				
				numSpaces += 2;
			}
			
		}
	}
	
	/**
	 * Tests for size of stack equal to zero, no valid values stored in array
	 * 
	 * @return Boolean result of test for empty
	 */
	public boolean isEmpty()
	{
		if(stackSize == 0)
			return true;
		
		return false;
	}
	
	/**
	 * returns value at top of stack without popping
	 * 
	 * @return GenericData front value if successful, null if not
	 */
	@SuppressWarnings("unchecked")
	public GenericData peekTop()
	{
		if(!isEmpty())
			return (GenericData) stackArray[stackSize - 1];
		
		return null;
	}
	
	/**
	 * pops item from stack
	 * 
	 * @return GenericData popped value if successful, null if not
	 */
	@SuppressWarnings("unchecked")
	public GenericData pop()
	{
		if(!isEmpty())
		{
			stackSize--;
			
			return (GenericData) stackArray[stackSize];
		}
		
		return null;
	}
	
	/**
	 * Description: prints specified number of characters recursively
	 * 
	 * @param numChars integer value specifying number of characters
	 * @param outChar character to be output
	 */
	public void printChars(int numChars, char outChar)
	{
		if(numChars > 0)
		{
			System.out.print(outChar);
			
			printChars(numChars - 1, outChar);
		}
	}
	
	/**
	 * Description: pushes item onto array
	 * <p>
	 * Checks for full stack and resizes as needed prior to data push
	 * 
	 * @param newValue GenericData value to be pushed onto stack
	 */
	public void push(GenericData newValue)
	{
		if(stackSize >= stackCapacity)
			resize();
		
		stackArray[stackSize] = newValue;
		
		stackSize++;
	}
	
	/**
	 * Description: Resets array capacity to twice the current capacity
	 */
	public void resize()
	{
		int index;
		
		stackCapacity *= 2;
		
		Object[] tempArray = new Object[stackCapacity];
		
		for(index = 0; index < stackSize; index++)
		{
			tempArray[index] = stackArray[index];
		}
		
		stackArray = tempArray;
	}
	
}
