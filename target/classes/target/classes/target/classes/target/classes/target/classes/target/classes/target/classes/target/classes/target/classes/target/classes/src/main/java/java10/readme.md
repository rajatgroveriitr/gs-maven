# Java SE 10 was released on March, 20th 2018
Java 10 has many features we may not use in everyday programming but it still has many features which work behind the scene to make it important milestone.

### Local Variable Type Inference
Java has been progressively working on reducing the verbosity from syntax. First it was Diamond operator, and now it is `var` to declare variables in java.  
It allows you to declare a local variable without specifying its type. 
The type of variable will be inferred from type of actual object created. 

Its only for local variables with the initializer. It cannot be used for member variables, method parameters, return types, etc – the initializer is required as without which compiler won't be able to infer the type.

var is not a keyword – this ensures backward compatibility for programs using var say, as a function or variable name. var is a reserved type name, just like int.

There is no runtime overhead in using var nor does it make Java a dynamically typed language. The type of the variable is still inferred at compile time and cannot be changed later.

#### Illegal Use of var

var won't work without the initializer:

	var n; // error: cannot use 'var' on variable without initializer

Nor would it work if initialized with null:

	var emptyList = null; // error: variable initializer is 'null'

It won't work for non-local variables:

	public var = "hello"; // error: 'var' is not allowed here

Lambda expression needs explicit target type, and hence var cannot be used:

	var p = (String s) -> s.length() > 10; // error: lambda expression needs an explicit target-type

Same is the case with the array initializer:

	var arr = { 1, 2, 3 }; // error: array initializer needs an explicit target-type

There are situations where var can be used legally, but may not be a good idea to do so. 
As the code could become less readable:

    var result = obj.prcoess(); //difficult to understand the type returned

It's best to avoid var is in streams with long pipeline
	
    var x = emp.getProjects.stream()
                .findFirst()
                .map(String::length)
                .orElse(0);

If we use it with the diamond operator

    var empList = new ArrayList<>(); //The type of empList will be ArrayList<Object>and not List<Object>
    var empList = new ArrayList<Employee>(); // If we want it to be ArrayList<Employee>, we will have to be explicit
    
Using var with non-denotable types could cause unexpected error.
For example, if we use var with the anonymous class instance

    var obj = new Object() {};

If we try to assign another Object to obj, we would get a compilation error because the inferred type of obj isn't Object.

    obj = new Object(); // error: Object cannot be converted to <anonymous Object>


### Time-Based Release Versioning

Starting from Java 10, Oracle has adapted time based version-string scheme. 
A new Java release every six months. The new format of the version number is:

    $FEATURE.$INTERIM.$UPDATE.$PATCH

Unlike the old releases, the new time-based releases will not be delayed and features will be released every six months, with no constraints on what features can go out in the releases.

    Version version = Runtime.version();
    version.feature();
    version.interim();
    version.update();
    version.patch();

There are Long Term Releases (LTS) for enterprise customers. 
LTS version of the products will offer premier and sustained support from Oracle and it will be targeted every 3 years. 
Also, updates for these releases will be available for at least three years.

This will cause “LTS” to be displayed prominently in the output of java –versions. e.g. e.g., 11.0.2+13-LTS

### Garbage-Collector Interface

In earlier JDK structure, the components that made up a Garbage Collector (GC) implementation were scattered throughout various parts of the code base. 
It’s changed in Java 10. Now, it is a clean interface within the JVM source code to allow alternative collectors to be quickly and easily integrated. 
It will improve source-code isolation of different garbage collectors.
It improves the code isolation of different garbage collectors by introducing a common Garbage Collector Interface.

This change provides better modularity to the Internal GC Code. 
It will help in the future for adding new GC without changing existing codebase, also help in removing or housekeeping of the previous GC.
This is purely refactoring. Everything that worked before needs to work afterwards, and performance should not regress.

### Parallel Full GC for G1

Java 9 made the Garbage-First Garbage Collector (G1GC) the default, replacing the Concurrent Mark-Sweep Garbage Collector (CMS). 
Java 10 introduces performance improvements to G1GC.

