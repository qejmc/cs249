package p1_package;

/**
 * Class manages two dimensional array of sticks or bones; 
 * includes one dimensional array of sticks
 * and one dimensional array of bones
 * <p>
 * The limit on number of sticks is 25 per cent of array height
 * and the limit on number of bones is 25 per cent of array width
 * 
 * @author William Rogers
 *
 */
public class SticksBonesClass
   {
    /**
     * Constant, default two dimensional array sides
     */
    private static final int TWO_DIM_ARRAY_SIDE = 50;
       
    /**
     * constant, stick and bone percentage of side length
     */
    private static final double STICK_BONE_PERCENTAGE = 0.25;

    /**
     * constant, horizontal line character
     */
    private static final char HORIZ_LINE = '-';
    
    /**
     * constant, vertical line character
     */
    private static final char VERTICAL_LINE = '|';
    
    /**
     * Master array of sticks and bones
     */
    private CellClass[][] fieldArray;
    
    /**
     * Array of sticks
     */
    private ArtifactClass[] stickArray;
    
    /**
     * height of cell array
     */
    private int fieldArrayHeight;
    
    /**
     * width of cell array
     */
    private int fieldArrayWidth;
    
    /**
     * Number of sticks
     */
    private int numSticks;
    
    /**
     * Array of bones
     */
    private ArtifactClass[] boneArray;
    
    /**
     * Number of bones
     */
    private int numBones;
    
    /**
     * Default constructor
     * <p>
     * Initializes class with random sticks and bones in square array;
     * arrays use default capacities
     */
    public SticksBonesClass()
       {
        this( TWO_DIM_ARRAY_SIDE, TWO_DIM_ARRAY_SIDE );
       }
    
    /**
     * Initialization constructor
     * <p>
     * Initializes class with random sticks and bones;
     * arrays use parameter capacities
     * 
     * @param height integer number of rows in two dimensional array
     * 
     * @param width integer number of columns in two dimensional array
     */
    public SticksBonesClass( int height, int width )
       {
        fieldArrayHeight = height;
        
        fieldArrayWidth = width;
        
        // generate random array
        createRandomStickBoneArray( height, width );
        
        // find sticks, store in array
        findAllSticks();
        
        // find bones, store in array
        findAllBones();
       }
    
    /**
     * Creates a random stick/bone array
     * <p>
     * Random x, y location, with random length of stick or bone
     * between 3 and 6
     * 
     * @param height integer value representing number of rows in array
     * 
     * @param width integer value representing number of columns in array
     */
    // create random stick/bone array
    private void createRandomStickBoneArray( int height, int width )
       {
        int rowIndex, colIndex, locIndex, index = 0;
        int yLoc, xLoc, startLoc = 0;
        int itemLength;
        int minItemLength = 3;
        int maxItemLength = 6;
        int stickCount, boneCount;
        
        fieldArrayHeight = height;
        fieldArrayWidth = width;
        
        // create array of cells
        fieldArray = new CellClass[ fieldArrayHeight ][ fieldArrayWidth ];
        
        for( rowIndex = 0; rowIndex < fieldArrayHeight; rowIndex++ )
           {
            for( colIndex = 0; colIndex < fieldArrayWidth; colIndex++ )
               {
                fieldArray[ rowIndex ][ colIndex ] = new CellClass();
               }
           }
        
        // generate between 1/3 of height and full height sticks and bones
        stickCount = generateRandBetween( height / 3, height );
        boneCount = generateRandBetween( height / 3, height );

        boolean createItemSuccess;
        
        while( index < stickCount )
           {
            yLoc = generateRandBetween( startLoc, height - 1 );
            xLoc = generateRandBetween( startLoc, width - 1 );
            itemLength = generateRandBetween( minItemLength, maxItemLength );

            createItemSuccess = true;
            
            for( locIndex = 0; 
                  locIndex < itemLength && createItemSuccess; locIndex++ )
               {
                if( yLoc + locIndex >= height
                      || xLoc >= width
                           || fieldArray[ yLoc + locIndex ][ xLoc ].isUsed() )
                   {
                    createItemSuccess = false;
                   }
               }
            
            if( createItemSuccess )
               {
                for( locIndex = 0; locIndex < itemLength; locIndex++ )
                   {
                    fieldArray[ yLoc + locIndex ][ xLoc ]
                                .setCellCharacter( CellClass.STICK_BONE_CHAR );
                   }

                index++;
               }
           }
        
        index = 0;
        
        while( index < boneCount )
           {
            yLoc = generateRandBetween( startLoc, height - 1 );
            xLoc = generateRandBetween( startLoc, width - 1 );
            itemLength = generateRandBetween( minItemLength, maxItemLength );
            
            createItemSuccess = true;
            
            for( locIndex = 0; 
                  locIndex < itemLength && createItemSuccess; locIndex++ )
               {
                if( xLoc + locIndex >= width
                    || yLoc >= height
                          || fieldArray[ yLoc ][ xLoc + locIndex ].isUsed() )

                   {
                    createItemSuccess = false;
                   }
               }
            
            if( createItemSuccess )
               {
                for( locIndex = 0; locIndex < itemLength; locIndex++ )
                   {
                    fieldArray[ yLoc ][ xLoc + locIndex ]
                                .setCellCharacter( CellClass.STICK_BONE_CHAR );
                   }

                index++;
               }
           }

       }
    
    /**
     * Finds all bones, assigns to bone array
     * <p>
     * May find adjacent crossing stick and count as bone
     */
    public void findAllBones()
       {
    		int xPos, yPos, boneLength = 1, index = 0;
    		int xBone = 0, yBone = 0;
    		
    		CellClass cell, nextCell;
    		
    		ArtifactClass bone;
    		
    		boneArray = new ArtifactClass[(int) (fieldArrayWidth * 
    				TWO_DIM_ARRAY_SIDE * STICK_BONE_PERCENTAGE)];
    		
    		numBones = 0;
    		
    		for(yPos = 0; yPos < fieldArrayHeight; yPos++)
    		{
    			for(xPos = 0; xPos < fieldArrayWidth; xPos++)
        		{
    				nextCell = fieldArray[yPos][xPos];
        			cell = fieldArray[yPos][xPos];
        			
        			if(xPos != fieldArrayWidth - 1)
        			{
        				nextCell = fieldArray[yPos][xPos + 1];
        			}
        			
        			// Checks if next cell is a bone character
        			if(cell.getCellCharacter() == CellClass.STICK_BONE_CHAR && 
        					nextCell.getCellCharacter() == 
        					CellClass.STICK_BONE_CHAR)
        			{
        				// If it is, and it is a new bone, mark the x and y pos
        				if(boneLength == 1)
        				{
        					xBone = xPos;
            				yBone = yPos;
        				}
        				
        				boneLength++;
        			}
        			
        			else
        			{
        				// Check if the program just finished passing through a 
        				// bone, if so, create a new bone artifact
        				if(boneLength > 2)
        				{
        					bone = new ArtifactClass(xBone, yBone, 
        							ArtifactClass.BONE, boneLength);
            				
            				boneArray[index] = bone;
            				
            				index++;
            				numBones++;
            				
        				}
        				
        				// Reset the bone status
        				boneLength = 1;
        				
        			}
        			
        		}
    		}
    		
       }
    
    /**
     * Finds all sticks, assigns to sticks array, 
     * <p>
     * May find adjacent crossing bone and count as stick
     */
    public void findAllSticks()
       {
			int xPos, yPos, stickLength = 1, index = 0;
			int xStick = 0, yStick = 0;
			
			CellClass cell, nextCell;
			
			ArtifactClass stick;
			
			stickArray = new ArtifactClass[(int) (fieldArrayHeight * 
					TWO_DIM_ARRAY_SIDE * STICK_BONE_PERCENTAGE)];
			
			numSticks = 0;
			
			for(xPos = 0; xPos < fieldArrayWidth; xPos++)
			{
				for(yPos = 0; yPos < fieldArrayHeight; yPos++)
	    		{
					nextCell = fieldArray[yPos][xPos];
	    			cell = fieldArray[yPos][xPos];
	    			
	    			if(yPos != fieldArrayHeight - 1)
	    			{
	    				nextCell = fieldArray[yPos + 1][xPos];
	    			}
	    			
	    			// Same as bone finder. See above.
	    			if(cell.getCellCharacter() == CellClass.STICK_BONE_CHAR && 
	    					nextCell.getCellCharacter() == 
	    					CellClass.STICK_BONE_CHAR)
	    			{
	    				
	    				if(stickLength == 1)
	    				{
	    					xStick = xPos;
	        				yStick = yPos;
	    				}
	    				
	    				stickLength++;
	    			}
	    			
	    			else
	    			{
	    				if(stickLength > 2)
	    				{
	    					stick = new ArtifactClass(xStick, yStick, 
	    							ArtifactClass.STICK, stickLength);
	        				
	        				stickArray[index] = stick;
	        				
	        				index++;
	        				numSticks++;
	        				
	    				}
	
	    				stickLength = 1;
	    				
	    			}
	    			
	    		}
			}
       }

    /**
     * Generates random values between given low, high, inclusive
     * 
     * @param low integer value representing low end of random output
     * 
     * @param high integer value representing high end of random output
     * 
     * @return integer random value returned between low and high
     * parameters, inclusive
     */
    public int generateRandBetween( int low, int high )
       {
        int range = high - low + 1;
        
        return (int)( Math.random() * range ) + low;          
       }    

    /**
     * Shows field of sticks and bones, has frame around perimeter
     */
    public void showField()
       {
    		int hPos, yPos, sPos;
    		CellClass cell;
    		
    		System.out.println("Field Display:");
    		
    		for(hPos = 0; hPos < fieldArrayWidth + 2; hPos++)
    		{
    			System.out.print(HORIZ_LINE);
    		}
    		
    		System.out.println();
    		
    		for(yPos = 0; yPos < fieldArrayHeight; yPos++)
    		{
    			System.out.print(VERTICAL_LINE);
    			
    			for(sPos = 0; sPos < fieldArrayWidth; sPos++)
    			{
    				cell = fieldArray[yPos][sPos];
    				
    				System.out.print(cell);
    			}
    			
    			System.out.println(VERTICAL_LINE);
    		}
    		
    		for(hPos = 0; hPos < fieldArrayWidth + 2; hPos++)
    		{
    			System.out.print(HORIZ_LINE);
    		}
    		
    		showBoneList();
    		showStickList();
       }
    
    /**
     * Shows bone list, provides results in formatted, aligned output
     *  
     */
    public void showBoneList()
       {
    		int index;
    		
    		System.out.format("\n\nFound %d Bones:", numBones);
    		
    		for(index = 0; index < numBones; index++)
    		{
    			System.out.format("\nBone at ( %2d, %2d ), length is: %2d", 
    					boneArray[index].xPos, boneArray[index].yPos, 
    					boneArray[index].length);
    		}
       }

    /**
     * Shows list of sticks, provides results in formatted, aligned output
     *    
     */
    public void showStickList()
       {
	    	int index;
			
			System.out.format("\n\n\nFound %d Sticks:", numSticks);
			
			for(index = 0; index < numSticks; index++)
			{
				System.out.format("\nStick at ( %2d, %2d ), length is: %2d", 
						stickArray[index].xPos, stickArray[index].yPos, 
						stickArray[index].length);
			}
       }
    
   }

