package p9_package;

/**
 * 2-3 Tree class that stores name strings
 * 
 * @author William Rogers
 *
 */
public class TwoThreeTreeClass {

	/**
	 * constant used for identifying one data item stored
	 */
	private final int ONE_DATA_ITEM = 1;
	
	/**
	 * Used for acquiring ordered tree visitations in String form
	 */
	private String outputString;
	
	/**
	 * root of tree
	 */
	private TwoThreeNodeClass root;
	
	/**
	 * constant used for identifying three data items stored
	 */
	private final int THREE_DATA_ITEMS = 3;
	
	/**
	 * constant used for identifying two data items stored
	 */
	private final int TWO_DATA_ITEMS = 2;
	
	/**
	 * Default 2-3 tree constructor
	 */
	public TwoThreeTreeClass()
	{
		root = null;
		outputString = "";
	}
	
	/**
	 * Copy 2-3 tree constructor
	 * 
	 * @param copied TwoThreeTreeClass object to be duplicated; data is copied, 
	 * but new nodes and references must be created
	 */
	public TwoThreeTreeClass(TwoThreeTreeClass copied)
	{
		outputString = "";
		
		copyConstructorHelper(copied.root);
	}
	
	/**
	 * Implements tree duplication effort with recursive method; copies data 
	 * into newly created nodes and creates all new references to child nodes
	 * 
	 * @param workingCopiedRef TwoThreeNodeClass reference that is updated to 
	 * lower level children with each recursive call
	 * 
	 * @return TwoThreeNodeClass reference to next higher level node; last 
	 * return is to root node of THIS object
	 */
	private TwoThreeNodeClass copyConstructorHelper(TwoThreeNodeClass 
			workingCopiedRef)
	{
		TwoThreeNodeClass localRef;
		
		//check for copied ref != null
		if(workingCopiedRef != null)
		{
			// create new node with copied ref, which becomes this(local ref)
			localRef = new TwoThreeNodeClass(workingCopiedRef);
			
			// set left child to recursive call to left
			localRef.rightChildRef = 
					copyConstructorHelper(workingCopiedRef.leftChildRef);
			
			//check for left child exists
			if(workingCopiedRef.leftChildRef != null)
			{
				// set the left child's parent to this
				localRef = workingCopiedRef.leftChildRef.parentRef;
			}
			
			// check for 2 node
			if(workingCopiedRef.numItems == TWO_DATA_ITEMS)
			{
				// set local num items to 2
				localRef.numItems = TWO_DATA_ITEMS;
				
				// set left and right data values to copied left/right
				localRef.leftData = workingCopiedRef.leftData;
				localRef.rightData = workingCopiedRef.rightData;
				
				// set center child to recursive call to center
				localRef.centerChildRef = 
						copyConstructorHelper(workingCopiedRef.centerChildRef);
				
				// check for center child exists
				if(localRef.centerChildRef != null)
				{
					// set center child's parent to this
					localRef = workingCopiedRef.centerChildRef.parentRef;
				}
					
			}
			
			// set right child to recursive call to right
			localRef.rightChildRef = 
					copyConstructorHelper(workingCopiedRef.rightChildRef);
			
			// check for right child exists
			if(workingCopiedRef.rightChildRef != null)
			{
				// set the right child's parent to this
				localRef = workingCopiedRef.rightChildRef.parentRef;
			}
				
			// return local ref
			return localRef;
		}
		
		return null;
	}
	
	/**
	 * Method is called when addItemHelper arrives at the bottom of the 2-3 
	 * search tree (i.e., all node's children are null);
	 * <p>
	 * Assumes one- or two- value nodes and adds one more to the appropriate one
	 * resulting in a two- or three- value node
	 * 
	 * @param localRef TwoThreeNodeClass reference has original node data and 
	 * contains added value when method completes; method does not manage any 
	 * node links
	 * @param itemStr String value to be added to 2-3 node
	 */
	private void addAndOrganizeData(TwoThreeNodeClass localRef, String itemStr)
	{
		//If one node
		if(localRef.numItems == ONE_DATA_ITEM)
		{
			//If < center
			if(compareStrings(itemStr, localRef.centerData) < 0)
			{
				localRef.leftData = itemStr;
				localRef.rightData = localRef.centerData;
			}
			
			//If > center
			else
			{
				localRef.rightData = itemStr;
				localRef.leftData = localRef.centerData;
			}
			
			localRef.centerData = "";
			localRef.numItems = TWO_DATA_ITEMS;
		}
		
		//If two nodes
		else
		{
			//If < left, shift up
			if(compareStrings(itemStr, localRef.leftData) < 0)
			{
				localRef.centerData = localRef.leftData;
			}
			
			//If > right, shift down
			else if(compareStrings(itemStr, localRef.rightData) > 0)
			{
				localRef.centerData = localRef.rightData;
				localRef.rightData = itemStr;
			}
			
			//If between left and right
			else
			{
				localRef.centerData = itemStr;
			}
			
			localRef.numItems = THREE_DATA_ITEMS;
		}
	}
	
