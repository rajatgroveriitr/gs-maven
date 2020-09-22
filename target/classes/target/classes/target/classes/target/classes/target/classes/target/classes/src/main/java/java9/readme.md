# Java 9 was released on 21st September 2017

### Java Platform Module System (Project Jigsaw)
It is a new kind of Java programing component that can be used to collect Java code (classes and packages). 
The main goal of this project is to easily scale down application to small devices. 
In Java 9, JDK itself has divided into set of modules to make it more lightweight. 
It also allows us to develop modular applications.

To reuse classes efficiently java has grouped them in packages and it is done in such a way so that similar type of classes are in a single package.

Packages are great way of organizing classes but there needs to be way to organize packages when we need to use several of them in our code.
 
“A module is a set of packages”. A module not only organizes packages, 
it also takes care of the accessibility so that the part of the module that we want to be reused, can be used and the part that we don’t want to be reused, cant be reused.

A Module is a self-describing collection of Code, Data, and some Resources. It is a set of related Packages, Types (classes, abstract classes, interfaces etc) with Code & Data and Resources. 
It support Single Responsibility (Functionality) Principle (SRP).

A “java.base” Module is known as Base Module, default module for all JDK Modules and User-Defined Modules. It’s an Independent module and does NOT dependent on any other modules. 
By default, all other Modules dependent on this module.

In Module Descriptor, we use “exports” clause to export a package(s) to outside world or to other Modules and use “requires” clause to import other Module’s packages.

A Module Descriptor can have only exports clause, no requires clause. That means it is exports it’s packages to other Modules and NOT depending on any other modules. 
It’s an Independent module.

A Module Descriptor can have both exports clause and requires clause. That means it is exports it’s packages to other Modules and also using other Module’s packages. 
It is depending on any other modules. It’s NOT an Independent module.

From Java 9 on-wards, We refer MODULEPATH instead of CLASSPATH. Of course, Java 9 supports both MODULEPATH and CLASSPATH.

### jlink: The Java Linker
When you have modules with explicit dependencies, and a modularized JDK, new possibilities arise. 
Your application modules now state their dependencies on other application modules and on the modules it uses from the JDK. 
Why not use that information to create a minimal runtime environment, containing just those modules necessary to run your application? 
That's made possible with the new jlink tool in Java 9. Instead of shipping your app with a fully loaded JDK installation, you can create a minimal runtime image optimized for your application.

jlink is a tool that can be used to assemble set of modules into a runtime image. 
It also allows us to assemble module's dependencies into the custom runtime image.

Link time is a phase between the compile and runtime, jlink works there for linking and assemble modules to runtime image.

### JShell: The Java Shell (REPL)
It is an interactive Java Shell tool, it allows us to execute Java code from the shell and shows output immediately. 
JShell is a REPL (Read Evaluate Print Loop) tool and run from the command line. 
It is benificial, if we want to test our business logic and get result immediately. 

### Interface Private Methods
In Java 9, we can create private methods inside an interface. 
Interface allows us to declare private methods that help to share common code between non-abstract methods.
Before Java 9, creating private methods inside an interface cause a compile time error.

### Try-With Resources
Java introduced try-with-resource feature in Java 7 that helps to close resource automatically after being used.

In other words, we can say that we don't need to close resources (file, connection, network etc) explicitly, try-with-resource close that automatically by using AutoClosable interface.

In Java 7, try-with-resources has a limitation that requires resource to declare locally within its block.
The main goal of this new statement is “Automatic Better Resource Management”.

### Java Collection Factory Methods
Factory methods are special type of static methods that are used to create unmodifiable instances of collections. 
It means we can use these methods to create list, set and map of small number of elements.

It is unmodifiable, so adding new element will throw `java.lang.UnsupportedOperationException`

`List.of()` instead of `Collections.unmodifiableList(list)`
`Set.of()` instead of `Collections.unmodifiableSet(hset)`
`Map.of()` instead of `Collections.unmodifiableMap(map)`