In Java 10, G1GC is getting a performance boost with the introduction of full parallel processing during a Full GC. 
This change won’t help the best-case performance times of the garbage collector, but it does significantly reduce the worst-case latencies. 
The mark-sweep-compact algorithm from G1 collector is parallelized as part of this change and will be triggered when concurrent threads for collection can’t revive the memory fast enough.
This makes pauses for garbage collection far less stressful on application performance.

When concurrent garbage collection falls behind, it triggers a Full GC collection. 
The performance improvement modifies the full collection so it is no longer single-threaded, which significantly reduces the time needed to do a full garbage collection.

The number of threads can be controlled by the `-XX:ParallelGCThreads` option.

### Heap Allocation on Alternative Memory Devices

Java is moving toward a more heterogeneous memory system by allowing users to specify alternative memory devices to allocate the heap.
Applications have become memory hungry, there’s an increase in cloud-native applications, in-memory databases, streaming applications. 
In order to cater to these services, there are various memory architectures available.
The goal of this change is to enable the HotSpot VM to allocate the Java object heap on an alternative memory device, such as an NV-DIMM, specified by the user.

This targets alternative memory devices that have the same semantics as DRAM, including the semantics of atomic operations, and can, therefore, be used instead of DRAM for the object heap without any change to existing application code.

To allocate the heap in such memory we can add a new option, `-XX:AllocateHeapAt=<path>`. 
This option would take a path to the file system and use memory mapping to achieve the desired result of allocating the object heap on the memory device. 
The existing heap related flags such as -Xmx, -Xms, etc., and garbage-collection related flags would continue to work as before.

### Consolidate the JDK Forest into a Single Repository

As part of this change numerous repositories of the JDK forest is combined into a single repository in order to simplify and streamline development.

In JDK 9 there are eight repos: `root`, `corba`, `hotspot`, `jaxp`, `jaxws`, `jdk`, `langtools`, and `nashorn`. 
In the consolidated forests, code for Java modules is generally combined under a single top-level `src` directory. 
For example, today in the JDK forest there are module-based directories like

    $ROOT/jdk/src/java.base
    ...
    $ROOT/langtools/src/java.compiler
    ...

In the consolidated forest, this code is instead organized as-

    $ROOT/src/java.base
    $ROOT/src/java.compiler
    ...

### Application Class-Data Sharing

This feature helps in improving the startup footprint, extends the existing Class-Data Sharing (“CDS”) feature to allow application classes to be placed in the shared archive.

JVM while starting performs some preliminary steps, one of which is loading classes in memory. 
If there are several jars having multiple classes, then the lag in the first request is clearly visible. 
This becomes an issue with serverless architecture, where the boot time is critical. 
In order to bring down application startup time, Application class-data sharing can be used. 
The idea is to reduce footprint by sharing common class metadata across different Java processes. 
This can be achieved by the following 3 steps:

1. Determining the classes to archive: 
Use the java launcher to create a list of files to archive, this can be achieved by the following parameters:

        $java -Xshare:off -XX:+UseAppCDS -XX:DumpLoadedClassList=hello.lst -cp hello.jar HelloWorld

2. Creating the AppCDS archive: 
Use java launcher to create the archive of the list of files to be used for Application CDS, this can be achieved by following parameters:

        $java -Xshare:dump -XX:+UseAppCDS -XX:SharedClassListFile=hello.lst -XX:SharedArchiveFile=hello.jsa -cp hello.jar

3. Using the AppCDS archive: 
Use Java launcher with the following parameters to use Application CDS.

        $java -Xshare:on -XX:+UseAppCDS -XX:SharedArchiveFile=hello.jsa -cp hello.jar HelloWorld

### Additional Unicode Language-Tag Extensions

It’s goal is to enhance java.util.Locale and related APIs to implement additional Unicode extensions of BCP 47 language tags. Support for BCP 47 language tags was was initially added in Java SE 7, with support for the Unicode locale extension limited to calendars and numbers. This JEP will implement more of the extensions specified in the latest LDML specification, in the relevant JDK classes.

This JEP will add support for the following additional extensions:

    cu (currency type)
    fw (first day of week)
    rg (region override)
    tz (time zone)

