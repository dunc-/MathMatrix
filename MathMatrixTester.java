import java.util.Random;

/*  Student information for assignment:
 *
 *  UTEID: cjd2682
 *  email address: duncan.chris@utexas.edu
 *  Grader name: Stas Ilinskiy
 *  Number of slip days I am using: 0
 *  
 */

/* 
 * Experiment #1: With 3000 x 3000 matrix and 1000 tests, average time = 0.006756885962000003
 * 				  With 6000 x 6000 matrix and 1000 tests, average time = 0.028130874954999982
 *                With 12000 x 12000 matrix and 1000 tests, average time = 0.10438753318699999
 *                
 * Experiment #2: With 200 x 200 matrix and 1000 tests, average time = 0.013700953954
 * 				  With 400 x 400 matrix and 1000 tests, average time = 0.20517358862000015
 * 				  With 800 x 800 matrix and 1000 tests, average time = 2.2257151576310017
 * 
 * 1. I would expect the program to take around .4 seconds on average
 * 2. I think the Big O of the add method is O(n^2), and my timing does support this
 * 3. I would expect the program to take around 22 seconds on average
 * 4. I think the Big O of the multiply method is O(n^4), and my timing does support this
 * 5. The max size of a matrix I could create was 21162 x 21162, which means there would be 447,830,244 elements.
 * 	  This results in 1,791,320,976 bytes being used, or 1.66829766333103 gigabytes of memory.
 * 
 */

/**
 * A class to run tests on the MathMatrix class
 */
public class MathMatrixTester {

