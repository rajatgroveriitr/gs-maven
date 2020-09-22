package java10;

import java.util.HashMap;
import java.util.List;

public class TypeInferenceExample {
    public static void main(String[] args) {
        String msg = "Good bye, Java 9";
        var message = "Hello, Java 10";
        System.out.println(message instanceof String);

        var idToNameMap = new HashMap<Integer, String>(); // focus on the variable name rather than on the variable type.

        var numbers = List.of(1, 2, 3, 4, 5); // inferred value ArrayList<Integer>
// Index of Enhanced For Loop
        for (var number : numbers) {
            System.out.println(number);
        }
// Local variable declared in a loop
        for (var i = 0; i < numbers.size(); i++) {
            System.out.println(numbers.get(i));
        }
    }

}
