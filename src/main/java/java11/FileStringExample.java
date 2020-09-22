package java11;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileStringExample {
    public static void main(String[] args) throws IOException {
        Path path = Files.writeString(Files.createTempFile(Paths.get("/tmp/"),"test", ".txt"), "This was posted on JD");
        System.out.println(path);
        String s = Files.readString(path);
        System.out.println(s); //This was posted on JD

    }
}
