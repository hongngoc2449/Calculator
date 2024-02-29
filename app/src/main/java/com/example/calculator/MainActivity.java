package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView solution_view, result_view;
    private MaterialButton butC,butOpenBrak,butCloseBrak,butDiv;
    private MaterialButton but7,but8,but9,butMul;
    private MaterialButton but4,but5,but6,butMinus;
    private MaterialButton but1,but2,but3,butPlus;
    private MaterialButton butAC,but0,butDot,butEqual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result_view = findViewById(R.id.result_view);
        solution_view = findViewById(R.id.solution_view);

        assignId(butC,R.id.delete);
        assignId(butOpenBrak,R.id.open_bracket);
        assignId(butCloseBrak,R.id.close_bracket);
        assignId(butDiv,R.id.divide);
        assignId(but9,R.id.nine);
        assignId(but8,R.id.eight);
        assignId(but7,R.id.seven);
        assignId(but6,R.id.six);
        assignId(but5,R.id.five);
        assignId(but4,R.id.four);
        assignId(but3,R.id.three);
        assignId(but2,R.id.two);
        assignId(but1,R.id.one);
        assignId(but0,R.id.zero);
        assignId(butMul,R.id.multiply);
        assignId(butMinus,R.id.minus);
        assignId(butPlus,R.id.plus);
        assignId(butAC,R.id.ac);
        assignId(butDot,R.id.dot);
        assignId(butEqual,R.id.equal);
    }
    void assignId(MaterialButton btn,int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonView = button.getText().toString();
        String dataToCal = solution_view.getText().toString();

        if(buttonView.equals("AC")){
            solution_view.setText("");
            result_view.setText("0");
            return;
        }
        if(buttonView.equals("=")){
            solution_view.setText(result_view.getText());
            return;
        }
        if(buttonView.equals("C")){
            dataToCal = dataToCal.substring(0, dataToCal.length()-1);
        }else{
            dataToCal = dataToCal + buttonView;
        }
        solution_view.setText(dataToCal);
        String finalResult = getResult(dataToCal);
        if(!finalResult.equals("Err")){
            result_view.setText(finalResult);
        }
    }
    String getResult(String data){
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initSafeStandardObjects();
            String finalResult = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        }catch (Exception e){
            return "Err";
        }
    }
}