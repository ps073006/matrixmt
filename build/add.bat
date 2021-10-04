copy ".\la4j\mutants\matrix\org\la4j\Matrix\mutants\%1\Matrix.class" ".\org\la4j\Matrix.class"

DEL /F /S /Q /A ".\class_dump_dir\*.*"

::java  -cp .;junit-4.10.jar;evosuite-1.0.6.jar org.junit.runner.JUnitCore org.la4j.matrix.MatrixTest>C:\Users\p66n633\workspace\la4j\output\matrix\output_%1.log


::java  -cp .;junit-4.10.jar;evosuite-1.0.6.jar org.junit.runner.JUnitCore org.la4j.Matrix_ESTest>C:\Users\p66n633\workspace\la4j\output\matrix\output_%1.log
::pause;
java -Dmethodname="add" -DMRname="add" -DFaultName=%1 -DTestNo=test1 -cp .;./lib/junit-4.10.jar;./lib/evosuite-1.0.6.jar -javaagent:.\lib\jacocoagent.jar=append=false,dumponexit=true,classdumpdir=./class_dump_dir,destfile=jacoco.exec org.junit.runner.JUnitCore  TestAllMethods  

::java  -cp .;asm-all-5.0.4.jar;org.jacoco.report-0.7.6.201601131008.jar;org.jacoco.core-0.7.6.201601131008.jar;org.jacoco.ant-0.7.6.201601131008.jar;org.jacoco.agent-0.7.6.201601131008.jar ReportGenerator C:\Users\p66n633\workspace\la4j C:\MRtest\zero\%1\test1\Initial
::pause;

java -Dmethodname="add" -DMRname="Addition" -DFaultName=%1 -DTestNo=test1 -cp .;./lib/junit-4.10.jar;./lib/evosuite-1.0.6.jar -javaagent:.\lib\jacocoagent.jar=append=false,dumponexit=true,classdumpdir=./class_dump_dir,destfile=jacoco.exec org.junit.runner.JUnitCore  TestAllMethods  

::java  -cp .;asm-all-5.0.4.jar;org.jacoco.report-0.7.6.201601131008.jar;org.jacoco.core-0.7.6.201601131008.jar;org.jacoco.ant-0.7.6.201601131008.jar;org.jacoco.agent-0.7.6.201601131008.jar ReportGenerator C:\Users\p66n633\workspace\la4j C:\MRtest\zero\%1\test1\Addition

java -Dmethodname="add" -DMRname="Multiplication" -DFaultName=%1 -DTestNo=test1 -cp .;./lib/junit-4.10.jar;./lib/evosuite-1.0.6.jar -javaagent:.\lib\jacocoagent.jar=append=false,dumponexit=true,classdumpdir=./class_dump_dir,destfile=jacoco.exec org.junit.runner.JUnitCore  TestAllMethods  

::java  -cp .;asm-all-5.0.4.jar;org.jacoco.report-0.7.6.201601131008.jar;org.jacoco.core-0.7.6.201601131008.jar;org.jacoco.ant-0.7.6.201601131008.jar;org.jacoco.agent-0.7.6.201601131008.jar ReportGenerator C:\Users\p66n633\workspace\la4j C:\MRtest\zero\%1\test1\Multiplication

java -Dmethodname="add" -DMRname="transpose" -DFaultName=%1 -DTestNo=test1 -cp .;./lib/junit-4.10.jar;./lib/evosuite-1.0.6.jar -javaagent:.\lib\jacocoagent.jar=append=false,dumponexit=true,classdumpdir=./class_dump_dir,destfile=jacoco.exec org.junit.runner.JUnitCore  TestAllMethods  

::java  -cp .;asm-all-5.0.4.jar;org.jacoco.report-0.7.6.201601131008.jar;org.jacoco.core-0.7.6.201601131008.jar;org.jacoco.ant-0.7.6.201601131008.jar;org.jacoco.agent-0.7.6.201601131008.jar ReportGenerator C:\Users\p66n633\workspace\la4j C:\MRtest\zero\%1\test1\Addition

java -Dmethodname="add" -DMRname="AdditionWithIdentity" -DFaultName=%1 -DTestNo=test1 -cp .;./lib/junit-4.10.jar;./lib/evosuite-1.0.6.jar -javaagent:.\lib\jacocoagent.jar=append=false,dumponexit=true,classdumpdir=./class_dump_dir,destfile=jacoco.exec org.junit.runner.JUnitCore  TestAllMethods  

::java  -cp .;asm-all-5.0.4.jar;org.jacoco.report-0.7.6.201601131008.jar;org.jacoco.core-0.7.6.201601131008.jar;org.jacoco.ant-0.7.6.201601131008.jar;org.jacoco.agent-0.7.6.201601131008.jar ReportGenerator C:\Users\p66n633\workspace\la4j C:\MRtest\zero\%1\test1\Addition

java -Dmethodname="add" -DMRname="MultiplyWithIdentity" -DFaultName=%1 -DTestNo=test1 -cp .;./lib/junit-4.10.jar;./lib/evosuite-1.0.6.jar -javaagent:.\lib\jacocoagent.jar=append=false,dumponexit=true,classdumpdir=./class_dump_dir,destfile=jacoco.exec org.junit.runner.JUnitCore  TestAllMethods  

::java  -cp .;asm-all-5.0.4.jar;org.jacoco.report-0.7.6.201601131008.jar;org.jacoco.core-0.7.6.201601131008.jar;org.jacoco.ant-0.7.6.201601131008.jar;org.jacoco.agent-0.7.6.201601131008.jar ReportGenerator C:\Users\p66n633\workspace\la4j C:\MRtest\zero\%1\test1\Multiplication

::pause