    /**
     * main method that runs simple test on the MathMatrix class
     *
     * @param args not used
     */
    public static void main(String[] args) {
    	
    	// Tests 1 & 2, changeElement
    	int[][] zeros = {
      			 {0, 0, 0, 0},
       			 {0, 0, 0, 0}};
    	MathMatrix testMatrix1 = new MathMatrix(zeros);
    	testMatrix1.changeElement(0, 0, 1);
    	int[][] expected = {
      			 {1, 0, 0, 0},
       			 {0, 0, 0, 0}};
    	printTestResult(get2DArray(testMatrix1), expected, 1, "change element method..");
    	testMatrix1.changeElement(1, 1, 47);
    	expected = new int[][]{
     			 {1, 0, 0, 0},
      			 {0, 47, 0, 0}};
    	printTestResult(get2DArray(testMatrix1), expected, 2, "change element method..");
    	
    	
    	// Tests 3 & 4, numRows
    	if (testMatrix1.numRows() == 2) 
    		System.out.println("Test number 3 tests the number of rows... The test passed");
    	else 
    		System.out.println("Test number 3 tests the number of rows... The test failed");
    	int[][] a = {
      			 {1, 2, 3, 4, 4},
       			 {5, 6, 7, 8, 7},
      			 {9, 10, 11, 12, 3}};
    	MathMatrix basic = new MathMatrix(a);
    	if (basic.numRows() == 3) 
    		System.out.println("Test number 4 tests the number of rows... The test passed");
    	else 
    		System.out.println("Test number 4 tests the number of rows... The test failed");
    	
    	
    	// Tests 5 & 6, numCols
    	if (testMatrix1.numCols() == 4) 
    		System.out.println("Test number 5 tests the number of columns... The test passed");
    	else 
    		System.out.println("Test number 5 tests the number of columns... The test failed");
    	if (basic.numCols() == 5) 
    		System.out.println("Test number 6 tests the number of columns... The test passed");
    	else 
    		System.out.println("Test number 6 tests the number of columns... The test failed");
    	
    	
    	// Tests 7 & 8, getVal
    	if (testMatrix1.getVal(0, 0) == 1)
    		System.out.println("Test number 7 tests the get value method... The test passed");
    	else 
    		System.out.println("Test number 7 tests the get value method... The test failed");
    	if (basic.getVal(1, 1) == 6) 
    		System.out.println("Test number 8 tests the get value method... The test passed");
    	else 
    		System.out.println("Test number 8 tests the get value method... The test failed");
    	
    	int [][] data1 = {
    			 {-11, -11, -11, -11},
    			 {-11, -11, -11, -11}};
    	int[][] data2 = {
   			 {11, 11, 11, 11},
   			 {11, 11, 11, 11}};
    	
    	
    	// Tests 9 & 10, addition
    	testMatrix1 = new MathMatrix(data1);
    	MathMatrix testMatrix2 = new MathMatrix(data2);
    	MathMatrix testMatrix3 = testMatrix2.add(testMatrix1);
    	expected = new int[][]{
   			 {0, 0, 0, 0},
   			 {0, 0, 0, 0}};
    	printTestResult(get2DArray(testMatrix3), expected, 9, "add method..");
    	testMatrix1 = new MathMatrix(data2);
    	testMatrix2 = new MathMatrix(data2);
    	testMatrix3 = testMatrix2.add(testMatrix1);
    	expected = new int[][]{
    	{22, 22, 22, 22},
    	{22, 22, 22, 22}};
    	printTestResult(get2DArray(testMatrix3), expected, 10, "add method..");
    	
    	
    	// Tests 11 & 12, subtraction
    	testMatrix1 = new MathMatrix(data1);
    	testMatrix2 = new MathMatrix(data2);
    	testMatrix3 = testMatrix2.subtract(testMatrix1);
    	expected = new int[][]{
   			 {22, 22, 22, 22},
   			 {22, 22, 22, 22}};
    	printTestResult(get2DArray(testMatrix3), expected, 11, "subtract method..");
    	testMatrix1 = new MathMatrix(data2);
    	testMatrix2 = new MathMatrix(data1);
    	testMatrix3 = testMatrix2.subtract(testMatrix1);
    	expected = new int[][]{
    	{-22, -22, -22, -22},
    	{-22, -22, -22, -22}};
    	printTestResult(get2DArray(testMatrix3), expected, 12, "subtract method..");
    	
    	
    	// Tests 13 & 14, multiply
    	data2 = new int[][]{
      			 {11, 11},
      			 {11, 11},
      			 {11, 11},
      			 {11, 11}};
    	testMatrix1 = new MathMatrix(data1);
    	testMatrix2 = new MathMatrix(data2);
    	testMatrix3 = testMatrix2.multiply(testMatrix1);
    	expected = new int[][]{
   			 {-242, -242, -242, -242},
   			 {-242, -242, -242, -242},
   			 {-242, -242, -242, -242},
   			 {-242, -242, -242, -242}};
    	printTestResult(get2DArray(testMatrix3), expected, 13, "multiplication method..");
    	data2 = new int[][]{
     			 {-1, 2},
     			 {3, -4},
     			 {-5, 6},
     			 {7, -8}};
    	testMatrix1 = new MathMatrix(data1);
    	testMatrix2 = new MathMatrix(data2);
    	testMatrix3 = testMatrix2.multiply(testMatrix1);
    	expected = new int[][]{
  			 {-11, -11, -11, -11},
  			 {11, 11, 11, 11},
  			 {-11, -11, -11, -11},
  			 {11, 11, 11, 11}};
    	printTestResult(get2DArray(testMatrix3), expected, 14, "multiplication method..");
    	
    	
    	// Tests 15 & 16, scale
    	testMatrix1.scale(2);
    	expected = new int[][]{
      			 {-22, -22, -22, -22},
      			 {-22, -22, -22, -22}};
    	printTestResult(get2DArray(testMatrix1), expected, 15, "scaling method..");
    	testMatrix1.scale(-2);
    	expected = new int[][]{
      			 {44, 44, 44, 44},
      			 {44, 44, 44, 44}};
    	printTestResult(get2DArray(testMatrix1), expected, 16, "scaling method..");
    	
    	
    	// Tests 17 & 18, getTranspose
    	testMatrix1 = new MathMatrix(zeros);
    	testMatrix3 = testMatrix1.getTranspose();
    	expected = new int[][]{
    			 {0, 0},
    			 {0, 0},
    			 {0, 0},
    			 {0, 0}};
    	printTestResult(get2DArray(testMatrix3), expected, 17, "transpose method..");
    	testMatrix1 = new MathMatrix(expected);
    	testMatrix3 = testMatrix1.getTranspose();
    	expected = new int[][]{
      			 {0, 0, 0, 0},
       			 {0, 0, 0, 0}};
    	printTestResult(get2DArray(testMatrix3), expected, 18, "transpose method..");
    	
    	
    	// Tests 19 & 20, equals
    	basic = new MathMatrix(expected);
    	if (testMatrix3.equals(basic))
    		System.out.println("Test number 19 tests the equals method... The test passed");
    	else 
    		System.out.println("Test number 19 tests the equals method... The test failed");
    	testMatrix3 = new MathMatrix(data1);
    	basic = new MathMatrix(data1);
    	if (testMatrix3.equals(basic)) 
    		System.out.println("Test number 20 tests the equals method... The test passed");
    	else 
    		System.out.println("Test number 20 tests the equals method... The test failed");
    	
    	
    	// Tests 21 & 22, toString
    	String expectedString = "| -11 -11 -11 -11|\n| -11 -11 -11 -11|\n";
    	if (testMatrix3.toString().equals(expectedString))
    		System.out.println("Test number 21 tests the toString method... The test passed");
    	else 
    		System.out.println("Test number 21 tests the toString method... The test failed");
    	testMatrix3 = new MathMatrix(zeros);
    	expectedString = "| 0 0 0 0|\n| 0 0 0 0|\n";
    	if (testMatrix3.toString().equals(expectedString))
    		System.out.println("Test number 22 tests the toString method... The test passed");
    	else 
    		System.out.println("Test number 22 tests the toString method... The test failed");
    	
    	
    	// Tests 23-25, upperTriangular
    	testMatrix1 = new MathMatrix(1, 1, 25);
    	if (testMatrix1.isUpperTriangular())
    		System.out.println("Test number 23 tests the isUpperTriangular method... The test passed");
    	else 
    		System.out.println("Test number 23 tests the isUpperTriangular method... The test failed");
    	int[][] triangle = new int[][]{
 			 {11, 11, 11},
 			 {0, 11, 11},
 			 {0, 0, 11}};
    	testMatrix1 = new MathMatrix(triangle);
    	if (testMatrix1.isUpperTriangular())
    		System.out.println("Test number 24 tests the isUpperTriangular method... The test passed");
    	else 
    		System.out.println("Test number 24 tests the isUpperTriangular method... The test failed");
    	triangle = new int[][]{
    			 {11, 11, 11},
    			 {11, 11, 11},
    			 {0, 0, 11}};
    	testMatrix1 = new MathMatrix(triangle);
    	if (!testMatrix1.isUpperTriangular())
    		System.out.println("Test number 25 tests the isUpperTriangular method... The test passed");
    	else 
    		System.out.println("Test number 25 tests the isUpperTriangular method... The test failed");
    	
    	
  /*  	// EXPERIMENT #1 CODE
    	Random rand = new Random();
    	MathMatrix forTest1 = new MathMatrix(3000, 3000, 1);
        MathMatrix forTest2 = new MathMatrix(3000, 3000, 2);
    	double time = 0;
    	Stopwatch s = new Stopwatch();
    	for (int i=0; i<1000; i++) {
    		s.start();
    		forTest1.add(forTest2);
    		s.stop();
    		time += s.time();
    	}
    	System.out.println(time/1000);
    	forTest1 = new MathMatrix(6000, 6000, 1);
    	forTest2 = new MathMatrix(6000, 6000, 2);
    	time = 0;
    	for (int i=0; i<1000; i++) {
    		s.start();
    		forTest1.add(forTest2);
    		s.stop();
    		time += s.time();
    	}
    	System.out.println(time/1000);
    	forTest1 = new MathMatrix(12000, 12000, 1);
    	forTest2 = new MathMatrix(12000, 12000, 2);
    	time = 0;
    	for (int i=0; i<1000; i++) {
    		s.start();
    		forTest1.add(forTest2);
    		s.stop();
    		time += s.time();
    	}
    	System.out.println(time/1000);     */
    	
    	
  /*   	// EXPERIMENT #2 CODE
    	MathMatrix forTest1 = new MathMatrix(200, 200, 1);
    	MathMatrix forTest2 = new MathMatrix(200, 200, 2);
    	double time = 0;
    	Stopwatch s = new Stopwatch();
    	for (int i=0; i<1000; i++) {
    		s.start();
    		forTest1.multiply(forTest2);
    		s.stop();
    		time += s.time();
    	}
     	System.out.println(time/1000);
     	forTest1 = new MathMatrix(400, 400, 1);
    	forTest2 = new MathMatrix(400, 400, 2);
    	time = 0;
    	for (int i=0; i<1000; i++) {
    		s.start();
    		forTest1.multiply(forTest2);
    		s.stop();
    		time += s.time();
    	}
    	System.out.println(time/1000);
    	forTest1 = new MathMatrix(800, 800, 1);
    	forTest2 = new MathMatrix(800, 800, 2);
    	time = 0;
    	for (int i=0; i<1000; i++) {
    		s.start();
    		forTest1.multiply(forTest2);
    		s.stop();
    		time += s.time();
    	}
    	System.out.println(time/1000);        */
    	
    	// MAX HEAP USAGE
    	//MathMatrix maxHeap = new MathMatrix(21162, 21162, 1);
    }
    