### Anonymous Classes Improvement
Java SE 7 has introduced Diamond Operator to avoid redundant code by leaving the generic type in the right side of the expression.
`List<String> myList = new ArrayList<>();`
It allowed to use diamond operator in normal classes but it didn’t allow to use them in anonymous inner classes.

Java 9 introduced a new feature that allows us to use diamond operator with anonymous classes. 
As long as the inferred type is denotable, we can use the diamond operator when we create an anonymous inner class.

### Stream API Improvement
In Java 9, Stream API has improved and four useful new methods added to `java.util.Stream` interface. 
As Stream is an interface, all those new implemented methods are default or static methods. 

`takeWhile()`, `dropWhile()`, `ofNullable()`, and one overloaded `iterate()` method are added to perform operations on stream elements. 

 * default `takeWhile()` returns all prefixed elements until they match Predicate condition. 
 When that Predicate returns false for first element, then it stops evaluation and returns that subset elements. 
 That Predicate is evaluated until that returns false for first time.
 * default `dropWhile()` first drops all prefixed elements until they match Predicate condition. 
 When that Predicate returns false for first element, then it stops evaluation and returns the rest of subset elements into resulted Stream.
 * static `iterate()` is similar to for-loop: First parameter is init value, next parameter is condition and final parameter is to generate next element.
 `Java SE9's iterate = Java SE8's (iterate + filter)`
 * static `ofNullable()` returns a sequential Stream containing a single element, if non-null, otherwise returns an empty Stream.

### Optional Class Improvements

In Java SE 9, Oracle Corp has introduced the following three methods to improve Optional functionality.

* `stream()` to work on Optional objects lazily 
* `ifPresentOrElse()` combines all those methods like ifPresent(), isPresent() and orElse() methods in a nice way.
If a value is present, performs the given action with the value, otherwise performs the given empty-based action.
* `or()` is used to return a value, if Optional contains a value, otherwise returns a value specified in the Supplier. 
     
### Java Process API Improvement
Java has improved its process API in Java 9 version that helps to manage and control operating system processes.

In earlier versions, it was complex to manage and control operating system processes by using Java programming. 
Now, new classes and interfaces are added to perform this task.

* New methods are added to the `java.lang.Process` class
* New interfaces `ProcessHandle` and `ProcessHandle.Info` are added

### More Concurrency Updates
More concurrency updates are added to JDK 9. 
These updates are improved CompletableFuture API and interoperable publish-subscribe framework.
To support some delays and timeouts, some utility methods and better sub-classing.

    Executor exe = CompletableFuture.delayedExecutor(50L, TimeUnit.SECONDS);
delayedExecutor() is a static utility method used to return a new Executor that submits a task to the default executor after the given delay.

### Reactive Streams
It is a Publish/Subscribe Framework to implement Asynchronous, Scalable and Parallel applications very easily using Java language.

                   Request N items  |-----------|  Request N items
    |-----------|   <-----------    |           |   <-----------    |-------------|
    | publisher |                   | processor |                   | subsciberer |
    |-----------|   ----------->    |           |   ----------->    |-------------|
                   Pushes N items   |-----------|  Pushes N items   

Processor is the entity sitting between the end publisher and subscriber to transform the data received from publisher so that subscriber can understand it. We can have a chain of processors.
    
Java 9 Reactive Streams allows us to implement non-blocking asynchronous stream processing.
Java SE 9 has introduced the following API to develop Reactive Streams in Java-based applications.

* `java.util.concurrent.Flow` This class encapsulates all the important interfaces of the Flow API. This is a final class and we can’t extend it.

* `java.util.concurrent.Flow.Publisher` This is a functional interface and every publisher has to implement it’s subscribe method to add the given subscriber to receive messages.

