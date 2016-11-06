/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.asandolo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 *
 * @author user
 */
public class Frame extends JFrame {
    
    
    private JPanel p,btns;
    private Content c;
    private JButton btn_triche,btn_reset,btn_quitte;
    
    public Frame(){
        this.btn_triche = new JButton("Trichez");
        this.btn_reset = new JButton("Reset");
        this.btn_quitte = new JButton("Quitter");
        
        this.btns = new JPanel();
        this.btns.add(this.btn_triche);
        this.btns.add(this.btn_reset);
        this.btns.add(this.btn_quitte);
        
        this.c = new Content();
        
        
        this.p = new JPanel();
        this.p.setLayout(new BorderLayout());
        this.p.add(this.btns, BorderLayout.NORTH);
        this.p.add(this.c, BorderLayout.CENTER);
        
        this.setTitle("2048 !");
        this.setMinimumSize(new Dimension(400,400));
        this.setPreferredSize(new Dimension(600,600));
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(this.p);
        this.setVisible(true);
    }
    
    public void setMatrix(int[][] mat){
        this.c.setMatrix(mat);
    }
    
    private class Content extends JPanel {
        int [][] nbs = {{}};
        
        public void setMatrix(int[][] mat){
            this.nbs = mat;
            repaint();
        }
        
        @Override
        public void paintComponent(Graphics gg){
            Graphics2D g = (Graphics2D)gg;
            
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            
            int w = g.getClipBounds().width;
            int h = g.getClipBounds().height;
            
            g.clearRect(0,0,w,h);
            
            // Draw table
            g.setColor(new Color(255, 100, 100));
            final int case_width = w/nbs.length;
            final int case_height = h/nbs.length;
            
            for(int i = case_width; i < w; i+= case_width)
                g.drawLine(i, 0, i, h);
            
            for(int i = case_height; i < w; i+= case_height)
                g.drawLine(0, i, w, i);
            
            //Draw numbers
            g.setFont(new Font("Arial", Font.PLAIN, 18));
            g.setColor(new Color(100, 100, 255));
            
            for(int x = 0; x < nbs.length; x++){
                for(int y = 0; y < nbs[x].length; y++){
                    if(nbs[x][y] > 0)
                        g.drawString(""+nbs[x][y], (int)(case_width*y),
                            (int)(case_height*(x+1)));
                }
            }
        }
    }
    
    
}
