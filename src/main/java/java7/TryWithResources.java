package java7;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

// A try statement that declares one or more resources. The resource is as an object that must be closed after finishing the program.
// The try-with-resources statement ensures that each resource is closed at the end of the statement execution.
// You can pass any object that implements java.lang.AutoCloseable, which includes all objects which implement java.io.Closeable.

// If a try block throws an exception and one or more exceptions are thrown by the try-with-resources, the exceptions thrown by try-with-resources are suppressed.
// In other words, we can say, exceptions which are thrown by try-with-resources are suppressed exceptions.
// You can get these exceptions by using the getSuppress() method of Throwable class.
// Java added a new constructor and two new methods in Throwable class to deal with suppressed exceptions.
public class TryWithResources {
    public static void main(String args[]){
//        tryWithResource();
        tryWithMultiResources();
    }

    private static void tryWithResource() {
        try(FileOutputStream fileOutputStream =new FileOutputStream("src/main/java/java7/try-resource.txt")){
            String msg = "Welcome to try with resources!";
            byte byteArray[] = msg.getBytes(); //converting string into byte array
            fileOutputStream.write(byteArray);
            System.out.println("Message written to file successfuly!");
        }catch(Exception exception){
            System.out.println(exception);
        }
    }

    private static void tryWithMultiResources() {
        try(    // Using multiple resources
                FileOutputStream fileOutputStream =new FileOutputStream("src/main/java/java7/try-resource.txt");
                InputStream input = new FileInputStream("src/main/java/java7/try-resource.txt")){
            // -----------------------------Code to write data into file--------------------------------------------//
            String msg = "Welcome to try with resources!";
            byte byteArray[] = msg.getBytes();  // Converting string into byte array
            fileOutputStream.write(byteArray);  // Writing  data into file
            System.out.println("------------Data written into file--------------");
            System.out.println(msg);
            // -----------------------------Code to read data from file---------------------------------------------//
            // Creating input stream instance
            DataInputStream inst = new DataInputStream(input);
            int data = input.available();
            // Returns an estimate of the number of bytes that can be read from this input stream.
            byte[] byteArray2 = new byte[data]; //
            inst.read(byteArray2);
            String str = new String(byteArray2); // passing byte array into String constructor
            System.out.println("------------Data read from file--------------");
            System.out.println(str); // display file data
        }catch(Exception exception){
            System.out.println(exception);
        }
        finally{
            System.out.println("Finally executes after closing of declared resources.");
        }
    }
}
