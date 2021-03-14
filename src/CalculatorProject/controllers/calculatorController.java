package CalculatorProject.controllers;

import CalculatorProject.Main;
import CalculatorProject.utils.EvaluateString;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.ArrayList;

public class  calculatorController {
    @FXML
    public Label ansText;
    @FXML
    private Label expression;

    private ArrayList<String> calcHistory = new ArrayList<>();

    @FXML
    public void initialize() {
        expression.setText("");
    }

    public void insertNumber(String number){
        expression.setText(expression.getText() + number);
    }

    public void insertOperator(String op){
        expression.setText(expression.getText() + " " + op + " " );
    }

    public void insertAnswer(String answer){ expression.setText(answer);}



    public void delLastCharacter(){
        if (!getExpression().getText().isEmpty()){
            expression.setText(expression.getText().substring(0,(expression.getText().length() - 1)));
        }
    }

    public void clearExpression(){
        expression.setText("");
        ansText.setText("");
    }

    public Label getExpression(){
        return expression;
    }

    public Label getAnsText() { return ansText; }

    public void setAnsText(String result){ this.ansText.setText("= " + result); }


    public void openHistoryWindow(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/CalculatorProject/resources/history.fxml"));
            Parent root = loader.load();

            Main.getHistoryStage().setScene(new Scene(root));

            historyController histControll = loader.getController();
            histControll.initializeCalculations(calcHistory);

            Main.getHistoryStage().show();
        }catch (IOException e){
            System.out.println(e);
        }
    }

    public void addCalculation(String expression, String result){
        this.calcHistory.add(expression + " = " + result);
    }

    public void onMouseClicked(MouseEvent mouseEvent) {
        Button button = (Button) mouseEvent.getSource();
        String character = button.getText();

        if (expression.getText().equals("Syntax Error")){
            expression.setText("");
        }
        switch(character){
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
            case "0":
                insertNumber(character);
                break;
            case "+":
            case "-":
            case "/":
            case "*":
                insertOperator(character);
                break;
            case "HIST":
                openHistoryWindow();
                break;
            case "CLEAR":
                clearExpression();
                break;
            case "DEL":
                delLastCharacter();
                break;
            case "=":
                if (!getExpression().getText().isEmpty()) {
                    try {
                        long res = EvaluateString.evaluate(this.getExpression().getText());
                        addCalculation(this.getExpression().getText(), String.valueOf(res));
                        setAnsText(String.valueOf(res));
                    } catch (Exception e) {
                        e.printStackTrace();
                       // expression.setText("Syntax Error");
                    }
                }
                break;
            case "ANS":
                    insertAnswer(getAnsText().getText().substring(2));
                break;




        }


    }
}
