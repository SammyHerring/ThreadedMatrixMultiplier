package com.matrix.task4;

public class Task4_Execution implements Runnable {
    //Executor Thread Factory, waiting for Thread Pool Capacity
    void execute() {
        if (Task4_Pool.currentCapacity < Task4_Pool.capacity) {

            Task4_Pool.currentCapacity++;
            Thread t = new Thread(new Task4_Execution());

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
            if (Task4_Pool.queue.size() != 0) {

                Task4_Pool.queue.poll().run();

            }
        }
    }

}
