package p8_package;

/**
 * Binary search tree (BST) class that stores StudentClassNode data using the 
 * student ID as the key
 * <p>
 * Note: Assumes all student ID keys are unique
 * 
 * @author William Rogers
 *
 */
public class BST_Class 
{

	/**
	 * Root of BST
	 */
	private StudentClassNode BST_Root;
	
	/**
	 * Used for acquiring ordered tree visitations in String form
	 */
	private String outputString;
	
	/**
	 * Default class constructor, initializes BST
	 */
	public BST_Class()
	{
		BST_Root = null;
		outputString = "";
	}
	
	/**
	 * Copy constructor
	 * <p>
	 * Note: Uses copyConstHelper
	 * 
	 * @param copied BST_Class object to be copied
	 */
	public BST_Class(BST_Class copied)
	{
		outputString = copied.outputString;
		
		BST_Root = copyConstHelper(copied.BST_Root);
	}
	
	/**
	 * Copy constructor helper, recursively adds nodes to duplicate tree
	 * 
	 * @param copiedRef StudentClassNode reference for accessing copied object 
	 * data
	 * 
	 * @return StudentClassNode reference to node added at current level of 
	 * recursion
	 */
	private StudentClassNode copyConstHelper(StudentClassNode copiedRef)
	{
		BST_Root = null;
		
		if(copiedRef != null)
		{
			BST_Root = new StudentClassNode(copiedRef);
			
			BST_Root.leftChildRef = copyConstHelper(copiedRef.leftChildRef);
			
			BST_Root.rightChildRef = copyConstHelper(copiedRef.rightChildRef);
		}
		
		return BST_Root;
	}
	
	/**
	 * Clears tree
	 */
	public void clearTree()
	{
		BST_Root = new StudentClassNode();
	}
	
	/**
	 * Insert method for BST
	 * <p>
	 * Note: uses insert helper method to insert by student ID key
	 * 
	 * @param inData StudentClassNode data to be added to BST
	 */
	public void insert(StudentClassNode inData)
	{
		insertHelper(BST_Root, inData);
	}
	
	/**
	 * Insert helper method for BST insert action
	 * <p>
	 * Note: Inserts by student ID key
	 * <p>
	 * Note: Uses look-down strategy and links to current node; handles special 
	 * case of empty tree
	 * 
	 * @param localRoot StudentClassNode tree root reference at the current 
	 * recursion level
	 * @param inData StudentClassNode item to be added to BST
	 */
	private void insertHelper(StudentClassNode localRoot, StudentClassNode 
			inData)
	{
		if(isEmpty())
		{
			BST_Root = new StudentClassNode(inData);
		}
		
		else
		{
			if(inData.studentID < localRoot.studentID)
			{
				if(localRoot.leftChildRef == null)
				{
					localRoot.leftChildRef = inData;
				}
				
				else
				{
					insertHelper(localRoot.leftChildRef, inData);
				}
			}
			
			else
			{
				if(localRoot.rightChildRef == null)
				{
					localRoot.rightChildRef = inData;
				}
				
				else
				{
					insertHelper(localRoot.rightChildRef, inData);
				}
			}
		}
	}
	
	/**
	 * Test for empty tree
	 * 
	 * @return Boolean result of test
	 */
	public boolean isEmpty()
	{
		return BST_Root == null;
	}
	
	/**
	 * Provides inOrder traversal for user as a string
	 * 
	 * @return String containing in order output
	 */
	public String outputInOrder()
	{
		outputString = "";
		
		outputInOrderHelper(BST_Root);
		
		return outputString;
	}
	
	/**
	 * Provides inOrder traversal action helper
	 * <p>
	 * Note: Updates string with each call
	 * 
	 * @param localRoot StudentClassNode tree root reference at the current 
	 * recursion level
	 */
	private void outputInOrderHelper(StudentClassNode localRoot)
	{	
		if(localRoot != null)
		{
			outputInOrderHelper(localRoot.leftChildRef);
			
			outputString += localRoot.toString() + "\n";
			
			outputInOrderHelper(localRoot.rightChildRef);
		}
	}
	
	/**
	 * Provides postOrder traversal for use as a string
	 * 
	 * @return String containing post order output
	 */
	public String outputPostOrder()
	{
		outputString = "";
		
		outputPostOrderHelper(BST_Root);
		
		return outputString;
	}
	
	/**
	 * Provides postOrder traversal action helper
	 * <p>
	 * Note: Updates string with each call
	 * 
	 * @return  StudentClassNode tree root reference at the current recursion 
	 * level
	 */
	private void outputPostOrderHelper(StudentClassNode localRoot)
	{
		if(localRoot != null)
		{
			outputInOrderHelper(localRoot.leftChildRef);
			
			outputInOrderHelper(localRoot.rightChildRef);
			
			outputString += localRoot.toString() + "\n";
		}
	}
	
