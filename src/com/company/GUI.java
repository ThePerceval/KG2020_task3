package com.company;

import com.company.Function.Function_1;
import com.company.Function.*;
import com.company.Function.ITimeDependentParam;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class GUI extends JFrame {
    private JPanel panel1;
    private JSplitPane splitPanel;
    private DrawPanel panelForDraw;
    private JPanel panelInterface;
    private JPanel panelArgumentA;
    private JPanel panelArgumentB;
    private JPanel panelArgumentC;
    private JPanel panelArgumentW;
    private JButton buttonExecute;

    private JComboBox comboBoxFunction;
    private JComboBox comboBoxArgA;
    private JComboBox comboBoxArgB;
    private JComboBox comboBoxArgC;
    private JComboBox comboBoxArgW;

    private JTextField textFieldFps;

    private JTextField textFieldArgA1;
    private JTextField textFieldArgA2;
    private JTextField textFieldArgA3;
    private JTextField textFieldArgA4;

    private JTextField textFieldArgB1;
    private JTextField textFieldArgB2;
    private JTextField textFieldArgB3;
    private JTextField textFieldArgB4;

    private JTextField textFieldArgC1;
    private JTextField textFieldArgC2;
    private JTextField textFieldArgC3;
    private JTextField textFieldArgC4;

    private JTextField textFieldArgW1;
    private JTextField textFieldArgW2;
    private JTextField textFieldArgW3;
    private JTextField textFieldArgW4;
    private JButton ButtonCenterAxis;
    private JButton ButtonExamples;
    private JButton ButtonStart;

    private Timer timer;

    public GUI() throws HeadlessException {
        setContentPane(panel1);
        splitPanel.setBorder(null);


        comboBoxFunction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(comboBoxFunction.getSelectedIndex() == 0){
                    panelArgumentW.setVisible(false);
                    setJComponentSize(panelInterface, 250, 730);
                }
                else if(comboBoxFunction.getSelectedIndex() == 1){
                    panelArgumentW.setVisible(true);
                    setJComponentSize(panelInterface, 250, 1000);
                }
            }
        });


        buttonExecute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<ITimeDependentParam> listParam = new ArrayList<ITimeDependentParam>();
                ITimeDependentParam A = comboBoxArgA.getSelectedIndex() == 0 ?
                                new FunctionArgument_1(getArray(textFieldArgA1, textFieldArgA2, textFieldArgA3, textFieldArgA4)) :
                                new FunctionArgument_2(getArray(textFieldArgA1, textFieldArgA2, textFieldArgA3)),
                        B = comboBoxArgB.getSelectedIndex() == 0 ?
                                new FunctionArgument_1(getArray(textFieldArgB1, textFieldArgB2, textFieldArgB3, textFieldArgB4)) :
                                new FunctionArgument_2(getArray(textFieldArgB1, textFieldArgB2, textFieldArgB3)),
                        C = comboBoxArgC.getSelectedIndex() == 0 ?
                                new FunctionArgument_1(getArray(textFieldArgC1, textFieldArgC2, textFieldArgC3, textFieldArgC4)) :
                                new FunctionArgument_2(getArray(textFieldArgC1, textFieldArgC2, textFieldArgC3));
                listParam.add(A);
                listParam.add(B);
                listParam.add(C);
                if(comboBoxFunction.getSelectedIndex() == 0){
                    panelForDraw.setFunction(new Function_1(listParam));
                }
                else{
                    ITimeDependentParam W = comboBoxArgW.getSelectedIndex() == 0 ?
                        new FunctionArgument_1(getArray(textFieldArgW1, textFieldArgW2, textFieldArgW3, textFieldArgW4)) :
                        new FunctionArgument_2(getArray(textFieldArgW1, textFieldArgW2, textFieldArgW3));
                    listParam.add(W);
                    panelForDraw.setFunction(new Function_2(listParam));
                }
                panelForDraw.setTime(0);
            }
        });
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelForDraw.drawTimeFunction(Double.parseDouble(textFieldFps.getText()));

            }
        });

        ButtonStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(timer.isRunning()){
                    timer.stop();
                    ButtonStart.setText("Запустить");
                }
                else{
                    int delay;
                    try{
                        delay = Integer.parseInt(textFieldFps.getText());
                    } catch(NumberFormatException ex){
                        JOptionPane.showMessageDialog(null, "Не удалось прочитать значение периода", "Ошибка",
                                JOptionPane.INFORMATION_MESSAGE);
                        ex.printStackTrace();
                        return;
                    }
                    if (delay < 0) {
                        JOptionPane.showMessageDialog(null, "Период не может быть отрицательным", "Ошибка",
                                JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    ButtonStart.setText("Остановить");
                    timer.setDelay(delay);
                    timer.setInitialDelay(delay);
                    timer.start();
                }

            }
        });

        ButtonCenterAxis.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelForDraw.setCenterCoordinates();
            }
        });

        actionForComboBoxArg(comboBoxArgA);
        actionForComboBoxArg(comboBoxArgB);
        actionForComboBoxArg(comboBoxArgC);
        actionForComboBoxArg(comboBoxArgW);
    }

    private void setJComponentSize(JComponent component, int width, int height){
        component.setMinimumSize(new Dimension(width, height));
        component.setPreferredSize(new Dimension(width, height));
        component.setMaximumSize(new Dimension(width, height));
    }

    private void createUIComponents() {
        panelForDraw = new DrawPanel();
    }

    private List<Double> getArray(JTextField tf1, JTextField tf2, JTextField tf3){
        List<Double> list = new ArrayList<Double>();
        list.add(Double.parseDouble(tf1.getText()));
        list.add(Double.parseDouble(tf2.getText()));
        list.add(Double.parseDouble(tf3.getText()));
        return list;
    }
    private List<Double> getArray(JTextField tf1, JTextField tf2, JTextField tf3, JTextField tf4){
        List<Double> list = new ArrayList<Double>();
        list.add(Double.parseDouble(tf1.getText()));
        list.add(Double.parseDouble(tf2.getText()));
        list.add(Double.parseDouble(tf3.getText()));
        list.add(Double.parseDouble(tf4.getText()));
        return list;
    }

    private void actionForComboBoxArg(final JComboBox box){
        box.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(box.getSelectedIndex() == 0){
                    box.getParent().getComponent(4).setVisible(true);
                    setJComponentSize((JComponent)box.getParent(), 250, 200);
                }
                else if(box.getSelectedIndex() == 1){
                    box.getParent().getComponent(4).setVisible(false);
                    setJComponentSize((JComponent)box.getParent(), 250, 150);
                }
            }
        });
    }
}
