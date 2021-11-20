package p11_package;

/**
 * Hash class that uses BST elements for data storage
 * 
 * @author William Rogers
 */
public class BST_HashClass {

    /**
     * Table size default
     */
    private static final int DEFAULT_TABLE_SIZE = 11;
    
    /**
     * Array for hash table
     */
    private BST_Class[] tableArray;
    
    /**
     * Capacity (Size) of the base table
     */
    private int tableSize;
    
    /**
     * Default constructor
     * <p>
     * Initializes array to default table size; initializes all BST elements
     * <p>
     * Note: One line of code
     */
    public BST_HashClass()
    {
        this(DEFAULT_TABLE_SIZE);
    }
    
    /**
     * Initialization constructor
     * <p>
     * Initializes array to specified table size; initializes all BST elements
     * 
     * @param inTableSize sets table size
     */
    public BST_HashClass(int inTableSize)
    {
        int index;
        
        tableSize = inTableSize;
        
        tableArray = new BST_Class[tableSize];
        
        for(index = 0; index < tableSize; index++)
        {
            tableArray[index] = new BST_Class();
        }
    }
    
    /**
     * Copy constructor
     * 
     * @param copied BST_HashClass object to be copied
     */
    public BST_HashClass(BST_HashClass copied)
    {
        int index;
        BST_Class[] copiedArray = new BST_Class[copied.tableSize];
        
        for(index = 0; index < tableSize; index++)
        {
            copiedArray[index] = new BST_Class(copied.tableArray[index]);
        }
        
        tableArray = copiedArray;
        tableSize = copied.tableSize;
    }
    
    /**
     * Adds item to hash table
     * <p>
     * Uses overloaded addItem with object
     * 
     * @param inName name of student
     * @param inStudentID student ID
     * @param inGender gender of student
     * @param inGPA gpa of student
     */
    public void addItem(String inName, int inStudentID, char inGender, double 
            inGPA)
    {
        StudentClassNode newItem = new StudentClassNode(inName, inStudentID, 
                inGender, inGPA);
        
        addItem(newItem);
    }
    
    /**
     * Adds item to hash table
     * <p>
     * Overloaded method that accepts StudentClassNode object
     * 
     * @param newItem student class object
     */
    public void addItem(StudentClassNode newItem)
    {
        int hash = generateHash(newItem);
        
        tableArray[hash].insert(newItem);
    }
    
    /**
     * Clears hash table by clearing all BST elements
     */
    public void clearHashTable()
    {
        int index;
        
        for(index = 0; index < tableSize; index++)
        {
            tableArray[index].clearTree();
        }
    }
    
    /**
     * Searches for item in hash table using student ID as key
     * 
     * @param studentID used for requesting data
     * 
     * @return StudentClassNode object removed, or null if not found
     */
    public StudentClassNode findItem(int studentID)
    {
        int hash;
        StudentClassNode searchData = new StudentClassNode();
        
        searchData.studentID = studentID;
        
        hash = generateHash(searchData);
        
        return tableArray[hash].search(searchData);
    }
    
    /**
     * Method uses student ID to generate hash value for use as index in hash 
     * table
     * <p>
     * Process sums the Unicode values of each of the student ID digits 
     * converted to characters, and then creates a hash index
     * 
     * @param studentData StudentClassNode object from which hash value will be 
     * generated
     * 
     * @return integer hash value to be used as array index
     */
    public int generateHash(StudentClassNode studentData)
    {
        int hash = 0, stdID = studentData.studentID;
        
        while(stdID > 0)
        {
            hash += (int) (stdID % 10 + '0');
            
            stdID /= 10;
        }
        
        return hash % tableSize;
    }
    
    /**
     * Removes item from hash table, using student ID as key
     * 
     * @param studentID used for requesting data
     * 
     * @return StudentClassNode object removed, or null if not found
     */
    public StudentClassNode removeItem(int studentID)
    {
        StudentClassNode removed, searchData;
        
        removed = findItem(studentID);
        
        if(removed != null)
        {
            searchData = new StudentClassNode();
            searchData.studentID = studentID;
            
            tableArray[generateHash(searchData)].removeNode(searchData);
            
        }
        
        return removed;
    }
    
    /**
     * Traverses through all array bins, finds counts from each BST, then 
     * displays as table
     * <p>
     * Shows table of list lengths, then shows table size, then shows the number
     * of empty bins and the longest linked list of the set; adapts to any size 
     * array
     */
    public void showHashTableStatus()
    {
        int index, numNodes, mostNodes = 0, numEmptyBins = 0;
        int leastNodes = tableArray[0].countNodes();
        
        System.out.println("\nArray node report:");
        
        System.out.print("\n           ");
        
        for(index = 0; index < tableSize; index++)
        {
            numNodes = tableArray[index].countNodes();
            System.out.format("  %2d ", numNodes);
            
            if(numNodes == 0)
            {
                numEmptyBins++;
            }
            
            else
            {
                if(numNodes > mostNodes)
                {
                    mostNodes = numNodes;
                }
                
                if(numNodes < leastNodes)
                {
                    leastNodes = numNodes;
                }
            }
            
            System.out.print("  ");
        }
        
        System.out.print("\n           ");
        
        for(index = 0; index < tableSize; index++)
        {
            System.out.print("-----");
            System.out.print("  ");
        }
        
        System.out.print("\n Index:    ");
        
        for(index = 0; index < tableSize; index++)
        {
            System.out.format("  %2d ", index);
            System.out.print("  ");
        }
        
        System.out.format("\n\nWith a table size of %d,", tableSize);
        System.out.format("\nThe number of empty bins was %d.", numEmptyBins);
        System.out.format("\nThe largest BST count was %d node(s).", mostNodes);
        System.out.format("\nThe smallest BST count was %d node(s).\n\n", 
                leastNodes);
    }
}
