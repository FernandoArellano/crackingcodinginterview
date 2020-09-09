package com.recursionCap8;


import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;

public class TestCap8 {
    public static void main(String [] args){
        System.out.println("Fibonacci 1");
        fibonacci1(5);
        System.out.println("******************************************\n\n");

        System.out.println("Optimized Fibonacci");
        optimizedFibonacci(5, new int[6]);
        System.out.println("******************************************\n\n");

        System.out.println("Magic Fast no duplicates");
        System.out.println(new int[]{-11,-7,0,3});
        System.out.println("******************************************\n\n");

    }

    private static int fibonacci1(int n){
        if(n == 1) return 1;
        if(n== 0) return 0;
        printFibonachiVals(n);
        return fibonacci1(n-1) + fibonacci1(n-2);
    }

    private static int optimizedFibonacci(int n, int[] cache){
        if(n == 0 || n == 1) return n;


        if(cache[n] == 0){
            printFibonachiVals(n);
            cache[n] = optimizedFibonacci(n-1, cache) + optimizedFibonacci(n-2, cache);
        }
        return cache[n];
    }

    private static void printFibonachiVals(int n) {
        System.out.print(n - 1);
        System.out.print(":");
        System.out.print(n - 2);
        System.out.print("\t");
    }


    private static ArrayList<Point> getPath(boolean [][] maze){
        if(maze == null || maze.length == 0) return null;

        ArrayList<Point> path = new ArrayList<Point>();
        HashSet<Point> failedPoints = new HashSet<Point>();
        if(getPath(maze, maze.length - 1, maze[0].length-1,path, failedPoints)){
            return path;
        }
        return null;

    }

    private static boolean getPath(boolean [][] maze, int row, int col, ArrayList<Point> path, HashSet<Point> failedPoints){
        if(col < 0 || row < 0 || !maze[row][col]){
            return false;
        }
        Point p = new Point(row,col);

        if(failedPoints.contains(p)){
            return false;
        }

        boolean isAtOrigin = (row ==0) && (col == 0);

        if(isAtOrigin || getPath(maze,row,col-1,path,failedPoints) ||
                getPath(maze, row-1,col, path, failedPoints)){
            path.add(p);
            return true;
        }

        failedPoints.add(p);
        return false;
    }

    /**
     * magic index if index and array[index[ are equal not duplicated values
     * */
    static int magicFast(int [] array){
        return magicFast(array, 0, array.length-1);
    }

    private static int magicFast(int[] array, int start, int end) {
        if(start>end){
            return -1;
        }

        int mid = (start + end) / 2;
        if(array[mid] == mid){
            return mid;
        }
        else if(array[mid] > mid){
            return magicFast(array, start, mid-1);
        }
        else{
            return magicFast(array, mid+1, end);
        }
    }
}
