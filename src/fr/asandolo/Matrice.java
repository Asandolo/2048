/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.asandolo;

import java.nio.file.Files;
import org.json.JSONObject;



/**
 *
 * @author Alexandre SANDOLO
 */
public class Matrice{
    private int mat[][];
    private int  sav[][];
    private final int dim;
    private int win;
    private int best = 0;

    public Matrice(int d, int w) {
        this.dim = d;
        this.win = w;
        
        this.mat = new int[d][d];
        this.sav = new int[d][d];
    }
    
    public void save(){
        for (int i = 0; i < this.dim; i++) {
            for (int j = 0; j < this.dim; j++) {
                this.sav[i][j] = this.mat[i][j];
            }
        }
    }
    
    public void saveinfile(){
        JSONObject o = new JSONObject("{\"score\":0}");
         if(Files.exists(Main.DATAFILE)){
           try{
                String content = new String(Files.readAllBytes(Main.DATAFILE));
                o = new JSONObject(content);
           }catch(Exception e){}
         }
         
        if(this.getscore() > o.getInt("score")){
            o.put("score", this.getscore());
        }
        
        this.best = o.getInt("score");
         
        JSONObject save = new JSONObject();
        save.put("dim", this.dim);
        save.put("w", this.win);
        save.put("datas", this.mat);
        o.put("save", save);
        try{
            Files.write(Main.DATAFILE, o.toString().getBytes());
        }catch(Exception e){
            e.printStackTrace();
        }
        /**
         * Sauvegarde la matrice dans un .json
         */
    }
    
    public void init(){
        for (int i = 0; i < this.dim; i++) {
            for (int j = 0; j < this.dim; j++) {
                this.mat[i][j] = 0;
            }
        }
        /**
         * Inisialisation de la matrice
         */
    }
    
   
    public void turnMat(int x){
        
        for (int t = 1; t <=x; t++) {
            int[][] s = new int[this.dim][this.dim];

                for (int i = 0; i < this.dim; i++) {
                    for (int j = 0; j < this.dim; j++) {
                        s[i][j] = this.mat[this.dim - j - 1][i];
                    }
                }
                this.mat = s;    
        }
                

    } 
    
    public int getBest(){
        return this.best;
    }
    
    public int getDim(){
        return this.dim;
    }
    
    
    public void affiche(){
        for (int i = 0; i < this.dim; i++) {
            for (int j = 0; j < this.dim; j++) {
                if (this.mat[i][j] != 0) {
                    System.out.print(this.mat[i][j]+"|");
                }else{
                    System.out.print(" |");                    
                }

            }
            System.out.println();
        }
    }
    
   
    
    public void setval(int x ,int ligne, int colone){
        this.mat[ligne][colone] = x;
        
    }
    
        private void dbas(){
		int k,l;
		for(int j=this.dim-1;j>=0;j--){
			for(int i=this.dim-1;i>=0;i--){
				if(this.mat[i][j]==0){
					k=i;
					l=i;
					while(k>=0){
						if(this.mat[k][j]!=0 && this.mat[l][j]==0){
							this.mat[l][j]=this.mat[k][j];
							this.mat[k][j]=0;
							while(this.mat[l][i]!=0 && l>0){
								l=l-1;
							}
						}
						k=k-1;
					}
				}
			}
		}
	}
        
        
        private boolean canGenerate(){
            for (int i = 0; i < this.dim; i++) {
                for (int j = 0; j < this.dim; j++) {
                    if (this.sav[i][j] != this.mat[i][j]) {
                        return true;
                    }
                }
            }
            
            return false;
        }
    
        public void bas(){
		this.dbas();
		for(int j=this.dim-1;j>=0;j--){
			for(int i=this.dim-1;i>0;i--){
				if(i>0){
					if(this.mat[i][j]==this.mat[i-1][j] && this.mat[i][j]!=0){
						this.mat[i][j]=this.mat[i-1][j]*2;
						this.mat[i-1][j]=0;
					}
				}
			}
		}
		this.dbas();
           if (this.canGenerate()) {
             this.generate();
            }
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
 
    public boolean iswin(){
        for (int i = 0; i < this.dim; i++) {
            for (int j = 0; j < this.dim; j++) {
                 if(this.mat[i][j] == this.win){
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
           
           
                   
           if(this.isComplete()){
               b = false;
           }else{
                if(this.mat[lign][col] == 0){
                    this.setval(x, lign, col);
                    b = false;
                }
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
    
    public int[][] getMatrix(){
        return this.mat;
    }
    
    
    public static Matrice resume(int[][] m, int d, int w){
        Matrice ret = new Matrice(d,w);
        for(int x = 0; x<m.length;x++)
            for(int y = 0; y<m[x].length;y++)
                ret.setval(m[x][y], x, y);
        return ret;
    }
}
