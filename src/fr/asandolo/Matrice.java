/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.asandolo;

/**
 *
 * @author Alexandre SANDOLO
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
    
    //TODO A metre en String ASAP
    public void affiche(){
        for (int i = 0; i < this.dim; i++) {
            for (int j = 0; j < this.dim; j++) {
                System.out.print(this.mat[i][j]+" ");
            }
            System.out.println(" ");
        }
    }
   
    private void rotate(int angle) {
        

        
        
    }
    
    private void setval(int x ,int ligne, int colone){
        this.mat[ligne][colone] = x;
    }
    
    
    public void gauche(){
        System.out.println("Droite !");
    }
    
    public void droit(){
        rotate(180);
    }
    
    public void haut(){
        System.out.println("Haut !");
    }
    
    public void bas(){
        System.out.println("Bas !");
    }
    
    public boolean isComplete(){
        for (int i = 0; i < this.dim; i++) {
            for (int j = 0; j < this.dim; j++) {
                 if(this.mat[i][j] == 0){
                  return false; 
                 }
            }
        }
        
        return true;
    
    }
    
    public void generate(){
        boolean b = true;
        int lign, col, x, r;
        
        while(b == true){
           lign = (int)( Math.random()*( 3 - 0 + 1 ) ) + 0;
           col =  (int)( Math.random()*( 3 - 0 + 1 ) ) + 0;
           
           r = (int)( Math.random()*( 2 - 1 + 1 ) ) + 1;
           if(r == 1){
               x = 2;
           }else{
               x = 4;
           }
           
                   
           if(this.isComplete() == false){
                if(this.mat[lign][col] == 0){
                    this.setval(x, lign, col);
                    b = false;
                }
           }else{
               b = false;
           }
             
            
           
        }
           
    }
        
}
