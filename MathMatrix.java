/**
 * A class that models systems of linear equations (Math Matrices)
 * as used in linear algebra.
 *
 * @version Skeleton file for students
 */
public class MathMatrix
{
    private int[][] values;
    
    /**
     * create a MathMatrix with cells equal to the values in mat.
     * A "deep" copy of mat is made.
     * Changes to mat after this constructor do not affect this
     * Matrix and changes to this MathMatrix do not affect mat
     * @param  mat  mat !=null, mat.length > 0, mat[0].length > 0,
     * mat is a rectangular matrix
     */
    public MathMatrix(int[][] mat) {
        // check the precondition. rectangularMatrix is a private method at end of Matrix class
        if((mat == null) || (mat.length == 0) || (mat[0].length == 0)
                || !rectangularMatrix(mat)) 
            throw new IllegalArgumentException("Violation of precondition: " +
            		"int[][] Matrix constructor");
        values = new int[mat.length][mat[0].length];
        for (int r=0; r<values.length; r++) { // For deep copying
        	for (int c=0; c<values[0].length; c++)
        		values[r][c] = mat[r][c];
        }
    }


    /**
     * create a MathMatrix of the specified size with all cells set to the intialValue.
     * <br>pre: numRows > 0, numCols > 0
     * <br>post: create a matrix with numRows rows and numCols columns. 
     * All elements of this matrix equal initialVal.
     * In other words after this method has been called getVal(r,c) = initialVal 
     * for all valid r and c.
     * @param numRows numRows > 0
     * @param numCols numCols > 0
     * @param initialVal all cells of this Matrix are set to initialVal
     */
    public MathMatrix(int numRows, int numCols, int initialVal) {
        if ((numRows <= 0) || (numCols <= 0)) 
        	throw new IllegalArgumentException("Violation of precondition: Both numRows"
        			+ " and numCols must be > 0");
        values = new int[numRows][numCols];
        for (int r=0; r<values.length; r++) {
        	for (int c=0; c<values[0].length; c++)
        		values[r][c] = initialVal; // initialize all values of the array as one value
        }
    }


    /**
     * change the value of one of the cells in this MathMatrix.
     * <br>pre: 0 <= row < numRows(), 0 <= col < numCols()
     * <br>post: getVal(row, col) = newValue
     * @param row 0 <= row < numRows()
     * @param col 0 <= col < numCols()
     */
    public void changeElement(int row, int col, int newValue) {
    	if (row < 0 || row >= numRows() || col < 0 || col >= numCols())
    		throw new IllegalArgumentException("Violation of precondition: row or col is not a valid value");
        values[row][col] = newValue; // replace the old value with the new value
    }


    /**
     * Get the number of rows.
     * @return the number of rows in this MathMatrix
     */
    public int numRows() {
        return values.length;
    }


    /**
     * Get the number of columns.
     * @return the number of columns in this MathMatrix
     */
    public int numCols() {
        return values[0].length;
    }


    /**
     * get the value of a cell in this MathMatrix.
     * <br>pre: row  0 <= row < numRows(), col  0 <= col < numCols()
     * @param  row  0 <= row < numRows()
     * @param  col  0 <= col < numCols()
     * @return the value at the specified position
     */
    public int getVal(int row, int col) {
    	if (row < 0 || row >= numRows() || col < 0 || col >= numCols())
    		throw new IllegalArgumentException("Violation of precondition: row or col is not a valid value");
    	return values[row][col]; // return the value at the given row and column
    }


   /**
    * implements MathMatrix addition, (this MathMatrix) + rightHandSide.
    * <br>pre: rightHandSide.numRows() = numRows(), rightHandSide.numCols() = numCols()
    * <br>post: This method does not alter the calling object or rightHandSide
    * @param rightHandSide rightHandSide.numRows() = numRows(), rightHandSide.numCols() = numCols()
    * @return a new MathMatrix that is the result of adding this Matrix to rightHandSide.
    * The number of rows in the returned Matrix is equal to the number of rows in this MathMatrix.
    * The number of columns in the returned Matrix is equal to the number of columns in this MathMatrix.
    */
    public MathMatrix add(MathMatrix rightHandSide) {
    	if (rightHandSide.numRows() != numRows() || rightHandSide.numCols() != numCols())
    		throw new IllegalArgumentException("Violation of precondition: dimensions do not match properly");
        for (int r=0; r<values.length; r++) { // for every row
        	for (int c=0; c<values[0].length; c++) // for every column
        		values[r][c] += rightHandSide.values[r][c]; // sum the values together and put that sum into the array to be returned
        }
        return this;
    }


   /**
    * implements MathMatrix subtraction, (this MathMatrix) - rightHandSide.
    * <br>pre: rightHandSide.numRows() = numRows(), rightHandSide.numCols() = numCols()
    * <br>post: This method does not alter the calling object or rightHandSide
    * @param rightHandSide rightHandSide.numRows() = numRows(), rightHandSide.numCols() = numCols()
    * @return a new MathMatrix that is the result of subtracting rightHandSide from this MathMatrix.
    * The number of rows in the returned MathMatrix is equal to the number of rows in this MathMatrix.
    * The number of columns in the returned MathMatrix is equal to the number of columns in this MathMatrix.
    */
    public MathMatrix subtract(MathMatrix rightHandSide) {
    	if (rightHandSide.numRows() != numRows() || rightHandSide.numCols() != numCols())
    		throw new IllegalArgumentException("Violation of precondition: dimensions do not match properly");
        for (int r=0; r<values.length; r++) { // for every row
        	for (int c=0; c<values[0].length; c++) // for every column
        		values[r][c] -= rightHandSide.values[r][c]; // put the difference in the array to be returned
        }
        return this;
    }


