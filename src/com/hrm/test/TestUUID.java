package com.hrm.test;

import java.util.UUID;

public class TestUUID {
    public static void main(String[] args) {
        String uuid = UUID.randomUUID().toString().replace("-","");
        System.out.println(uuid);
    }
}
