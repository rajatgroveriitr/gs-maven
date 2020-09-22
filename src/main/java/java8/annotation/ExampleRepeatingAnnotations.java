package java8.annotation;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Declaring repeatable annotation type
// Declaring of repeatable annotation type must be marked with the @Repeatable meta-annotation
// The value of the @Repeatable meta-annotation, in parentheses, is the type of the container annotation that the Java compiler generates to store repeating annotations.
@Repeatable(Games.class)
@interface Game{
    String name();
    String day();
}
// Declaring container for repeatable annotation type
@Retention(RetentionPolicy.RUNTIME)
@interface Games {
    // Containing annotation type must have a value element with an array type.
    // The component type of the array type must be the repeatable annotation type.
    Game[] value();
}
// Repeating annotation
@Game(name = "Cricket",  day = "Sunday")
@Game(name = "Hockey",   day = "Friday")
@Game(name = "Football", day = "Saturday")
public class ExampleRepeatingAnnotations {
    public static void main(String[] args) {
        // Getting annotation by type into an array
        Game[] game = ExampleRepeatingAnnotations.class.getAnnotationsByType(Game.class);
        for (Game game2 : game) {    // Iterating values
            System.out.println(game2.name()+" on "+game2.day());
        }
    }
}
