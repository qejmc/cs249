package p2_package;

/**
 * 
 * @author William Rogers
 *
 */

public class RegistrarClass
{
	
	/**
	 * default capacity
	 */
	private final int DEFAULT_CAPACITY = 10;
	
	/**
	 * Constant used if item not found in array
	 */
	private final int NOT_FOUND = -1;
	
	/**
	 * Private capacity and size data
	 */
	private int capacity;
	
	/**
	 * Private capacity and size data
	 */
	private int size;
	
	/**
	 * Private array holding student data
	 */
	private StudentClass[] studentArr;
	
	/**
	 * Default constructor
	 */
	public RegistrarClass() 
	{
		capacity = DEFAULT_CAPACITY;
		studentArr = new StudentClass[capacity];
		size = capacity;
	}
	
	/**
	 * Initialization constructor
	 * 
	 * @param initialCapacity - integer value to set class initial capacity
	 */
	public RegistrarClass(int initialCapacity)
	{
		capacity = initialCapacity;
		studentArr = new StudentClass[capacity];
		size = capacity;
	}
	
	/**
	 * Copy constructor
	 * 
	 * @param copied - RegistrarClass object to be copied
	 */
	public RegistrarClass(RegistrarClass copied)
	{
		int index;
		
		capacity = copied.capacity;
		size = copied.size;
		
		for(index = 0; index < size; index++)
		{
			studentArr[index] = copied.studentArr[index];
		}
	}
	
	/**
	 * Adds a StudentClass item to list
	 * <p>
	 * Note: Overloaded method
	 * 
	 * @param newStudent - StudentClass object to be added to array
	 * 
	 * @return Boolean result of item addition to array
	 */
	public boolean addStudent(StudentClass newStudent)
	{
		int index = 0;
		
		if(newStudent == null)
		{
			return false;
		}
		
		while(studentArr[index] != null)
		{
			index++;
		}
		
		studentArr[index] = newStudent;
		
		size++;
		
		//TODO
		return true;
	}
	
	/**
	 * Creates a StudentClass item, then adds to list using other method
	 * <p>
	 * Note: Uses anonymous StudentClass instantiation in call to other method; 
	 * one line of code
	 * <p>
	 * Note: Overloaded method
	 * 
	 * @param stdName - String name of student
	 * 
	 * @param stdID - integer student ID of student
	 * 
	 * @param stdGender - character gender of student
	 * 
	 * @param stdGPA - double GPA of student
	 * 
	 * @return Boolean result of adding student
	 */
	public boolean addStudent(String stdName, int stdID, char stdGender, 
			double stdGPA)
	{
		StudentClass newStudent = new StudentClass(stdName, stdID, stdGender, 
				stdGPA );
		
		if(addStudent(newStudent))
			return true;
		
		return false;
		
	}
	
	/**
	 * Copies student list from one array to other
	 * <p>
	 * Note: Must create new StudentClass object to assign to each element to destination array to eliminate aliasing
	 * 
	 * @param dest - StudentClass array to which data is copied
	 * 
	 * @param source - StudentClass array from which data is copied
	 */
	private void copyArrayData(StudentClass[] dest, StudentClass[] source)
	{
		int index = 0;
		
		while(source[index] != null)
		{
			dest[index] = new StudentClass(source[index].name, 
					source[index].studentID, source[index].gender, 
					source[index].gpa);
			index++;
		}
		
		
	}
	
	/**
	 * Optional method, local array dump for diagnostics
	 */
	public void diagnosticArrayDump()
	{
		
	}
	
	/**
	 * Finds student in array, returns data
	 * <p>
	 * Note: Uses findStudentIndex
	 * 
	 * @param student - StudentClass object to be found
	 * 
	 * @return StudentClass object found, or null if not found
	 */
	public StudentClass findStudent(StudentClass student)
	{
		int index;
		
		index = findStudentIndex(student);
		
		if(index == NOT_FOUND)
		{
			return null;
		}
		
		return studentArr[index];
	}
	
	/**
	 * Finds student's index in array, returns index, or returns NOT_FOUND if 
	 * item is not in the array
	 * <p>
	 * Note: Must use appropriate comparison method for class
	 * 
	 * @param student - StudentClass object to be found
	 * 
	 * @return index of StudentClass object, or NOT_FOUND
	 */
	public int findStudentIndex(StudentClass student)
	{
		int index;
		
		for(index = 0; index < size; index++)
		{
			if(studentArr[index] == student)
			{
				return index;
			}
		}
		
		return NOT_FOUND;
	}
	
	/**
	 * Removes student from array, shifts elements down to keep array contiguous
	 * <p>
	 * Note: Uses findStudentIndex
	 * 
	 * @param student - StudentClass object to be removed
	 * 
	 * @return StudentClass object that was removed, or null if not found
	 */
	public StudentClass removeStudent(StudentClass student)
	{
		int index;
		
		index = findStudentIndex(student);
		
		if(index == NOT_FOUND)
		{
			return null;
		}
		
		studentArr[index] = null;
		
		while(studentArr[index + 1] != null)
		{
			studentArr[index] = studentArr[index + 1];
			index++;
		}
		
		studentArr[index] = null;
		
		size--;
		
		return student;
		
	}
	
