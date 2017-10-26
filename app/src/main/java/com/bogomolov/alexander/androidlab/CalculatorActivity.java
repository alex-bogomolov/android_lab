package com.bogomolov.alexander.androidlab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalculatorActivity extends AppCompatActivity {
    enum State { Initial, FirstOperand, SecondOperand, Operation }

    String firstOperand, secondOperand, operation;
    TextView output;
    State state;
    Button equalsButton, dotButton;
    Boolean dotAbailable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        this.firstOperand = "0";
        this.output = (TextView) findViewById(R.id.output);
        this.output.setText(this.firstOperand);
        this.state = State.Initial;
        this.equalsButton = (Button) findViewById(R.id.buttonEquals);
        this.dotButton = (Button) findViewById(R.id.buttonDot);
        this.dotAbailable = true;
    }

    public void buttonClicked(View view) {
        String clickedButton = view.getTag().toString();
        this.equalsButton.setEnabled(true);

        switch (this.state) {
            case Initial:
                handleInitialState(clickedButton);
                break;
            case FirstOperand:
                handleFirstOperandState(clickedButton);
                break;
            case SecondOperand:
                handleSecondOperandState(clickedButton);
                break;
            case Operation:
                handleOperationState(clickedButton);
                break;
        }
    }

    private void handleInitialState(String input) {
        if (isDigit(input)) {
            this.state = State.FirstOperand;
            this.firstOperand = input;
            this.output.setText(firstOperand);
        } else if (isOperation(input)) {
            this.state = State.SecondOperand;
            this.operation = input;
            this.secondOperand = "";
            this.equalsButton.setEnabled(false);
        } else if (isEquals(input)) {

        } else if (isDot(input)) {
            this.dotAbailable = false;
            this.firstOperand += ".";
            this.output.setText(firstOperand);
            this.state = State.FirstOperand;
            this.dotAbailable = false;
            this.dotButton.setEnabled(false);
        }
    }

    private void handleFirstOperandState(String input) {
        if (isDigit(input)) {
            this.firstOperand += input;
            this.output.setText(firstOperand);
        } else if (isOperation(input)) {
            this.state = State.SecondOperand;
            this.secondOperand = "";
            this.operation = input;
            this.equalsButton.setEnabled(false);
            this.dotAbailable = true;
            this.dotButton.setEnabled(true);
        } else if (isEquals(input)) {

        } else if (isDot(input)) {
            this.dotAbailable = false;
            this.firstOperand += ".";
            this.output.setText(firstOperand);
            this.dotAbailable = false;
            this.dotButton.setEnabled(false);
        }
    }

    private void handleSecondOperandState(String input) {
        if (isDigit(input)) {
            this.secondOperand += input;
            this.output.setText(this.secondOperand);
        } else if (isOperation(input)) {
            if (secondOperand.equals("")) {
                this.operation = input;
            } else {
                this.performCalculation();
                this.state = State.SecondOperand;
                this.secondOperand = "";
                this.equalsButton.setEnabled(false);
                this.operation = input;
            }
        } else if (isEquals(input)) {
            this.performCalculation();
        } else if (isDot(input)) {
            this.dotAbailable = false;
            if (this.secondOperand.equals((""))) {
                this.secondOperand = "0.";
            } else {
                this.secondOperand += ".";
            }
            this.output.setText(this.secondOperand);
            this.dotAbailable = false;
            this.dotButton.setEnabled(false);
        }
    }

    private void handleOperationState(String input) {
        if (isDigit(input)) {
            this.firstOperand = input;
            this.output.setText(this.firstOperand);
            this.state = State.FirstOperand;
        } else if (isOperation(input)) {
            this.state = State.SecondOperand;
            this.operation = input;
            this.secondOperand = "";
        } else if (isEquals(input)) {
        }
    }

    private boolean isOperation(String input) {
        return input.equals("+") || input.equals("-") || input.equals("*") || input.equals("/");
    }

    private boolean isDigit(String input) {
        Pattern p = Pattern.compile("^\\d$");
        Matcher m = p.matcher(input);
        return m.matches();
    }

    private boolean isEquals(String input) {
        return input.equals("=");
    }

    private boolean isDot(String input) {
        return input.equals(".");
    }

    private void performCalculation() {
        float a = Float.parseFloat(this.firstOperand);
        float b = Float.parseFloat(this.secondOperand);
        float c = 0;

        switch (this.operation) {
            case "+":
                c = a + b;
                break;
            case "-":
                c = a - b;
                break;
            case "*":
                c = a * b;
                break;
            case "/":
                c = a / b;
                break;
        }

        this.state = State.Operation;
        this.operation = null;
        this.output.setText(Float.toString(c));
        this.firstOperand = Float.toString(c);
        this.dotAbailable = true;
        this.dotButton.setEnabled(true);
    };

    public void clearButtonClicked(View view) {
        this.state = State.Initial;
        this.firstOperand = "0";
        this.operation = "";
        this.secondOperand = "";
        this.equalsButton.setEnabled(true);
    }
}