    // method that sums elements of mat, may overflow int!
    // pre: mat != null
    private static int sumVals(MathMatrix mat) {
        if(mat == null)
            throw new IllegalArgumentException("mat may not be null");
        
        int result = 0;
        final int ROWS =  mat.numRows();
        final int COLS = mat.numCols();
        for(int r = 0; r < ROWS; r++)
            for(int c = 0; c < COLS; c++) 
                result += mat.getVal(r, c); // likely to overflow, but can still do simple check
        return result;
    }
    
    // create a matrix with random values
    // pre: rows > 0, cols > 0, randNumGen != null
    private static MathMatrix createMat(Random randNumGen, int rows,
            int cols, final int LIMIT) {
        
        if(randNumGen == null)
            throw new IllegalArgumentException("randomNumGen variable may no be null");
        else if(rows <= 0 || cols <= 0)
            throw new IllegalArgumentException("rows and columns must be greater than 0. " +
            		"rows: " + rows + ", cols: " + cols);
        
        int[][] temp = new int[rows][cols];
        final int SUB = LIMIT / 4;
        for(int r = 0; r < rows; r++)
            for(int c = 0; c < cols; c++)
                temp[r][c] = randNumGen.nextInt(LIMIT) - SUB;
        
        return new MathMatrix(temp);
    }

