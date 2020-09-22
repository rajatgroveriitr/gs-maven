# Java SE 11 was released on September, 25th 2018 
Oracle has changed the licensing and support model which means if you download the Java 11 Oracle JDK, it will be paid for commercial use.
No need to pay unless you download the Oracle JDK and use it in production.

Java 10 was the last free Oracle JDK that could be downloaded.

While Oracle JDK is no longer free, you can always download the Open JDK builds from Oracle or other providers such as AdoptOpenJDK, Azul, IBM, Red Hat, etc.

### Running Java File with single command
One major change is that you don’t need to compile the java source file with `javac` tool first. 
You can directly run the file with `java` command and it implicitly compiles.

Note that the program cannot use any external dependencies other than the java.base module. 
And program can be only single-file program.

### New utility methods in String class
`isBlank()` : This instance method returns a boolean value. Empty Strings and Strings with only white spaces are treated as blank.

`lines()` : This method returns a stream of strings, which is a collection of all substrings split by lines.

`strip()` : Removes the white space from both, beginning and the end of string. 
It is “Unicode-aware” evolution of `trim()`. check the method `Character.isWhitespace(c)` to know if a unicode is whitespace or not.
Similarly `stripLeading()`, `stripTrailing()`

`repeat(int)` : The repeat method simply repeats the string that many numbers of times as mentioned in the method in the form of an int.
f this string is empty or count is zero then the empty string is returned.

### Collection.toArray(IntFunction)
    
Before Java 11, converting a collection to array was not straightforward. Java 11 makes the conversion more convenient.

### Optional.isEmpty()    
Optional is a container object which may or may not contain a non-null value. If no value is present, the object is considered empty.

Previously existing method `isPresent()` returns true if a value is present, otherwise false. Sometimes, it forces us to write negative conditions which are not readable.

`isEmpty()` method is reverse of `isPresent()` method and returns false if a value is present, otherwise true.

So we do not to write negative conditions in any case. Use any of these two methods when appropriate.

### Local-Variable Syntax for Lambda Parameters
In Java 10, Local Variable Type Inference was introduced. Thus we could infer the type of the variable from the Right Hand Side.
     
     var list = new ArrayList<String>();

JEP 323 allows var to be used to declare the formal parameters of an implicitly typed lambda expression.

    (var s1, var s2) -> s1 + s2

**But why is this needed when we can just skip the type in the lambda?**
If you need to apply an annotation just as `@Nullable`, you cannot do that without defining the type.

**Limitation of this feature** – You must specify the type var on all parameters or none.
    
    (var s1, s2) -> s1 + s2 //no skipping allowed
    (var s1, String y) -> s1 + y //no mixing allowed
    
    var s1 -> s1 //not allowed. Need parentheses if you use var in lambda.

### Nested Based Access Control
Before Java 11 if we use Java Reflection, accessing private method of the Nesting class will give an `IllegalStateException`.

Java 11 nested access control addresses this and `java.lang.Class` introduces three methods in the reflection API: `getNestHost()`, `getNestMembers()`, and `isNestmateOf()`.


### JEP 321: HTTP Client
Java 11 standardizes the Http CLient API. The new API supports both HTTP/1.1 and HTTP/2.

It is designed to improve the overall performance of sending requests by a client and receiving responses from the server. 

It also natively supports WebSockets. HTTP API can handle synchronous and asynchronous communication. 

Java had `HttpURLConnection` class for long time for HTTP communication. But over the time, requirements have gone complex and more demanding in applications. 
Before Java 11, developers had to resort to feature-rich libraries like Apache `HttpComponents` or `OkHttp` etc.

We saw Java 9 release to include an `HttpClient` implementation as an experimental feature. It has grown over time and now a final feature of Java 11. 
Now the Java applications can make HTTP communications without the need to any external dependency.

### Reading/Writing Strings to and from the Files
Using these overloaded methods, Java 11 aims to reduce a lot of boilerplate code which makes much easier to read and write files.

    readString()
    writeString()
    
### JEP 328: Flight Recorder
Flight Recorder which earlier used to be a commercial add-on in Oracle JDK is now open-sourced since Oracle JDK is itself not free anymore.

JFR is a profiling tool used to gather diagnostics and profiling data from a running Java application.
Its performance overhead is negligible and that’s usually below 1%. Hence it can be used in production applications.

### Remove the Java EE and CORBA Modules    
The modules were already deprecated in Java 9. They are now completely removed.

`java.xml.ws`, `java.xml.bind`, `java.activation`, `java.xml.ws.annotation`, `java.corba`, `java.transaction`, `java.se.ee`, `jdk.xml.ws`, `jdk.xml.bind` packages are removed.

### Dynamic Class-File Constants

The Java class-file format now extends support a new constant pool form, CONSTANT_Dynamic. 

The goal of this JEP is to reduce the cost and disruption of developing new forms of materializable class-file constraints, by creating a single new constant-pool form that can be parameterized with user-provided behavior.
This enhances performance

### Epsilon: A No-Op Garbage Collector
This is an experimental feature.    
Unlike the JVM GC which is responsible for allocating memory and releasing it, Epsilon only allocates memory.
It allocates memory for the following things:

* Performance testing.
* Memory pressure testing.
* VM interface testing.
* Extremely short lived jobs.
* Last-drop latency improvements.
* Last-drop throughput improvements.

Now Epsilon is good only for test environments. 
It will lead to `OutOfMemoryError` in production and crash the applications.

The benefit of Epsilon is no memory clearance overhead. 
Hence it’ll give an accurate test result of performance and we can no longer GC for stopping it.

### ChaCha20 and Poly1305 Cryptographic Algorithms

Java 11 provides ChaCha20 and ChaCha20-Poly1305 cipher implementations. These algorithms will be implemented in the SunJCE provider.

### Improve Aarch64 Intrinsics

Improve the existing string and array intrinsics, and implement new intrinsics for the java.lang.Math sin, cos, and log functions, on AArch64 processors.

### ZGC: A Scalable Low-Latency Garbage Collector (Experimental)

Java 11 has introduced a low latency GC. This is an experimental feature.
It’s good to see that Oracle is giving importance to GC’s.

### Deprecate the Nashorn JavaScript Engine

Nashorn JavaScript script engine and APIs are deprecated thereby indicating that they will be removed in the subsequent releases.











# Java SE 12 was released on March, 19th 2019

# Java SE 13 was released on September, 17th 2019 

# Java SE 14 was released on March, 17th 2020

# Java SE 15 was released on September, ??th 2020 