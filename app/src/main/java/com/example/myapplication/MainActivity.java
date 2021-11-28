package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Stack;

public class MainActivity extends AppCompatActivity {
    private String calculatorString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        calculatorString = "";
        setContentView(R.layout.calculator_main);
    }
    public void button0(View view){
        calculatorString = calculatorString + "0";
        EditText temp = findViewById(R.id.textBox);
        temp.setText(calculatorString, TextView.BufferType.EDITABLE);
    }
    public void button1(View view){
        calculatorString = calculatorString + "1";
        EditText temp = findViewById(R.id.textBox);
        temp.setText(calculatorString, TextView.BufferType.EDITABLE);
    }
    public void button2(View view){
        calculatorString = calculatorString + "2";
        EditText temp = findViewById(R.id.textBox);
        temp.setText(calculatorString, TextView.BufferType.EDITABLE);
    }
    public void button3(View view){
        calculatorString = calculatorString + "3";
        EditText temp = findViewById(R.id.textBox);
        temp.setText(calculatorString, TextView.BufferType.EDITABLE);
    }
    public void button4(View view){
        calculatorString = calculatorString + "4";
        EditText temp = findViewById(R.id.textBox);
        temp.setText(calculatorString, TextView.BufferType.EDITABLE);
    }
    public void button5(View view){
        calculatorString = calculatorString + "5";
        EditText temp = findViewById(R.id.textBox);
        temp.setText(calculatorString, TextView.BufferType.EDITABLE);
    }
    public void button6(View view){
        calculatorString = calculatorString + "6";
        EditText temp = findViewById(R.id.textBox);
        temp.setText(calculatorString, TextView.BufferType.EDITABLE);
    }
    public void button7(View view){
        calculatorString = calculatorString + "7";
        EditText temp = findViewById(R.id.textBox);
        temp.setText(calculatorString, TextView.BufferType.EDITABLE);
    }
    public void button8(View view){
        calculatorString = calculatorString + "8";
        EditText temp = findViewById(R.id.textBox);
        temp.setText(calculatorString, TextView.BufferType.EDITABLE);
    }
    public void button9(View view){
        calculatorString = calculatorString + "9";
        EditText temp = findViewById(R.id.textBox);
        temp.setText(calculatorString, TextView.BufferType.EDITABLE);
    }
    public void buttonAdd(View view){
        calculatorString = calculatorString + "+";
        EditText temp = findViewById(R.id.textBox);
        temp.setText(calculatorString, TextView.BufferType.EDITABLE);
    }
    public void buttonSub(View view){
        calculatorString = calculatorString + "-";
        EditText temp = findViewById(R.id.textBox);
        temp.setText(calculatorString, TextView.BufferType.EDITABLE);
    }
    public void buttonMul(View view){
        calculatorString = calculatorString + "*";
        EditText temp = findViewById(R.id.textBox);
        temp.setText(calculatorString, TextView.BufferType.EDITABLE);
    }
    public void buttonDiv(View view){
        calculatorString = calculatorString + "/";
        EditText temp = findViewById(R.id.textBox);
        temp.setText(calculatorString, TextView.BufferType.EDITABLE);
    }
    public void buttonPow(View view){
        calculatorString = calculatorString + "^";
        EditText temp = findViewById(R.id.textBox);
        temp.setText(calculatorString, TextView.BufferType.EDITABLE);
    }
    public void buttonEq(View view){
        calculatorString = String.valueOf(eval(calculatorString));
        EditText temp = findViewById(R.id.textBox);
        temp.setText(calculatorString, TextView.BufferType.EDITABLE);
    }
    public void buttonDot(View view){
        calculatorString = calculatorString + ".";
        EditText temp = findViewById(R.id.textBox);
        temp.setText(calculatorString, TextView.BufferType.EDITABLE);
    }
    public void buttonDel(View view){
        if (calculatorString.length() > 0) {
            calculatorString = calculatorString.substring(0, calculatorString.length() - 1);
            EditText temp = findViewById(R.id.textBox);
            temp.setText(calculatorString, TextView.BufferType.EDITABLE);
        }
    }
    public static double eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                return x;
            }

            //Code to parse expression from the internet.
            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if      (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if      (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

                return x;
            }
        }.parse();
    }
}