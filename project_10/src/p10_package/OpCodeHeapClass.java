package p10_package;

/**
 * Array-based OpCodeClass min heap class used as a priority queue for 
 * OpCodeClass data
 * 
 * @author William Rogers
 */
public class OpCodeHeapClass {

    /**
     * Management data for array
     */
    private int arrayCapacity;
    
    /**
     * Management data for array
     */
    private int arraySize;
    
    /**
     * Initial array capacity
     */
    public final int DEFAULT_ARRAY_CAPACITY = 10;
    
    /**
     * Display flag can be set to observe bubble up and trickle down operations
     */
    private boolean displayFlag;
    
    /**
     * Array for heap
     */
    private OpCodeClass[] heapArray;
    
    /**
     * Default constructor sets up array management conditions and default 
     * display flag setting
     */
    public OpCodeHeapClass()
    {
        arrayCapacity = DEFAULT_ARRAY_CAPACITY;
        arraySize = 0;
        displayFlag = false;
        heapArray = new OpCodeClass[arrayCapacity];
    }
    
    /**
     * Copy constructor copies array and array management conditions and default
     * display flag setting
     * 
     * @param copied
     */
    public OpCodeHeapClass(OpCodeHeapClass copied)
    {
        int index;
        
        arrayCapacity = copied.arrayCapacity;
        arraySize = copied.arraySize;
        displayFlag = copied.displayFlag;
        heapArray = new OpCodeClass[arrayCapacity];
        
        for(index = 0; index < arraySize; index++)
        {
            heapArray[index] = new OpCodeClass(copied.heapArray[index]);
        }
    }
    
    /**
     * Accepts OpCodeData item and adds it to heap
     * <p>
     * Note: uses bubbleUpArrayHeap to resolve unbalanced heap after data 
     * addition
     * <p>
     * Note: must check for resize before attempting to add an item
     * 
     * @param newItem OpCodeClass data item to be added
     */
    public void addItem(OpCodeClass newItem)
    {
        checkForResize();
        
        heapArray[arraySize] = newItem;
        
        bubbleUpArrayHeap(arraySize);
        
        arraySize++;
    }
    
    /**
     * Recursive operation to reset data in the correct order for the min heap 
     * after new data addition
     * 
     * @param currentIndex index of current item being assessed, and moved up as
     * needed
     */
    private void bubbleUpArrayHeap(int currentIndex)
    {
        OpCodeClass temp;
        int parentIndex;
        
        if(currentIndex > 0)
        {
            parentIndex = (currentIndex - 1 )/ 2;
            
            //currentIndex val < parentIndex val
            if(heapArray[currentIndex].compareTo(heapArray[parentIndex]) < 0)
            {
                temp = heapArray[parentIndex];
                heapArray[parentIndex] = heapArray[currentIndex];
                heapArray[currentIndex] = temp;
                
                bubbleUpArrayHeap(parentIndex);
            }
        }
    }
    
    /**
     * Automatic resize operation used prior to any new data addition in the 
     * heap
     * <p>
     * Tests for full heap array, and resizes to twice the current capacity as 
     * required
     */
    private void checkForResize()
    {
        OpCodeClass[] resizedArray;
        int index;
        
        if(arraySize >= arrayCapacity)
        {
            arrayCapacity *= 2;
            
            resizedArray = new OpCodeClass[arrayCapacity];
            
            for(index = 0; index < arraySize; index++)
            {
                resizedArray[index] = heapArray[index];
            }
            
            heapArray = resizedArray;
        }
    }
    
    /**
     * Tests for empty heap
     * 
     * @return boolean result of test
     */
    public boolean isEmpty()
    {
        return arraySize == 0;
    }
    
    /**
     * Removes OpCodeClass data item from top of min heap, thus being the 
     * operation with the lowest priority value
     * <p>
     * Note: Uses trickleDownArrayHeap to resolve unbalanced heap after data 
     * removal
     * 
     * @return OpCodeClass item removed
     */
    public OpCodeClass removeItem()
    {
        OpCodeClass removed = null;
        
        if(!isEmpty())
        {
            removed = heapArray[0];
            heapArray[0] = heapArray[arraySize - 1];
            
            trickleDownArrayHeap(0);
        }
        
        arraySize--;
        
        return removed;
    }
    
    /**
     * Utility method to set the display flag for displaying internal operations
     * of the heap bubble and trickle operations
     * 
     * @param setState flag used to set the state to display, or not
     */
    public void setDisplayFlag(boolean setState)
    {
        displayFlag = setState;
    }
    
    /**
     * Dumps array to screen as is, no filtering or management
     */
    public void showArray()
    {
        int index;
        
        for(index = 0; index < arraySize; index++)
        {
            System.out.print(heapArray[index].toString());
        }
    }
    
    /**
     * Recursive operation to reset data in the correct order for the min heap 
     * after data removal
     * 
     * @param currentIndex index of current item being assessed, and moved down 
     * as required
     */
    private void trickleDownArrayHeap(int currentIndex)
    {
        OpCodeClass temp;
        int leftChildIndex, rightChildIndex, leftDelta, rightDelta;
        
        leftChildIndex = currentIndex * 2 + 1;
        rightChildIndex = currentIndex * 2 + 1;
        
        if(leftChildIndex < arraySize && rightChildIndex < arraySize)
        {
            leftDelta = heapArray[leftChildIndex].compareTo(
                    heapArray[currentIndex]);
            rightDelta = heapArray[rightChildIndex].compareTo(
                    heapArray[currentIndex]);
            
            if(leftDelta < rightDelta && leftDelta < 0)
            {
                temp = heapArray[currentIndex];
                heapArray[currentIndex] = heapArray[leftChildIndex];
                heapArray[leftChildIndex] = temp;
                trickleDownArrayHeap(leftChildIndex);
            }
            
            else if(rightDelta < 0)
            {
                temp = heapArray[currentIndex];
                heapArray[currentIndex] = heapArray[rightChildIndex];
                heapArray[rightChildIndex] = temp;
                trickleDownArrayHeap(rightChildIndex);
            }
        }
        
        else if(leftChildIndex < arraySize)
        {
            if(heapArray[leftChildIndex].compareTo(heapArray[currentIndex]) < 0)
            {
                temp = heapArray[currentIndex];
                heapArray[currentIndex] = heapArray[leftChildIndex];
                heapArray[leftChildIndex] = temp;
                trickleDownArrayHeap(leftChildIndex);
            }
        }
    }
}
