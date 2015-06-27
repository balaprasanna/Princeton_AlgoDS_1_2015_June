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
public class QU {
    // variable, property declaration
    int array[];
    
    
    //gettters
    public int[] getArray() {
        return array;
    }
   
    // constructor
    public QU(int size)
    {
        array = new int[size];
        for (int i = 0; i < array.length; i++) {array[i] = i;}
    }
    
    //operation methods
    public boolean connected (int x, int y){
        return root(x)==root(y);
    }
    
    public int root(int x){
        while (array[x] != x){
            x = array[x];
        }
        return x;
    }
    
    public void union(int p, int q){
        int leftRoot = root(p);
        int rightRoot = root(q);
      // Take root of the first iteam, then make it as child of second item
      array[leftRoot] = rightRoot;
    }
    
    // util methods
    public void print(){
        System.out.println("");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
        }
    }
    
}
