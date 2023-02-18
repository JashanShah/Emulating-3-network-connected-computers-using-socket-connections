File Format:

AdvancedOSProject1
├── P1
│   └── Client1.java
├── P2
│   └── Client2.java
├── P3
│   └── Server.java
├── Makefile
└── README.md


In this project, the code is organized into three directories: P1, P2, and P3. 
The P1 and P2 directories contain the source code for the two clients, Client1 and Client2, 
respectively. The P3 directory contains the source code for Server.

The Makefile is included in the root directory of the project, and it can be used to build 
the project and run the programs. Running make will compile all the Java source code and 
create the necessary .class files.

After the make file runs, you need to go to P3 and do java Server, then go to P1 and do
java Client1, then go to P2 and do java Client2. This should run the code correctly.

Note: If the system prints out "An error has occured." Then you will need to change the ports in 
all java files and recompile and run the program.