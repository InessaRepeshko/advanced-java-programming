## Laboratory Training № 6 "Metaprogramming. Multithreading"



## 6.1 Individual Task

Create a graphical user interface application that is designed for plotting arbitrary functions. The user enters the real values of a and b, the functions f(x) and g(x) in the form of strings corresponding to the Java syntax. The program calculates the function h(x) according to the individual task:

|Number of Variant | Function _h(x)_       | 
|:----------------:|:----------------------|
|        18        | _f(a / x) – g(b / x)_ |

After entering the necessary functions, the range of the graph display and pressing the corresponding button, the chart is plotted. You should also consider the function of clearing input lines and graphics.

To implement the processing of entered expressions, you must apply a dynamic code compilation. You should use JavaFX tools to create graphical user interface application. The recommended approach is to use the ```LineChart``` component.



## 6.2 Creating a GUI Application for Getting Prime Factors of Numbers

Implement JavaFX GUI application, in which the user inputs a range of numbers (from and to), and in the window the numbers and their prime factors are displayed. Realize the ability to pause, resume the stream, and also complete termination and re-calculation with new data.



## 6.3 Working with BlockingQueue

Create a console program in which one thread adds integers to the ```BlockingQueue``` queue, and the other calculates their arithmetic mean.



## 6.4 Interpretation of Mathematical Expressions

Create a console application that allows you to enter mathematical expressions, calculate and output the result. An expression can consist of constants, mathematical operations, and brackets. To implement the program, use the tools of the JavaScript language interpretation.

Note. The syntax of mathematical expressions of JavaScript is similar to Java. The result can be output using the ```print()``` function without creating additional variables.



## 6.5 Calculating π in a Separate Thread

Implement the program of calculation $`π`$ with precision up to a given $`ε`$ as the sum of a sequence:

```math
π = \frac{4}{1} - \frac{4}{3} + \frac{4}{5} - \frac{4}{7} + \frac{4}{9} - \frac{4}{11} + \frac{4}{13} - ...
```

The calculation should be done in a separate thread. When performing the calculation, you should give the user the opportunity to enter a request for the number of calculated elements of the sum.



## Examples of program results

The results of the programs are available in the report at the link: 

* [lab6-report.pdf](https://github.com/InessaRepeshko/advanced-java-programming/tree/main/reports/RepeshkoIV-CS222a-Lab6.pdf)



© Inessa Repeshko. 2024
