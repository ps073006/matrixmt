

import static org.evosuite.shaded.org.mockito.Matchers.anyDouble;
import static org.evosuite.shaded.org.mockito.Matchers.anyInt;
import static org.evosuite.shaded.org.mockito.Mockito.doReturn;
import static org.evosuite.shaded.org.mockito.Mockito.mock;
import static org.la4j.M.a;
import static org.la4j.M.m;
import static org.la4j.M.ms;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

import org.evosuite.runtime.ViolatedAssumptionAnswer;
import org.evosuite.runtime.mock.java.util.MockRandom;
import org.la4j.Matrices;
import org.la4j.Matrix;
import org.la4j.Vector;
import org.la4j.linear.ForwardBackSubstitutionSolver;
import org.la4j.linear.LeastSquaresSolver;
import org.la4j.linear.SquareRootSolver;
import org.la4j.matrix.ColumnMajorSparseMatrix;
import org.la4j.matrix.DenseMatrix;
import org.la4j.operation.ooplace.OoPlaceMatrixByItsTransposeMultiplication;
import org.la4j.vector.DenseVector;
import org.la4j.vector.functor.VectorFunction;
import org.la4j.vector.sparse.CompressedVector;


public class FrameMethod {
	
	public static Random rand = new Random();
	public static int Const_INT = getInt();
	public static final	int MAX = 100;
	public static long permSeed = 12345; 
	
	//common methods
	public static int getInt(){
		return rand.nextInt(MAX) + 1;
	}
	public static double getDouble() {
        return rand.nextDouble() * getInt() + 1;
    }
	public static int[] getIntArray(){
		int size = rand.nextInt(8) + 4;
		
		return getIntArray(size);
	}
	public static int[] getIntArray(int size){	
		return rand.ints(size, 1, MAX).toArray();
	}
	public static double[] getDoubleArray() {
        int size = rand.nextInt(9) + 1;
        double[] arr = new double[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (rand.nextDouble() * MAX) + 1;
        }
        return arr;
    }

    public static double[] getDoubleArray(int size) {
        double[] arr = new double[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (rand.nextDouble() * MAX) + 1;
        }
        return arr;
    }
    
	//Addition MR methods
	public static int add(int Initial, int Const) {
      //  return Initial + Const_INT;
		return Initial + Const;

    }

    public static double add(double Initial, int Const) {
        //return Initial + ( (double)Const_INT);
    	return Initial + ( (double)Const);
    }

    public static long add(long Initial, int Const) {
        //return Initial + ( (long)Const_INT);
    	return Initial + ( (long)Const);
    }
	
    public static int[] add(int[] Initial, int Const){
		int[] Followup = new int[Initial.length];
		for(int i=0;i<Followup.length;i++){
			 //add Const_INT with each element
			//Followup[i]= Initial[i] + Const_INT;	
			Followup[i]= Initial[i] + Const;	
		 }
		return Followup;	
	}
	
	public static double[] add(double[] original, int Const) {
        double[] additive = new double[original.length];
        for (int i = 0; i < original.length; i++) {
          //  additive[i] = original[i] + ( (double)Const_INT);
        	additive[i] = original[i] + ( (double)Const);
        }
        return additive;
    }
	public static boolean addTest(Matrix InitTest, Matrix FollowupTest){
		if(FollowupTest.rows()>=InitTest.rows()){
			return true;
		}else if(FollowupTest.columns()>=InitTest.columns()){
			return true;
		}else{
			return false;
		}
		//return InitTest <= FollowupTest;		
	}
	
	public static boolean addTest(int InitTest, int FollowupTest){
		
		return InitTest <= FollowupTest;		
	}
	
	public static boolean addTest(double orig, double next) {
        return ((Double.compare(orig, next) <= 0) || ((next - orig) > -0.0001));
    }
	
	public static boolean addTest(int[] InitTest, int[] FollowupTest) {
        if (InitTest.length != FollowupTest.length) { return false; }
        for (int i = 0; i < InitTest.length; i++) {
            if (!addTest(InitTest[i], FollowupTest[i])) {
                return false;
            }
        }
        return true;
    }
	
	public static boolean addTest(double[] orig, double[] next) {
        if (orig.length != next.length) { return false; }
        for (int i = 0; i < orig.length; i++) {
            if (!addTest(orig[i], next[i])) {
                return false;
            }
        }
        return true;
    }
	
	//Permutation MR methods
	  public static int[] perm(int[] original, Random r) {
	        int temp, index;
	        int[] permuted = Arrays.copyOf(original, original.length);
	       // Random r = new Random();
	        for (int i = original.length - 1; i > 0; i--) {
	            index = r.nextInt(i+1);
	            temp = permuted[i];
	            permuted[i] = permuted[index];
	            permuted[index] = temp;
	        }
	        return permuted;
	    }
	  public static double[] perm(double[] original, Random r) {
	        int index;
	        double temp;
	        double[] permuted = Arrays.copyOf(original, original.length);
	      //  Random r = new Random(permSeed);
	        for (int i = original.length - 1; i > 0; i--) {
	            index = r.nextInt(i+1);
	            temp = permuted[i];
	            permuted[i] = permuted[index];
	            permuted[index] = temp;
	        }
	        return permuted;
	    }

	  public static double perm(double original) { return original; }
	  public static int perm(int original) { return original; }
	  
	  public static boolean PermTest(int o, int n) {
	        return (o == n);
	    }
	  
	  public static boolean PermTest(double o, double n) {
	        return ((Double.compare(o,n) == 0) || (Math.abs(o - n) < 0.0001));
	    }

	  public static boolean PermTest(int[] o, int[] n) {
	        if (o.length != n.length) { return false; }
	        for (int i = 0; i < o.length; i++) {
	            if (!PermTest(o[i], n[i])) {
	                return false;
	            }
	        }
	        return true;
	    }

	  public static boolean PermTest(double[] o, double[] n) {
	        if (o.length != n.length) { return false; }
	        for (int i = 0; i < o.length; i++) {
	            if (!PermTest(o[i], n[i])) {
	                return false;
	            }
	        }
	        return true;
	    }
	  
	  //Multiplication MR methods
	  public static int[] mult(int[] original, int Const) {
	        int[] multiplied = new int[original.length];
	        for (int i = 0; i < original.length; i++) {
	          //  multiplied[i] = original[i] * Const_INT;
	        	multiplied[i] = original[i] * Const;
	        }
	        return multiplied;
	    }
	  public static double[] mult(double[] original, int Const) {
	        double[] multiplied = new double[original.length];
	        for (int i = 0; i < original.length; i++) {
	           // multiplied[i] = original[i] * Const_INT;
	        	 multiplied[i] = original[i] * Const;
	        }
	        return multiplied;
	    }

	  public static double mult(double original, int Const) {
	      //  return original * (double)Const_INT;
	        return original * (double)Const;
	    }

	  public static int mult(int original, int Const) {
	        //return original * Const_INT;
		  return original * Const;
	    }
	  public static boolean multTest(Matrix o, Matrix n) { return addTest(o, n); }
	  public static boolean multTest(int o, int n) { return addTest(o, n); }
	  public static boolean multTest(double o, double n) { return addTest(o, n); }
	  public static boolean multTest(int[] o, int[] n) { return addTest(o, n); }
	  public static boolean multTest(double[] o, double[] n) { return addTest(o, n); }
	  
	//Inclusive MR methods
	  public static int[] inc(int[] original) {
	        int[] included = new int[original.length + 1];
	        for (int i = 0; i < original.length; i++) {
	            included[i] = original[i];
	        }
	        included[original.length] = getInt(); //new final element
	        return included;
	    }
	  
	  public static double[] inc(double[] original) {
	        double[] included = new double[original.length + 1];
	        for (int i = 0; i < original.length; i++) {
	            included[i] = original[i];
	        }
	        included[original.length] = getDouble(); //new final element
	        return included;
	    }

	  public static int inc(int original) { return original; }

	  public static double inc(double original) { return original; }
	    
	  public static boolean incTest(int o, int n) { return addTest(o, n); }
	  public static boolean incTest(double o, double n) { return addTest(o, n); }
	  public static boolean incTest(int[] o, int[] n) { return addTest(o, n); }
	  public static boolean incTest(double[] o, double[] n) { return addTest(o, n); }
	
	  //Exclusive MR methods 
	  public static int[] exc(int[] original) {
	        int[] excluded = new int[original.length - 1];
	        for (int i = 0; i < excluded.length; i++) { //output excludes final element
	            excluded[i] = original[i];
	        }
	        return excluded;
	    }
	  
	  public static double[] exc(double[] original) {
	        double[] excluded = new double[original.length - 1];
	        for (int i = 0; i < excluded.length; i++) { //output excludes final element
	            excluded[i] = original[i];
	        }
	        return excluded;
	    }
	  
	  public static int exc(int original) { return original; }

	  public static double exc(double original) { return original; }
	  
	  public static boolean excTest(int pre, int post) {
	        return (post <= pre);
	    }
	  public static boolean excTest(int[] pre, int[] post) {
	        if (pre.length != post.length) { return false; }
	        for (int i = 0; i < pre.length; i++) {
	            if (!excTest(pre[i], post[i])) {
	                return false;
	            }
	        }
	        return true;
	    }
	  
	  public static boolean excTest(double pre, double post) {
	        return ( (Double.compare(pre, post) >= 0) || ((pre - post) > -0.0001) );
	    }

	    public static boolean excTest(double[] pre, double[] post) {
	        if (pre.length != post.length) { return false; }
	        for (int i = 0; i < pre.length; i++) {
	            if (!excTest(pre[i], post[i])) {
	                return false;
	            }
	        }
	        return true;
	    }

	//Inversion MR methods  
	  public static int[] inv(int[] original) {
	        int[] inverse = new int[original.length];
	        for (int i = 0; i < original.length; i++) {
	            inverse[i] = 1 / original[i]; //Integer arithmetic. Problem?
	        }
	        return inverse;
	    }
	  
	  public static double[] inv(double[] original) {
	        double[] inverse = new double[original.length];
	        for (int i = 0; i < original.length; i++) {
	            inverse[i] = 1.0 / original[i];
	        }
	        return inverse;
	    }

	    public static double inv(double original) {
	        return 1.0 / original;
	    }

	    public static int inv(int original) {
	        return 1 / original;
	    }
	    
	    public static boolean invTest(int o, int n) { return excTest(o, n); }
	    public static boolean invTest(double o, double n) { return excTest(o, n); }
	    public static boolean invTest(int[] o, int[] n) { return excTest(o, n); }
	    public static boolean invTest(double[] o, double[] n) { return excTest(o, n); }
	  
