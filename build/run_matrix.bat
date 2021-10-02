copy "C:\pit\la4j_matrix_class\pit-reports\org\la4j\Matrix\mutants\%1\Matrix.class" "C:\Users\p66n633\workspace\la4j\src\org\la4j\Matrix.class"

::copy "C:\MRtest\la4j\mutants\pit\matrix\zero\%1\Matrix.class" "C:\Users\p66n633\workspace\la4j\src\org\la4j\Matrix.class"

::DEL /F /S /Q /A "C:\Users\p66n633\workspace\la4j\bin\*.*"

::java -Dmethodname="zero" -DMRname="zero" -DFaultName=%1 -DTestNo=test1 -cp .;junit-4.10.jar;evosuite-1.0.6.jar -javaagent:C:\Users\p66n633\workspace\la4j\src\lib\jacocoagent.jar=append=false,dumponexit=true,classdumpdir=C:/Users/p66n633/workspace/la4j/bin/class_dump_dir,destfile=C:/Users/p66n633/workspace/la4j/jacoco.exec org.junit.runner.JUnitCore  TestAllMethods 

::java  -cp .;asm-all-5.0.4.jar;org.jacoco.report-0.7.6.201601131008.jar;org.jacoco.core-0.7.6.201601131008.jar;org.jacoco.ant-0.7.6.201601131008.jar;org.jacoco.agent-0.7.6.201601131008.jar ReportGenerator C:\Users\p66n633\workspace\WekaTest C:\MRtest\void_buildClassifier(weka.core.Instances)\%1\test1\Initial
::pause;

::copy "C:\MRtest\la4j\mutants\pit\matrix\updatecolumn\%1\Matrix.class" "C:\Users\p66n633\workspace\la4j\src\org\la4j\Matrix.class"

::DEL /F /S /Q /A "C:\Users\p66n633\workspace\la4j\bin\*.*"

::java -Dmethodname="updateColumn" -DMRname="updateColumn" -DFaultName=%1 -DTestNo=test1 -cp .;junit-4.10.jar;evosuite-1.0.6.jar -javaagent:C:\Users\p66n633\workspace\la4j\src\lib\jacocoagent.jar=append=false,dumponexit=true,classdumpdir=C:/Users/p66n633/workspace/la4j/bin/class_dump_dir,destfile=C:/Users/p66n633/workspace/la4j/jacoco.exec org.junit.runner.JUnitCore  TestAllMethods 

::copy "C:\MRtest\la4j\mutants\pit\matrix\transpose\%1\Matrix.class" "C:\Users\p66n633\workspace\la4j\src\org\la4j\Matrix.class"

::DEL /F /S /Q /A "C:\Users\p66n633\workspace\la4j\bin\*.*"

::java -Dmethodname="transpose" -DMRname="transpose" -DFaultName=%1 -DTestNo=test1 -cp .;junit-4.10.jar;evosuite-1.0.6.jar -javaagent:C:\Users\p66n633\workspace\la4j\src\lib\jacocoagent.jar=append=false,dumponexit=true,classdumpdir=C:/Users/p66n633/workspace/la4j/bin/class_dump_dir,destfile=C:/Users/p66n633/workspace/la4j/jacoco.exec org.junit.runner.JUnitCore  TestAllMethods 

::copy "C:\MRtest\la4j\mutants\pit\matrix\transformRow\%1\Matrix.class" "C:\Users\p66n633\workspace\la4j\src\org\la4j\Matrix.class"

::DEL /F /S /Q /A "C:\Users\p66n633\workspace\la4j\bin\*.*"

::java -Dmethodname="transformRow" -DMRname="transformRow" -DFaultName=%1 -DTestNo=test1 -cp .;junit-4.10.jar;evosuite-1.0.6.jar -javaagent:C:\Users\p66n633\workspace\la4j\src\lib\jacocoagent.jar=append=false,dumponexit=true,classdumpdir=C:/Users/p66n633/workspace/la4j/bin/class_dump_dir,destfile=C:/Users/p66n633/workspace/la4j/jacoco.exec org.junit.runner.JUnitCore  TestAllMethods 


::copy "C:\MRtest\la4j\mutants\pit\matrix\transformColumn\%1\Matrix.class" "C:\Users\p66n633\workspace\la4j\src\org\la4j\Matrix.class"

::DEL /F /S /Q /A "C:\Users\p66n633\workspace\la4j\bin\*.*"

