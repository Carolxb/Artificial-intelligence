/* * 
 * Name: Tianbo Liu
 * NetID: tliu36
 * Email: tliu36@u.rochester.edu
 * Project 2
 *
 * Name: Hanrui Liu
 * NetID: hliu60
 * Email: hliu60@u.rochester.edu
 * Project 2
 * 
 *
 * Name: Xubin Lou
 * NetID: xlou5
 * Email: xlou5@u.rochester.edu
 * Project 2
 */


Our group wrote four classes for the projects 2, below are file names and which part they belong to:
1. Clause.java
   the class that we build for the Object Clause
2. ModelChecking.java
   the class mainly for part 2
3. SatisfiabilityTesting.java
   the class mainly for part 3 with concise informative messages printed in the console & part 1's readCNF() method
4. Detail_Informed_Version.java
   this class is ALMOST THE SAME as SatisfiableTesting.java, which also for part 3, but with more detailed printed messages that inform users about the algorithm and the processes our program are doing now


For the ModelChecking.java, [part 2]
we built the three questions right in the main method by stating the variables in comment and have converted by hand into simple "and" and "or" statements. So users could directly run this class in Eclipse and the result would shown with informative messages in the console.


For SatisfiabilityTesting.java, [part 3] (change the file address before running)
We also built the three questions in the main method. Users should input the question they want to test and MAX-FLIP and MAX-TRIES numbers in the console respectively before seeing the result; We provide the suggested values for MAX-FLIP and MAX-TRIES in the console, and the default values are also provided. 

For question 1, users could merely run the program and type in the proper values in the console.

For question 2, we used the readCNF() method in part 1, which is to read sentences from CNF files; so before running this part, users should change the content of String "n_queensFirstPart" in the main method (line 11) to change file address, and here is the TIP!! USERS ONLY NEED TO INCLUDE THE ADDRESS WITHOUT TYPING ANYTHING AFTER "nqueens_"!! (END by "...\\nquees_") Users are only required to input the N values of their nqueens problem in the console after the right suggestion. We have written the corresponding code to make sure our program could read the right file address.

For question 3, we also used the same method in part 1; so before running this part, users should also change the content of String "quinn" (line 12) and "zebra" (line 13) in the main method to change file address. The second file/question will be run after the result of the first file/question is done, and there will be corresponding instructions to run the second file.

