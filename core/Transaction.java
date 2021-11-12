package ggc.core;

import java.io.Serializable;

abstract class Transaction implements Serializable {
    private static final long serialVersionUID = 4L;
    public static int _numberOfTransactions;
	private int _id;
    private int _paymentDeadline;
    private double _baseValue;
    private double _paidValue;
    private boolean _isPaid;
    /* Using individual quant and prod attributes to facilitate removal o products from batches
    without creating new Batches and adding them to every instance that stores it*/ 
    private int _quantity;
    private Product _product;
    private Partner _partner;

    Transaction(Product prod, int quant, Partner part, int deadline){
        _id = _numberOfTransactions++;
        _product = prod;
        _paymentDeadline = deadline;
        _quantity = quant;
        _partner = part;
    }

    static int getNumberOfTransactions() {
        return _numberOfTransactions;
    }

    int getId(){
        return _id;
    }

    Product getProduct(){
        return _product;
    }

    int getQuantity(){
        return _quantity;
    }

    double getBaseValue(){
        return _baseValue;
    }

    boolean isPaid(){
        return _isPaid;
    }

    int getDeadline(){
        return _paymentDeadline;
    }

    double getPaidValue(){
        return _paidValue;
    }

    String getPartnerName(){
        return _partner.getName();
    }

    String getProductName(){
        return _product.getId();
    }

    void pay(double baseVal){
        _isPaid = true;
        _baseValue = baseVal;
    }

    public abstract String toString();
}