/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.asandolo;

/**
 *
 * @author asandolo
 */
public class Matrice{
    private final int mat[][];
    private final int dim;
    
    public Matrice(int d){
        this.dim = d;
        
        this.mat = new int[d][d];
    }
    
    public void init(){
        for (int i = 0; i < this.dim; i++) {
            for (int j = 0; j < this.dim; j++) {
                this.mat[i][j] = 0;
            }
        }
    }
    
    public void affiche(){
        for (int i = 0; i < this.dim; i++) {
            for (int j = 0; j < this.dim; j++) {
                System.out.print(this.mat[i][j]+" ");
            }
            System.out.println(" ");
        }
    }
    
    public void setval(int x ,int ligne, int colone){
        this.mat[ligne][colone] = x;
    }
        
}
