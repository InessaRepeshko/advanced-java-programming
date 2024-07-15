## Laboratory Training № 2 "Working with Dates and Text. Localization"



## 2.1 Individual Task

Design and implement classes to represent the entities of the [Laboratory training #3](https://github.com/InessaRepeshko/fundamentals-of-java-programming/blob/main/lab3) of the course "Fundamentals of Java Programming". The solution should be based on the previously created class hierarchy.

You should create a derived class that represents the first entity. How to basically use the class created in the [Laboratory training #1](https://github.com/InessaRepeshko/advanced-java-programming/blob/main/java-advanced/src/main/java/part2/lab1) of the course "Advanced Java Programming". The class should implement the support of various localizations, in particular, Ukrainian and American. It is necessary to provide for the translation of text, the output of numbers, as well as dates and times, taking into account different localizations. Add (modify) search for words in comments (or other text) using regular expressions. Sort the entities alphabetically using the ```Collator``` class.

Create a derived class from the class that represents the second entity. Add the time and date when a certain event occurs as a field. To represent the time and date, use classes of ```java.time``` package(taking into account the time zone). Calculate the time intervals between events and find and output the smallest of the intervals. If the class that represented the second entity of the individual task did not contain a ```String``` type field, you should add a field, e.g. a comment to the event. For a new (or existing) text field, provide for the possibility of output in Ukrainian or English, depending on localization.

The program must demonstrate:

* presentation of the implementation of tasks of laboratory trainings [# 3](https://github.com/InessaRepeshko/fundamentals-of-java-programming/blob/main/lab3) and [#4](https://github.com/InessaRepeshko/fundamentals-of-java-programming/blob/main/lab4) of the course "Fundamentals of Java Programming";
* formatting numerical data in various ways, as well as taking into account localization;
* output of data about dates and times of events taking into account localization;
* output of comments in Ukrainian and English;
* the ability to sort alphabetically using a class ```Collator```;
* calculation and output of time intervals between events related to the second essence of the task; 
* finding and output the smallest of the intervals;
* variants of complex search in the text (using regular expressions), in particular, searching for a fragment of text at the beginning (at the end) of a word.

The terms of the individual assignment for variant No. 24:

* Entities:


| Variant No. | First Entity   | Obligatory Fields of First Entity | Second Entity | Obligatory Fields of Second Entity | Main Assignment: Find and show the following data:                                                                  |
|:-----------:| :------------- | :-------------------------------- | :------------ | :--------------------------------- | :------------------------------------------------------------------------------------------------------------------ |
|     24      | Subway station | Name, year of opening	           | Hour          | Count of passengers, comments	    | Total count of passengers, hours with the minimum count of passengers, hours the maximum count of words in comments |

* Sorting criteria:


  | Variant No. | First Criterion of Sorting                   | Second Criterion of Sorting                 |
  |:-----------:| :------------------------------------------- | :------------------------------------------ |
  |     24      | By count of passengers (in decreasing order) | By length of comments (in decreasing order) |



## 2.2 Date Entering

Implement a program in which the user enters a string. The program checks whether the string corresponds to the date representation accepted in Ukraine. The regular expressions must be used for checking. If the string does not meet the requirements, an error message is displayed. Otherwise, objects of types ```Date```, ```GregorianCalendar``` and ```LocalDate``` are created and output to the console.



## 2.3 Phone Number Verification

Develop a program to verify the correctness of the fact that the string is the telephone number of the Kyivstar operator. Use regular expressions.



## 2.4 Checking the Password String

Develop a program for checking password compliance with the requirements:

* the password can contain letters of the Latin alphabet, numbers and special symbols: _ - *;
* there must be at least one lowercase letter;
* there must be at least one capital letter;
* must be at least one digit;
* there must be at least one special character.

Use regular expressions.


## 2.5 Obtaining an Array of Substrings (Advanced Task)

A string longer than 20 characters contains letters and numbers. Get from this string an array of substrings that contain letters between numbers (groups of numbers). Define numbers as separators.



## 2.6 Control tasks (Exercises)
### Control task 1

Display information about all time zones.



## Examples of program results

The results of the programs are available in the report at the link: 

* [lab2-report.pdf](https://github.com/InessaRepeshko/advanced-java-programming/tree/main/reports/RepeshkoIV-CS222a-Lab2.pdf)



© Inessa Repeshko. 2024
