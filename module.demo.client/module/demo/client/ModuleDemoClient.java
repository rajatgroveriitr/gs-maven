package module.demo.client;

import module.demo.ModuleDemo;

public class ModuleDemoClient {

    public static void main (String arg[]) {

        ModuleDemo demo = new ModuleDemo();
        System.out.println(demo.welcomeMessage());

    }

}