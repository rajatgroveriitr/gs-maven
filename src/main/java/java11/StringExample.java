package java11;

import java.util.stream.Collectors;

public class StringExample {
    public static void main(String[] args) {
        testIsBlank();
        testLines();
        testStrips();
        testRepeat();
    }

    private static void testIsBlank() {
        System.out.println("".isBlank()); //true
        System.out.println(" ".isBlank()); //true
        System.out.println("Rajat".isBlank()); //false
    }

    private static void testLines() {
        String str = "JD\nJD\nJD";
        System.out.println(str);
        System.out.println(str.lines().collect(Collectors.toList()));
    }

    private static void testStrips() {
        String str = " JD ";
        System.out.print("Start");
        System.out.print(str.strip());
        System.out.println("End");

        System.out.print("Start");
        System.out.print(str.stripLeading());
        System.out.println("End");

        System.out.print("Start");
        System.out.print(str.stripTrailing());
        System.out.println("End");
    }

    private static void testRepeat() {
        String str = "=".repeat(2);
        System.out.println(str); //prints ==
    }
}
