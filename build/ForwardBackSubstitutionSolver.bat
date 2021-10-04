copy ".\la4j\mutants\ForwardBackSubstitutionSolver\org\la4j\linear\ForwardBackSubstitutionSolver\mutants\%1\ForwardBackSubstitutionSolver.class" ".\org\la4j\linear\ForwardBackSubstitutionSolver.class"

DEL /F /S /Q /A ".\class_dump_dir\*.*"


::java  -cp .;junit-4.10.jar;evosuite-1.0.6.jar org.junit.runner.JUnitCore org.la4j.linear.ForwardBackSubstitutionSolver_ESTest>C:\Users\p66n633\workspace\la4j\src\output\ForwardBackSubstitutionSolver\output_%1.log

::java  -cp .;junit-4.10.jar;evosuite-1.0.6.jar org.junit.runner.JUnitCore org.la4j.linear.ForwardBackSubstitutionSolverTest>C:\Users\p66n633\workspace\la4j\src\output\ForwardBackSubstitutionSolver\output_%1.log

::pause;
java -Dmethodname="ForwardBackSubstitutionSolver" -DMRname="solve" -DFaultName=%1 -DTestNo=test1 -cp .;./lib/junit-4.10.jar;./lib/evosuite-1.0.6.jar -javaagent:.\lib\jacocoagent.jar=append=false,dumponexit=true,classdumpdir=./class_dump_dir,destfile=jacoco.exec org.junit.runner.JUnitCore  TestAllMethods 

java -Dmethodname="ForwardBackSubstitutionSolver" -DMRname="Multiplication" -DFaultName=%1 -DTestNo=test1 -cp .;./lib/junit-4.10.jar;./lib/evosuite-1.0.6.jar -javaagent:.\lib\jacocoagent.jar=append=false,dumponexit=true,classdumpdir=./class_dump_dir,destfile=jacoco.exec org.junit.runner.JUnitCore  TestAllMethods 

java -Dmethodname="ForwardBackSubstitutionSolver" -DMRname="permuteRow" -DFaultName=%1 -DTestNo=test1 -cp .;./lib/junit-4.10.jar;./lib/evosuite-1.0.6.jar -javaagent:.\lib\jacocoagent.jar=append=false,dumponexit=true,classdumpdir=./class_dump_dir,destfile=jacoco.exec org.junit.runner.JUnitCore  TestAllMethods 

java -Dmethodname="ForwardBackSubstitutionSolver" -DMRname="matrixAdd" -DFaultName=%1 -DTestNo=test1 -cp .;./lib/junit-4.10.jar;./lib/evosuite-1.0.6.jar -javaagent:.\lib\jacocoagent.jar=append=false,dumponexit=true,classdumpdir=./class_dump_dir,destfile=jacoco.exec org.junit.runner.JUnitCore  TestAllMethods 

java -Dmethodname="ForwardBackSubstitutionSolver" -DMRname="transpose" -DFaultName=%1 -DTestNo=test1 -cp .;./lib/junit-4.10.jar;./lib/evosuite-1.0.6.jar -javaagent:.\lib\jacocoagent.jar=append=false,dumponexit=true,classdumpdir=./class_dump_dir,destfile=jacoco.exec org.junit.runner.JUnitCore  TestAllMethods 

java -Dmethodname="ForwardBackSubstitutionSolver" -DMRname="MultiplyWithIdentity" -DFaultName=%1 -DTestNo=test1 -cp .;./lib/junit-4.10.jar;./lib/evosuite-1.0.6.jar -javaagent:.\lib\jacocoagent.jar=append=false,dumponexit=true,classdumpdir=./class_dump_dir,destfile=jacoco.exec org.junit.runner.JUnitCore  TestAllMethods 

java -Dmethodname="ForwardBackSubstitutionSolver" -DMRname="negation" -DFaultName=%1 -DTestNo=test1 -cp .;./lib/junit-4.10.jar;./lib/evosuite-1.0.6.jar -javaagent:.\lib\jacocoagent.jar=append=false,dumponexit=true,classdumpdir=./class_dump_dir,destfile=jacoco.exec org.junit.runner.JUnitCore  TestAllMethods 


::pause;