package spring.cloud.service.controller;

public class B extends A {

    public B() {
        System.out.println("b");
    }
    public B(int b) {
        System.out.println("you can b=" + b);
    }


    static{
        System.out.println("satic b");
    }

}