	/**
	 * Adds item to 2-3 tree using addItemHelper as needed
	 * 
	 * @param itemStr String value to be added to the tree
	 */
	public void addItem(String itemStr)
	{
		if(root == null)
		{
			root = new TwoThreeNodeClass(itemStr);
			root.numItems = ONE_DATA_ITEM;
		}
		
		else
		{
			addItemHelper(root, itemStr);
		}
	}
	
	/**
	 * Helper method searches from top of tree to bottom using divide and 
	 * conquer strategy to find correct location (node) for new added value; 
	 * once location is found, item is added to node using addAndOrganizeData 
	 * and then fixUpInsert is called in case the updated node has become a 
	 * three-value node
	 * 
	 * @param localRef TwoThreeNodeClass reference to the current item at the 
	 * same given point in the recursion process
	 * @param itemStr String value to be added to the tree
	 */
	private void addItemHelper(TwoThreeNodeClass localRef, String itemStr)
	{
		//If node is a leaf
		if(localRef.leftChildRef == null && localRef.rightChildRef == null 
				&& localRef.centerChildRef == null)
		{
			addAndOrganizeData(localRef, itemStr);
		}
		
		//If node is not a leaf
		else
		{
			//If one node
			if(localRef.numItems == ONE_DATA_ITEM)
			{
				//If < center, move left
				if(compareStrings(itemStr, localRef.centerData) < 0)
				{
					addItemHelper(localRef.leftChildRef, itemStr);
				}
				
				//If > center, move right
				else
				{
					addItemHelper(localRef.rightChildRef, itemStr);
				}
			}
			
			//If two nodes
			else
			{
				//If < left, move left
				if(compareStrings(itemStr, localRef.leftData) < 0)
				{
					addItemHelper(localRef.leftChildRef, itemStr);
				}
				
				//If > right, move right
				else if(compareStrings(itemStr, localRef.rightData) > 0)
				{
					addItemHelper(localRef.rightChildRef, itemStr);
				}
				
				//If between left and right, move down the middle
				else
				{
					addItemHelper(localRef.centerChildRef, itemStr);
				}
			}
		}
		
		//Fix up all three node complications
		fixUpInsert(localRef);
	}
	
	/**
	 * Method clears tree so that new items can be added again
	 */
	public void clear()
	{
		root = null;
	}
	
	/**
	 * Compares two strings
	 * <p>
	 * Returns value greater than zero if left string greater than right string
	 * <p>
	 * Returns value less than zero if left string less than right string
	 * <p>
	 * Returns zero if strings are equal
	 * 
	 * @param leftStr String to be compared
	 * @param rightStr String to be compared
	 * 
	 * @return integer result of test as specified
	 */
	public int compareStrings(String leftStr, String rightStr)
	{
		int index;
		int leftStrLength = leftStr.length();
		int rightStrLength = rightStr.length();
		
		for(index = 0; index < leftStrLength && index < rightStrLength; index++)
		{
			if(leftStr.charAt(index) > rightStr.charAt(index))
			{
				return 1;
			}
			
			else if(leftStr.charAt(index) < rightStr.charAt(index))
			{
				return -1;
			}
		}
		
		return 0;
	}
	
