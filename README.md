#Java-Application

This repository contains 3 applications each unique from the other. 
1) Twitter CLI App
    An application that allows user to post, view and delete a tweet.
    
2) JDBC App
    An application that allows user to create, read, update and delete (CRUD) a value in the postgreSQL database.
    
3) Java Grep
    An application that allows user to find all lines with a specified regex text within all the files of a directory. The lines are written and saved into an output file.


##Twitter CLI App
###Introduction
The Twitter CLI App is an application that can post, find and delete a tweet on twitter as along as the private and public keys are available.

###Usage
The public and private keys obtained in Twitter Developer account needs to be inputted in as environment variable.

Additionally, three arguments are inputted in order to use different functions of the application:

1) post "tweet" "latitude:longitude"
2) show tweet_id
3) delete tweet_id

eg.
1) post "tweet example" "-20:45"
Here, the application receives the command to post the tweet "tweet example" at the geo-location "-20:45"

2) show 123456789
Here, the application receives the command to print the tweet that was posted online at id=123456789. If the tweet does not exist with that id, then an error will occur.

3) delete 123456789
Here, the application receives the command to delete the tweet at id=123456789. If the tweet does not exist with that id, then an error will occur. If the tweet exists with that id, then the program deletes the tweet.
 
###Design and Implementation
A few libraries that were included in maven were:
Spring Framework - Spring boot starter parent - 2.1.3.RELEASE model
Oauth 1.0 and httpclient4
Jackson JSON
Spring
JDBC app
Mockito
JUnit

Fig 1. Twitter Architecture

<img src="images/twitter%20diagram1.png">

When the program was runned, TwitterCLIApp which contains the main argument executes DAO. The DAO is where it sets OAuth connection using private and public keys, and POST and GET was implemented. The DAO connects to Util and DTO for support. The output from DAO was used as input when connecting to Service where the program validates the input. Then the main argument performs post, show and delete functions.    

###Enhancements and Issues
The same tweet could not be tweeted at different times. In order to avoid this issue, each tweet is posted with the inclusion of the posted times timestamp. This way, the tweet is different each time it is posted. An improvement would be to make the program tweet the same tweet without needing to add additional information/text other than location.


##Java JDBC App
###Introduction
Java Database Connectivity (JDBC) application is an API which defines how a client may access a database. The JDBC connections support creation, deletion and execution statements like SQL's CREATE, INSERT, UPDATE, DELETE. This application was made with the primary focus of supporting Create, Read, Update, and Delete (CRUD).
 
###Usage
The JDBC application made does not have any input arguments. It was made by following the format and steps provided by https://www.lynda.com/Java-tutorials/Learning-JDBC/779748-2.html. Note that the PostgreSQL must start along with docker or else the program will not execute.

###Design and Implementation
An important library that needs to be installed within Maven is the JDBC driver.
 
 <dependency>
     <groupId>org.postgresql</groupId>
     <artifactId>postgresql</artifactId>
     <version>42.2.5</version>
 </dependency>

The purpose of using the JDBC driver is to setup a connection with the PostgreSQL database.

Fig. 2: JDBC Architecture

<img src="images/jdbc%20diagram.JPG">

When the program was run, it enters the JDBCexecutor where the main argument is in. The main argument calls out to ApacheDataSource which sets the database connection. The main then calls Customer object which implements DataAccessObject which extends interface ApacheTransferObject. The main then calls CustomerDAO which extends the interface ApacheTransferObject of type Customer. The CustomerDAO also implements the CRUD methods thus correctly executing the application. In order to use Advanced Data Access techniques, the codes used to implement Customer and CustomerDAO are commented out and a new classes are implemented. Object Order is implemented replacing Customer, that is, it has the same function as Customer and implements DataAccessObject which extends from the interface ApacheTransferObject. The OrderDAO, similar to Order, replaces CustomerDAO  while still performing the same function. That is, it extends ApacheTransferObject of type Order and implements CRUD. Despite it being the same function, there is one primary difference. Both Order and OrderDAO call for object OrderLine.  

###Enhancements and Issues
It needs to be noted that postgreSQL should be started in order for the application to work. If it is not started, then the compile returns errors and the error message points to getConnetion() command that synchronizes with the database.  


##Java Grep App
###Introduction
The java grep app searches for  a text pattern within a directory and output the lines with matched text into a file name "grep.out."

###Usage
The application takes in three arguments and uses them to perform its function.

Arguments:
regex rootPath outFile
eg. 
.*data.* /users/jrvs/bootcamp/input /tmp/grep.out

Here, the program will scan the files located in rootPath -> /users/jrvs/bootcamp/input for regex -> data in the form of a word or part of a word. The lines with the matched text will be written into outFile -> /tmp/grep.out.
 
###Design and Implementation
The pseudo code involved in controlling the entire application was given within the documentation. Additionally, the interface involved was already provided. All that was left was to implement the entire application following the pseudo code and interface.

Pseudo Code:
matchedLines = [] 
for file in listFiles(rootDir)  
    for line in readLines(file)
          if containsPattern(line)
                  matchedLines.add(line)
writeToFile(matchedLines)

Interface:
Same as src/main/java/ca/jrvs/apps/grep/JavaGrep

The auto-generate function within intellij was used to create all the methods from the interface.
In pseudo code:
The first for loop starts from the first file in the directory to the last file in the directory. Within that loop, a second for loop was used to read lines from first line to last line within the file. If the line contains the matched text, it adds the line into the array. After the end of the last file, that is, after the program exits the first for loop, the array was written into a file.

###Enhancements and Issues
Functionally speaking, the application runs perfectly.
An issue that was noted was that the output file was not completely labeled. That is, the lines that were written into the file could not be determined from which file it came from, thus anyone analyzing the contents will find it difficult to find the origin of the line.
A solution would be to create a new paragraph while application enters a new file. It should also be possible to write the origin file name of the line before the paragraph. 