* `java.util.concurrent.Flow.Subscriber` Every subscriber has to implement this interface. The methods in the subscriber are invoked in strict sequential order. There are four methods in this interface:
        
    1. `onSubscribe`: This is the first method to get invoked when subscriber is subscribed to receive messages by publisher. 
    Usually we invoke `subscription.request` to start receiving items from processor.
        
    2. `onNext`: This method gets invoked when an item is received from publisher, this is where we implement our business logic to process the stream and then request for more data from publisher.

    3. `onError`: This method is invoked when an irrecoverable error occurs, we can do cleanup taks in this method, such as closing database connection.
        
    4. `onComplete`: This is like finally method and gets invoked when no other items are being produced by publisher and publisher is closed. 
    We can use it to send notification of successful processing of stream.
        
* `java.util.concurrent.Flow.Processor` This interface extends both Publisher and Subscriber, this is used to transform the message between publisher and subscriber.

* `java.util.concurrent.SubmissionPublisher` A Publisher implementation that asynchronously issues submitted items to current subscribers until it is closed. 
It uses Executor framework to add subscriber and then submit items to them.

Subscription `cancel()` to stop receiving message in subscriber. Note that if we cancel the subscription, then subscriber will not receive onComplete or onError signal.

> When publisher is producing messages in much faster rate than it’s being consumed by subscriber, **back pressure** gets built. 
Flow API doesn’t provide any mechanism to signal about back pressure or to deal with it. 
But we can devise our own strategy to deal with it, such as fine tuning the subscriber or reducing the message producing rate.

[RxJava](https://github.com/ReactiveX/RxJava) and Akka Streams have been the popular implementation of reactive streams. 

### Enhanced Deprecation
In Java SE 8 and earlier versions, @Deprecated annotation is just a Marker interface without any methods. 
It is used to mark a Java API that is a class, field, method, interface, constructor, enum etc.

In Java SE 9, Oracle Corp has enhanced @Deprecated annotation to provide more information about deprecated API and also provide a Tool to analyze an application’s static usage of deprecated APIs. 
They have add two methods to this Deprecated interface: forRemoval and since to serve this information.

* `@Deprecated(forRemoval=true)`: It indicates that the API will be removed from the future release of Java.
* `@Deprecated(since="version")`: It contains the Java SE version string that indicates deprecared API element.

### Java @SafeVarargs Annotation
Java 7 introduced the @SafeVarargs annotation to suppress the unsafe operation warnings that arises when a method is having varargs (variable number of arguments). 
The @SafeVarargs annotation can only be used with methods (final or static methods or constructors) that cannot be overriden because an overriding method can still perform unsafe operation on their varargs 

Java 9 extended the use of @SafeVarargs annotation, it can now used with private methods as well. This is because private methods cannot be overridden.

### HTTP/2 Client

A new way of performing HTTP calls arrives with Java 9. 
This much overdue replacement for the old `HttpURLConnection` API also supports WebSockets and HTTP/2 out of the box. 
One caveat: The new HttpClient API is delivered as a so-called _incubator module_ in Java 9. This means the API isn't guaranteed to be 100% final yet. 
Still, with the arrival of Java 9 you can already start using this API: 
New API uses 3 major classes i.e. `HttpClient`, `HttpRequest` and `HttpResponse`.

    HttpClient httpClient = HttpClient.newHttpClient(); 
    HttpRequest httpRequest = HttpRequest.newBuilder().uri(new URI("//egencia.com/")).GET().build(); 
    HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandler.asString()); 
    System.out.println( httpResponse.body() ); 

New API also support Async HTTP requests using `httpClient.sendAsync()` method. 
It returns CompletableFuture object which can be used to determine whether the request has been completed or not. 
It also provide you access to the HttpResponse once request is completed, if you needed you can even cancel the request before it completes. 
e.g.

    if(httpResponse.isDone()) {
        System.out.println(httpResponse.get().statusCode());
        System.out.println(httpResponse.get().body());
    } else {
        httpResponse.cancel(true);
    }
Besides this simple request/response model, HttpClient provides new APIs to deal with HTTP/2 features such as streams and server push.

### Remove GC Combinations Deprecated in JDK 8
Deprecated garbage collector combination has removed from JDK 9. 
Following are the garbage collector combination that are not supported in new version.