   /**
    * implements matrix multiplication, (this MathMatrix) * rightHandSide.
    * <br>pre: rightHandSide.numRows() = numCols()
    * <br>post: This method should not alter the calling object or rightHandSide
    * @param rightHandSide rightHandSide.numRows() = numCols()
    * @return a new MathMatrix that is the result of multiplying this MathMatrix and rightHandSide.
    * The number of rows in the returned MathMatrix is equal to the number of rows in this MathMatrix.
    * The number of columns in the returned MathMatrix is equal to the number of columns in rightHandSide.
    */
    public MathMatrix multiply(MathMatrix rightHandSide) {
    	int leftRows = numRows();
    	int rightRows = rightHandSide.numRows();
    	int leftCols = numCols();
    	int rightCols = rightHandSide.numCols();
        if (rightRows != leftCols)
        	throw new IllegalArgumentException("Violation of precondition: dimensions do not match properly");
        int[][] product = new int[leftRows][rightCols]; // To be used for storing multiplied values
        for (int i=0; i<leftRows; i++) { // for the number of rows of the array on the left
        	for (int j=0; j<rightCols; j++) { // for the number of columns of the array on the right
        		for (int k=0; k<rightRows; k++) // for the number of rows of the array on the right
        			product[i][j] += values[i][k] * rightHandSide.values[k][j]; // Use += to get all of the multiplied values added together, place it in the array to be returned
        	}
        }
        values = product;
        return this;
    } 


   /**
    * Multiply all elements of this MathMatrix by factor.
    * <br>pre: none
    * <br>post: all elements in this matrix have been multiplied by factor. 
    * In other words after this method has been called getVal(r,c) = old getVal(r, c) * factor
    * for all valid r and c.
    * @param factor the value to multiply every cell in this Matrix by.
    */
    public void scale(int factor) {
    	for (int r=0; r<values.length; r++) {
        	for (int c=0; c<values[0].length; c++)
        		values[r][c] *= factor; // multiply every value by the scalar
        }
    }


    /**
     * accessor: get a transpose of this MathMatrix. 
     * This Matrix is not changed.
     * <br>pre: none
     * @return a transpose of this MathMatrix
     */
    public MathMatrix getTranspose() {
        MathMatrix transpose = new MathMatrix(numCols(), numRows(), 0);
        for (int r=0; r<transpose.values.length; r++) {
        	for (int c=0; c<transpose.values[0].length; c++)
        		transpose.values[r][c] = values[c][r]; // Use "opposite" dimensions ([r][c] vs [c][r]) to transpose the array
        }
        return transpose;
    }

    
    /**
     * override equals.
     * @return true if rightHandSide is the same size as this MathMatrix and all values in the
     * two MathMatrix objects are the same, false otherwise
     */
    public boolean equals(Object rightHandSide) {
        if(rightHandSide != null && this.getClass() == rightHandSide.getClass()){
            MathMatrix otherMatrix = (MathMatrix)rightHandSide;
            if (values.length != otherMatrix.values.length || values[0].length != otherMatrix.values[0].length) return false;
            for (int r=0; r<values.length; r++) {
            	for (int c=0; c<values[0].length; c++)
            		if (values[r][c] != otherMatrix.values[r][c]) return false;
            }
            
        }
        return true;
    }


    /**
     * override toString.
     * @return a String with all elements of this MathMatrix. 
     * Each row is on a separate line.
     * Spacing based on longest element in this Matrix.
     * Each row starts and ends with a vertical bar: '|'
     */
    public String toString(){
        StringBuilder matrix = new StringBuilder("");
        int maxIntLength = 0;
        for (int r=0; r<values.length; r++) {
        	for (int c=0; c<values[0].length; c++) {
        		String s = "" + values[r][c]; // Create a string version of the int
        		if (s.length() > maxIntLength) maxIntLength = s.length(); // If the length is longer, reassign maxIntLength
        	}
        }
        maxIntLength++; // For proper spacing
        for (int r=0; r<numRows(); r++) {
        	matrix.append("|");
        	for (int c=0; c<numCols(); c++) {
        		for (int i=0; i<maxIntLength-(""+values[r][c]).length(); i++) { // Put in the proper amount of spaces
        			matrix.append(" ");
        		}
        		matrix.append(values[r][c]);
        	}
        	matrix.append("|\n");
        }
        return matrix.toString(); // Returns the string version of the "matrix" StringBuilder
    }
    
    /**
     * Return true if this MathMatrix is upper triangular. To
     * be upper triangular all elements below the main 
     * diagonal must be 0.<br>
     * pre: this is a square matrix. numRows() == numCols()  
     * @return <tt>true</tt> if this MathMatrix is upper triangular,
     * <tt>false</tt> otherwise. 
     */
    public boolean isUpperTriangular() {
        if (numRows() != numCols())
        	throw new IllegalArgumentException("Violation of precondition: dimensions do not match properly");
        if (numRows() == 1) return true; // Every 1x1 matrix is considered to be upper triangular
        for (int r=1; r<numRows(); r++) {
        	for (int c=0; c<r; c++)
        		if (values[r][c] != 0) return false; // If any values below the middle line aren't 0, return false
        		
        }
        return true;
    }
    
    // method to ensure mat is rectangular
    // pre: mat != null
    public static boolean rectangularMatrix(int[][] mat) {
        if(mat == null)
            throw new IllegalArgumentException("Violation of precondition: "
                    + " Parameter mat may not be null");
        boolean isRectangular = true;
        int row = 1;
        final int COLUMNS = mat[0].length;
        while( isRectangular && row < mat.length ) {
            isRectangular = (mat[row].length == COLUMNS);
            row++;
        }
        return isRectangular;
    }
}
