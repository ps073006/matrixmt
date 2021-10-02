copy ".\la4j\mutants\SquareRootSolver\org\la4j\linear\SquareRootSolver\mutants\%1\SquareRootSolver.class" ".\org\la4j\linear\SquareRootSolver.class"

DEL /F /S /Q /A ".\class_dump_dir\*.*"

::java  -cp .;junit-4.10.jar;evosuite-1.0.6.jar org.junit.runner.JUnitCore org.matmt.linear.SquareRootSolver_ESTest>C:\Users\p66n633\workspace\matmt\src\output\SquareRootSolver\output_%1.log

::java  -cp .;junit-4.10.jar;evosuite-1.0.6.jar org.junit.runner.JUnitCore org.matmt.linear.SquareRootSolverTest>C:\Users\p66n633\workspace\matmt\src\output\SquareRootSolver\output_%1.log

::pause;

java -Dmethodname="SquareRootSolver" -DMRname="solve" -DFaultName=%1 -DTestNo=test1 -cp .;./lib/junit-4.10.jar;./lib/evosuite-1.0.6.jar -javaagent:.\lib\jacocoagent.jar=append=false,dumponexit=true,classdumpdir=./class_dump_dir,destfile=jacoco.exec org.junit.runner.JUnitCore  TestAllMethods 

java -Dmethodname="SquareRootSolver" -DMRname="Multiplication" -DFaultName=%1 -DTestNo=test1 -cp .;./lib/junit-4.10.jar;./lib/evosuite-1.0.6.jar -javaagent:.\lib\jacocoagent.jar=append=false,dumponexit=true,classdumpdir=./class_dump_dir,destfile=jacoco.exec org.junit.runner.JUnitCore  TestAllMethods 

::java -Dmethodname="SquareRootSolver" -DMRname="permuteRow" -DFaultName=%1 -DTestNo=test1 -cp .;./lib/junit-4.10.jar;./lib/evosuite-1.0.6.jar -javaagent:.\lib\jacocoagent.jar=append=false,dumponexit=true,classdumpdir=./class_dump_dir,destfile=jacoco.exec org.junit.runner.JUnitCore  TestAllMethods 

java -Dmethodname="SquareRootSolver" -DMRname="matrixAdd" -DFaultName=%1 -DTestNo=test1 -cp .;./lib/junit-4.10.jar;./lib/evosuite-1.0.6.jar -javaagent:.\lib\jacocoagent.jar=append=false,dumponexit=true,classdumpdir=./class_dump_dir,destfile=jacoco.exec org.junit.runner.JUnitCore  TestAllMethods 

java -Dmethodname="SquareRootSolver" -DMRname="transpose" -DFaultName=%1 -DTestNo=test1 -cp .;./lib/junit-4.10.jar;./lib/evosuite-1.0.6.jar -javaagent:.\lib\jacocoagent.jar=append=false,dumponexit=true,classdumpdir=./class_dump_dir,destfile=jacoco.exec org.junit.runner.JUnitCore  TestAllMethods 

java -Dmethodname="SquareRootSolver" -DMRname="MultiplyWithIdentity" -DFaultName=%1 -DTestNo=test1 -cp .;./lib/junit-4.10.jar;./lib/evosuite-1.0.6.jar -javaagent:.\lib\jacocoagent.jar=append=false,dumponexit=true,classdumpdir=./class_dump_dir,destfile=jacoco.exec org.junit.runner.JUnitCore  TestAllMethods 

java -Dmethodname="SquareRootSolver" -DMRname="negation" -DFaultName=%1 -DTestNo=test1 -cp .;./lib/junit-4.10.jar;./lib/evosuite-1.0.6.jar -javaagent:.\lib\jacocoagent.jar=append=false,dumponexit=true,classdumpdir=./class_dump_dir,destfile=jacoco.exec org.junit.runner.JUnitCore  TestAllMethods 

::pause;