::java -Dmethodname="transformColumn" -DMRname="transformColumn" -DFaultName=%1 -DTestNo=test1 -cp .;junit-4.10.jar;evosuite-1.0.6.jar -javaagent:C:\Users\p66n633\workspace\la4j\src\lib\jacocoagent.jar=append=false,dumponexit=true,classdumpdir=C:/Users/p66n633/workspace/la4j/bin/class_dump_dir,destfile=C:/Users/p66n633/workspace/la4j/jacoco.exec org.junit.runner.JUnitCore  TestAllMethods 

::copy "C:\MRtest\la4j\mutants\pit\matrix\equals\%1\Matrix.class" "C:\Users\p66n633\workspace\la4j\src\org\la4j\Matrix.class"

::DEL /F /S /Q /A "C:\Users\p66n633\workspace\la4j\bin\*.*"

::java -Dmethodname="equals" -DMRname="equals" -DFaultName=%1 -DTestNo=test1 -cp .;junit-4.10.jar;evosuite-1.0.6.jar -javaagent:C:\Users\p66n633\workspace\la4j\src\lib\jacocoagent.jar=append=false,dumponexit=true,classdumpdir=C:/Users/p66n633/workspace/la4j/bin/class_dump_dir,destfile=C:/Users/p66n633/workspace/la4j/jacoco.exec org.junit.runner.JUnitCore  TestAllMethods 

::copy "C:\MRtest\la4j\mutants\pit\matrix\shuffle\%1\Matrix.class" "C:\Users\p66n633\workspace\la4j\src\org\la4j\Matrix.class"

::DEL /F /S /Q /A "C:\Users\p66n633\workspace\la4j\bin\*.*"

::java -Dmethodname="shuffle" -DMRname="shuffle" -DFaultName=%1 -DTestNo=test1 -cp .;junit-4.10.jar;evosuite-1.0.6.jar -javaagent:C:\Users\p66n633\workspace\la4j\src\lib\jacocoagent.jar=append=false,dumponexit=true,classdumpdir=C:/Users/p66n633/workspace/la4j/bin/class_dump_dir,destfile=C:/Users/p66n633/workspace/la4j/jacoco.exec org.junit.runner.JUnitCore  TestAllMethods 

::copy "C:\MRtest\la4j\mutants\pit\matrix\rotate\%1\Matrix.class" "C:\Users\p66n633\workspace\la4j\src\org\la4j\Matrix.class"

::DEL /F /S /Q /A "C:\Users\p66n633\workspace\la4j\bin\*.*"

::java -Dmethodname="rotate" -DMRname="rotate" -DFaultName=%1 -DTestNo=test1 -cp .;junit-4.10.jar;evosuite-1.0.6.jar -javaagent:C:\Users\p66n633\workspace\la4j\src\lib\jacocoagent.jar=append=false,dumponexit=true,classdumpdir=C:/Users/p66n633/workspace/la4j/bin/class_dump_dir,destfile=C:/Users/p66n633/workspace/la4j/jacoco.exec org.junit.runner.JUnitCore  TestAllMethods 

::copy "C:\MRtest\la4j\mutants\pit\matrix\removeLastRow\%1\Matrix.class" "C:\Users\p66n633\workspace\la4j\src\org\la4j\Matrix.class"

::DEL /F /S /Q /A "C:\Users\p66n633\workspace\la4j\bin\*.*"

::java -Dmethodname="removeLastRow" -DMRname="removeLastRow" -DFaultName=%1 -DTestNo=test1 -cp .;junit-4.10.jar;evosuite-1.0.6.jar -javaagent:C:\Users\p66n633\workspace\la4j\src\lib\jacocoagent.jar=append=false,dumponexit=true,classdumpdir=C:/Users/p66n633/workspace/la4j/bin/class_dump_dir,destfile=C:/Users/p66n633/workspace/la4j/jacoco.exec org.junit.runner.JUnitCore  TestAllMethods 

::copy "C:\MRtest\la4j\mutants\pit\matrix\removeLastColumn\%1\Matrix.class" "C:\Users\p66n633\workspace\la4j\src\org\la4j\Matrix.class"

::DEL /F /S /Q /A "C:\Users\p66n633\workspace\la4j\bin\*.*"

::java -Dmethodname="removeLastColumn" -DMRname="removeLastColumn" -DFaultName=%1 -DTestNo=test1 -cp .;junit-4.10.jar;evosuite-1.0.6.jar -javaagent:C:\Users\p66n633\workspace\la4j\src\lib\jacocoagent.jar=append=false,dumponexit=true,classdumpdir=C:/Users/p66n633/workspace/la4j/bin/class_dump_dir,destfile=C:/Users/p66n633/workspace/la4j/jacoco.exec org.junit.runner.JUnitCore  TestAllMethods 

