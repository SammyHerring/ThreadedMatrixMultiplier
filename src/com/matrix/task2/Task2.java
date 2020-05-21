package com.matrix.task2;

import com.matrix.generic.Generic;
import com.matrix.generic.ProgressBar;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

public class Task2 {

    public static int[][] task2(int MATRIX_COUNT, boolean DEBUG_MODE, int[][] matrixA, int[][] matrixB) {

        int [][] matrixZ = new int[0][0];
        long timeElapsed = 0;

        System.out.println("\n--\tTASK 2\t-\tSTARTING\t--\t");

        try {
            Instant start = Instant.now();
            //START     -   Multiplication Function

            matrixZ = multiplyMatrices(matrixA, matrixB, MATRIX_COUNT);

            //END       -   Multiplication Function
            Instant finish = Instant.now();
            timeElapsed = Duration.between(start, finish).toMillis(); //Instant values subtracted to get computation time

            if (DEBUG_MODE) {
                Generic.printMatrix(matrixA, Optional.of("A"));
                Generic.printMatrix(matrixB, Optional.of("B"));
                Generic.printMatrix(matrixZ, Optional.of("Z"));
            }
        } catch (InterruptedException e) {
            System.out.println("Task 2 Failed");
            e.printStackTrace();
        }

        System.out.println("\n--\tTASK 2\t-\tCOMPLETED in " + timeElapsed / 1000 + " seconds\t--\t");

        return matrixZ;
    }

    /**
     * Function: Matrix Multiplication Function
     * Returns: Integer Matrix
     */
    public static int[][] multiplyMatrices(int[][] matrixA, int[][] matrixB, int MATRIX_COUNT) throws InterruptedException {

        int matrixA_Row = matrixA.length;
        int matrixA_Column = matrixA[0].length;
        int matrixB_Row = matrixB.length;
        int matrixB_Column = matrixB[0].length ;

        ProgressBar bar = new ProgressBar();
        bar.update(0, MATRIX_COUNT);

        if (matrixA_Column != matrixB_Row) {
            throw new IllegalArgumentException("Error: Matrix A Column Count != Matrix B Row Count.");
        }

        int[][] matrixProduct = new int[matrixA_Row][matrixB_Column];
        Task2_Thread [][] matrixThread = new Task2_Thread[MATRIX_COUNT][MATRIX_COUNT];

        for(int i = 0; i < matrixA_Row; i++) {
            for (int j = 0; j < matrixB_Column; j++) {
                    matrixThread[i][j] = new Task2_Thread(i, j, matrixA, matrixB);
                    matrixThread[i][j].start();

                    if (i % 100 == 0 || i == MATRIX_COUNT) {
                        bar.update(i, MATRIX_COUNT);
                    }

                    matrixThread[i][j].join();
                    matrixProduct[i][j] += matrixThread[i][j].getValue();
            }
        }

        return matrixProduct;
    }
}
