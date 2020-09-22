package java7;

//Java 7 allows you to catch multiple type exceptions in a single catch block and helps to optimize code.
//Use vertical bar (|) to separate multiple exceptions in catch block.
public class ExampleMultipleException {
    public static void main(String args[]){
        try{
            int array[] = new int[10];
            array[10] = 30/0;
        }
        //If you are using super (general) class, don't use child (specialized) class.
        catch(ArithmeticException | ArrayIndexOutOfBoundsException e){ //must be disjoint class
            System.out.println(e.getMessage());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
