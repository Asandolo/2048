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
       Matrice m = new Matrice(4);
       m.init();
       m.generate();
       m.affiche();
       
       while(fq == false){

            do{
               try{
                  System.out.println("Choisisez : H = Haut, B = Bas, G = Gauche, D = Droite");
                   System.out.print(">");
                  chx = sc.nextLine().charAt(0);
               }catch(Exception e){
                   System.out.println("Erreur :"+e);
               }


            }while(chx != 'D' && chx != 'G' && chx != 'H' && chx != 'B' && chx != 'f');

             switch(chx){
                 case 'H':
                     m.haut();
                     m.generate();
                     m.affiche();
                     break;
                 case 'B':
                     m.bas();
                     break;
                 case 'G':
                     m.gauche();
                 case 'D':
                     m.droite();
                     break;
                 case 'f':
                     fq = true;
                     break;                     
                 default:
                     System.out.println("Erreur");
                     break;

             }

         }
    }
    
}
