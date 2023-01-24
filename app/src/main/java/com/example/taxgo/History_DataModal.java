package com.example.taxgo;

public class History_DataModal {
    String date;
    String time;
    String method;
    String amount;

    public History_DataModal(String date, String time, String method, String amount) {
        this.date = date;
        this.time = time;
        this.method = method;
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getMethod() {
        return method;
    }

    public String getAmount() {
        return amount;
    }
}