/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nus.bala.coursera.princeton.algo1;

/**
 *
 * @author prasannav
 */
public class QF {
    
    int array[];

    public int[] getArray() {
        return array;
    }
    
    public QF(int size)
    {
     array = new int[size];
     for (int i = 0; i < array.length; i++) {array[i] = i;}
    }
    
     
    public boolean connected (int x, int y)
    {
        return array[x] == array[y];
    }
    
    public void union(int p, int q)
    {
        int x = array[p];
        int y =  array[q];
        for (int i = 0; i < array.length; i++) {
            if(x == array[i])
            {
                array[i] = y;
            }
        }
        
    }
    
   
     // util methods
    public void print(){
        System.out.println("");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
        }
    }
    
}
