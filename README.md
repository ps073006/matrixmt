Running Metamorphic Testing using batch files::
----------------------------------------------------------------
Required files:
----------------------
      1. Batch files: testAllMethod.bat, run_mutants.bat, *method_name*.bat, e.g. Add.bat (all files are located in build folder)
      2. Original code of subject programs (.java files) (located in .\build\original\)

Note: This code was tested on JDK 13.0.1 & Windows 10 OS. 

Executing with Mutants
---------------------------------
Step 1:
      Copy for loop code  for any particular method (e.g. add() method) from ‘testAllMethod.bat’ file to ‘run_mutants.bat’ file.
Step 2:
      Run ‘run_mutants.bat’. It will run the Metamorphic testing and create the mutants pass/fail report inside the ‘MRtest’ folder. E.g. add.csv will have reports for each mutant killed ->’False’ / alive->’True’ status per MR. If any mutants throw any exception, those reports will be in the ‘add_exception.txt’ file.

Executing with Original program
---------------------------------------------
Step 1:
      Copy the subject program (.java file) from the  ‘.\build\original\’ location to the ‘.src\’ folder location. E.g. Matrix.java is our subject program. Copy this file from ‘build\original\org\la4j’ to ‘\src\org\la4j’.
Step 2:
      Follow step 1&2  from the ‘Setting up Matrixmt project’ section for the very first time if the project is not set up. Follow step 3 from the ‘Setting up Matrixmt project’ section always. 
Step 3:
      Copy the class (e.g. Matrix.class) file from the ‘Bin’ folder  to ‘build\\org\la4j’ and replace if there is any previous class (e.g. Matrix.class) file exists.
Step 4:
      Run ‘*method_name*.bat’ from build folder  to test any method from matrix class. E.g. Executing the ‘add.bat’ file will generate the ‘add.csv’ file inside the ‘MRtest’ folder. ‘add.csv’ will have killed ->’False’ / alive->’True’ status per MR reports for original programs.

Modifying Files to switch among test suites::
----------------------------------------------------------------
Required files/IDE:
--------------------------
      1. Java file: TestAllMethods.java (located in ‘src’ folder)
      2. Jar files: evosuite-1.0.6.jar, jacocoagent.jar, junit-4.10.jar, org.jacoco.agent-0.8.5.201910111838.jar, org.jacoco.ant-0.8.5.201910111838.jar, org.jacoco.core-0.8.5.201910111838.jar, org.jacoco.report-0.8.5.201910111838.jar (located in ‘/src/lib’)
      3. Eclipse IDE

Setting up Matrixmt project:
-------------------------------------
Step 1: Create a java project in Eclipse and copy the whole matrixmt project.
Step 2: Add all the .jar  files from lib folder to the build path 
Step 3: Clean and build the project

Making changes to TestAllMethods.java file :
-----------------------------------------------------------
Step 1: Pick a method you want to test and find the method name inside the .java file. E.g for Add method from matrix class there is a ‘add() method’ inside TestAllMethods.java file
Step 2: Make changes inside the method. E.g.  If you want to test branch test cases in the Add() method, uncomment the branch part of the code and comment out the current test case. And also  uncomment the branch part in the MR cases in the Switch condition and comment out the current statements there.
Step 3: Save the changes and then clean and build the project.  
Step 4: Copy TestAllMethods.class file from the ‘bin’ folder to the ‘build’ folder. Then the build folder is ready to execute Metamorphic testing for that modified method of the subject program



