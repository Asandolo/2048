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
    
    public int getDim(){
        return this.dim;
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
   
    
    public void setval(int x ,int ligne, int colone){
        this.mat[ligne][colone] = x;
    }
    
    
    public void dhaut(){
        for (int i = this.dim-1; i > 0; i--) {
            for (int j = 0; j < this.dim; j++) {
                if(this.mat[i-1][j] == 0){
                    this.mat[i-1][j] = this.mat[i][j];
                    this.mat[i][j]=0;
                }

            }
        }   
    }
    
    
    public void haut(){
        this.dhaut();
        for (int i = this.dim-1; i > 0; i--) {
             for (int j = 0; j < this.dim; j++) {
                 if(this.mat[i-1][j] == this.mat[i][j]){
                     this.mat[i-1][j] = this.mat[i][j]*2;
                     this.mat[i][j]=0;
                 }
             }
         }
       this.dhaut();
    }
    
    public void dbas(){
        for (int i = 0; i < this.dim-1; i++) {
            for (int j = 0; j < this.dim; j++) {
                if(this.mat[i+1][j] == 0){
                    this.mat[i+1][j] = this.mat[i][j];
                    this.mat[i][j]=0;
                }
            }
        }

    }
    
        public void bas(){
            this.dbas();
        for (int i = 0; i < this.dim-1; i++) {
            for (int j = 0; j < this.dim; j++) {
                if(this.mat[i+1][j] == this.mat[i][j]){
                    this.mat[i+1][j] = this.mat[i][j]*2;
                    this.mat[i][j]=0;
                }
            }
        }
        
        this.dbas();

    }
    
    public void dgauche(){
        for (int j = this.dim-1; j > 0; j--) {
           for (int i = 0; i < this.dim; i++) {
               if(this.mat[i][j-1] == 0){
                   this.mat[i][j-1] = this.mat[i][j];
                   this.mat[i][j]=0;
               }
           }
       }
    }
    
        public void gauche(){
            this.dgauche();
        for (int j = this.dim-1; j > 0; j--) {
           for (int i = 0; i < this.dim; i++) {
                if(this.mat[i][j-1] == this.mat[i][j]){
                    this.mat[i][j-1] = this.mat[i][j]*2;
                    this.mat[i][j]=0;
                }
           }
       }
        this.dgauche();
    }
   
    public void ddroite(){
        for (int j = 0; j < this.dim-1; j++) {
           for (int i = 0; i < this.dim; i++) {
               if(this.mat[i][j+1] == 0){
                   this.mat[i][j+1] = this.mat[i][j];
                   this.mat[i][j]=0;
               }
           }
       }
    }
    
    public void droite(){
        this.ddroite();
        for (int j = 0; j < this.dim-1; j++) {
           for (int i = 0; i < this.dim; i++) {
                if(this.mat[i][j+1] == this.mat[i][j]){
                    this.mat[i][j+1] = this.mat[i][j]*2;
                    this.mat[i][j]=0;
                }
           }
       }
        this.ddroite();
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
 
    public boolean iswin(int w){
        for (int i = 0; i < this.dim; i++) {
            for (int j = 0; j < this.dim; j++) {
                 if(this.mat[i][j] == w){
                  return true; 
                 }
            }
        }
        
        return false;
    
    }
    
    public void generate(){
        boolean b = true;
        int lign, col, x, r;
        
        while(b == true){
           lign = (int)( Math.random()*( this.dim-1 - 0 + 1 ) ) + 0;
           col =  (int)( Math.random()*( this.dim-1 -0 + 1 ) ) + 0;
           
           r = (int)( Math.random()*( 2 - 1 + 1 ) ) + 1;
          x = (r == 1)? 2: 4;
           
           
                   
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
    
    public int getscore(){
        int s = 0;
        for (int i = 0; i < this.dim; i++) {
            for (int j = 0; j < this.dim; j++) {
                s=s+this.mat[i][j];
            }
        }
        return s;
    }
        
}