	  //MR initial and followup test case
	    public static boolean testWhetherMatricesContainSameElements(Matrix matrix1,Matrix matrix2) {			
			// Test for equal columns and rows
			if (matrix1.rows() != matrix2.rows()) {
			return false;
			}
			if (matrix1.columns() != matrix2.columns()) {
			return false;
			}
			
			// Test for same elements
			double[] a = new double[matrix1.columns() * matrix1.rows()];
			int k = 0;
			for (int i = 0; i < matrix1.rows(); i++) {
			for (int j = 0; j < matrix1.columns(); j++) {
			a[k] = matrix1.get(i, j);
			k++;
			}
			}
			ArrayList<Double> b = new ArrayList<Double>();
			for (int i = 0; i < matrix2.rows(); i++) {
			for (int j = 0; j < matrix2.columns(); j++) {
			b.add(matrix2.get(i, j));
			}
			}
			for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < b.size(); j++) {
			if (a[i] == b.get(j)) {
			b.remove(j); // If match found, remove it from ArrayList to
			// decrease complexity
			break;
			}
			}
			}
			
			return b.size() == 0;
	    }
	    //Addition
	    public static boolean Addition( Matrix matrix0, Matrix matrix1, String methodName){
	    	boolean result = true;
			int Const = Const_INT;
			if(methodName == "insert"){
				//line
				matrix0 = matrix0.add((double)Const);
				matrix0 = matrix0.insert(matrix0, 0, 1, 10, 0);
					
				
				//wm
				 //double[][] doubleArray0 = new double[4][1];
				 //Matrix matrix = Matrix.from2DArray(doubleArray0);
			     //matrix0 = matrix.insert((Matrix) matrix0.add((double)Const), 0, 0);			     
				 
				 if(matrix1.sum() <= matrix0.sum()){
						 result = true; 
				  }else{
						 result = false;
				  }
			  }else if(methodName == "subtract"){
				//developer
				  matrix0 = matrix0.add((double)Const);
				  matrix0 = matrix0.subtract(10.0);
				  if(!matrix1.equals(matrix0, 0)){
						 result = true; 
				  }else{
						 result = false;
				  }
				  //line
				  /*matrix0 = matrix0.add((double)Const);
				  matrix0 = matrix0.subtract(0.0+(double)Const);				     
				  if(matrix1.equals(matrix0, 0)){
						 result = true; 
				  }else{
						 result = false;
				  }*/
			  }else if(methodName == "transformRow"){
				  //int key = add(a,2);
					//line returns false
					  VectorFunction vectorFunction0 = mock(VectorFunction.class, new ViolatedAssumptionAnswer());
					  doReturn(0.0).when(vectorFunction0).evaluate(anyInt() , anyDouble());
					  matrix0 = matrix0.add((double)Const);
					  matrix0 = matrix0.transformRow(1337, vectorFunction0);
					//branch returns true
				  	  //matrix0 = matrix0.add((double)Const);
				  	  //matrix0 = matrix0.transformRow(1, (VectorFunction) null);

					  if(matrix1.getRow(1337).equals(matrix0.getRow(1337), 0)){
							 result = true; 
					  }else{
							 result = false;
					  }
				  }else if(methodName == "transformColumn"){
					  VectorFunction vectorFunction0 = mock(VectorFunction.class, new ViolatedAssumptionAnswer());				      
					  doReturn(0.0).when(vectorFunction0).evaluate(anyInt() , anyDouble());
					  matrix0 = matrix0.add((double)Const);
					  matrix0 = matrix0.transformColumn(0, vectorFunction0);
				      //Boolean result = matrix1.equals((Object)matrix0);//false
				      
					  if(matrix1.equals(matrix0, 0)){
							 result = true; 
					  }else{
							 result = false;
					  }
				  }else if(methodName == "transpose"){
						 //all return true
					  matrix0 = matrix0.add(Const);
					  matrix0 = matrix0.transpose();
						 
					  result = matrix0.equals(matrix1.add(Const));
					  if(matrix0.equals(matrix1.add(Const))){
					    	 result = true;
					  }else{
					    	 result =  false;
					  }
				  }else if(methodName == "rotate"){
						//line,developer
					  	 matrix0 = matrix0.add(Const);
					  	 matrix0 = matrix0.rotate();
						 //wm
					     /*MockRandom mockRandom0 = new MockRandom();
					     DenseMatrix denseMatrix0 = DenseMatrix.randomSymmetric(9, mockRandom0);
					     denseMatrix0.add((double)Const);
					     Matrix matrix1 = denseMatrix0.rotate();
					     //boolean result = matrix0.equals((Object)denseMatrix0);//false*/
					      
						  if(matrix1.sum() <= matrix0.sum()){
								 result = true; 
						  }else{
								 result = false;
						  }
					  }else if(methodName == "select"){
						  //developer
						  matrix0 = matrix0.add((double)Const);
						  //int[] rowInd = new int[]{1, 3, 4};
					      //int[] colInd = new int[]{0, 1, 2, 3, 4, 5};
					      //developer2
					      //int[] rowInd = new int[]{0, 1, 2, 3, 4};
					      //int[] colInd = new int[]{0, 2, 4, 5}; // all columns
					      //devloper3
					      int[] rowInd = new int[]{1, 3, 3, 4};
					      int[] colInd = new int[]{2, 2, 4, 5, 5};
					      
					      matrix0 = matrix0.select(rowInd, colInd);
						  //branch
						  //int[] intArray0 = new int[8];
						  //matrix0 = matrix0.add((double)Const);
						  //matrix0 = matrix0.select(intArray0, intArray0);
					      //wm
					      //int[] intArray0 = new int[5];
					      //matrix0 = matrix0.add((double)Const);
					      //matrix0 = matrix0.select(intArray0, intArray0);
						     
						  if(matrix0.columns()==0 && matrix0.rows()==0 && matrix1.columns()==0 && matrix1.rows()==0){
								  result = true; 
						  }else if(!matrix1.equals(matrix0, 0)){
								 result = true; 
						  }else{
								 result = false;
						  }
					  }else if(methodName == "removeLastRow"){
						  matrix0 = matrix0.add((double)Const);
						  matrix0 = matrix0.removeLastRow();
					      
						  if(matrix0.columns()==0 && matrix0.rows()==0 && matrix1.columns()==0 && matrix1.rows()==0){
								  result = true; 
						  }else if(!matrix1.equals(matrix0, 0)){
								 result = true; 
						  }else{
								 result = false;
						  }						  
					  }else if(methodName == "removeLastColumn"){
						  matrix0 = matrix0.add((double)Const);
						  matrix0 = matrix0.removeLastColumn();
						    
						  if(matrix0.columns()==0 && matrix0.rows()==0 && matrix1.columns()==0 && matrix1.rows()==0){
								  result = true; 
						  }else if(!matrix1.equals(matrix0, 0)){
								 result = true; 
						  }else{
								 result = false;
						  }
					  }else if(methodName == "add"){
						  //double key = add(a,2);
						//developer1
						  matrix0 = matrix0.add((double)Const);
						  matrix0 = matrix0.add(10);
						//branch
						  //matrix0 = matrix0.add((double)Const);
						  //matrix0 = matrix0.add(-3300.470609235);
						  //wm
						  //matrix0 = matrix0.add((double)Const);
						  //matrix0 = matrix0.add((-205.4383));
						         
							  if(matrix0.columns()==0 && matrix0.rows()==0 && matrix1.columns()==0 && matrix1.rows()==0){
									  result = true; 
							  }else if(!matrix1.equals(matrix0, 0)){
									 result = true; 
							  }else{
									 result = false;
							  }
						  }

	    	return result;
	    }
	  public static boolean Addition(int a, int b, Matrix matrix0, String methodName){
		  boolean result = true;
		  int Const = Const_INT;
		  if(methodName == "zero"){
			  //int key = getInt(); 
			  Matrix matrix1 = Matrix.zero(a+Const,b+Const);
			  //matrix1 = matrix1.add(Const);
			  //result = FrameMethod.addTest(matrix0, matrix1);  
			  if(!matrix1.equals(matrix0, 0)){
					 result = true; 
			  }else{
					 result = false;
			  }
		  }else if(methodName == "updateColumn"){
			  //int key = add(a,2);
			  	 //line
				 Matrix matrix1 = Matrix.constant(2, 32, (-623.0));
				 matrix1 = matrix1.add(Const);
				 VectorFunction vectorFunction0 = mock(VectorFunction.class, new ViolatedAssumptionAnswer());
			     doReturn(0.0).when(vectorFunction0).evaluate(anyInt() , anyDouble());
				 matrix1.updateColumn(2, vectorFunction0);
				 
			  if(matrix1.sum()> matrix0.sum()){
				 result = true; 
			  }else{
				 result = false;
			  }
			 // result = FrameMethod.addTest(sum, sum1);  
		  }else if(methodName == "sliceTopLeft"){
			  //developer1
			  /*Matrix matrix1 = m(a(1.0, 0.0, 0.0, 0.0),
	                     a(0.0, 5.0, 0.0, 0.0),
	                     a(0.0, 0.0, 9.0, 0.0),
	                     a(0.0, 0.0, 0.0, 15.0));
			  matrix1 = matrix1.add((double)Const);
			  matrix1 = matrix1.sliceTopLeft(1, 3);*/
			//developer2
				Matrix matrix1 = m(a(1.0, 0.0, 3.0, 0.0),
	                     a(0.0, 5.0, 0.0, 7.0),
	                     a(4.0, 0.0, 9.0, 0.0));
				matrix1 = matrix1.add((double)Const);
				matrix1 = matrix1.sliceTopLeft(2, 4);
			  //wm
			  //Matrix matrix1 = Matrix.constant(1, 13, 0);
			  //matrix1 = matrix1.add((double)Const);
			  //matrix1 = matrix1.sliceTopLeft(1, 13);
			     
			  /*if(matrix0.columns()==0 && matrix0.rows()==0 && matrix1.columns()==0 && matrix1.rows()==0){
					  result = true; 
			  }else if(!matrix1.equals(matrix0, 0)){
					 result = true; 
			  }else{
					 result = false;
			  }*/
					  if(matrix1.sum() >= matrix0.sum()){
							 result = true; 
					  }else{
							 result = false;
					  }
					  
		  }else if(methodName == "setRow"){
			  //developer
			  /*Matrix matrix1 = m(a(8.0, 3.0, 1.0, 9.0),
	                     a(4.0, 9.0, 6.0, 6.0),
	                     a(9.0, 1.0, 1.0, 4.0),
	                     a(5.0, 7.0, 3.0, 0.0));
			  matrix1.setRow(1, 10.0 + 5.0);//(double) Const);*/
			  //wm
			  Matrix matrix1 = Matrix.unit(2, 2);
			  matrix1.setRow(1, 1 + (double) Const);
			     
			  /*if(matrix0.columns()==0 && matrix0.rows()==0 && matrix1.columns()==0 && matrix1.rows()==0){
					  result = true; 
			  }else if(!matrix1.equals(matrix0, 0)){
					 result = true; 
			  }else{
					 result = false;
			  }*/
			  if(matrix1.sum() > matrix0.sum()){
					 result = true; 
			  }else{
					 result = false;
			  }
		  }
		  return result;
	  }
	  public static boolean Addition( Matrix a, Matrix b, Matrix matrix0, String methodName){
		  boolean result = true;
		  int Const = Const_INT;
		  if(methodName == "insert"){			  
				a = a.add((double)Const);
				b = b.add((double)Const);
				//developer1
				//Matrix matrix1 = b.insert(a);	
				//developer2
				//Matrix matrix1 = b.insert(a).slice(0, 0, 2, 2); 
				//developer3
				//Matrix matrix1 = b.insert(a, 1, 1, 1, 1, 2, 2).slice(1, 1, 3, 3); 
				//developer4
				//Matrix matrix1 = b.insert(a, 1, 1, a.rows(), a.columns()).slice(1, 1, 3, 3);
				//developer5
				//Matrix matrix1 = b.insert(a, 0, 2, a.rows(), a.columns()).slice(0, 2, 3, 3);
				//developer6
				Matrix matrix1 = b.insert(a, 2, 0, a.rows(), a.columns()).slice(2, 0, 3, 3);
				if(matrix1.sum() >= matrix0.sum()){
						 result = true; 
				}else{
						 result = false;
				}
		  }
		  return result;
	  }
	  public static boolean Addition(Matrix matrix0, Matrix matrix1, Boolean sourceResult, String methodName){
		  boolean result = true;
		  int Const = Const_INT;
		  if(methodName == "equals"){
			  	 //int key = add(a,1);
			  //developer
			  	 /*matrix1 = matrix1.add(Const);
			     matrix0 = matrix0.add(Const);
			     //result = matrix1.equals(matrix0, Matrices.EPS);
			     result = matrix1.equals(matrix0);*/
			  	 //line
			  	 //MockRandom mockRandom0 = new MockRandom(1698);
				 //Matrix matrix2 = Matrix.random(1698, 1, mockRandom0);
				 //VectorFunction vectorFunction0 = mock(VectorFunction.class, new ViolatedAssumptionAnswer());
			     //doReturn(0.0).when(vectorFunction0).evaluate(anyInt() , anyDouble());
			     //matrix1 = matrix2.transformRow(1337, vectorFunction0);
			     /*matrix1 = matrix1.add((double)Const);
			     matrix0 = matrix0.add((double)Const);
			     result = matrix1.equals((Object)matrix0);//false*/
			     //branch
			     //matrix1 = matrix1.add((double)Const);
			     //matrix0 = matrix0.add((double)Const);
			     //result = matrix1.equals((Object)matrix0);//true
			     //wm1			   
			     /*int[] intArray0 = new int[5];
			  	 matrix0 = matrix0.add((double)Const);
			     matrix1 = matrix0.select(intArray0, intArray0);			     
			     result = matrix1.equals((Object)matrix0);//false*/
			     //wm2
			  	 matrix0 = matrix0.add((double)Const);
			     matrix1 = matrix1.add((double)Const);
			     result = matrix1.equals((Object)matrix0);//false
			     //wm3
			    // matrix1 = matrix1.add(Const);
			    // matrix0 = matrix0.add(Const);
			    // result = matrix1.equals(matrix0);//false
		      
			  if(result == sourceResult ){
					  result = true;  
			  }else{
					 result = false;
			  }
		  }
		  return result;
	  }
	  public static boolean Addition(int a, int b, Boolean sourceresult, String methodName){
		  boolean result = true;
		  int Const = Const_INT;
		  
		  return result;
	  }
		  

	//Multiplication
	  public static boolean Multiplication(Matrix matrix0, Matrix matrix1, String methodName){
		  boolean result = true;
		  int Const = Const_INT;
		  if(methodName == "insert"){
			  //int key = mult(a,2);
				//line
				 matrix0 = matrix0.multiply((double)Const);
				 matrix0 = matrix0.insert(matrix0, 0, 1, 10, 0);

			     //wm
			     //double[][] doubleArray0 = new double[4][1];
			     //Matrix matrix = Matrix.from2DArray(doubleArray0);
			     //matrix0 = matrix.insert((Matrix) matrix0.multiply((double)Const), 0, 0);			     
				 
			     
			  if(matrix1.sum() <= matrix0.sum()){
					 result = true; 
			  }else{
					 result = false;
			  }
		  }else if(methodName == "power"){
			  //developer1
			  //matrix0 = matrix0.multiply((double)Const);
			  //matrix0 = matrix0.power(2);
			  //line
			  int key = mult(1293,2);
			  //matrix0 = matrix0.multiply((double)Const);
			  matrix0 = matrix0.multiply(3.5);
			  matrix0 = matrix0.power(key);
			     
			  if(!matrix1.equals(matrix0, 0)){
					 result = true; 
			  }else{
					 result = false;
			  }
			  //experiment
			  /*if(matrix0.sum()>= matrix1.sum()){
					 result = true; 
			  }else{
					 result = false;
			  }*/
		  }else if(methodName == "subtract"){
			  //developer
			  matrix0 = matrix0.multiply((double)Const);
			  matrix0 = matrix0.subtract(10.0);
			  if(!matrix1.equals(matrix0, 0)){
					 result = true; 
			  }else{
					 result = false;
			  }
			  //line
			   /*matrix0 = matrix0.multiply((double)Const);
			   matrix0 = matrix0.subtract(0.0 *(double)Const);
		      if(matrix1.equals(matrix0, 0)){
					 result = true; 
			  }else{
					 result = false;
			  }*/
		  }else if(methodName == "transformRow"){
			  //int key = mult(a,1);
			//line returns false
			  VectorFunction vectorFunction0 = mock(VectorFunction.class, new ViolatedAssumptionAnswer());
			  doReturn(0.0).when(vectorFunction0).evaluate(anyInt() , anyDouble());
			  matrix0 = matrix0.multiply((double)Const);
			  matrix0 = matrix0.transformRow(1337, vectorFunction0);
			//branch returns true
			 /*  LinkedList<Double> linkedList0 = new LinkedList<Double>();
			   DenseVector denseVector0 = DenseVector.fromCollection(linkedList0);
			   Matrix matrix2 = denseVector0.toDiagonalMatrix();
			   Matrix matrix1 = matrix2.multiply((double)Const);
			   matrix1 = matrix1.transformRow(a, (VectorFunction) null);
			    */ 
			  if(matrix1.getRow(1337).equals(matrix0.getRow(1337), 0)){
					 result = true; 
			  }else{
					 result = false;
			  }
		  }else if(methodName == "transformColumn"){
			  VectorFunction vectorFunction0 = mock(VectorFunction.class, new ViolatedAssumptionAnswer());
		      doReturn(0.0).when(vectorFunction0).evaluate(anyInt() , anyDouble());
		      matrix0 = matrix0.multiply((double)Const);
		      matrix0 = matrix0.transformColumn(0, vectorFunction0);
		      //Boolean result = matrix1.equals((Object)matrix0);//false
		      
			  if(matrix1.equals(matrix0, 0)){
					 result = true; 
			  }else{
					 result = false;
			  }
		  }else if(methodName == "transpose"){
			  //all return true
			  matrix0 = matrix0.multiply(Const);
			  matrix0 = matrix0.transpose();				 
			  //result = matrix0.equals(matrix1.multiply(Const));
			     if(matrix0.equals(matrix1.multiply(Const))){
			    	 result = true;
			     }else{
			    	 result = false;
			     } 
		  }else if(methodName == "rotate"){
			//line,developer
			  matrix0 = matrix0.multiply(Const);
			  matrix0 = matrix0.rotate();
			 //wm
		     /*MockRandom mockRandom0 = new MockRandom();
		     DenseMatrix denseMatrix0 = DenseMatrix.randomSymmetric(9, mockRandom0);
		     denseMatrix0.multiply((double)Const);
		     Matrix matrix1 = denseMatrix0.rotate();
		     //boolean result = matrix0.equals((Object)denseMatrix0);//false*/
		      
			  if(matrix1.sum() <= matrix0.sum()){
					 result = true; 
			  }else{
					 result = false;
			  }
		  }else if(methodName == "select"){
			  //branch
			  /*int[] intArray0 = new int[8];
			  matrix0 = matrix0.multiply((double)Const);
			  matrix0 = matrix0.select(intArray0, intArray0);
			  if(matrix0.columns()==0 && matrix0.rows()==0 && matrix1.columns()==0 && matrix1.rows()==0){
				  result = true; 
			  }else if(matrix1.equals(matrix0, 0)){
					 result = true; 
			  }else{
					 result = false;
			  }*/
			  //developer1
			  matrix0 = matrix0.multiply((double)Const);
			  //int[] rowInd = new int[]{1, 3, 4};
		      //int[] colInd = new int[]{0, 1, 2, 3, 4, 5};
		      //developer2
		      //int[] rowInd = new int[]{0, 1, 2, 3, 4};
		      //int[] colInd = new int[]{0, 2, 4, 5}; // all columns
		      //devloper3
		      int[] rowInd = new int[]{1, 3, 3, 4};
		      int[] colInd = new int[]{2, 2, 4, 5, 5};
		      
		      matrix0 = matrix0.select(rowInd, colInd);
		      //wm
		      //int[] intArray0 = new int[5];
		      //matrix0 = matrix0.multiply((double)Const);
		      //matrix0 = matrix0.select(intArray0, intArray0);
			     
			  if(matrix0.columns()==0 && matrix0.rows()==0 && matrix1.columns()==0 && matrix1.rows()==0){
					  result = true; 
			  }else if(!matrix1.equals(matrix0, 0)){
					 result = true; 
			  }else{
					 result = false;
			  }
		  }else if(methodName == "removeLastRow"){
			  matrix0 = matrix0.multiply((double)Const);
			  matrix0 = matrix0.removeLastRow();
		      
			  if(matrix0.columns()==0 && matrix0.rows()==0 && matrix1.columns()==0 && matrix1.rows()==0){
					  result = true; 
			  }else if(!matrix1.equals(matrix0, 0)){
					 result = true; 
			  }else{
					 result = false;
			  }
		  }else if(methodName == "removeLastColumn"){
			  matrix0 = matrix0.multiply((double)Const);
			  matrix0 = matrix0.removeLastColumn();
			     
			  if(matrix0.columns()==0 && matrix0.rows()==0 && matrix1.columns()==0 && matrix1.rows()==0){
					  result = true; 
			  }else if(!matrix1.equals(matrix0, 0)){
					 result = true; 
			  }else{
					 result = false;
			  }
		  }else if(methodName == "add"){
			  //double key = mult(a,2);
			//developer1
			  matrix0 = matrix0.multiply((double)Const);
			  matrix0 = matrix0.add(10);
			  if(matrix0.columns()==0 && matrix0.rows()==0 && matrix1.columns()==0 && matrix1.rows()==0){
				  result = true; 
			  }else if(!matrix1.equals(matrix0, 0)){
					 result = true; 
			  }else{
					 result = false;
			  }
			//branch
			  /*matrix0 = matrix0.multiply((double)Const);
			  matrix0 = matrix0.add(-3300.470609235);
			  
			  if(matrix0.columns()==0 && matrix0.rows()==0 && matrix1.columns()==0 && matrix1.rows()==0){
				  result = true; 
			  }else if(matrix1.equals(matrix0, 0)){
					 result = true; 
			  }else{
					 result = false;
			  }*/
		      //wm
			  //matrix0 = matrix0.multiply((double)Const);
			  //matrix0 = matrix0.add((-205.4383));
			     
			  /*if(matrix0.columns()==0 && matrix0.rows()==0 && matrix1.columns()==0 && matrix1.rows()==0){
					  result = true; 
			  }else if(!matrix1.equals(matrix0, 0)){
					 result = true; 
			  }else{
					 result = false;
			  }*/
		  }
			  return result;
		  }
	  public static boolean Multiplication(int a, int b, Matrix matrix0, String methodName){
		  boolean result = true;
		  int Const = Const_INT;
		  if(methodName == "zero"){
			  //int key = getInt();
			  Matrix matrix1 = Matrix.zero(a*Const,b*Const);
			  //matrix1 = matrix1.multiply(Const);
			  //result = FrameMethod.addTest(matrix0, matrix1);  
			  if(!matrix1.equals(matrix0, 0)){
					 result = true; 
				  }else{
					  result = false;
				  }
			  //Matrix matrix1 = Matrix.zero(mult(a,Const),mult(b,Const));
			  //result = FrameMethod.multTest(matrix0, matrix1);  
		  }else if(methodName == "updateColumn"){
			  //int key = mult(a,2);
			//line
				 Matrix matrix1 = Matrix.constant(2, 32, (-623.0));
				 matrix1 = matrix1.multiply(Const);
				 VectorFunction vectorFunction0 = mock(VectorFunction.class, new ViolatedAssumptionAnswer());
			     doReturn(0.0).when(vectorFunction0).evaluate(anyInt() , anyDouble());
				 matrix1.updateColumn(2, vectorFunction0);
				 matrix1 = matrix1.multiply(-1);
				 matrix0 = matrix0.multiply(-1);
				 
			  if(matrix1.sum()> matrix0.sum()){
				 result = true; 
			  }else{
				 result = false;
			  }
			 // result = FrameMethod.addTest(sum, sum1);  
		  }else if(methodName == "sliceTopLeft"){
			//developer1
			  /*Matrix matrix1 = m(a(1.0, 0.0, 0.0, 0.0),
	                     a(0.0, 5.0, 0.0, 0.0),
	                     a(0.0, 0.0, 9.0, 0.0),
	                     a(0.0, 0.0, 0.0, 15.0));
			  matrix1 = matrix1.multiply((double)Const);
			  matrix1 = matrix1.sliceTopLeft(1, 3);*/
			//developer2
				Matrix matrix1 = m(a(1.0, 0.0, 3.0, 0.0),
	                     a(0.0, 5.0, 0.0, 7.0),
	                     a(4.0, 0.0, 9.0, 0.0));
				matrix1 = matrix1.multiply((double)Const);
				matrix1 = matrix1.sliceTopLeft(2, 4);
			  //wm
			  //Matrix matrix1 = Matrix.constant(1, 13, 0);
			  //matrix1 = matrix1.multiply((double)Const);
			  //matrix1 = matrix1.sliceTopLeft(1, 13);
			     
			  /*if(matrix0.columns()==0 && matrix0.rows()==0 && matrix1.columns()==0 && matrix1.rows()==0){
					  result = true; 
			  }else if(matrix1.equals(matrix0, 0)){
					 result = true; 
			  }else{
					 result = false;
			  }*/
			  if(matrix1.sum() >= matrix0.sum()){
					 result = true; 
			  }else{
					 result = false;
			  }
		  }else if(methodName == "setRow"){
			//developer
			 /* Matrix matrix1 = m(a(8.0, 3.0, 1.0, 9.0),
	                     a(4.0, 9.0, 6.0, 6.0),
	                     a(9.0, 1.0, 1.0, 4.0),
	                     a(5.0, 7.0, 3.0, 0.0));
			  matrix1.setRow(1, 10.0 * 2.0);//(double) Const);*/
			  //wm
			  Matrix matrix1 = Matrix.unit(2, 2);
			  matrix1.setRow(1, (double) 1 * Const);
			     
			  /*if(matrix0.columns()==0 && matrix0.rows()==0 && matrix1.columns()==0 && matrix1.rows()==0){
					  result = true; 
			  }else if(!matrix1.equals(matrix0, 0)){
					 result = true; 
			  }else{
					 result = false;
			  }*/
			  if(matrix1.sum() > matrix0.sum()){
					 result = true; 
			  }else{
					 result = false;
			  }
		  }
		  return result;
	  }
	  public static boolean Multiplication(Matrix a, Matrix b, Matrix matrix0, String methodName){
		  boolean result = true;
		  int Const = Const_INT;
		  if(methodName == "insert"){			  
				 a = a.multiply((double)Const);
				 b = b.multiply((double)Const);
				//developer1
				 //Matrix matrix1 = b.insert(a);
				//developer2
				 //Matrix matrix1 = b.insert(a).slice(0, 0, 2, 2); 
				//developer3
				 //Matrix matrix1 = b.insert(a, 1, 1, 1, 1, 2, 2).slice(1, 1, 3, 3);
				//developer4
				 //Matrix matrix1 = b.insert(a, 1, 1, a.rows(), a.columns()).slice(1, 1, 3, 3);
				//developer5
				//Matrix matrix1 = b.insert(a, 0, 2, a.rows(), a.columns()).slice(0, 2, 3, 3);
				//developer6
				Matrix matrix1 = b.insert(a, 2, 0, a.rows(), a.columns()).slice(2, 0, 3, 3);
				
				 if(matrix1.sum() >= matrix0.sum()){
					 result = true; 
				 }else{
					 result = false;
				 }
		  }
		  return result;
	  }
	  public static boolean Multiplication(Matrix matrix0,Matrix matrix1, Boolean sourceResult, String methodName){
		  boolean result = true;
		  int Const = Const_INT;
		  if(methodName == "equals"){
			  //developer
			  	 /*matrix1 = matrix1.multiply(Const);
			     matrix0 = matrix0.multiply(Const);
			     //result = matrix1.equals(matrix0, Matrices.EPS);
			     result = matrix1.equals(matrix0);*/
			  	 //int key = mult(a,1);
			  	 //line
			  	 //MockRandom mockRandom0 = new MockRandom(1698);
				 //Matrix matrix2 = Matrix.random(1698, 1, mockRandom0);
				 //VectorFunction vectorFunction0 = mock(VectorFunction.class, new ViolatedAssumptionAnswer());
			     //doReturn(0.0).when(vectorFunction0).evaluate(anyInt() , anyDouble());
			     //matrix1 = matrix2.transformRow(1337, vectorFunction0);
			    /* matrix1 = matrix1.multiply(Const);
			     matrix0 = matrix0.multiply(Const);
			     result = matrix1.equals((Object)matrix0);//false*/
			     //branch
			     //matrix1 = matrix1.multiply(Const);
			     //matrix0 = matrix0.multiply(Const);
			     //result = matrix1.equals((Object)matrix0);//true
			     //wm1			     
			     /*int[] intArray0 = new int[5];
			     matrix0 = matrix0.multiply(Const);
			     matrix1 = matrix0.select(intArray0, intArray0);			     
			     result = matrix1.equals((Object)matrix0);//false*/
			     //wm2
			     matrix0 = matrix0.multiply(Const);
			     matrix1 = matrix1.multiply(Const);
			     result = matrix1.equals((Object)matrix0);//false
			     //wm3
			     //matrix1 = matrix1.multiply(Const);
			     //matrix0 = matrix0.multiply(Const);
			     //result = matrix1.equals(matrix0);//false
		      
			  if(result == sourceResult ){
					  result = true;  
			  }else{
					 result = false;
			  }
		  }
		  return result;
	  }
	  public static boolean Multiplication(Matrix matrixinitial, Vector vectorinitial, Vector sourceresult, String methodName){
		  boolean result = true;
		  int Const = Const_INT;
		  Matrix matrix = null;
		  Vector vector = null;
		  Vector vectorFollowup = null;
		  double precision = 0.0001;
		  if(methodName == "LeastSquaresSolver"){
			  //developer
			     /*matrix = matrixinitial.multiply(2.0);
				 LeastSquaresSolver leastSquaresSolver0 = new LeastSquaresSolver(matrix);
				 vector = vectorinitial.multiply(2.0);
				 vectorFollowup = leastSquaresSolver0.solve(vector);*/
			  	//wm1 followup = source
				 matrix = matrixinitial.multiply(2.0);
				 LeastSquaresSolver leastSquaresSolver0 = new LeastSquaresSolver(matrix);
				 vector = vectorinitial.multiply(2.0);
				 vectorFollowup = leastSquaresSolver0.solve(vector);
				//wm2 followup = source
				 /*matrix = matrixinitial.multiply(2.0);
				 LeastSquaresSolver leastSquaresSolver0 = new LeastSquaresSolver(matrix);
				 vector = vectorinitial.multiply(2.0);
				 vectorFollowup = leastSquaresSolver0.solve(vector);*/
				//line followup = source
				 /*matrix = matrixinitial.multiply(2.0);
				 LeastSquaresSolver leastSquaresSolver0 = new LeastSquaresSolver(matrix);
				 vector = vectorinitial.multiply(2.0);
				 vectorFollowup = leastSquaresSolver0.solve(vector);*/
				//branch followup = source
				 /*matrix = matrixinitial.multiply(2.0);
				 LeastSquaresSolver leastSquaresSolver0 = new LeastSquaresSolver(matrix);
				 vector = vectorinitial.multiply(2.0);
				 vectorFollowup = leastSquaresSolver0.solve(vector);*/
				 
				 result = vectorFollowup.equals(sourceresult, precision);
				 
		  }else  if(methodName == "ForwardBackSubstitutionSolver"){
			//all followup = source
				 matrix = matrixinitial.multiply(2.0);
				 ForwardBackSubstitutionSolver forwardBackSubstitutionSolver0 = new ForwardBackSubstitutionSolver(matrix);
				 vector = vectorinitial.multiply(2.0);
				 vectorFollowup = forwardBackSubstitutionSolver0.solve(vector);
				 
				 result = vectorFollowup.equals(sourceresult, precision);
		  }else  if(methodName == "SquareRootSolver"){
				//all followup = source
				 matrix = matrixinitial.multiply(2.0);
				 SquareRootSolver squareRootSolver0 = new SquareRootSolver(matrix);
				 vector = vectorinitial.multiply(2.0);
				 vectorFollowup = squareRootSolver0.solve(vector);
				 
				 result = vectorFollowup.equals(sourceresult, precision);
		  }
		  return result;
	  }
	  
	  //matrix addition
	  public static boolean matrixAdd(Matrix matrix0, Matrix matrix, String methodName){
		  boolean result = true;
		  //int Const = Const_INT;
		  if(methodName == "transpose"){			     
			     //similar code for 3 test suites
			     //matrix = matrix.transpose();
			     matrix0 = matrix0.add(matrix0);
				 matrix0 = matrix0.transpose();
				 //matrix = matrix.transpose(); //transposing to make it similar
				 Boolean followupresult = matrix0.equals(matrix.add(matrix));
			     
			     if(followupresult){//if the output of matrices vary
			    	 result = true;
			     }else{
			    	 result = false;
			     }
		  }else if(methodName == "rotate"){
			//line,developer
			  matrix0 = matrix0.add(matrix0); //adding matrix
			  matrix0 = matrix0.rotate();
			 //wm
		      /*MockRandom mockRandom0 = new MockRandom();
		      DenseMatrix denseMatrix0 = DenseMatrix.randomSymmetric(9, mockRandom0);
		      Matrix matrix0 = denseMatrix0.add(matrix); //adding matrix
		      matrix0 = matrix0.rotate();
		     //boolean result = matrix0.equals((Object)denseMatrix0);//false*/
			  if(matrix.sum() <= matrix0.sum()){
					 result = true; 
			  }else{
					 result = false;
			  }
		  }
		  return result;
	  }
	  public static boolean matrixAdd(Matrix matrix0, Matrix matrix, Boolean sourceResult,  String methodName){
		  boolean result = true;
		  //int Const = Const_INT;
		 if(methodName == "equals"){
			  	 //int key = mult(a,1);
			 	 //developer
			 	 /*matrix = matrix.add(matrix); //adding matrix
			 	 matrix0 = matrix0.add(matrix0); //adding matrix
			 	 //result = matrix.equals(matrix0, Matrices.EPS);
			 	 result = matrix.equals(matrix0);*/
			  	 //line
			 	 //matrix = matrix.add(matrix); //adding matrix
			     //matrix0 = matrix0.add(matrix); //adding matrix
			     //result = matrix.equals((Object)matrix0);//false
			     //branch
			     /*LinkedList<Double> linkedList0 = new LinkedList<Double>();
			     DenseVector denseVector0 = DenseVector.fromCollection(linkedList0);
			     Matrix matrix2 = denseVector0.toDiagonalMatrix();
			     matrix2 = matrix2.add(matrix); //adding matrix
			     Matrix matrix1 = matrix2.transformRow(1, (VectorFunction) null);
			     result = matrix1.equals((Object)matrix2);//true*/
			     //wm1
			     //Matrix matrix2 = Matrix.unit(3338, 3338);
			     /*int[] intArray0 = new int[5];
			     matrix0 = matrix0.add(matrix0); //adding matrix
			     Matrix matrix1 = matrix0.select(intArray0, intArray0);			     
			     result = matrix1.equals((Object)matrix0);//false*/
			     //wm2
			 	 matrix0 = matrix0.add(matrix0);
			 	 matrix = matrix.add(matrix); //adding matrix
			     result = matrix.equals((Object)matrix0);//false
			     //wm3
			 	 //matrix = matrix.add(matrix); //adding matrix
			 	 //matrix0 = matrix0.add(matrix0); //adding matrix
			     //result = matrix.equals(matrix0);//false
		      
			  if(result == sourceResult ){
					  result = true;  
			  }else{
					 result = false;
			  }
		  } 
		 return result;
	  }
	  public static boolean matrixAdd(int a, int b, Matrix matrix, Boolean sourceResult,  String methodName){
		  boolean result = true;
		  //int Const = Const_INT;
		
		  return result;
	  }
	  public static boolean matrixAdd(Matrix matrixinitial, Vector vectorinitial, Vector sourceresult, String methodName){
		  boolean result = true;
		  Matrix matrix = null;
		  Vector vector = null;
		  Matrix matrixAdd = null;
		  Vector vectorAdd = null;
		  Vector vectorFollowup = null;
		  Matrix matrixrandom = null;
		  double precision = 0.0001;
		  if(methodName == "LeastSquaresSolver"){
			//developer
			     /*matrix = matrixinitial.add(matrixinitial);
				 LeastSquaresSolver leastSquaresSolver0 = new LeastSquaresSolver(matrix);
				 vector = vectorinitial.add(vectorinitial);
				 vectorFollowup = leastSquaresSolver0.solve(vector); */ 
			  //wm1 followup = source
				 matrix = matrixinitial.add(matrixinitial);
				 LeastSquaresSolver leastSquaresSolver0 = new LeastSquaresSolver(matrix);
				 vector = vectorinitial.add(vectorinitial);
				 vectorFollowup = leastSquaresSolver0.solve(vector);
				//wm2 followup = source
				 /*matrix = matrixinitial.add(matrixinitial);
				 LeastSquaresSolver leastSquaresSolver0 = new LeastSquaresSolver(matrix);
				 vector = vectorinitial.add(vectorinitial);
				 vectorFollowup = leastSquaresSolver0.solve(vector);*/
				//line followup = source
				 /*matrix = matrixinitial.add(matrixinitial);
				 LeastSquaresSolver leastSquaresSolver0 = new LeastSquaresSolver(matrix);
				 vector = vectorinitial.add(vectorinitial);
				 vectorFollowup = leastSquaresSolver0.solve(vector);*/
				//branch followup = source
				 /*matrix = matrixinitial.add(matrixinitial);
				 LeastSquaresSolver leastSquaresSolver0 = new LeastSquaresSolver(matrix);
				 vector = vectorinitial.add(vectorinitial);
				 vectorFollowup = leastSquaresSolver0.solve(vector);*/
				
				result = vectorFollowup.equals(sourceresult, precision);
		  }else if(methodName == "ForwardBackSubstitutionSolver"){
			//all followup = source
				 matrix = matrixinitial.add(matrixinitial);
				 ForwardBackSubstitutionSolver forwardBackSubstitutionSolver0 = new ForwardBackSubstitutionSolver(matrix);
				 vector = vectorinitial.add(vectorinitial);
				 vectorFollowup = forwardBackSubstitutionSolver0.solve(vector);
				 
				 result = vectorFollowup.equals(sourceresult, precision);
		  }else if(methodName == "SquareRootSolver"){
				//all followup = source
				 matrix = matrixinitial.add(matrixinitial);
				 SquareRootSolver squareRootSolver0 = new SquareRootSolver(matrix);
				 vector = vectorinitial.add(vectorinitial);
				 vectorFollowup = squareRootSolver0.solve(vector);
				 
				 result = vectorFollowup.equals(sourceresult, precision);
		  }
		  return result;
	  }
	  public static boolean matrixMul(Matrix matrix0, Matrix matrix, String methodName){
		  boolean result = true;
		  if(methodName == "power"){
			  //developer1
			  matrix0 = matrix0.multiply(matrix0); //multiply matrix
			  matrix0 = matrix0.power(2);
			  if(!matrix0.equals(matrix, 0)){//check equals
					 result = true; 
			  }else{
					 result = false;
			  }
			  //line
			  /*matrix0 = matrix0.multiply(matrix0); //multiply matrix
			  matrix0 = matrix0.power(1293);
			     
			  if(matrix0.equals(matrix, 0)){//check equals
					 result = true; 
			  }else{
					 result = false;
			  }*/
		  }
		  return result;
	  }
		 
	  //matrix Multiplication
	  public static boolean matrixMul(Matrix matrix0, Matrix matrix, Boolean sourceResult,  String methodName){
		  boolean result = true;
		  //int Const = Const_INT;
		 if(methodName == "equals"){
			  	 //int key = mult(a,1);
			 	//developer
			  	/*matrix = matrix.multiply(matrix.transpose()); //multiply matrix
			  	matrix0 = matrix0.multiply(matrix0.transpose()); //multiply matrix
			  	//result = matrix.equals(matrix0, Matrices.EPS);
			  	result = matrix.equals(matrix0);*/
			  	 //line
			     //matrix = matrix.multiply(matrix.transpose()); //multiply matrix
			     //matrix0 = matrix0.multiply(matrix0.transpose());
			     //result = matrix0.equals((Object)matrix);//false
			     //branch
			     /*LinkedList<Double> linkedList0 = new LinkedList<Double>();
			     DenseVector denseVector0 = DenseVector.fromCollection(linkedList0);
			     Matrix matrix2 = denseVector0.toDiagonalMatrix();
			     matrix = matrix.transpose();//for multiply
			     matrix2 = matrix2.multiply(matrix); //multiply matrix
			     Matrix matrix1 = matrix2.transformRow(1, (VectorFunction) null);
			     result = matrix1.equals((Object)matrix2);//true*/
			     //wm1
			    /* int[] intArray0 = new int[5];			     
			     Matrix matrix1 = matrix0.select(intArray0, intArray0);
			     matrix1 = matrix1.multiply(matrix);
			     result = matrix1.equals((Object)matrix0);//false*/
			     //wm2
			     matrix0 = matrix0.multiply(matrix0.transpose());
			     matrix = matrix.multiply(matrix.transpose()); //multiply matrix
			     result = matrix.equals((Object)matrix0);//false
			     //wm3
			     //matrix = matrix.multiply(matrix.transpose()); //multiply matrix
			     //matrix0 = matrix0.multiply(matrix0.transpose()); //multiply matrix
			     //result = matrix.equals(matrix0);//false
			  //rest
			  if(result == sourceResult ){
					  result = true;  
			  }else{
					 result = false;
			  }
		      //wm3
			  /*if(result != sourceResult ){
					  result = true;  
			  }else{
					 result = false;
			  }*/
		  }
		  return result;
	  }
	  //transpose
	  public static boolean transpose( Matrix matrix0, Matrix matrix,  String methodName){
		  boolean result = true;
		  if(methodName == "power"){
			  //developer1
			  matrix0 = matrix0.transpose();//for transpose
			  matrix0 = matrix0.power(2);
			  if(!matrix0.equals(matrix, 0)){//check equals
					 result = true; 
			  }else{
					 result = false;
			  }
			  //line
			  /*matrix0 = matrix0.transpose();//for transpose
			  matrix0 = matrix0.power(1293);
 
			  if(matrix0.equals(matrix, 0)){//check equals
					 result = true; 
			  }else{
					 result = false;
			  }*/
		  }else if(methodName == "subtract"){
			 //developer
			  matrix0 = matrix0.transpose();//for transpose
			  matrix0 = matrix0.subtract(10.0);
			  //line
			  //matrix0 = matrix0.transpose();//for transpose
			  //matrix0 = matrix0.subtract(0.0);
			  
		      if(matrix0.equals(matrix.transpose(), 0)){
					 result = true; 
			  }else{
					 result = false;
			  }
		  }else if(methodName == "multiply"){
			//developer1
			 /* matrix0 = matrix0.transpose();//for transpose
			  matrix0 = matrix0.multiply(2.0);
			  if(matrix.columns()==0 && matrix.rows()==0 && matrix0.columns()==0 && matrix0.rows()==0){
				  result = true; 
			  }else if(matrix0.equals(matrix.transpose(), 0)){
				 result = true; 
			  }else{
				 result = false;
			  }*/
			  //developer2
			 /* matrix0 = matrix0.transpose();//for transpose
			  for (Matrix b: ms(a(4.0, 9.0, 0.0, 3.0),
                      a(6.0, 7.0, 7.0, 6.0),
                      a(9.0, 4.0, 3.0, 3.0),
                      a(4.0, 4.0, 1.0, 6.0))) {

				
				  matrix0 = matrix0.multiply(b);
				}
			  if(matrix.columns()==0 && matrix.rows()==0 && matrix0.columns()==0 && matrix0.rows()==0){
				  result = true; 
			  }else if(!matrix0.equals(matrix.transpose(), 0)){
				 result = true; 
			  }else{
				 result = false;
			  }*/
			  //matrix0 = matrix0.multiply(2.0);
			  //line
			  //matrix0 = matrix0.transpose();//for transpose
			  //matrix0 = matrix0.multiply((double) 85);
			   //branch
			   matrix0 = matrix0.transpose();//for transpose
			   matrix0 = matrix0.multiply((double) 3405);
			   //only for branch and line
			  if(matrix.columns()==0 && matrix.rows()==0 && matrix0.columns()==0 && matrix0.rows()==0){
					  result = true; 
			  }else if(matrix0.equals(matrix.transpose(), 0)){
					 result = true; 
			  }else{
					 result = false;
			  }
			  //wm
			   //matrix0 = matrix0.transpose();//for transpose
			   //matrix0 = matrix0.multiply((-119.9752908860286));
			   
			   //only for wm  
			  /*if(matrix.columns()==0 && matrix.rows()==0 && matrix0.columns()==0 && matrix0.rows()==0){
					  result = true; 
			  }else if(matrix0.equals(matrix, 0)){
					 result = true; 
			  }else{
					 result = false;
			  }*/
		  }else if(methodName == "add"){
			  //int key = mult(a,2);
			  //developer1
			  matrix0 = matrix0.transpose();//for transpose
			  matrix0 = matrix0.add(10);
			//branch
			  //matrix0 = matrix0.transpose();//for transpose
			  //matrix0 = matrix0.add(-3300.470609235);
		      //wm
			   //matrix0 = matrix0.transpose();//for transpose
			   //matrix0 = matrix0.add((-205.4383));
			     
			  if(matrix.columns()==0 && matrix.rows()==0 && matrix0.columns()==0 && matrix0.rows()==0){
					  result = true; 
			  }else if(matrix0.equals(matrix.transpose(), 0)){
					 result = true; 
			  }else{
					 result = false;
			  }
		  }  
		  return result;
	  }
	  public static boolean transpose(Matrix matrix0, Matrix matrix, Boolean sourceResult,  String methodName){
		  boolean result = true;
		  //int Const = Const_INT;
		 if(methodName == "equals"){
			  	 //int key = mult(a,1);
			 	//developer
			 	/*matrix0 = matrix0.transpose();//for transpose
			 	matrix = matrix.transpose();//for transpose
			 	//result = matrix.equals(matrix0, Matrices.EPS);
			 	result = matrix.equals(matrix0);*/
			  	 //line
			 	 //matrix0 = matrix0.transpose();//for transpose
			 	 //matrix = matrix.transpose();//for transpose
			     //result = matrix0.equals((Object)matrix);//false
			     //branch
			     /*LinkedList<Double> linkedList0 = new LinkedList<Double>();
			     DenseVector denseVector0 = DenseVector.fromCollection(linkedList0);
			     Matrix matrix2 = denseVector0.toDiagonalMatrix();
			     Matrix matrix1 = matrix2.transformRow(1, (VectorFunction) null);
			     matrix2 = matrix2.transpose();//for transpose
			     matrix1 = matrix1.transpose();//for transpose
			     result = matrix1.equals((Object)matrix2);//true*/
			     //wm1
			     /*int[] intArray0 = new int[5];
			     matrix0 = matrix0.transpose();//for transpose
			     Matrix matrix1 = matrix0.select(intArray0, intArray0);		     
			     result = matrix1.equals((Object)matrix0);//false*/
			     //wm2
			 	 matrix0 = matrix0.transpose();//for transpose
			     matrix = matrix.transpose();//for transpose
			     result = matrix.equals((Object)matrix0);//false
			     //wm3
			 	 //matrix0 = matrix0.transpose();//for transpose
			 	 //matrix = matrix.transpose();//for transpose
			     //result = matrix0.equals(matrix);//false
		      
			  if(result == sourceResult ){
					  result = true;  
			  }else{
					 result = false;
			  }
		  }
		 return result;
	  }
	  public static boolean transpose(int a, Matrix matrix, String methodName){
		  boolean result = true;
		  //int Const = Const_INT;
		if(methodName == "rank"){
			  //Matrix matrix0 = Matrix.constant(0, 0, (-1.0));
			  matrix = matrix.transpose();//for transpose
			  int int0 = matrix.rank();   
			  
			  if(int0 == a){
					  result = true; 
			  }else{
					 result = false;
			  }
		  }
		  return result;
	  }
	  public static boolean transpose(double a, int b, Matrix matrix, Boolean sourceResult,  String methodName){
		  boolean result = true;
		  if(methodName == "determinant"){
			  matrix = matrix.transpose();//for transpose
			  double double0 = matrix.determinant();
			  
			    if((double0 - a) > -0.00001){
				  result = true;
			  }else{
				  result = false;
			  }
		  }
		  return result;
	  }
	  public static boolean transpose(Matrix matrixinitial, Vector vectorinitial, Vector sourceresult, String methodName){
		  boolean result = true;
		  Matrix matrix = null;
		  Vector vector = null;
		  Matrix matrixAdd = null;
		  Vector vectorAdd = null;
		  Vector vectorFollowup = null;
		  Matrix matrixrandom = null;
		  double precision = 0.0001;
		  if(methodName == "LeastSquaresSolver"){
			  //developer
			     /*matrix = matrixinitial.transpose();
				 matrixAdd = matrix.multiply(matrixinitial);
				 LeastSquaresSolver leastSquaresSolver0 = new LeastSquaresSolver(matrixAdd);
				 vector = matrix.multiply(vectorinitial);
				 vectorFollowup = leastSquaresSolver0.solve(vector);*/
			//wm1 followup = source
				 matrix = matrixinitial.transpose();
				 matrix = matrix.multiply(matrixinitial);
				 LeastSquaresSolver leastSquaresSolver0 = new LeastSquaresSolver(matrix);
				 vector = matrix.multiply(vectorinitial);
				 vectorFollowup = (CompressedVector)leastSquaresSolver0.solve(vector);
				//wm2 throws exception not applicable
				 /*matrix = matrixinitial.transpose();
				 matrixinitial = matrix.multiply(matrixinitial);
				 LeastSquaresSolver leastSquaresSolver0 = new LeastSquaresSolver(matrixinitial);
				 vector = matrix.multiply(vectorinitial);
				 vectorFollowup = leastSquaresSolver0.solve(vector);*/
				//line followup = source
				 /*matrix = matrixinitial.transpose();
				 matrixAdd = matrix.multiply(matrixinitial);
				 LeastSquaresSolver leastSquaresSolver0 = new LeastSquaresSolver(matrixAdd);
				 vector = matrix.multiply(vectorinitial);
				 vectorFollowup = leastSquaresSolver0.solve(vector);*/
				//branch followup == source
				 /*matrix = matrixinitial.transpose();
				 matrixAdd = matrix.multiply(matrixinitial);
				 LeastSquaresSolver leastSquaresSolver0 = new LeastSquaresSolver(matrixAdd);
				 vector = matrix.multiply(vectorinitial);
				 vectorFollowup = leastSquaresSolver0.solve(vector);*/
				
				result = vectorFollowup.equals(sourceresult, precision);
		  }else  if(methodName == "ForwardBackSubstitutionSolver"){
			//all followup = source
				 matrix = matrixinitial.transpose();
				 matrixAdd = matrix.multiply(matrixinitial);
				 ForwardBackSubstitutionSolver forwardBackSubstitutionSolver0 = new ForwardBackSubstitutionSolver(matrixAdd);
				 vector = matrix.multiply(vectorinitial);
				 vectorFollowup = forwardBackSubstitutionSolver0.solve(vector);
				 
				 result = vectorFollowup.equals(sourceresult, precision);
		  }else  if(methodName == "SquareRootSolver"){
				//all followup = source
				 matrix = matrixinitial.transpose();
				 matrixAdd = matrix.multiply(matrixinitial);
				 SquareRootSolver squareRootSolver0 = new SquareRootSolver(matrixAdd);
				 vector = matrix.multiply(vectorinitial);
				 vectorFollowup = squareRootSolver0.solve(vector);
				 
				 result = vectorFollowup.equals(sourceresult, precision);
		  }
		  return result;
	  }
	  //permutation of rows
	  public static boolean permuteRow(Matrix matrix0, Matrix matrix, String methodName){
		  boolean result = true;
		  if(methodName == "insert"){
				//line
				matrix0.swapRows(2, 3);
				matrix0 = matrix0.insert(matrix0, 0, 1, 10, 0);

				//wm
			  	/*double[][] doubleArray0 = new double[4][1];
			  	Matrix matrix1 = Matrix.from2DArray(doubleArray0);
			    matrix0.swapRows(2, 3);
				matrix0 = matrix1.insert((Matrix) matrix0, 0, 0);*/

				     if(matrix.sum() == matrix0.sum()){//if the output of matrices vary
				    	 result = true;
				     }else{
				    	 result = false;
				     }
			  }else if(methodName == "transpose"){
				     matrix0.swapRows(0, 1);
				     matrix0 = matrix0.transpose(); //transposing to make it similar
				     matrix.swapColumns(0, 1);
				     Boolean followupresult = matrix0.equals(matrix);
				     
				     if(followupresult){//if the output of matrices vary
				    	 result = true;
				     }else{
				    	 result = false;
				     }
			  }else if(methodName == "shuffle"){
				  //developer1
				  matrix0.swapRows(1, 2);
				  matrix0 = matrix0.shuffle();
				  if(testWhetherMatricesContainSameElements(matrix0, matrix)){//if the output of matrices vary
				    	 result = true;
				     }else{
				    	 result = false;
				     }
				  //line
				 /* matrix0.swapRows(1, 2);
				  matrix0 = matrix0.shuffle();
				  //matrix.swapRows(1, 2);
				     if(matrix0.sum() == matrix.sum() ){//if the output of matrices vary
				    	 result = true;
				     }else{
				    	 result = false;
				     }*/
			  }if(methodName == "rotate"){
				//line,developer
				  matrix0.swapRows(0, 1);
				  matrix0 = matrix0.rotate();
			     //boolean result = matrix0.equals((Object)denseMatrix0);//true*/
				 //wm
			      /*MockRandom mockRandom0 = new MockRandom();
			      DenseMatrix denseMatrix0 = DenseMatrix.randomSymmetric(9, mockRandom0);
			      denseMatrix0.swapRows(2, 3);
			      Matrix matrix0 = denseMatrix0.rotate();
			     //boolean result = matrix0.equals((Object)denseMatrix0);//false*/
				  if(matrix0.sum() == matrix.sum()){//if the output of matrices vary
				    	 result = true;
				  }else{
				    	 result = false;
				  }
			  } 
		  return result;
	  }
	  public static boolean permuteRow(Matrix a, Matrix b, Matrix matrix, String methodName){
		  boolean result = true;
		  //int Const = Const_INT;
		  if(methodName == "insert"){
				//developer1
				//a.swapRows(1, 2);
				//b.swapRows(1, 2);
				//Matrix matrix0 = b.insert(a);
				//developer2
				//a.swapRows(0, 1);
				//b.swapRows(0, 1);
				//Matrix matrix0 = b.insert(a).slice(0, 0, 2, 2);
				//developer3
				//a.swapRows(1, 2);
				//b.swapRows(1, 2);
				//Matrix matrix0 = b.insert(a, 1, 1, 1, 1, 2, 2).slice(1, 1, 3, 3);
				//developer4
				//a.swapRows(0, 1);
				//b.swapRows(0, 1);
				//Matrix matrix0 = b.insert(a, 1, 1, a.rows(), a.columns()).slice(1, 1, 3, 3);
				//developer5
				a.swapRows(0, 1);
				b.swapRows(0, 1);
				Matrix matrix0 = b.insert(a, 0, 2, a.rows(), a.columns()).slice(0, 2, 3, 3);
				
				if(matrix.sum() == matrix0.sum()){//if the output of matrices vary
				    	 result = true;
				}else{
				    	 result = false;
				}
			  }
			  
		  return result;
	  }
	  public static boolean permuteRow(int a, int b, Matrix matrix, Random rand, String methodName){
		  boolean result = true;
		  return result;
	  }
	  public static boolean permuteRow(Matrix matrixinitial, Vector vectorinitial, Vector sourceresult, String methodName){
		  boolean result = true;
		  Matrix matrix = null;
		  Vector vector = null;
		  Matrix matrixAdd = null;
		  Vector vectorAdd = null;
		  Vector vectorFollowup = null;
		  Matrix matrixrandom = null;
		  double precision = 0.0001;
		  if(methodName == "LeastSquaresSolver"){
			  //developer
			     /*matrixinitial.swapRows(0, 1);
			     LeastSquaresSolver leastSquaresSolver0 = new LeastSquaresSolver(matrixinitial);
			     vectorinitial.swapElements(0, 1);
				 vectorFollowup = leastSquaresSolver0.solve(vectorinitial);*/
			  	//wm1 followup = source
			     matrixinitial.swapRows(0, 1);
			     LeastSquaresSolver leastSquaresSolver0 = new LeastSquaresSolver(matrixinitial);
				 //value same no need to swap
				 vectorFollowup = (CompressedVector)leastSquaresSolver0.solve(vectorinitial);
				//wm2 followup = source
			  /*matrixinitial.swapRows(1, 2);
			  LeastSquaresSolver leastSquaresSolver0 = new LeastSquaresSolver(matrixinitial);
			  vectorinitial.swapElements(1, 2);
			  vectorFollowup = leastSquaresSolver0.solve(vectorinitial);*/
				//line followup == source
			     /*matrixinitial.swapRows(1, 2);
			     LeastSquaresSolver leastSquaresSolver0 = new LeastSquaresSolver(matrixinitial);
			     vectorinitial.swapElements(1, 2);
				 vectorFollowup = leastSquaresSolver0.solve(vectorinitial);*/
				//branch followup == source
			  	/*matrixinitial.swapRows(1, 2);
			  	LeastSquaresSolver leastSquaresSolver0 = new LeastSquaresSolver(matrixinitial);
			  	vectorinitial.swapElements(1, 2);
				vectorFollowup = leastSquaresSolver0.solve(vectorinitial);*/
				
				result = vectorFollowup.equals(sourceresult, precision);
		  }else if(methodName == "ForwardBackSubstitutionSolver"){
			  //developer
			     /*matrixinitial.swapRows(0, 1);
			     ForwardBackSubstitutionSolver forwardBackSubstitutionSolver0 = new ForwardBackSubstitutionSolver(matrixinitial);
			     vectorinitial.swapElements(0, 1);
				 vectorFollowup = forwardBackSubstitutionSolver0.solve(vectorinitial);*/
			//line,branch,wm1,wm2,wm4 followup == source
			     matrixinitial.swapRows(1, 2);
			     ForwardBackSubstitutionSolver forwardBackSubstitutionSolver0 = new ForwardBackSubstitutionSolver(matrixinitial);
			     vectorinitial.swapElements(1, 2);
				 vectorFollowup = forwardBackSubstitutionSolver0.solve(vectorinitial);
			
		  }else if(methodName == "SquareRootSolver"){
			//developer
			     matrixinitial.swapRows(0, 1);
			     SquareRootSolver squareRootSolver0 = new SquareRootSolver(matrixinitial);
			     vectorinitial.swapElements(0, 1);
				 vectorFollowup = squareRootSolver0.solve(vectorinitial);
				//all followup == source
			     /*matrixinitial.swapRows(1, 2);
			     SquareRootSolver squareRootSolver0 = new SquareRootSolver(matrixinitial);
			     vectorinitial.swapElements(1, 2);
				 vectorFollowup = squareRootSolver0.solve(vectorinitial);*/
			
		  }
		  return result;
	  }
	  //permutation of elements
	  public static boolean permuteElement(Matrix matrix0, Matrix matrix, String methodName){
		  boolean result = true;
		  if(methodName == "insert"){
				//line
			     if(matrix0.rows()>=1 || matrix0.columns()>1){
			    	 int i = 5;
			    	 Double v1 = matrix0.get(i, 0);
			    	 Double v2 = matrix0.get(i+1, 0);
			    	 matrix0.set(i, 0, v2);
			    	 matrix0.set(i+1,0, v1);
			     }
			     matrix0 = matrix0.insert(matrix0, 0, 1, 10, 0);
			  //wm
			  /*double[][] doubleArray0 = new double[4][1];
			  Matrix matrix1 = Matrix.from2DArray(doubleArray0);
			  if(matrix1.rows()>=1 && matrix1.columns()>1){
			    	 int i = 2;
			    	 Double v1 = matrix1.get(0, i);
			    	 Double v2 = matrix1.get(0, i+1);
			    	 matrix1.set(0, i, v2);
			    	 matrix1.set(0,i+1, v1);
			     }
			   matrix0 = matrix1.insert((Matrix) matrix0, 0, 0);*/
			     
			     if(matrix0.sum() == matrix.sum()){//if the output of matrices vary
			    	 result = true;
			     }else{
			    	 result = false;
			     }
		  }else if(methodName == "transpose"){
			   //similar code for 3 test suites
			    	 Double v1 = matrix0.get(0, 1);
			    	 Double v2 = matrix0.get(0, 2);
			    	 matrix0.set(0, 1, v2);
			    	 matrix0.set(0,2, v1);
			     matrix0 = matrix0.transpose(); //transposing to make it similar 
			      v1 = matrix.get(1, 0);
		    	  v2 = matrix.get(2, 0);
		    	  matrix.set(1, 0, v2);
		    	  matrix.set(2,0, v1);
			     Boolean followupresult = matrix0.equals(matrix);
			     
			     if(followupresult){//if the output of matrices vary
			    	 result = true;
			     }else{
			    	 result = false;
			     }
		  }else if(methodName == "shuffle"){
			  //developer1
			  Double v1 = matrix0.get(0, 1);
			  Double v2 = matrix0.get(1, 1);
			  matrix0.set(0, 1, v2);
			  matrix0.set(1,1, v1);
			  matrix0 = matrix0.shuffle();
			  if(testWhetherMatricesContainSameElements(matrix0, matrix)){//if the output of matrices vary
			    	 result = true;
			     }else{
			    	 result = false;
			     }
			  //line
			  /*Double v1 = matrix0.get(0, 1);
			  Double v2 = matrix0.get(0, 2);
			  matrix0.set(0, 1, v2);
			  matrix0.set(0,2, v1);
			  matrix0 = matrix0.shuffle();
			  //Boolean followupresult = matrix0.equals((Object)matrix);
			     if(matrix0.sum() == matrix.sum()){//if the output of matrices vary
			    	 result = true;
			     }else{
			    	 result = false;
			     }*/
		  }else if(methodName == "rotate"){
			//line,developer
			  Double v1 = matrix0.get(0, 0);
			  Double v2 = matrix0.get(0, 1);
			  matrix0.set(0, 0, v2);
			  matrix0.set(0,1, v1);
			  matrix0 = matrix0.rotate();
		     //boolean result = matrix0.equals((Object)denseMatrix0);//true*/
			 //wm
		      /*MockRandom mockRandom0 = new MockRandom();
		      DenseMatrix denseMatrix0 = DenseMatrix.randomSymmetric(9, mockRandom0);
		      if(denseMatrix0.rows()>=1 && denseMatrix0.columns()>1){
			    	 int i = b/2;
			    	 Double v1 = denseMatrix0.get(0, i);
			    	 Double v2 = denseMatrix0.get(0, i+1);
			    	 denseMatrix0.set(0, i, v2);
			    	 denseMatrix0.set(0,i+1, v1);
			     }
		      Matrix matrix0 = denseMatrix0.rotate();
		     //boolean result = matrix0.equals((Object)denseMatrix0);//false*/

			     if(matrix0.sum() == matrix.sum()){//if the output of matrices vary
			    	 result = true;
			     }else{
			    	 result = false;
			     } 
		  } 
		  return result;
	  }
	  public static boolean permuteElement(Matrix a, Matrix b, Matrix matrix, String methodName){
		  boolean result = true;
		  //int Const = Const_INT;
		  if(methodName == "insert"){
				//developer1
			    /* if(a.rows()>=1 || a.columns()>1){
			    	 int i = 1;
			    	 Double v1 = a.get(i, 0);
			    	 Double v2 = a.get(i+1, 0);
			    	 a.set(i, 0, v2);
			    	 a.set(i+1,0, v1);
			     }
			     if(b.rows()>=1 || b.columns()>1){
			    	 int i = 1;
			    	 Double v1 = b.get(i, 0);
			    	 Double v2 = b.get(i+1, 0);
			    	 b.set(i, 0, v2);
			    	 b.set(i+1,0, v1);
			     }
			     Matrix matrix0 = b.insert(a);*/
			   //developer2
			     /*if(a.rows()>=1 || a.columns()>1){
			    	 int i = 0;
			    	 Double v1 = a.get(i, 0);
			    	 Double v2 = a.get(i+1, 0);
			    	 a.set(i, 0, v2);
			    	 a.set(i+1,0, v1);
			     }
			     if(b.rows()>=1 || b.columns()>1){
			    	 int i = 0;
			    	 Double v1 = b.get(i, 0);
			    	 Double v2 = b.get(i+1, 0);
			    	 b.set(i, 0, v2);
			    	 b.set(i+1,0, v1);
			     }
			     Matrix matrix0 = b.insert(a).slice(0, 0, 2, 2);*/
			   //developer3
			     /*if(a.rows()>=1 || a.columns()>1){
			    	 int i = 1;
			    	 Double v1 = a.get(i, 0);
			    	 Double v2 = a.get(i+1, 0);
			    	 a.set(i, 0, v2);
			    	 a.set(i+1,0, v1);
			     }
			     if(b.rows()>=1 || b.columns()>1){
			    	 int i = 1;
			    	 Double v1 = b.get(i, 0);
			    	 Double v2 = b.get(i+1, 0);
			    	 b.set(i, 0, v2);
			    	 b.set(i+1,0, v1);
			     }
			     Matrix matrix0 = b.insert(a, 1, 1, 1, 1, 2, 2).slice(1, 1, 3, 3);*/
			   //developer4
			    /* if(a.rows()>=1 || a.columns()>1){
			    	 int i = 0;
			    	 Double v1 = a.get(i, 0);
			    	 Double v2 = a.get(i+1, 0);
			    	 a.set(i, 0, v2);
			    	 a.set(i+1,0, v1);
			     }
			     if(b.rows()>=1 || b.columns()>1){
			    	 int i = 0;
			    	 Double v1 = b.get(i, 0);
			    	 Double v2 = b.get(i+1, 0);
			    	 b.set(i, 0, v2);
			    	 b.set(i+1,0, v1);
			     }
			     Matrix matrix0 = b.insert(a, 1, 1, a.rows(), a.columns()).slice(1, 1, 3, 3);*/
			   //developer5
			     /*if(a.rows()>=1 || a.columns()>1){
			    	 int i = 0;
			    	 Double v1 = a.get(i, 0);
			    	 Double v2 = a.get(i+1, 0);
			    	 a.set(i, 0, v2);
			    	 a.set(i+1,0, v1);
			     }
			     if(b.rows()>=1 || b.columns()>1){
			    	 int i = 0;
			    	 Double v1 = b.get(i, 0);
			    	 Double v2 = b.get(i+1, 0);
			    	 b.set(i, 0, v2);
			    	 b.set(i+1,0, v1);
			     }
			     Matrix matrix0 = b.insert(a, 0, 2, a.rows(), a.columns()).slice(0, 2, 3, 3);*/
			   //developer6
			     if(a.rows()>=1 || a.columns()>1){
			    	 int i = 0;
			    	 Double v1 = a.get(i, 0);
			    	 Double v2 = a.get(i+1, 0);
			    	 a.set(i, 0, v2);
			    	 a.set(i+1,0, v1);
			     }
			     if(b.rows()>=1 || b.columns()>1){
			    	 int i = 0;
			    	 Double v1 = b.get(i, 0);
			    	 Double v2 = b.get(i+1, 0);
			    	 b.set(i, 0, v2);
			    	 b.set(i+1,0, v1);
			     }
			     Matrix matrix0 = b.insert(a, 2, 0, a.rows(), a.columns()).slice(2, 0, 3, 3);
			     
			     if(matrix0.sum() == matrix.sum()){//if the output of matrices vary
			    	 result = true;
			     }else{
			    	 result = false;
			     }
		  }
		  return result;
	  }
	  public static boolean permuteElement(int a, int b, Matrix matrix, Random rand, String methodName){
		  boolean result = true;
		  
		  return result;
	  }
	  //permutation of columns
	  public static boolean permuteColumn(Matrix matrix0, Matrix matrix, String methodName){
		  boolean result = true;
		  if(methodName == "insert"){
			//line
			  matrix0.swapColumns(2, 3);
			  matrix0 = matrix0.insert(matrix0, 0, 1, 1698, 0);
			  //wm
			  /*double[][] doubleArray0 = new double[4][1];
			  Matrix matrix1 = Matrix.from2DArray(doubleArray0);
			  matrix1.swapColumns(2, 3);
			  matrix0 = matrix1.insert((Matrix) matrix0, 0, 0);*/
			    
			     if(matrix0.sum() == matrix.sum()){//if the output of matrices vary
			    	 result = true;
			     }else{
			    	 result = false;
			     }
		  }else if(methodName == "transpose"){
			     //similar code for 3 test suites
			     matrix0.swapColumns(1,2);
			     matrix0 = matrix0.transpose(); //transposing to make it similar 
			     matrix.swapRows(1,2);
			     Boolean followupresult = matrix0.equals(matrix);
			     
			     if(followupresult){//if the output of matrices vary
			    	 result = true;
			     }else{
			    	 result = false;
			     }
		  }else if(methodName == "shuffle"){
			  //developer1
			  matrix0.swapColumns(0, 1);
			  matrix0 = matrix0.shuffle();
			  if(testWhetherMatricesContainSameElements(matrix0, matrix)){//if the output of matrices vary
			    	 result = true;
			     }else{
			    	 result = false;
			     }
			  //line
			  /*matrix0.swapColumns(1, 2);
			  matrix0 = matrix0.shuffle();
			  //matrix.swapColumns(1, 2);
			  //Boolean followupresult = matrix0.equals((Object)matrix);
			     if(matrix0.sum()==matrix.sum()){//if the output of matrices vary
			    	 result = true;
			     }else{
			    	 result = false;
			     }*/
		  }else if(methodName == "rotate"){
			//line,developer
			  matrix0.swapColumns(0, 1);
			  matrix0 = matrix0.rotate();
		     //boolean result = matrix0.equals((Object)denseMatrix0);//true*/
			 //wm
		      /*MockRandom mockRandom0 = new MockRandom();
		      DenseMatrix denseMatrix0 = DenseMatrix.randomSymmetric(9, mockRandom0);
		      denseMatrix0.swapColumns(2, 3);
		      Matrix matrix0 = denseMatrix0.rotate();
		     //boolean result = matrix0.equals((Object)denseMatrix0);//false*/
			  if(matrix.sum() == matrix0.sum()){//for matrix elements value=0
			    	 result =  true;
			     } else{
			    	 result = false;
			     } 
		  } 
		 
		  return result;
	  }
	  public static boolean permuteColumn(Matrix a, Matrix b, Matrix matrix, String methodName){
		  boolean result = true;
		  //int Const = Const_INT;
		  if(methodName == "insert"){
				//developer1
				  //a.swapColumns(1, 2);
				  //b.swapColumns(1, 2);
				  //Matrix matrix0 = b.insert(a);
				//developer2
				  //a.swapColumns(0, 1);
				  //b.swapColumns(0, 1);
				  //Matrix matrix0 = b.insert(a).slice(0, 0, 2, 2);
				//developer3
				  //a.swapColumns(1, 2);
				  //b.swapColumns(1, 2);
				  //Matrix matrix0 = b.insert(a, 1, 1, 1, 1, 2, 2).slice(1, 1, 3, 3); 
				//developer4
				  //a.swapColumns(0, 1);
				  //b.swapColumns(0, 1);
				  //Matrix matrix0 = b.insert(a, 1, 1, a.rows(), a.columns()).slice(1, 1, 3, 3);
				//developer6
				  a.swapColumns(0, 1);
				  b.swapColumns(0, 1);
				  Matrix matrix0 = b.insert(a, 2, 0, a.rows(), a.columns()).slice(2, 0, 3, 3);
				  
				  if(matrix0.sum() == matrix.sum()){//if the output of matrices vary
				    	 result = true;
				     
				  }else{
				    	 result = false;
				     
				  }
		  }
		  return result;
	  }
	  public static boolean permuteColumn(int a, int b, Matrix matrix, Random rand, String methodName){
		  boolean result = true;
		  
		  return result;
	  }
	  //addition with Identity matrix
	  public static boolean AdditionWithIdentity(Matrix matrix0, Matrix matrix, String methodName){
		  boolean result = true;
		  if(methodName == "subtract"){
			//line
			  Matrix matrixIdentity = Matrix.identity(matrix0.columns());//with size of column
			  matrix0 = matrix0.add(matrixIdentity);
			  matrix0 = matrix0.subtract(10.0);
			  
			  //line
			  //Matrix matrixIdentity = Matrix.identity(6);//with size of column
			  //matrix0 = matrix0.add(matrixIdentity);
			  //matrix0 = matrix0.subtract(0.0);
		      
			  if(matrix0.equals(matrix.add(matrixIdentity), 0)){
					 result = true; 
			  }else{
					 result = false;
			  }
		  }else if(methodName == "transpose"){			     
				 //wm return true
				 /*MockRandom mockRandom0 = new MockRandom(66L);
			     DenseMatrix denseMatrix0 = DenseMatrix.random(a, b, mockRandom0);*/
			  	 Matrix matrixIdentity = Matrix.identity(matrix0.columns());//with size of column
			     //similar code for 3 test suites
			  	 matrix0 = matrix0.add(matrixIdentity);
			     matrix0 = matrix0.transpose(); //transposing to make it similar 
			     Boolean followupresult = matrix0.equals(matrix.add(matrixIdentity));
			     if(followupresult){//if the output of matrices vary
			    	 result = true;
			     }else{
			    	 result = false;
			     }
		  }else if(methodName == "add"){
			//developer1
				Matrix matrixIdentity = Matrix.identity(matrix0.columns());//with size of column
				matrix0 = matrix0.add(matrixIdentity);
				matrix0 = matrix0.add(10);
			//branch
			//Matrix matrixIdentity = Matrix.identity(6);//with size of column
			//matrix0 = matrix0.add(matrixIdentity);
			//matrix0 = matrix0.add(-3300.470609235);
		 //wm
		  //Matrix matrixIdentity = Matrix.identity(37);//with size of column
		  //matrix0 = matrixIdentity.add(matrix0);
		  //matrix0 = matrix0.add((-205.4383));
			     
			  if(matrix.columns()==0 && matrix.rows()==0 && matrix0.columns()==0 && matrix0.rows()==0){
					  result = true; 
			  }else if(!matrix0.equals(matrix, 0)){
					 result = true; 
			  }else{
					 result = false;
			  }
		  }
		  return result;
	  }
	  public static boolean AdditionWithIdentity(int a, int b, Matrix matrix, Boolean sourceresult, String methodName){
		  boolean result = true;
		  //int Const = Const_INT;
		  //Matrix matrixIdentity = null;
		  
		  return result;
	  }
	  public static boolean AdditionWithIdentity(double a, int b, Matrix matrix, Boolean sourceresult, String methodName){
		  boolean result = true;
		  
		  return result;
	  }
	  //element by element multiplication with identity matrix
	  public static boolean MultiplyWithIdentity(Matrix matrix0, Matrix matrix, String methodName){
		  boolean result = true;
		  if(methodName == "power"){
			  //developer1
			  Matrix matrixIdentity = Matrix.identity(matrix0.columns());//with size of matrix1 column
			  matrix0 = matrix0.multiply(matrixIdentity);
			  matrix0 = matrix0.power(2);
			  //line
			  //Matrix matrixIdentity = Matrix.identity(matrix0.columns());//with size of matrix1 column
			  //matrix0 = matrix0.multiply(matrixIdentity);
			  //matrix0 = matrix0.power(1293);
			     
			  if(matrix0.equals(matrix, 0)){
					 result = true; 
			  }else{
					 result = false;
			  }
		  }else if(methodName == "subtract"){
			//developer
			  Matrix matrixIdentity = Matrix.identity(matrix0.columns());//with size of column
			  matrix0 = matrixIdentity.multiply(matrix0);
			  matrix0 = matrix0.subtract(10.0);
			  if(matrix0.equals(matrix, 0)){
					 result = true; 
			  }else{
					 result = false;
			  }
			  //line
			  /*Matrix matrixIdentity = Matrix.identity(6);//with size of column
			  matrix0 = matrixIdentity.multiply(matrix0);
			  matrix0 = matrix0.subtract(0.0);
		      if(matrix0.equals(matrix, 0)){
					 result = true; 
			  }else{
					 result = false;
			  }*/
		  }else if(methodName == "transpose"){
			     Matrix matrixIdentity = Matrix.identity(matrix0.columns());//with size of column
			     //similar code for 3 test suites
			     matrix0 = matrix0.multiply(matrixIdentity);
			     matrix0 = matrix0.transpose(); //transposing to make it similar 
			     Boolean followupresult = matrix0.equals(matrix.multiply(matrixIdentity));
			     if(followupresult){//if the output of matrices vary
			    	 result = true;
			     }else{
			    	 result = false;
			     }
		  }else if(methodName == "select"){
			  //branch
			  /*int[] intArray0 = new int[8];
		      Matrix matrixIdentity = Matrix.identity(9);//with size of column
		      matrix0 = matrix0.multiply(matrixIdentity);
		      matrix0 = matrix0.select(intArray0, intArray0);*/
		      //wm
		      //int[] intArray0 = new int[5];
		      //Matrix matrixIdentity = Matrix.identity(3338);//with size of column
			  //matrix0 = matrix0.multiply(matrixIdentity);
		      //matrix0 = matrix0.select(intArray0, intArray0);
		      
		      //developer1
		      Matrix matrixIdentity = Matrix.identity(matrix0.columns());//with size of column
			  matrix0 = matrix0.multiply(matrixIdentity);
		      //int[] rowInd = new int[]{1, 3, 4};
		      //int[] colInd = new int[]{0, 1, 2, 3, 4, 5};
			  //developer2
		      //int[] rowInd = new int[]{0, 1, 2, 3, 4};
		      //int[] colInd = new int[]{0, 2, 4, 5}; // all columns
		      //devloper3
		      int[] rowInd = new int[]{1, 3, 3, 4};
		      int[] colInd = new int[]{2, 2, 4, 5, 5};
		      
		      matrix0 = matrix0.select(rowInd, colInd);
			     
			  if(matrix.columns()==0 && matrix.rows()==0 && matrix0.columns()==0 && matrix0.rows()==0){
					  result = true; 
			  }else if(matrix0.equals(matrix, 0)){
					 result = true; 
			  }else{
					 result = false;
			  }
		      /*if(matrix0.sum() >= matrix.sum()){
		    	  result =true;
		      }else{
		    	  result = false;
		      }*/
		  }else if(methodName == "multiply"){
			//developer1
			  //Matrix matrixIdentity = Matrix.identity(matrix0.columns());//with size of matrix1 column
			  //matrix0 = matrix0.multiply(matrixIdentity);
			  //matrix0 = matrix0.multiply(2.0);
			//developer2
			 /* Matrix matrixIdentity = Matrix.identity(matrix0.columns());//with size of matrix1 column
			  matrix0 = matrix0.multiply(matrixIdentity);
			  for (Matrix b: ms(a(4.0, 9.0, 0.0, 3.0),
                      a(6.0, 7.0, 7.0, 6.0),
                      a(9.0, 4.0, 3.0, 3.0),
                      a(4.0, 4.0, 1.0, 6.0))) {

				
				  matrix0 = matrix0.multiply(b);
				}*/
			//developer3
			 /* Matrix matrixIdentity = Matrix.identity(matrix0.columns());//with size of matrix1 column
			  matrix0 = matrix0.multiply(matrixIdentity);
			  Matrix b = m(a(9.28, 7.63, 4.1, 4.71),
                      a(4.68, 2.82, 9.18, 5.39),
                      a(4.54, 6.86, 1.29, 5.4),
                      a(8.72, 2.06, 4.28, 7.37),
                      a(2.43, 3.7, 7.52, 5.87),
                      a(8.21, 9.36, 4.85, 0.3),
                      a(9.87, 8.19, 5.03, 6.14),
                      a(9.47, 4.28, 3.86, 3.12),
                      a(5.29, 4.41, 5.23, 4.85)) ;

			  matrix0 = matrix0.multiply(b);*/
			  
			 /* if(matrix.columns()==0 && matrix.rows()==0 && matrix0.columns()==0 && matrix0.rows()==0){
				  result = true; 
			  }else if(matrix0.equals(matrix, 0)){
				 result = true; 
			  }else{
				 result = false;
			  }*/
			  //line
			  //Matrix matrixIdentity = Matrix.identity(matrix0.columns());//with size of matrix1 column
			  //matrix0 = matrix0.multiply(matrixIdentity);
			  //matrix0 = matrix0.multiply((double) 85);
			     //branch
			   Matrix matrixIdentity = Matrix.identity(matrix0.columns());//with size of matrix1 column
			   matrix0 = matrix0.multiply(matrixIdentity);
			   matrix0 = matrix0.multiply((double) 3405);
			   //only for branch
			  if(matrix.columns()==0 && matrix.rows()==0 && matrix0.columns()==0 && matrix0.rows()==0){
					  result = true; 
			  }else if(matrix0.equals(matrix, 0)){
					 result = true; 
			  }else{
					 result = false;
			  }

			     //wm
			   //Matrix matrixIdentity = Matrix.identity(matrix0.columns());//with size of matrix1 column
			   //matrix0 = matrix0.multiply(matrixIdentity);
			   //matrix0 = matrix0.multiply((-119.9752908860286));
			  
			  //only for line and wm   
			  /*if(matrix.columns()==0 && matrix.rows()==0 && matrix0.columns()==0 && matrix0.rows()==0){
					  result = true; 
			  }else if(matrix0.equals(matrix, 0)){
					 result = true; 
			  }else{
					 result = false;
			  }*/
		  }else if(methodName == "add"){
			  //developer1
			  Matrix matrixIdentity = Matrix.identity(matrix0.columns());//with size of column
			  matrix0 = matrixIdentity.multiply(matrix0);
			  matrix0 = matrix0.add(10);
			  //branch
			  //Matrix matrixIdentity = Matrix.identity(6);//with size of column
			  //matrix0 = matrixIdentity.multiply(matrix0);
			  //matrix0 = matrix0.add(-3300.470609235);
		      //wm
			  //Matrix matrixIdentity = Matrix.identity(37);//with size of column
			  //matrix0 = matrixIdentity.multiply(matrix0);
			  //matrix0 = matrix0.add((-205.4383));
			     
			  if(matrix.columns()==0 && matrix.rows()==0 && matrix0.columns()==0 && matrix0.rows()==0){
					  result = true; 
			  }else if(matrix0.equals(matrix, 0)){
					 result = true; 
			  }else{
					 result = false;
			  }
		  } 
		  return result;
	  }
	  public static boolean MultiplyWithIdentity(Matrix matrix0, Matrix matrix, Boolean sourceResult, String methodName){
		  boolean result = true;
		  //int Const = Const_INT;
		  //Matrix matrixIdentity = null;
		  if(methodName == "equals"){
			  	 //int key = mult(a,1);
			  	 //developer
			  	 /*Matrix matrixIdentity = Matrix.identity(matrix0.columns());//with size of matrix1 column
			     matrix0 = matrix0.multiply(matrixIdentity);
			     matrix = matrix.multiply(matrix.identity(matrix.columns()));
			     //result = matrix.equals(matrix0, Matrices.EPS);
			     result = matrix.equals(matrix0);*/
			  	 //line
			  	 /*Matrix matrixIdentity = Matrix.identity(matrix0.columns());//with size of matrix1 column
			  	 matrix0 = matrix0.multiply(matrixIdentity);
			  	 matrix = matrix.multiply(matrix.identity(matrix.columns()));
			     result = matrix0.equals((Object)matrix);//false*/
			     //branch
			     /*LinkedList<Double> linkedList0 = new LinkedList<Double>();
			     DenseVector denseVector0 = DenseVector.fromCollection(linkedList0);
			     Matrix matrix2 = denseVector0.toDiagonalMatrix();
			     Matrix matrix1 = matrix2.transformRow(1, (VectorFunction) null);
			     Matrix matrixIdentity = Matrix.identity(matrix1.columns());//with size of matrix1 column
			     matrix1 = matrix1.multiply(matrixIdentity);
			     result = matrix1.equals((Object)matrix2);//true*/
			     //wm1
			     /*int[] intArray0 = new int[5];			     
			     Matrix matrixIdentity = Matrix.identity(matrix0.columns());//with size of matrix1 column
			     matrix0 = matrix0.multiply(matrixIdentity);
			     Matrix matrix1 = matrix0.select(intArray0, intArray0);
			     result = matrix1.equals((Object)matrix0);//false*/
			     //wm2
			     Matrix matrixIdentity = Matrix.identity(matrix0.columns());//with size of matrix1 column
			     matrix0 = matrix0.multiply(matrixIdentity);
			     matrix = matrix.multiply(matrix.identity(matrix.columns()));
			     result = matrix.equals((Object)matrix0);//false
			     //wm3
			    /* Matrix matrixIdentity = Matrix.identity(matrix0.columns());//with size of matrix1 column
			     matrix0 = matrix0.multiply(matrixIdentity);
			     matrix = matrix.multiply(matrix.identity(matrix.columns()));
			     result = matrix0.equals(matrix);//false*/
		      
			  if(result == sourceResult ){
					  result = true;  
			  }else{
					 result = false;
			  }
		  }
		  return result;
	  }
	  public static boolean MultiplyWithIdentity(int a, int b, Matrix matrix, Boolean sourceResult, String methodName){
		  boolean result = true;
		  //int Const = Const_INT;
		  //Matrix matrixIdentity = null;
		  if(methodName == "equals"){
			  	 //int key = mult(a,1);
			  	 //line
			  	 /*MockRandom mockRandom0 = new MockRandom(1698);
				 Matrix matrix2 = Matrix.random(1698, 1, mockRandom0);
				 VectorFunction vectorFunction0 = mock(VectorFunction.class, new ViolatedAssumptionAnswer());
			     doReturn(0.0).when(vectorFunction0).evaluate(anyInt() , anyDouble()); 
			     Matrix matrix1 = matrix2.transformRow(1337, vectorFunction0);
			     Matrix matrixIdentity = Matrix.identity(matrix1.columns());//with size of matrix1 column
			     matrix1 = matrix1.multiply(matrixIdentity);
			     result = matrix1.equals((Object)matrix2);//false*/
			     //branch
			     /*LinkedList<Double> linkedList0 = new LinkedList<Double>();
			     DenseVector denseVector0 = DenseVector.fromCollection(linkedList0);
			     Matrix matrix2 = denseVector0.toDiagonalMatrix();
			     Matrix matrix1 = matrix2.transformRow(1, (VectorFunction) null);
			     Matrix matrixIdentity = Matrix.identity(matrix1.columns());//with size of matrix1 column
			     matrix1 = matrix1.multiply(matrixIdentity);
			     result = matrix1.equals((Object)matrix2);//true*/
			     //wm1
			     /*Matrix matrix2 = Matrix.unit(3338, 3338);
			     int[] intArray0 = new int[5];
			     Matrix matrix1 = matrix2.select(intArray0, intArray0);
			     Matrix matrixIdentity = Matrix.identity(matrix1.columns());//with size of matrix1 column
			     matrix1 = matrix1.multiply(matrixIdentity);
			     result = matrix1.equals((Object)matrix2);//false*/
			     //wm2
			     /*MockRandom mockRandom0 = new MockRandom();
			     DenseMatrix denseMatrix0 = DenseMatrix.randomSymmetric(9, mockRandom0); 
			     Matrix matrix1 = denseMatrix0.shuffle();
			     Matrix matrixIdentity = Matrix.identity(matrix1.columns());//with size of matrix1 column
			     matrix1 = matrix1.multiply(matrixIdentity);
			     result = matrix1.equals((Object)denseMatrix0);//false*/
			     //wm3
			     double[][] doubleArray0 = new double[1][1];
			     double[] doubleArray1 = new double[4];
			     doubleArray0[0] = doubleArray1;
			     Matrix matrix2 = Matrix.from2DArray(doubleArray0);			    
			     Matrix matrix1 = matrix2.removeFirstColumn();
			     Matrix matrixIdentity = Matrix.identity(matrix1.columns());//with size of matrix1 column
			     matrix1 = matrix1.multiply(matrixIdentity);
			     result = matrix1.equals((Object)matrix2);//false*/
		      
			  if(result == sourceResult ){
					  result = true;  
			  }else{
					 result = false;
			  }
		  }
		  return result;
	  }
	  public static boolean MultiplyWithIdentity(double a, int b, Matrix matrix, Boolean sourceresult, String methodName){
		  boolean result = true;
		  
		  return result;
	  }
	  public static boolean MultiplyWithIdentity(Matrix matrixinitial, Vector vectorinitial, Vector sourceresult, String methodName){
		  boolean result = true;
		  Matrix matrix = null;
		  Vector vector = null;
		  Matrix matrixAdd = null;
		  Vector vectorAdd = null;
		  Vector vectorFollowup = null;
		  Matrix matrixrandom = null;
		  double precision = 0.0001;
		  if(methodName == "LeastSquaresSolver"){
			  //developer
			     //dev1,dev3,dev5,dev8
			     //matrixAdd = Matrix.identity(1);
			     //dev2,dev6,dev9
			     //matrixAdd = Matrix.identity(2);
			    //dev4,dev10
			     //matrixAdd = Matrix.identity(3);
			   //dev7
			     //matrixAdd = Matrix.identity(4);
			   //dev11
			     //matrixAdd = Matrix.identity(5);
			     
				 /*matrix = matrixinitial.multiply(matrixAdd);
				 LeastSquaresSolver leastSquaresSolver0 = new LeastSquaresSolver(matrix);
				 vectorFollowup = leastSquaresSolver0.solve(vectorinitial);*/
			//wm1 followup = source
				 matrixAdd = Matrix.identity(2);
				 matrix = matrixinitial.multiply(matrixAdd);
				 LeastSquaresSolver leastSquaresSolver0 = new LeastSquaresSolver(matrix);
				 vectorFollowup = (CompressedVector)leastSquaresSolver0.solve(vectorinitial);
				//wm2 throws exception  not applicable
				 /*matrixAdd = Matrix.identity(209);
				 matrix = matrixAdd.multiply(matrixinitial);
				 LeastSquaresSolver leastSquaresSolver0 = new LeastSquaresSolver(matrix);
				 vectorFollowup = leastSquaresSolver0.solve(vectorinitial);*/
				//line followup = source
				 /*matrixAdd = Matrix.identity(4);
				 matrix = matrixinitial.multiply(matrixAdd);
				 LeastSquaresSolver leastSquaresSolver0 = new LeastSquaresSolver(matrix);
				 vectorFollowup = leastSquaresSolver0.solve(vectorinitial);*/
				//branch followup = source
				 /*matrixAdd = Matrix.identity(8);
				 matrix = matrixinitial.multiply(matrixAdd);
				 LeastSquaresSolver leastSquaresSolver0 = new LeastSquaresSolver(matrix);
				 vectorFollowup = leastSquaresSolver0.solve(vectorinitial);*/
				 
				result = vectorFollowup.equals(sourceresult, precision);
		  }else if(methodName == "ForwardBackSubstitutionSolver"){
			//developer
			     //dev1
			     //matrixAdd = Matrix.identity(1);
			     //dev2
			     //matrixAdd = Matrix.identity(2);
			    //dev3,dev4
			     //matrixAdd = Matrix.identity(3);
			   //dev5,dev6
			     //matrixAdd = Matrix.identity(4);
			   //dev7
			     //matrixAdd = Matrix.identity(5);
			     
			     /*matrix = matrixinitial.multiply(matrixAdd);
				 ForwardBackSubstitutionSolver forwardBackSubstitutionSolver0 = new ForwardBackSubstitutionSolver(matrix);
				 vectorFollowup = forwardBackSubstitutionSolver0.solve(vectorinitial);
				 result = vectorFollowup.equals(sourceresult, precision);*/
			//line followup = source
				 /*matrixAdd = Matrix.identity(3);
				 matrix = matrixinitial.multiply(matrixAdd);
				 ForwardBackSubstitutionSolver forwardBackSubstitutionSolver0 = new ForwardBackSubstitutionSolver(matrix);
				 vectorFollowup = forwardBackSubstitutionSolver0.solve(vectorinitial);*/
				//branch followup = source
				 /*matrixAdd = Matrix.identity(23);
				 matrix = matrixinitial.multiply(matrixAdd);
				 ForwardBackSubstitutionSolver forwardBackSubstitutionSolver0 = new ForwardBackSubstitutionSolver(matrix);
				 vectorFollowup = forwardBackSubstitutionSolver0.solve(vectorinitial);	*/ 
				 //wm1
				 /*matrixAdd = Matrix.identity(11);
				 matrix = matrixinitial.multiply(matrixAdd);
				 ForwardBackSubstitutionSolver forwardBackSubstitutionSolver0 = new ForwardBackSubstitutionSolver(matrix);
				 vectorFollowup = forwardBackSubstitutionSolver0.solve(vectorinitial);	
				 result = vectorFollowup.equals(sourceresult, precision);*/
				 //wm2
				 matrixAdd = Matrix.identity(22);
				 matrix = matrixinitial.multiply(matrixAdd);
				 ForwardBackSubstitutionSolver forwardBackSubstitutionSolver0 = new ForwardBackSubstitutionSolver(matrix);
				 vectorFollowup = forwardBackSubstitutionSolver0.solve(vectorinitial);	
				 result = vectorFollowup.equals(sourceresult, precision);
				 //wm3
				 /*matrixAdd = Matrix.identity(1);
				 matrix = matrixinitial.multiply(matrixAdd);
				 ForwardBackSubstitutionSolver forwardBackSubstitutionSolver0 = new ForwardBackSubstitutionSolver(matrix);
				 vectorFollowup = forwardBackSubstitutionSolver0.solve(vectorinitial);	
				 result = vectorFollowup.equals(sourceresult, precision);*/
				//wm4
				 /*matrixAdd = Matrix.identity(11);
				 matrix = matrixinitial.multiply(matrixAdd);
				 ForwardBackSubstitutionSolver forwardBackSubstitutionSolver0 = new ForwardBackSubstitutionSolver(matrix);
				 vectorFollowup = forwardBackSubstitutionSolver0.solve(vectorinitial);	
				 result = vectorFollowup.equals(sourceresult, precision);*/
				//wm5
				 /*matrixAdd = Matrix.identity(1);
				 matrix = matrixinitial.multiply(matrixAdd);
				 ForwardBackSubstitutionSolver forwardBackSubstitutionSolver0 = new ForwardBackSubstitutionSolver(matrix);
				 vectorFollowup = forwardBackSubstitutionSolver0.solve(vectorinitial);	
				 result = vectorFollowup.equals(sourceresult, precision);*/
				//wm6
				 /*matrixAdd = Matrix.identity(11);
				 matrix = matrixinitial.multiply(matrixAdd);
				 ForwardBackSubstitutionSolver forwardBackSubstitutionSolver0 = new ForwardBackSubstitutionSolver(matrix);
				 vectorFollowup = forwardBackSubstitutionSolver0.solve(vectorinitial);	
				 result = vectorFollowup.equals(sourceresult, precision);*/
		  }else if(methodName == "SquareRootSolver"){
			//developer
			     //dev1
			     matrixAdd = Matrix.identity(1);
			     //dev2
			     //matrixAdd = Matrix.identity(2);
			    //dev3,dev4
			     //matrixAdd = Matrix.identity(3);
			   //dev5
			     //matrixAdd = Matrix.identity(4);
			   //dev6
			     //matrixAdd = Matrix.identity(5);
			     
			     matrix = matrixinitial.multiply(matrixAdd);
				 SquareRootSolver squareRootSolver0 = new SquareRootSolver(matrix);
				 vectorFollowup = squareRootSolver0.solve(vectorinitial);	
				 result = vectorFollowup.equals(sourceresult, precision);
			  	 //wm
				 //matrixAdd = Matrix.identity(10);
				 //matrixAdd = Matrix.identity(12);
				 /*matrixAdd = Matrix.identity(16);
				 matrix = matrixinitial.multiply(matrixAdd);
				 SquareRootSolver squareRootSolver0 = new SquareRootSolver(matrix);
				 vectorFollowup = squareRootSolver0.solve(vectorinitial);	
				 result = vectorFollowup.equals(sourceresult, precision);*/
				 //line
				 /*matrixAdd = Matrix.identity(18);
				 matrix = matrixinitial.multiply(matrixAdd);
				 SquareRootSolver squareRootSolver0 = new SquareRootSolver(matrix);
				 vectorFollowup = squareRootSolver0.solve(vectorinitial);	
				 result = vectorFollowup.equals(sourceresult, precision);*/
				//branch
				 /*matrixAdd = Matrix.identity(6);
				 matrix = matrixinitial.multiply(matrixAdd);
				 SquareRootSolver squareRootSolver0 = new SquareRootSolver(matrix);
				 vectorFollowup = squareRootSolver0.solve(vectorinitial);	
				 result = vectorFollowup.equals(sourceresult, precision);*/
		  }
		  return result;
	  }
	  //multiply with -1
	  public static boolean Negation(Matrix matrixinitial, Vector vectorinitial, Vector sourceresult, String methodName){
		  boolean result = true;
		  Matrix matrix = null;
		  Vector vector = null;
		  Matrix matrixAdd = null;
		  Vector vectorAdd = null;
		  Vector vectorFollowup = null;
		  Matrix matrixrandom = null;
		  double precision = 0.0001;
		  if(methodName == "LeastSquaresSolver"){
			  //developer
			    /* matrix = matrixinitial.multiply(-1.0);
				 LeastSquaresSolver leastSquaresSolver0 = new LeastSquaresSolver(matrix);
				 vector = vectorinitial.multiply(-1.0);
				 vectorFollowup = leastSquaresSolver0.solve(vector);*/
			   //wm1 followup = source
				 matrix = matrixinitial.multiply(-2.0);
				 LeastSquaresSolver leastSquaresSolver0 = new LeastSquaresSolver(matrix);
				 vector = vectorinitial.multiply(-2.0);
				 vectorFollowup = leastSquaresSolver0.solve(vector);
				//wm2 followup = source
				 /*matrix = matrixinitial.multiply(-2.0);
				 LeastSquaresSolver leastSquaresSolver0 = new LeastSquaresSolver(matrix);
				 vector = vectorinitial.multiply(-2.0);
				 vectorFollowup = leastSquaresSolver0.solve(vector);*/
				//line followup == source
				 /*matrix = matrixinitial.multiply(-1.0);
				 LeastSquaresSolver leastSquaresSolver0 = new LeastSquaresSolver(matrix);
				 vector = vectorinitial.multiply(-1.0);
				 vectorFollowup = leastSquaresSolver0.solve(vector);*/
				//branch followup = source
				 /*matrix = matrixinitial.multiply(-1.0);
				 LeastSquaresSolver leastSquaresSolver0 = new LeastSquaresSolver(matrix);
				 vector = vectorinitial.multiply(-1.0);
				 vectorFollowup = leastSquaresSolver0.solve(vector);*/
				 
				result = vectorFollowup.equals(sourceresult, precision);
		  }else if(methodName == "ForwardBackSubstitutionSolver"){
			//all followup == source
				 matrix = matrixinitial.multiply(-1.0);
				 ForwardBackSubstitutionSolver forwardBackSubstitutionSolver0 = new ForwardBackSubstitutionSolver(matrix);
				 vector = vectorinitial.multiply(-1.0);
				 vectorFollowup = forwardBackSubstitutionSolver0.solve(vector);
				 
				 result = vectorFollowup.equals(sourceresult, precision);
		  }else if(methodName == "SquareRootSolver"){
				//all
			  matrix = matrixinitial.multiply(-1.0);
			  SquareRootSolver squareRootSolver0 = new SquareRootSolver(matrix);
				 vector = vectorinitial.multiply(-1.0);
				 vectorFollowup = squareRootSolver0.solve(vector);
				 
				 result = vectorFollowup.equals(sourceresult, precision);
		  }
		  return result;
	  }
}
