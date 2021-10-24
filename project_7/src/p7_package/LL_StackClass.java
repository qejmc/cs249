package p7_package;

/**
 * Class uses IteratorClass as data for Stack class
 * 
 * @author William Rogers
 *
 */
public class LL_StackClass 
{
	/**
	 * IteratorClass object used as data for class
	 */
	LL_IteratorClass stackData;
	
	/**
	 * Default constructor
	 */
	public LL_StackClass()
	{
		stackData = new LL_IteratorClass();
	}
	
	/**
	 * Copy constructor
	 * 
	 * @param copied LL_StackClass object to be copied
	 */
	public LL_StackClass(LL_StackClass copied)
	{
		stackData = new LL_IteratorClass(copied.stackData);
	}
	
	/**
	 * Clears all data
	 */
	public void clear()
	{
		stackData.clear();
	}
	
	/**
	 * checks for empty stack
	 * 
	 * @return Boolean result of test
	 */
	public boolean isEmpty()
	{
		return stackData.isEmpty();
	}
	
	/**
	 * Returns data from top of stack without removing
	 * 
	 * @return integer value at top of stack
	 */
	public int peekTop()
	{
		stackData.setToLast();
		
		return stackData.getValueAtCurrent();
	}
	
	/**
	 * Removes data from top of stack
	 * 
	 * @return integer value at top of stack
	 */
	public int pop()
	{
		int value;
		
		stackData.setToLast();
		
		value = stackData.getValueAtCurrent();
		
		stackData.removeAtCurrent();
		
		return value;
	}
	
	/**
	 * Places data at top of stack
	 * 
	 * @param value integer value to be pushed onto stack
	 */
	public void push(int value)
	{
		stackData.insertAtEnd(value);
	}
}
