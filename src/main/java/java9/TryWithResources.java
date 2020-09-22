package java9;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class TryWithResources {
    public static void main(String args[]) throws IOException {
//        tryWithResource();
        tryWithMultiResources();
    }

    private static void tryWithResource() throws IOException {
        FileOutputStream fileOutputStream =new FileOutputStream("src/main/java/java7/try-resource.txt");
        try(fileOutputStream){
            String msg = "Welcome to try with resources!";
            byte byteArray[] = msg.getBytes(); //converting string into byte array
            fileOutputStream.write(byteArray);
            System.out.println("Message written to file successfuly!");
        }catch(Exception exception){
            System.out.println(exception);
        }
    }

    private static void tryWithMultiResources() throws FileNotFoundException {
        // Using multiple resources
        FileOutputStream fileOutputStream =new FileOutputStream("src/main/java/java7/try-resource.txt");
        InputStream input = new FileInputStream("src/main/java/java7/try-resource.txt");
        try( fileOutputStream; input ){
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