	/**
	 * Description: Sorts elements using the bubble sort algorithm
	 * <p>
	 * Note: Creates new StudentClass array, sorts contents of array, and 
	 * returns the sorted result; does not modify (this) object student array
	 * 
	 * @return new StudentClass array with sorted items
	 */
	public StudentClass[] runBubbleSort()
	{
		int listIndex = 0, swappingIndex = 0;
		
		StudentClass[] stdArr = new StudentClass[capacity];
		
		copyArrayData( stdArr, studentArr );
		
		for(listIndex = 0; listIndex < size; listIndex++)
		{
			swappingIndex = 0;
			
			while(stdArr[swappingIndex + 1] != null)
			{
				
				if(stdArr[swappingIndex].compareTo(stdArr[swappingIndex + 1]) 
						> 0)
				{
					swapValues(stdArr, swappingIndex, swappingIndex + 1);
				}
				
				swappingIndex++;
			}
		}
		
		return stdArr;
		
		
	}
	
	/**
	 * Description: Sorts character elements using the insertion sort algorithm
	 * <p>
	 * Note: Creates new StudentClass array, sorts contents of array, and 
	 * returns the sorted result; does not modify (this) object student array
	 * 
	 * @return new StudentClass array with sorted items
	 */
	public StudentClass[] runInsertionSort()
	{
		int searchIndex, listIndex = 1;
		StudentClass tempStd;
		
		StudentClass[] stdArr = new StudentClass[capacity];
		
		copyArrayData( stdArr, studentArr );
		
		while(stdArr[listIndex] != null)
		{
			tempStd = stdArr[listIndex];
			
			searchIndex = listIndex;
			
			while(searchIndex > 0 && (tempStd.compareTo(stdArr[searchIndex - 1])
					< 0))
			{
				stdArr[searchIndex] = stdArr[searchIndex - 1];
				
				searchIndex--;
			}
			
			stdArr[searchIndex] = tempStd;
			
			listIndex++;
			
		}
		
		return stdArr;
		
	}
	
	/**
	 * Description: Sorts character elements using the selection sort algorithm
	 * <p>
	 * Creates new StudentClass array, sorts contents of array, and returns the 
	 * sorted result; does not modify (this) object student array
	 * 
	 * @return new StudentClass array with sorted items
	 */
	public StudentClass[] runSelectionSort()
	{
		int listIndex = 0, lowestIndex, currentSearchIndex;
		
		StudentClass[] stdArr = new StudentClass[capacity];
		
		copyArrayData( stdArr, studentArr );
		
		while(stdArr[listIndex] != null)
		{
			lowestIndex = listIndex;
			
			currentSearchIndex = listIndex + 1;
			
			while(stdArr[currentSearchIndex] != null)
			{
				if(stdArr[currentSearchIndex].compareTo(
						stdArr[lowestIndex]) < 0)
				{
					lowestIndex = currentSearchIndex;
				}
				
				currentSearchIndex++;
			}
			
			swapValues(stdArr, listIndex, lowestIndex);
			
			listIndex++;
			
		}
		
		return stdArr;
	}
	
    /**
     * Uses Shell's sorting algorithm to sort an array of integers
     * <p>
     * Shell's sorting algorithm is an optimized insertion algorithm
     * 
     * <p>
     * Note: Creates new StudentClass array, sorts contents of array, 
     * and returns the sorted result; 
     * does not modify (this) object student array
     * 
     * @return new StudentClass array with sorted items
     */
    public StudentClass[] runShellSort()
    {
    	int gap, gapPassIndex, insertionIndex;
        StudentClass tempItem;
        
        StudentClass[] stdArr = new StudentClass[ capacity ];
        
        copyArrayData( stdArr, studentArr );

        for( gap = size / 2; gap > 0; gap /= 2 )
        {
        	gapPassIndex = gap;
        	
            while(stdArr[gapPassIndex] != null)
               {
                tempItem = stdArr[ gapPassIndex ];

                insertionIndex = gapPassIndex;

                while( insertionIndex >= gap 
                && stdArr[ insertionIndex - gap].compareTo( tempItem ) > 0 ) 
                   {
                    stdArr[ insertionIndex ] 
                                           = stdArr[ insertionIndex - gap ];
                   
                    insertionIndex -= gap;
                   }  // end search loop

                stdArr[ insertionIndex ] = tempItem;
                
                gapPassIndex++;
               }  // end list loop
            
            
           
        }  // end gap size setting loop   
       
        return stdArr;
    }
    
    /**
     * Swaps values within given array
     * 
     * @param stdArray - StudentClass array used for swapping
     * 
     * @param indexOne - integer index for one of the two items to be swapped
     * 
     * @param indexOther - integer index for the other of the two items to be 
     * swapped
     */
    public void swapValues(StudentClass[] stdArray, int indexOne, 
    		int indexOther)
    {
    	StudentClass studentOne = stdArray[indexOne];
    	
    	stdArray[indexOne] = stdArray[indexOther];
    	stdArray[indexOther] = studentOne;
    	
    }
	
	
}