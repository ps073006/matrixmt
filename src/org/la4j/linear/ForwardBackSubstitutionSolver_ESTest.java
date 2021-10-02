/*
 * This file was automatically generated by EvoSuite
 * Tue Aug 27 22:05:50 GMT 2019
 */

package org.la4j.linear;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.shaded.org.mockito.Mockito.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.util.Enumeration;
import java.util.LinkedList;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.Random;
import org.evosuite.runtime.ViolatedAssumptionAnswer;
import org.evosuite.runtime.mock.java.util.MockRandom;
import org.evosuite.runtime.testdata.EvoSuiteFile;
import org.evosuite.runtime.testdata.FileSystemHandling;
import org.junit.runner.RunWith;
import org.la4j.Matrix;
import org.la4j.Vector;
import org.la4j.linear.ForwardBackSubstitutionSolver;
import org.la4j.matrix.ColumnMajorSparseMatrix;
import org.la4j.matrix.DenseMatrix;
import org.la4j.matrix.RowMajorSparseMatrix;
import org.la4j.operation.inplace.InPlaceCopyMatrixToMatrix;
import org.la4j.operation.ooplace.OoPlaceMatricesAddition;
import org.la4j.operation.ooplace.OoPlaceMatrixByVectorMultiplication;
import org.la4j.operation.ooplace.OoPlaceVectorByMatrixMultiplication;
import org.la4j.operation.ooplace.OoPlaceVectorsAddition;
import org.la4j.vector.DenseVector;
import org.la4j.vector.SparseVector;
import org.la4j.vector.functor.VectorFunction;
import org.la4j.vector.sparse.CompressedVector;

