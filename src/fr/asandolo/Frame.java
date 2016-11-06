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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


/**
 *
 * @author Alexandre SANDOLO ft BARDON Nathan
 */
public class Frame extends JFrame {
    
    private Matrice matrice;
    private JPanel p,btns;
    private Content c;
    private JButton btn_triche,btn_reset,btn_quitte;
    private JLabel lbl_win,lbl_score;
    
    public Frame(Matrice m){
        this.matrice = m;
        
        BtnListener b = new BtnListener();
        
        this.lbl_win = new JLabel("Gagné !");
        this.lbl_win.setVisible(false);
        this.lbl_win.setForeground(Color.red);
        
        this.lbl_score = new JLabel();
        this.lbl_score.setForeground(new Color(100,255,100));
        
        
        this.btn_triche = new JButton("Trichez");
        this.btn_triche.setActionCommand("triche");
        this.btn_triche.addActionListener(b);
        
        this.btn_reset = new JButton("Reset");
        this.btn_reset.setActionCommand("reset");
        this.btn_reset.addActionListener(b);
        
        this.btn_quitte = new JButton("Quitter");
        this.btn_quitte.setActionCommand("quitte");
        this.btn_quitte.addActionListener(b);
        
        this.btns = new JPanel();
        this.btns.add(this.lbl_win);
        this.btns.add(this.btn_triche);
        this.btns.add(this.btn_reset);
        this.btns.add(this.btn_quitte);
        this.btns.add(this.lbl_score);
        
        this.c = new Content();
        
        
        this.p = new JPanel();
        this.p.setLayout(new BorderLayout());
        this.p.add(this.btns, BorderLayout.NORTH);
        this.p.add(this.c, BorderLayout.CENTER);
        
        this.setTitle("2048 !");
        this.setMinimumSize(new Dimension(400,400));
        this.setPreferredSize(new Dimension(600,600));
        this.setResizable(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(this.p);
        this.setVisible(true);
        
        
        
        this.c.addKeyListener(new KeyListener() {
            @Override public void keyTyped(KeyEvent e) {}
            @Override public void keyPressed(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {
                switch(e.getKeyCode()){
                    case KeyEvent.VK_UP:
                        matrice.haut();
                        matrice.generate();
                        break;
                    
                    case KeyEvent.VK_DOWN:
                        matrice.bas();
                        matrice.generate();
                        break;
                        
                    case KeyEvent.VK_LEFT:
                        matrice.gauche();
                        matrice.generate();
                        break;
                        
                    case KeyEvent.VK_RIGHT:
                        matrice.droite();
                        matrice.generate();
                        break;
                        
                }
                matrice.affiche();
                matrice.save();
                c.repaint();
            }
        });
        
        
        this.c.requestFocus();
    }
    
    private class BtnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                switch(e.getActionCommand()){
                    case "triche":
                        int line= -1, col = -1;
                        while(line < 0 || line > matrice.getDim()){
                            try{
                                String l = JOptionPane.showInputDialog(c, "Entrez la ligne :", "Triche 2048", JOptionPane.QUESTION_MESSAGE);
                                line = Integer.parseInt(l);
                            }catch(Exception ex){}
                        }
                        while(col < 0 || col > matrice.getDim()){
                            try{
                                String l = JOptionPane.showInputDialog(c, "Entrez la colone :", "Triche 2048", JOptionPane.QUESTION_MESSAGE);
                                col = Integer.parseInt(l);
                            }catch(Exception ex){}
                        }
                        matrice.setval(0, line-1, col-1);
                        c.repaint();
                        
                        break;
                        
                    case "quitte":
                        if(JOptionPane.showConfirmDialog(c,"Etes-vous certain de vouloir quitter?",
                                "Quitter 2048?", JOptionPane.YES_NO_OPTION) == 0)
                        System.exit(0);
                        break;
                        
                    case "reset":
                        matrice.init();
                        matrice.generate();
                        matrice.generate();
                        c.repaint();
                        break;
                    default:
                        throw new Exception("BTN NON IMPLEMENTE");
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        
    }
    
    
    private class Content extends JPanel {
        public Content(){
            this.setFocusable(true);
        }
        
        @Override
        public void paintComponent(Graphics gg){
            int[][] nbs = matrice.getMatrix();
            
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
            int margin_top = case_height/2 + g.getFontMetrics().getHeight()/2;
            
            for(int x = 0; x < nbs.length; x++){
                for(int y = 0; y < nbs[x].length; y++){
                    if(nbs[x][y] > 0){
                        int sw = g.getFontMetrics().stringWidth(""+nbs[x][y]);
                        int margin_left = case_width/2-sw/2;
                        
                        g.drawString(""+nbs[x][y], (int)(case_width*y) +margin_left,
                            (int)(case_height*x)+margin_top);
                    }
                }
            }
            
            if(matrice.iswin()){
                lbl_win.setVisible(true);
            }
            
            lbl_score.setText("Meilleur : "+matrice.getBest());
        }
    }
    
    
}
