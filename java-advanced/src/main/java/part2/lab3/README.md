## Laboratory Training № 3 "Advanced Features of Working with Files"



## 3.1 Individual Task

Create a new Maven project and transfer to this project the previously created classes that represent entities of the individual tasks of [Laboratory training #3](https://github.com/InessaRepeshko/fundamentals-of-java-programming/blob/main/lab3) and [Laboratory training #4](https://github.com/InessaRepeshko/fundamentals-of-java-programming/blob/main/lab4) of the "Fundamentals of Java Programming" course. Create derived classes in which to override the implementation of all methods related to processing sequences through the use of Stream API tools. If the class representing the second entity has no sequence processing, the base class can be used.

The program must demonstrate:

* reproduction of the implementation of individual tasks of laboratory training [# 3](https://github.com/InessaRepeshko/fundamentals-of-java-programming/blob/main/lab3) and [#4](https://github.com/InessaRepeshko/fundamentals-of-java-programming/blob/main/lab4) of the course "Fundamentals of Java Programming";
* using Stream API tools for processing and outputting sequences;
* outputting data to a text file using Stream API with subsequent reading; 
* serialization of objects into an XML file and a JSON file and corresponding deserialization using the XStream library; 
* recording events related to program execution in the system log; 
* testing individual classes using JUnit.

To present the individual data, you should use the classes that were created during the implementation of the individual assignment of laboratory training #1 of this course.

Note: localization and translation of texts can be carried out at the wish of the student.

The terms of the individual assignment for variant No. 24:

* Entities:


| Variant No. | First Entity   | Obligatory Fields of First Entity | Second Entity | Obligatory Fields of Second Entity | Main Assignment: Find and show the following data:                                                                  |
|:-----------:| :------------- | :-------------------------------- | :------------ | :--------------------------------- | :------------------------------------------------------------------------------------------------------------------ |
|     24      | Subway station | Name, year of opening	           | Hour          | Count of passengers, comments	    | Total count of passengers, hours with the minimum count of passengers, hours the maximum count of words in comments |

* Sorting criteria:


  | Variant No. | First Criterion of Sorting                   | Second Criterion of Sorting                 |
  |:-----------:| :------------------------------------------- | :------------------------------------------ |
  |     24      | By count of passengers (in decreasing order) | By length of comments (in decreasing order) |



## 3.2 List of Files of all Subdirectories

Enter the name of a specific folder. Display the names of all files in this directory, as well as all files in subdirectories, their subdirectories, and so on. Implement two approaches:

* search by means of a ```java.io.File``` class using a recursive function;
* search by means of the ```java.nio.file``` package.

Display both results sequentially on the screen. If the folder does not exist, display an error message.



## 3.3 Working with Text Files using the Stream API

Use the ```Files.lines()``` function to read strings from a text file, sort by increasing length, and output strings that contain the letter "a" to another file.



## 3.4 Creating files and Reading from Files Data About the Student and the Academic Group

Create the classes "Student" and "Academic group" (with array of students as the field). Create objects. Provide file creation and reading from files using the following approaches:

* using Stream API tools for working with text files;
* serialization and deserialization in XML and JSON (by means of XStream).



## 3.5 Working with the org.json Library (Additional Task)

Complete task 3.4 using tools for working with JSON files of the org.json library.



## 3.6 Use of SAX and DOM Technologies (Additional Task)

Prepare an XML document with data about the students of the academic group. Using SAX technology read data from an XML document and display data on the console. Using DOM technology, read data from the same XML document, modify the data, and write it to a new document.



## 3.7 Control tasks (Exercises)

### Control task 1

Read floating point values from a text file (up to the end of the file), find their sum, and output to another text file. Apply Stream API facilities.

### Control task 2

Read integer values from a text file, replace negative values with modules, positive values with zeros, and output the received values to another text file. Apply Stream API facilities.

### Control task 3

Read integer values from a text file,divide even elements by 2, increase odd ones by 2 times and output the received values to another text file. Apply Stream API facilities.



## Examples of program results

The results of the programs are available in the report at the link:

* [lab3-report.pdf](https://github.com/InessaRepeshko/advanced-java-programming/tree/main/reports/RepeshkoIV-CS222a-Lab3.pdf)



© Inessa Repeshko. 2024
