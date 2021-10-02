import org.evosuite.runtime.ViolatedAssumptionAnswer;
import org.junit.*;
import org.la4j.vector.functor.VectorFunction;

import java.io.*;
import static org.evosuite.shaded.org.mockito.Matchers.anyDouble;
import static org.evosuite.shaded.org.mockito.Matchers.anyInt;
import static org.evosuite.shaded.org.mockito.Mockito.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

import org.apache.commons.math4.linear.Array2DRowRealMatrix;
import org.apache.commons.math4.linear.ArrayRealVector;
import org.apache.commons.math4.linear.DecompositionSolver;
import org.apache.commons.math4.linear.DiagonalMatrix;
import org.apache.commons.math4.linear.MatrixUtils;
import org.apache.commons.math4.linear.QRDecomposition;
import org.apache.commons.math4.linear.RealMatrix;
import org.apache.commons.math4.linear.RealVector;
import org.apache.commons.math4.linear.RealVectorAbstractTest;
import org.apache.commons.math4.linear.SingularValueDecomposition;
import org.evosuite.runtime.mock.java.util.MockRandom;
import static org.la4j.M.*;
import org.la4j.Matrix;
import org.la4j.Vector;
import org.la4j.linear.SquareRootSolver;
import org.la4j.matrix.ColumnMajorSparseMatrix;
import org.la4j.matrix.DenseMatrix;
import org.la4j.operation.inplace.InPlaceCopyMatrixToMatrix;
import org.la4j.operation.ooplace.OoPlaceMatrixByItsTransposeMultiplication;
import org.la4j.operation.ooplace.OoPlaceVectorsAddition;
import org.la4j.vector.DenseVector;
import org.la4j.vector.sparse.CompressedVector;
import org.la4j.linear.ForwardBackSubstitutionSolver;
import org.la4j.linear.LeastSquaresSolver;

 public  class TestAllMethods {

	public static void main(String[] args){

	}
	
	@Test
	public void AllMethods(){
		boolean result = true;
	
		
		String method = System.getProperty("methodname");
	//	method = "sequential_search";
		 
		 switch (method) {
		 // program MethodCollection2 
		 case "SingularValueDecomposition": result = SingularValueDecomposition();
		 break;
		 case "QRDecomposition": result = QRDecomposition();
		 break;
		 case "LeastSquaresSolver": result = LeastSquaresSolverSolve();
		 break;
		 case "ForwardBackSubstitutionSolver": result = ForwardBackSubstitutionSolver();
		 break;
		 case "SquareRootSolver": result = SquareRootSolver();
		 break;
		 case "zero": result =  zero();//line,wm
         break; 
		 case "updateColumn": result =  updateColumn();//line,branch
 		 break;
		 case "transpose": result =  transpose();//all
 		 break;
		 case "transformRow": result = transformRow();//line,branch
 		 break;
		 case "transformColumn": result = transformColumn();//line
         break;
         case "equals": result = equals();//all
 		 break; 
		 case "shuffle":result =  shuffle();//line
 		 break;
		 case "rotate": result = rotate();//line,wm
 		 break;		 
		 case "removeLastRow": result = removeLastRow();//line
         break;
         //uses removeRow same as removeLastRow
		 //case "removeFirstRow": result = removeFirstRow();//line
 		 //break;        		 
		 case "removeLastColumn": result = removeLastColumn();//line,wm
		 break;	
		//uses removeColumn same as removeLastColumn
		// case "removeFirstColumn": removeFirstColumn(); //wm
		// break;
		 case "power": result = power();//line
		 break;		 
		 case "multiply":result =  multiply();//all
		 break;	
		 //skipped uses multiply
		 //case "divide":result =  divide();//line
		// break;		 
		 case "insert": result = insert(); //line,wm
		 break;		 
		 case "fromCSV":result =  fromCSV();//all
         break;		 
		 case "subtract":result =  subtract();//line
 		 break; 
		 case "add":result =  add(); //branch,wm
		 break;
		 case "select":result = select();//branch,wm
		 break;
		 case "determinant":result = determinant(); //branch
		 break;
		 case "sliceTopLeft":result = sliceTopLeft(); //wm
		 break;
		 case "rank":result =  rank();//wm
		 break;        
		 case "setRow":result =  setRow();//wm
		 break;
		 default: method = "Invalid method";
                 break;
		 }
		 
		 
		// public static void reportFailure(String methodName, String faultName, String MRname, String testNo, String Failure){
				try
				{   
					String sFileName = ".//MRtest//"+method+".csv";
					FileWriter writer = new FileWriter(sFileName,true);
				    File file = new File(sFileName);
				    if ( !file.exists() ){
				    	file.createNewFile();	
				    }
			   if(!method.toLowerCase().equals(System.getProperty("MRname").toLowerCase())) { 
				//  for(int i=0; i<MRname.size();i++ )  {
				    //-Dmethodname="Add_values" -DMRname="Addition" -DFaultName="Addition" -DTestNo="1"
					  	writer.append(System.getProperty("FaultName"));
					    writer.append(',');
					    writer.append(System.getProperty("MRname"));
					    writer.append(',');
					    writer.append(System.getProperty("TestNo")); 
					    writer.append(',');
					    writer.append(Boolean.toString(result));
					    writer.append('\n');  
				//  }
					    }

				    writer.flush();
				    writer.close();
					
					
				}
				catch(IOException e)
				{
				     e.printStackTrace();
				}		
			//} 
	}
	public boolean SingularValueDecomposition(){
		String MRname = System.getProperty("MRname");
		Boolean testResult	= true;
		Matrix matrix = null;
		Vector vector = null;
		Matrix matrixAdd = null;
		Vector vectorAdd = null;
		Vector vectorInitial = null;
		Vector vectorFollowup = null;
		Matrix matrixrandom = null;
		RealMatrix matrixModified = null;
		RealMatrix resultFollowup = null;
		RealVector resultInitialVector = null;
		RealVector resultFollowupVector = null;
		RealVector b1 = null;
		//developer test suite
		/*double[][] testSquare = {
	            { 24.0 / 25.0, 43.0 / 25.0 },
	            { 57.0 / 25.0, 24.0 / 25.0 }
	    };
		DecompositionSolver solver =
	            new SingularValueDecomposition(MatrixUtils.createRealMatrix(testSquare)).getSolver();
	    RealMatrix b = MatrixUtils.createRealMatrix(new double[][] {
	                { 1, 2, 3 }, { 0, -5, 1 }
	        });*/
		//test1 using RealMatrix
		/*RealMatrix m =
	            MatrixUtils.createRealMatrix(new double[][] {
	                                   { 1.0, 0.0 },
	                                   { 0.0, 0.0 }
	                               });
	    DecompositionSolver solver = new SingularValueDecomposition(m).getSolver();
	    RealMatrix b = MatrixUtils.createRealMatrix(new double[][] {
	            { 11, 12 }, { 21, 22 }
	        });
	    RealMatrix xMatrix = solver.solve(b);*/
		//test2 using RealMatrix		 
	    //RealMatrix xMatrix = solver.solve(b);
	    //test3 ArrayRealVector
	    //resultInitialVector = solver.solve(b.getColumnVector(0));
		//test4
	    //resultInitialVector = solver.solve(b.getColumnVector(1));
	    //test5
	    /*ArrayRealVectorTest.RealVectorTestImpl v =
                new ArrayRealVectorTest.RealVectorTestImpl(b.getColumn(0));
	    resultInitialVector = solver.solve(v);*/
	    //test6
	    /*ArrayRealVectorTest.RealVectorTestImpl v =
                new ArrayRealVectorTest.RealVectorTestImpl(b.getColumn(1));
	    resultInitialVector = solver.solve(v);*/
		//evosuite
		//branch
		/*double[] doubleArray0 = new double[5];
		doubleArray0[1] = (-1172.656027014185);
	    doubleArray0[0] = 0.09430711890185595;
	    doubleArray0[4] = (-1172.656027014185);
	    DiagonalMatrix diagonalMatrix0 = new DiagonalMatrix(doubleArray0, false);
	    //SingularValueDecomposition.Solver singularValueDecomposition_Solver0 = new SingularValueDecomposition.Solver(doubleArray0, diagonalMatrix0, diagonalMatrix0, true, 0.0);
	    DecompositionSolver singularValueDecomposition0 = new SingularValueDecomposition(diagonalMatrix0).getSolver();
	    ArrayRealVector arrayRealVector0 = new ArrayRealVector(doubleArray0);
	    resultInitialVector = singularValueDecomposition0.solve((RealVector) arrayRealVector0);*/
	    //line1
	    /*double[] doubleArray0 = new double[8];
	    doubleArray0[0] = 915.907525512;
	    DiagonalMatrix diagonalMatrix0 = new DiagonalMatrix(doubleArray0, true);
	    //SingularValueDecomposition.Solver singularValueDecomposition_Solver0 = new SingularValueDecomposition.Solver(doubleArray0, diagonalMatrix0, diagonalMatrix0, true, (-719.0));
	    DecompositionSolver singularValueDecomposition0 = new SingularValueDecomposition(diagonalMatrix0).getSolver();
	    ArrayRealVector arrayRealVector0 = new ArrayRealVector(doubleArray0, true);
	    resultInitialVector = singularValueDecomposition0.solve((RealVector) arrayRealVector0);*/
	    //line2
	    /*double[] doubleArray0 = new double[1];
	    doubleArray0[0] = 915.907525512;
	    double[][] doubleArray1 = new double[1][2];
	    doubleArray1[0] = doubleArray0;
	    Array2DRowRealMatrix array2DRowRealMatrix0 = new Array2DRowRealMatrix(doubleArray1, true);
	    //SingularValueDecomposition.Solver singularValueDecomposition_Solver0 = new SingularValueDecomposition.Solver(doubleArray0, array2DRowRealMatrix0, array2DRowRealMatrix0, false, 0.0);
	    DecompositionSolver singularValueDecomposition0 = new SingularValueDecomposition(array2DRowRealMatrix0).getSolver();
	    Array2DRowRealMatrix array2DRowRealMatrix1 = (Array2DRowRealMatrix)singularValueDecomposition0.solve((RealMatrix) array2DRowRealMatrix0);*/
	    //wm1
	      /*double[] doubleArray0 = new double[7];
	      doubleArray0[0] = 479.93754753532;
	      doubleArray0[1] = 2.220446049250313E-16;
	      doubleArray0[2] = 0.0;
	      doubleArray0[3] = 4276.007316200578;
	      doubleArray0[4] = 4276.007316200578;
	      doubleArray0[5] = 4865.703412728138;
	      doubleArray0[6] = 4865.703412728138;
	      DiagonalMatrix diagonalMatrix0 = new DiagonalMatrix(doubleArray0, false);
	      SingularValueDecomposition singularValueDecomposition0 = new SingularValueDecomposition(diagonalMatrix0);
	      singularValueDecomposition0.getSolver();
	      //diagonalMatrix0.transpose();
	      RealMatrix realMatrix0 = singularValueDecomposition0.getUT();
	      DecompositionSolver singularValueDecomposition_Solver0 = new SingularValueDecomposition(realMatrix0).getSolver();
	      RealMatrix resultInitial = singularValueDecomposition_Solver0.solve(realMatrix0);*/
		  //wm2
		  double[] doubleArray0 = new double[7];
	      doubleArray0[0] = 0.0;
	      doubleArray0[1] = (-935.545609043);
	      doubleArray0[2] = 0.0;
	      doubleArray0[3] = (-3426.5);
	      doubleArray0[4] = 0.0;
	      doubleArray0[5] = (-1103.7020248578);
	      doubleArray0[6] = 6.283185307179586;
	      DiagonalMatrix diagonalMatrix0 = new DiagonalMatrix(doubleArray0);
	      RealMatrix realMatrix0 = diagonalMatrix0.copy();
	      DecompositionSolver singularValueDecomposition0 = new SingularValueDecomposition(realMatrix0).getSolver();
	      ArrayRealVector arrayRealVector0 = new ArrayRealVector(doubleArray0);
	      resultInitialVector = singularValueDecomposition0.solve((RealVector) arrayRealVector0);
	    try{	 
			 switch (MRname) {			
			 case "SingularValueDecomposition":
			 try {
				FileWriter writer = new FileWriter("C:\\MRtest\\"+MRname+".txt",true);
				File file = new File("C:\\MRtest\\"+MRname+".txt");
				if ( file.exists() ){
					 //file.delete();
			    }
				//file.createNewFile();
		    	//writer.write(Integer.toString(a));
		    	//writer.append('\n'); 
		    	writer.write(System.getProperty("FaultName"));
		    	writer.append('\n'); 
		    	//writer.write(xMatrix.toString());
		    	writer.write(resultInitialVector.toString());
		    	//writer.write(array2DRowRealMatrix1.toString());
		    	//writer.write(resultInitial.toString());
		    	writer.append('\n');
			    writer.close();				     
			 } catch (IOException ex) {
			   // report
			 } 
	        break;    
			case "Multiplication":	
				//developer
				//test1
				/*solver = new SingularValueDecomposition(m.scalarMultiply(2.0)).getSolver();
				resultFollowup = solver.solve(b.scalarMultiply(2.0));
				testResult = resultFollowup.toString().equals(xMatrix.toString());*/
				
				/*matrixModified = MatrixUtils.createRealMatrix(testSquare);
				matrixModified = matrixModified.scalarMultiply(2.0);
				solver = new SingularValueDecomposition(matrixModified).getSolver();*/
				//test2
				/*resultFollowup = solver.solve(b.scalarMultiply(2.0));
				testResult = resultFollowup.toString().equals(xMatrix.toString());*/
				//test3
				/*resultFollowupVector = solver.solve(b.getColumnVector(0).mapMultiply(2.0));
				testResult = resultFollowupVector.toString().equals(resultInitialVector.toString());*/
				//test4
				/*resultFollowupVector = solver.solve(b.getColumnVector(1).mapMultiply(2.0));
				testResult = resultFollowupVector.toString().equals(resultInitialVector.toString());*/
				//test5,6
				/*resultFollowupVector = solver.solve(v.mapMultiply(2.0));
				testResult = resultFollowupVector.toString().equals(resultInitialVector.toString());*/
				//evosuite
				//branch
				//singularValueDecomposition0 = new SingularValueDecomposition(diagonalMatrix0.scalarMultiply(2.0)).getSolver();
			    //arrayRealVector0 = new ArrayRealVector(doubleArray0);
			    //resultFollowupVector = singularValueDecomposition0.solve((RealVector) arrayRealVector0.mapMultiply(2.0));
			    //line1
				//singularValueDecomposition0 = new SingularValueDecomposition(diagonalMatrix0.scalarMultiply(2.0)).getSolver();
			    //arrayRealVector0 = new ArrayRealVector(doubleArray0, true);
			    //resultFollowupVector = singularValueDecomposition0.solve((RealVector) arrayRealVector0.mapMultiply(2.0));
			    
				//testResult = resultFollowupVector.toString().equals(resultInitialVector.toString());
				
				//line2
				 //singularValueDecomposition0 = new SingularValueDecomposition(array2DRowRealMatrix0.scalarMultiply(2.0)).getSolver();
				 //resultFollowup = (Array2DRowRealMatrix)singularValueDecomposition0.solve((RealMatrix) array2DRowRealMatrix0.scalarMultiply(2.0));
			    
			    //testResult = resultFollowup.toString().equals(array2DRowRealMatrix1.toString());
				//wm1
				 //singularValueDecomposition_Solver0 = new SingularValueDecomposition(realMatrix0.scalarMultiply(2.0)).getSolver();
				 //resultFollowup = singularValueDecomposition_Solver0.solve(realMatrix0.scalarMultiply(2.0));
			    
			     //testResult = resultFollowup.toString().equals(resultInitial.toString());
				//wm2
				 singularValueDecomposition0 = new SingularValueDecomposition(realMatrix0.scalarMultiply(2.0)).getSolver();
			     arrayRealVector0 = new ArrayRealVector(doubleArray0);
			     resultFollowupVector = singularValueDecomposition0.solve((RealVector) arrayRealVector0.mapMultiply(2.0));
			     testResult = resultFollowupVector.toString().equals(resultInitialVector.toString());
					   
     		break; 
			case "permuteRow":
				//developer
				//test1
				/*RealMatrix m1 = m.getRowMatrix(0);
				RealMatrix m2 = m.getRowMatrix(1);
				m.setRowMatrix(1, m1);
				m.setRowMatrix(0, m2); 
				solver = new SingularValueDecomposition(m).getSolver();
				RealMatrix b2 = b.getRowMatrix(0);
				RealMatrix b3 = b.getRowMatrix(1);
				b.setRowMatrix(1, b2);
				b.setRowMatrix(0, b3); 
				resultFollowup = solver.solve(b);
				testResult = resultFollowup.toString().equals(xMatrix.toString());*/
				
				/*matrixModified = MatrixUtils.createRealMatrix(testSquare);
				RealMatrix m1 = matrixModified.getRowMatrix(0);
				RealMatrix m2 = matrixModified.getRowMatrix(1);
				matrixModified.setRowMatrix(1, m1);
				matrixModified.setRowMatrix(0, m2); 
				solver = new SingularValueDecomposition(matrixModified).getSolver();*/
				//test2
				/*RealMatrix b2 = b.getRowMatrix(0);
				RealMatrix b3 = b.getRowMatrix(1);
				b.setRowMatrix(1, b2);
				b.setRowMatrix(0, b3); 
				resultFollowup = solver.solve(b);
				testResult = resultFollowup.toString().equals(xMatrix.toString());*/
				//test3
				/*b1 = b.getColumnVector(0);
				double b2 = b1.getEntry(0);
				double b3 = b1.getEntry(1);
				b1.setEntry(1, b2);
				b1.setEntry(0, b3);
				resultFollowupVector = solver.solve(b1);
				testResult = resultFollowupVector.toString().equals(resultInitialVector.toString());*/
				//test4
				/*b1 = b.getColumnVector(1);
				double b2 = b1.getEntry(0);
				double b3 = b1.getEntry(1);
				b1.setEntry(1, b2);
				b1.setEntry(0, b3);
				resultFollowupVector = solver.solve(b1);
				testResult = resultFollowupVector.toString().equals(resultInitialVector.toString());*/
				//test5,6
				/*double b2 = v.getEntry(0);
				double b3 = v.getEntry(1);
				v.setEntry(1, b2);
				v.setEntry(0, b3);
				resultFollowupVector = solver.solve(v);
				testResult = resultFollowupVector.toString().equals(resultInitialVector.toString());*/
				//evosuite
				//branch
				/*double[] a1 = diagonalMatrix0.getRow(2);
				double[] a2 = diagonalMatrix0.getRow(3);
				diagonalMatrix0.setRow(2, a2);
				diagonalMatrix0.setRow(3, a1);
				singularValueDecomposition0 = new SingularValueDecomposition(diagonalMatrix0).getSolver();
			    //arrayRealVector0 = new ArrayRealVector(doubleArray0);
			    double d1 = arrayRealVector0.getEntry(2);
			    double d2 = arrayRealVector0.getEntry(3);
			    arrayRealVector0.setEntry(2, d2);
			    arrayRealVector0.setEntry(3, d1);
			    resultFollowupVector = singularValueDecomposition0.solve((RealVector) arrayRealVector0);*/			
				//line1
				/*double[] a1 = diagonalMatrix0.getRow(2);
				double[] a2 = diagonalMatrix0.getRow(3);
				diagonalMatrix0.setRow(2, a2);
				diagonalMatrix0.setRow(3, a1);
				singularValueDecomposition0 = new SingularValueDecomposition(diagonalMatrix0).getSolver();
			    arrayRealVector0 = new ArrayRealVector(doubleArray0, true);
			    double d1 = arrayRealVector0.getEntry(2);
			    double d2 = arrayRealVector0.getEntry(3);
			    arrayRealVector0.setEntry(2, d2);
			    arrayRealVector0.setEntry(3, d1);
			    resultFollowupVector = singularValueDecomposition0.solve((RealVector) arrayRealVector0);*/
			    
			    //testResult = resultFollowupVector.toString().equals(resultInitialVector.toString());
				//line2
				/*double[] d1 = array2DRowRealMatrix0.getRow(0);
				double[] d2 = array2DRowRealMatrix0.getRow(1);
				array2DRowRealMatrix0.setRow(0, d2);
				array2DRowRealMatrix0.setRow(1, d1);
			    singularValueDecomposition0 = new SingularValueDecomposition(array2DRowRealMatrix0).getSolver();
				resultFollowup = (Array2DRowRealMatrix)singularValueDecomposition0.solve((RealMatrix) array2DRowRealMatrix0);
			    
			    testResult = resultFollowup.toString().equals(array2DRowRealMatrix1.toString());*/
				//wm1
				/*double[] d1 = realMatrix0.getRow(0);
				double[] d2 = realMatrix0.getRow(1);
				realMatrix0.setRow(0, d2);
				realMatrix0.setRow(1, d1);
				singularValueDecomposition_Solver0 = new SingularValueDecomposition(realMatrix0).getSolver();
				resultFollowup = singularValueDecomposition_Solver0.solve(realMatrix0);
			    
			    testResult = resultFollowup.toString().equals(resultInitial.toString());*/
				//wm2
				 double[] d1 = realMatrix0.getRow(0);
				 double[] d2 = realMatrix0.getRow(2);
				 realMatrix0.setRow(0, d2);
				 realMatrix0.setRow(2, d1);				 
				 singularValueDecomposition0 = new SingularValueDecomposition(realMatrix0).getSolver();
			     arrayRealVector0 = new ArrayRealVector(doubleArray0);
			     double a1 = arrayRealVector0.getEntry(0);
			     double a2 = arrayRealVector0.getEntry(2);
			     arrayRealVector0.setEntry(0, a2);
			     arrayRealVector0.setEntry(2, a1);
			     resultFollowupVector = singularValueDecomposition0.solve((RealVector) arrayRealVector0);
			     testResult = resultFollowupVector.toString().equals(resultInitialVector.toString());
				
			break;
     		case "matrixAdd":
     			//developer
     			//test1
     			/*solver = new SingularValueDecomposition(m.add(m)).getSolver();
     			resultFollowup = solver.solve(b.add(b));
				testResult = resultFollowup.toString().equals(xMatrix.toString());*/
     			     			
     			/*matrixModified = MatrixUtils.createRealMatrix(testSquare); 
     			solver = new SingularValueDecomposition(matrixModified.add(matrixModified)).getSolver();*/
     			//test2
     			/*resultFollowup = solver.solve(b.add(b));
				testResult = resultFollowup.toString().equals(xMatrix.toString());*/
     			//test3
     			/*resultFollowupVector = solver.solve(b.getColumnVector(0).add(b.getColumnVector(0)));
				testResult = resultFollowupVector.toString().equals(resultInitialVector.toString());*/
				//test4
     			/*resultFollowupVector = solver.solve(b.getColumnVector(1).add(b.getColumnVector(1)));
				testResult = resultFollowupVector.toString().equals(resultInitialVector.toString());*/
				//test5,6
     			/*resultFollowupVector = solver.solve(v.add(v));
				testResult = resultFollowupVector.toString().equals(resultInitialVector.toString());*/
     			//evosuite
     			//branch
     			//singularValueDecomposition0 = new SingularValueDecomposition(diagonalMatrix0.add(diagonalMatrix0)).getSolver();
			    //arrayRealVector0 = new ArrayRealVector(doubleArray0);
			    //resultFollowupVector = singularValueDecomposition0.solve((RealVector) arrayRealVector0.add(arrayRealVector0));
			    //line1
     			//singularValueDecomposition0 = new SingularValueDecomposition(diagonalMatrix0.add(diagonalMatrix0)).getSolver();
			    //arrayRealVector0 = new ArrayRealVector(doubleArray0, true);
			    //resultFollowupVector = singularValueDecomposition0.solve((RealVector) arrayRealVector0.add(arrayRealVector0));
			    
     		    //testResult = resultFollowupVector.toString().equals(resultInitialVector.toString());
     		    //line2
     			//singularValueDecomposition0 = new SingularValueDecomposition(array2DRowRealMatrix0.add(array2DRowRealMatrix0)).getSolver();
				//resultFollowup = (Array2DRowRealMatrix)singularValueDecomposition0.solve((RealMatrix) array2DRowRealMatrix0.add(array2DRowRealMatrix0));
			    
			    //testResult = resultFollowup.toString().equals(array2DRowRealMatrix1.toString());
     			//wm1
     			//singularValueDecomposition_Solver0 = new SingularValueDecomposition(realMatrix0.add(realMatrix0)).getSolver();
				//resultFollowup = singularValueDecomposition_Solver0.solve(realMatrix0.add(realMatrix0));
			    
			    //testResult = resultFollowup.toString().equals(resultInitial.toString());
     			//wm2
				 singularValueDecomposition0 = new SingularValueDecomposition(realMatrix0.add(realMatrix0)).getSolver();
			     arrayRealVector0 = new ArrayRealVector(doubleArray0);
			     resultFollowupVector = singularValueDecomposition0.solve((RealVector) arrayRealVector0.add(arrayRealVector0));
			     testResult = resultFollowupVector.toString().equals(resultInitialVector.toString());
				
     		break;  
			case "transpose":
				//developer
				//test1
				//solver = new SingularValueDecomposition(m.transpose().multiply(m)).getSolver();
				//resultFollowup = solver.solve(m.transpose().multiply(b));
				//testResult = resultFollowup.toString().equals(xMatrix.toString());
				
				/*matrixModified = MatrixUtils.createRealMatrix(testSquare);
				solver = new SingularValueDecomposition(matrixModified.transpose().multiply(matrixModified)).getSolver();*/				
				//test2
				/*resultFollowup = solver.solve(matrixModified.transpose().multiply(b));
				testResult = resultFollowup.toString().equals(xMatrix.toString());*/
				//test3
				/*resultFollowupVector = solver.solve(matrixModified.transpose().multiply(b).getColumnVector(0));
				testResult = resultFollowupVector.toString().equals(resultInitialVector.toString());*/
				//test4
				/*resultFollowupVector = solver.solve(matrixModified.transpose().multiply(b).getColumnVector(1));
				testResult = resultFollowupVector.toString().equals(resultInitialVector.toString());*/
				//test5
				/*b = matrixModified.transpose().multiply(b);
				v = new ArrayRealVectorTest.RealVectorTestImpl(b.getColumn(0));
				resultFollowupVector = solver.solve(v);
				testResult = resultFollowupVector.toString().equals(resultInitialVector.toString());*/
				//test6
				/*b = matrixModified.transpose().multiply(b);
				v = new ArrayRealVectorTest.RealVectorTestImpl(b.getColumn(1));
				resultFollowupVector = solver.solve(v);
				testResult = resultFollowupVector.toString().equals(resultInitialVector.toString());*/
				//evosuite
     			//branch
				/*matrixModified = diagonalMatrix0.transpose().multiply(diagonalMatrix0);
				singularValueDecomposition0 = new SingularValueDecomposition(matrixModified).getSolver();
				doubleArray0[0] = matrixModified.getEntry(0, 0);
				doubleArray0[1] = matrixModified.getEntry(1, 1);
				doubleArray0[2] = matrixModified.getEntry(2, 2);
				doubleArray0[3] = matrixModified.getEntry(3, 3);
				doubleArray0[4] = matrixModified.getEntry(4, 4);
			    arrayRealVector0 = new ArrayRealVector(doubleArray0);
			    resultFollowupVector = singularValueDecomposition0.solve((RealVector) arrayRealVector0);*/
			    //line1
				//singularValueDecomposition0 = new SingularValueDecomposition(diagonalMatrix0.transpose().multiply(diagonalMatrix0)).getSolver();
			    //arrayRealVector0 = new ArrayRealVector(doubleArray0, true);
			    //resultFollowupVector = singularValueDecomposition0.solve((RealVector) diagonalMatrix0.transpose().multiply(diagonalMatrix0).getColumnVector(0));
			    
     		    //testResult = resultFollowupVector.toString().equals(resultInitialVector.toString());
     		    //line2
				//singularValueDecomposition0 = new SingularValueDecomposition(array2DRowRealMatrix0.transpose().multiply(array2DRowRealMatrix0)).getSolver();
				//resultFollowup = (Array2DRowRealMatrix)singularValueDecomposition0.solve((RealMatrix) array2DRowRealMatrix0.transpose().multiply(array2DRowRealMatrix0));
			    
			    //testResult = resultFollowup.toString().equals(array2DRowRealMatrix1.toString());
				//wm1
				//singularValueDecomposition_Solver0 = new SingularValueDecomposition(realMatrix0.transpose().multiply(realMatrix0)).getSolver();
				//resultFollowup = singularValueDecomposition_Solver0.solve(realMatrix0.transpose().multiply(realMatrix0));
			    
			    //testResult = resultFollowup.toString().equals(resultInitial.toString());
				//wm2
				 singularValueDecomposition0 = new SingularValueDecomposition(realMatrix0.transpose().multiply(realMatrix0)).getSolver();
			     arrayRealVector0 = new ArrayRealVector(doubleArray0);
			     resultFollowupVector = singularValueDecomposition0.solve((RealVector) realMatrix0.transpose().preMultiply(arrayRealVector0));
			     testResult = resultFollowupVector.toString().equals(resultInitialVector.toString());
				
			break;
			case "MultiplyWithIdentity":
				//test1				
				//solver = new SingularValueDecomposition(m.multiply(MatrixUtils.createRealIdentityMatrix(2))).getSolver();
				//resultFollowup = solver.solve(b);
				//testResult = resultFollowup.toString().equals(xMatrix.toString());
				
				/*matrixModified = MatrixUtils.createRealMatrix(testSquare); 
				solver = new SingularValueDecomposition(matrixModified.multiply(MatrixUtils.createRealIdentityMatrix(2))).getSolver();	*/			
				//test2
				/*resultFollowup = solver.solve(b);
				testResult = resultFollowup.toString().equals(xMatrix.toString());*/
				//test3
				/*resultFollowupVector = solver.solve(b.getColumnVector(0));
				testResult = resultFollowupVector.toString().equals(resultInitialVector.toString());*/
				//test4
				/*resultFollowupVector = solver.solve(b.getColumnVector(1));
				testResult = resultFollowupVector.toString().equals(resultInitialVector.toString());*/
				//test5,6
				/*resultFollowupVector = solver.solve(v);
				testResult = resultFollowupVector.toString().equals(resultInitialVector.toString());*/
    		break;
			case "negation":
				//developer
				//solver = new SingularValueDecomposition(m.scalarMultiply(-1.0)).getSolver();
				//resultFollowup = solver.solve(b.scalarMultiply(-1.0));
				//testResult = resultFollowup.toString().equals(xMatrix.toString());
				
				/*matrixModified = MatrixUtils.createRealMatrix(testSquare); 
				solver = new SingularValueDecomposition(matrixModified.scalarMultiply(-1.0)).getSolver();*/
				//test2
				/*resultFollowup = solver.solve(b.scalarMultiply(-1.0));
				testResult = resultFollowup.toString().equals(xMatrix.toString());*/
				//test3
				/*resultFollowupVector = solver.solve(b.getColumnVector(0).mapMultiply(-1.0));
				testResult = resultFollowupVector.toString().equals(resultInitialVector.toString());*/
				//test4
				/*resultFollowupVector = solver.solve(b.getColumnVector(1).mapMultiply(-1.0));
				testResult = resultFollowupVector.toString().equals(resultInitialVector.toString());*/
				//test5,6
				/*resultFollowupVector = solver.solve(v.mapMultiply(-1.0));
				testResult = resultFollowupVector.toString().equals(resultInitialVector.toString());*/
				//evosuite
				//branch
				//singularValueDecomposition0 = new SingularValueDecomposition(diagonalMatrix0.scalarMultiply(-1.0)).getSolver();
			    //arrayRealVector0 = new ArrayRealVector(doubleArray0);
			    //resultFollowupVector = singularValueDecomposition0.solve((RealVector) arrayRealVector0.mapMultiply(-1.0));
			    //line1
				//singularValueDecomposition0 = new SingularValueDecomposition(diagonalMatrix0.scalarMultiply(-1.0)).getSolver();
			    //arrayRealVector0 = new ArrayRealVector(doubleArray0, true);
			    //resultFollowupVector = singularValueDecomposition0.solve((RealVector) arrayRealVector0.mapMultiply(-1.0));
			    
			    //testResult = resultFollowupVector.toString().equals(resultInitialVector.toString());
			    //line2
				//singularValueDecomposition0 = new SingularValueDecomposition(array2DRowRealMatrix0.scalarMultiply(-1.0)).getSolver();
				//resultFollowup = (Array2DRowRealMatrix)singularValueDecomposition0.solve((RealMatrix) array2DRowRealMatrix0.scalarMultiply(-1.0));
			    
			    //testResult = resultFollowup.toString().equals(array2DRowRealMatrix1.toString());
				//wm1
				//singularValueDecomposition_Solver0 = new SingularValueDecomposition(realMatrix0.scalarMultiply(-1.0)).getSolver();
				//resultFollowup = singularValueDecomposition_Solver0.solve(realMatrix0.scalarMultiply(-1.0));
			    
			    //testResult = resultFollowup.toString().equals(resultInitial.toString());
				//wm2
				singularValueDecomposition0 = new SingularValueDecomposition(realMatrix0.scalarMultiply(-1.0)).getSolver();
			    arrayRealVector0 = new ArrayRealVector(doubleArray0);
			    resultFollowupVector = singularValueDecomposition0.solve((RealVector) arrayRealVector0.mapMultiply(-1.0));
			    testResult = resultFollowupVector.toString().equals(resultInitialVector.toString());
				
			break;
	        default: MRname = "Invalid MR";
	                 break;
			 }
		 }catch (Exception ex) {
			 try {
				 FileWriter writer = new FileWriter("C:\\MRtest\\SingularValueDecomposition_exception.txt",true);
				File file = new File("C:\\MRtest\\SingularValueDecomposition_exception.txt");
				file.createNewFile();
				writer.append(System.getProperty("FaultName"));
			    writer.append(',');
			    writer.append(System.getProperty("MRname"));
			    writer.append(',');
			    writer.append(System.getProperty("TestNo")); 
			    writer.append('\n');  
			    writer.close();				     
			 } catch (IOException ex1) {
			   // report
			 } 
			   // report
			 System.exit(0);
			// testResult = false;
			 }
return testResult;
	}
	public boolean QRDecomposition(){
		String MRname = System.getProperty("MRname");
		Boolean testResult	= true;
		Matrix matrix = null;
		Vector vector = null;
		Matrix matrixAdd = null;
		Vector vectorAdd = null;
		Vector vectorInitial = null;
		Vector vectorFollowup = null;
		Matrix matrixrandom = null;
		RealMatrix matrixModified = null;
		RealMatrix resultFollowup = null;
		RealVector resultInitialVector = null;
		RealVector resultFollowupVector = null;
		RealVector b1 = null;
		//developer test suite
		 double[][] testData3x3NonSingular = {
		            { 12, -51,   4 },
		            {  6, 167, -68 },
		            { -4,  24, -41 }
		    };
		 QRDecomposition decomposition =
		            new QRDecomposition(MatrixUtils.createRealMatrix(testData3x3NonSingular));
		 DecompositionSolver solver = decomposition.getSolver();
		 RealMatrix b = MatrixUtils.createRealMatrix(new double[][] {
		                { -102, 12250 }, { 544, 24500 }, { 167, -36750 }
		        });
		 RealMatrix xRef = MatrixUtils.createRealMatrix(new double[][] {
		                { 1, 2515 }, { 2, 422 }, { -3, 898 }
		        }); 
		//test1 using RealMatrix
		 //RealMatrix resultInitial = solver.solve(b); 
		//test2 using ArrayRealVector		 
		 resultInitialVector = solver.solve(b.getColumnVector(0));
		//test3 using ArrayRealVector
		// resultInitialVector = solver.solve(b.getColumnVector(1));
	    /* for (int i = 0; i < b.getColumnDimension(); ++i) {
	    	 resultInitial = solver.solve(b.getColumnVector(i));
	            //final double error = x.subtract(xRef.getColumnVector(i)).getNorm();
	            //Assert.assertEquals(0, error, 3.0e-16 * xRef.getColumnVector(i).getNorm());
	     }*/
	  //test4 using RealVector with an alternate implementation 
		 /*ArrayRealVectorTest.RealVectorTestImpl v =
	                new ArrayRealVectorTest.RealVectorTestImpl(b.getColumn(0));
		 resultInitialVector = solver.solve(v);*/
	  //test5 using RealVector with an alternate implementation  
		 /*ArrayRealVectorTest.RealVectorTestImpl v =
	                new ArrayRealVectorTest.RealVectorTestImpl(b.getColumn(1));
		 resultInitialVector = solver.solve(v);*/
	     /*RealVector x = null;
	     for (int i = 0; i < b.getColumnDimension(); ++i) {
	            ArrayRealVectorTest.RealVectorTestImpl v =
	                new ArrayRealVectorTest.RealVectorTestImpl(b.getColumn(i));
	            x = solver.solve(v);
	            //final double error = x.subtract(xRef.getColumnVector(i)).getNorm();
	            //Assert.assertEquals(0, error, 3.0e-16 * xRef.getColumnVector(i).getNorm());
	      }*/
		 //evosuite
		 //wm
		 /*double[] doubleArray0 = new double[6];
	     doubleArray0[0] = (-1148.537155311);
	     Array2DRowRealMatrix array2DRowRealMatrix0 = new Array2DRowRealMatrix(doubleArray0);
	     QRDecomposition qRDecomposition0 = new QRDecomposition(array2DRowRealMatrix0, 1.0);
	     DecompositionSolver decompositionSolver0 = qRDecomposition0.getSolver();
	     BlockRealMatrix blockRealMatrix0 = (BlockRealMatrix)decompositionSolver0.solve((RealMatrix) array2DRowRealMatrix0);*/
	     //line
	     /*double[] doubleArray0 = new double[3];
	     double[][] doubleArray1 = new double[7][6];
	     doubleArray1[0] = doubleArray0;
	     doubleArray0[0] = (-15.0);
	     Array2DRowRealMatrix array2DRowRealMatrix0 = new Array2DRowRealMatrix(doubleArray0);
	     QRDecomposition qRDecomposition_Solver0 = new QRDecomposition(array2DRowRealMatrix0,(-0.4920160107399424));	 
	     DecompositionSolver decompositionSolver0 = qRDecomposition_Solver0.getSolver();
	     //QRDecomposition.Solver qRDecomposition_Solver0 = new QRDecomposition.Solver(doubleArray1, doubleArray0, (-0.4920160107399424));
	     RealVectorAbstractTest.RealVectorTestImpl realVectorAbstractTest_RealVectorTestImpl0 = new RealVectorAbstractTest.RealVectorTestImpl(doubleArray0);
	     resultInitialVector = decompositionSolver0.solve((RealVector) realVectorAbstractTest_RealVectorTestImpl0);*/
	     //branch1
	     /*double[] doubleArray0 = new double[7];
	     doubleArray0[0] = 629.9297221487707;
	     Array2DRowRealMatrix array2DRowRealMatrix0 = new Array2DRowRealMatrix(doubleArray0);
	     double[][] doubleArray1 = new double[11][8];
	     doubleArray1[0] = doubleArray0;
	     QRDecomposition qRDecomposition0 = new QRDecomposition(array2DRowRealMatrix0, (-2332.376534698));
	     DecompositionSolver decompositionSolver0 = qRDecomposition0.getSolver(); 	     
	     //QRDecomposition.Solver qRDecomposition_Solver0 = new QRDecomposition.Solver(doubleArray1, doubleArray0, (-2332.376534698));
	     BlockRealMatrix blockRealMatrix0 = (BlockRealMatrix)decompositionSolver0.solve((RealMatrix) array2DRowRealMatrix0);*/
	     //branch2
	     /*double[][] doubleArray0 = new double[4][6];
	     double[] doubleArray1 = new double[3];
	     doubleArray1[0] = (-364.0859343743173);
	     doubleArray1[1] = 9.4221;
	     doubleArray1[2] = (-3464.4680862236);
	     doubleArray0[0] = doubleArray1;
	     Array2DRowRealMatrix array2DRowRealMatrix0 = new Array2DRowRealMatrix(doubleArray1);
	     QRDecomposition qRDecomposition0 = new QRDecomposition(array2DRowRealMatrix0, 0.0);
	     DecompositionSolver decompositionSolver0 = qRDecomposition0.getSolver(); 
	     //QRDecomposition.Solver qRDecomposition_Solver0 = new QRDecomposition.Solver(doubleArray0, doubleArray1, 0.0);
	     OpenMapRealVector openMapRealVector0 = new OpenMapRealVector(doubleArray1, (-2089.5296));
	     resultInitialVector = decompositionSolver0.solve((RealVector) openMapRealVector0);*/
	       
	    try{	 
			 switch (MRname) {			
			 case "QRDecomposition":
			 try {
				FileWriter writer = new FileWriter("C:\\MRtest\\"+MRname+".txt",true);
				File file = new File("C:\\MRtest\\"+MRname+".txt");
				if ( file.exists() ){
					 //file.delete();
			    }
				//file.createNewFile(); 
		    	writer.write(System.getProperty("FaultName"));
		    	writer.append('\n'); 
		    	//writer.write(resultInitial.toString());
		    	writer.write(resultInitialVector.toString());
		    	//writer.write(blockRealMatrix0.toString());
		    	//writer.write(realVector0.toString());
		    	writer.append('\n');
			    writer.close();				     
			 } catch (IOException ex) {
			   // report
			 } 
	        break;    
			case "Multiplication":	
				//developer
				/*matrixModified = MatrixUtils.createRealMatrix(testData3x3NonSingular);
				matrixModified = matrixModified.scalarMultiply(2.0);
				decomposition = new QRDecomposition(matrixModified);
				solver = decomposition.getSolver();*/
				//test1
				/*b = b.scalarMultiply(2.0);
				resultFollowup = solver.solve(b);
				testResult = resultFollowup.toString().equals(resultInitial.toString());*/
				//test2
				//resultFollowupVector = solver.solve(b.getColumnVector(0).mapMultiply(2.0));
				//test3
				//resultFollowupVector = solver.solve(b.getColumnVector(1).mapMultiply(2.0));
				//test4,5
				//resultFollowupVector = solver.solve(v.mapMultiply(2.0));
				
				//testResult = resultFollowupVector.toString().equals(resultInitialVector.toString());	
				//wm
				 //qRDecomposition0 = new QRDecomposition(array2DRowRealMatrix0.scalarMultiply(2.0), 1.0*2.0);
			     //decompositionSolver0 = qRDecomposition0.getSolver();
			     //resultFollowup = (BlockRealMatrix)decompositionSolver0.solve((RealMatrix) array2DRowRealMatrix0.scalarMultiply(2.0));
			     
				//line
			    /*qRDecomposition_Solver0 = new QRDecomposition(array2DRowRealMatrix0.scalarMultiply(2.0),(-0.4920160107399424));	 
			    decompositionSolver0 = qRDecomposition_Solver0.getSolver();
				//qRDecomposition_Solver0 = new QRDecomposition.Solver(doubleArray1, doubleArray0, (-0.4920160107399424*2.0));
			    realVectorAbstractTest_RealVectorTestImpl0 = new RealVectorAbstractTest.RealVectorTestImpl(doubleArray0);
			    resultFollowupVector = decompositionSolver0.solve((RealVector) realVectorAbstractTest_RealVectorTestImpl0.mapMultiply(2.0));*/
			     
				//branch1
				 /*qRDecomposition0 = new QRDecomposition(array2DRowRealMatrix0.scalarMultiply(2.0), (-2332.376534698));
			     decompositionSolver0 = qRDecomposition0.getSolver(); 	     
			     //QRDecomposition.Solver qRDecomposition_Solver0 = new QRDecomposition.Solver(doubleArray1, doubleArray0, (-2332.376534698));
			     resultFollowup = (BlockRealMatrix)decompositionSolver0.solve((RealMatrix) array2DRowRealMatrix0.scalarMultiply(2.0));*/
			      
				//branch2
				//qRDecomposition0 = new QRDecomposition(array2DRowRealMatrix0.scalarMultiply(2.0), 0.0);
			    //decompositionSolver0 = qRDecomposition0.getSolver(); 
			    //resultFollowupVector = decompositionSolver0.solve((RealVector) openMapRealVector0.mapMultiply(2.0));
			     
			     //testResult = resultFollowup.toString().equals(blockRealMatrix0.toString());	
			    testResult = resultFollowupVector.toString().equals(resultInitialVector.toString());	
							
     		break; 
			case "permuteRow":
				//developer
				 /*matrixModified = MatrixUtils.createRealMatrix(testData3x3NonSingular);
				 RealMatrix a = matrixModified.getRowMatrix(0);
				 RealMatrix c = matrixModified.getRowMatrix(1);
				 matrixModified.setRowMatrix(0, c);
				 matrixModified.setRowMatrix(1, a);
				 decomposition = new QRDecomposition(matrixModified);
				 solver = decomposition.getSolver();*/
				//test1
				/* RealMatrix d = b.getRowMatrix(0);
				 RealMatrix e = b.getRowMatrix(1);
				 b.setRowMatrix(0, e);
				 b.setRowMatrix(1, d);
				 resultFollowup = solver.solve(b);				 
				 testResult = resultFollowup.toString().equals(resultInitial.toString());	*/
				 //test2
				 /*b1 = b.getColumnVector(0);
				 double b2 = b1.getEntry(0);
				 double b3 = b1.getEntry(1);
				 b1.setEntry(1, b2);
				 b1.setEntry(0, b3);
				 resultFollowupVector = solver.solve(b1);*/
				//test3
				 /*b1 = b.getColumnVector(1);
				 double b2 = b1.getEntry(0);
				 double b3 = b1.getEntry(1);
				 b1.setEntry(1, b2);
				 b1.setEntry(0, b3);
				 resultFollowupVector = solver.solve(b1);*/
				//test4,5 
				 /*double b2 = v.getEntry(0);
				 double b3 = v.getEntry(1);
				 v.setEntry(1, b2);
				 v.setEntry(0, b3);
				 resultFollowupVector = solver.solve(v);*/
				 
				 //testResult = resultFollowupVector.toString().equals(resultInitialVector.toString());
				 //wm
				 /*double temp = doubleArray0[0];
				 doubleArray0[0] = doubleArray0[1];
				 doubleArray0[1] = temp;
				 array2DRowRealMatrix0 = new Array2DRowRealMatrix(doubleArray0);
			     qRDecomposition0 = new QRDecomposition(array2DRowRealMatrix0, 1.0);
			     decompositionSolver0 = qRDecomposition0.getSolver();
			     resultFollowup = (BlockRealMatrix)decompositionSolver0.solve((RealMatrix) array2DRowRealMatrix0);*/
				
			     //line
				 /*double[] d1 = array2DRowRealMatrix0.getRow(0);
				 double[] d2 = array2DRowRealMatrix0.getRow(1);
				 array2DRowRealMatrix0.setRow(0, d2);
				 array2DRowRealMatrix0.setRow(1, d1);
				 qRDecomposition_Solver0 = new QRDecomposition(array2DRowRealMatrix0,(-0.4920160107399424));	 
				 decompositionSolver0 = qRDecomposition_Solver0.getSolver();					
			     realVectorAbstractTest_RealVectorTestImpl0 = new RealVectorAbstractTest.RealVectorTestImpl(doubleArray0);
			     double temp1 = realVectorAbstractTest_RealVectorTestImpl0.getEntry(0);
			     double temp2 = realVectorAbstractTest_RealVectorTestImpl0.getEntry(1);
			     realVectorAbstractTest_RealVectorTestImpl0.setEntry(1, temp1);
			     realVectorAbstractTest_RealVectorTestImpl0.setEntry(0, temp2);
			     resultFollowupVector = decompositionSolver0.solve((RealVector) realVectorAbstractTest_RealVectorTestImpl0);
				*/
			     //branch1
				 /*RealMatrix temp1 = array2DRowRealMatrix0.getRowMatrix(0);
				 RealMatrix temp2 = array2DRowRealMatrix0.getRowMatrix(1);
				 array2DRowRealMatrix0.setRowMatrix(1, temp1);
				 array2DRowRealMatrix0.setRowMatrix(0, temp2);
				 qRDecomposition0 = new QRDecomposition(array2DRowRealMatrix0, (-2332.376534698));
			     decompositionSolver0 = qRDecomposition0.getSolver(); 	     
			     //QRDecomposition.Solver qRDecomposition_Solver0 = new QRDecomposition.Solver(doubleArray1, doubleArray0, (-2332.376534698));
			     resultFollowup = (BlockRealMatrix)decompositionSolver0.solve((RealMatrix) array2DRowRealMatrix0);*/
			     
				 //branch2
				 /*double[] d1 = array2DRowRealMatrix0.getRow(0);
				 double[] d2 = array2DRowRealMatrix0.getRow(1);
				 array2DRowRealMatrix0.setRow(0, d2);
				 array2DRowRealMatrix0.setRow(1, d1);
				 double a1 = openMapRealVector0.getEntry(0);
				 double a2 = openMapRealVector0.getEntry(1);
				 openMapRealVector0.setEntry(0, a2);
				 openMapRealVector0.setEntry(1, a1);
			     qRDecomposition0 = new QRDecomposition(array2DRowRealMatrix0, 0.0);
			     decompositionSolver0 = qRDecomposition0.getSolver(); 
			     resultFollowupVector = decompositionSolver0.solve((RealVector) openMapRealVector0);*/
			     
			     //testResult = resultFollowup.toString().equals(blockRealMatrix0.toString());
			     testResult = resultFollowupVector.toString().equals(resultInitialVector.toString());
				 
	        break;
     		case "matrixAdd":
     			//developer
     			/*matrixModified = MatrixUtils.createRealMatrix(testData3x3NonSingular);
     			matrixModified = matrixModified.add(matrixModified);
     			decomposition = new QRDecomposition(matrixModified);
				solver = decomposition.getSolver();*/
				//test1
				/*b = b.add(b);
				resultFollowup = solver.solve(b);				 
				testResult = resultFollowup.toString().equals(resultInitial.toString());*/
				//test2
				 /*b1 = b.getColumnVector(0).add(b.getColumnVector(0));
				 resultFollowupVector = solver.solve(b1);*/
				//test3
				 //b1 = b.getColumnVector(1).add(b.getColumnVector(1));
				 //resultFollowupVector = solver.solve(b1);
				 //test4,5 
				 //resultFollowupVector = solver.solve(v.add(v));
				 
				 //testResult = resultFollowupVector.toString().equals(resultInitialVector.toString());
				//wm
     			 /*array2DRowRealMatrix0 = array2DRowRealMatrix0.add(array2DRowRealMatrix0);
     			 qRDecomposition0 = new QRDecomposition(array2DRowRealMatrix0, 1.0);
     		     decompositionSolver0 = qRDecomposition0.getSolver();
     		     resultFollowup = (BlockRealMatrix)decompositionSolver0.solve((RealMatrix) array2DRowRealMatrix0);*/
     		    //line
     			//qRDecomposition_Solver0 = new QRDecomposition(array2DRowRealMatrix0.add(array2DRowRealMatrix0),(-0.4920160107399424));	 
				//decompositionSolver0 = qRDecomposition_Solver0.getSolver();					   
     			//resultFollowupVector = decompositionSolver0.solve((RealVector) realVectorAbstractTest_RealVectorTestImpl0.add(realVectorAbstractTest_RealVectorTestImpl0));
     		     
     			//branch1
     			 /*qRDecomposition0 = new QRDecomposition(array2DRowRealMatrix0.add(array2DRowRealMatrix0), (-2332.376534698));
			     decompositionSolver0 = qRDecomposition0.getSolver(); 	     
			     //QRDecomposition.Solver qRDecomposition_Solver0 = new QRDecomposition.Solver(doubleArray1, doubleArray0, (-2332.376534698));
			     resultFollowup = (BlockRealMatrix)decompositionSolver0.solve((RealMatrix) array2DRowRealMatrix0.add(array2DRowRealMatrix0));*/
			     
     			//branch2			    
     			 //qRDecomposition0 = new QRDecomposition(array2DRowRealMatrix0.add(array2DRowRealMatrix0), 0.0);
			     //decompositionSolver0 = qRDecomposition0.getSolver(); 
			     //resultFollowupVector = decompositionSolver0.solve((RealVector) openMapRealVector0.add(openMapRealVector0));
			      
     			 //testResult = resultFollowup.toString().equals(blockRealMatrix0.toString());
			     testResult = resultFollowupVector.toString().equals(resultInitialVector.toString());
				     			
    		break;  
			case "transpose":				
				//developer
     			/*matrixModified = MatrixUtils.createRealMatrix(testData3x3NonSingular);
     			RealMatrix matrixT =  matrixModified.transpose();
     			matrixModified = matrixT.multiply(matrixModified);
     			decomposition = new QRDecomposition(matrixModified);
				solver = decomposition.getSolver();*/
				//test1
				/*
				b = matrixT.multiply(b);
				resultFollowup = solver.solve(b);				 
				testResult = resultFollowup.toString().equals(resultInitial.toString());*/
				//test2
				/*b = matrixT.multiply(b);
				resultFollowupVector = solver.solve(b.getColumnVector(0));*/
				//test3
				//b = matrixT.multiply(b);
				//resultFollowupVector = solver.solve(b.getColumnVector(1));
				//test4
				/*b = matrixT.multiply(b);
				v = new ArrayRealVectorTest.RealVectorTestImpl(b.getColumn(0));
				resultFollowupVector = solver.solve(v);*/
				//test5
				/*b = matrixT.multiply(b);
				v = new ArrayRealVectorTest.RealVectorTestImpl(b.getColumn(1));
				resultFollowupVector = solver.solve(v);*/
				 
				//testResult = resultFollowupVector.toString().equals(resultInitialVector.toString());
				//wm
				/*matrixModified = array2DRowRealMatrix0.transpose();
				RealMatrix array2DRowRealMatrix1 = matrixModified.multiply(array2DRowRealMatrix0);
    			qRDecomposition0 = new QRDecomposition(array2DRowRealMatrix1, 1.0);
    		    decompositionSolver0 = qRDecomposition0.getSolver();
    		    resultFollowup = (BlockRealMatrix)decompositionSolver0.solve((RealMatrix) array2DRowRealMatrix1);*/
    		    //line
				/*matrixModified = array2DRowRealMatrix0.transpose();
				qRDecomposition_Solver0 = new QRDecomposition(matrixModified.multiply(array2DRowRealMatrix0),(-0.4920160107399424));	 
				decompositionSolver0 = qRDecomposition_Solver0.getSolver();	
  			    b1 = matrixModified.multiply(array2DRowRealMatrix0).getColumnVector(0);
			    resultFollowupVector = decompositionSolver0.solve( b1);*/
			    //branch1
				 /*qRDecomposition0 = new QRDecomposition(array2DRowRealMatrix0.transpose().multiply(array2DRowRealMatrix0), (-2332.376534698));
			     decompositionSolver0 = qRDecomposition0.getSolver(); 	     
			     //QRDecomposition.Solver qRDecomposition_Solver0 = new QRDecomposition.Solver(doubleArray1, doubleArray0, (-2332.376534698));
			     resultFollowup = (BlockRealMatrix)decompositionSolver0.solve((RealMatrix) array2DRowRealMatrix0.transpose().multiply(array2DRowRealMatrix0));*/
			     //branch2
				/*matrixModified = array2DRowRealMatrix0.transpose();
				qRDecomposition0 = new QRDecomposition(matrixModified.multiply(array2DRowRealMatrix0), 0.0);
			    decompositionSolver0 = qRDecomposition0.getSolver(); 
			    resultFollowupVector = decompositionSolver0.solve((RealVector) matrixModified.multiply(array2DRowRealMatrix0).getColumnVector(0));*/
			      
			    //testResult = resultFollowup.toString().equals(blockRealMatrix0.toString());
    		    testResult = resultFollowupVector.toString().equals(resultInitialVector.toString());
				
    		break;
			case "MultiplyWithIdentity":
				//developer
     			/*matrixModified = MatrixUtils.createRealMatrix(testData3x3NonSingular);
     			RealMatrix matrixid = MatrixUtils.createRealIdentityMatrix(3);
     			matrixModified = matrixModified.multiply(matrixid);
     			decomposition = new QRDecomposition(matrixModified);
				solver = decomposition.getSolver();*/
				//test1
				/*resultFollowup = solver.solve(b);				 
				testResult = resultFollowup.toString().equals(resultInitial.toString());*/
				//test2
				//resultFollowupVector = solver.solve(b.getColumnVector(0));
				//test3
				//resultFollowupVector = solver.solve(b.getColumnVector(1));
				//test4,5
				//resultFollowupVector = solver.solve(v);
				
				//testResult = resultFollowupVector.toString().equals(resultInitialVector.toString());
				
    		break;
			case "negation":
				//developer
     			/*matrixModified = MatrixUtils.createRealMatrix(testData3x3NonSingular);
     			matrixModified = matrixModified.scalarMultiply(-1.0);     			
     			decomposition = new QRDecomposition(matrixModified);
				solver = decomposition.getSolver();*/
				//test1
				/*b = b.scalarMultiply(-1.0);
				 resultFollowup = solver.solve(b);				 
				testResult = resultFollowup.toString().equals(resultInitial.toString());*/
				//test2
				/*b1 = b.getColumnVector(0).mapMultiply(-1.0);
				resultFollowupVector = solver.solve(b1);*/
				//test3
				//b1 = b.getColumnVector(1).mapMultiply(-1.0);
				//resultFollowupVector = solver.solve(b1);
				//test4,5
				//resultFollowupVector = solver.solve(v.mapMultiply(-1.0));
				
				//testResult = resultFollowupVector.toString().equals(resultInitialVector.toString());
				//wm
				 //qRDecomposition0 = new QRDecomposition(array2DRowRealMatrix0.scalarMultiply(-1.0), 1.0*(-1.0));
			     //decompositionSolver0 = qRDecomposition0.getSolver();
			     //resultFollowup = (BlockRealMatrix)decompositionSolver0.solve((RealMatrix) array2DRowRealMatrix0.scalarMultiply(-1.0));
			     
				//line
				/*qRDecomposition_Solver0 = new QRDecomposition(array2DRowRealMatrix0.scalarMultiply(-1.0),(-0.4920160107399424));	 
			    decompositionSolver0 = qRDecomposition_Solver0.getSolver();
				//qRDecomposition_Solver0 = new QRDecomposition.Solver(doubleArray1, doubleArray0, (-0.4920160107399424*2.0));
			    realVectorAbstractTest_RealVectorTestImpl0 = new RealVectorAbstractTest.RealVectorTestImpl(doubleArray0);
			    resultFollowupVector = decompositionSolver0.solve((RealVector) realVectorAbstractTest_RealVectorTestImpl0.mapMultiply(-1.0));
			     */
				//branch1
				 /*qRDecomposition0 = new QRDecomposition(array2DRowRealMatrix0.scalarMultiply(-1.0), (-2332.376534698));
			     decompositionSolver0 = qRDecomposition0.getSolver(); 	     
			     //QRDecomposition.Solver qRDecomposition_Solver0 = new QRDecomposition.Solver(doubleArray1, doubleArray0, (-2332.376534698));
			     resultFollowup = (BlockRealMatrix)decompositionSolver0.solve((RealMatrix) array2DRowRealMatrix0.scalarMultiply(-1.0));*/
			      
				//branch2
				 //qRDecomposition0 = new QRDecomposition(array2DRowRealMatrix0.scalarMultiply(-1.0), 0.0);
			     //decompositionSolver0 = qRDecomposition0.getSolver(); 
			     //resultFollowupVector = decompositionSolver0.solve((RealVector) openMapRealVector0.mapMultiply(-1.0));
			     
			    //testResult = resultFollowup.toString().equals(blockRealMatrix0.toString());
    		    testResult = resultFollowupVector.toString().equals(resultInitialVector.toString());
				

			break;
	        default: MRname = "Invalid MR";
	                 break;
			 }
		 }catch (Exception ex) {
			 try {
				 FileWriter writer = new FileWriter("C:\\MRtest\\QRDecomposition_exception.txt",true);
				File file = new File("C:\\MRtest\\QRDecomposition_exception.txt");
				file.createNewFile();
				writer.append(System.getProperty("FaultName"));
			    writer.append(',');
			    writer.append(System.getProperty("MRname"));
			    writer.append(',');
			    writer.append(System.getProperty("TestNo")); 
			    writer.append('\n');  
			    writer.close();				     
			 } catch (IOException ex1) {
			   // report
			 } 
			   // report
			 System.exit(0);
			// testResult = false;
			 }
return testResult;
	}
	public boolean SquareRootSolver(){
		String MRname = System.getProperty("MRname");
		Boolean testResult	= true;
		Matrix matrix = null;
		Vector vector = null;
		Matrix matrixAdd = null;
		Vector vectorAdd = null;
		Vector vectorInitial = null;
		Vector vectorFollowup = null;
		Matrix matrixrandom = null;
		//for test
		/*double[][] arr = {{ 1 , -2 ,  3}, { 5,   8 , -1},{ 2,   1 ,  1},{-1,   4 , -3}};
		double[] arr1 = {2,-1,3,-2};
		//double[][] arr = {{ 1 , -2 ,  3}, { 5,   8 , -1},{ 2,   1 ,  1}};
		//double[] arr1 = {2,-1,3};
		matrixrandom = Matrix.from2DArray(arr);
		LeastSquaresSolver leastSquaresSolver0 = new LeastSquaresSolver(matrixrandom);
		vector = Vector.fromArray(arr1);
		vectorInitial = leastSquaresSolver0.solve(vector);*/
		//developer test suite
				//dev_1
				double a[][] = new double[][] { 
		            { 44.0 } 
		        };
		        double b[] = new double[] { -22.0 };
		        //dev_2
		        /*double a[][] = new double[][] { 
		            { -7.0, 77.0 },
		            { 77.0, 14.0 }
		        };
		        double b[] = new double[] { -77.0, 768.6 };*/
		        //dev_3
		        /*double a[][] = new double[][] { 
		            { 1.0, 0.0, 0.0 }, 
		            { 0.0, 5.0, 0.0 },
		            { 0.0, 0.0, 9.0 } 
		        };
		        double b[] = new double[] { 0.0, 2.0, 0.0 };*/
		        //dev_4
		        /*double a[][] = new double[][] { 
		            { 9.0, -1.0, -18.0 }, 
		            { -1.0, 6.0, -3.0 },
		            { -18.0, -3.0, 33.0 } 
		        };
		        double b[] = new double[] { -45.0, -10.0, 81.0 };*/
		        //dev_5
		        /*double a[][] = new double[][] { 
		            { -55.0, 11.0, 1.0, 0.0 }, 
		            { 11.0, 66.0, 4.0, -1.0 },
		            { 1.0, 4.0, -44.0, -9.0 },
		            { 0.0, -1.0, -9.0, 33.0 }
		        };
		        double b[] = new double[] { -276.0, 29.0, -439.0, -89.0 };*/
		        //dev_6
		        /*double a[][] = new double[][] { 
		            { 14.0, 0.0, -10.0, 11.0, -4.0 },
		            { 0.0, 21.0, -2.0, 0.0, 11.0 },
		            { -10.0, -2.0, -7.0, 1.0, -3.0 },
		            { 11.0, 0.0, 1.0, -28.0, 6.0  },
		            { -4.0, 11.0, -3.0, 6.0, 0.7 },
		        };
		        double b[] = new double[] { 104.0, -256.0, -45.0, 54.0, -143.8 };*/
		        
		        Matrix matrixDev = Matrix.from2DArray(a);
				Vector vectorDev = Vector.fromArray(b);
				SquareRootSolver squareRootSolver0 = new SquareRootSolver(matrixDev);
				vectorInitial = squareRootSolver0.solve(vectorDev);
		//wm
		  /*InPlaceCopyMatrixToMatrix inPlaceCopyMatrixToMatrix0 = new InPlaceCopyMatrixToMatrix();
	      SparseMatrix sparseMatrix0 = SparseMatrix.identity(10);
	      MockRandom mockRandom0 = new MockRandom();
	      DenseMatrix denseMatrix0 = DenseMatrix.randomSymmetric(10, mockRandom0);
	      Matrix matrix0 = inPlaceCopyMatrixToMatrix0.applySimple(sparseMatrix0, denseMatrix0);
	      SquareRootSolver squareRootSolver0 = new SquareRootSolver(matrix0);
	      SparseVector sparseVector0 = SparseVector.zero(10);
	      CompressedVector compressedVector0 = (CompressedVector)squareRootSolver0.solve(sparseVector0);*/
		  
		  /*DenseMatrix denseMatrix0 = DenseMatrix.identity(12);
	      SquareRootSolver squareRootSolver0 = new SquareRootSolver(denseMatrix0);
	      DenseVector denseVector0 = DenseVector.zero(12);
	      Vector vector0 = squareRootSolver0.solve(denseVector0);*/
		
		  /*ColumnMajorSparseMatrix columnMajorSparseMatrix0 = ColumnMajorSparseMatrix.identity(16);
	      Matrix matrix0 = columnMajorSparseMatrix0.add(281.0);
	      SquareRootSolver squareRootSolver0 = new SquareRootSolver(matrix0);
	      SparseVector sparseVector0 = SparseVector.zero(16);
	      Vector vector0 = sparseVector0.add(281.0);
	      Vector vector1 = squareRootSolver0.solve(vector0);*/
	      //line
		  /*DenseMatrix denseMatrix0 = DenseMatrix.diagonal(18, 18);
	      SquareRootSolver squareRootSolver0 = new SquareRootSolver(denseMatrix0);
	      SparseVector sparseVector0 = SparseVector.zero(18);
	      CompressedVector compressedVector0 = (CompressedVector)squareRootSolver0.solve(sparseVector0);*/
	    //branch
	      /*SparseMatrix sparseMatrix0 = SparseMatrix.diagonal(6, 6);
	      SquareRootSolver squareRootSolver0 = new SquareRootSolver(sparseMatrix0);
	      MockRandom mockRandom0 = new MockRandom(6);
	      DenseVector denseVector0 = DenseVector.random(6, mockRandom0);
	      Vector vector0 = squareRootSolver0.solve(denseVector0);*/
	    try{	 
			 switch (MRname) {			
			 case "solve":
			 try {
				 FileWriter writer = new FileWriter(".//MRtest//"+MRname+".txt",true);
				File file = new File(".//MRtest//"+MRname+".txt");
				if ( file.exists() ){
					 //file.delete();
			    }
				//file.createNewFile();
		    	//writer.write(Integer.toString(a));
		    	//writer.append('\n'); 
		    	writer.write(System.getProperty("FaultName"));
		    	writer.append('\n'); 
		    	//writer.write(compressedVector1.toCSV());
		    	//writer.write(compressedVector0.toCSV());
		    	//writer.write(vector0.toCSV());
		    	//writer.write(vector1.toCSV());
		    	writer.write(vectorInitial.toCSV());
		    	writer.append('\n');
			    writer.close();				     
			 } catch (IOException ex) {
			   // report
			 } 
	                 break;    
			 case "Multiplication": 
				 //output vectorFollowup = 0.331, -0.189, 0.515
					/*matrixrandom = matrixrandom.multiply(0.5);
					leastSquaresSolver0 = new LeastSquaresSolver(matrixrandom);
					vector = vector.multiply(0.5);
					vectorFollowup = leastSquaresSolver0.solve(vector);*/
				 //developer
				 testResult= FrameMethod.Multiplication(matrixDev, vectorDev, vectorInitial,"SquareRootSolver");
				//wm followup = source
				 //testResult= FrameMethod.Multiplication(matrix0, sparseVector0, compressedVector0,"SquareRootSolver");
				 //testResult= FrameMethod.Multiplication(denseMatrix0, denseVector0, vector0,"SquareRootSolver");
				 //testResult= FrameMethod.Multiplication(matrix0, vector0, vector1,"SquareRootSolver");
				//line followup = source
				 //testResult= FrameMethod.Multiplication(denseMatrix0, sparseVector0, compressedVector0,"SquareRootSolver");
					
				//branch followup = source
				 //testResult= FrameMethod.Multiplication(sparseMatrix0, denseVector0, vector0,"SquareRootSolver");
     		 		break; 
			 case "permuteRow":
				//output vectorFollowup = 0.331, -0.189, 0.515
					/*matrixrandom.swapRows(1, 2);
					leastSquaresSolver0 = new LeastSquaresSolver(matrixrandom);
					vector.swapElements(1, 2);
					vectorFollowup = leastSquaresSolver0.solve(vector);*/
				 //developer
				 testResult= FrameMethod.permuteRow(matrixDev, vectorDev, vectorInitial,"SquareRootSolver");
				
				//wm not applicable followup = source 
				 //testResult= FrameMethod.permuteRow(matrix0, sparseVector0, compressedVector0,"SquareRootSolver");
				 //testResult= FrameMethod.permuteRow(denseMatrix0, denseVector0, vector0,"SquareRootSolver");
				//line not applicable followup = source
				 //testResult= FrameMethod.permuteRow(denseMatrix0, sparseVector0, compressedVector0,"SquareRootSolver");
					
				//branch not applicable followup = source
			     //testResult= FrameMethod.permuteRow(sparseMatrix0, denseVector0, vector0,"SquareRootSolver");
	         		 break;
     		case "matrixAdd":
     			//output vectorFollowup = 0.331, -0.189, 0.515
     			/*matrixrandom = matrixrandom.add(matrixrandom);
				leastSquaresSolver0 = new LeastSquaresSolver(matrixrandom);
				vector = vector.add(vector);
				vectorFollowup = leastSquaresSolver0.solve(vector);*/
     			 //developer
				 testResult= FrameMethod.matrixAdd(matrixDev, vectorDev, vectorInitial,"SquareRootSolver");
				
     			//wm followup = source
     			//testResult= FrameMethod.matrixAdd(matrix0, sparseVector0, compressedVector0,"SquareRootSolver");
     			//testResult= FrameMethod.matrixAdd(denseMatrix0, denseVector0, vector0,"SquareRootSolver");
     			//testResult= FrameMethod.matrixAdd(matrix0, vector0, vector1,"SquareRootSolver");
     			
				//line followup = source
     			//testResult= FrameMethod.matrixAdd(denseMatrix0, sparseVector0, compressedVector0,"SquareRootSolver");
				//branch followup = source
				 //testResult= FrameMethod.matrixAdd(sparseMatrix0, denseVector0, vector0,"SquareRootSolver");
    		 break;  
			case "transpose":
				//output vectorFollowup = 0.331, -0.189, 0.515
				/*matrix = matrixrandom.transpose();
				matrixrandom = matrix.multiply(matrixrandom);
				leastSquaresSolver0 = new LeastSquaresSolver(matrixrandom);
				vector = matrix.multiply(vector);
				vectorFollowup = leastSquaresSolver0.solve(vector);*/
				 //developer
				 testResult= FrameMethod.transpose(matrixDev, vectorDev, vectorInitial,"SquareRootSolver");
				
				//wm followup = source
				//testResult= FrameMethod.transpose(matrix0, sparseVector0, compressedVector0,"SquareRootSolver");
				//testResult= FrameMethod.transpose(denseMatrix0, denseVector0, vector0,"SquareRootSolver");
				//testResult= FrameMethod.transpose(matrix0, vector0, vector1,"SquareRootSolver");
				
				//line followup == source
				//testResult= FrameMethod.transpose(denseMatrix0, sparseVector0, compressedVector0,"SquareRootSolver");
				//branch followup = source
				 //testResult= FrameMethod.transpose(sparseMatrix0, denseVector0, vector0,"SquareRootSolver");
    		 break;
			case "MultiplyWithIdentity":
				//output vectorFollowup = 0.331, -0.189, 0.515
				/*matrix = Matrix.identity(matrixrandom.columns());
				matrixrandom = matrixrandom.multiply(matrix);
				leastSquaresSolver0 = new LeastSquaresSolver(matrixrandom);
				//vector = matrix.multiply(vector);
				vectorFollowup = leastSquaresSolver0.solve(vector);*/
				 //developer
				 testResult= FrameMethod.MultiplyWithIdentity(matrixDev, vectorDev, vectorInitial,"SquareRootSolver");
				
				//wm followup = source
				//testResult= FrameMethod.MultiplyWithIdentity(matrix0, sparseVector0, compressedVector0,"SquareRootSolver");
				//testResult= FrameMethod.MultiplyWithIdentity(denseMatrix0, denseVector0, vector0,"SquareRootSolver");
				//testResult= FrameMethod.MultiplyWithIdentity(matrix0, vector0, vector1,"SquareRootSolver");
				
				//line followup = source
				//testResult= FrameMethod.MultiplyWithIdentity(denseMatrix0, sparseVector0, compressedVector0,"SquareRootSolver");
				//branch followup = source
			     //testResult= FrameMethod.MultiplyWithIdentity(sparseMatrix0, denseVector0, vector0,"SquareRootSolver");
    		 break;
			case "negation":				
				//output vectorFollowup = 0.331, -0.189, 0.515
				/*matrixrandom = matrixrandom.multiply(-1.0);
				leastSquaresSolver0 = new LeastSquaresSolver(matrixrandom);
				vector = vector.multiply(-1.0);
				vectorFollowup = leastSquaresSolver0.solve(vector);*/
				 //developer
				 testResult= FrameMethod.Negation(matrixDev, vectorDev, vectorInitial,"SquareRootSolver");
				
				//wm followup = source
				//testResult= FrameMethod.Negation(matrix0, sparseVector0, compressedVector0,"SquareRootSolver");
				//testResult= FrameMethod.Negation(denseMatrix0, denseVector0, vector0,"SquareRootSolver");
				//testResult= FrameMethod.Negation(matrix0, vector0, vector1,"SquareRootSolver");
				
				//line followup = source
				//testResult= FrameMethod.Negation(denseMatrix0, sparseVector0, compressedVector0,"SquareRootSolver");
				//branch followup = source
				 //testResult= FrameMethod.Negation(sparseMatrix0, denseVector0, vector0,"SquareRootSolver");
				break;
	        default: MRname = "Invalid MR";
	                 break;
			 }
		 }catch (Exception ex) {
			 try {
				 FileWriter writer = new FileWriter(".//MRtest//SquareRootSolver_exception.txt",true);
				File file = new File(".//MRtest//SquareRootSolver_exception.txt");
				file.createNewFile();
				writer.append(System.getProperty("FaultName"));
			    writer.append(',');
			    writer.append(System.getProperty("MRname"));
			    writer.append(',');
			    writer.append(System.getProperty("TestNo")); 
			    writer.append('\n');  
			    writer.close();				     
			 } catch (IOException ex1) {
			   // report
			 } 
			   // report
			 System.exit(0);
			// testResult = false;
			 }
return testResult;
	}
	public boolean ForwardBackSubstitutionSolver(){
		String MRname = System.getProperty("MRname");
		Boolean testResult	= true;
		Matrix matrix = null;
		Vector vector = null;
		Matrix matrixAdd = null;
		Vector vectorAdd = null;
		Vector vectorInitial = null;
		Vector vectorFollowup = null;
		Matrix matrixrandom = null;
		//for test
		/*double[][] arr = {{ 1 , -2 ,  3}, { 5,   8 , -1},{ 2,   1 ,  1},{-1,   4 , -3}};
		double[] arr1 = {2,-1,3,-2};
		//double[][] arr = {{ 1 , -2 ,  3}, { 5,   8 , -1},{ 2,   1 ,  1}};
		//double[] arr1 = {2,-1,3};
		matrixrandom = Matrix.from2DArray(arr);
		LeastSquaresSolver leastSquaresSolver0 = new LeastSquaresSolver(matrixrandom);
		vector = Vector.fromArray(arr1);
		vectorInitial = leastSquaresSolver0.solve(vector);*/
		//developer test suite
		//dev_1
		/*double a[][] = new double[][] {
                { 62.0 }
        };
        double b[] = new double[] { -31.0 };*/
        //dev_2
        /*double a[][] = new double[][] {
                { 18.0, 4.0 },
                { 10.0, -2.0 }
        };
        double b[] = new double[] { 11.0, 4.0 };*/
        //dev_3
		/*double a[][] = new double[][] {
            { -19.0, 15.0, 4.0 },
            { 2.0, -2.0, 1.0 },
            { 9.0, 55.0, -1.0 }
		};
		double b[] = new double[] { -146.0, 16.5, 7.5 };*/
        //dev_4
        /*double a[][] = new double[][] {
                { 1.0, 0.0, -10.0 },
                { 20.0, 5.0, -1.0},
                { 0.0, 0.0, -100.0 }
        };
        double b[] = new double[] { 8.0, 115.2, 20.0 };*/
        //dev_5
        /*double a[][] = new double[][] {
                { 7.0, -42.0, 11.0, 2.0 },
                { 8.0, 1.0, -2.0, -54.0 },
                { 6.0, 0.0, 55.0, -15.0 },
                { 24.0, -12.0, 44.0, -1.0 }
        };
        double b[] = new double[] { 52.3, -32.6, -29.0, 37.1 };*/
        //dev_6
        /*double a[][] = new double[][] {
                { -100.0, 1.0, 0.0, -1.0 },
                { 80.0, 6.0, -2.0, -6.0 },
                { 0.0, -14.0, -110.0, 0.0 },
                { 22.0, 0.0, 0.0, -16.0 }
        };
        double b[] = new double[] { -41.5, 63.0, -1533.0, 155.0 };*/
      //dev_7
        /*double a[][] = new double[][] {
                { 4.0, 15.0, -0.5, 0.6, 0.0 },
                { 9.0, 16.0, -9.0, 0.0, 1.0 },
                { 77.0, -8.0, 16.0, -32.0, 0.0 },
                { 8.0, -12.0, 22.0, -2.0, 4.0 },
                { 0.0, 0.0, 9.0, -1.0, 60.0 }
        };
        double b[] = new double[] { -110.44, -79.2, 862.8, 221.0, -3.1 };*/
        
        /*Matrix matrixDev = Matrix.from2DArray(a);
		Vector vectorDev = Vector.fromArray(b);
		ForwardBackSubstitutionSolver forwardBackSubstitutionSolver0 = new ForwardBackSubstitutionSolver(matrixDev);
		vectorInitial = forwardBackSubstitutionSolver0.solve(vectorDev);*/
		//wm1
		/*FileSystemHandling.appendLineToFile((EvoSuiteFile) null, "");
	    FileSystemHandling.createFolder((EvoSuiteFile) null);
	    ColumnMajorSparseMatrix columnMajorSparseMatrix0 = ColumnMajorSparseMatrix.identity(11);
	    ForwardBackSubstitutionSolver forwardBackSubstitutionSolver0 = new ForwardBackSubstitutionSolver(columnMajorSparseMatrix0);
	    //forwardBackSubstitutionSolver0.unknowns = 1;
	    CompressedVector compressedVector0 = (CompressedVector)columnMajorSparseMatrix0.getRow(11);
	    CompressedVector compressedVector1 = (CompressedVector)forwardBackSubstitutionSolver0.solve(compressedVector0);*/
		//wm2
		  InPlaceCopyMatrixToMatrix inPlaceCopyMatrixToMatrix0 = new InPlaceCopyMatrixToMatrix();
	      MockRandom mockRandom0 = new MockRandom();
	      DenseMatrix denseMatrix0 = DenseMatrix.randomSymmetric(22, mockRandom0);
	      Matrix matrix0 = inPlaceCopyMatrixToMatrix0.apply(denseMatrix0, denseMatrix0);
	      ForwardBackSubstitutionSolver forwardBackSubstitutionSolver0 = new ForwardBackSubstitutionSolver(matrix0);
	      OoPlaceVectorsAddition ooPlaceVectorsAddition0 = new OoPlaceVectorsAddition();
	      DenseVector denseVector0 = DenseVector.unit(22);
	      Vector vector0 = ooPlaceVectorsAddition0.apply(denseVector0, denseVector0);
	      Vector vector1 = vector0.divide(22);
	      Vector vector2 = forwardBackSubstitutionSolver0.solve(vector0);
	     //wm3
		 //Random.setNextRandom(1);
	      /*RowMajorSparseMatrix rowMajorSparseMatrix0 = RowMajorSparseMatrix.diagonal(1, (-1.0));
	      DenseVector denseVector0 = DenseVector.unit(1);
	      ForwardBackSubstitutionSolver forwardBackSubstitutionSolver0 = new ForwardBackSubstitutionSolver(rowMajorSparseMatrix0);
	      Vector vector0 = forwardBackSubstitutionSolver0.solve(denseVector0);*/
	      //wm4
	      /*ColumnMajorSparseMatrix columnMajorSparseMatrix0 = ColumnMajorSparseMatrix.identity(11);
	      ForwardBackSubstitutionSolver forwardBackSubstitutionSolver0 = new ForwardBackSubstitutionSolver(columnMajorSparseMatrix0);
	      FileSystemHandling.appendStringToFile((EvoSuiteFile) null, ";_O|E");
	      //Random.setNextRandom(11);
	      OoPlaceMatrixByVectorMultiplication ooPlaceMatrixByVectorMultiplication0 = new OoPlaceMatrixByVectorMultiplication();
	      RowMajorSparseMatrix rowMajorSparseMatrix0 = RowMajorSparseMatrix.diagonal(11, (-1.0));
	      DenseVector denseVector0 = DenseVector.unit(11);
	      Vector vector0 = ooPlaceMatrixByVectorMultiplication0.apply(rowMajorSparseMatrix0, denseVector0);
	      Vector vector1 = forwardBackSubstitutionSolver0.solve(vector0);*/
	      //wm5
		/*ColumnMajorSparseMatrix columnMajorSparseMatrix0 = ColumnMajorSparseMatrix.identity(1);
	      ForwardBackSubstitutionSolver forwardBackSubstitutionSolver0 = new ForwardBackSubstitutionSolver(columnMajorSparseMatrix0);
	      assertEquals(1, columnMajorSparseMatrix0.rows());
	      assertEquals(0.0, columnMajorSparseMatrix0.min(), 0.01);
	      assertEquals(1.0, columnMajorSparseMatrix0.max(), 0.01);
	      assertFalse(columnMajorSparseMatrix0.isRowMajor());
	      assertEquals(1, columnMajorSparseMatrix0.cardinality());
	      assertEquals(1, columnMajorSparseMatrix0.columns());
	      assertEquals(1.0, columnMajorSparseMatrix0.density(), 0.01);
	      assertEquals(1, forwardBackSubstitutionSolver0.equations());
	      assertEquals(1, forwardBackSubstitutionSolver0.unknowns());
	      assertNotNull(forwardBackSubstitutionSolver0);
	      
	      //Random.setNextRandom(1);
	      OoPlaceMatrixByVectorMultiplication ooPlaceMatrixByVectorMultiplication0 = new OoPlaceMatrixByVectorMultiplication();
	      RowMajorSparseMatrix rowMajorSparseMatrix0 = RowMajorSparseMatrix.diagonal(1, (-1.0));
	      DenseVector denseVector0 = DenseVector.unit(1);
	      Vector vector0 = ooPlaceMatrixByVectorMultiplication0.apply(rowMajorSparseMatrix0, denseVector0);
	      Vector vector1 = forwardBackSubstitutionSolver0.solve(vector0);*/
	     //wm6
		  /*ColumnMajorSparseMatrix columnMajorSparseMatrix0 = ColumnMajorSparseMatrix.identity(11);
	      ForwardBackSubstitutionSolver forwardBackSubstitutionSolver0 = new ForwardBackSubstitutionSolver(columnMajorSparseMatrix0);
	      CompressedVector compressedVector0 = (CompressedVector)columnMajorSparseMatrix0.getRow(11);
	      CompressedVector compressedVector1 = (CompressedVector)forwardBackSubstitutionSolver0.solve(compressedVector0);*/
	    //line
		/*ColumnMajorSparseMatrix columnMajorSparseMatrix0 = ColumnMajorSparseMatrix.identity(3);
	    ForwardBackSubstitutionSolver forwardBackSubstitutionSolver0 = new ForwardBackSubstitutionSolver(columnMajorSparseMatrix0);
	    OoPlaceMatrixByVectorMultiplication ooPlaceMatrixByVectorMultiplication0 = new OoPlaceMatrixByVectorMultiplication();
	    LinkedList<Integer> linkedList0 = new LinkedList<Integer>();
	    SparseVector sparseVector0 = SparseVector.fromCollection(linkedList0);
	    CompressedVector compressedVector0 = (CompressedVector)ooPlaceMatrixByVectorMultiplication0.apply(columnMajorSparseMatrix0, sparseVector0);
	    CompressedVector compressedVector1 = (CompressedVector)forwardBackSubstitutionSolver0.solve(compressedVector0);*/
	    //branch
	    /*DenseVector denseVector0 = DenseVector.constant(23, 23);
	    Matrix matrix0 = denseVector0.toDiagonalMatrix();
	    ForwardBackSubstitutionSolver forwardBackSubstitutionSolver0 = new ForwardBackSubstitutionSolver(matrix0);
	    Vector vector0 = forwardBackSubstitutionSolver0.solve(denseVector0);*/
	    try{	 
			 switch (MRname) {			
			 case "solve":
			 try {
				 FileWriter writer = new FileWriter("C:\\MRtest\\"+MRname+".txt",true);
				File file = new File("C:\\MRtest\\"+MRname+".txt");
				if ( file.exists() ){
					 //file.delete();
			    }
				//file.createNewFile();
		    	//writer.write(Integer.toString(a));
		    	//writer.append('\n'); 
		    	writer.write(System.getProperty("FaultName"));
		    	writer.append('\n'); 
		    	//writer.write(compressedVector1.toCSV());    	
		    	//writer.write(vector0.toCSV());
		    	//writer.write(vector1.toCSV());
		    	writer.write(vector2.toCSV());
		    	//writer.write(vectorInitial.toCSV());
		    	writer.append('\n');
			    writer.close();				     
			 } catch (IOException ex) {
			   // report
			 } 
	                 break;    
			 case "Multiplication": 
				 //output vectorFollowup = 0.331, -0.189, 0.515
					/*matrixrandom = matrixrandom.multiply(0.5);
					leastSquaresSolver0 = new LeastSquaresSolver(matrixrandom);
					vector = vector.multiply(0.5);
					vectorFollowup = leastSquaresSolver0.solve(vector);*/
				 //developer
				 //testResult= FrameMethod.Multiplication(matrixDev, vectorDev, vectorInitial,"ForwardBackSubstitutionSolver");
				//wm1 followup = source
				 //testResult= FrameMethod.Multiplication(columnMajorSparseMatrix0, compressedVector0, compressedVector1,"ForwardBackSubstitutionSolver");
				//wm2 followup = source
				 testResult= FrameMethod.Multiplication(matrix0, vector0, vector2,"ForwardBackSubstitutionSolver");
				 //wm3
				 //testResult= FrameMethod.Multiplication(rowMajorSparseMatrix0, denseVector0, vector0,"ForwardBackSubstitutionSolver");
				//wm4
				 //testResult= FrameMethod.Multiplication(columnMajorSparseMatrix0, vector0, vector1,"ForwardBackSubstitutionSolver");
				//wm5
				 //testResult= FrameMethod.Multiplication(columnMajorSparseMatrix0, vector0, vector1,"ForwardBackSubstitutionSolver");
				//wm6
				 //testResult= FrameMethod.Multiplication(columnMajorSparseMatrix0, compressedVector0, compressedVector1,"ForwardBackSubstitutionSolver");
				 //line followup = source
				 //testResult= FrameMethod.Multiplication(columnMajorSparseMatrix0, compressedVector0, compressedVector1,"ForwardBackSubstitutionSolver");	
				//branch followup = source
				 //testResult= FrameMethod.Multiplication(matrix0, denseVector0, vector0,"ForwardBackSubstitutionSolver");
					
     		 		break; 
			 case "permuteRow":
				//output vectorFollowup = 0.331, -0.189, 0.515
					/*matrixrandom.swapRows(1, 2);
					leastSquaresSolver0 = new LeastSquaresSolver(matrixrandom);
					vector.swapElements(1, 2);
					vectorFollowup = leastSquaresSolver0.solve(vector);*/
				//developer
				 //testResult= FrameMethod.permuteRow(matrixDev, vectorDev, vectorInitial,"ForwardBackSubstitutionSolver");
				//wm1 followup = source
				 //testResult= FrameMethod.permuteRow(columnMajorSparseMatrix0, compressedVector0, compressedVector1,"ForwardBackSubstitutionSolver");
				//wm2 followup = source
				 testResult= FrameMethod.permuteRow(matrix0, vector0, vector2,"ForwardBackSubstitutionSolver");
				//wm3 //MR not applicable because of matrix size
				//wm4
				 //testResult= FrameMethod.permuteRow(columnMajorSparseMatrix0, vector0, vector1,"ForwardBackSubstitutionSolver");
				//wm5 //MR not applicable because of matrix size
				//wm6
				 //testResult= FrameMethod.permuteRow(columnMajorSparseMatrix0, compressedVector0, compressedVector1,"ForwardBackSubstitutionSolver");
				  //line followup = source
				 //testResult= FrameMethod.permuteRow(columnMajorSparseMatrix0, compressedVector0, compressedVector1,"ForwardBackSubstitutionSolver");
					
				//branch followup == source
			     //testResult= FrameMethod.permuteRow(matrix0, denseVector0, vector0,"ForwardBackSubstitutionSolver");
	         		 break;
     		case "matrixAdd":
     			//output vectorFollowup = 0.331, -0.189, 0.515
     			/*matrixrandom = matrixrandom.add(matrixrandom);
				leastSquaresSolver0 = new LeastSquaresSolver(matrixrandom);
				vector = vector.add(vector);
				vectorFollowup = leastSquaresSolver0.solve(vector);*/
     			//developer
				 //testResult= FrameMethod.matrixAdd(matrixDev, vectorDev, vectorInitial,"ForwardBackSubstitutionSolver");
     			//wm1 followup = source
     			//testResult= FrameMethod.matrixAdd(columnMajorSparseMatrix0, compressedVector0, compressedVector1,"ForwardBackSubstitutionSolver");
				//wm2 followup = source
     			testResult= FrameMethod.matrixAdd(matrix0, vector0, vector2,"ForwardBackSubstitutionSolver");
     			//wm3
				 //testResult= FrameMethod.matrixAdd(rowMajorSparseMatrix0, denseVector0, vector0,"ForwardBackSubstitutionSolver");
				//wm4
				 //testResult= FrameMethod.matrixAdd(columnMajorSparseMatrix0, vector0, vector1,"ForwardBackSubstitutionSolver");
     			//wm5
				 //testResult= FrameMethod.matrixAdd(columnMajorSparseMatrix0, vector0, vector1,"ForwardBackSubstitutionSolver");
     			//wm6
				 //testResult= FrameMethod.matrixAdd(columnMajorSparseMatrix0, compressedVector0, compressedVector1,"ForwardBackSubstitutionSolver");
				 //line followup = source
     			//testResult= FrameMethod.matrixAdd(columnMajorSparseMatrix0, compressedVector0, compressedVector1,"ForwardBackSubstitutionSolver");
				//branch followup = source
				 //testResult= FrameMethod.matrixAdd(matrix0, denseVector0, vector0,"ForwardBackSubstitutionSolver");
    		 break;  
			case "transpose":
				//output vectorFollowup = 0.331, -0.189, 0.515
				/*matrix = matrixrandom.transpose();
				matrixrandom = matrix.multiply(matrixrandom);
				leastSquaresSolver0 = new LeastSquaresSolver(matrixrandom);
				vector = matrix.multiply(vector);
				vectorFollowup = leastSquaresSolver0.solve(vector);*/
				//developer
				 //testResult= FrameMethod.transpose(matrixDev, vectorDev, vectorInitial,"ForwardBackSubstitutionSolver");
				//wm1 followup = source
				//testResult= FrameMethod.transpose(columnMajorSparseMatrix0, compressedVector0, compressedVector1,"ForwardBackSubstitutionSolver");
				//wm2 throws exception not applicable
				testResult= FrameMethod.transpose(matrix0, vector0, vector2,"ForwardBackSubstitutionSolver");
				//wm3
				 //testResult= FrameMethod.transpose(rowMajorSparseMatrix0, denseVector0, vector0,"ForwardBackSubstitutionSolver");
				//wm4
				 //testResult= FrameMethod.transpose(columnMajorSparseMatrix0, vector0, vector1,"ForwardBackSubstitutionSolver");
				//wm5
				 //testResult= FrameMethod.transpose(columnMajorSparseMatrix0, vector0, vector1,"ForwardBackSubstitutionSolver");
				//wm6
				 //testResult= FrameMethod.transpose(columnMajorSparseMatrix0, compressedVector0, compressedVector1,"ForwardBackSubstitutionSolver");
				 //line followup != source
				//testResult= FrameMethod.transpose(columnMajorSparseMatrix0, compressedVector0, compressedVector1,"ForwardBackSubstitutionSolver");
				//branch followup = source
				 //testResult= FrameMethod.transpose(matrix0, denseVector0, vector0,"ForwardBackSubstitutionSolver");
    		 break;
			case "MultiplyWithIdentity":
				//output vectorFollowup = 0.331, -0.189, 0.515
				/*matrix = Matrix.identity(matrixrandom.columns());
				matrixrandom = matrixrandom.multiply(matrix);
				leastSquaresSolver0 = new LeastSquaresSolver(matrixrandom);
				//vector = matrix.multiply(vector);
				vectorFollowup = leastSquaresSolver0.solve(vector);*/
				//developer
				 //testResult= FrameMethod.MultiplyWithIdentity(matrixDev, vectorDev, vectorInitial,"ForwardBackSubstitutionSolver");
				//wm1 followup = source
				//testResult= FrameMethod.MultiplyWithIdentity(columnMajorSparseMatrix0, compressedVector0, compressedVector1,"ForwardBackSubstitutionSolver");
				//wm2 followup = source
				testResult= FrameMethod.MultiplyWithIdentity(matrix0, vector0, vector2,"ForwardBackSubstitutionSolver");
				//wm3
				 //testResult= FrameMethod.MultiplyWithIdentity(rowMajorSparseMatrix0, denseVector0, vector0,"ForwardBackSubstitutionSolver");
				//wm4
				 //testResult= FrameMethod.MultiplyWithIdentity(columnMajorSparseMatrix0, vector0, vector1,"ForwardBackSubstitutionSolver");
				//wm5
				 //testResult= FrameMethod.MultiplyWithIdentity(columnMajorSparseMatrix0, vector0, vector1,"ForwardBackSubstitutionSolver");
				//wm6
				 //testResult= FrameMethod.MultiplyWithIdentity(columnMajorSparseMatrix0, compressedVector0, compressedVector1,"ForwardBackSubstitutionSolver");
				 //line followup = source
				//testResult= FrameMethod.MultiplyWithIdentity(columnMajorSparseMatrix0, compressedVector0, compressedVector1,"ForwardBackSubstitutionSolver");
				//branch followup = source
			     //testResult= FrameMethod.MultiplyWithIdentity(matrix0, denseVector0, vector0,"ForwardBackSubstitutionSolver");
    		 break;
			case "negation":				
				//output vectorFollowup = 0.331, -0.189, 0.515
				/*matrixrandom = matrixrandom.multiply(-1.0);
				leastSquaresSolver0 = new LeastSquaresSolver(matrixrandom);
				vector = vector.multiply(-1.0);
				vectorFollowup = leastSquaresSolver0.solve(vector);*/
				//developer
				 //testResult= FrameMethod.Negation(matrixDev, vectorDev, vectorInitial,"ForwardBackSubstitutionSolver");
				//wm1 followup = source
				//testResult= FrameMethod.Negation(columnMajorSparseMatrix0, compressedVector0, compressedVector1,"ForwardBackSubstitutionSolver");
				//wm2 followup = source
				testResult= FrameMethod.Negation(matrix0, vector0, vector2,"ForwardBackSubstitutionSolver");
				//wm3
				 //testResult= FrameMethod.Negation(rowMajorSparseMatrix0, denseVector0, vector0,"ForwardBackSubstitutionSolver");
				//wm4
				 //testResult= FrameMethod.Negation(columnMajorSparseMatrix0, vector0, vector1,"ForwardBackSubstitutionSolver");
				//wm5
				 //testResult= FrameMethod.Negation(columnMajorSparseMatrix0, vector0, vector1,"ForwardBackSubstitutionSolver");
				//wm6
				 //testResult= FrameMethod.Negation(columnMajorSparseMatrix0, compressedVector0, compressedVector1,"ForwardBackSubstitutionSolver");
				 //line followup = source
				//testResult= FrameMethod.Negation(columnMajorSparseMatrix0, compressedVector0, compressedVector1,"ForwardBackSubstitutionSolver");
				//branch followup = source
				 //testResult= FrameMethod.Negation(matrix0, denseVector0, vector0,"ForwardBackSubstitutionSolver");
				break;
	        default: MRname = "Invalid MR";
	                 break;
			 }
		 }catch (Exception ex) {
			 try {
				 FileWriter writer = new FileWriter("C:\\MRtest\\ForwardBackSubstitutionSolver_exception.txt",true);
				File file = new File("C:\\MRtest\\ForwardBackSubstitutionSolver_exception.txt");
				file.createNewFile();
				writer.append(System.getProperty("FaultName"));
			    writer.append(',');
			    writer.append(System.getProperty("MRname"));
			    writer.append(',');
			    writer.append(System.getProperty("TestNo")); 
			    writer.append('\n');  
			    writer.close();				     
			 } catch (IOException ex1) {
			   // report
			 } 
			   // report
			 System.exit(0);
			// testResult = false;
			 }
return testResult;
	}
	public boolean LeastSquaresSolverSolve(){
		String MRname = System.getProperty("MRname");
		Boolean testResult	= true;
		Matrix matrix = null;
		Vector vector = null;
		Matrix matrixAdd = null;
		Vector vectorAdd = null;
		Vector vectorInitial = null;
		Vector vectorFollowup = null;
		Matrix matrixrandom = null;
		//for test
		/*double[][] arr = {{ 1 , -2 ,  3}, { 5,   8 , -1},{ 2,   1 ,  1},{-1,   4 , -3}};
		double[] arr1 = {2,-1,3,-2};
		//double[][] arr = {{ 1 , -2 ,  3}, { 5,   8 , -1},{ 2,   1 ,  1}};
		//double[] arr1 = {2,-1,3};
		matrixrandom = Matrix.from2DArray(arr);
		LeastSquaresSolver leastSquaresSolver0 = new LeastSquaresSolver(matrixrandom);
		vector = Vector.fromArray(arr1);
		vectorInitial = leastSquaresSolver0.solve(vector);*/
		//developer tests suite
		//dev1
		/*double a[][] = new double[][] {
            { 55.0 }
		};
		double b[] = new double[] { -5.0 };*/
		//dev2
		/*double a[][] = new double[][] {
            { 6.0, 3.0 },
            { 9.0, 18.0 }
		};
		double b[] = new double[] { 5.0, 21.0 };*/
		//dev3
		/*double a[][] = new double[][] {
            { 20.0 },
            { -24.0 }
		};

		double b[] = new double[] { -10.0, 12.0 };*/
		//dev4
		/*double a[][] = new double[][] {
            { 99.0, -1.0, 0.0 },
            { 9.0, 50.0, -2.0 },
            { 10.0, 60.0, -4.0 }
		};

		double b[] = new double[] { -98.9, -34.0, -56.0 };*/
		//dev5
		/*double a[][] = new double[][] {
            { 44.0 },
            { -4.0 },
            { 444.0 }
		};

		double b[] = new double[] { 4.4, -0.4, 44.4 };*/
		//dev6
		/*double a[][] = new double[][] {
            { 10.0, 1.0 },
            { 0.0, 2.0 },
            { 4.0, -8.0 }
		};

		double b[] = new double[] { 90.0, -20.0, 120.0 };*/
		//dev7
		/*double a[][] = new double[][] {
            { 77.0, -5.0, 10.0, 0.0 },
            { 44.0, 1.0, 0.0, -2.0 },
            { 33.0, 20.0, 1.0, 0.0 },
            { 0.0, 10.0, -12.0, 54.0 }
		};

		double b[] = new double[] { 708.0, 405.0, 319.0, -230.0 };*/
		//dev8
		/*double a[][] = new double[][] {
            { 2.0 },
            { -16.0 },
            { 32.0 },
            { 8.0  }
		};

		double b[] = new double[] { 1.0, -8.0, 16.0, 4.0 };*/
		//dev9
		/*double a[][] = new double[][] {
            { 100.0, -88.0 },
            { 40.0, -20.0 },
            { -0.5, 0.5 },
            { 1.0, 0.1 }
		};

		double b[] = new double[] { -870.0, -196.0, 4.95, 1.1 };*/
		//dev10
		/*double a[][] = new double[][] {
            { 64.0, -10.0, 1.0 },
            { -0.6, 11.0, -15.0 },
            { 29.0, 160.0, -9.0 },
            { 11.0, -54.0, 22.0 }
		};

		double b[] = new double[] { -36.5, 116.9, 1633.5, -540.0 };*/
		//dev11
		/*double a[][] = new double[][] {
            { -5.0, 0.0, 1.0, 20.0, -16.0 },
            { 0.0, -10.0, 2.0, -22.0, 14.0 },
            { 0.0, -2.0, 0.5, 54.0, 17.0 },
            { -0.2, 20.0, -10.0, 45.0, -1.0 },
            { 8.0, 9.0, 11.0, -2.0, 15.0 }
		};

		double b[] = new double[] { 42.5, -71.0, 117.75, 107.5, 41.0 };*/
    
		/*Matrix matrixDev = Matrix.from2DArray(a);
		Vector vectorDev = Vector.fromArray(b);
		LeastSquaresSolver leastSquaresSolver0 = new LeastSquaresSolver(matrixDev);
		vectorInitial = leastSquaresSolver0.solve(vectorDev);*/
		//wm1
		double[] doubleArray0 = new double[21];
	    doubleArray0[1] = (double) 2;
	    doubleArray0[2] = (double) 2;
	    ColumnMajorSparseMatrix columnMajorSparseMatrix0 = ColumnMajorSparseMatrix.from1DArray(2, 2, doubleArray0);
	    LeastSquaresSolver leastSquaresSolver0 = new LeastSquaresSolver(columnMajorSparseMatrix0);
	    HashMap<Integer, Integer> hashMap0 = new HashMap<Integer, Integer>(2, 2);
	    CompressedVector compressedVector0 = (CompressedVector)Vector.fromMap(hashMap0, 2);
	    CompressedVector compressedVector1 = (CompressedVector)leastSquaresSolver0.solve(compressedVector0);
		//wm2
	    /*DenseVector denseVector0 = DenseVector.constant(209, 209);
	    Matrix matrix0 = denseVector0.toColumnMatrix();
	    LeastSquaresSolver leastSquaresSolver0 = new LeastSquaresSolver(matrix0);
	    Vector vector0 = leastSquaresSolver0.solve(denseVector0);*/
	    //line
	    /*MockRandom mockRandom0 = new MockRandom();
	    DenseMatrix denseMatrix0 = DenseMatrix.randomSymmetric(4, mockRandom0);
	    LeastSquaresSolver leastSquaresSolver0 = new LeastSquaresSolver(denseMatrix0);
	    DenseVector denseVector0 = DenseVector.constant(4, 4);
	    Vector vector0 = leastSquaresSolver0.solve(denseVector0);*/
	    //branch
	    /*MockRandom mockRandom0 = new MockRandom();
	    DenseMatrix denseMatrix0 = DenseMatrix.randomSymmetric(8, mockRandom0);
	    LeastSquaresSolver leastSquaresSolver0 = new LeastSquaresSolver(denseMatrix0);
	    Vector vector0 = Vector.zero(8);
	    Vector vector1 = leastSquaresSolver0.solve(vector0);*/
	    try{	 
			 switch (MRname) {			
			 case "solve":
			 try {
				 FileWriter writer = new FileWriter("C:\\MRtest\\"+MRname+".txt",true);
				File file = new File("C:\\MRtest\\"+MRname+".txt");
				if ( file.exists() ){
					 //file.delete();
			    }
				//file.createNewFile();
		    	//writer.write(Integer.toString(a));
		    	//writer.append('\n'); 
		    	writer.write(System.getProperty("FaultName"));
		    	writer.append('\n'); 
		    	writer.write(compressedVector1.toCSV());
		    	//writer.write(vector0.toCSV());
		    	//writer.write(vector1.toCSV());
		    	//writer.write(vectorInitial.toCSV());
		    	writer.append('\n');
			    writer.close();				     
			 } catch (IOException ex) {
			   // report
			 } 
	                 break;    
			 case "Multiplication": 
				 //output vectorFollowup = 0.331, -0.189, 0.515
					/*matrixrandom = matrixrandom.multiply(0.5);
					leastSquaresSolver0 = new LeastSquaresSolver(matrixrandom);
					vector = vector.multiply(0.5);
					vectorFollowup = leastSquaresSolver0.solve(vector);*/
				 //developer
				 //testResult= FrameMethod.Multiplication(matrixDev, vectorDev, vectorInitial,"LeastSquaresSolver");
					
				//wm1 followup = source
				 testResult= FrameMethod.Multiplication(columnMajorSparseMatrix0, compressedVector0, compressedVector1,"LeastSquaresSolver");
				//wm2 followup = source
				 //testResult= FrameMethod.Multiplication(matrix0, denseVector0, vector0,"LeastSquaresSolver");
				//line followup = source
				 //testResult= FrameMethod.Multiplication(denseMatrix0, denseVector0, vector0,"LeastSquaresSolver");
					
				//branch followup = source
				 //testResult= FrameMethod.Multiplication(denseMatrix0, vector0, vector1,"LeastSquaresSolver");
     		 		break; 
			 case "permuteRow":
				//output vectorFollowup = 0.331, -0.189, 0.515
					/*matrixrandom.swapRows(1, 2);
					leastSquaresSolver0 = new LeastSquaresSolver(matrixrandom);
					vector.swapElements(1, 2);
					vectorFollowup = leastSquaresSolver0.solve(vector);*/
				//developer
				 //testResult= FrameMethod.permuteRow(matrixDev, vectorDev, vectorInitial,"LeastSquaresSolver");
				//wm1 followup = source
				 testResult= FrameMethod.permuteRow(columnMajorSparseMatrix0, compressedVector0, compressedVector1,"LeastSquaresSolver");
				//wm2 followup = source
				 //testResult= FrameMethod.permuteRow(matrix0, denseVector0, vector0,"LeastSquaresSolver");
				//line followup = source
				 //testResult= FrameMethod.permuteRow(denseMatrix0, denseVector0, vector0,"LeastSquaresSolver");
					
				//branch followup != source
			     //testResult= FrameMethod.permuteRow(denseMatrix0, vector0, vector1,"LeastSquaresSolver");
	         		 break;
     		case "matrixAdd":
     			//output vectorFollowup = 0.331, -0.189, 0.515
     			/*matrixrandom = matrixrandom.add(matrixrandom);
				leastSquaresSolver0 = new LeastSquaresSolver(matrixrandom);
				vector = vector.add(vector);
				vectorFollowup = leastSquaresSolver0.solve(vector);*/
     			//developer
				 //testResult= FrameMethod.matrixAdd(matrixDev, vectorDev, vectorInitial,"LeastSquaresSolver");
     			//wm1 followup = source
     			testResult= FrameMethod.matrixAdd(columnMajorSparseMatrix0, compressedVector0, compressedVector1,"LeastSquaresSolver");
				//wm2 followup = source
     			//testResult= FrameMethod.matrixAdd(matrix0, denseVector0, vector0,"LeastSquaresSolver");
				//line followup = source
     			//testResult= FrameMethod.matrixAdd(denseMatrix0, denseVector0, vector0,"LeastSquaresSolver");
				//branch followup = source
				 //testResult= FrameMethod.matrixAdd(denseMatrix0, vector0, vector1,"LeastSquaresSolver");
    		 break;  
			case "transpose":
				//output vectorFollowup = 0.331, -0.189, 0.515
				/*matrix = matrixrandom.transpose();
				matrixrandom = matrix.multiply(matrixrandom);
				leastSquaresSolver0 = new LeastSquaresSolver(matrixrandom);
				vector = matrix.multiply(vector);
				vectorFollowup = leastSquaresSolver0.solve(vector);*/
				//developer
				 //testResult= FrameMethod.transpose(matrixDev, vectorDev, vectorInitial,"LeastSquaresSolver");
				//wm1 followup = source
				testResult= FrameMethod.transpose(columnMajorSparseMatrix0, compressedVector0, compressedVector1,"LeastSquaresSolver");
				//wm2 throws exception not applicable
				//testResult= FrameMethod.transpose(matrix0, denseVector0, vector0,"LeastSquaresSolver");
				//line followup != source
				//testResult= FrameMethod.transpose(denseMatrix0, denseVector0, vector0,"LeastSquaresSolver");
				//branch followup = source
				 //testResult= FrameMethod.transpose(denseMatrix0, vector0, vector1,"LeastSquaresSolver");
    		 break;
			case "MultiplyWithIdentity":
				//output vectorFollowup = 0.331, -0.189, 0.515
				/*matrix = Matrix.identity(matrixrandom.columns());
				matrixrandom = matrixrandom.multiply(matrix);
				leastSquaresSolver0 = new LeastSquaresSolver(matrixrandom);
				//vector = matrix.multiply(vector);
				vectorFollowup = leastSquaresSolver0.solve(vector);*/
				//developer
				 //testResult= FrameMethod.MultiplyWithIdentity(matrixDev, vectorDev, vectorInitial,"LeastSquaresSolver");
				//wm1 followup = source
				testResult= FrameMethod.MultiplyWithIdentity(columnMajorSparseMatrix0, compressedVector0, compressedVector1,"LeastSquaresSolver");
				//wm2 followup = source
				//testResult= FrameMethod.MultiplyWithIdentity(matrix0, denseVector0, vector0,"LeastSquaresSolver");
				//line followup = source
				//testResult= FrameMethod.MultiplyWithIdentity(denseMatrix0, denseVector0, vector0,"LeastSquaresSolver");
				//branch followup = source
			     //testResult= FrameMethod.MultiplyWithIdentity(denseMatrix0, vector0, vector1,"LeastSquaresSolver");
    		 break;
			case "negation":				
				//output vectorFollowup = 0.331, -0.189, 0.515
				/*matrixrandom = matrixrandom.multiply(-1.0);
				leastSquaresSolver0 = new LeastSquaresSolver(matrixrandom);
				vector = vector.multiply(-1.0);
				vectorFollowup = leastSquaresSolver0.solve(vector);*/
				//developer
				 //testResult= FrameMethod.Negation(matrixDev, vectorDev, vectorInitial,"LeastSquaresSolver");
				//wm1 followup = source
				testResult= FrameMethod.Negation(columnMajorSparseMatrix0, compressedVector0, compressedVector1,"LeastSquaresSolver");
				//wm2 followup = source
				//testResult= FrameMethod.Negation(matrix0, denseVector0, vector0,"LeastSquaresSolver");
				//line followup = source
				//testResult= FrameMethod.Negation(denseMatrix0, denseVector0, vector0,"LeastSquaresSolver");
				//branch followup = source
				 //testResult= FrameMethod.Negation(denseMatrix0, vector0, vector1,"LeastSquaresSolver");
				break;
	        default: MRname = "Invalid MR";
	                 break;
			 }
		 }catch (Exception ex) {
			 try {
				 FileWriter writer = new FileWriter("C:\\MRtest\\LeastSquaresSolverSolve_exception.txt",true);
				File file = new File("C:\\MRtest\\LeastSquaresSolverSolve_exception.txt");
				file.createNewFile();
				writer.append(System.getProperty("FaultName"));
			    writer.append(',');
			    writer.append(System.getProperty("MRname"));
			    writer.append(',');
			    writer.append(System.getProperty("TestNo")); 
			    writer.append('\n');  
			    writer.close();				     
			 } catch (IOException ex1) {
			   // report
			 } 
			   // report
			 System.exit(0);
			// testResult = false;
			 }
return testResult;
	}

	public  boolean zero(){
	
			//int[] a = FrameMethod.getIntArray();
			//int key = FrameMethod.getInt();
			//boolean result = true;
			//line1
			//int a = 0;
			//int b = 0;
			//line2
			//int a = 704;
			//int b = 704;
			//wm1
			int a = 5;
			int b = 5;
			Matrix sum ;
			
			String MRname = System.getProperty("MRname");
			//	method = "sequential_search";
			Boolean testResult	= true; 
			try{	 
				 switch (MRname) {			
				 case "zero":sum =  Matrix.zero(a, b);
				 try {
					 FileWriter writer = new FileWriter("C:\\MRtest\\"+MRname+".txt",true);
					File file = new File("C:\\MRtest\\"+MRname+".txt");
					if ( file.exists() ){
						 //file.delete();
				    }
					//file.createNewFile();
			    	//writer.write(Integer.toString(a));
			    	//writer.append('\n'); 
			    	writer.write(System.getProperty("FaultName"));
			    	writer.append('\n'); 
			    	writer.write(sum.toCSV());
			    	writer.append('\n');
				    writer.close();				     
				 } catch (IOException ex) {
				   // report
				 } 
		                 break;
				 case "Addition":
					 sum =  Matrix.zero(a, b);
					 testResult =  FrameMethod.Addition(a, b, sum, "zero");
		         		 break;        
				 case "Multiplication": 
					 sum =  Matrix.zero(a, b);
					 testResult = FrameMethod.Multiplication(a, b, sum, "zero");
         		 		break; 
		        default: MRname = "Invalid MR";
		                 break;
				 }
			 }catch (Exception ex) {
				 try {
					 FileWriter writer = new FileWriter("C:\\MRtest\\zero_exception.txt",true);
					File file = new File("C:\\MRtest\\zero_exception.txt");
					file.createNewFile();
					writer.append(System.getProperty("FaultName"));
				    writer.append(',');
				    writer.append(System.getProperty("MRname"));
				    writer.append(',');
				    writer.append(System.getProperty("TestNo")); 
				    writer.append('\n');  
				    writer.close();				     
				 } catch (IOException ex1) {
				   // report
				 } 
				   // report
				 System.exit(0);
				// testResult = false;
				 }
return testResult;
}
	
	public  boolean  updateColumn(){
			String MRname = System.getProperty("MRname");
			Boolean testResult	= true;
			//line
			 Matrix matrix0 = Matrix.constant(2, 32, (-623.0));
			 VectorFunction vectorFunction0 = mock(VectorFunction.class, new ViolatedAssumptionAnswer());
		     doReturn(0.0).when(vectorFunction0).evaluate(anyInt() , anyDouble());
			 matrix0.updateColumn(2, vectorFunction0);
			try{
				 switch (MRname) {
				
				 case "updateColumn":
					 
				// FileWriter writer = null;
				 try {
					 FileWriter writer = new FileWriter("C:\\MRtest\\"+MRname+".txt",true);
					File file = new File("C:\\MRtest\\"+MRname+".txt");
					if ( file.exists() ){
						 //file.delete();
				    }
					//file.createNewFile();
			    	writer.write(System.getProperty("FaultName"));
			    	writer.append('\n'); 
			    	writer.write(matrix0.toCSV());
			    	writer.append('\n');
				    writer.close();				     
				 } catch (IOException ex) {
				   // report
				 } 
		                 break;
				 case "Addition": 
					 testResult=   FrameMethod.Addition(2,0,matrix0, "updateColumn" );
		         		 break;        
				 case "Multiplication":
					 testResult=  FrameMethod.Multiplication(2,0, matrix0, "updateColumn");
         		 		break;
				
				 }
			 }catch (Exception ex) {
				 try {
					 FileWriter writer = new FileWriter("C:\\MRtest\\updateColumn_exception.txt",true);
					File file = new File("C:\\MRtest\\updateColumn_exception.txt");
					file.createNewFile();
					writer.append(System.getProperty("FaultName"));
				    writer.append(',');
				    writer.append(System.getProperty("MRname"));
				    writer.append(',');
				    writer.append(System.getProperty("TestNo")); 
				    writer.append('\n');  
				    writer.close();				     
				 } catch (IOException ex1) {
				   // report
				 } 
				   // report
				 System.exit(0);
				// testResult = false;
				 }
return testResult;
			
}
		
	public boolean  transpose(){
				String MRname = System.getProperty("MRname");
				DenseMatrix denseMatrix0 = null;
				Matrix matrix0 = null;
				Boolean result = true;
				MockRandom mockRandom0 = null;
				double[][] doubleArray0 = null;
				Boolean testResult	= true;
				//developer1
				/* Matrix a = m(a(0.0, 14.2, 0.0, 4.0),
	                     a(0.0, 5.0, 10.0, 0.0),
	                     a(0.0, 3.0, 0.0, 2.3),
	                     a(11.0, 7.0, 0.0, 1.0));*/
				 //developer2
				 /*Matrix a = m(a(0.0, 14.2, 0.0),
	                     a(0.0, 5.0, 10.0),
	                     a(0.0, 3.0, 0.0),
	                     a(11.0, 7.0, 0.0),
	                     a(12.0, 7.5, 0.0));*/
				//developer3
				 Matrix a = m(a(8.93, 3.96, 7.37, 3.43, 7.05),
	                     a(5.88, 8.26, 5.79, 9.08, 7.75),
	                     a(6.57, 2.51, 8.8, 1.16, 8.11),
	                     a(9.3, 9.61, 0.87, 2.3, 2.93),
	                     a(3.65, 4.63, 7.83, 3.66, 9.04),
	                     a(0.08, 6.12, 6.15, 4.93, 6.72));
				 matrix0 = a.transpose();
				//line return false
				  //denseMatrix0 = DenseMatrix.unit(1, 32);
				  //matrix0 = denseMatrix0.transpose();
				 //branch return false
				  //doubleArray0 = new double[1][9];
			      //denseMatrix0 = DenseMatrix.from2DArray(doubleArray0);
			      //matrix0 = denseMatrix0.transpose();
				 //wm return false
				  //mockRandom0 = new MockRandom(66L);
			      //denseMatrix0 = DenseMatrix.random(10, 10, mockRandom0);
			      //matrix0 = denseMatrix0.transpose();
				  
				try{	 
				switch (MRname) {
					
					 case "transpose": 
					      result = matrix0.equals((Object)denseMatrix0);
					 try {
						FileWriter writer = new FileWriter("C:\\MRtest\\"+MRname+".txt",true);
						File file = new File("C:\\MRtest\\"+MRname+".txt");
						if ( file.exists() ){
							 //file.delete();
					    }
						//file.createNewFile();
				    	writer.write(System.getProperty("FaultName"));
				    	writer.append(','); 
				    	//writer.write(Boolean.toString(result));
				    	writer.append('\n');
				    	//writer.write(denseMatrix0.toCSV());
				    	//writer.append('\n');
				    	writer.write(matrix0.toCSV());
				    	writer.append('\n');
					    writer.close();				     
					 } catch (IOException ex) {
					   // report
					 } 
			                 break;
					 case "Addition":// relation true
						//developer
						 testResult= FrameMethod.Addition(a, matrix0, "transpose");
						 //evosuite
						 //testResult = FrameMethod.Addition(denseMatrix0,matrix0, "transpose");
			         		 break;        
					 case "Multiplication"://relation true
						//developer
						 testResult= FrameMethod.Multiplication(a, matrix0, "transpose");
						 //evosuite
					     //testResult = FrameMethod.Multiplication(denseMatrix0,matrix0, "transpose");
			         	 break;
					 case "matrixAdd"://relation false
						//developer
						 testResult= FrameMethod.matrixAdd(a, matrix0, "transpose");
						 //evosuite
					     //testResult = FrameMethod.matrixAdd(denseMatrix0,matrix0, "transpose");
			         	 break;
					 case "permuteRow":
						//developer
						 testResult= FrameMethod.permuteRow(a, matrix0, "transpose");
						 //evosuite
					     //testResult = FrameMethod.permuteRow(denseMatrix0,matrix0, "transpose");
			         	 break;
					 case "permuteElement":
						//developer
						 testResult= FrameMethod.permuteElement(a, matrix0, "transpose");
						 //evosuite
						     //testResult = FrameMethod.permuteElement(denseMatrix0,matrix0, "transpose");
				         	 break;							 
					 case "permuteColumn":
						//developer
						 testResult= FrameMethod.permuteColumn(a, matrix0, "transpose");
						 //evosuite
						     //testResult = FrameMethod.permuteColumn(denseMatrix0,matrix0, "transpose");
			         	     break;						
					 case "AdditionWithIdentity":// relation true
						//developer
						 testResult= FrameMethod.AdditionWithIdentity(a, matrix0, "transpose");
						 //evosuite
						 //line branch NA, because of matrix size
						     //testResult = FrameMethod.AdditionWithIdentity(denseMatrix0,matrix0, "transpose");
			         	     break;								 
					 case "MultiplyWithIdentity":// relation true
						//developer
						 testResult= FrameMethod.MultiplyWithIdentity(a, matrix0, "transpose");
						 //evosuite
						 //testResult = FrameMethod.MultiplyWithIdentity(denseMatrix0,matrix0, "transpose");
				         		 break;  
			        default: MRname = "Invalid MR";
			                 break;
					 }
	}catch (Exception ex) {
		try {
			FileWriter writer = new FileWriter("C:\\MRtest\\transpose_exception.txt",true);
			File file = new File("C:\\MRtest\\transpose_exception.txt");
			file.createNewFile();
			writer.append(System.getProperty("FaultName"));
		    writer.append(',');
		    writer.append(System.getProperty("MRname"));
		    writer.append(',');
		    writer.append(System.getProperty("TestNo")); 
		    writer.append('\n');  
		    writer.close();				     
		 } catch (IOException ex1) {
		   // report
		 } 
		   // report
		 System.exit(0);
		 //testResult = false;
		 }
return testResult;
	}
	
	  
	public boolean  transformRow(){
				String MRname = System.getProperty("MRname");
				//	method = "sequential_search";
				boolean testResult = true;
				
				//line returns false
				 int a = 1337;
				 MockRandom mockRandom0 = new MockRandom(1698);
				 Matrix matrix0 = Matrix.random(1698, 1, mockRandom0);
				 VectorFunction vectorFunction0 = mock(VectorFunction.class, new ViolatedAssumptionAnswer());
			     doReturn(0.0).when(vectorFunction0).evaluate(anyInt() , anyDouble());
			     Matrix matrix1 = matrix0.transformRow(a, vectorFunction0);
				 //branch returns true
				 /*int a = 1;
			     LinkedList<Double> linkedList0 = new LinkedList<Double>();
			     DenseVector denseVector0 = DenseVector.fromCollection(linkedList0);
			     Matrix matrix0 = denseVector0.toDiagonalMatrix();
			     Matrix matrix1 = matrix0.transformRow(a, (VectorFunction) null);*/
			     
				try{ 
					 switch (MRname) {
					
					 case "transformRow":
					     Boolean result = matrix1.equals((Object)matrix0);
					 try {
						 FileWriter writer = new FileWriter("C:\\MRtest\\"+MRname+".txt",true);
						File file = new File("C:\\MRtest\\"+MRname+".txt");
						if ( file.exists() ){
							// file.delete();
					    }
						//file.createNewFile();
						writer.write(System.getProperty("FaultName"));
				    	writer.append(','); 
				    	//writer.write(Boolean.toString(result));
				    	writer.write(matrix1.toCSV());
				    	writer.append('\n');
					    writer.close();				     
					 } catch (IOException ex) {
					   // report
					 } 
			                 break;
					
					 case "Addition":
					     //result = matrix1.equals((Object)matrix0);
						 testResult =  FrameMethod.Addition(matrix0, matrix1,  "transformRow");
			         		 break;        
					 case "Multiplication": 
					     //result = matrix1.equals((Object)matrix0);
						 testResult = FrameMethod.Multiplication(matrix0, matrix1, "transformRow");
	         		 		break; 
			        default: MRname = "Invalid MR";
			                 break;
					 }
				}catch (Exception ex) {
					try {
						FileWriter writer = new FileWriter("C:\\MRtest\\transformRow_exception.txt",true);
						File file = new File("C:\\MRtest\\transformRow_exception.txt");
						file.createNewFile();
						writer.append(System.getProperty("FaultName"));
					    writer.append(',');
					    writer.append(System.getProperty("MRname"));
					    writer.append(',');
					    writer.append(System.getProperty("TestNo")); 
					    writer.append('\n');  
					    writer.close();				     
					 } catch (IOException ex1) {
					   // report
					 } 
					   // report
					 System.exit(0);
					 //testResult = false;
					 }
					 return testResult;	
	}
	 
	
	public boolean  transformColumn(){
		
				//int[] a = FrameMethod.getIntArray();
				//line
				int a = 0;
				//boolean result = true;
				int[] sum = null;
				boolean testResult = true;
				String MRname = System.getProperty("MRname");
				MockRandom mockRandom0 = new MockRandom(1698);
			      Matrix matrix0 = Matrix.random(1698, 1, mockRandom0);
			      VectorFunction vectorFunction0 = mock(VectorFunction.class, new ViolatedAssumptionAnswer());
			      doReturn(0.0).when(vectorFunction0).evaluate(anyInt() , anyDouble());
			      Matrix matrix1 = matrix0.transformColumn(a, vectorFunction0);
			      //Boolean result = matrix1.equals((Object)matrix0);//false
			try{ 	 
					 switch (MRname) {
					
					 case "transformColumn":					  
						 //sum =  MethodCollection2.transformColumn(a);
					 try {
						 FileWriter writer = new FileWriter("C:\\MRtest\\"+MRname+".txt",true);
						File file = new File("C:\\MRtest\\"+MRname+".txt");
						if ( file.exists() ){
							 //file.delete();
					    }
						//file.createNewFile();
						writer.write(System.getProperty("FaultName"));
				    	writer.append(','); 
				    	//writer.write(Boolean.toString(result));
				    	writer.write(matrix1.toCSV());
				    	writer.append('\n');
					    writer.close();				     
					 } catch (IOException ex) {
					   // report
					 }
			                 break;
					 case "Addition":
						 testResult =  FrameMethod.Addition(matrix0, matrix1,  "transformColumn");
				         	break;        
					case "Multiplication": 
						 testResult = FrameMethod.Multiplication(matrix0, matrix1, "transformColumn");
		         		 		break; 
			        default: MRname = "Invalid MR";
			                 break;
					 }
				}catch (Exception ex) {
					try {
						FileWriter writer = new FileWriter("C:\\MRtest\\transformColumn_exception.txt",true);
						File file = new File("C:\\MRtest\\transformColumn_exception.txt");
						file.createNewFile();
						writer.append(System.getProperty("FaultName"));
					    writer.append(',');
					    writer.append(System.getProperty("MRname"));
					    writer.append(',');
					    writer.append(System.getProperty("TestNo")); 
					    writer.append('\n');  
					    writer.close();				     
					 } catch (IOException ex1) {
					   // report
					 } 
					   // report
					 System.exit(0);
					 //testResult = false;
					 }
					 return testResult;		
	} 
	 
		
    public boolean  equals(){
				boolean testResult = true;
				String MRname = System.getProperty("MRname");
				int[] intArray0 = null;
				double[][] doubleArray0 = null;
				double[] doubleArray1 = null;
				Matrix matrix2 = null;
				//developer
				 /*Matrix e = m(a(Double.MIN_VALUE, Double.MIN_VALUE),
	                     a(Double.MIN_VALUE, Double.MIN_VALUE));
				 Matrix f = m(a(Double.MIN_NORMAL, Double.MIN_NORMAL),
	                     a(Double.MIN_NORMAL, Double.MIN_NORMAL));
				 //boolean result = e.equals(f, Matrices.EPS);
				 boolean result = e.equals(f);*/
				 //developer2
				 /*Matrix e = mz(2, 2);
			     Matrix f = mz(2, 2);
			     //boolean result = e.equals(f, Matrices.EPS);
			     boolean result = e.equals(f);*/
				//line
				 /*MockRandom mockRandom0 = new MockRandom(1698);
				 Matrix matrix0 = Matrix.random(1698, 1, mockRandom0);
				 VectorFunction vectorFunction0 = mock(VectorFunction.class, new ViolatedAssumptionAnswer());
			     doReturn(0.0).when(vectorFunction0).evaluate(anyInt() , anyDouble());
			     Matrix matrix1 = matrix0.transformRow(1337, vectorFunction0);
			     boolean result = matrix1.equals((Object)matrix0);//false*/
			     //branch
			     /*LinkedList<Double> linkedList0 = new LinkedList<Double>();
			     DenseVector denseVector0 = DenseVector.fromCollection(linkedList0);
			     Matrix matrix0 = denseVector0.toDiagonalMatrix();
			     Matrix matrix1 = matrix0.transformRow(1, (VectorFunction) null);
			     boolean result = matrix1.equals((Object)matrix0);//true*/
			     //wm1
			     /*Matrix matrix0 = Matrix.unit(3338, 3338);
			     intArray0 = new int[5];
			     Matrix matrix1 = matrix0.select(intArray0, intArray0);
			     boolean result = matrix1.equals((Object)matrix0);//false*/
			     //wm2
			     MockRandom mockRandom0 = new MockRandom();
			     DenseMatrix denseMatrix0 = DenseMatrix.randomSymmetric(9, mockRandom0);
			     Matrix matrix1 = denseMatrix0.shuffle();
			     boolean result = matrix1.equals((Object)denseMatrix0);//false
			     //wm3
			     /*doubleArray0 = new double[1][1];
			     doubleArray1 = new double[4];
			     doubleArray0[0] = doubleArray1;
			     Matrix matrix0 = Matrix.from2DArray(doubleArray0);
			     Matrix matrix1 = matrix0.removeFirstColumn();
			     boolean result = matrix1.equals((Object)matrix0);//false*/
			     
				try{ 	 	 
					 switch (MRname) {

					 case "equals": 	 
					 try {
						 FileWriter writer = new FileWriter("C:\\MRtest\\"+MRname+".txt",true);
						File file = new File("C:\\MRtest\\"+MRname+".txt");
						if ( file.exists() ){
							 //file.delete();
					    }
						//file.createNewFile();
				    	writer.write(System.getProperty("FaultName"));
				    	writer.append(','); 
				    	writer.write(Boolean.toString(result));
				    	writer.append('\n'); 
				    	//writer.write(Integer.toString(sum));
					    writer.close();				     
					 } catch (IOException ex) {
					   // report
					 }
			                 break;
					 
					case "Addition":
						//developer
						//testResult= FrameMethod.Addition(e, f, result,"equals");
						//wm2
						testResult= FrameMethod.Addition(denseMatrix0, matrix1, result,"equals");         		 		
						//rest
					     //testResult= FrameMethod.Addition(matrix0, matrix1, result,"equals");
	         		 		break;
					case "Multiplication":
						//developer
						//testResult= FrameMethod.Multiplication(e, f, result,"equals");
						//wm2
						testResult= FrameMethod.Multiplication(denseMatrix0, matrix1, result, "equals");
		         		 //rest
					     //testResult= FrameMethod.Multiplication(matrix0, matrix1, result, "equals");
	         		 break;  
					case "matrixAdd":
						//developer
						//testResult= FrameMethod.matrixAdd(e, f, result,"equals");
						//wm2
						testResult= FrameMethod.matrixAdd(denseMatrix0, matrix1,result, "equals");
		         		 //rest
					     //testResult= FrameMethod.matrixAdd(matrix0, matrix1,result, "equals");
	         		 break;  
					case "matrixMul":
						//developer
						//testResult= FrameMethod.matrixMul(e, f, result,"equals");
						//wm2
						testResult= FrameMethod.matrixMul(denseMatrix0, matrix1,result, "equals");
		         		 //rest
					     //testResult= FrameMethod.matrixMul(matrix0, matrix1,result, "equals");
	         		 break; 
					case "transpose":
						//developer
						//testResult= FrameMethod.transpose(e, f, result,"equals");
						//wm2
						testResult= FrameMethod.transpose(denseMatrix0, matrix1,result, "equals");
		         		 //rest
					     //testResult= FrameMethod.transpose(matrix0, matrix1,result, "equals");
	         		 break;
					case "MultiplyWithIdentity":
						//developer
						//testResult= FrameMethod.MultiplyWithIdentity(e, f, result,"equals");
						//wm2
						testResult= FrameMethod.MultiplyWithIdentity(denseMatrix0, matrix1,result, "equals");
		         		 //rest
					     //testResult= FrameMethod.MultiplyWithIdentity(matrix0, matrix1,result, "equals");
	         		 break;
					default: MRname = "Invalid MR";
			                 break;
					 }
				}catch (Exception ex) {
					try {
						FileWriter writer = new FileWriter("C:\\MRtest\\equals_exception.txt",true);
						File file = new File("C:\\MRtest\\equals_exception.txt");
						file.createNewFile();
						writer.append(System.getProperty("FaultName"));
					    writer.append(',');
					    writer.append(System.getProperty("MRname"));
					    writer.append(',');
					    writer.append(System.getProperty("TestNo")); 
					    writer.append('\n');  
					    writer.close();				     
					 } catch (IOException ex1) {
					   // report
					 } 
					   // report
					 System.exit(0);
					 //testResult = false;
					 }
			return testResult;
	} 

	public boolean  shuffle(){
				boolean testResult = true;
				String MRname = System.getProperty("MRname");
				Random rand = new Random();
				DenseMatrix denseMatrix0 = null;
				Matrix matrix0 = null;
				//developer1
				//Matrix a = m(a(1.0, 2.0),
	            //         a(4.0, 5.0),
	            //         a(7.0, 8.0));
				
				//developer2
				Matrix a = m(a(1.0, 2.0, 3.0),
	                      a(4.0, 5.0, 6.0),
	                      a(7.0, 8.0, 9.0),
	                      a(10.0, 11.0, 12.0),
	                      a(13.0, 14.0, 15.0));
				matrix0 = a.shuffle();
				//line
				//denseMatrix0 = DenseMatrix.randomSymmetric(3, (Random) rand);
			    //matrix0 = denseMatrix0.shuffle();
				boolean result = true;
				try{ 	 	 
					 switch (MRname) {
					
					 case "shuffle": 						 
					      result = matrix0.equals((Object)denseMatrix0);
					 try {
						 FileWriter writer = new FileWriter("C:\\MRtest\\"+MRname+".txt",true);
						File file = new File("C:\\MRtest\\"+MRname+".txt");
						if ( file.exists() ){
							// file.delete();
					    }
						//file.createNewFile();
				    	writer.write(System.getProperty("FaultName"));
				    	writer.append(',');
				    	writer.write(matrix0.toCSV());
				    	writer.append('\n');
					    writer.close();				     
					 } catch (IOException ex) {
					   // report
					 }
			                 break;
					 case "permuteRow":
						 	//developer1
						    testResult = FrameMethod.permuteRow(a,matrix0, "shuffle");
							//line return false
						    //testResult = FrameMethod.permuteRow(denseMatrix0,matrix0, "shuffle");
				            break;
					 case "permuteElement":
						 	//developer1
						    testResult = FrameMethod.permuteElement(a,matrix0, "shuffle");
							//line return false
							//testResult = FrameMethod.permuteElement(denseMatrix0,matrix0, "shuffle");
					        break;
					 case "permuteColumn":
						 	//developer1
						    testResult = FrameMethod.permuteColumn(a,matrix0, "shuffle");
							//line return false
							//testResult = FrameMethod.permuteColumn(denseMatrix0,matrix0, "shuffle");
					        break;
					 default: MRname = "Invalid MR";
			                 break;
					 }
	}catch (Exception ex) {
		try {
			FileWriter writer = new FileWriter("C:\\MRtest\\shuffle_exception.txt",true);
			File file = new File("C:\\MRtest\\shuffle_exception.txt");
			file.createNewFile();
			writer.append(System.getProperty("FaultName"));
		    writer.append(',');
		    writer.append(System.getProperty("MRname"));
		    writer.append(',');
		    writer.append(System.getProperty("TestNo")); 
		    writer.append('\n');  
		    writer.close();				     
		 } catch (IOException ex1) {
		   // report
		 } 
		   // report
		 System.exit(0);
		 //testResult = false;
		 }
					 return testResult;
			
	} 


		public boolean  rotate(){
		
				boolean testResult = true;
				String MRname = System.getProperty("MRname");
				DenseMatrix denseMatrix0 = null;
				Matrix matrix0 = null;
				//developer1
				//Matrix a = m(a(1.0),
	            //          a(3.0),
	            //          a(5.0));
				//developer2
				//Matrix a = m(a(1.0, 2.0),
	            //          a(3.0, 4.0));
				
				//developer3
				//Matrix a = m(a(1.0, 2.0, 3.0, 4.0),
	            //          a(5.0, 6.0, 7.0, 8.0));
				
				//developer4
				Matrix a = m(a(1.0, 2.0, 3.0),
	                      a(4.0, 5.0, 6.0),
	                      a(7.0, 8.0, 9.0),
	                      a(10.0, 11.0, 12.0),
	                      a(13.0, 14.0, 15.0));
				
				matrix0 = a.rotate();
				//line
				//MockRandom mockRandom0 = new MockRandom();
				//denseMatrix0 = DenseMatrix.randomSymmetric(1, (Random) mockRandom0);
			    //matrix0 = denseMatrix0.rotate();

				//wm
				//MockRandom mockRandom0 = new MockRandom();
			    //denseMatrix0 = DenseMatrix.randomSymmetric(9, mockRandom0);
			    //matrix0 = denseMatrix0.rotate();
				try{ 	 
					 switch (MRname) {
					
					 case "rotate":						 
					     boolean result = matrix0.equals((Object)denseMatrix0);//false*/
					 try {
						 FileWriter writer = new FileWriter("C:\\MRtest\\"+MRname+".txt",true);
						File file = new File("C:\\MRtest\\"+MRname+".txt");
						if ( file.exists() ){
							// file.delete();
					    }
						//file.createNewFile();
						writer.write(System.getProperty("FaultName"));
				    	writer.append(',');
				    	writer.write(matrix0.toCSV());
				    	writer.append('\n');
					    writer.close();				     
					 } catch (IOException ex) {
					   // report
					 }
			                 break;
					 case "Addition": 
						 //developer1
						 testResult= FrameMethod.Addition(a, matrix0, "rotate");
					     //line,branch
					     //testResult= FrameMethod.Addition(denseMatrix0, matrix0, "rotate");
			         		 break;        
					 case "Multiplication":
						//developer1
						 testResult= FrameMethod.Multiplication(a, matrix0, "rotate");
					     //line,branch
					       //testResult=FrameMethod.Multiplication(denseMatrix0, matrix0, "rotate");
	         		 		break; 
					 case "matrixAdd":
						//developer1
						 testResult= FrameMethod.matrixAdd(a, matrix0, "rotate");
					     //line,branch
						  // testResult=FrameMethod.matrixAdd(denseMatrix0, matrix0,  "rotate");
		         		 	break;
		         	case "permuteRow":
		         		//developer1
						 testResult= FrameMethod.permuteRow(a, matrix0, "rotate");
					     //line,branch
					      // testResult=FrameMethod.permuteRow(denseMatrix0, matrix0,  "rotate");
	         		 		break;
		         	case "permuteElement":
		         		//developer1
						 testResult= FrameMethod.permuteElement(a, matrix0, "rotate");
					     //line,branch
					      // testResult=FrameMethod.permuteElement(denseMatrix0, matrix0,"rotate");
	         		 		break;
		         	case "permuteColumn":
		         		//developer1
						 testResult= FrameMethod.permuteColumn(a, matrix0, "rotate");
					     //line,branch
					      // testResult=FrameMethod.permuteColumn(denseMatrix0, matrix0,"rotate");
	         		 		break;
			        default: MRname = "Invalid MR";
			                 break;
					 }
		}catch (Exception ex) {
			try {
				FileWriter writer = new FileWriter("C:\\MRtest\\rotate_exception.txt",true);
				File file = new File("C:\\MRtest\\rotate_exception.txt");
				file.createNewFile();
				writer.append(System.getProperty("FaultName"));
			    writer.append(',');
			    writer.append(System.getProperty("MRname"));
			    writer.append(',');
			    writer.append(System.getProperty("TestNo")); 
			    writer.append('\n');  
			    writer.close();				     
			 } catch (IOException ex1) {
			   // report
			 } 
			   // report
			 System.exit(0);
			 //testResult = false;
			 }
					 return testResult;
	} 


		public boolean  removeLastRow(){
				boolean testResult = true;
				String MRname = System.getProperty("MRname");
				//developer
				 Matrix a = m(a(1.0, 0.0, 4.0),
	                     a(0.0, 5.0, 3.0),
	                     a(9.0, 0.0, 0.0));
				Matrix matrix1 = a.removeLastRow();
				//line
				//MockRandom mockRandom0 = new MockRandom();
				//Matrix matrix0 = Matrix.randomSymmetric(16, mockRandom0);
			    //Matrix matrix1 = matrix0.removeLastRow();
				try{ 	 	 
					 switch (MRname) {					
					 case "removeLastRow": 					
					 try {
						 FileWriter writer = new FileWriter("C:\\MRtest\\"+MRname+".txt",true);
						File file = new File("C:\\MRtest\\"+MRname+".txt");
						if ( file.exists() ){
							 //file.delete();
					    }
						//file.createNewFile();
						writer.write(System.getProperty("FaultName"));
				    	writer.append(',');
				    	writer.write(matrix1.toCSV());
				    	writer.append('\n');
					    writer.close();				     
					 } catch (IOException ex) {
					   // report
					 }
			                 break;
					 case "Addition":
						//developer
					     testResult=  FrameMethod.Addition(a,matrix1, "removeLastRow" );
					     //line
					     //testResult=  FrameMethod.Addition(matrix0,matrix1, "removeLastRow" );
			         		 break;        
					 case "Multiplication":
						//developer
					     testResult=  FrameMethod.Multiplication(a,matrix1, "removeLastRow" );
					     //line
					     //testResult= FrameMethod.Multiplication(matrix0,matrix1, "removeLastRow");
	         		 		break;
			        default: MRname = "Invalid MR";
			                 break;
					 }
		}catch (Exception ex) {
			try {
				FileWriter writer = new FileWriter("C:\\MRtest\\removeLastRow_exception.txt",true);
				File file = new File("C:\\MRtest\\removeLastRow_exception.txt");
				file.createNewFile();
				writer.append(System.getProperty("FaultName"));
			    writer.append(',');
			    writer.append(System.getProperty("MRname"));
			    writer.append(',');
			    writer.append(System.getProperty("TestNo")); 
			    writer.append('\n');  
			    writer.close();				     
			 } catch (IOException ex1) {
			   // report
			 } 
			   // report
			 System.exit(0);
			 //testResult = false;
			 }
					 return testResult;
 }

	
		public boolean  removeLastColumn(){
				boolean testResult = true;
				String MRname = System.getProperty("MRname");
				//developer
				 Matrix a = m(a(1.0, 0.0, 4.0),
	                     a(0.0, 5.0, 3.0),
	                     a(9.0, 0.0, 0.0));
				Matrix matrix1 = a.removeLastColumn();
				//line
				 //MockRandom mockRandom0 = new MockRandom();
			     //Matrix matrix0 = Matrix.randomSymmetric(16, mockRandom0);
			     //Matrix matrix1 = matrix0.removeLastColumn();

			     //wm
			     //MockRandom mockRandom0 = new MockRandom();
			     //DenseMatrix denseMatrix0 = DenseMatrix.randomSymmetric(9, mockRandom0);
			     //Matrix matrix1 = denseMatrix0.removeLastColumn();
				try{ 	 
					 switch (MRname) {
					 case "removeLastColumn":
					 try {
						 FileWriter writer = new FileWriter("C:\\MRtest\\"+MRname+".txt",true);
						File file = new File("C:\\MRtest\\"+MRname+".txt");
						if ( file.exists() ){
							// file.delete();
					    }
						//file.createNewFile();
						writer.write(System.getProperty("FaultName"));
				    	writer.append(',');
				    	writer.write(matrix1.toCSV());
				    	writer.append('\n');
					    writer.close();				     
					 } catch (IOException ex) {
					   // report
					 }
			                 break;
					 case "Addition":
						//developer
						 testResult= FrameMethod.Addition(a,matrix1, "removeLastColumn");		         		 						 
						//line
						 //testResult= FrameMethod.Addition(matrix0,matrix1, "removeLastColumn");		         		 
						 //wm
						 //testResult= FrameMethod.Addition(denseMatrix0,matrix1, "removeLastColumn");
			         		 break;        
					 case "Multiplication":
						//developer
						 testResult= FrameMethod.Multiplication(a,matrix1, "removeLastColumn");		         		 						 						
						//line
						 //testResult= FrameMethod.Multiplication(matrix0,matrix1, "removeLastColumn");		         		 
						 //wm
						 //testResult= FrameMethod.Multiplication(denseMatrix0,matrix1, "removeLastColumn");
	         		 		break;
					 
					 default: MRname = "Invalid MR";
			                 break;
					 }
		}catch (Exception ex) {
			try {
				FileWriter writer = new FileWriter("C:\\MRtest\\removeLastColumn_exception.txt",true);
				File file = new File("C:\\MRtest\\removeLastColumn_exception.txt");
				file.createNewFile();
				writer.append(System.getProperty("FaultName"));
			    writer.append(',');
			    writer.append(System.getProperty("MRname"));
			    writer.append(',');
			    writer.append(System.getProperty("TestNo")); 
			    writer.append('\n');  
			    writer.close();				     
			 } catch (IOException ex1) {
			   // report
			 } 
			   // report
			 System.exit(0);
			 //testResult = false;
			 }
				//sum = MethodCollection2.array_copy(a);
			return  testResult;
	} 

	
		public boolean  power(){
				//int a = 1293;	
				boolean testResult = true;
				String MRname = System.getProperty("MRname");
				//line
				MockRandom mockRandom0 = new MockRandom();
			    DenseMatrix denseMatrix0 = DenseMatrix.randomSymmetric(2, mockRandom0);
			    //experiment denseMatrix0 = (DenseMatrix) denseMatrix0.multiply(4.99);
			    Matrix matrix0 = denseMatrix0.power(1293);
			    //developer
			    /*Matrix a = m(a(1.0, 2.0),
	                     a(3.0, 4.0));
			    Matrix matrix0 = a.power(2);*/
				try{ 	 	 
					 switch (MRname) {
					
					 case "power": 
					     //boolean result = matrix0.equals((Object)denseMatrix0);//true
						 //sum=  MethodCollection2.find_euc_dist(a, b);
					 try {
						 FileWriter writer = new FileWriter("C:\\MRtest\\"+MRname+".txt",true);
						File file = new File("C:\\MRtest\\"+MRname+".txt");
						if ( file.exists() ){
							// file.delete();
					    }
						//file.createNewFile();
				    	writer.write(System.getProperty("FaultName"));
				    	writer.write(denseMatrix0.toCSV());
				    	writer.append(','); 
				    	//writer.write(Boolean.toString(result));
				    	writer.write(matrix0.toCSV());
				    	writer.append('\n');
					    writer.close();				     
					 } catch (IOException ex) {
					   // report
					 }
			                 break;
					 case "Multiplication":
					     //result = matrix0.equals((Object)denseMatrix0);//true
						 testResult=FrameMethod.Multiplication(denseMatrix0, matrix0, "power");
						 //developer
					     //testResult=FrameMethod.Multiplication(a, matrix0, "power");
	         		 		break;
					 case "matrixMul":
					     //result = matrix0.equals((Object)denseMatrix0);//true
						 testResult=FrameMethod.matrixMul(denseMatrix0, matrix0, "power");
						 //developer
					     //testResult=FrameMethod.matrixMul(a, matrix0, "power");
	         		 		break;
					 case "transpose":
					     //result = matrix0.equals((Object)denseMatrix0);//true
						 testResult=FrameMethod.transpose(denseMatrix0, matrix0, "power");
						 //developer
					     //testResult=FrameMethod.transpose(a, matrix0, "power");
	         		 		break;
					 case "MultiplyWithIdentity":
					     //result = matrix0.equals((Object)denseMatrix0);//true
						 testResult=FrameMethod.MultiplyWithIdentity(denseMatrix0, matrix0, "power");
						 //developer
					     //testResult=FrameMethod.MultiplyWithIdentity(a, matrix0, "power");
	         		 		break;
					 default: MRname = "Invalid MR";
			                 break;
					 }
		}catch (Exception ex) {
			try {
				FileWriter writer = new FileWriter("C:\\MRtest\\power_exception.txt",true);
				File file = new File("C:\\MRtest\\power_exception.txt");
				file.createNewFile();
				writer.append(System.getProperty("FaultName"));
			    writer.append(',');
			    writer.append(System.getProperty("MRname"));
			    writer.append(',');
			    writer.append(System.getProperty("TestNo")); 
			    writer.append('\n');  
			    writer.close();				     
			 } catch (IOException ex1) {
			   // report
			 } 
			   // report
			 System.exit(0);
			 }
					 return testResult;
			
	} 	 

	
		public boolean  multiply(){
				boolean testResult = true;
				String MRname = System.getProperty("MRname");
				MockRandom mockRandom0 = null;
				Matrix matrix0 = null;
				Matrix matrix1 = null;
				DenseVector denseVector0 = null;
				DenseMatrix denseMatrix0 = null;
				//developer1
				Matrix a = m(a(1.0, 0.0, 3.0),
	                     a(0.0, 5.0, 0.0));
				matrix1 = a.multiply(2.0);
				//developer2
				/*Matrix a = m(a(8.0, 3.0, 1.0, 9.0),
	                       a(4.0, 9.0, 6.0, 6.0),
	                       a(9.0, 1.0, 1.0, 4.0),
	                       a(5.0, 7.0, 3.0, 0.0));
				for (Matrix b: ms(a(4.0, 9.0, 0.0, 3.0),
                        a(6.0, 7.0, 7.0, 6.0),
                        a(9.0, 4.0, 3.0, 3.0),
                        a(4.0, 4.0, 1.0, 6.0))) {

				
				matrix1 = a.multiply(b);
				}*/
				//developer3
				/*Matrix a = m(a(5.98, 3.76, 9.01, 9.68, 2.12, 6.34, 0.64, 6.22, 1.16),
	                       a(8.4, 9.65, 7.06, 2.56, 7.66, 4.69, 3.29, 8.6, 8.55),
	                       a(4.99, 7.06, 6.07, 7.53, 0.08, 1.08, 9.69, 8.51, 6.61),
	                       a(4.72, 7.06, 4.0, 0.75, 2.45, 4.4, 8.33, 5.81, 0.57));
				Matrix b = m(a(9.28, 7.63, 4.1, 4.71),
                        a(4.68, 2.82, 9.18, 5.39),
                        a(4.54, 6.86, 1.29, 5.4),
                        a(8.72, 2.06, 4.28, 7.37),
                        a(2.43, 3.7, 7.52, 5.87),
                        a(8.21, 9.36, 4.85, 0.3),
                        a(9.87, 8.19, 5.03, 6.14),
                        a(9.47, 4.28, 3.86, 3.12),
                        a(5.29, 4.41, 5.23, 4.85)) ;

				matrix1 = a.multiply(b);*/
				
				//line
				 //mockRandom0 = new MockRandom(85);
			     //matrix0 = Matrix.random(85, 85, mockRandom0);
			     //matrix1 = matrix0.multiply((double) 85);
			     //branch
			     denseVector0 = DenseVector.zero(3405);
			     matrix0 = denseVector0.toRowMatrix();
			     matrix1 = matrix0.multiply((double) 3405);
			     //wm
			     //mockRandom0 = new MockRandom();
			     //denseMatrix0 = DenseMatrix.randomSymmetric(9, mockRandom0);
			     //matrix1 = denseMatrix0.multiply((-119.9752908860286));
				try{ 	 		 
					 switch (MRname) {
					 case "multiply":	 
					 try {
						 FileWriter writer = new FileWriter("C:\\MRtest\\"+MRname+".txt",true);
						File file = new File("C:\\MRtest\\"+MRname+".txt");
						if ( file.exists() ){
							// file.delete();
					    }
						//file.createNewFile();
				    	writer.write(System.getProperty("FaultName"));
				    	writer.append(','); 
				    	writer.write(matrix1.toCSV());
				    	writer.append('\n'); 
					    writer.close();				     
					 } catch (IOException ex) {
					   // report
					 }
			                 break;
					 case "transpose":
						//developer
						 //testResult=FrameMethod.transpose(a, matrix1, "multiply");						 
						 //branch,line
						 testResult=FrameMethod.transpose(matrix0, matrix1, "multiply");
						 //wm
					     //testResult=FrameMethod.transpose(denseMatrix0, matrix1, "multiply");
	         		 		break;
					 case "MultiplyWithIdentity":
						//developer
						 //testResult=FrameMethod.MultiplyWithIdentity(a, matrix1, "multiply");						 						 
						//branch,line
						 testResult=FrameMethod.MultiplyWithIdentity(matrix0, matrix1, "multiply");
						//wm
					     //testResult=FrameMethod.MultiplyWithIdentity(denseMatrix0, matrix1, "multiply");
	         		 	break;		
			        default: MRname = "Invalid MR";
			                 break;
					 }
				}catch (Exception ex) {
					try {
				FileWriter writer = new FileWriter("C:\\MRtest\\multiply_exception.txt",true);
				File file = new File("C:\\MRtest\\multiply_exception.txt");
				file.createNewFile();
				writer.append(System.getProperty("FaultName"));
			    writer.append(',');
			    writer.append(System.getProperty("MRname"));
			    writer.append(',');
			    writer.append(System.getProperty("TestNo")); 
			    writer.append('\n');  
			    writer.close();				     
			 } catch (IOException ex1) {
			   // report
			 } 
			   // report
			 System.exit(0);
			 }
						return testResult;	
			
	}

	
		public boolean  insert(){
				boolean testResult = true;
				String MRname = System.getProperty("MRname");
				double[][] doubleArray0 = null;
				Matrix matrix0 = null;
				ColumnMajorSparseMatrix columnMajorSparseMatrix0 = null;
				Matrix matrix1 = null;
				boolean result = true;
				MockRandom mockRandom0 = null;
				//developer1
				/*Matrix a = m(a(1.0, 2.0, 3.0),
	                     a(4.0, 5.0, 6.0),
	                     a(7.0, 8.0, 9.0));
				Matrix b = mz(3, 3);
				matrix1 = b.insert(a);*/
				//developer2
				/*Matrix a = m(a(1.0, 2.0),
	                     a(3.0, 4.0));

				Matrix b = mz(3, 3);
				matrix1 = b.insert(a).slice(0, 0, 2, 2);*/
				//developer3
				/*Matrix a = m(a(1.0, 2.0, 3.0),
	                     a(4.0, 5.0, 6.0),
	                     a(7.0, 8.0, 9.0));

				Matrix b = mz(4, 4);
				matrix1 = b.insert(a, 1, 1, 1, 1, 2, 2).slice(1, 1, 3, 3);*/
				//developer4
				/*Matrix a = m(a(1.0, 2.0),
	                     a(3.0, 4.0));
	        
				Matrix b = mz(3, 3);
				matrix1 = b.insert(a, 1, 1, a.rows(), a.columns()).slice(1, 1, 3, 3);*/
				//developer5
				/*Matrix a = m(a(1.0),
	                     a(2.0),
	                     a(3.0));

				Matrix b = mz(3, 3);
				matrix1 = b.insert(a, 0, 2, a.rows(), a.columns()).slice(0, 2, 3, 3);*/
				//developer6
				 Matrix a = m(a(1.0, 2.0, 3.0));
			     Matrix b = mz(3, 3);
			     matrix1 = b.insert(a, 2, 0, a.rows(), a.columns()).slice(2, 0, 3, 3);
				 //line
				  //mockRandom0 = new MockRandom(10);
			      //matrix0 = Matrix.random(10, 1, mockRandom0);
			      //matrix1 = matrix0.insert(matrix0, 0, 1, 10, 0);
			      //result = matrix1.equals((Object)matrix0);//true*/
			     //wm
			     /*doubleArray0 = new double[4][1];
			     matrix0 = Matrix.from2DArray(doubleArray0);
			     columnMajorSparseMatrix0 = ColumnMajorSparseMatrix.from1DArray(0, 0, doubleArray0[0]);
			     matrix1 = matrix0.insert((Matrix) columnMajorSparseMatrix0, 0, 0);
			     //result = matrix1.equals((Object)matrix0);//true*/
				try{ 	 
					 switch (MRname) {
					 case "insert":
					 try {
						 FileWriter writer = new FileWriter("C:\\MRtest\\"+MRname+".txt",true);
						File file = new File("C:\\MRtest\\"+MRname+".txt");
						if ( file.exists() ){
							// file.delete();
					    }
						//file.createNewFile();
				    	//writer.write(Integer.toString(a));
				    	//writer.append('\n'); 
				    	//writer.write(matrix1.toCSV());
						writer.append(System.getProperty("FaultName"));
					    writer.append(',');
					    writer.append('\n');
						//writer.write(Boolean.toString(result));
						writer.write(matrix1.toCSV());
				    	writer.append('\n');
					    writer.close();				     
					 } catch (IOException ex) {
					   // report
					 }
			                 break;
					 case "Addition":
						 //developer
						 testResult= FrameMethod.Addition(a,b, matrix1, "insert");
						 	 //line
						     //testResult= FrameMethod.Addition(matrix0, matrix1, "insert");
						     //wm
						     //testResult= FrameMethod.Addition((Matrix) columnMajorSparseMatrix0, matrix1, "insert");						     
	         		 		break;
	         		 case "Multiplication":
	         			//developer
						 testResult= FrameMethod.Multiplication(a,b, matrix1, "insert");
	         			 //line
	         			//testResult= FrameMethod.Multiplication(matrix0, matrix1, "insert");
	         			//wm
					    //testResult= FrameMethod.Multiplication((Matrix) columnMajorSparseMatrix0, matrix1, "insert");
					     break;
	         		 case "permuteRow":
	         			//developer
						 testResult= FrameMethod.permuteRow(a,b, matrix1, "insert");
	         			 	//line
		         			//testResult= FrameMethod.permuteRow(matrix0, matrix1, "insert");
		         			//wm
						    //testResult= FrameMethod.permuteRow((Matrix) columnMajorSparseMatrix0, matrix1, "insert");
						 break;
	         		 case "permuteElement":
	         			//developer
						 testResult= FrameMethod.permuteElement(a,b, matrix1, "insert");
	         			 	//line
		         			//testResult= FrameMethod.permuteElement(matrix0, matrix1, "insert");
		         			//wm
						    //testResult= FrameMethod.permuteElement((Matrix) columnMajorSparseMatrix0, matrix1, "insert");
						   break;
	         		 case "permuteColumn": //not applicable
	         			//developer
						 testResult= FrameMethod.permuteColumn(a,b, matrix1, "insert");
	         			 	//line
		         			//testResult= FrameMethod.permuteColumn(matrix0, matrix1, "insert");
		         			//wm
						    //testResult= FrameMethod.permuteColumn((Matrix) columnMajorSparseMatrix0, matrix1, "insert");
						    break;
			        default: MRname = "Invalid MR";
			                 break;
					 }
		}catch (Exception ex) {
			try {
		FileWriter writer = new FileWriter("C:\\MRtest\\insert_exception.txt",true);
		File file = new File("C:\\MRtest\\insert_exception.txt");
		file.createNewFile();
		writer.append(System.getProperty("FaultName"));
	    writer.append(',');
	    writer.append(System.getProperty("MRname"));
	    writer.append(',');
	    writer.append(System.getProperty("TestNo")); 
	    writer.append('\n');  
	    writer.close();				     
	 } catch (IOException ex1) {
	   // report
	 } 
	   // report
	 System.exit(0);
	 }
 return testResult;
			
	} 	 
	 
	
		public boolean  fromCSV(){
		
				//int[] a = FrameMethod.getIntArray();
				//line
				//int[] a = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
				//branch
				//int[] a = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
				//wm
				//int[] a = {0,-3681};
				//boolean result = true;
				//double sum = 0;
				boolean testResult = true;
				String MRname = System.getProperty("MRname");
				//	method = "sequential_search";
				/*if(!MRname.equals("average")){
					try (BufferedReader br = new BufferedReader(new FileReader("C:\\MRtest\\average.txt")))
					{
						String sCurrentLine;
						a = null;
						sum = 0;
						while ((sCurrentLine = br.readLine()) != null) {
							//a = Arrays;
							
							if(sCurrentLine.startsWith("[")){
								if(a==null){
									a = Arrays.stream(sCurrentLine.substring(1, sCurrentLine.length()-1).split(","))
										    .map(String::trim).mapToInt(Integer::parseInt).toArray();
								}
						}
							else {sum =Double.parseDouble(sCurrentLine);}
								
						// (new File("C:\\MRtest\\"+MRname+".txt")).delete();
						}
					} catch (IOException e) {
						//e.printStackTrace();
					} 
				} */
				try{ 	 	 
					 switch (MRname) {
					
					 case "fromCSV":
						 //line
						 //Matrix matrix0 = Matrix.fromCSV("     0.000, 0.100");
						 //branch
						 //Matrix matrix0 = Matrix.fromCSV("");
						 //wm
						 Matrix matrix0 = Matrix.fromCSV(" ");
					 try {
						 FileWriter writer = new FileWriter("C:\\MRtest\\"+MRname+".txt",true);
						File file = new File("C:\\MRtest\\"+MRname+".txt");
						if ( file.exists() ){
							// file.delete();
					    }
						//file.createNewFile();
				    	writer.write(matrix0.toCSV());
				    	writer.append('\n'); 
					    writer.close();				     
					 } catch (IOException ex) {
					   // report
					 }
			                 break;
					 case "Addition"://testResult=  FrameMethod.Addition(a,sum, "average" );
			         		 break;        
					 case "Permutation"://testResult=  FrameMethod.Permutation(a, sum, "average");
	         		 		break; 
					 case "Multiplication"://testResult= FrameMethod.Multiplication(a, sum, "average");
	         		 		break;
					 case "Inversion"://testResult=  FrameMethod.Inversion(a, sum, "average");
	 		 			break;		
			 
			        default: MRname = "Invalid MR";
			                 break;
					 }
		}catch (Exception ex) {
			try {
		FileWriter writer = new FileWriter("C:\\MRtest\\fromCSV_exception.txt",true);
		File file = new File("C:\\MRtest\\fromCSV_exception.txt");
		file.createNewFile();
		writer.append(System.getProperty("FaultName"));
	    writer.append(',');
	    writer.append(System.getProperty("MRname"));
	    writer.append(',');
	    writer.append(System.getProperty("TestNo")); 
	    writer.append('\n');  
	    writer.close();				     
	 } catch (IOException ex1) {
	   // report
	 } 
	   // report
	 System.exit(0);
	 }
					 return testResult;
	}


		public boolean  subtract(){
				boolean testResult = true;
				String MRname = System.getProperty("MRname");
				double[][] doubleArray0 = null;
				Matrix matrix0 = null;
				Matrix matrix1 = null;
				Boolean result = true;
				//developer
				/*Matrix a = m(a(1.0, 0.0, 0.0),
                        a(0.0, 5.0, 0.0),
                        a(0.0, 0.0, 9.0));*/
				Matrix a = m(a(10.0, 10.0, 10.0, 10.0),
                        a(10.0, 10.0, 10.0, 10.0));
				matrix1 = a.subtract(10.0);
				//line
				//doubleArray0 = new double[2][6];
			    //matrix0 = Matrix.from2DArray(doubleArray0);
			    //matrix1 = matrix0.subtract(0.0);
				try{ 		 
					 switch (MRname) {
					 case "subtract":  
					      result = matrix1.equals((Object)matrix0);//true
						 //sum= MethodCollection2.find_max(a);
					 try {
						 FileWriter writer = new FileWriter("C:\\MRtest\\"+MRname+".txt",true);
						File file = new File("C:\\MRtest\\"+MRname+".txt");
						if ( file.exists() ){
							 //file.delete();
					    }
						//file.createNewFile();
						writer.append(System.getProperty("FaultName"));
					    writer.append(',');
						//writer.write(Boolean.toString(result));
						writer.write(matrix1.toCSV());
				    	writer.append('\n'); 
				    	writer.close();				     
					 } catch (IOException ex) {
					   // report
					 }
			                 break;
					 case "Addition":
						 //developer
						 testResult= FrameMethod.Addition(a,matrix1, "subtract" );						 
					      //line
						 //testResult= FrameMethod.Addition(matrix0,matrix1, "subtract" );
						 break;        
					 case "Multiplication":
						//developer
						 testResult= FrameMethod.Multiplication(a,matrix1, "subtract" );						 
					      //line
						 //testResult= FrameMethod.Multiplication(matrix0,matrix1, "subtract" );
						 break; 
					 case "transpose":
						//developer
						 testResult= FrameMethod.transpose(a,matrix1, "subtract" );						 
					      //line
						 //testResult= FrameMethod.transpose(matrix0,matrix1, "subtract" );
						 break;		
					 case "AdditionWithIdentity":// relation true
						//developer
						 testResult= FrameMethod.AdditionWithIdentity(a,matrix1, "subtract" );						 
					      //line
						    //doubleArray0 = new double[6][6];
						    //matrix0 = Matrix.from2DArray(doubleArray0);
						    //matrix1 = matrix0.subtract(0.0);
					        //testResult= FrameMethod.AdditionWithIdentity(matrix0,matrix1, "subtract" );
						  break; 
					 case "MultiplyWithIdentity":// relation true
						//developer
						 testResult= FrameMethod.MultiplyWithIdentity(a,matrix1, "subtract" );						 
					      //line
						    //doubleArray0 = new double[6][6];
						    //matrix0 = Matrix.from2DArray(doubleArray0);
						    //matrix1 = matrix0.subtract(0.0);
					        //testResult = FrameMethod.MultiplyWithIdentity(matrix0, matrix1,"subtract");
						    break;  
					default: MRname = "Invalid MR";
			                 break;
					 }
		}catch (Exception ex) {
			try {
		FileWriter writer = new FileWriter("C:\\MRtest\\subtract_exception.txt",true);
		File file = new File("C:\\MRtest\\subtract_exception.txt");
		file.createNewFile();
		writer.append(System.getProperty("FaultName"));
	    writer.append(',');
	    writer.append(System.getProperty("MRname"));
	    writer.append(',');
	    writer.append(System.getProperty("TestNo")); 
	    writer.append('\n');  
	    writer.close();				     
	 } catch (IOException ex1) {
	   // report
	 } 
	   // report
	 System.exit(0);
	 }
					 return testResult;
	}
	 	 
	 
		public boolean  add(){
				boolean testResult = true;
				String MRname = System.getProperty("MRname");
				double[][] doubleArray0 = null;
				Matrix matrix0 = null;
				Matrix matrix1 = null;
				DenseMatrix denseMatrix0 = null;
				//developer1
				/*Matrix a = m(a(1.0, 0.0, 0.0),
                        a(0.0, 5.0, 0.0),
                        a(0.0, 0.0, 9.0));*/
				//developer2
				Matrix a = m(a(12.0, 10.0),
                        a(10.0, 20.0),
                        a(24.0, 10.0),
                        a(10.0, 28.0));
				matrix1 = a.add(10);
				
				 //branch
				  //doubleArray0 = new double[1][6];
			      //matrix0 = Matrix.from2DArray(doubleArray0);
			      //matrix1 = matrix0.add(-3300.470609235);
			      //wm
			       //denseMatrix0 = DenseMatrix.diagonal(37, (-205.4383));
			       //matrix1 = denseMatrix0.add((-205.4383));
				try{ 		 
					 switch (MRname) {
					 case "add":	
					 try {
						 FileWriter writer = new FileWriter("C:\\MRtest\\"+MRname+".txt",true);
						File file = new File("C:\\MRtest\\"+MRname+".txt");
						if ( file.exists() ){
							// file.delete();
					    }
						//file.createNewFile();
						writer.append(System.getProperty("FaultName"));
					    writer.append(',');
						writer.write(matrix1.toCSV());
				    	writer.append('\n'); 
				    	
				    	//writer.write(Integer.toString(sum));
					    writer.close();				     
					 } catch (IOException ex) {
					   // report
					 }
			                 break;
					 case "Addition":
						 //developer1
						 testResult= FrameMethod.Addition(a,matrix1, "add" );
						 //branch
						 //testResult= FrameMethod.Addition(matrix0,matrix1, "add" );
						 //wm
						 //testResult= FrameMethod.Addition(denseMatrix0,matrix1, "add" );
			         		 break;        
					 case "Multiplication":
						//developer1
						 testResult= FrameMethod.Multiplication(a,matrix1, "add" );
						//branch
						 //testResult= FrameMethod.Multiplication(matrix0,matrix1, "add" );
						//wm
						  //testResult= FrameMethod.Multiplication(denseMatrix0, matrix1, "add");
	         		 		break; 
					 case "transpose":
						//developer1
						 testResult= FrameMethod.transpose(a,matrix1, "add" );
						//branch
						 //testResult= FrameMethod.transpose(matrix0,matrix1, "add" );
						//wm
						  //testResult=FrameMethod.transpose(denseMatrix0, matrix1, "add");
		         		 		break;		
					 case "AdditionWithIdentity":// relation true
						//developer1
						 testResult= FrameMethod.AdditionWithIdentity(a,matrix1, "add" );
						//branch not applicable
						 //testResult= FrameMethod.AdditionWithIdentity(matrix0,matrix1, "add" );
						//wm
						  //testResult = FrameMethod.AdditionWithIdentity(denseMatrix0, matrix1,"add");
				         		 break; 
					 case "MultiplyWithIdentity":// relation true
						//developer1
						 testResult= FrameMethod.MultiplyWithIdentity(a,matrix1, "add" );
						//branch not applicable
						 //testResult= FrameMethod.MultiplyWithIdentity(matrix0,matrix1, "add" );
						//wm
						  //testResult = FrameMethod.MultiplyWithIdentity(denseMatrix0, matrix1, "add");
				         		 break; 
					 default: MRname = "Invalid MR";
				            break;
					 }
						}catch (Exception ex) {
							try {
						FileWriter writer = new FileWriter("C:\\MRtest\\add_exception.txt",true);
						File file = new File("C:\\MRtest\\add_exception.txt");
						file.createNewFile();
						writer.append(System.getProperty("FaultName"));
					    writer.append(',');
					    writer.append(System.getProperty("MRname"));
					    writer.append(',');
					    writer.append(System.getProperty("TestNo")); 
					    writer.append('\n');  
					    writer.close();				     
					 } catch (IOException ex1) {
					   // report
					 } 
					   // report
					 System.exit(0);
					 }
					 return	 testResult;
					
	}

	
		public boolean  select(){
				boolean testResult = true;
				String MRname = System.getProperty("MRname");
				//developer
				Matrix a = m(a(8.93, 5.88, 6.57, 9.3, 3.65, 0.08),
		                 a(3.96, 8.26, 2.51, 9.61, 4.63, 6.12),// 1
		                 a(7.37, 5.79, 8.8, 0.87, 7.83, 6.15),// 2
		                 a(3.43, 9.08, 1.16, 2.3, 3.66, 4.93),// 3
		                 a(7.05, 7.75, 8.11, 2.93, 9.04, 6.72)); // 4
				//developer1
		        //int[] rowInd = new int[]{1, 3, 4};
		        //int[] colInd = new int[]{0, 1, 2, 3, 4, 5};
		        
		        //developer2
		        //int[] rowInd = new int[]{0, 1, 2, 3, 4};
		        //int[] colInd = new int[]{0, 2, 4, 5}; // all columns
		        //devloper3
		        int[] rowInd = new int[]{1, 3, 3, 4};
		        int[] colInd = new int[]{2, 2, 4, 5, 5};
		        
		        Matrix matrix1 = a.select(rowInd, colInd);
				  //branch
				  /*int[] intArray0 = new int[8];
				  double[][] doubleArray0 = new double[1][9];
			      DenseMatrix denseMatrix0 = DenseMatrix.from2DArray(doubleArray0);
			      Matrix matrix1 = denseMatrix0.select(intArray0, intArray0);*/
			      //wm
			      //int[] intArray0 = new int[5];
			      //Matrix matrix0 = Matrix.unit(3338, 3338);
			      //Matrix matrix1 = matrix0.select(intArray0, intArray0);
				try{ 		 
					 switch (MRname) {
					
					 case "select":						 
					 try {
						 FileWriter writer = new FileWriter("C:\\MRtest\\"+MRname+".txt",true);
						File file = new File("C:\\MRtest\\"+MRname+".txt");
						if ( file.exists() ){
							// file.delete();
					    }
						//file.createNewFile();
						writer.append(System.getProperty("FaultName"));
					    writer.append(',');
					    writer.write(matrix1.toCSV());
				    	writer.append('\n'); 
				    	
				    	//writer.write(Double.toString(sum));
					    writer.close();				     
					 } catch (IOException ex) {
					   // report
					 }
			                 break;
					 case "Addition":
						 //developer
						 testResult =  FrameMethod.Addition(a,matrix1, "select" );
						 //branch
						 //testResult =  FrameMethod.Addition(denseMatrix0,matrix1, "select" );
						 //wm
						 //testResult =  FrameMethod.Addition(matrix0,matrix1, "select" );
						 
						 //testResult =  FrameMethod.Addition(0,0,matrix1, "select" );
	         		 break;         
					 case "Multiplication":
						//developer
						 testResult =  FrameMethod.Multiplication(a,matrix1, "select" );
						//branch
						 //testResult =  FrameMethod.Multiplication(denseMatrix0,matrix1, "select" );
						//wm
						 //testResult =  FrameMethod.Multiplication(matrix0,matrix1, "select" );
						 //testResult = FrameMethod.Multiplication(0,0,matrix1, "select" );
		     		 		break;
					 case "MultiplyWithIdentity":
						//developer
						 testResult =  FrameMethod.MultiplyWithIdentity(a,matrix1, "select" );
						//branch
						 //testResult =  FrameMethod.MultiplyWithIdentity(denseMatrix0,matrix1, "select" );
						//wm
						 //testResult =  FrameMethod.MultiplyWithIdentity(matrix0,matrix1, "select" );
						 //testResult =  FrameMethod.MultiplyWithIdentity(0,0,matrix1,null, "select" );
				 			break;		 	
					 default: MRname = "Invalid MR";
				            break;
					 }
						}catch (Exception ex) {
							try {
						FileWriter writer = new FileWriter("C:\\MRtest\\select_exception.txt",true);
						File file = new File("C:\\MRtest\\select_exception.txt");
						file.createNewFile();
						writer.append(System.getProperty("FaultName"));
					    writer.append(',');
					    writer.append(System.getProperty("MRname"));
					    writer.append(',');
					    writer.append(System.getProperty("TestNo")); 
					    writer.append('\n');  
					    writer.close();				     
					 } catch (IOException ex1) {
					   // report
					 } 
					   // report
					 System.exit(0);
					 }
					 return	 testResult;		
	}

	 
		public boolean  determinant(){
				boolean testResult = true;
				String MRname = System.getProperty("MRname");
				OoPlaceMatrixByItsTransposeMultiplication ooPlaceMatrixByItsTransposeMultiplication0 = new OoPlaceMatrixByItsTransposeMultiplication();
				//developer1
				/*Matrix a = m(a(1.0, 0.0, 0.0),
	                     a(0.0, 5.0, 0.0),
	                     a(0.0, 0.0, 9.0));*/
				//developer2
				/*Matrix a = m(a(1.0, 2.0, 3.0, 4.0),
	                     a(1.0, 1.0, 1.0, 1.0),
	                     a(2.0, 3.0, 4.0, 5.0),
	                     a(2.0, 2.0, 2.0, 2.0));*/
				//developer3
			/*	Matrix a = m(a(1.0, 6.0, -8.0, 5.0, -3.0, 41.0, -2.0),
	                     a(-8.0, -5.0, 7.0, 23.0, -7.0, 12.0, -2.0),
	                     a(8.0, 77.0, -65.0, 13.0, -8.0, 55.0, -47.0),
	                     a(26.0, 27.0, -81.0, -1.0, 10.0, -48.0, -3.0),
	                     a(0.0, 34.0, -79.0, 4.0, -1.0, 28.0, 6.0),
	                     a(-5.0, 8.0, -20.0, 36.0, -12.0, -7.0, -10.0),
	                     a(-6.0, 13.0, 9.0, -4.0, 95.0, 2.0, 46.0));
				double double0 = a.determinant();*/
				//branch
				//DenseMatrix denseMatrix0 = DenseMatrix.zero(4,4); //1, 10
			    DenseMatrix denseMatrix0 = DenseMatrix.zero(1, 10);
			    Matrix matrix0 = ooPlaceMatrixByItsTransposeMultiplication0.apply(denseMatrix0);
			    double double0 = matrix0.determinant();
				try{ 	 
					 switch (MRname) {
					
					 case "determinant":
					     //boolean result = matrix0.equals((Object)denseMatrix0);//false
					 try {
						 FileWriter writer = new FileWriter("C:\\MRtest\\"+MRname+".txt",true);
						File file = new File("C:\\MRtest\\"+MRname+".txt");
						if ( file.exists() ){
							// file.delete();
					    }
						//file.createNewFile();
						writer.append(System.getProperty("FaultName"));
					    writer.append(',');
				    	writer.write(Double.toString(double0));
				    	//writer.append(',');
				    	//writer.write(Boolean.toString(result));
				    	writer.append('\n');
				    	//writer.write(Double.toString(sum));
					    writer.close();				     
					 } catch (IOException ex) {
					   // report
					 }
			                 break;
					 case "transpose":
						//developer
						 //testResult= FrameMethod.transpose(double0,0,a,null, "determinant" );
			         		 
						 //branch
						 testResult= FrameMethod.transpose(double0,0,matrix0,null, "determinant" );
			         		 break;        
			        default: MRname = "Invalid MR";
			                 break;
					 }
				}catch (Exception ex) {
					try {
						FileWriter writer = new FileWriter("C:\\MRtest\\determinant_exception.txt",true);
						File file = new File("C:\\MRtest\\determinant_exception.txt");
						file.createNewFile();
						writer.append(System.getProperty("FaultName"));
					    writer.append(',');
					    writer.append(System.getProperty("MRname"));
					    writer.append(',');
					    writer.append(System.getProperty("TestNo")); 
					    writer.append('\n');  
					    writer.close();				     
					 } catch (IOException ex1) {
					   // report
					 } 
					   // report
					 System.exit(0);
					 //testResult = false;
					 }
					 return testResult;	
			
	}	 

	//target method slice
		public boolean  sliceTopLeft(){
				boolean testResult = true;
				String MRname = System.getProperty("MRname");
				//developer1
				/*Matrix a = m(a(1.0, 0.0, 0.0, 0.0),
	                     a(0.0, 5.0, 0.0, 0.0),
	                     a(0.0, 0.0, 9.0, 0.0),
	                     a(0.0, 0.0, 0.0, 15.0));
				Matrix matrix0 = a.sliceTopLeft(1, 3);*/
				//developer2
				Matrix a = m(a(1.0, 0.0, 3.0, 0.0),
	                     a(0.0, 5.0, 0.0, 7.0),
	                     a(4.0, 0.0, 9.0, 0.0));
				Matrix matrix0 = a.sliceTopLeft(2, 4);
				//test case wm
				//Matrix matrix0 = Matrix.constant(1, 13, 0);
				//matrix0 = matrix0.sliceTopLeft(1, 13);
				try{ 	 	 
					 switch (MRname) {
					
					 case "sliceTopLeft":	 
					 try {
						 FileWriter writer = new FileWriter("C:\\MRtest\\"+MRname+".txt",true);
						File file = new File("C:\\MRtest\\"+MRname+".txt");
						if ( file.exists() ){
							// file.delete();
					    }
						//file.createNewFile();
						writer.append(System.getProperty("FaultName"));
					    writer.append(',');
				    	writer.write(matrix0.toCSV());
				    	writer.append('\n'); 
				    	
				    	//writer.write(Double.toString(sum));
					    writer.close();				     
					 } catch (IOException ex) {
					   // report
					 }
			                 break;
							
					 case "Addition":
						 //developer
						 testResult=  FrameMethod.Addition(0,0,a, "sliceTopLeft" );
						 //wm
						 //testResult=  FrameMethod.Addition(0,0,matrix0, "sliceTopLeft" );
			         		 break;         
					 case "Multiplication":
						//developer
						 testResult=  FrameMethod.Multiplication(0,0,a, "sliceTopLeft" );
						 //wm
						 //testResult= FrameMethod.Multiplication(0,0, matrix0, "sliceTopLeft");
		     		 		break;
					 default: MRname = "Invalid MR";
			                break;
					 }
		}catch (Exception ex) {
			try {
		FileWriter writer = new FileWriter("C:\\MRtest\\sliceTopLeft_exception.txt",true);
		File file = new File("C:\\MRtest\\sliceTopLeft_exception.txt");
		file.createNewFile();
		writer.append(System.getProperty("FaultName"));
	    writer.append(',');
	    writer.append(System.getProperty("MRname"));
	    writer.append(',');
	    writer.append(System.getProperty("TestNo")); 
	    writer.append('\n');  
	    writer.close();				     
	 } catch (IOException ex1) {
	   // report
	 } 
	   // report
	 System.exit(0);
	 }
					 return testResult;		
	}	 

	
		public boolean  rank(){
				boolean testResult = true;
				String MRname = System.getProperty("MRname");
				Matrix matrix0 = Matrix.constant(3, 4, (-1.0));
			    int int0 = matrix0.rank();
			    //developer
			    //Matrix a = m(a(1.0, 0.0, 0.0),
	            //         a(0.0, 5.0, 0.0),
	            //         a(0.0, 0.0, 9.0));
			   /* Matrix a = m(a(15, 6, 84, 67, 4, 11, 81, 47, 57, 41),
	                     a(21, 94, 86, 55, 16, 31, 60, 62, 33, 61),
	                     a(3, 32, 57, 56, 55, 66, 70, 87, 6, 37),
	                     a(71, 48, 8, 24, 43, 61, 56, 24, 37, 63),
	                     a(79, 45, 36, 20, 13, 96, 31, 77, 67, 54),
	                     a(20, 63, 41, 79, 46, 100, 90, 23, 42, 94),
	                     a(15, 10, 36, 18, 25, 81, 76, 29, 23, 53),
	                     a(43, 79, 60, 94, 26, 24, 50, 38, 53, 12),
	                     a(100, 59, 26, 99, 72, 17, 29, 3, 76, 14),
	                     a(18, 38, 141, 123, 59, 77, 151, 134, 63, 78));*/
			    //Matrix a = mz(3, 3);
			    /*Matrix a = m(a(0.0, 13.0, 25.0, 43.0, 81.0, 0.0, 39.0, 60.0, 70.0, 21.0, 44.0, 0.0),
	                     a(44.0, 0.0, 13.0, 67.0, 35.0, 0.0, 84.0, 35.0, 23.0, 88.0, 11.0, 0.0),
	                     a(5.0, 34.0, 0.0, 143.0, 35.0, 0.0, 65.0, 99.0, 22.0, 13.0, 26.0, 0.0),
	                     a(89.0, 23.0, 13.0, 0.0, 78.0, 0.0, 13.0, 24.0, 98.0, 65.0, 0.0, 0.0),
	                     a(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0),
	                     a(56.0, 4.0, 24.0, 56.0, 78.0, 0.0, 13.0, 0.0, 24.0, 57.0, 8.0, 1.0),
	                     a(0.0, 0.0, 46.0, 666.0, 34.0, 13.0, 67.0, 9.0, 12.0, 45.0, 38.0, 0.0));*/
			    Matrix a = m(a(1, 2, 0),
	                     a(0, 0, 0),
	                     a(0, 0, 0),
	                     a(0, 0, 0),
	                     a(0, 0, 1),
	                     a(0, 0, -1),
	                     a(1, 2, 1));

	        
			    int0 = a.rank();
				try{ 	 	 
					 switch (MRname) {
					
					 case "rank":	 
					 try {
						 FileWriter writer = new FileWriter("C:\\MRtest\\"+MRname+".txt",true);
						File file = new File("C:\\MRtest\\"+MRname+".txt");
						if ( file.exists() ){
							// file.delete();
					    }
						//file.createNewFile();
						writer.append(System.getProperty("FaultName"));
					    writer.append(',');
				    	writer.write(Integer.toString(int0));
				    	writer.append('\n'); 
				    	
					    writer.close();				     
					 } catch (IOException ex) {
					   // report
					 }
			                 break;
					  
					 case "transpose":
						 //developer
						 testResult=  FrameMethod.transpose(int0, a, "rank");	      		 			
						 //wm
						 //testResult=  FrameMethod.transpose(int0, matrix0, "rank");
      		 			 break;	
			         default: MRname = "Invalid MR";
			            break;
					 }
				}catch (Exception ex) {
					try {
						FileWriter writer = new FileWriter("C:\\MRtest\\rank_exception.txt",true);
						File file = new File("C:\\MRtest\\rank_exception.txt");
						file.createNewFile();
						writer.append(System.getProperty("FaultName"));
					    writer.append(',');
					    writer.append(System.getProperty("MRname"));
					    writer.append(',');
					    writer.append(System.getProperty("TestNo")); 
					    writer.append('\n');  
					    writer.close();				     
					 } catch (IOException ex1) {
					   // report
					 } 
					   // report
					 System.exit(0);
					 //testResult = false;
					 }
			return testResult;
	} 	 	 

	 
		public boolean  setRow(){
				boolean testResult = true;
				String MRname = System.getProperty("MRname");
				//developer
				/*Matrix a = m(a(8.0, 3.0, 1.0, 9.0),
	                     a(4.0, 9.0, 6.0, 6.0),
	                     a(9.0, 1.0, 1.0, 4.0),
	                     a(5.0, 7.0, 3.0, 0.0));
				a.setRow(1, 10.0);*/
				//wm
				Matrix matrix0 = Matrix.unit(2, 2);
			    matrix0.setRow(1, (double) 1);
				try{ 	 	 
					 switch (MRname) {
					 case "setRow": 
					 try {
						 FileWriter writer = new FileWriter("C:\\MRtest\\"+MRname+".txt",true);
						File file = new File("C:\\MRtest\\"+MRname+".txt");
						if ( file.exists() ){
							// file.delete();
					    }
						//file.createNewFile();
						writer.append(System.getProperty("FaultName"));
					    writer.append(',');
					    //writer.write(a.toCSV());
				    	writer.write(matrix0.toCSV());
				    	writer.append('\n'); 
				    	//writer.write(Double.toString(sum));
					    writer.close();				     
					 } catch (IOException ex) {
					   // report
					 }
			                 break;
					 case "Addition":
						//developer
						 //testResult=  FrameMethod.Addition(0,0,a, "setRow" );
						 //wm
						 testResult=  FrameMethod.Addition(0,0,matrix0, "setRow" );
	         		 		break;         
					 case "Multiplication":
						//developer
						 //testResult=  FrameMethod.Multiplication(0,0,a, "setRow" );
						 //wm
						 testResult=  FrameMethod.Multiplication(0,0,matrix0, "setRow" );
      		 			break;
			         default: MRname = "Invalid MR";
			            break;
					 }
				}catch (Exception ex) {
					try {
						FileWriter writer = new FileWriter("C:\\MRtest\\setRow_exception.txt",true);
						File file = new File("C:\\MRtest\\setRow_exception.txt");
						file.createNewFile();
						writer.append(System.getProperty("FaultName"));
					    writer.append(',');
					    writer.append(System.getProperty("MRname"));
					    writer.append(',');
					    writer.append(System.getProperty("TestNo")); 
					    writer.append('\n');  
					    writer.close();				     
					 } catch (IOException ex1) {
					   // report
					 } 
					   // report
					 System.exit(0);
					 //testResult = false;
					 }	 
					
					 return testResult;
	}	 
	 
	}
		  	 		 
	 


