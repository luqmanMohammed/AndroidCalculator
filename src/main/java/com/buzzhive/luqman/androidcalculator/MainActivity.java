package com.buzzhive.luqman.androidcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.buzzhive.luqman.androidcalculator.Helpers.CalculationHelper;

public class MainActivity extends AppCompatActivity {

    //String Builder to hold current view Expression
    private static StringBuilder sb = new StringBuilder();
    //TextViews
    private TextView tvMain,tvAwn;
    //Boolean to make sure no operands are used one after another
    private static boolean operandLock = true;
    //For negative numbers
    private static int minusCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.tvMain = findViewById(R.id.tvMain);
        this.tvAwn = findViewById(R.id.tvAwn);

        //Setting OnClickListeners for number buttons
        findViewById(R.id.num1).setOnClickListener(new NumButtonOnClickListener("1"));
        findViewById(R.id.num2).setOnClickListener(new NumButtonOnClickListener("2"));
        findViewById(R.id.num3).setOnClickListener(new NumButtonOnClickListener("3"));
        findViewById(R.id.num0).setOnClickListener(new NumButtonOnClickListener("0"));
        findViewById(R.id.num4).setOnClickListener(new NumButtonOnClickListener("4"));
        findViewById(R.id.num5).setOnClickListener(new NumButtonOnClickListener("5"));
        findViewById(R.id.num6).setOnClickListener(new NumButtonOnClickListener("6"));
        findViewById(R.id.num7).setOnClickListener(new NumButtonOnClickListener("7"));
        findViewById(R.id.num8).setOnClickListener(new NumButtonOnClickListener("8"));
        findViewById(R.id.num9).setOnClickListener(new NumButtonOnClickListener("9"));

        //Setting OnClickListener for operands
        findViewById(R.id.btnDivide).setOnClickListener(new OperandButtonClickListener("/"));
        findViewById(R.id.btnMultiply).setOnClickListener(new OperandButtonClickListener("*"));
        findViewById(R.id.btnAdd).setOnClickListener(new OperandButtonClickListener("+"));
        findViewById(R.id.btnDecimal).setOnClickListener(new OperandButtonClickListener("."));

        //OnClickListener for minus operand. Special listener to implement negative numbers
        findViewById(R.id.btnMinus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sb.length() == 0 ) {
                    sb.append("-");
                    minusCount = 10;
                }
                else if(minusCount < 2) {
                    minusCount++;
                    sb.append("-");
                }
                if(minusCount >= 2)
                    operandLock = true;
                tvMain.setText(sb.toString());
            }
        });

        //Calculates starts here
        findViewById(R.id.btnEquals).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(operandLock){
                    double awn = CalculationHelper.calculate(sb.toString());
                    tvAwn.setText(Double.toString(awn));
                }
                else {
                    Toast t = Toast.makeText(null,"Invalid Expression",Toast.LENGTH_LONG);
                    t.show();
                }
            }
        });

        //Clears Everything in screen
        findViewById(R.id.btnClearEverything).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sb = new StringBuilder();
                tvMain.setText(sb.toString());
                operandLock = true;
                minusCount = 0;
                tvAwn.setText("");
            }
        });

        //Clears last character and check if its a valid expression
        findViewById(R.id.btnClear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sb.length() > 0){
                    if(sb.charAt(sb.length()-1) == '-')
                        minusCount--;
                    sb.deleteCharAt(sb.length()-1);
                    tvMain.setText(sb.toString());
                    try {
                        Double.parseDouble(sb.charAt(sb.length()-1)+"");
                    }catch(NumberFormatException nfe) {
                        operandLock = true;
                    }
                }
                if(sb.length() == 0)
                    operandLock = true;
            }
        });


    }

    //Inner Class to Implement Common Operand OnClickListener
    protected class OperandButtonClickListener implements View.OnClickListener {

        private String operand;

        OperandButtonClickListener(String operand) {
            this.operand = operand;
        }

        @Override
        public void onClick(View view) {
            if (!operandLock) {
                sb.append(operand);
                operandLock = true;
            } else if (sb.length() == 1 && sb.charAt(0) == '-') {
                return;
            }else {
                if(sb.length() > 0)
                    sb.setCharAt(sb.length()-1,operand.charAt(0));
            }
            tvMain.setText(sb.toString());
        }
    }

    //Inner Class to Implement Common Number OnClickListener
    protected class NumButtonOnClickListener implements View.OnClickListener {

        private String buttonOperand;

        NumButtonOnClickListener(String operand) {
            this.buttonOperand = operand;
        }

        @Override
        public void onClick(View view) {
            sb.append(this.buttonOperand);
            tvMain.setText(sb.toString());
            operandLock = false;
            minusCount = 0;
        }
    }
}
