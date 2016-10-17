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
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Matrice m = new Matrice(4);
       m.init();
       m.affiche();
       m.setval(2, 0, 0);
       System.out.println(" ");
       m.affiche();
        System.out.println("test");
    }
    
}
