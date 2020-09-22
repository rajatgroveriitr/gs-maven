package java7;

/**
 * In Java 7, Java allows you to use string objects in the expression of switch statement.
 * In order to use string, you need to consider the following points:
 *
 * => It must be only string object.
 * Object game = "Hockey"; // It is not allowed
 *     String game = "Hockey"; // It is OK.
 * => String object is case sensitive.
 * "Hockey" and "hockey" are not equal.
 * => No Null object
 * be careful while passing string object, passing a null object cause to NullPointerException.
 */
public class ExampleStringInSwitch {
    public static void main(String[] args) {
        String game = "Card-Games";
        switch(game){
            case "Hockey": case"Cricket": case"Football":
                System.out.println("This is a outdoor game");
                break;
            case "Chess": case"Card-Games": case"Puzzles": case"Indoor basketball":
                System.out.println("This is a indoor game");
                break;
            default:
                System.out.println("What game it is?");
        }
    }
}
