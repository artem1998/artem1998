package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleGUI extends JFrame {
    JTextField inputLine = new JTextField();
    JButton jButton = new JButton("+");
    ButtonListener buttonListener = new ButtonListener();
    int numberInputCharacters = 8;
    public SimpleGUI() {
        super("Мой первый калькулятор");
        this.setSize(470,310);
     //   this.setBounds(1,1,300,350);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel jPanel = new JPanel(new GridLayout(1,2,5,50));
        jPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        inputLine.setEnabled(false);
        jPanel.add(inputLine);
        jButton.addActionListener(buttonListener);
        jPanel.add(jButton);
        this.getContentPane().add(BorderLayout.NORTH,jPanel);

        JPanel jPanel1 = new JPanel(new GridLayout(4,4,10,10));
        jPanel1.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        for(String v: "123-456*789/.0C=".split("")) {
            JButton button = new JButton(v);
            button.addActionListener(buttonListener);
            button.setSize(10,40);
            jPanel1.add(button);
        }
        this.getContentPane().add(BorderLayout.CENTER,jPanel1);
    }
    class ButtonListener implements ActionListener {
        double a = 0,b = 0;
        String sign = "";
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand() == "+" | e.getActionCommand() == "-" | e.getActionCommand().toCharArray()[0] == '*' | e.getActionCommand().toCharArray()[0] == '/') {
                a = Double.parseDouble(inputLine.getText());
                sign = e.getActionCommand();
                inputLine.setText("");
            }
            else {
                switch (e.getActionCommand()) {
                    case ("C"):
                        inputLine.setText("");
                        a = 0;
                        b = 0;
                        break;
                    case ("."):
                        for (char c: inputLine.getText().toCharArray()) {
                            if(c == '.') {
                                return;
                            }
                        }
                        if(inputLine.getText().length() >= 1)
                            inputLine.setText(inputLine.getText() + e.getActionCommand());
                        break;
                    case ("="):
                        b = Double.parseDouble(inputLine.getText());
                        inputLine.setText(String.valueOf(calc(a,b,sign)));
                        break;
                    default:
                        if(inputLine.getText().length() < numberInputCharacters)
                            inputLine.setText(inputLine.getText() + e.getActionCommand());
                        break;
                }
                if(inputLine.getText().length() >= 2) {
                    if(inputLine.getText().toCharArray()[0] == '0' & inputLine.getText().toCharArray()[1] == '0')
                        inputLine.setText("0");
                }

            }
        }
        public double calc(double num1, double num2, String operation) {
            double result = 0;
            switch (operation) {
                case ("+"):
                    result = num1 + num2;
                    break;
                case ("-"):
                    result = num1 - num2;
                    break;
                case ("*"):
                    result = num1 * num2;
                    break;
                case ("/"):
                    if( num1 != 0)
                        result = num1 / num2;
                    break;
            }
            return result;
        }
    }

}
