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
       Scanner sc = new Scanner(System.in);
       boolean fq = false;
       char chx = 'A';
       int d,w = 0;
       
       
        System.out.print("Dimention de la matrice:");
        d =  sc.nextInt();
        System.out.println(" ");
        
        System.out.print("Tuille de fin :");
        w =  sc.nextInt();
        System.out.println(" ");
                
       
       
       Matrice m = new Matrice(d);
       m.init();
       m.generate();
       m.generate();
       m.affiche();
       
       while(fq == false){
           if (m.iswin(w)) {
               System.out.println("Gagner !");
           }
           System.out.println("Score : "+m.getscore());
            do{
               try{
                  System.out.println("Choisisez : H = Haut, B = Bas, G = Gauche, D = Droite Q = Quiter T= Triche");
                   System.out.print(">");
                  chx = sc.nextLine().charAt(0);
               }catch(Exception e){
                   System.out.println("Erreur :"+e);
               }


            }while(chx != 'D' && chx != 'G' && chx != 'H' && chx != 'B' && chx != 'Q' && chx != 'T');

             switch(chx){
                 case 'H' :
                     m.haut();
                     m.generate();
                     m.affiche();
                     break;
                 case 'B':
                     m.bas();
                     m.generate();
                     m.affiche();
                     break;
                 case 'G':
                     m.gauche();
                     m.generate();
                     m.affiche();
                     break;
                 case 'D':
                     m.droite();
                     m.generate();
                     m.affiche();
                     break;
                 case 'Q':
                     fq = true;
                     break;
                 case 'T':
                     int a,b;
                     System.out.println("Quel case voulez vous supprimer ?");
                     System.out.println("Num ligne :");
                     a = sc.nextInt();
                     System.out.println("Num colone ");
                     b = sc.nextInt();
                     
                     m.setval(0, a-1, b-1);
                     m.affiche();
                     break;
                 default:
                     System.out.println("Erreur");
                     break;

             }

         }
    }
    
}
