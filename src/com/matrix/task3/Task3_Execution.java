package com.matrix.task3;

public class Task3_Execution implements Runnable {
    //Executor Thread Factory, waiting for Thread Pool Capacity
    void execute() {
        if (Task3_Pool.currentCapacity < Task3_Pool.capacity) {

            Task3_Pool.currentCapacity++;
            Thread t = new Thread(new Task3_Execution());

            try {
                t.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //Run Thread Executor Service if Items Exist
    @Override
    public void run() {
        while (true) {
            if (Task3_Pool.queue.size() != 0) {

                Task3_Pool.queue.poll().run();

            }
        }
    }

}
