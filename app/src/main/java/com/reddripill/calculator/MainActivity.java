package com.reddripill.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etOp1, etOp2, etOper;
    private TextView tvRes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        etOp1 = findViewById(R.id.op1);
        etOp2 = findViewById(R.id.op2);
        etOper = findViewById(R.id.oper);
        tvRes = findViewById(R.id.result);
        Button btn = findViewById(R.id.btn);

        btn.setOnClickListener(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void onClick(View v) {
        float op1, op2, res = 0;
        boolean isCorrectOperation = true;
        String oper;

        try {
            op1 = Float.parseFloat(etOp1.getText().toString());
            op2 = Float.parseFloat(etOp2.getText().toString());
            oper = etOper.getText().toString();

            switch (oper) {
                case "+":
                    res = op1 + op2;
                    break;
                case "-":
                    res = op1 - op2;
                    break;
                case "*":
                    res = op1 * op2;
                    break;
                case "/":
                    res = op1 / op2;
                    break;
                case "^":
                    res = (float) Math.pow(op1, op2);
                    break;
                default:
                    isCorrectOperation = false;
                    break;
            }
            if(isCorrectOperation) {
                tvRes.setText(op1+" "+oper+" "+op2+" = "+res);
            } else {
                tvRes.setText(R.string.unknown_error);
            }
        } catch (Exception err) {
            Toast toastErr = Toast.makeText(this, R.string.some_error, Toast.LENGTH_SHORT);
            toastErr.show();
        }
    }
}