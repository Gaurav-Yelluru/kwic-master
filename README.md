Java implementation of Key Word in Context (KWIC)

This project was developed using Pair Programming technique by [Amrut Prabhu](https://github.com/amrut-prabhu) and [Muhammad Nur Kamal B M A](https://github.com/MuhdNurKamal).
The objective of this project is to understand different ways of decomposing a system into modules, and the pros and cons of each solution.

The program takes 3 command line arguments:
1. The KWIC implementation to be used- 1 for Shared Storage Implementation and 2 for ADT Implementation
2. The path to the corpus file (input file)
3. The keywords to be ignored in the KWIC output

Sample of the commands needed to compile and run the program:
```
javac com/company/Main.java  -Xlint:unchecked 
java com/company/Main 1 ../sampleInput/2.txt of man the 
java com/company/Main 2 ../sampleInput/1.txt
```
