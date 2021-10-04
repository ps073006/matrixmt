copy ".\la4j\mutants\LeastSquaresSolverSolve\org\la4j\linear\LeastSquaresSolver\mutants\%1\LeastSquaresSolver.class" ".\org\la4j\linear\LeastSquaresSolver.class"

DEL /F /S /Q /A ".\class_dump_dir\*.*"

::java  -cp .;junit-4.10.jar;evosuite-1.0.6.jar org.junit.runner.JUnitCore org.la4j.linear.LeastSquaresSolver_ESTest>C:\Users\p66n633\workspace\la4j\src\output\leastsqaure\output_%1.log

::java  -cp .;junit-4.10.jar;evosuite-1.0.6.jar org.junit.runner.JUnitCore org.la4j.linear.LeastSquaresSolverTest>C:\Users\p66n633\workspace\la4j\src\output\leastsqaure\output_%1.log

::pause;
java -Dmethodname="LeastSquaresSolver" -DMRname="solve" -DFaultName=%1 -DTestNo=test1 -cp .;./lib/junit-4.10.jar;./lib/evosuite-1.0.6.jar -javaagent:.\lib\jacocoagent.jar=append=false,dumponexit=true,classdumpdir=./class_dump_dir,destfile=jacoco.exec org.junit.runner.JUnitCore  TestAllMethods 

java -Dmethodname="LeastSquaresSolver" -DMRname="Multiplication" -DFaultName=%1 -DTestNo=test1 -cp .;./lib/junit-4.10.jar;./lib/evosuite-1.0.6.jar -javaagent:.\lib\jacocoagent.jar=append=false,dumponexit=true,classdumpdir=./class_dump_dir,destfile=jacoco.exec org.junit.runner.JUnitCore  TestAllMethods 

java -Dmethodname="LeastSquaresSolver" -DMRname="permuteRow" -DFaultName=%1 -DTestNo=test1 -cp .;./lib/junit-4.10.jar;./lib/evosuite-1.0.6.jar -javaagent:.\lib\jacocoagent.jar=append=false,dumponexit=true,classdumpdir=./class_dump_dir,destfile=jacoco.exec org.junit.runner.JUnitCore  TestAllMethods 

java -Dmethodname="LeastSquaresSolver" -DMRname="matrixAdd" -DFaultName=%1 -DTestNo=test1 -cp .;./lib/junit-4.10.jar;./lib/evosuite-1.0.6.jar -javaagent:.\lib\jacocoagent.jar=append=false,dumponexit=true,classdumpdir=./class_dump_dir,destfile=jacoco.exec org.junit.runner.JUnitCore  TestAllMethods 

java -Dmethodname="LeastSquaresSolver" -DMRname="transpose" -DFaultName=%1 -DTestNo=test1 -cp .;./lib/junit-4.10.jar;./lib/evosuite-1.0.6.jar -javaagent:.\lib\jacocoagent.jar=append=false,dumponexit=true,classdumpdir=./class_dump_dir,destfile=jacoco.exec org.junit.runner.JUnitCore  TestAllMethods 

java -Dmethodname="LeastSquaresSolver" -DMRname="MultiplyWithIdentity" -DFaultName=%1 -DTestNo=test1 -cp .;./lib/junit-4.10.jar;./lib/evosuite-1.0.6.jar -javaagent:.\lib\jacocoagent.jar=append=false,dumponexit=true,classdumpdir=./class_dump_dir,destfile=jacoco.exec org.junit.runner.JUnitCore  TestAllMethods 

java -Dmethodname="LeastSquaresSolver" -DMRname="negation" -DFaultName=%1 -DTestNo=test1 -cp .;./lib/junit-4.10.jar;./lib/evosuite-1.0.6.jar -javaagent:.\lib\jacocoagent.jar=append=false,dumponexit=true,classdumpdir=./class_dump_dir,destfile=jacoco.exec org.junit.runner.JUnitCore  TestAllMethods 

::pause;