//@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class ForwardBackSubstitutionSolver_ESTest extends ForwardBackSubstitutionSolver_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      FileSystemHandling.appendLineToFile((EvoSuiteFile) null, "");
      FileSystemHandling.createFolder((EvoSuiteFile) null);
      ColumnMajorSparseMatrix columnMajorSparseMatrix0 = ColumnMajorSparseMatrix.identity(11);
      ForwardBackSubstitutionSolver forwardBackSubstitutionSolver0 = new ForwardBackSubstitutionSolver(columnMajorSparseMatrix0);
      forwardBackSubstitutionSolver0.unknowns = 1;
      CompressedVector compressedVector0 = (CompressedVector)columnMajorSparseMatrix0.getRow(11);
      CompressedVector compressedVector1 = (CompressedVector)forwardBackSubstitutionSolver0.solve(compressedVector0);
      assertEquals(11, columnMajorSparseMatrix0.columns());
      assertEquals(0.0, columnMajorSparseMatrix0.min(), 0.01);
      assertEquals(11, columnMajorSparseMatrix0.cardinality());
      assertEquals(1.0, columnMajorSparseMatrix0.max(), 0.01);
      assertFalse(columnMajorSparseMatrix0.isRowMajor());
      assertEquals(11, columnMajorSparseMatrix0.rows());
      assertEquals(0.09090909090909091, columnMajorSparseMatrix0.density(), 0.01);
      assertEquals(1, forwardBackSubstitutionSolver0.unknowns());
      assertEquals(11, forwardBackSubstitutionSolver0.equations());
      assertEquals(11, compressedVector0.length());
      assertEquals(0, compressedVector0.cardinality());
      assertEquals(0.0, compressedVector0.density(), 0.01);
      assertEquals(0, compressedVector1.cardinality());
      assertEquals(0.0, compressedVector1.density(), 0.01);
      assertEquals(1, compressedVector1.length());
      assertNotNull(compressedVector1);
      assertNotSame(compressedVector0, compressedVector1);
      assertNotSame(compressedVector1, compressedVector0);
      assertFalse(compressedVector1.equals((Object)compressedVector0));
  }

  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
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
      assertEquals(22, denseMatrix0.columns());
      assertEquals(22, denseMatrix0.rows());
      assertEquals(22, matrix0.columns());
      assertEquals(22, matrix0.rows());
      assertEquals(22, forwardBackSubstitutionSolver0.equations());
      assertEquals(22, forwardBackSubstitutionSolver0.unknowns());
      assertEquals(22, denseVector0.length());
      assertEquals(22, vector0.length());
      assertEquals(22, vector2.length());
      assertNotNull(vector2);
      assertSame(denseMatrix0, matrix0);
      assertSame(matrix0, denseMatrix0);
      assertNotSame(denseVector0, vector1);
      assertNotSame(denseVector0, vector0);
      assertNotSame(denseVector0, vector2);
      assertNotSame(vector0, vector2);
      assertNotSame(vector0, denseVector0);
      assertNotSame(vector0, vector1);
      assertNotSame(vector2, vector1);
      assertNotSame(vector2, vector0);
      assertNotSame(vector2, denseVector0);
      assertFalse(denseVector0.equals((Object)vector1));
      assertFalse(denseVector0.equals((Object)vector0));
      assertFalse(vector0.equals((Object)denseVector0));
      assertFalse(vector0.equals((Object)vector1));
      assertFalse(vector2.equals((Object)vector1));
      assertFalse(vector2.equals((Object)vector0));
      assertFalse(vector2.equals((Object)denseVector0));
      
      Random.setNextRandom(22);
      int int0 = 785;
      Random.setNextRandom(785);
      Random.setNextRandom(1022);
      OoPlaceVectorByMatrixMultiplication ooPlaceVectorByMatrixMultiplication0 = new OoPlaceVectorByMatrixMultiplication();
      Enumeration<InputStream> enumeration0 = (Enumeration<InputStream>) mock(Enumeration.class, new ViolatedAssumptionAnswer());
      doReturn(false).when(enumeration0).hasMoreElements();
      SequenceInputStream sequenceInputStream0 = new SequenceInputStream(enumeration0);
      // Undeclared exception!
      try { 
        DenseVector.fromMatrixMarket(sequenceInputStream0);
        fail("Expecting exception: IllegalArgumentException");
      
      } catch(IllegalArgumentException e) {
         //
         // Wrong input file format: can not read header '%%MatrixMarket'.
         //
         verifyException("org.la4j.Vector", e);
      }
  }

  @Test(timeout = 4000)
  public void test02()  throws Throwable  {
      Vector vector0 = Vector.unit(28);
      Vector vector1 = Vector.constant(28, 28);
      Random.setNextRandom(28);
      Matrix matrix0 = Matrix.zero(28, 28);
      ForwardBackSubstitutionSolver forwardBackSubstitutionSolver0 = new ForwardBackSubstitutionSolver(matrix0);
      forwardBackSubstitutionSolver0.unknowns = 0;
      Vector vector2 = forwardBackSubstitutionSolver0.solve(vector1);
      assertEquals(28, vector1.length());
      assertEquals(28, matrix0.columns());
      assertEquals(28, matrix0.rows());
      assertEquals(0, forwardBackSubstitutionSolver0.unknowns());
      assertEquals(28, forwardBackSubstitutionSolver0.equations());
      assertEquals(0, vector2.length());
      assertNotNull(vector2);
      assertNotSame(vector1, vector0);
      assertNotSame(vector1, vector2);
      assertNotSame(vector2, vector0);
      assertNotSame(vector2, vector1);
      assertFalse(vector1.equals((Object)vector0));
      assertFalse(vector2.equals((Object)vector0));
      assertFalse(vector2.equals((Object)vector1));
      
      // Undeclared exception!
      try { 
        forwardBackSubstitutionSolver0.solve(vector2);
        fail("Expecting exception: IllegalArgumentException");
      
      } catch(IllegalArgumentException e) {
         //
         // Wrong length of RHS vector: 0.
         //
         verifyException("org.la4j.linear.AbstractSolver", e);
      }
  }

  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      Random.setNextRandom(1);
      RowMajorSparseMatrix rowMajorSparseMatrix0 = RowMajorSparseMatrix.diagonal(1, (-1.0));
      DenseVector denseVector0 = DenseVector.unit(1);
      ForwardBackSubstitutionSolver forwardBackSubstitutionSolver0 = new ForwardBackSubstitutionSolver(rowMajorSparseMatrix0);
      Vector vector0 = forwardBackSubstitutionSolver0.solve(denseVector0);
      assertEquals(1.0, rowMajorSparseMatrix0.density(), 0.01);
      assertEquals(1, rowMajorSparseMatrix0.cardinality());
      assertEquals(1, rowMajorSparseMatrix0.columns());
      assertEquals((-1.0), rowMajorSparseMatrix0.min(), 0.01);
      assertEquals(1, rowMajorSparseMatrix0.rows());
      assertEquals(0.0, rowMajorSparseMatrix0.max(), 0.01);
      assertTrue(rowMajorSparseMatrix0.isRowMajor());
      assertEquals(1, denseVector0.length());
      assertEquals(1, forwardBackSubstitutionSolver0.equations());
      assertEquals(1, forwardBackSubstitutionSolver0.unknowns());
      assertEquals(1, vector0.length());
      assertNotNull(vector0);
      assertNotSame(denseVector0, vector0);
      assertNotSame(vector0, denseVector0);
      assertFalse(vector0.equals((Object)denseVector0));
      
      Vector vector1 = forwardBackSubstitutionSolver0.solve(vector0);
      assertEquals(1.0, rowMajorSparseMatrix0.density(), 0.01);
      assertEquals(1, rowMajorSparseMatrix0.cardinality());
      assertEquals(1, rowMajorSparseMatrix0.columns());
      assertEquals((-1.0), rowMajorSparseMatrix0.min(), 0.01);
      assertEquals(1, rowMajorSparseMatrix0.rows());
      assertEquals(0.0, rowMajorSparseMatrix0.max(), 0.01);
      assertTrue(rowMajorSparseMatrix0.isRowMajor());
      assertEquals(1, denseVector0.length());
      assertEquals(1, forwardBackSubstitutionSolver0.equations());
      assertEquals(1, forwardBackSubstitutionSolver0.unknowns());
      assertEquals(1, vector0.length());
      assertEquals(1, vector1.length());
      assertNotNull(vector1);
      assertNotSame(denseVector0, vector0);
      assertNotSame(denseVector0, vector1);
      assertNotSame(vector0, denseVector0);
      assertNotSame(vector0, vector1);
      assertNotSame(vector1, denseVector0);
      assertNotSame(vector1, vector0);
      assertFalse(denseVector0.equals((Object)vector0));
      assertFalse(vector0.equals((Object)denseVector0));
      assertFalse(vector1.equals((Object)vector0));
      assertTrue(vector1.equals((Object)denseVector0));
  }

  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
      int int0 = 3;
      FileSystemHandling.appendStringToFile((EvoSuiteFile) null, "");
      RowMajorSparseMatrix rowMajorSparseMatrix0 = RowMajorSparseMatrix.diagonal(3, 0.0);
      ForwardBackSubstitutionSolver forwardBackSubstitutionSolver0 = new ForwardBackSubstitutionSolver(rowMajorSparseMatrix0);
      Vector vector0 = Vector.unit(3);
      // Undeclared exception!
      try { 
        forwardBackSubstitutionSolver0.solve(vector0);
        fail("Expecting exception: IllegalArgumentException");
      
      } catch(IllegalArgumentException e) {
         //
         // This system can not be solved: coefficient matrix is singular.
         //
         verifyException("org.la4j.linear.AbstractSolver", e);
      }
  }

  @Test(timeout = 4000)
  public void test05()  throws Throwable  {
      ColumnMajorSparseMatrix columnMajorSparseMatrix0 = ColumnMajorSparseMatrix.identity(11);
      ForwardBackSubstitutionSolver forwardBackSubstitutionSolver0 = new ForwardBackSubstitutionSolver(columnMajorSparseMatrix0);
      FileSystemHandling.appendStringToFile((EvoSuiteFile) null, ";_O|E");
      Random.setNextRandom(11);
      OoPlaceMatrixByVectorMultiplication ooPlaceMatrixByVectorMultiplication0 = new OoPlaceMatrixByVectorMultiplication();
      RowMajorSparseMatrix rowMajorSparseMatrix0 = RowMajorSparseMatrix.diagonal(11, (-1.0));
      DenseVector denseVector0 = DenseVector.unit(11);
      Vector vector0 = ooPlaceMatrixByVectorMultiplication0.apply(rowMajorSparseMatrix0, denseVector0);
      Vector vector1 = forwardBackSubstitutionSolver0.solve(vector0);
      assertEquals(11, columnMajorSparseMatrix0.cardinality());
      assertFalse(columnMajorSparseMatrix0.isRowMajor());
      assertEquals(0.0, columnMajorSparseMatrix0.min(), 0.01);
      assertEquals(11, columnMajorSparseMatrix0.columns());
      assertEquals(11, columnMajorSparseMatrix0.rows());
      assertEquals(0.09090909090909091, columnMajorSparseMatrix0.density(), 0.01);
      assertEquals(1.0, columnMajorSparseMatrix0.max(), 0.01);
      assertEquals(11, forwardBackSubstitutionSolver0.equations());
      assertEquals(11, forwardBackSubstitutionSolver0.unknowns());
      assertTrue(rowMajorSparseMatrix0.isRowMajor());
      assertEquals((-1.0), rowMajorSparseMatrix0.min(), 0.01);
      assertEquals(11, rowMajorSparseMatrix0.columns());
      assertEquals(0.0, rowMajorSparseMatrix0.max(), 0.01);
      assertEquals(0.09090909090909091, rowMajorSparseMatrix0.density(), 0.01);
      assertEquals(11, rowMajorSparseMatrix0.rows());
      assertEquals(11, rowMajorSparseMatrix0.cardinality());
      assertEquals(11, denseVector0.length());
      assertEquals(11, vector0.length());
      assertEquals(11, vector1.length());
      assertNotNull(vector1);
      assertNotSame(denseVector0, vector1);
      assertNotSame(denseVector0, vector0);
      assertNotSame(vector0, denseVector0);
      assertNotSame(vector0, vector1);
      assertNotSame(vector1, denseVector0);
      assertNotSame(vector1, vector0);
      assertFalse(denseVector0.equals((Object)vector0));
      assertFalse(vector0.equals((Object)denseVector0));
      assertFalse(vector1.equals((Object)denseVector0));
      assertTrue(vector1.equals((Object)vector0));
  }

  @Test(timeout = 4000)
  public void test06()  throws Throwable  {
      boolean boolean0 = FileSystemHandling.setPermissions((EvoSuiteFile) null, true, true, true);
      DenseVector denseVector0 = DenseVector.constant(0, (-1803.4025584));
      Vector vector0 = denseVector0.blank();
      Matrix matrix0 = denseVector0.toDiagonalMatrix();
      matrix0.product();
      Vector vector1 = matrix0.getColumn(0);
      denseVector0.infinityNorm();
      VectorFunction vectorFunction0 = mock(VectorFunction.class, new ViolatedAssumptionAnswer());
      Matrix matrix1 = matrix0.transformColumn((-1), vectorFunction0);
      ForwardBackSubstitutionSolver forwardBackSubstitutionSolver0 = new ForwardBackSubstitutionSolver(matrix0);
      Vector vector2 = forwardBackSubstitutionSolver0.solve(denseVector0);
      assertEquals(0, denseVector0.length());
      assertEquals(0, matrix0.columns());
      assertEquals(0, matrix0.rows());
      assertEquals(0, forwardBackSubstitutionSolver0.unknowns());
      assertEquals(0, forwardBackSubstitutionSolver0.equations());
      assertEquals(0, vector2.length());
      assertNotNull(vector2);
      assertNotSame(denseVector0, vector0);
      assertNotSame(denseVector0, vector1);
      assertNotSame(denseVector0, vector2);
      assertNotSame(matrix0, matrix1);
      assertNotSame(vector2, vector1);
      assertNotSame(vector2, denseVector0);
      assertNotSame(vector2, vector0);
      assertTrue(denseVector0.equals((Object)vector1));
      assertTrue(denseVector0.equals((Object)vector0));
      assertTrue(matrix0.equals((Object)matrix1));
      assertTrue(vector2.equals((Object)denseVector0));
      assertTrue(vector2.equals((Object)vector0));
      assertTrue(vector2.equals((Object)vector1));
      
      forwardBackSubstitutionSolver0.unknowns = (-1182);
      boolean boolean1 = forwardBackSubstitutionSolver0.applicableTo(matrix0);
      assertTrue(boolean1);
      assertEquals(0, denseVector0.length());
      assertEquals(0, matrix0.columns());
      assertEquals(0, matrix0.rows());
      assertEquals((-1182), forwardBackSubstitutionSolver0.unknowns());
      assertEquals(0, forwardBackSubstitutionSolver0.equations());
      assertNotSame(denseVector0, vector0);
      assertNotSame(denseVector0, vector1);
      assertNotSame(denseVector0, vector2);
      assertNotSame(matrix0, matrix1);
      assertTrue(denseVector0.equals((Object)vector2));
      assertTrue(denseVector0.equals((Object)vector1));
      assertTrue(denseVector0.equals((Object)vector0));
      assertTrue(matrix0.equals((Object)matrix1));
      assertFalse(boolean1 == boolean0);
      
      forwardBackSubstitutionSolver0.a = matrix0;
      // Undeclared exception!
      try { 
        forwardBackSubstitutionSolver0.solve(vector2);
        fail("Expecting exception: NegativeArraySizeException");
      
      } catch(NegativeArraySizeException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("org.la4j.vector.dense.BasicVector", e);
      }
  }

  @Test(timeout = 4000)
  public void test07()  throws Throwable  {
      ColumnMajorSparseMatrix columnMajorSparseMatrix0 = ColumnMajorSparseMatrix.identity(1);
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
      
      Random.setNextRandom(1);
      OoPlaceMatrixByVectorMultiplication ooPlaceMatrixByVectorMultiplication0 = new OoPlaceMatrixByVectorMultiplication();
      RowMajorSparseMatrix rowMajorSparseMatrix0 = RowMajorSparseMatrix.diagonal(1, (-1.0));
      DenseVector denseVector0 = DenseVector.unit(1);
      Vector vector0 = ooPlaceMatrixByVectorMultiplication0.apply(rowMajorSparseMatrix0, denseVector0);
      Vector vector1 = forwardBackSubstitutionSolver0.solve(vector0);
      assertEquals(0.0, columnMajorSparseMatrix0.min(), 0.01);
      assertEquals(1, columnMajorSparseMatrix0.rows());
      assertEquals(1, columnMajorSparseMatrix0.cardinality());
      assertEquals(1.0, columnMajorSparseMatrix0.max(), 0.01);
      assertEquals(1, columnMajorSparseMatrix0.columns());
      assertFalse(columnMajorSparseMatrix0.isRowMajor());
      assertEquals(1.0, columnMajorSparseMatrix0.density(), 0.01);
      assertEquals(1, forwardBackSubstitutionSolver0.equations());
      assertEquals(1, forwardBackSubstitutionSolver0.unknowns());
      assertEquals(1, rowMajorSparseMatrix0.rows());
      assertTrue(rowMajorSparseMatrix0.isRowMajor());
      assertEquals(1, rowMajorSparseMatrix0.cardinality());
      assertEquals(0.0, rowMajorSparseMatrix0.max(), 0.01);
      assertEquals(1, rowMajorSparseMatrix0.columns());
      assertEquals(1.0, rowMajorSparseMatrix0.density(), 0.01);
      assertEquals((-1.0), rowMajorSparseMatrix0.min(), 0.01);
      assertEquals(1, denseVector0.length());
      assertEquals(1, vector0.length());
      assertEquals(1, vector1.length());
      assertNotNull(vector1);
      assertNotSame(denseVector0, vector1);
      assertNotSame(denseVector0, vector0);
      assertNotSame(vector0, denseVector0);
      assertNotSame(vector0, vector1);
      assertNotSame(vector1, vector0);
      assertNotSame(vector1, denseVector0);
      assertFalse(denseVector0.equals((Object)vector0));
      assertFalse(vector0.equals((Object)denseVector0));
      assertFalse(vector1.equals((Object)denseVector0));
      assertTrue(vector1.equals((Object)vector0));
  }

  @Test(timeout = 4000)
  public void test08()  throws Throwable  {
      int int0 = 1;
      Random.setNextRandom(1);
      LinkedList<Integer> linkedList0 = new LinkedList<Integer>();
      SparseVector sparseVector0 = SparseVector.fromCollection(linkedList0);
      Matrix matrix0 = sparseVector0.toDiagonalMatrix();
      ForwardBackSubstitutionSolver forwardBackSubstitutionSolver0 = new ForwardBackSubstitutionSolver(matrix0);
      Integer integer0 = new Integer(1);
      linkedList0.add(integer0);
      forwardBackSubstitutionSolver0.unknowns = 1;
      // Undeclared exception!
      try { 
        forwardBackSubstitutionSolver0.solve(sparseVector0);
        fail("Expecting exception: IndexOutOfBoundsException");
      
      } catch(IndexOutOfBoundsException e) {
         //
         // Row '0' is invalid.
         //
         verifyException("org.la4j.Matrix", e);
      }
  }

  @Test(timeout = 4000)
  public void test09()  throws Throwable  {
      Matrix matrix0 = Matrix.zero(2691, 1229);
      ForwardBackSubstitutionSolver forwardBackSubstitutionSolver0 = null;
      try {
        forwardBackSubstitutionSolver0 = new ForwardBackSubstitutionSolver(matrix0);
        fail("Expecting exception: IllegalArgumentException");
      
      } catch(IllegalArgumentException e) {
         //
         // Given coefficient matrix can not be used with this solver.
         //
         verifyException("org.la4j.linear.AbstractSolver", e);
      }
  }

  @Test(timeout = 4000)
  public void test10()  throws Throwable  {
      double[][] doubleArray0 = new double[0][6];
      Matrix matrix0 = Matrix.from2DArray(doubleArray0);
      Matrix.diagonal(2965, 2965);
      ForwardBackSubstitutionSolver forwardBackSubstitutionSolver0 = new ForwardBackSubstitutionSolver(matrix0);
      OoPlaceVectorByMatrixMultiplication ooPlaceVectorByMatrixMultiplication0 = new OoPlaceVectorByMatrixMultiplication();
      double[] doubleArray1 = new double[9];
      doubleArray1[0] = (double) 2965;
      doubleArray1[1] = (double) 2965;
      doubleArray1[2] = 2622.34;
      doubleArray1[3] = (double) 2965;
      doubleArray1[4] = (double) 2965;
      doubleArray1[5] = (double) 2965;
      doubleArray1[6] = (double) 2965;
      doubleArray1[7] = (double) 2965;
      doubleArray1[8] = (double) 2965;
      SparseVector sparseVector0 = SparseVector.fromArray(doubleArray1);
      DenseMatrix denseMatrix0 = DenseMatrix.from2DArray(doubleArray0);
      Vector vector0 = ooPlaceVectorByMatrixMultiplication0.apply(sparseVector0, denseMatrix0);
      Vector vector1 = forwardBackSubstitutionSolver0.solve(vector0);
      assertEquals(0, doubleArray0.length);
      assertEquals(9, doubleArray1.length);
      assertEquals(0, denseMatrix0.rows());
      assertEquals(0, denseMatrix0.columns());
      assertEquals(0, vector0.length());
      assertEquals(0, matrix0.rows());
      assertEquals(0, matrix0.columns());
      assertEquals(0, vector1.length());
      assertEquals(0, forwardBackSubstitutionSolver0.equations());
      assertEquals(0, forwardBackSubstitutionSolver0.unknowns());
      assertEquals(9, sparseVector0.cardinality());
      assertEquals(9, sparseVector0.length());
      assertEquals(1.0, sparseVector0.density(), 0.01);
      assertNotNull(vector1);
      assertNotSame(denseMatrix0, matrix0);
      assertNotSame(vector0, vector1);
      assertNotSame(matrix0, denseMatrix0);
      assertNotSame(vector1, vector0);
      assertArrayEquals(new double[] {2965.0, 2965.0, 2622.34, 2965.0, 2965.0, 2965.0, 2965.0, 2965.0, 2965.0}, doubleArray1, 0.01);
      assertTrue(denseMatrix0.equals((Object)matrix0));
      assertTrue(matrix0.equals((Object)denseMatrix0));
      assertTrue(vector1.equals((Object)vector0));
  }

  @Test(timeout = 4000)
  public void test11()  throws Throwable  {
      OoPlaceMatricesAddition ooPlaceMatricesAddition0 = new OoPlaceMatricesAddition();
      DenseMatrix denseMatrix0 = DenseMatrix.zero(1, 8);
      Matrix matrix0 = ooPlaceMatricesAddition0.apply(denseMatrix0, denseMatrix0);
      ForwardBackSubstitutionSolver forwardBackSubstitutionSolver0 = null;
      try {
        forwardBackSubstitutionSolver0 = new ForwardBackSubstitutionSolver(matrix0);
        fail("Expecting exception: IllegalArgumentException");
      
      } catch(IllegalArgumentException e) {
         //
         // Given coefficient matrix can not be used with this solver.
         //
         verifyException("org.la4j.linear.AbstractSolver", e);
      }
  }

  @Test(timeout = 4000)
  public void test12()  throws Throwable  {
      ColumnMajorSparseMatrix columnMajorSparseMatrix0 = ColumnMajorSparseMatrix.identity(11);
      ForwardBackSubstitutionSolver forwardBackSubstitutionSolver0 = new ForwardBackSubstitutionSolver(columnMajorSparseMatrix0);
      CompressedVector compressedVector0 = (CompressedVector)columnMajorSparseMatrix0.getRow(11);
      CompressedVector compressedVector1 = (CompressedVector)forwardBackSubstitutionSolver0.solve(compressedVector0);
      assertEquals(11, columnMajorSparseMatrix0.rows());
      assertEquals(1.0, columnMajorSparseMatrix0.max(), 0.01);
      assertEquals(0.09090909090909091, columnMajorSparseMatrix0.density(), 0.01);
      assertEquals(11, columnMajorSparseMatrix0.columns());
      assertEquals(0.0, columnMajorSparseMatrix0.min(), 0.01);
      assertEquals(11, columnMajorSparseMatrix0.cardinality());
      assertFalse(columnMajorSparseMatrix0.isRowMajor());
      assertEquals(11, forwardBackSubstitutionSolver0.unknowns());
      assertEquals(11, forwardBackSubstitutionSolver0.equations());
      assertEquals(0, compressedVector0.cardinality());
      assertEquals(0.0, compressedVector0.density(), 0.01);
      assertEquals(11, compressedVector0.length());
      assertEquals(0, compressedVector1.cardinality());
      assertEquals(0.0, compressedVector1.density(), 0.01);
      assertEquals(11, compressedVector1.length());
      assertNotNull(compressedVector1);
      assertNotSame(compressedVector0, compressedVector1);
      assertNotSame(compressedVector1, compressedVector0);
      assertTrue(compressedVector1.equals((Object)compressedVector0));
  }

  @Test(timeout = 4000)
  public void test13()  throws Throwable  {
      DenseMatrix denseMatrix0 = DenseMatrix.zero(1, 30);
      ForwardBackSubstitutionSolver forwardBackSubstitutionSolver0 = null;
      try {
        forwardBackSubstitutionSolver0 = new ForwardBackSubstitutionSolver(denseMatrix0);
        fail("Expecting exception: IllegalArgumentException");
      
      } catch(IllegalArgumentException e) {
         //
         // Given coefficient matrix can not be used with this solver.
         //
         verifyException("org.la4j.linear.AbstractSolver", e);
      }
  }

  @Test(timeout = 4000)
  public void test14()  throws Throwable  {
      Vector vector0 = Vector.unit(3);
      Matrix matrix0 = Matrix.zero(3, 3);
      ForwardBackSubstitutionSolver forwardBackSubstitutionSolver0 = new ForwardBackSubstitutionSolver(matrix0);
      // Undeclared exception!
      try { 
        forwardBackSubstitutionSolver0.solve(vector0);
        fail("Expecting exception: IllegalArgumentException");
      
      } catch(IllegalArgumentException e) {
         //
         // This system can not be solved: coefficient matrix is singular.
         //
         verifyException("org.la4j.linear.AbstractSolver", e);
      }
  }
}
