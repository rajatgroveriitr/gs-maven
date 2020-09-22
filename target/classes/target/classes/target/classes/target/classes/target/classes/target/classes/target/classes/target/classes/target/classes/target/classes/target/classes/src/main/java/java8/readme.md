# Java 8 was released on 18th March 2014

### 1. forEach() method in Iterable interface

Whenever we need to traverse through a Collection, we need to create an Iterator
whose whole purpose is to iterate over and then we have business logic in a loop for each of the elements in the Collection.
We might get `ConcurrentModificationException` if iterator is not used properly.

Java 8 has introduced forEach method in java.lang.Iterable interface so that while writing code we focus on business logic only.
forEach method takes java.util.function.Consumer object as argument, so it helps in having our business logic at a separate location that we can reuse.

forEach method helps in having the logic for iteration and business logic at separate place resulting in higher separation of concern and cleaner code.

### 2. default and static methods in Interfaces

forEach method is defined in Iterable interface but we know that interfaces can’t have method body. 
From Java 8, interfaces are enhanced to have method with implementation. 
We can use default and static keyword to create interfaces with method implementation.

We know that Java doesn’t provide multiple inheritance in Classes because it leads to Diamond Problem. 
So how it will be handled with interfaces now, since interfaces are now similar to abstract classes. 
The solution is that compiler will throw exception in this scenario and 
we will have to provide implementation logic in the class implementing the interfaces.

Java 8 uses default and static methods heavily in Collection API and default methods are added so that our code remains backward compatible.
An interface and an abstract class is almost similar except that you can create constructor in the abstract class whereas you can't do this in interface.

If any class in the hierarchy has a method with the same signature, then default methods become irrelevant. 
Since any class implementing an interface already has Object as a superclass, 
if we have equals(), hashCode() default methods in the interface, it will become irrelevant. 
That’s why for better clarity, interfaces are not allowed to have Object default methods.

### 3. Functional Interfaces

An interface with exactly one abstract method becomes Functional Interface. 
We don’t need to use `@FunctionalInterface` annotation to mark an interface as Functional Interface. 
`@FunctionalInterface` annotation is a facility to avoid accidental addition of abstract methods in the functional interfaces. 
You can think of it like `@Override` annotation and it’s best practice to use it. 
`java.lang.Runnable` with single abstract method `run()` is a great example of functional interface.

Java provides predefined functional interfaces to deal with functional programming by using lambda and method references.
`BiConsumer<T,U>` `Consumer<T>` `Function<T,R>` `Predicate<T>` `BiFunction<T,U,R>` `BinaryOperator<T>` `BiPredicate<T,U>` `BooleanSupplier` `Supplier<T>` etc. are placed in `java.util.function` package.

One of the major benefits of functional interface is the possibility to use lambda expressions to instantiate them.
Since functional interfaces have only one method, lambda expressions can easily provide the method implementation.
We just need to provide method arguments and business logic.

### 4. Lambda Expressions

So lambda expressions are a means to create anonymous classes of functional interfaces easily. 
There are no runtime benefits of using lambda expressions.

With lambda expression the amount of code is reduced, we can benefit from the Stream API sequential and parallel operations.

#### Method and Constructor References 

If you are surprised with the double colon (::) operator, it’s introduced in Java 8 and used for method references. Java Compiler takes care of mapping the arguments to the called method. It’s short form of lambda expressions 

A method reference is used to refer to a method without invoking it; a constructor reference is similarly used to refer to a constructor without creating a new instance of the named class or array type.

Reference to a Static Method <br>
`System::getProperty` Class::staticMethod

Reference to an Instance Method <br>
`"abc"::length` object::instanceMethod <br>
`System.out::println`

Reference to an Instance Method of an Object of a Particular Type <br>
`String::isEmpty` Class::instanceMethod

Reference to a Constructor <br>
`ArrayList::new` Class::new <br>
`int[]::new`

### 5. Java Stream API for Bulk Data Operations on Collections
A new `java.util.stream` has been added in Java 8 to perform filter/map/reduce like operations with the collection. 
Stream provides following features:

* Stream does not store elements. It simply conveys elements from a source such as a data structure, an array, or an I/O channel, through a pipeline of computational operations.
* Stream is functional in nature. Operations performed on a stream does not modify it's source. For example, filtering a Stream obtained from a collection produces a new Stream without the filtered elements, rather than removing elements from the source collection.
* Stream is lazy and evaluates code only when required.
* The elements of a stream are only visited once during the life of a stream. Like an Iterator, a new stream must be generated to revisit the same elements of the source.

Collection interface has been extended with `stream()` and `parallelStream()` default methods to get the Stream for sequential and parallel execution.

Parallel processing values are not in order, so parallel processing will be very helpful while working with huge collections.

Collectors is a final class that extends Object class. It provides reduction operations, such as accumulating elements into collections, summarizing elements according to various criteria, etc.

#### Optional&lt;T>
It is a public final class in `java.util` package and used to deal with `NullPointerException` that we frequently encounters if we do not perform null checks in our code. 


### 6. Java Time API

It has always been hard to work with Date, Time and Time Zones in java. 
`java.time` package that will streamline the process of working with time in java.
It has some sub-packages `java.time.format` that provides classes to print and parse dates and times 
and `java.time.zone` provides support for time-zones and their rules.

