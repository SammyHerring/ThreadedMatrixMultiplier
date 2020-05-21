package com.matrix.task4;

import java.util.Random;

public class Task4_Thread implements Runnable {
    private int[][] A;
    private int[][] B;
    private int[][] Z;
    private int i;
    private int j;
    private int bias;

    public Task4_Thread(int i, int j, int[][] A, int[][] B, int[][] Z, int bias) {
        this.A = A;         //Matrix A Ref
        this.B = B;         //Matrix B Ref
        this.Z = Z;         //Matrix Z Ref (Product of AB)
        this.i = i;         //Row Identifier
        this.j = j;         //Column Identifier
        this.bias = bias;   //BIAS Value for Random Chance Thread Kill - SET in Main Class (Main.java)
    }

    @Override
    public void run() {
        try {
            Thread.sleep(200); //Wait for 200ms as required by assignment

            int newZ = 0; //Track Matrix Cell Value in case of failure

            //Perform cross-multiplication for given row/column
            for (int k = 0; k < A[0].length; k++) {
                newZ += (A[i][k] * B[k][j]);
            }

            if (getBiasedRandom(bias)) {
                run(); //If fail, retry calculation
            } else {
                Z[i][j] = newZ; //If Succeed, send value to Product Matrix
            }

        } catch (Exception e) {
            e.printStackTrace(); //Handle potential errors gracefully
        }
    }

    //Function to return boolean randomly with bias of 0 - 100
    public boolean getBiasedRandom(int bias) {

        int number;
        Random rnd = new Random();

        number = rnd.nextInt(100);

        if (number > bias){
            return false;
        }
        else {
            return true;
        }
    }

}
