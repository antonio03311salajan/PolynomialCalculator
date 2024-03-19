package ro.tuc.gui;

import ro.tuc.bussineslogic.Operation;
import ro.tuc.datamodels.Polynomial;

import javax.swing.*;
import java.awt.*;

public class CalculatorInterface extends JFrame {
    private JPanel interfacePanel;
    private JTextField tfP1;
    private JTextField tfP2;
    private JButton butonAdunare;
    private JButton butonScadere;
    private JButton butonInmultire;
    private JButton butonImpartire;
    private JButton butonDerivare;
    private JButton butonIntegrare;
    private JTextField tfExpresie;
    private JTextField tfRest;
    private JLabel labelRest;

    private static final int WIDTH=620;
    private static final int HEIGHT=360;

    public CalculatorInterface(){
        super();
        this.setLocationRelativeTo(null);
        this.setContentPane(interfacePanel);
        this.setSize(new Dimension(WIDTH,HEIGHT));
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Calculator Polinoame");
        interfacePanel.requestFocus();
        butonAdunare.addActionListener(e -> {
            restShowDis();
            String polynomial1Expression=tfP1.getText();
            String polynomial2Expression=tfP2.getText();
            if(polynomial1Expression.isEmpty() || polynomial2Expression.isEmpty()){
                displayEmptyPolynomialsError(2);
            } else {
                Polynomial polynomial1=new Polynomial(polynomial1Expression);
                Polynomial polynomial2=new Polynomial(polynomial2Expression);
                tfExpresie.setText(Operation.add(polynomial1,polynomial2).toString());
            }
        });
        butonScadere.addActionListener(e -> {
            restShowDis();
            String polynomial1Expression=tfP1.getText();
            String polynomial2Expression=tfP2.getText();
            if(polynomial1Expression.isEmpty() || polynomial2Expression.isEmpty()){
                displayEmptyPolynomialsError(2);
            } else{
                Polynomial polynomial1=new Polynomial(polynomial1Expression);
                Polynomial polynomial2=new Polynomial(polynomial2Expression);
                tfExpresie.setText(Operation.substarct(polynomial1,polynomial2).toString());
            }
        });
        butonInmultire.addActionListener(e -> {
            restShowDis();
            String polynomial1Expression=tfP1.getText();
            String polynomial2Expression=tfP2.getText();
            if(polynomial1Expression.isEmpty() || polynomial2Expression.isEmpty()) {
                displayEmptyPolynomialsError(2);
            }
            else{
                Polynomial polynomial1=new Polynomial(polynomial1Expression);
                Polynomial polynomial2=new Polynomial(polynomial2Expression);
                tfExpresie.setText(Operation.multiplication(polynomial1,polynomial2).toString());
            }
        });
        butonDerivare.addActionListener(e -> {
            restShowDis();
            String polynomialExpression=tfP1.getText();
            if(polynomialExpression.isEmpty()) {
                displayEmptyPolynomialsError(1);
            }
            else{
                Polynomial polynomial=new Polynomial(polynomialExpression);
                tfExpresie.setText(Operation.derivative(polynomial).toString());
            }
        });
        butonIntegrare.addActionListener(e -> {
            restShowDis();
            String polynomialExpression=tfP1.getText();
            if(polynomialExpression.isEmpty()) {
                displayEmptyPolynomialsError(1);
            }
            else{
                Polynomial polynomial=new Polynomial(polynomialExpression);
                tfExpresie.setText(Operation.integral(polynomial).toString());
            }
        });
        butonImpartire.addActionListener(e -> {
            String polynomial1Expression=tfP1.getText();
            String polynomial2Expression=tfP2.getText();
            tfRest.setVisible(true);
            labelRest.setVisible(true);
            if(polynomial1Expression.isEmpty() || polynomial2Expression.isEmpty()) {
                displayEmptyPolynomialsError(2);
            }else if(polynomial2Expression.equals("0")){
                displayDivedeZeroError();
            }
            else{
                Polynomial polynomial1=new Polynomial(polynomial1Expression);
                Polynomial polynomial2=new Polynomial(polynomial2Expression);
                Polynomial[] res;
                res= Operation.division(polynomial1,polynomial2);
                tfExpresie.setText(res[0].toString());
                tfRest.setText(res[1].toString());
            }
        });
        this.setVisible(true);
    }

    private void displayEmptyPolynomialsError(int count) {
        //Display the error message
        String text=null;
        if(count==2) {
            text="Introduceti doua polinoame!!!";
        }
        if(count==1){
            text="Introduceti un polinom!!!";
        }
        JOptionPane.showMessageDialog(this, text,
                "Eroare", JOptionPane.ERROR_MESSAGE);
        //Empty the polynomials tfs
        tfP1.setText("");
        tfP2.setText("");
    }

    private void displayDivedeZeroError(){
        JOptionPane.showMessageDialog(this, "Nu puteti imparti un numar la 0!!!",
                "Eroare", JOptionPane.ERROR_MESSAGE);
        //Empty the polynomials tfs
        tfP1.setText("");
        tfP2.setText("");
    }
    private void restShowDis(){
        labelRest.setVisible(false);
        tfRest.setVisible(false);
    }
}
