::copy "C:\pit\commons-math\mutants\QRDecomposition\mutants\%1\QRDecomposition.class" "C:\Users\p66n633\workspace\la4j\src\org\apache\commons\math4\linear\QRDecomposition.class"
copy "C:\major\mutants\QRDecomposition\mutants\%1\org\apache\commons\math4\linear\QRDecomposition.java" "C:\Users\p66n633\workspace\la4j\src\org\apache\commons\math4\linear\QRDecomposition.java"

del "C:\Users\p66n633\workspace\la4j\src\org\apache\commons\math4\linear\QRDecomposition.class"
javac -cp C:\Users\p66n633\workspace\la4j\src C:\Users\p66n633\workspace\apachecommons\src\org\apache\commons\math4\linear\QRDecomposition.java
::DEL /F /S /Q /A "C:\Users\p66n633\workspace\la4j\bin\*.*"

::java  -cp .;junit-4.10.jar;evosuite-1.0.6.jar org.junit.runner.JUnitCore org.apache.commons.math4.linear.QRSolverTest>C:\Users\p66n633\workspace\la4j\src\output\QRDecomposition\output_%1.log
::java  -cp .;junit-4.10.jar;evosuite-1.0.6.jar org.junit.runner.JUnitCore org.apache.commons.math4.linear.QRDecomposition_ESTest>C:\Users\p66n633\workspace\la4j\src\output\QRDecomposition\output_%1.log
::pause;

DEL /F /S /Q /A "C:\Users\p66n633\workspace\la4j\bin\*.*"

::pause;




java -Dmethodname="QRDecomposition" -DMRname="QRDecomposition" -DFaultName=%1 -DTestNo=test1 -cp .;junit-4.10.jar;evosuite-1.0.6.jar -javaagent:C:\Users\p66n633\workspace\la4j\src\lib\jacocoagent.jar=append=false,dumponexit=true,classdumpdir=C:/Users/p66n633/workspace/la4j/bin/class_dump_dir,destfile=C:/Users/p66n633/workspace/la4j/jacoco.exec org.junit.runner.JUnitCore  TestAllMethods 

java -Dmethodname="QRDecomposition" -DMRname="Multiplication" -DFaultName=%1 -DTestNo=test1 -cp .;junit-4.10.jar;evosuite-1.0.6.jar -javaagent:C:\Users\p66n633\workspace\la4j\src\lib\jacocoagent.jar=append=false,dumponexit=true,classdumpdir=C:/Users/p66n633/workspace/la4j/bin/class_dump_dir,destfile=C:/Users/p66n633/workspace/la4j/jacoco.exec org.junit.runner.JUnitCore  TestAllMethods 

java -Dmethodname="QRDecomposition" -DMRname="permuteRow" -DFaultName=%1 -DTestNo=test1 -cp .;junit-4.10.jar;evosuite-1.0.6.jar -javaagent:C:\Users\p66n633\workspace\la4j\src\lib\jacocoagent.jar=append=false,dumponexit=true,classdumpdir=C:/Users/p66n633/workspace/la4j/bin/class_dump_dir,destfile=C:/Users/p66n633/workspace/la4j/jacoco.exec org.junit.runner.JUnitCore  TestAllMethods 

java -Dmethodname="QRDecomposition" -DMRname="matrixAdd" -DFaultName=%1 -DTestNo=test1 -cp .;junit-4.10.jar;evosuite-1.0.6.jar -javaagent:C:\Users\p66n633\workspace\la4j\src\lib\jacocoagent.jar=append=false,dumponexit=true,classdumpdir=C:/Users/p66n633/workspace/la4j/bin/class_dump_dir,destfile=C:/Users/p66n633/workspace/la4j/jacoco.exec org.junit.runner.JUnitCore  TestAllMethods 

java -Dmethodname="QRDecomposition" -DMRname="transpose" -DFaultName=%1 -DTestNo=test1 -cp .;junit-4.10.jar;evosuite-1.0.6.jar -javaagent:C:\Users\p66n633\workspace\la4j\src\lib\jacocoagent.jar=append=false,dumponexit=true,classdumpdir=C:/Users/p66n633/workspace/la4j/bin/class_dump_dir,destfile=C:/Users/p66n633/workspace/la4j/jacoco.exec org.junit.runner.JUnitCore  TestAllMethods 

::java -Dmethodname="QRDecomposition" -DMRname="MultiplyWithIdentity" -DFaultName=%1 -DTestNo=test1 -cp .;junit-4.10.jar;evosuite-1.0.6.jar -javaagent:C:\Users\p66n633\workspace\la4j\src\lib\jacocoagent.jar=append=false,dumponexit=true,classdumpdir=C:/Users/p66n633/workspace/la4j/bin/class_dump_dir,destfile=C:/Users/p66n633/workspace/la4j/jacoco.exec org.junit.runner.JUnitCore  TestAllMethods 

java -Dmethodname="QRDecomposition" -DMRname="negation" -DFaultName=%1 -DTestNo=test1 -cp .;junit-4.10.jar;evosuite-1.0.6.jar -javaagent:C:\Users\p66n633\workspace\la4j\src\lib\jacocoagent.jar=append=false,dumponexit=true,classdumpdir=C:/Users/p66n633/workspace/la4j/bin/class_dump_dir,destfile=C:/Users/p66n633/workspace/la4j/jacoco.exec org.junit.runner.JUnitCore  TestAllMethods 

pause;