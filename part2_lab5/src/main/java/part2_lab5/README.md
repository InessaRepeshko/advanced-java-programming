## Laboratory Training № 5 "Reflection. Creation of Database Applications"



## 5.1 Creating a Database Console Application

Create a database console application for working with the entities of individual tasks of previous laboratory trainings.

Domain entity data must be stored in a relational database. The MySQL DBMS should be used (or another DBMS, upon agreement with the teacher). In the console application, you should create two database tables, respectively for the first and second entity of the individual task (first and second table).

After creating the tables, you must perform the following actions:

* import into the database from a JSON file; 
* display of both tables; 
* search by features defined in the [Laboratory training # 3](https://github.com/InessaRepeshko/fundamentals-of-java-programming/blob/main/lab3) of the course "Fundamentals of Java Programming"; 
* performing sorting according to the features defined in the [Laboratory training # 4](https://github.com/InessaRepeshko/fundamentals-of-java-programming/blob/main/lab4) of the course "Fundamentals of Java Programming"; 
* adding a new record to the table; 
* deleting certain records from the table; 
* export from the database to a JSON file.

The terms of the individual assignment for variant No. 24:

* Entities:


| Variant No. | First Entity   | Obligatory Fields of First Entity | Second Entity | Obligatory Fields of Second Entity | Main Assignment: Find and show the following data:                                                                  |
|:-----------:| :------------- | :-------------------------------- | :------------ | :--------------------------------- | :------------------------------------------------------------------------------------------------------------------ |
|     24      | Subway station | Name, year of opening	           | Hour          | Count of passengers, comments	    | Total count of passengers, hours with the minimum count of passengers, hours the maximum count of words in comments |

* Sorting criteria:


  | Variant No. | First Criterion of Sorting                   | Second Criterion of Sorting                 |
  |:-----------:| :------------------------------------------- | :------------------------------------------ |
  |     24      | By count of passengers (in decreasing order) | By length of comments (in decreasing order) |



## 5.2 View All Class Fields

Create a console application in which the user enters the class name and receives information about all the fields (including private and protected).



## 5.3 Creating a Database GUI Application (Advanced Task)

Use JavaFX tools for creation of graphical user interface application in which the data of individual tasks of previous laboratory works is processed. Data about domain area entities is stored in a relational database. The main window should contain a menu in which you need to implement the functions listed in task 5.1. Adding a new record to the table should be implemented in a separate window with control of the entered data.

In the left part of the window, you should place the display area for the search results, as well as the buttons that ensure the execution of the main functions of the program. Tables for data display should be placed in the middle part of the window.



## 5.4 Obtaining Data about Method Annotations (Advanced Task)

Create a console program in which the user enters the name of a class method and receives information about all the annotations with ```RUNTIME``` retention policy with which this method is marked.



## 5.5 Control tasks (Exercises)

### Control task 1

Create a console application in which the user enters the name of a class and receives information about all public fields of this class.

### Control task 2

Create the Student class by generating the necessary methods using Lombok tools.

### Control task 3

Create a Student class with read-only data by generating the necessary methods using Lombok tools.



## Examples of program results

The results of the programs are available in the report at the link: 

* [lab5-report.pdf](https://github.com/InessaRepeshko/advanced-java-programming/tree/main/reports/RepeshkoIV-CS222a-Lab5.pdf)



© Inessa Repeshko. 2024
