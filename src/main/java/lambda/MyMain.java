package lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Created by rgrover on 29/11/18.
 */
public class MyMain {

    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("charls", "dekin", 311),
                new Person("lewis", "caroll", 51),
                new Person("thomos", "carlyle", 71),
                new Person("Charlotte", "Bronte", 11),
                new Person("matthew", "arnold", 41)
        );

        // sort list by last name
        Collections.sort(people, (o1, o2) -> o1.getLastName().compareToIgnoreCase(o2.getLastName()));

        System.out.println("\n create a method that prints all elements in the list");
        people.forEach(p -> System.out.println(p));
        System.out.println("\n create a method that prints all elements in the list");
        performConditionally(people, p -> true, p->System.out.println(p));

        System.out.println("\n create a method that prints all people that have last name beginning with C");
        performConditionally(people, (p) -> p.getLastName().startsWith("c"), p->System.out.println(p));


        System.out.println("\n create a method that prints all people that have first name beginning with C");
        performConditionally(people, (p) -> p.getFirstName().startsWith("c"), p->System.out.println(p.getFirstName()));
    }

    private static void performConditionally(List<Person> people, Predicate<Person> condition, Consumer<Person> consumer) {
        people.forEach(p -> {
            if(condition.test(p)) {
                consumer.accept(p);
            }
        });
    }
}
