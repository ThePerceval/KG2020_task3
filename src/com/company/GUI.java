package com.company;

import com.company.drawUtils.DrawPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;

public class GUI extends JFrame {
    private JPanel panel1;
    private DrawPanel panelForDraw;
    private JComboBox comboBoxFunction;
    private JTextField textFieldFps;
    private JButton buttonExecute;
    private JComboBox comboBoxArgA;
    private JComboBox comboBoxArgB;
    private JComboBox comboBoxArgC;
    private JComboBox comboBoxArgW;
    private JSplitPane splitPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JTextField textField9;
    private JTextField textField10;
    private JTextField textField11;
    private JLabel LabelArgA_a;
    private JLabel LabelArgA_b;
    private JLabel LabelArgA_d;
    private JLabel LabelArgB_a;
    private JLabel LabelArgB_c;
    private JLabel LabelArgB_d;
    private JLabel LabelArgC_a;
    private JLabel LabelArgC_b;
    private JLabel LabelArgC_d;
    private JLabel LabelArgW_a;
    private JLabel LabelArgW_b;
    private JLabel LabelArgW_d;
    private JTextField textField12;
    private JTextField textField13;
    private JTextField textField14;
    private JTextField textField15;
    private JPanel panelArgumentW;
    private JPanel panelInterface;
    private JLabel LabelArgA_c;
    private JLabel LabelArgB_b;
    private JLabel LabelArgC_c;
    private JLabel LabelArgW_c;
    private JPanel panelArgW4;
    private JPanel panelArg4C;
    private JPanel panelArg4B;
    private JPanel panelArg4A;
    private JPanel panelArgumentA;
    private JPanel panelArgumentB;
    private JPanel panelArgumentC;

    public GUI() throws HeadlessException {
        //panelForDraw = new DrawPanel();

        setContentPane(panel1);
        splitPanel.setBorder(null);


        comboBoxFunction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(comboBoxFunction.getSelectedIndex() == 0){
                    panelArgumentW.setVisible(false);
                    setJComponentSize(panelInterface, 250, 730);
                    //`````````panelForDraw.setFunction();
                }
                else if(comboBoxFunction.getSelectedIndex() == 1){
                    panelArgumentW.setVisible(true);
                    setJComponentSize(panelInterface, 250, 900);
                }
            }
        });
        comboBoxArgA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(comboBoxArgA.getSelectedIndex() == 1){
                    panelArg4A.setVisible(false);
                    setJComponentSize(panelArgumentA, 250, 150);
                }
                else if(comboBoxArgA.getSelectedIndex() == 0){
                    panelArg4A.setVisible(true);
                    setJComponentSize(panelArgumentA, 250, 180);
                }
            }
        });
        comboBoxArgB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(comboBoxArgB.getSelectedIndex() == 1){
                    panelArg4B.setVisible(false);
                    setJComponentSize(panelArgumentB, 250, 150);
                }
                else if(comboBoxArgB.getSelectedIndex() == 0){
                    panelArg4B.setVisible(true);
                    setJComponentSize(panelArgumentB, 250, 180);
                }
            }
        });
        comboBoxArgC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(comboBoxArgC.getSelectedIndex() == 1){
                    panelArg4C.setVisible(false);
                    setJComponentSize(panelArgumentC, 250, 150);
                }
                else if(comboBoxArgC.getSelectedIndex() == 0){
                    panelArg4C.setVisible(true);
                    setJComponentSize(panelArgumentC, 250, 180);
                }
            }
        });
        comboBoxArgW.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(comboBoxArgW.getSelectedIndex() == 1){
                    panelArgW4.setVisible(false);
                    setJComponentSize(panelArgumentW, 250, 150);
                }
                else if(comboBoxArgW.getSelectedIndex() == 0){
                    panelArgW4.setVisible(true);
                    setJComponentSize(panelArgumentB, 250, 200);
                }
            }
        });
    }

    private void setJComponentSize(JComponent component, int width, int height){
        component.setMinimumSize(new Dimension(width, height));
        component.setPreferredSize(new Dimension(width, height));
        component.setMaximumSize(new Dimension(width, height));
    }

    private void createUIComponents() {
        panelForDraw = new DrawPanel();
    }
}
