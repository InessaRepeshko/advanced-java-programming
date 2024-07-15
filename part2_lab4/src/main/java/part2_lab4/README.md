## Laboratory Training № 4 "Development of GUI Applications"



## 4.1 Individual Task

It is necessary to implement in Java language with the help of JavaFX tools the graphical user interface application, in which the data processing of individual tasks of previous laboratory trainings is carried out. The main window should contain a menu in which you need to implement the following functions:

* creating a new data set; 
* loading data from an XML document for editing; 
* storing the changed data in an XML document; 
* search according to the criteria specified in the [Laboratory training # 3](https://github.com/InessaRepeshko/fundamentals-of-java-programming/blob/main/lab3) of the course "Fundamentals of Java Programming"; 
* sorting according to the criteria specified in the [Laboratory training # 4](https://github.com/InessaRepeshko/fundamentals-of-java-programming/blob/main/lab4)  of the course "Fundamentals of Java Programming"; 
* showing "About" window with short data about the program and the author.

In the left part of the window, you should arrange the boxes for entering the scalar data, the display area for the search results, and the buttons that provide the basic functions of the program. In the middle of the window, you should place a table to display and edit the data.

The terms of the individual assignment for variant No. 24:

* Entities:


| Variant No. | First Entity   | Obligatory Fields of First Entity | Second Entity | Obligatory Fields of Second Entity | Main Assignment: Find and show the following data:                                                                  |
|:-----------:| :------------- | :-------------------------------- | :------------ | :--------------------------------- | :------------------------------------------------------------------------------------------------------------------ |
|     24      | Subway station | Name, year of opening	           | Hour          | Count of passengers, comments	    | Total count of passengers, hours with the minimum count of passengers, hours the maximum count of words in comments |

* Sorting criteria:


  | Variant No. | First Criterion of Sorting                   | Second Criterion of Sorting                 |
  |:-----------:| :------------------------------------------- | :------------------------------------------ |
  |     24      | By count of passengers (in decreasing order) | By length of comments (in decreasing order) |



## 4.2 Using ObservableList

Create a list (```ObservableList```) of real numbers of type ```Double```. Change the initial order of numbers so that positive numbers are placed first (without changing their relative order), and then negative numbers in the opposite order. Each change in the state of the list should result in the output of the list items to the console window.



## 4.3 Mini Calculator

Create a graphical user interface application, which, after entering numbers in two boxes of ```TextField``` type, performs one of four arithmetic operations (depending on the radio button selected). The result should be displayed in a different text field.



## 4.4 Dictionary (Advanced Task)

Develop a GUI application for viewing the words of a small English-Ukrainian (English-Russian, etc.) dictionary. Implement search functions for words, adding new words. Use ```Map``` to store data.



## 4.7 Control tasks (Exercises)

### Control task 1

Create a graphical user interface application in which user enters two strings in two text fields, and after clicking the button receives the concatenated string in the third text filed.



## Examples of program results

The results of the programs are available in the report at the link: 

* [lab4-report.pdf](https://github.com/InessaRepeshko/advanced-java-programming/tree/main/reports/RepeshkoIV-CS222a-Lab4.pdf)



© Inessa Repeshko. 2024
