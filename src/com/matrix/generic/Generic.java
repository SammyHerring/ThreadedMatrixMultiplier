package com.matrix.generic;

import java.util.Optional;

public class Generic {

    //Cross-Task Method to Print Formatted Matrix with Optional Title
    public static void printMatrix(int[][] matrix, Optional<String> name) {

        if (name.isPresent()) {
            System.out.println("Matrix " + name.get() + ": ");
        }

        for (int i=0; i < matrix.length; i++) {
            for (int j=0; j < matrix[i].length; j++) {
                System.out.printf("\t%d", matrix[i][j]);
            }
            System.out.println();
        }

    }

    //Cross-Task Method to Generate Random 2 by 2 Matrix
    public static int[][] generate2by2Matrix(int MATRIX_COUNT) {

        int [][] matrix = new int [MATRIX_COUNT][MATRIX_COUNT];

        for (int i=0; i < matrix.length; i++) {
            for (int j=0; j< matrix[i].length; j++) {
                matrix[i][j] = (int) (Math.random()*10);
            }
        }
        return matrix;
    }

}


