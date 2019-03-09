package com.buzzhive.luqman.androidcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static StringBuilder sb = new StringBuilder();
    private TextView tvMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.tvMain = findViewById(R.id.tvMain);

        findViewById(R.id.num1).setOnClickListener(new MainButtonOnClickListener("1"));
        findViewById(R.id.num2).setOnClickListener(new MainButtonOnClickListener("2"));
        findViewById(R.id.num3).setOnClickListener(new MainButtonOnClickListener("3"));
        findViewById(R.id.num0).setOnClickListener(new MainButtonOnClickListener("0"));
        findViewById(R.id.num4).setOnClickListener(new MainButtonOnClickListener("4"));
        findViewById(R.id.num5).setOnClickListener(new MainButtonOnClickListener("5"));
        findViewById(R.id.num6).setOnClickListener(new MainButtonOnClickListener("6"));
        findViewById(R.id.num7).setOnClickListener(new MainButtonOnClickListener("7"));
        findViewById(R.id.num8).setOnClickListener(new MainButtonOnClickListener("8"));
        findViewById(R.id.num9).setOnClickListener(new MainButtonOnClickListener("9"));
        findViewById(R.id.btnDecimal).setOnClickListener(new MainButtonOnClickListener("."));
        findViewById(R.id.btnAdd).setOnClickListener(new MainButtonOnClickListener("+"));
        findViewById(R.id.btnMinus).setOnClickListener(new MainButtonOnClickListener("-"));
        findViewById(R.id.btnMultiply).setOnClickListener(new MainButtonOnClickListener("*"));
        findViewById(R.id.btnDivide).setOnClickListener(new MainButtonOnClickListener("/"));

    }

    protected class MainButtonOnClickListener implements View.OnClickListener {

        private String buttonOperand;

        MainButtonOnClickListener(String operand) {
            this.buttonOperand = operand;
        }

        @Override
        public void onClick(View view) {
            sb.append(this.buttonOperand);
            tvMain.setText(sb.toString());
        }
    }
}