::copy "C:\MRtest\la4j\mutants\pit\matrix\power\%1\Matrix.class" "C:\Users\p66n633\workspace\la4j\src\org\la4j\Matrix.class"

::DEL /F /S /Q /A "C:\Users\p66n633\workspace\la4j\bin\*.*"

::java -Dmethodname="power" -DMRname="power" -DFaultName=%1 -DTestNo=test1 -cp .;junit-4.10.jar;evosuite-1.0.6.jar -javaagent:C:\Users\p66n633\workspace\la4j\src\lib\jacocoagent.jar=append=false,dumponexit=true,classdumpdir=C:/Users/p66n633/workspace/la4j/bin/class_dump_dir,destfile=C:/Users/p66n633/workspace/la4j/jacoco.exec org.junit.runner.JUnitCore  TestAllMethods 

::copy "C:\MRtest\la4j\mutants\pit\matrix\multiply\%1\Matrix.class" "C:\Users\p66n633\workspace\la4j\src\org\la4j\Matrix.class"

::DEL /F /S /Q /A "C:\Users\p66n633\workspace\la4j\bin\*.*"

::java -Dmethodname="multiply" -DMRname="multiply" -DFaultName=%1 -DTestNo=test1 -cp .;junit-4.10.jar;evosuite-1.0.6.jar -javaagent:C:\Users\p66n633\workspace\la4j\src\lib\jacocoagent.jar=append=false,dumponexit=true,classdumpdir=C:/Users/p66n633/workspace/la4j/bin/class_dump_dir,destfile=C:/Users/p66n633/workspace/la4j/jacoco.exec org.junit.runner.JUnitCore  TestAllMethods 


::copy "C:\MRtest\la4j\mutants\pit\matrix\insert\%1\Matrix.class" "C:\Users\p66n633\workspace\la4j\src\org\la4j\Matrix.class"

::DEL /F /S /Q /A "C:\Users\p66n633\workspace\la4j\bin\*.*"

::java -Dmethodname="insert" -DMRname="insert" -DFaultName=%1 -DTestNo=test1 -cp .;junit-4.10.jar;evosuite-1.0.6.jar -javaagent:C:\Users\p66n633\workspace\la4j\src\lib\jacocoagent.jar=append=false,dumponexit=true,classdumpdir=C:/Users/p66n633/workspace/la4j/bin/class_dump_dir,destfile=C:/Users/p66n633/workspace/la4j/jacoco.exec org.junit.runner.JUnitCore  TestAllMethods 

::copy "C:\MRtest\la4j\mutants\pit\matrix\fromCSV\%1\Matrix.class" "C:\Users\p66n633\workspace\la4j\src\org\la4j\Matrix.class"

::DEL /F /S /Q /A "C:\Users\p66n633\workspace\la4j\bin\*.*"

::java -Dmethodname="fromCSV" -DMRname="fromCSV" -DFaultName=%1 -DTestNo=test1 -cp .;junit-4.10.jar;evosuite-1.0.6.jar -javaagent:C:\Users\p66n633\workspace\la4j\src\lib\jacocoagent.jar=append=false,dumponexit=true,classdumpdir=C:/Users/p66n633/workspace/la4j/bin/class_dump_dir,destfile=C:/Users/p66n633/workspace/la4j/jacoco.exec org.junit.runner.JUnitCore  TestAllMethods 

::copy "C:\MRtest\la4j\mutants\pit\matrix\subtract\%1\Matrix.class" "C:\Users\p66n633\workspace\la4j\src\org\la4j\Matrix.class"

::DEL /F /S /Q /A "C:\Users\p66n633\workspace\la4j\bin\*.*"

::java -Dmethodname="subtract" -DMRname="subtract" -DFaultName=%1 -DTestNo=test1 -cp .;junit-4.10.jar;evosuite-1.0.6.jar -javaagent:C:\Users\p66n633\workspace\la4j\src\lib\jacocoagent.jar=append=false,dumponexit=true,classdumpdir=C:/Users/p66n633/workspace/la4j/bin/class_dump_dir,destfile=C:/Users/p66n633/workspace/la4j/jacoco.exec org.junit.runner.JUnitCore  TestAllMethods 

::copy "C:\MRtest\la4j\mutants\pit\matrix\add\%1\Matrix.class" "C:\Users\p66n633\workspace\la4j\src\org\la4j\Matrix.class"

::DEL /F /S /Q /A "C:\Users\p66n633\workspace\la4j\bin\*.*"

