package java9;

import java.util.ArrayList;
import java.util.List;

public class SafeVarargsExample {
    //compilation error if try to compile this in Java 7 and 8
    @SafeVarargs // without annotation it produce few warnings in Java 9.
    private void printMe(List... names) {
        for (List<String> name : names) {
            System.out.println(name);
        }
    }
    public static void main(String[] args) {
        SafeVarargsExample obj = new SafeVarargsExample();
        List<String> list = new ArrayList<>();
        list.add("Kevin");
        list.add("Rick");
        list.add("Negan");
        obj.printMe(list);
    }
}