	/**
	 * Provides preOrder traversal for user as a string
	 * 
	 * @return String containing pre order output
	 */
	public String outputPreOrder()
	{
		outputString = "";
		
		outputPreOrderHelper(BST_Root);
		
		return outputString;
	}
	
	/**
	 * Provides preOrder traversal action helper
	 * <p>
	 * Note: Updates string with each call
	 * 
	 * @param localRoot StudentClassNode tree root reference at the current 
	 * recursion level
	 */
	private void outputPreOrderHelper(StudentClassNode localRoot)
	{
		if(localRoot != null)
		{
			outputString += localRoot.toString() + "\n";
			
			outputInOrderHelper(localRoot.leftChildRef);
			
			outputInOrderHelper(localRoot.rightChildRef);
		}
	}
	
	/**
	 * Searches tree from given node to maximum value node below it, unlinks and
	 * returns found node
	 * 
	 * @param parent StudentClassNode reference to current node
	 * @param child StudentClassNode reference to child node to be tested
	 * 
	 * @return StudentClassNode reference containing removed node
	 */
	private StudentClassNode removeFromMax(StudentClassNode parent, 
			StudentClassNode child)
	{
		if(child.rightChildRef != null)
		{
			removeFromMax(child, child.rightChildRef);
		}
		
		else
		{
			parent.rightChildRef = child.leftChildRef;
		}
		
		return child;
	}
	
	/**
	 * Removes data node from tree using student ID key
	 * <p>
	 * Note: uses remove helper method
	 * <p>
	 * Note: Verifies if data is available with search method, then if found, 
	 * calls remove
	 * 
	 * @param inData StudentClassNode that includes the student ID key
	 * 
	 * @return StudentClassNode result of remove action
	 */
	public StudentClassNode removeNode(StudentClassNode inData)
	{
		StudentClassNode removed, localRoot = search(inData);
		
		if(localRoot != null)
		{
			//create a new node from removed reference, assign to same removed 
			//reference
			removed = removeNodeHelper(localRoot, inData);
			
			localRoot = new StudentClassNode(removed);
			
			return localRoot;
		}
		
		return null;
	}
	
	/**
	 * Remove helper for BST remove action for removing by student ID key
	 * <p>
	 * Note: uses removeFromMax method
	 * <p>
	 * Note: Assumes removed node is available since it was previously found by 
	 * search in removeNode
	 *  
	 * @param localRoot StudentClassNode tree root reference at the current 
	 * recursion level
	 * @param outData StudentClassNode item that includes the student ID key
	 * 
	 * @return StudentClassNode reference result of remove helper action
	 */
	private StudentClassNode removeNodeHelper(StudentClassNode localRoot, 
			StudentClassNode outData)
	{
		StudentClassNode data;
		
		if(localRoot.leftChildRef == null)
		{
			return localRoot.rightChildRef;
		}
		
		else if(localRoot.rightChildRef == null)
		{
			return localRoot.leftChildRef;
		}
		
		else
		{
			if(localRoot.leftChildRef.rightChildRef != null)
			{
				data = removeFromMax(localRoot, localRoot.leftChildRef);
				localRoot.setStudentClassData(data);
			}
			
			else
			{
				localRoot = localRoot.leftChildRef;
				localRoot.leftChildRef = localRoot.leftChildRef.leftChildRef;
			}
		}
		
		return localRoot;
	}
	
	/**
	 * Searches for data in BST given StudentClassNode with necessary key
	 * 
	 * @param searchData StudentClassNode item containing key
	 * 
	 * @return StudentClassNode reference to found data
	 */
	public StudentClassNode search(StudentClassNode searchData)
	{
		return searchHelper(BST_Root, searchData);
	}
	
	/**
	 * Helper method for BST search action
	 * 
	 * @param localRoot StudentClassNode tree root reference at the current 
	 * recursion level
	 * @param searchData StudentClassNode item containing key
	 * 
	 * @return StudentClassNode item found
	 */
	private StudentClassNode searchHelper(StudentClassNode localRoot, 
			StudentClassNode searchData)
	{
		if(!isEmpty())
		{
			if(searchData.studentID < localRoot.studentID)
			{
				searchHelper(localRoot.leftChildRef, searchData);
			}
			
			else if(searchData.studentID > localRoot.studentID)
			{
				searchHelper(localRoot.rightChildRef, searchData);
			}
			
			else
			{
				return localRoot;
			}
		}
		
		return null;
	}
}
