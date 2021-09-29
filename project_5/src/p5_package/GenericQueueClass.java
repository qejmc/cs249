package p5_package;

/**
 * Java Queue class of generic data (classes)
 * <p>
 * Note: Only works with class that extends Comparable, as shown in class 
 * declaration
 * <p>
 * Note: Queue automatically resizes as needed
 * <p>
 * Note: Queue is maintained within array with front and rear indices rotated as 
 * needed to manage queue; array capacity is only increased when the queue array 
 * is full
 *
 * @author William Rogers
 * 
 * @param <GenericData> Generic object class
 */
public class GenericQueueClass<GenericData extends Comparable<GenericData>> 
{

	/**
	 * constant for default size of queue
	 */
	private static int DEFAULT_CAPACITY = 10;
	
	/**
	 * index of front and rear of queue
	 */
	private int frontIndex;
	
	/**
	 * array holding queued items
	 */
	private Object[] queueArray;
	
	/**
	 * queue size and capacity
	 */
	private int queueCapacity;
	
	/**
	 * queue size and capacity
	 */
	private int queueSize;
	
	/**
	 * index of front and rear of queue
	 */
	private int rearIndex;
	
	/**
	 * constant use for space
	 */
	private static char SPACE = ' ';
	
	/**
	 * Default constructor, initializes queue to default capacity (10)
	 */
	public GenericQueueClass()
	{
		this(DEFAULT_CAPACITY);
	}
	
	/**
	 * Initializing constructor, initializes queue to specified capacity
	 * 
	 * @param capacity maximum capacity specification for the queue
	 */
	public GenericQueueClass(int capacity)
	{
		queueCapacity = capacity;
		
		queueArray = new Object[queueCapacity];
		
		queueSize = 0;
		frontIndex = 0;
		rearIndex = 0;
	}
	
	/**
	 * Clears queue of all valid values by setting array size to zero, values 
	 * remain in array but are not accessible
	 */
	public void clear()
	{
		queueSize = 0;
		frontIndex = 0;
		rearIndex = 0;
	}
	
	/**
	 * dequeues item from queue
	 * 
	 * @return dequeued value if successful, null if not
	 */
	@SuppressWarnings("unchecked")
	public GenericData dequeue()
	{
		if(!isEmpty())
		{
			frontIndex = (frontIndex - 1) % queueCapacity;
			queueSize--;

			return (GenericData) queueArray[frontIndex];
		}
		
		return null;
			
	}
	
	/**
	 * Description: shows stack from front to rear
	 * <p>
	 * No display if stack is empty
	 */
	public void displayQueue()
	{
		int index, numSpaces = 13;
		
		if(!isEmpty())
		{
			for(index = queueSize - 1; index >= 0; index--)
			{
				if(index == queueSize - 1)
					System.out.print("Queue Front: ");
				
				else
					printChars(numSpaces, SPACE);

				if(index == 0)
					System.out.print("Queue Rear: ");

				System.out.print(queueArray[index].toString());
				
				System.out.println();
				
				numSpaces += 2;
			}		
		}
	}
	
	/**
	 * Description: Enqueues item into array
	 * <p>
	 * Checks for full queue and resizes as needed prior to data enqueue
	 * 
	 * @param newValue GenericData value to be inserted into queue
	 */
	public void enqueue(GenericData newValue)
	{
		int index;

		if(queueSize >= queueCapacity)
			resize();
		
		for(index = frontIndex; index > 0; index--)
		{
			queueArray[index] = queueArray[index - 1];
		}
		
		queueArray[rearIndex] = newValue;
		
		frontIndex++;
		
		queueSize++;
		
	}
	
	/**
	 * Tests for size of queue equal to zero, no valid values stored in array
	 * 
	 * @return Boolean result of test for empty
	 */
	public boolean isEmpty()
	{
		if(queueSize == 0)
			return true;
		
		return false;
	}
	
	/**
	 * returns value at front of queue without dequeuing
	 * 
	 * @return GenericData front value if successful, null if not
	 */
	@SuppressWarnings("unchecked")
	public GenericData peekFront()
	{
		if(!isEmpty())
			return (GenericData) queueArray[queueSize - 1];
		
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
	 * Description: Resets array capacity to twice the current capacity
	 */
	public void resize()
	{
		int index;
		
		queueCapacity *= 2;
		
		Object[] tempArray = new Object[queueCapacity];
		
		for(index = 0; index < queueSize; index++)
		{
			tempArray[index] = queueArray[index];
		}
		
		queueArray = tempArray;
	}
	
}
