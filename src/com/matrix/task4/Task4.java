package com.matrix.task4;

import com.matrix.generic.Generic;
import com.matrix.generic.ProgressBar;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

public class Task4 {

    public static int[][] task4(int MATRIX_COUNT, boolean DEBUG_MODE, int[][] matrixA, int[][] matrixB, int bias) {

        int [][] matrixZ = new int[0][0];
        long timeElapsed = 0;

        System.out.println("\n--\tTASK 4\t-\tSTARTING\t--\t");

        try {
            Instant start = Instant.now();
            //START     -   Multiplication Function

            matrixZ = multiplyMatrices(matrixA, matrixB, MATRIX_COUNT, bias);

            //END       -   Multiplication Function
            Instant finish = Instant.now();
            timeElapsed = Duration.between(start, finish).toMillis(); //Instant values subtracted to get computation time

            if (DEBUG_MODE) {
                Generic.printMatrix(matrixA, Optional.of("A"));
                Generic.printMatrix(matrixB, Optional.of("B"));
                Generic.printMatrix(matrixZ, Optional.of("Z"));
            }
        } catch (InterruptedException e) {
            System.out.println("Task 4 Failed");
            e.printStackTrace();
        }

        System.out.println("\n--\tTASK 4\t-\tCOMPLETED in " + timeElapsed / 1000 + " seconds\t--\t");

        return matrixZ;
    }

    /**
     * Function: Matrix Multiplication Function
     * Returns: Integer Matrix
     */
    public static int[][] multiplyMatrices(int[][] matrixA, int[][] matrixB, int MATRIX_COUNT, int bias) throws InterruptedException {

        Task4_Pool executor = Task4_Executor.newFixedThreadPool(100);

        int matrixA_Row = matrixA.length;
        int matrixA_Column = matrixA[0].length;
        int matrixB_Row = matrixB.length;
        int matrixB_Column = matrixB[0].length;

        ProgressBar bar = new ProgressBar();
        bar.update(0, MATRIX_COUNT);

        if (matrixA_Column != matrixB_Row) {
            throw new IllegalArgumentException("Error: Matrix A Column Count != Matrix B Row Count.");
        }

        int[][] matrixProduct = new int[matrixA_Row][matrixB_Column];
        Task4_Thread[][] matrixThread = new Task4_Thread[MATRIX_COUNT][MATRIX_COUNT];

        for (int i = 0; i < matrixA_Row; i++) {
            for (int j = 0; j < matrixB_Column; j++) {
                executor.submit(new Task4_Thread(i, j, matrixA, matrixB, matrixProduct, bias));

                if (i % 100 == 0 || i == MATRIX_COUNT) {
                    bar.update(i, MATRIX_COUNT);
                }
            }
        }

        while (true) {
            if (!executor.executorRunning()) {
                Thread.sleep(2000);
                executor.shutdownNow();
                break;
            }
        }

        return matrixProduct;

    }
}
