package com.example.worktask.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.worktask.Adapter.HistoryAdapter;
import com.example.worktask.Models.HistoryModel;
import com.example.worktask.Models.Operations;
import com.example.worktask.R;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Calculator extends AppCompatActivity implements Observer {
    Button plus, minus, multiply, div;
    ImageView undo, redo;
    TextView result;
    EditText value;
    String operator = "";
    RecyclerView history_board;
    HistoryAdapter adapter;
    Operations operations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        operations = new Operations();
        operations.addObserver(this);
        init_View();
        init_Recycler_View();
    }

    private void init_Recycler_View() {
        history_board.setLayoutManager(new GridLayoutManager(this, 3));
        adapter = new HistoryAdapter(Calculator.this, operations.getOperation_history());
        history_board.setAdapter(adapter);
    }

    private void init_View() {
        plus = findViewById(R.id.Plus);
        minus = findViewById(R.id.minus);
        multiply = findViewById(R.id.miltiply);
        div = findViewById(R.id.div);
        undo = findViewById(R.id.undo);
        redo = findViewById(R.id.reudo);
        result = findViewById(R.id.result_id);
        value = findViewById(R.id.value_enter);
        history_board = findViewById(R.id.history_board);
    }

    public void plus_Button(View view) {
        operator = "+";
        disable_another_operators(minus, div, multiply);
    }

    private void disable_another_operators(Button b1, Button b2, Button b3) {
        b1.setEnabled(false);
        b2.setEnabled(false);
        b3.setEnabled(false);
        value.setEnabled(true);
    }

    private void Enable_All_operators() {
        plus.setEnabled(true);
        minus.setEnabled(true);
        multiply.setEnabled(true);
        div.setEnabled(true);
        value.setEnabled(false);
    }

    @SuppressLint("SetTextI18n")
    public void result_Button(View view) {
        if (operations.check_if_last_item_removed()) {
            redo.setVisibility(View.GONE);
        }
        String text_value = value.getText().toString().trim();
        value.setText("");
        if (!text_value.isEmpty() && !operator.isEmpty()) {
            operations.do_operations(Double.parseDouble(text_value), operator);
        }
    }
    public void minus_Button(View view) {
        operator = "-";
        disable_another_operators(plus, div, multiply);
    }

    public void division_Button(View view) {
        operator = "/";
        disable_another_operators(plus, minus, multiply);

    }

    public void multiply_Button(View view) {
        operator = "*";
        disable_another_operators(plus, minus, div);
    }

    public void undo(View view) {
        if (operations.undo_operation()) {
            redo.setVisibility(View.VISIBLE);
        }
    }
    public void redo(View view) {
        if (operations.redo_operation()) {
            undo.setVisibility(View.VISIBLE);
        }
    }
    @Override
    public void update(Observable o, Object arg) {
        result.setText(String.valueOf(operations.getResult_value()));
        Enable_All_operators();
        adapter.notifyDataSetChanged();
        if (operations.getOperation_history().size() > 0)
        undo.setVisibility(View.VISIBLE);
        if (operations.getOperation_history().size() == 0)
            undo.setVisibility(View.GONE);
        if (operations.getRedo_history_List().size() == 0)
            redo.setVisibility(View.GONE);
    }
}