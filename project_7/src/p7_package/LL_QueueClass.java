package p7_package;

/**
 * Class that is inherited from IteratorClass to implement a queue data 
 * structure
 * 
 * @author William Rogers
 *
 */
public class LL_QueueClass extends LL_IteratorClass
{
	/**
	 * Default constructor
	 */
	public LL_QueueClass()
	{
		super();
	}
	
	/**
	 * Copy constructor
	 * 
	 * @param copied LL_QueueClass object to be copied
	 */
	public LL_QueueClass(LL_QueueClass copied)
	{
		super(copied);
	}
	
	/**
	 * Clears queue
	 */
	public void clear()
	{
		super.clear();
	}
	
	/**
	 * Dequeues data, returns
	 * 
	 * @return integer value from queue
	 */
	public int dequeue()
	{
		setToFirst();
		
		return removeAtCurrent();
	}
	
	/**
	 * Enqueues data
	 * 
	 * @param value integer value to enqueued
	 */
	public void enqueue(int value)
	{
		insertAtCurrent(value);
	}
	
	/**
	 * Checks for empty
	 * 
	 * @return Boolean result of test
	 */
	public boolean isEmpty()
	{
		return super.isEmpty();
	}
	
	/**
	 * Views value at front of queue
	 * 
	 * @return integer value at front of queue
	 */
	public int peekFront()
	{
		setToFirst();
		
		return getValueAtCurrent();
	}
}
