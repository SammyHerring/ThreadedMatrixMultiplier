package com.matrix.task3;

//Executor Service Factory
public class Task3_Executor {

    int capacity;

    //Fixed Thread Pool Factory, in-line with syntax style of real Java Thread Pool Factory Method
    static Task3_Pool newFixedThreadPool(int capacity) {

        return new Task3_Pool(capacity);

    }

}
