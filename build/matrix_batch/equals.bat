copy "C:\pit\la4j\mutants\matrix\org\la4j\Matrix\mutants\%1\Matrix.class" "C:\Users\p66n633\workspace\la4j\src\org\la4j\Matrix.class"

DEL /F /S /Q /A "C:\Users\p66n633\workspace\la4j\bin\*.*"

::java  -cp .;junit-4.10.jar;evosuite-1.0.6.jar org.junit.runner.JUnitCore org.la4j.Matrix_ESTest>C:\Users\p66n633\workspace\la4j\src\output\matrix\output_%1.log


java -Dmethodname="equals" -DMRname="equals" -DFaultName=%1 -DTestNo=test1 -cp .;junit-4.10.jar;evosuite-1.0.6.jar -javaagent:C:\Users\p66n633\workspace\la4j\src\lib\jacocoagent.jar=append=false,dumponexit=true,classdumpdir=C:/Users/p66n633/workspace/la4j/bin/class_dump_dir,destfile=C:/Users/p66n633/workspace/la4j/jacoco.exec org.junit.runner.JUnitCore  TestAllMethods 

::java  -cp .;asm-all-5.0.4.jar;org.jacoco.report-0.7.6.201601131008.jar;org.jacoco.core-0.7.6.201601131008.jar;org.jacoco.ant-0.7.6.201601131008.jar;org.jacoco.agent-0.7.6.201601131008.jar ReportGenerator C:\Users\p66n633\workspace\la4j C:\MRtest\zero\%1\test1\Initial
::pause;

java -Dmethodname="equals" -DMRname="Addition" -DFaultName=%1 -DTestNo=test1 -cp .;junit-4.10.jar;evosuite-1.0.6.jar -javaagent:C:\Users\p66n633\workspace\la4j\src\lib\jacocoagent.jar=append=false,dumponexit=true,classdumpdir=C:/Users/p66n633/workspace/la4j/bin/class_dump_dir,destfile=C:/Users/p66n633/workspace/la4j/jacoco.exec org.junit.runner.JUnitCore  TestAllMethods 

::java  -cp .;asm-all-5.0.4.jar;org.jacoco.report-0.7.6.201601131008.jar;org.jacoco.core-0.7.6.201601131008.jar;org.jacoco.ant-0.7.6.201601131008.jar;org.jacoco.agent-0.7.6.201601131008.jar ReportGenerator C:\Users\p66n633\workspace\la4j C:\MRtest\zero\%1\test1\Addition

java -Dmethodname="equals" -DMRname="Multiplication" -DFaultName=%1 -DTestNo=test1 -cp .;junit-4.10.jar;evosuite-1.0.6.jar -javaagent:C:\Users\p66n633\workspace\la4j\src\lib\jacocoagent.jar=append=false,dumponexit=true,classdumpdir=C:/Users/p66n633/workspace/la4j/bin/class_dump_dir,destfile=C:/Users/p66n633/workspace/la4j/jacoco.exec org.junit.runner.JUnitCore  TestAllMethods 

::java  -cp .;asm-all-5.0.4.jar;org.jacoco.report-0.7.6.201601131008.jar;org.jacoco.core-0.7.6.201601131008.jar;org.jacoco.ant-0.7.6.201601131008.jar;org.jacoco.agent-0.7.6.201601131008.jar ReportGenerator C:\Users\p66n633\workspace\la4j C:\MRtest\zero\%1\test1\Multiplication

java -Dmethodname="equals" -DMRname="matrixAdd" -DFaultName=%1 -DTestNo=test1 -cp .;junit-4.10.jar;evosuite-1.0.6.jar -javaagent:C:\Users\p66n633\workspace\la4j\src\lib\jacocoagent.jar=append=false,dumponexit=true,classdumpdir=C:/Users/p66n633/workspace/la4j/bin/class_dump_dir,destfile=C:/Users/p66n633/workspace/la4j/jacoco.exec org.junit.runner.JUnitCore  TestAllMethods 

::java  -cp .;asm-all-5.0.4.jar;org.jacoco.report-0.7.6.201601131008.jar;org.jacoco.core-0.7.6.201601131008.jar;org.jacoco.ant-0.7.6.201601131008.jar;org.jacoco.agent-0.7.6.201601131008.jar ReportGenerator C:\Users\p66n633\workspace\la4j C:\MRtest\zero\%1\test1\Addition

java -Dmethodname="equals" -DMRname="matrixMul" -DFaultName=%1 -DTestNo=test1 -cp .;junit-4.10.jar;evosuite-1.0.6.jar -javaagent:C:\Users\p66n633\workspace\la4j\src\lib\jacocoagent.jar=append=false,dumponexit=true,classdumpdir=C:/Users/p66n633/workspace/la4j/bin/class_dump_dir,destfile=C:/Users/p66n633/workspace/la4j/jacoco.exec org.junit.runner.JUnitCore  TestAllMethods 

::java  -cp .;asm-all-5.0.4.jar;org.jacoco.report-0.7.6.201601131008.jar;org.jacoco.core-0.7.6.201601131008.jar;org.jacoco.ant-0.7.6.201601131008.jar;org.jacoco.agent-0.7.6.201601131008.jar ReportGenerator C:\Users\p66n633\workspace\la4j C:\MRtest\zero\%1\test1\Multiplication

java -Dmethodname="equals" -DMRname="transpose" -DFaultName=%1 -DTestNo=test1 -cp .;junit-4.10.jar;evosuite-1.0.6.jar -javaagent:C:\Users\p66n633\workspace\la4j\src\lib\jacocoagent.jar=append=false,dumponexit=true,classdumpdir=C:/Users/p66n633/workspace/la4j/bin/class_dump_dir,destfile=C:/Users/p66n633/workspace/la4j/jacoco.exec org.junit.runner.JUnitCore  TestAllMethods 

::java  -cp .;asm-all-5.0.4.jar;org.jacoco.report-0.7.6.201601131008.jar;org.jacoco.core-0.7.6.201601131008.jar;org.jacoco.ant-0.7.6.201601131008.jar;org.jacoco.agent-0.7.6.201601131008.jar ReportGenerator C:\Users\p66n633\workspace\la4j C:\MRtest\zero\%1\test1\Addition

java -Dmethodname="equals" -DMRname="MultiplyWithIdentity" -DFaultName=%1 -DTestNo=test1 -cp .;junit-4.10.jar;evosuite-1.0.6.jar -javaagent:C:\Users\p66n633\workspace\la4j\src\lib\jacocoagent.jar=append=false,dumponexit=true,classdumpdir=C:/Users/p66n633/workspace/la4j/bin/class_dump_dir,destfile=C:/Users/p66n633/workspace/la4j/jacoco.exec org.junit.runner.JUnitCore  TestAllMethods 

::java  -cp .;asm-all-5.0.4.jar;org.jacoco.report-0.7.6.201601131008.jar;org.jacoco.core-0.7.6.201601131008.jar;org.jacoco.ant-0.7.6.201601131008.jar;org.jacoco.agent-0.7.6.201601131008.jar ReportGenerator C:\Users\p66n633\workspace\la4j C:\MRtest\zero\%1\test1\Multiplication
