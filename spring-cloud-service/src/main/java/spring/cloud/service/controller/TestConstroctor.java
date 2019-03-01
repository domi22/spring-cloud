package spring.cloud.service.controller;

public class TestConstroctor {

//    public class A {
//        public A() {
//            System.out.println("a");
//        }
//    }
//
//    public class B extends A {
//        public B() {
//            System.out.println("b");
//        }
//    }

    static int a = 100;
    public static void main(String[] args) {
//        A a = new B(1);
//        a = new B(2);
        TestConstroctor da = new TestConstroctor();
        TestConstroctor.a++;


    }

    public class Ss extends TestConstroctor {
        public void hehh() {
            Ss.a++;
        }

    }


}