Related APIs which got modified are:

    java.text.DateFormat::get*Instance
    java.text.DateFormatSymbols::getInstance
    java.text.DecimalFormatSymbols::getInstance
    java.text.NumberFormat::get*Instance
    java.time.format.DateTimeFormatter::localizedBy
    java.time.format.DateTimeFormatterBuilder::getLocalizedDateTimePattern
    java.time.format.DecimalStyle::of
    java.time.temporal.WeekFields::of
    java.util.Calendar::{getFirstDayOfWeek,getMinimalDaysInWeek}
    java.util.Currency::getInstance
    java.util.Locale::getDisplayName
    java.util.spi.LocaleNameProvider

### Root Certificates

The cacerts keystore, which is part of the JDK, is intended to contain a set of root certificates that can be used to establish trust in the certificate chains employed in various security protocols. The cacerts keystore in the JDK source code, however, is currently empty.

The cacerts keystore will be populated with a set of root certificates issued by the CAs of Oracle’s Java SE Root CA Program. A lot of vendors have already signed to the required agreement and, for each, a list of the root certificates that will be included. Those that do not sign an agreement will not be included at this time. Those that take longer to process will be included in the next release.

This will also mean that both Oracle & Open JDK binaries will be functionally the same. Critical security components such as TLS will work by default in OpenJDK builds going forward.

### Experimental Java-Based JIT Compiler

This feature enables the Java-based JIT compiler, **Graal**, to be used as an experimental JIT compiler on the Linux/x64 platform. 
Graal was introduced in Java 9. It’s an alternative to the JIT compiler which we have been used to. 
It’s a plugin to the JVM, which means that the JIT compiler is not tied to JVM and it can be dynamically plugged in and replaced with any another plugin which JVMCI compliant (Java-Level JVM Compiler Interface). 
It also brings Ahead of Time (AOT) compilation in java world. It also supports polyglot language interpretation.

To enable Graal as the JIT compiler, use the following options on the java command line:

    -XX:+UnlockExperimentalVMOptions -XX:+UseJVMCICompiler

Graal is a complete rewrite of the JIT compiler in Java from scratch. Previous JIT complier was written in C++.
he JVM can be compiled AOT and then JIT compiler can be used within JVM it for enhancing performance through live code optimization.

### Thread-Local Handshakes

This lays the groundwork for improved VM performance, by making it possible to execute a callback on application threads without performing a global VM safepoint. This would mean that the JVM could stop individual threads and not just all of them.

Thread-local handshakes will be implemented initially on x64 and SPARC. Other platforms will fall back to normal safepoints. A new product option, `-XX:ThreadLocalHandshakes` (default value `true`), allows users to select normal safepoints on supported platforms.

### Remove the Native-Header Generation Tool

It will remove the javah tool from the JDK, a separate tool to generate header files when compiling JNI code, as this can be done through javac.

This is another Java 10 feature which focuses on housekeeping.

### New Added APIs and Options

* `Optional.orElseThrow()` has been added to the Optional class. It is synonymous with and is now the preferred alternative to the existing get method.
which doesn't take any argument and throws NoSuchElementException if no value is present
* `List.copyOf`, `Set.copyOf`, and `Map.copyOf` These methods create new unmodifiable copy of the given Collection. 
Any attempt to modify such a collection would result in java.lang.UnsupportedOperationExceptionruntime exception.
* `java.util.stream.Collectors` get additional methods to collect a Stream into unmodifiable List, Map or Set.
`Collectors.toUnmodifiableList`, `Collectors.toUnmodifiableSet`, `Collectors.toUnmodifiableMap` These methods allow the elements of a Stream to be collected into an unmodifiable collection
Any attempt to modify such a collection would result in java.lang.UnsupportedOperationExceptionruntime exception.
* `--jdk.disableLastUsageTracking` To disable JRE last usage tracking for a running VM.
* `--add-stylesheet` Provides support for the use of multiple stylesheets in the generated documentation.
* `--main-stylesheet` To help distinguish the main stylesheet from any additional stylesheets.
* `@summary` Tag Added to explicitly specify the text used as the summary of the API description. By default, the summary of an API description is inferred from the first sentence.

### More
Deprecated Classes in `com.sun.security.auth.*`


--------------------------------------------------------------
