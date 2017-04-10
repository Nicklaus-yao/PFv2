package com.nykidxxx.pfv2.model;
//Created March 6th 2017

public class Transactions {

    private int _id;
    private String _payee;
    private String _amount;
    private String _category;
    private String _month;

    public Transactions(){

    }

    public Transactions(String payee, String amount, String category, String month) {
        this._payee = payee;
        this._month = month;
        this._category = category;
        this._amount = amount;
    }

    //----- Setters -----
    public void set_payee(String _payee) {
        this._payee = _payee;
    }

    public void set_amount(String _amount) {
        this._amount = _amount;
    }

    public void set_category(String _category) {
        this._category = _category;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_month(String _month) {
        this._month = _month;
    }

    //----- Getters -----
    public String get_payee() {
        return _payee;
    }

    public String get_amount() {
        return _amount;
    }

    public String get_category() {
        return _category;
    }

    public int get_id() {
        return _id;
    }

    public String get_month() {
        return _month;
    }
}
