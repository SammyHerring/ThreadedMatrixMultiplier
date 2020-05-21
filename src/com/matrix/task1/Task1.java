package com.matrix.task1;

import com.matrix.generic.Generic;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

public class Task1 {

    public static int[][] task1(int MATRIX_COUNT, boolean DEBUG_MODE, int[][] matrixA, int[][] matrixB) {

        System.out.println("\n--\tTASK 1\t-\tSTARTING\t--\t");

        Instant start = Instant.now();
        //START     -   Multiplication Function

        int [][] matrixZ = multiplyMatrices(matrixA, matrixB);

        //END       -   Multiplication Function
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis(); //Instant values subtracted to get computation time

        if (DEBUG_MODE) {
            Generic.printMatrix(matrixA, Optional.of("A"));
            Generic.printMatrix(matrixB, Optional.of("B"));
            Generic.printMatrix(matrixZ, Optional.of("Z"));
        }

        System.out.println("--\tTASK 1\t-\tCOMPLETED in " + timeElapsed / 1000 + " seconds\t--\t");

        return matrixZ;
    }

    /**
     * Function: Matrix Multiplication Function
     * Returns: Integer Matrix
     */
    public static int[][] multiplyMatrices(int[][] matrixA, int[][] matrixB) {

        int matrixA_Row = matrixA.length;
        int matrixA_Column = matrixA[0].length;
        int matrixB_Row = matrixB.length;
        int matrixB_Column = matrixB[0].length ;

        if (matrixA_Column != matrixB_Row) {
            throw new IllegalArgumentException("Error: Matrix A Column Count != Matrix B Row Count.");
        }

        int[][] matrixProduct = new int[matrixA_Row][matrixB_Column];

        for(int i = 0; i < matrixA_Row; i++) {
            for (int j = 0; j < matrixB_Column; j++) {
                for (int k = 0; k < matrixA_Column; k++) {
                    matrixProduct[i][j] += matrixA[i][k] * matrixB[k][j];
                }
            }
        }

        return matrixProduct;
    }
}
