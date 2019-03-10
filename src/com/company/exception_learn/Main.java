package com.company.exception_learn;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        File file = new File("");
        try {
            FileWriter fw = new FileWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void test() {
        throw new Error("hello world");
    }
    //native 关键字
    //
}

/**
 through this study, you will gain the following knowledge:
    0. common exception.
    1. session
    2. java-lock
    3. aop principle
    4. what is different between listLink and arrayList.
    5. gc principle
    6. multithreading
    7. spark hadoop principle
    8. mechanism classLoader
    9. redis synchronization mechanism
    10.Spring AOP
    11.Http agreement
    12.




 */
