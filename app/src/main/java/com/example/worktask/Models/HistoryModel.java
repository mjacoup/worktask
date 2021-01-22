package com.example.worktask.Models;

public class HistoryModel {
    String operator;
    double number;

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public double getNumber() {
        return number;
    }

    public void setNumber(double number) {
        this.number = number;
    }

    public HistoryModel(String operator, double number) {
        this.operator = operator;
        this.number = number;
    }
}
