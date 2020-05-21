package com.matrix.task3;

import java.util.concurrent.LinkedBlockingQueue;

public class Task3_Pool implements Task3_iExecutor {
    static int capacity;
    static int currentCapacity;
    static LinkedBlockingQueue<Runnable> queue;
    Task3_Execution task;

    //Thread Pool Constructor
    public Task3_Pool(int capacity) {
        this.capacity = capacity;
        currentCapacity = 0;
        queue = new LinkedBlockingQueue<Runnable>();
        task = new Task3_Execution();
    }

    //Function to submit tasks to Thread Pool from iExecutor
    @Override
    public void submit(Runnable thread) {
        queue.add(thread);
        task.execute();
    }

    //Function to return boolean if executor service has active threads
    public boolean executorRunning() {
        if (queue.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    //Clear running executor service active threads, in-line with built-in Java executor service syntax
    public void shutdownNow() {
        queue.clear();
        currentCapacity = 0;

    }
}