* DefNew + CMS
* ParNew + SerialOld
* Incremental CMS

### Unified GC Logging
Garbage collection logging is reimplemented by using the unified JVM logging framework.

### Deprecate the Concurrent Mark Sweep (CMS) Garbage Collector
The Concurrent Mark Sweep (CMS) garbage collector is deprecated. 
It issue a warning message when requested on the command line. 
The Garbage-First (G1) garbage collector is intended to be a replacement for most uses of CMS.

### Unified JVM Logging
Java new version introduces a common logging system for all components of the JVM.
t provides the infrastructure to do the logging, but it does not add the actual logging calls from all JVM components. 
It also does not add logging to Java code in the JDK.
The logging framework defines a set of tags – for example, gc, compiler, threads, etc. 
We can use the command line parameter -Xlog to turn on logging during startup.

Let's log messages tagged with ‘gc' tag using ‘debug' level to a file called ‘gc.txt' with no decorations:

	java -Xlog:gc=debug:file=gc.txt:none ...

-Xlog:help will output possible options and examples. 
Logging configuration can be modified runtime using jcmd command. 
We are going to set GC logs to info and redirect them to a file – gc_logs:

	jcmd 9615 VM.log output=gc_logs what=gc


### Compact Strings
Adopts a more space-efficient internal representation for strings. 
Previously, the String class stored characters in a char array, using two bytes (16 bits) for each character. 
The new internal representation of the String class is a byte array plus an encoding-flag field.

This is purely an implementation change, with no changes to existing public interfaces.

### Platform Logging API and Service
The java.util.logging API helps to log messages, together with a service interface for consumers. 
An application or library can provide implementation of this service to log messages to logging framework. 
It uses default implementation, if no implementation is provided.

