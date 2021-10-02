::copy "C:\pit\commons-math\mutants\SingularValueDecomposition\mutants\%1\SingularValueDecomposition.class" "C:\Users\p66n633\workspace\la4j\src\org\apache\commons\math4\linear\SingularValueDecomposition.class"
::copy "C:\major\mutants\SingularValueDecomposition\mutants\%1\org\apache\commons\math4\linear\SingularValueDecomposition.class" "C:\Users\p66n633\workspace\la4j\src\org\apache\commons\math4\linear\SingularValueDecomposition.class"
copy "C:\major\mutants\SingularValueDecomposition\mutants\%1\org\apache\commons\math4\linear\SingularValueDecomposition.java" "C:\Users\p66n633\workspace\la4j\src\org\apache\commons\math4\linear\SingularValueDecomposition.java"
del "C:\Users\p66n633\workspace\la4j\src\org\apache\commons\math4\linear\SingularValueDecomposition.class"
javac -cp C:\Users\p66n633\workspace\la4j\src\ SingularValueDecomposition.java

DEL /F /S /Q /A "C:\Users\p66n633\workspace\la4j\bin\*.*"

::java  -cp .;junit-4.10.jar;evosuite-1.0.6.jar org.junit.runner.JUnitCore org.apache.commons.math4.linear.SingularValueSolverTest>C:\Users\p66n633\workspace\la4j\src\output\SingularValueDecomposition\output_%1.log

::pause;
java -Dmethodname="SingularValueDecomposition" -DMRname="SingularValueDecomposition" -DFaultName=%1 -DTestNo=test1 -cp C:\Users\p66n633\workspace\la4j\src\;C:\Users\p66n633\workspace\la4j\src\junit-4.10.jar;C:\Users\p66n633\workspace\la4j\src\evosuite-1.0.6.jar -javaagent:C:\Users\p66n633\workspace\la4j\src\lib\jacocoagent.jar=append=false,dumponexit=true,classdumpdir=C:/Users/p66n633/workspace/la4j/bin/class_dump_dir,destfile=C:/Users/p66n633/workspace/la4j/jacoco.exec org.junit.runner.JUnitCore  TestAllMethods 

java -Dmethodname="SingularValueDecomposition" -DMRname="Multiplication" -DFaultName=%1 -DTestNo=test1 -cp C:\Users\p66n633\workspace\la4j\src\;C:\Users\p66n633\workspace\la4j\src\junit-4.10.jar;C:\Users\p66n633\workspace\la4j\src\evosuite-1.0.6.jar -javaagent:C:\Users\p66n633\workspace\la4j\src\lib\jacocoagent.jar=append=false,dumponexit=true,classdumpdir=C:/Users/p66n633/workspace/la4j/bin/class_dump_dir,destfile=C:/Users/p66n633/workspace/la4j/jacoco.exec org.junit.runner.JUnitCore  TestAllMethods 

java -Dmethodname="SingularValueDecomposition" -DMRname="permuteRow" -DFaultName=%1 -DTestNo=test1 -cp C:\Users\p66n633\workspace\la4j\src\;C:\Users\p66n633\workspace\la4j\src\junit-4.10.jar;C:\Users\p66n633\workspace\la4j\src\evosuite-1.0.6.jar -javaagent:C:\Users\p66n633\workspace\la4j\src\lib\jacocoagent.jar=append=false,dumponexit=true,classdumpdir=C:/Users/p66n633/workspace/la4j/bin/class_dump_dir,destfile=C:/Users/p66n633/workspace/la4j/jacoco.exec org.junit.runner.JUnitCore  TestAllMethods 

java -Dmethodname="SingularValueDecomposition" -DMRname="matrixAdd" -DFaultName=%1 -DTestNo=test1 -cp C:\Users\p66n633\workspace\la4j\src\;C:\Users\p66n633\workspace\la4j\src\junit-4.10.jar;C:\Users\p66n633\workspace\la4j\src\evosuite-1.0.6.jar -javaagent:C:\Users\p66n633\workspace\la4j\src\lib\jacocoagent.jar=append=false,dumponexit=true,classdumpdir=C:/Users/p66n633/workspace/la4j/bin/class_dump_dir,destfile=C:/Users/p66n633/workspace/la4j/jacoco.exec org.junit.runner.JUnitCore  TestAllMethods 

java -Dmethodname="SingularValueDecomposition" -DMRname="transpose" -DFaultName=%1 -DTestNo=test1 -cp C:\Users\p66n633\workspace\la4j\src\;C:\Users\p66n633\workspace\la4j\src\junit-4.10.jar;C:\Users\p66n633\workspace\la4j\src\evosuite-1.0.6.jar -javaagent:C:\Users\p66n633\workspace\la4j\src\lib\jacocoagent.jar=append=false,dumponexit=true,classdumpdir=C:/Users/p66n633/workspace/la4j/bin/class_dump_dir,destfile=C:/Users/p66n633/workspace/la4j/jacoco.exec org.junit.runner.JUnitCore  TestAllMethods 

::java -Dmethodname="SingularValueDecomposition" -DMRname="MultiplyWithIdentity" -DFaultName=%1 -DTestNo=test1 -cp .;junit-4.10.jar;evosuite-1.0.6.jar -javaagent:C:\Users\p66n633\workspace\la4j\src\lib\jacocoagent.jar=append=false,dumponexit=true,classdumpdir=C:/Users/p66n633/workspace/la4j/bin/class_dump_dir,destfile=C:/Users/p66n633/workspace/la4j/jacoco.exec org.junit.runner.JUnitCore  TestAllMethods 

java -Dmethodname="SingularValueDecomposition" -DMRname="negation" -DFaultName=%1 -DTestNo=test1 -cp C:\Users\p66n633\workspace\la4j\src\;C:\Users\p66n633\workspace\la4j\src\junit-4.10.jar;C:\Users\p66n633\workspace\la4j\src\evosuite-1.0.6.jar -javaagent:C:\Users\p66n633\workspace\la4j\src\lib\jacocoagent.jar=append=false,dumponexit=true,classdumpdir=C:/Users/p66n633/workspace/la4j/bin/class_dump_dir,destfile=C:/Users/p66n633/workspace/la4j/jacoco.exec org.junit.runner.JUnitCore  TestAllMethods 

::pause;