The new Time API prefers enums over integer constants for months and days of the week. 
One of the useful class is `DateTimeFormatter` for converting DateTime objects to strings.

It is JSR-310 implementation. It is designed to overcome all the flaws in the legacy date time implementations.
Immutability, Separation of Concerns, Clarity, Utility operations, Extendable

`LocalDate` is an immutable class that represents Date with default format of yyyy-MM-dd. We can use now() method to get the current date. 
We can also provide input arguments for year, month and date to create LocalDate instance. 
This class provides overloaded method for now() where we can pass ZoneId for getting date in specific time zone.

`LocalTime` is an immutable class whose instance represents a time in the human readable format. It’s default format is hh:mm:ss.zzz. 
Just like LocalDate, this class provides time zone support and creating instance by passing hour, minute and second as input arguments.

`LocalDateTime` is an immutable date-time object that represents a date-time, with default format as yyyy-MM-dd-HH-mm-ss.zzz. 
It provides a factory method that takes LocalDate and LocalTime input arguments to create LocalDateTime instance. 

If we provide invalid arguments for creating Date/Time, then it throws java.time.DateTimeException that is a RuntimeException, so we don’t need to explicitly catch it.
we can get Date/Time data by passing ZoneId, you can get the list of supported ZoneId values from it’s javadoc.

`Instant` class is used to work with machine readable time format, it stores date time in unix timestamp.

There are some other utility methods for adjusting the date using TemporalAdjuster and to calculate the period between two dates.


### 7. Collection API improvements

We have already seen forEach() method and Stream API for collections. 
Some new methods added in Collection API are:

* Iterator default method `forEachRemaining(Consumer action)` to perform the given action for each remaining element until all elements have been processed or the action throws an exception.
* `Collection` default method `removeIf(Predicate filter)` to remove all of the elements of this collection that satisfy the given predicate.
* `Collection` `spliterator()` method returning Spliterator instance that can be used to traverse elements sequentially or parallel.
* `Map` `replaceAll()`, `compute()`, `merge()` methods.
* Performance Improvement for `HashMap` class with Key Collisions

### 8. Concurrency API improvements

Some important concurrent API enhancements are:

* `ConcurrentHashMap` `compute()`, `forEach()`, `forEachEntry()`, `forEachKey()`, `forEachValue()`, `merge()`, `reduce()` and `search()` methods.
*  `CompletableFuture` that may be explicitly completed (setting its value and status).
* `Executors` `newWorkStealingPool()` method to create a work-stealing thread pool using all available processors as its target parallelism level.

### 9. Java IO improvements

Some IO improvements known to me are:

* `Files.list(Path dir)` that returns a lazily populated `Stream`, the elements of which are the entries in the directory.
* `Files.lines(Path path)` that reads all lines from a file as a `Stream`.
* `Files.find()` that returns a `Stream` that is lazily populated with Path by searching for files in a file tree rooted at a given starting file.
* `BufferedReader.lines()` that return a `Stream`, the elements of which are lines read from this `BufferedReader`.

### 10. Miscellaneous Core API improvements

Some misc API improvements that might come handy are:

1. `ThreadLocal` static method `withInitial(Supplier supplier)` to create instance easily.
2. `Comparator` interface has been extended with a lot of default and static methods for natural ordering, reverse order etc.
3. `min()`, `max()` and `sum()` methods in `Integer`, `Long` and `Double` wrapper classes.
4. `logicalAnd()`, `logicalOr()` and `logicalXor()` methods in `Boolean` class.
5. `ZipFile.stream()` method to get an ordered `Stream` over the ZIP file entries. 
    Entries appear in the Stream in the order they appear in the central directory of the ZIP file.
6. Several utility methods in `Math` class.
7. `jjs` command is added to invoke `Nashorn` Engine.
8. `jdeps` command is added to analyze class files
9. `JDBC`-`ODBC` Bridge has been removed. Also added some new features in JDBC 4.2.
    * Oracle does not support the JDBC-ODBC Bridge. Oracle recommends that you use JDBC drivers provided by the vendor of your database instead of the JDBC-ODBC Bridge.
    
10. `PermGen` memory space has been removed
11. New methods has added to `java.util.Arrays` package that use the JSR 166 Fork/Join parallelism common pool to provide sorting of arrays in parallel.
The methods are called `parallelSort()` and are overloaded for all the primitive data types and Comparable objects.
12. Type inference is a feature of Java which provides ability to compiler to look at each method invocation and corresponding declaration to determine the type of arguments.
    Java provides improved version of type inference in Java 8 with generic classes and methods.
    you can call specialized method without explicitly mentioning of type of arguments.
13. Parameter class provides information about method parameters `getParameterizedType()`, including its name `getName()` and modifiers. 
By default .class does not store parameters and returns argsN as parameter name, where N is a number of parameters in the method.
`-parameter` flag in the javac command is used to store parameters in the respective class file.
14. Java SE 8 , annotations can be applied to any type use. It means that annotations can be used anywhere you use a type like `Arrays<@NonNegative Integer> arr` 
15. Allows repeating annotations in source code. It is helpful to reuse annotation for the same class. You can repeat an annotation anywhere that you would use a standard annotation.
    For compatibility reasons, repeating annotations are stored in a container annotation that is automatically generated by the Java compiler. 
    In order for the compiler to do this, two declarations are required in code.
    * Declare a repeatable annotation type
    * Declare the containing annotation type