[Click for more JDK 9 features](https://docs.oracle.com/javase/9/whatsnew/toc.htm#JSNEW-GUID-DB9EB298-4944-4BF9-9CE0-B4A884F8294F)
-------------------------------------------------------------
##Miscellaneous
### Java New Version-String Scheme
Java version-string is a format that contains version specific information. 
This version-string consists of major, minor, security and patch update releases.
In Java 9, a new version-string scheme is introduced.

### Java 9 Control Panel
Java control panel is used to control Java applications that are embedded in browser. 
This control panel maintains the settings that manage Java application embedded in browser.

In Java 9, control panel was rewritten as a JavaFX application and the storage location has changed.

### Installer Enhancement for Microsoft windows
Java 9 includes improved version of Microsoft Windows installer and added the following feature.

This installer allows us to enable and disable web deployement. We can enable web deployment by selecting Custom Setup, install and click on the checkbox from the welcome page of the installer.

### Installer Enhancements for macOS
Java 9 included the following features to the macOS installer.

Feature	Description
CPU Version Availability	It provides notification on next CPU availability after uninstalling the current CPU version.
User Experience	It enhanced user experience while updating the JRE.
Add More Diagnostic Commands
Java 9 has added some new commands to improve the diagnose issues. These diagnostic command are used to diagnose Hotspot and JDK.

The Java jcmd utility can be used to send diagnostic command requests to a running Java Virtual Machine (JVM).

### Remove Launch-Time JRE Version Selection
Java 9 has removed JRE (Java Runtime Environment) version selection at launch time. 
Now days, modern application has own active installer that further contains methods to manage the JRE. 
That's why JRE version selection has been removed.

### Remove the JVM TI hprof Agent
Java 9 has removed the hprof from the existed JDK. It was not intended to be a production tool. 
Some better featured of the hprof agent have been superseded for better alternatives.

### Remove the Jhat Tool
Java has removed the jhat tool in its new release JDK 9. 
It was an experimental and unsupported tool added in JDK 6, now has outdated.

### Validate JVM Command-Line Flag Arguments
Java validates arguments to all numeric JVM command-line flags to avoid failure. 
If arguments are invalid or out-of-range, it displays an appropriate error message.

Constraint check has been implemented for range and optional that require numeric value.

### Datagram Transport Layer Security (DTLS)
DTLS is a protocol which is used to construct TLS over datagram. JSSE (Java Secure Socket Extension) API support DTLS protocol and both versions 1.0 and 1.2.

The TLS protocol requires a TCP, So it can't be used to secure unreliable datagram traffic.

### TLS Application-Layer Protocol Negotiation Extension
This extension allows the client and server in TLS connection to negotiate for application protocol. 
Client server communicate and inform to each other about supported application protocols. 
The application protocol negotiation accomplished within the TLS handshake.

### OCSP Stapling for TLS
OCSP (Online Certificate Status Protocol) helps to the server in a TLS connection to check for a revoked X.509 certificate revocation. 
During TLS handshaking server contact an OCSP responder for the certificate. 
Server then staple the revocation information to the certificate at client.

### DRBG-Based SecureRandom Implementation
Java 9 includes the functionality of DRBG (Deterministic Random Bit Generator) mechanisms as specified in NIST SP 800-90Ar1 in the SecureRandom API.

The DRBG mechanisms use modern algorithms as strong as SHA-512 and AES-256. 
Each of these mechanisms can be configured with different security strengths and features to match user requirements.

### Disable SHA-1 Certificates
The security configuration of the JDK has improved. 
It provides more flexible mechanism to disable X.509 certificate having SHA-1-based signatures.

The jdk.certpath.disabledAlgorithms security property is enhanced with several new constraints that allow greater control over the types of certificates that can be disabled.

### Create PKCS12 Keystores by Default
The default keystore type has modified from JKS to PKCS12. 
The PKCS is an extensible, standard, and widely supported format for storing cryptographic keys. 
It improves confidentiality by storing private keys, trusted public key certificates etc. 
This feature also opens opportunities for interoperability with other systems such as Mozilla, Microsoft's Internet Explorer, and OpenSSL that support PKCS12.

### SHA-3 Hash Algorithms
New Java version supports SHA-3 cryptographic hash functions.

The java.security.MessageDigest API supports various algorithms like: SHA3-224, SHA3-256, SHA3-384, and SHA3-512.

The following providers support SHA-3 algorithm enhancements:

* SUN provider: SHA3-224, SHA3-256, SHA3-384, and SHA3-512
* OracleUcrypto provider: SHA-3 digests supported by Solaris 12.0

### Deprecate the Java Plug-in
Java Plug-in and applet technologies has deprecate in JDK 9. 
In future releases, these technologies will be removed.

Java Plug-in is require to run applet and JavaFX applications in web browser. 
It is recommended to rewrite applications as Java Web applications.

### Enhanced Java Control Panel
Java control panel has improved, information is easier to locate, a search field is available and modal dialog boxes are no longer used. 
Note that the location of some options has changed from previous versions of the Java Control Panel.

### Simplified Doclet API
The old Doclet API is replaced with a new simplified API that leverages other standard, existing APIs. 
In Java 9, the standard doclet has been rewritten to use the new Doclet API.

### Compiler Control
Now, we can control JVM compilation through compiler directive options. 
The level of control is runtime-manageable and method-specific. 
Compiler Control supersedes, and is backward compatible, with CompileCommand.

### Segmented Code Cache
Code cache is divided into distinct segments. 
Each segment is a compiled code and improve performance and enable extensibility.

### XML Catalogs
Standard XML catalog API is added which supports the organization for the Advancement of OASIS (Structured Information Standards) XML Catalogs version 1.1. This API consists of catalog-resolver which can be used as an intrinsic with the JAXP processors.

### Filter Incoming Serialization Data
It helps to filter date of incoming stream of object-serialization data to improve both robustness and security. Object-serialization clients can validate their input more easily, and exported Remote Method Invocation (RMI) objects can validate invocation arguments more easily as well.

Serialization clients implement a filter interface that is set on an ObjectInputStream. For RMI, the object is exported through a RemoteServerRef that sets the filter on the MarshalInputStream to validate the invocation arguments as they are unmarshalled.

### Stack-Walking API
Java included a stack-walking API that allows lazy access of information in stack. It also allows easy filtering of information.

This stack-walking API allows access to Class objects, if the stack walker is configured.

### Spin-Wait Hints
It enables Java code to inform that a spin loop is executing. 
A spin loop repeatedly checks to see if a condition is true, such as when a lock can be acquired, after which some computation can be safely performed followed by the release of the lock. 
This API is purely a hint, and carries no semantic behavior requirements.

### Parser API for Nashorn
Java added Parser API which allows use to Enable applications, in server-side framework, particular IDEs etc..

It can be used to parse ECMAScript code from a string, URL, or file with methods of Parser class. Methods of this class return an object of CompilationUnitTree class, which represents ECMAScript code as an abstract syntax tree.

Nashorn parser API is located into jdk.nashorn.api.tree package.

### Implement Selected ECMAScript 6 Features in Nashorn
Java added some new features to Nashorn in the 6th edition of ECMA-62. Following are the Implemented features.

Template strings
let, const, and block scope
Iterators and for..of loops
Map, Set, WeakMap, and WeakSet
Symbols
Binary and octal literals
Prepare JavaFX UI Controls and CSS APIs for Modularization
Java included public APIs for CSS functionality and JavaFX UI controls. These functionalities previously available only through internal packages, but now can be accessible because of modular approach.

A new package javafx.scene.control.skin is included that consists of a set of classes to provide a default implementation of each UI (User Interface) control.

### BeanInfo Annotations
The @beaninfo Javadoc tag is replaced with the annotation types JavaBean, BeanProperty, and SwingContainer.

We can use these attributed directly in the Bean class. It also allows auto removal for automatically created classes and set the corresponding feature attributes during BeanInfo generation at runtime.

### TIFF Image I/O
TIFF (Tag Image File Format) is added for reading and writing as standard. It is located into the package javax.imageio. One more new package javax.imageio.plugins.tiff is added to provide classes that simplify the optional manipulation of TIFF metadata.

### HiDPI Graphics on Windows and Linux
Automatically scales and sizes AWT and Swing components for High Dots Per Inch (HiDPI) displays on Windows and Linux.

The JDK already supports HiDPI "retina displays" on OS X.

Prior to this release, on Windows and Linux, Java applications were sized and rendered based on pixels, even on HiDPI displays that can have pixel densities two to three times as high as traditional displays. This led to GUI components and windows that were too small to read or use.

### Platform-Specific Desktop Features
Some new methods are added to the java.awt.Desktop class. These methods provides the following features.

Show custom About and Preferences windows.
Handle requests to open or print a list of files.
Handle requests to open a URL.
Open the native help viewer application.
Set the default menu bar.
Enable or disable the application to be suddenly terminated.
Enable GTK 3 on Linux
Java new version allows Java graphical applications(JavaFX, Swing, or Abstract Window Toolkit) to use GTK version on Linux.

JDK uses GTK +2 by default, if it is not available, GTK+3 is used.

We can use specific version by setting property of jdk.gtk.version.

### Unicode 8.0
Java supports Unicode 8.0 in its new Java 9 version, previously Unicode 6.2 was used.

Java 9 supports, Unicode 6.3, 7.0 and 8.0 standards that combined introduced 10,555 characters, 29 scripts, and 42 blocks.

### CLDR Locale Data Enabled by Default
CLDR (Common Locale Data Repository's) represents the locale data provided by the Unicode CLDR project. It was first added in JDK 8 and now default in JDK 9.

We can enable behavior compatible with Java 8 by setting the property java.locale.providers to a value with COMPAT ahead of CLDR.

### UTF-8 Properties Files
The UTF-8 is a convenient way to represent non-Latin characters. The new version of java loads properties files in UTF-8 encoding. In earlier versions, ISO-8859-1 encoding was used when loading property resource bundles.

Java removes the endorsed-standards override mechanism and the extension mechanism.
The rt.jar and tools.jar both has been removed from JRE.
JDK's internal APIs are inaccessible by default.

