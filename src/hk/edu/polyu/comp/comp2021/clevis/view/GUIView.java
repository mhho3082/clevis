package hk.edu.polyu.comp.comp2021.clevis.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


import hk.edu.polyu.comp.comp2021.clevis.controller.CommandHandler;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class GUIView{

    private final CommandHandler handler;

    public GUIView(CommandHandler handler) {
        this.handler = handler;
    }

    
    public void exec(){

        JFrame mainf = new JFrame("CLEVIS GUI");
        JTextField userinput = new JTextField();
        JButton enter = new JButton("Enter");
        
        mainf.setResizable(false);
        mainf.getContentPane().setBackground(Color.blue);
        mainf.setSize(1200, 650);
        
        JOptionPane.showMessageDialog(null,"Welcome to GP 32 CLEEVIS");

        userinput.setBounds(250,550,600,30);
        enter.setBounds(900,550,80,30);

        mainf.add(userinput);
        mainf.add(enter);

        mainf.setLayout(null);
        mainf.setVisible(true);
        enter.setVisible(true);
        userinput.setVisible(true);


    }

    

}