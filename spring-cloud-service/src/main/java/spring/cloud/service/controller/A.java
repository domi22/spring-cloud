package spring.cloud.service.controller;

public class A {
    public A() {
        System.out.println("a");
    }
    public A(int a) {
        System.out.println("you can a=" + a);
    }


    static{
        System.out.println("satic a");
        System.out.println("satic a");
        System.out.println("satic a");
    }

}
