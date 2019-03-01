package spring.cloud.service.controller;

import java.util.ArrayList;
import java.util.List;

public class Test2 {

    public static void main(String[] args) {
        List list = new ArrayList<>();
        list.add("nihao");
        int na = getNa();
        System.out.println(na);
    }

    private static int getNa() {
        int na = 100;
        for (int i = 0; i < 20; i++) {
            na++;
        }
        return na;
    }


}
