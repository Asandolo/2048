/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.asandolo;

import java.util.Scanner;

/**
 *
 * @author Alexandre SANDOLO
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Main();
    }

    public Main(){ 
       
       Scanner sc = new Scanner(System.in);
       boolean fq = false;
       char chx = ' ';
       char c = ' ';
       int d = 0,w = 0;
       
       
        do {
            try {  
                System.out.println("Voulez vous Changez les options O= Oui N= Non");
                System.out.print(">");
                c = sc.nextLine().charAt(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (c != 'O' && c != 'N');
        
        if (c == 'O') {
            try {
                System.out.print("Dimention de la matrice >");
                d =  sc.nextInt();
                System.out.println(" ");
            } catch (Exception e) {
                e.printStackTrace();
            }
           
            try {
                System.out.print("Tuille de fin >");
                w =  sc.nextInt();
                System.out.println(" ");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(c == 'N') {
            d = 4;
            w = 2048;
        }
                
       
       
       Matrice m = new Matrice(d);
       m.init();
       m.generate();
       m.generate();
       m.affiche();
       
       Frame f = new Frame(m);
       f.repaint();
       
       while(fq == false){
           chx = ' ';
           if (m.iswin(w)) {
               System.out.println("Gagner !");
           }
           System.out.println("Score : "+m.getscore());
            do{
               try{
                  System.out.println("Choisisez : H= Haut, B= Bas, G= Gauche, D= Droite Q= Quiter T= Triche R=Reset");
                  System.out.print(">");
                  String l = sc.nextLine();
                  if(l.length()>0) chx = l.charAt(0);
               }catch(Exception e){
                   e.printStackTrace();
               }


            }while(chx != 'D' && chx != 'G' && chx != 'H' && chx != 'B' && chx != 'Q' && chx != 'R' && chx != 'T');

             switch(chx){
                 case 'H' :
                     m.haut();
                     m.generate();
                     break;
                 case 'B':
                     m.bas();
                     m.generate();
                     break;
                 case 'G':
                     m.gauche();
                     m.generate();
                     break;
                 case 'D':
                     m.droite();
                     m.generate();
                     break;
                 case 'Q':
                     fq = true;
                     break;
                 case 'R':
                     m.init();
                     m.generate();
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
                     System.out.println("Erreur");
                     break;
             }
             m.affiche();
             f.repaint();
         }
    }
    
}
