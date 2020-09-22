package java8;

import java.util.Optional;

public class ExampleOptional {
    public static void main(String[] args) {
        String[] str = new String[10];
//        withoutOptional(str);

        Optional<String> isNull = Optional.ofNullable(str[9]);
        withOptionIsPresent(str, isNull);

        str[9] = "OhCool";
        Optional<String> isNull2 = Optional.ofNullable(str[9]);
        withOptionIsPresent(str, isNull2);


        Optional<String> got = Optional.of("Game of Thrones"); //Creating Optional object from a String
        Optional<String> nothing = Optional.empty(); //Optional.empty() creates an empty Optional object
        isVsifPresent(got, nothing);
        orElseVsOrElseGet(got, nothing);

        mapVsFlatMap(got, nothing);
        withFilter(got);
    }

    private static void withFilter(Optional<String> got) {
        /* Filter returns an empty Optional instance if the output doesn't
         * contain any value, else it returns the Optional object of the
         * given value.
         */
        System.out.println(got.filter(s -> s.equals("GAME OF THRONES")));
        System.out.println(got.filter(s -> s.equalsIgnoreCase("GAME OF THRONES")));
    }

    private static void mapVsFlatMap(Optional<String> got, Optional<String> nothing) {
        System.out.println(got.map(String::toLowerCase));
        System.out.println(nothing.map(String::toLowerCase));

        Optional<Optional<String>> anotherOptional = Optional.of(Optional.of("BreakingBad"));
        System.out.println("Value of Optional object"+anotherOptional);
        System.out.println("Optional.map: "
                +anotherOptional.map(ao -> ao.map(String::toUpperCase)));
        //Optional<Optional<String>>    -> flatMap -> Optional<String>
        System.out.println("Optional.flatMap: "
                +anotherOptional.flatMap(ao -> ao.map(String::toUpperCase)));
    }

    private static void orElseVsOrElseGet(Optional<String> got, Optional<String> nothing) {
        //orElse() method
        System.out.println(got.orElse("Default Value"));
        System.out.println(nothing.orElse("Default Value"));

        //orElseGet() method
        System.out.println(got.orElseGet(() -> "Default Value"));
        System.out.println(nothing.orElseGet(() -> "Default Value"));
    }

    private static void isVsifPresent(Optional<String> got, Optional<String> nothing) {
        // isPresent() method: Checks whether the given Optional Object is empty or not.
        if (got.isPresent())
            System.out.println("Watching Game of Thrones");
        else
            System.out.println("I am getting Bored");

        // ifPresent() method: It executes only if the given Optional object is non-empty.
        //This will print as the GOT is non-empty
        got.ifPresent(s -> System.out.println("Watching GOT is fun!"));
        //This will not print as the nothing is empty
        nothing.ifPresent(s -> System.out.println("I prefer getting bored"));
    }

    private static void withOptionIsPresent(String[] str, Optional<String> isNull) {
        if(isNull.isPresent()){
            String str2 = str[9].substring(2, 5);   //Getting the substring
            System.out.println("Substring is: "+ str2);   //Displaying substring
        }
        else
            System.out.println("Cannot get the substring from an empty string");
    }

    private static void withoutOptional(String[] str) {
        String str2 = str[9].substring(2, 5);   //Getting the substring
        System.out.println("Substring is: "+ str2);   //Displaying substring
    }
}
