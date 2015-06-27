/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nus.bala.coursera.princeton.algo1;

import java.util.Random;

/**
 *
 * @author prasannav
 */
public class WQU_test {
    
    public static void main(String[] args) {
    
    
    WQU quickUnion = new WQU(10);
      
        Random r = new Random();
//        int x = r.nextInt(9);
//        int y = r.nextInt(9);
        
        performUnion(quickUnion, 9, 6);
        performUnion(quickUnion, 8, 2);
        performUnion(quickUnion, 0, 8);
        performUnion(quickUnion, 3, 4);
        performUnion(quickUnion, 5, 1);
        performUnion(quickUnion, 4, 1);
        performUnion(quickUnion, 6, 2);
        performUnion(quickUnion, 4, 2);
        performUnion(quickUnion, 8, 7);
        
        //9-6 8-2 0-8 3-4 5-1 4-1 6-2 4-2 8-
        
        System.out.println(">> Test >>");
        int[] testArry = {0,4,4,4,0,2,4,2,4,4};
        int[] testArry1 = {5,0,0,6,0,3,0,0,0,6};
        
        //WQU.findRoots(testArry1);

                
//        performUnion(quickUnion, 4, 3);
//        performUnion(quickUnion, 3, 8);
//        performUnion(quickUnion, 6, 5);
//        performUnion(quickUnion, 9, 4);
//        performUnion(quickUnion, 2, 1);
//        performUnion(quickUnion, 5, 0);
//        performUnion(quickUnion, 7, 2);
//        performUnion(quickUnion, 6, 1);
//        performUnion(quickUnion, 7, 3);

    } 
    public static void performUnion(WQU quickUnion, int x, int y){
     
        if(!quickUnion.connected(x, y))
        {
            quickUnion.union(x, y);
            quickUnion.print();
        }
    }
}
