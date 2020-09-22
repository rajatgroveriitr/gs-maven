package java8.interfaces;

public class MyMain {

    public static void main(String[] args) {
        MyClass myClass = new MyClass();
        myClass.log("wow!");

        //java.lang.Runnable is a example of functional interface with single abstract method run()
        Runnable r1 = () -> System.out.println("MyMain Runnable");

        //
        InterfaceA iA = (s) -> System.out.println("MyMain Lambda called::" + s);
        iA.methodA("abc");

    }

}
