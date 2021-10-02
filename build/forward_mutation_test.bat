copy "C:\pit\la4j\mutants\ForwardBackSubstitutionSolver\org\la4j\linear\ForwardBackSubstitutionSolver\mutants\%1\ForwardBackSubstitutionSolver.class" "C:\Users\p66n633\workspace\la4j\src\org\la4j\linear\ForwardBackSubstitutionSolver.class"

DEL /F /S /Q /A "C:\Users\p66n633\workspace\la4j\bin\*.*"

::java  -cp .;junit-4.10.jar;evosuite-1.0.6.jar org.junit.runner.JUnitCore org.la4j.linear.ForwardBackSubstitutionSolver_ESTest>C:\Users\p66n633\workspace\la4j\src\output\ForwardBackSubstitutionSolver\output_%1.log

::java  -cp .;junit-4.10.jar;evosuite-1.0.6.jar org.junit.runner.JUnitCore org.la4j.linear.ForwardBackSubstitutionSolverTest>C:\Users\p66n633\workspace\la4j\src\output\ForwardBackSubstitutionSolver\output_%1.log

::pause;
java -Dmethodname="ForwardBackSubstitutionSolver" -DMRname="solve" -DFaultName=%1 -DTestNo=test1 -cp .;junit-4.10.jar;evosuite-1.0.6.jar -javaagent:C:\Users\p66n633\workspace\la4j\src\lib\jacocoagent.jar=append=false,dumponexit=true,classdumpdir=C:/Users/p66n633/workspace/la4j/bin/class_dump_dir,destfile=C:/Users/p66n633/workspace/la4j/jacoco.exec org.junit.runner.JUnitCore  TestAllMethods 

java -Dmethodname="ForwardBackSubstitutionSolver" -DMRname="Multiplication" -DFaultName=%1 -DTestNo=test1 -cp .;junit-4.10.jar;evosuite-1.0.6.jar -javaagent:C:\Users\p66n633\workspace\la4j\src\lib\jacocoagent.jar=append=false,dumponexit=true,classdumpdir=C:/Users/p66n633/workspace/la4j/bin/class_dump_dir,destfile=C:/Users/p66n633/workspace/la4j/jacoco.exec org.junit.runner.JUnitCore  TestAllMethods 

java -Dmethodname="ForwardBackSubstitutionSolver" -DMRname="permuteRow" -DFaultName=%1 -DTestNo=test1 -cp .;junit-4.10.jar;evosuite-1.0.6.jar -javaagent:C:\Users\p66n633\workspace\la4j\src\lib\jacocoagent.jar=append=false,dumponexit=true,classdumpdir=C:/Users/p66n633/workspace/la4j/bin/class_dump_dir,destfile=C:/Users/p66n633/workspace/la4j/jacoco.exec org.junit.runner.JUnitCore  TestAllMethods 

java -Dmethodname="ForwardBackSubstitutionSolver" -DMRname="matrixAdd" -DFaultName=%1 -DTestNo=test1 -cp .;junit-4.10.jar;evosuite-1.0.6.jar -javaagent:C:\Users\p66n633\workspace\la4j\src\lib\jacocoagent.jar=append=false,dumponexit=true,classdumpdir=C:/Users/p66n633/workspace/la4j/bin/class_dump_dir,destfile=C:/Users/p66n633/workspace/la4j/jacoco.exec org.junit.runner.JUnitCore  TestAllMethods 

java -Dmethodname="ForwardBackSubstitutionSolver" -DMRname="transpose" -DFaultName=%1 -DTestNo=test1 -cp .;junit-4.10.jar;evosuite-1.0.6.jar -javaagent:C:\Users\p66n633\workspace\la4j\src\lib\jacocoagent.jar=append=false,dumponexit=true,classdumpdir=C:/Users/p66n633/workspace/la4j/bin/class_dump_dir,destfile=C:/Users/p66n633/workspace/la4j/jacoco.exec org.junit.runner.JUnitCore  TestAllMethods 

java -Dmethodname="ForwardBackSubstitutionSolver" -DMRname="MultiplyWithIdentity" -DFaultName=%1 -DTestNo=test1 -cp .;junit-4.10.jar;evosuite-1.0.6.jar -javaagent:C:\Users\p66n633\workspace\la4j\src\lib\jacocoagent.jar=append=false,dumponexit=true,classdumpdir=C:/Users/p66n633/workspace/la4j/bin/class_dump_dir,destfile=C:/Users/p66n633/workspace/la4j/jacoco.exec org.junit.runner.JUnitCore  TestAllMethods 

java -Dmethodname="ForwardBackSubstitutionSolver" -DMRname="negation" -DFaultName=%1 -DTestNo=test1 -cp .;junit-4.10.jar;evosuite-1.0.6.jar -javaagent:C:\Users\p66n633\workspace\la4j\src\lib\jacocoagent.jar=append=false,dumponexit=true,classdumpdir=C:/Users/p66n633/workspace/la4j/bin/class_dump_dir,destfile=C:/Users/p66n633/workspace/la4j/jacoco.exec org.junit.runner.JUnitCore  TestAllMethods 
