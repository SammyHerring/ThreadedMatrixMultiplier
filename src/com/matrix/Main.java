package com.matrix;

import com.matrix.generic.Generic;
import com.matrix.task1.Task1;
import com.matrix.task2.Task2;
import com.matrix.task3.Task3;
import com.matrix.task4.Task4;

public class Main {

    public static void main(String[] args) {

        int MATRIX_COUNT = 100;    //Number of Randomly Generated Matrix Rows and Columns
        boolean DEBUG_MODE = true; //DEBUG_MODE defines whether matrix values printed to console
        int BIAS = 10;              //BIAS must be a number between 0 to 100 to act as a chance value for Task 4

        System.out.println("--\tMatrix Multiplication Utility\t--\t");

        //Generate Two Matrices Randomly
        int [][] matrixA = Generic.generate2by2Matrix(MATRIX_COUNT);
        int [][] matrixB = Generic.generate2by2Matrix(MATRIX_COUNT);

        //Run Task 1 Method --> Approx. 0.1 Min /w MATRIX_COUNT of 1000
        int[][] t1_result = Task1.task1(MATRIX_COUNT, DEBUG_MODE, matrixA, matrixB);

        //Run Task 2 Method + Perform Validation --> Approx. 1 Min /w MATRIX_COUNT of 1000
        int[][] t2_result = Task2.task2(MATRIX_COUNT, DEBUG_MODE, matrixA, matrixB);
        String status2 =  (compare3by3Matrix(t1_result, t2_result)) ? "VALID" : "INVALID";
        System.out.println("--\tTask 2\t-\tRESULTS " + status2 + "\t--");

        //Run Task 3 Method + Perform Validation --> Approx. 8 Min /w MATRIX_COUNT of 1000
        int[][] t3_result = Task3.task3(MATRIX_COUNT, DEBUG_MODE, matrixA, matrixB);
        String status3 =  (compare3by3Matrix(t1_result, t3_result)) ? "VALID" : "INVALID";
        System.out.println("--\tTask 3\t-\tRESULTS " + status3 + "\t--");

        //Run Task 4 Method + Perform Validation --> Approx. 45 Min /w MATRIX_COUNT of 1000 and BIAS OF 20
        int[][] t4_result = Task4.task4(MATRIX_COUNT, DEBUG_MODE, matrixA, matrixB, BIAS);
        String status4 =  (compare3by3Matrix(t1_result, t4_result)) ? "VALID" : "INVALID";
        System.out.println("--\tTask 4\t-\tRESULTS " + status4 + "\t--");

    }

    /**
     * Function: Check if two matrices of equal dimensions have matching values
     * Returns: Boolean
     */
    public static boolean compare3by3Matrix(int[][] goldMatrix, int[][] comparisionMatrix) {

        //Get Gold Matrix Dimensions
        int goldMatrix_Row = goldMatrix.length;
        int goldMatrix_Column = goldMatrix[0].length;

        //Get Comparision Matrix Dimensions
        int comparisionMatrix_Row = comparisionMatrix.length;
        int comparisionMatrix_Column = comparisionMatrix[0].length;

        int [][] diff = new int[goldMatrix.length][goldMatrix.length];


        //Check to ensure Matrix Dimensions Match
        if (!(goldMatrix_Row == comparisionMatrix_Row && goldMatrix_Column == comparisionMatrix_Column)) {
            throw new IllegalArgumentException("Error: Matrix Dimensions not 3 by 3");

        }

        //Subtract comparision matrix cells from gold matrix cells in new Diff Matrix
        for(int i = 0; i < goldMatrix_Row; i++){
            for(int j = 0; j < goldMatrix_Column; j++){
                diff[i][j] = goldMatrix[i][j] - comparisionMatrix[i][j];
            }
        }

        //Check if all Diff Matrix values equal 0. If not, return false
        for(int i = 0; i < goldMatrix_Row; i++){
            for(int j = 0; j < goldMatrix_Column; j++){
                if (diff[i][j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

}
