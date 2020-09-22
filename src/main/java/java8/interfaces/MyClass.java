package java8.interfaces;

public class MyClass implements InterfaceA, InterfaceB {

    @Override
    public void methodB() {
    }

    @Override
    public void methodA(String str) {
    }

    //MyClass won't compile without having it's own log() implementation
    @Override
    public void log(String str){
        System.out.println("MyClass logging::" + str);

        //As you can see that InterfaceA has static method implementation that is used in MyClass.log() method implementation.
        InterfaceA.print("abc");
    }
}
