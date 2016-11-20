/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.asandolo;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import javax.swing.JOptionPane;
import org.json.JSONArray;
import org.json.JSONObject;



/**
 *
 * @author Alexandre SANDOLO
 */
public class Main {
    public static final Path DATAFILE = FileSystems.getDefault().getPath("save.json");
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Main();
    }

    public Main(){  
       Scanner sc = new Scanner(System.in);
       int d = -1,w = -1;
       Matrice m = null;
       boolean q = false;
       
       
       if(Files.exists(Main.DATAFILE)){
           try{
                String content = new String(Files.readAllBytes(Main.DATAFILE));
                JSONObject o = new JSONObject(content);
                
                if(o.has("save")&&
                        JOptionPane.showConfirmDialog(null, "Voulez vous reprendre la partie en cours ?", "Reprendre?", JOptionPane.YES_NO_OPTION) == 0){
                    int dim = o.getJSONObject("save").getInt("dim");
                    int win = o.getJSONObject("save").getInt("w");
                    JSONArray datas = o.getJSONObject("save").getJSONArray("datas");
                    int[][] mat = new int[dim][dim];
                    for(int x = 0;x<datas.length();x++){
                        JSONArray sub = datas.getJSONArray(x);
                        for(int y = 0; y<sub.length();y++){
                            mat[x][y] = sub.getInt(y);
                        }
                    }
                    m = Matrice.resume(mat, dim, win);
                }
           }catch(Exception e){
               e.printStackTrace();
           }
       }
       
       if(m == null){
            int o = JOptionPane.showConfirmDialog(null, "Voulez vous changez les options ?", "Option", JOptionPane.YES_NO_CANCEL_OPTION);
            switch(o){
                case 0:
                    while(d < 2 || d >= 16){
                        try {
                            String dim = JOptionPane.showInputDialog(null, "Taille de la grille (ex 4 pour 4*4, max 16) :", "Option", JOptionPane.QUESTION_MESSAGE);
                            d = Integer.parseInt(dim);
                        } catch (Exception e) {}
                    }
                    while(w<=0){
                        String win = JOptionPane.showInputDialog(null, "Tuille gagante (ex: 2048):", "Option", JOptionPane.QUESTION_MESSAGE);
                        w = Integer.parseInt(win);                    
                    }
                    break;
                case 1:
                        d = 4;
                        w = 2048;
                    break;
                case -1:
                case 2:
                    System.exit(0);
                    break;
            }
       
           m = new Matrice(d, w);
           m.init();
           m.generate();
           m.generate();
           m.affiche();
           m.save();
       }
       
       Frame f = new Frame(m);
       f.repaint();
       while(q==false){
           char chx = ' ';
           if (m.iswin()) {
               System.out.println("Gagner !");
           }
           
           System.out.println("Score : "+m.getscore());
           
           do {  
               
               try {
                  System.out.println("Choisisez : H= Haut, B= Bas, G= Gauche, D= Droite Q= Quiter T= Triche R=Reset");
                   System.out.print(">");
                   String l = sc.nextLine();
                   if(l.length()>0) chx = l.charAt(0);                   
               } catch (Exception e) {
                   e.printStackTrace();
               }
               
           } while (chx != 'D' && chx != 'G' && chx != 'H' && chx != 'B' && chx != 'Q' && chx != 'R' && chx != 'T');
       
            
           switch(chx){
               case 'H':
                   m.turnMat(2);
                   m.save();
                   m.bas();
                   m.turnMat(2);
                   break;
               case 'B':
                   m.save();
                   m.bas();
                   break;
               case 'G':
                   m.turnMat(3);
                   m.save();
                   m.bas();
                   m.turnMat(1);
                   break;
               case 'D':
                   m.turnMat(1);
                   m.save();
                   m.bas();
                   m.turnMat(3);
                   break;
               case 'Q':
                   q = true;
                   System.exit(0);
                   break;
               case 'R':
                   m.init();
                   m.generate();
                   break;
               case 'T':
                      int a=0,b=0;
                      System.out.println("Quel case voulez vous supprimer ?");
                      try {
                         System.out.println("Num ligne :");
                         a = sc.nextInt();
                                  
                         System.out.println("Num colone ");
                         b = sc.nextInt();
                      } catch (Exception e) {
                          e.printStackTrace();
                      }
                      
                      m.setval(0, a-1, b-1);                  
                   break;
               default:
                   System.out.println("Erreur :");
                break;            
           }
           m.affiche();
           m.saveinfile();
           f.repaint();
           
       
       }
       m.saveinfile();
       
    }
    
}
