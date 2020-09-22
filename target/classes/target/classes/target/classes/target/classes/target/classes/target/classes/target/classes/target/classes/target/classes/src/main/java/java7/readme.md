# Java 7 was released on 28th July 2011

### Java Type Inference for Generics
In Java 7, Java provides improved compiler which is enough smart to infer the type of generic instance. 
Now, you can replace the type arguments with an empty set of type parameters (<>). 
This pair of angle brackets is informally called the diamond.


### Java Numeric Literals with Underscore
Java 7 allows you to use underscore in numeric literals. 
To separate groups of digits in numeric literals, which can improve the readability of your source code.
The following points are considerable:

* You cannot use underscore at the beginning or end of a number.
Ex. int a = _10; // Error, this is an identifier, not a numeric literal  
Ex. int a = 10_; // Error, cannot put underscores at the end of a number  
* You cannot use underscore adjacent to a decimal point in a floating point literal.
Ex. float a = 10._0; // Error, cannot put underscores adjacent to a decimal point  
Ex. float a = 10_.0; // Error, cannot put underscores adjacent to a decimal point  
* You cannot use underscore prior to an F or L suffix
Ex. long a = 10_100_00_L; // Error, cannot put underscores prior to an L suffix  
Ex. float a = 10_100_00_F; // Error, cannot put underscores prior to an F suffix  
* You cannot use underscore in positions where a string of digits is expected.

### JDBC Improvements
JDBC (Java Database Connectivity) provides universal data access from the Java programming language.
You can access any data from database, spreadsheets or flat files by using JDBC.

In Java 7, Java has introduced the following features:

1) It provides the ability to use a try-with-resources statement to automatically close resources of type Connection, ResultSet, and Statement.

2) RowSet 1.1: The introduction of the RowSetFactory interface and the RowSetProvider class, which enable you to create all types of row sets supported by your JDBC driver.