    private static void printTestResult(int[][] data1, int[][] data2, int testNum, String testingWhat) {
        System.out.print( "Test number " + testNum + " tests the " + testingWhat +". The test ");
        String result = equals(data1, data2) ? "passed" : "failed";
        System.out.println( result );
    }

    // pre: m != null, m is at least 1 by 1 in size
    private static int[][] get2DArray(MathMatrix m) {
        //check precondition
        assert ( m != null ) && ( m.numRows() > 0 ) && ( m.numCols()> 0 )
                : "Violation of precondition: get2DArray";

        int[][] result = new int[m.numRows()][m.numCols()];
        for(int r = 0; r < result.length; r++)
        {   for(int c = 0; c < result[0].length; c++)
            {   result[r][c] = m.getVal(r,c);
            }
        }
        return result;
    }

    // pre: data1 != null, data2 != null, data1 and data2 are at least 1 by 1 matrices
    //      data1 and data2 are rectangular matrices
    // post: return true if data1 and data2 are the same size and all elements are
    //      the same
    private static boolean equals(int[][] data1, int[][] data2) {
       //check precondition
        if( ( data1 == null ) || ( data1.length == 0 )
               || ( data1[0].length == 0 ) || !rectangularMatrix(data1)
               ||  ( data2 == null ) || ( data2.length == 0 )
               || ( data2[0].length == 0 ) || !rectangularMatrix(data2))
                throw new IllegalArgumentException( "Violation of precondition: equals check on 2d arrays of ints");

        boolean result = (data1.length == data2.length) && (data1[0].length == data2[0].length);
        int row = 0;
        while( result && row < data1.length ) {
            int col = 0;
            while( result && col < data1[0].length ) {
               result = (data1[row][col] == data2[row][col]);
                col++;
            }
            row++;
        }

        return result;
    }


    // method to ensure mat is rectangular
    // pre: mat != null, mat is at least 1 by 1
    private static boolean rectangularMatrix( int[][] mat ) {
        if(mat == null || mat.length == 0 || mat[0].length == 0)
            throw new IllegalArgumentException("Violation of precondition: "
                    + " Parameter mat may not be null" 
                    + " and must be at least 1 by 1");
        return MathMatrix.rectangularMatrix(mat);
    }
}