	/**
	 * Method used to fix tree any time a three-value node has been added to the
	 * bottom of the tree; it is always called but decides to act only if it 
	 * finds a three-value node
	 * <p>
	 * Resolves current three-value node which may add a value to the node 
	 * above; if the node above becomes a three-value node, then this is 
	 * resolved with the next recursive call
	 * <p>
	 * Recursively climbs from bottom to top of tree resolving any three-value 
	 * nodes found
	 * 
	 * @param localRef TwoThreeNodeClass reference initially given the currently 
	 * updated node, then is given parent node recursively each time a 
	 * three-value node was resolved
	 */
	private void fixUpInsert(TwoThreeNodeClass localRef)
	{
		TwoThreeNodeClass newNode;
		
		if(localRef.numItems == THREE_DATA_ITEMS)
		{
			// check for at root ref
			if(localRef.parentRef == null)
			{
				// create a new node
				newNode = new TwoThreeNodeClass();
				
				// set new node parent to local ref's parent
				newNode.parentRef = localRef.parentRef;
				
				// set root ref to this new node
				localRef = newNode;
				
				// set the local ref's left child with select left
				localRef.leftChildRef = new 
						TwoThreeNodeClass(TwoThreeNodeClass.LEFT_CHILD_SELECT, 
								newNode);
			}
			
			// check for one node
			if(localRef.numItems == ONE_DATA_ITEM)
			{
				localRef.parentRef.numItems = 2;
				
				//check for being left child (if parent left child = localref)
				if(localRef.parentRef.leftChildRef == localRef)
				{
					localRef.parentRef.leftData = localRef.centerData;
					
					localRef.parentRef.rightData = 
							localRef.parentRef.centerData;
				}
				
				// assume, being a right child
				else
				{
					// set the parent's left value to parent's center value
					localRef.parentRef.leftData = localRef.parentRef.centerData;
					
					// set the parent's right value to local refs's center value
					localRef.parentRef.rightData = localRef.centerData;
				}
			}
			
			// check for two node
			else if(localRef.numItems == TWO_DATA_ITEMS)
			{
				// check for being left child
				if(localRef.parentRef.leftChildRef == localRef)
				{
					fixUpInsert(localRef.leftChildRef);
				}
				
				// check for being center child
				else if(localRef.parentRef.centerChildRef == localRef)
				{
					fixUpInsert(localRef.centerChildRef);
				}
				
				// assume it is a right child
				else
				{
					fixUpInsert(localRef.rightChildRef);
				}
			}
		}
	}
	
	/**
	 * Tests center value if single node, tests left and right values if 
	 * two-value node; returns true if specified data is found in any given node
	 * <p>
	 * Note: Method does not use any branching operations such as if/else/etc.
	 * 
	 * @param localRef TwoThreeNodeClass reference to node with one or two data 
	 * items in it
	 * @param searchStr String value to be found in given node
	 * 
	 * @return boolean result of test
	 */
	private boolean foundInNode(TwoThreeNodeClass localRef, String searchStr)
	{
		return compareStrings(localRef.centerData, searchStr) == 0 || 
				compareStrings(localRef.leftData, searchStr) == 0 || 
				compareStrings(localRef.rightData, searchStr) == 0;
	}
	
	/**
	 * Public method called by user to display data in order
	 * 
	 * @return String result to be displayed and/or analyzed
	 */
	public String inOrderTraversal()
	{
		inOrderTraversalHelper(root);
		
		return outputString;
	}
	
	/**
	 * Helper method conducts in order traversal with 2-3 tree
	 * <p>
	 * Shows location of each value in a node: "C" at center of single node "L" 
	 * or "R" at left or right of two-value node
	 * 
	 * @param localRef TwoThreeNodeClass reference to current location at any 
	 * given point in the recursion process
	 */
	private void inOrderTraversalHelper(TwoThreeNodeClass localRef)
	{
		if(localRef != null)
		{
			inOrderTraversalHelper(localRef.leftChildRef);
		
			if(localRef.numItems == 1)
			{
				outputString += " | C: " + localRef.centerData;
			}
			
			else
			{
				outputString += " | L: " + localRef.leftData;
				outputString += " | R: " + localRef.rightData;
			}
			
			inOrderTraversalHelper(localRef.rightChildRef);
		}
	}
	
	/**
	 * Search method used by programmer to find specified item in 2-3 tree
	 * 
	 * @param searchStr String value to be found in tree
	 * 
	 * @return boolean result of search effort
	 */
	public boolean search(String searchStr)
	{
		if(root == null)
		{
			return false;
		}
		
		return searchHelper(root, searchStr);
	}
	
	/**
	 * Search helper method that traverses through tree in a recursive divide 
	 * and conquer search fashion to find given integer in 2-3 tree
	 * 
	 * @param localRef TwoThreeNodeClass reference to given node at any point 
	 * during the recursive process
	 * @param searchStr String value to be found in tree
	 * 
	 * @return boolean result of search effort
	 */
	private boolean searchHelper(TwoThreeNodeClass localRef, String searchStr)
	{
		if(foundInNode(localRef, searchStr))
		{
			return true;
		}
		
		else if(compareStrings(searchStr, localRef.leftData) < 0)
		{
			searchHelper(localRef.leftChildRef, searchStr);
		}
		
		else if(compareStrings(searchStr, localRef.rightData) > 0)
		{
			searchHelper(localRef.rightChildRef, searchStr);
		}
		
		//Value is between left and right, so move down the center
		else
		{
			searchHelper(localRef.centerChildRef, searchStr);
		}
		
		return false;
	}
}
