package com.matrix.task4;

//Executor Service Factory
public class Task4_Executor {

    int capacity;

    //Fixed Thread Pool Factory, in-line with syntax style of real Java Thread Pool Factory Method
    static Task4_Pool newFixedThreadPool(int capacity) {

        return new Task4_Pool(capacity);

    }

}
