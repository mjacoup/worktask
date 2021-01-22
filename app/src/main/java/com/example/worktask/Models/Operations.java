package com.example.worktask.Models;

import android.annotation.SuppressLint;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.example.worktask.Adapter.HistoryAdapter;

import java.util.ArrayList;
import java.util.Observable;

public class Operations extends Observable {
    double result_value = 0.0;
    ArrayList<HistoryModel> operation_history = new ArrayList<>();
    ArrayList<HistoryModel> redo_history_List = new ArrayList<>();

    public boolean check_if_last_item_removed() {
        if (operation_history.size() == 0) {
            redo_history_List.clear();
            return true;
        } else {
            return false;
        }
    }
    public void do_operations(double value, String operator) {
        switch (operator) {
            case "+":
                result_value = result_value + value;
                break;
            case "-":
                result_value = result_value - value;
                break;
            case "*":
                result_value = result_value * value;
                break;
            case "/":
                result_value = result_value / value;
                break;
        }
        Add_result_to_history(operator, String.valueOf(value));
        setChanged();
        notifyObservers();
    }
    private void Add_result_to_history(String operator, String text_value) {
        HistoryModel model = new HistoryModel(operator, Double.parseDouble(text_value));
        operation_history.add(model);
    }
    public boolean undo_operation() {
        if (operation_history.size() > 0) {
            String operator = operation_history.get(operation_history.size() - 1).getOperator();
            double number = operation_history.get(operation_history.size() - 1).getNumber();
            Undo_Operation(operator, number, operation_history.size() - 1);
            return true;
        } else {
            return false;
        }
    }

    @SuppressLint("SetTextI18n")
    private void Undo_Operation(String operator, double number, int pos) {
        switch (operator) {
            case "+":
                result_value = result_value - number;
                break;
            case "-":
                result_value = result_value + number;
                break;
            case "*":
                result_value = result_value / number;
                break;
            case "/":
                result_value = result_value * number;
                break;
        }
        redo_history_List.add(operation_history.get(pos));
        operation_history.remove(pos);
        setChanged();
        notifyObservers();

    }

    public boolean redo_operation() {
        if (redo_history_List.size() > 0) {
            String operator = redo_history_List.get(redo_history_List.size() - 1).getOperator();
            double number = redo_history_List.get(redo_history_List.size() - 1).getNumber();
            Redo_Operation(operator, number, redo_history_List.size() - 1);
            return true;
        }
        return false;
    }

    @SuppressLint("SetTextI18n")
    private void Redo_Operation(String operator, double number, int pos) {
        switch (operator) {
            case "+":
                result_value = result_value + number;
                break;
            case "-":
                result_value = result_value - number;
                break;
            case "*":
                result_value = result_value * number;
                break;
            case "/":
                result_value = result_value / number;
                break;
        }
        operation_history.add(redo_history_List.get(pos));
        redo_history_List.remove(pos);
        setChanged();
        notifyObservers();
    }
    public ArrayList<HistoryModel> getRedo_history_List() {
        return redo_history_List;
    }
    public ArrayList<HistoryModel> getOperation_history() {
        return operation_history;
    }
    public double getResult_value() {
        return result_value;
    }
}
