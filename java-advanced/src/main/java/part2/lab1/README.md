## Laboratory Training № 1 "Working with Big Numbers and Data Sets"



## 1.1 Individual Task

Design and implement classes to represent the entities of the [Laboratory training #3](https://github.com/InessaRepeshko/fundamentals-of-java-programming/blob/main/lab3) of the course "Fundamentals of Java Programming". The solution should be based on the previously created class hierarchy.

You should create a derived class that represents the base entity. The class created in the [Laboratory training #4](https://github.com/InessaRepeshko/fundamentals-of-java-programming/blob/main/lab4) of the course "Fundamentals of Java Programming" should be used as base class. You should use a class that represents a sequence as a list. Create derived classes in which to override the implementation of all methods related to sequence processing through the use of Stream API tools. If the class representing the second entity has no sequence processing, the class can be left unmodified.

The program must demonstrate:

* reproduction of the functionality of laboratory trainings [No. 3](https://github.com/InessaRepeshko/fundamentals-of-java-programming/blob/main/lab3) and [No. 4](https://github.com/InessaRepeshko/fundamentals-of-java-programming/blob/main/lab3) of the course "Fundamentals of Java Programming";
* using Stream API tools for all sequence processing and output functions;
* testing methods of individual classes using JUnit.

The terms of the individual assignment for variant No. 24:

* Entities:


| Variant No. | First Entity   | Obligatory Fields of First Entity | Second Entity | Obligatory Fields of Second Entity | Main Assignment: Find and show the following data:                                                                  |
|:-----------:| :------------- | :-------------------------------- | :------------ | :--------------------------------- | :------------------------------------------------------------------------------------------------------------------ |
|     24      | Subway station | Name, year of opening	           | Hour          | Count of passengers, comments	    | Total count of passengers, hours with the minimum count of passengers, hours the maximum count of words in comments |

* Sorting criteria:


  | Variant No. | First Criterion of Sorting                   | Second Criterion of Sorting                 |
  |:-----------:| :------------------------------------------- | :------------------------------------------ |
  |     24      | By count of passengers (in decreasing order) | By length of comments (in decreasing order) |



## 1.2 Finding an Integer Power

Write a program that fills a number of type ```BigInteger``` with random digits and calculates the integer power of this number. For the result, use ```BigInteger``` type. Implement two ways: using a ```pow()``` method and a function that provides multiplication of long integers. Compare the results.

Provide testing of class methods using JUnit.



## 1.3 Filtering and Sorting

Create a list of objects of type ```BigDecimal```. Fill the list with random values. Sort by decreasing absolute value. Find the product of positive numbers. Implement three approaches:

* using loops and conditional statements (without facilities added in Java 8);
* without explicit loops and branches, using functions that have been defined in the Java Collection Framework interfaces since Java 8;
* using Stream API tools.

Provide testing of classes using JUnit.



## 1.4 Finding all Divisors (Advanced Task)

Use the Stream API to organize a search for all divisors of a positive integer. Create a separate static function that accepts an integer and returns an array of integers. Inside the function create an ```IntStream``` object. Apply ```range()``` function and filter. Do not use explicit loops.



## 1.5 Control tasks (Exercises)
### Control task 1

Implement the function of obtaining the integer part of the square root of a number of type ```BigInteger```.



## Examples of program results

The results of the programs are available in the report at the link:

* [lab1-report.pdf](https://github.com/InessaRepeshko/advanced-java-programming/tree/main/reports/RepeshkoIV-CS222a-Lab1.pdf)



© Inessa Repeshko. 2024