::java -Dmethodname="add" -DMRname="add" -DFaultName=%1 -DTestNo=test1 -cp .;junit-4.10.jar;evosuite-1.0.6.jar -javaagent:C:\Users\p66n633\workspace\la4j\src\lib\jacocoagent.jar=append=false,dumponexit=true,classdumpdir=C:/Users/p66n633/workspace/la4j/bin/class_dump_dir,destfile=C:/Users/p66n633/workspace/la4j/jacoco.exec org.junit.runner.JUnitCore  TestAllMethods 

::copy "C:\MRtest\la4j\mutants\pit\matrix\select\%1\Matrix.class" "C:\Users\p66n633\workspace\la4j\src\org\la4j\Matrix.class"

::DEL /F /S /Q /A "C:\Users\p66n633\workspace\la4j\bin\*.*"

::java -Dmethodname="select" -DMRname="select" -DFaultName=%1 -DTestNo=test1 -cp .;junit-4.10.jar;evosuite-1.0.6.jar -javaagent:C:\Users\p66n633\workspace\la4j\src\lib\jacocoagent.jar=append=false,dumponexit=true,classdumpdir=C:/Users/p66n633/workspace/la4j/bin/class_dump_dir,destfile=C:/Users/p66n633/workspace/la4j/jacoco.exec org.junit.runner.JUnitCore  TestAllMethods 

::copy "C:\MRtest\la4j\mutants\pit\matrix\determinant\%1\Matrix.class" "C:\Users\p66n633\workspace\la4j\src\org\la4j\Matrix.class"

::DEL /F /S /Q /A "C:\Users\p66n633\workspace\la4j\bin\*.*"

::java -Dmethodname="determinant" -DMRname="determinant" -DFaultName=%1 -DTestNo=test1 -cp .;junit-4.10.jar;evosuite-1.0.6.jar -javaagent:C:\Users\p66n633\workspace\la4j\src\lib\jacocoagent.jar=append=false,dumponexit=true,classdumpdir=C:/Users/p66n633/workspace/la4j/bin/class_dump_dir,destfile=C:/Users/p66n633/workspace/la4j/jacoco.exec org.junit.runner.JUnitCore  TestAllMethods 

::copy "C:\MRtest\la4j\mutants\pit\matrix\sliceTopLeft\%1\Matrix.class" "C:\Users\p66n633\workspace\la4j\src\org\la4j\Matrix.class"

::DEL /F /S /Q /A "C:\Users\p66n633\workspace\la4j\bin\*.*"

::java -Dmethodname="sliceTopLeft" -DMRname="sliceTopLeft" -DFaultName=%1 -DTestNo=test1 -cp .;junit-4.10.jar;evosuite-1.0.6.jar -javaagent:C:\Users\p66n633\workspace\la4j\src\lib\jacocoagent.jar=append=false,dumponexit=true,classdumpdir=C:/Users/p66n633/workspace/la4j/bin/class_dump_dir,destfile=C:/Users/p66n633/workspace/la4j/jacoco.exec org.junit.runner.JUnitCore  TestAllMethods 

::copy "C:\MRtest\la4j\mutants\pit\matrix\rank\%1\Matrix.class" "C:\Users\p66n633\workspace\la4j\src\org\la4j\Matrix.class"

::DEL /F /S /Q /A "C:\Users\p66n633\workspace\la4j\bin\*.*"

::java -Dmethodname="rank" -DMRname="rank" -DFaultName=%1 -DTestNo=test1 -cp .;junit-4.10.jar;evosuite-1.0.6.jar -javaagent:C:\Users\p66n633\workspace\la4j\src\lib\jacocoagent.jar=append=false,dumponexit=true,classdumpdir=C:/Users/p66n633/workspace/la4j/bin/class_dump_dir,destfile=C:/Users/p66n633/workspace/la4j/jacoco.exec org.junit.runner.JUnitCore  TestAllMethods 


::copy "C:\MRtest\la4j\mutants\pit\matrix\setRow\%1\Matrix.class" "C:\Users\p66n633\workspace\la4j\src\org\la4j\Matrix.class"

DEL /F /S /Q /A "C:\Users\p66n633\workspace\la4j\bin\*.*"

java -Dmethodname="setRow" -DMRname="setRow" -DFaultName=%1 -DTestNo=test1 -cp .;junit-4.10.jar;evosuite-1.0.6.jar -javaagent:C:\Users\p66n633\workspace\la4j\src\lib\jacocoagent.jar=append=false,dumponexit=true,classdumpdir=C:/Users/p66n633/workspace/la4j/bin/class_dump_dir,destfile=C:/Users/p66n633/workspace/la4j/jacoco.exec org.junit.runner.JUnitCore  TestAllMethods 
