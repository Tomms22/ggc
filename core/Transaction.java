package ggc.core;

// Representation of a trade between Warehouse and a Partner
public abstract class Transaction {
	// Stats for keeping relationships with Partners
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
    
    // Generic constructor for any transaction
    Transaction(Product prod, int quant, Partner part, int deadline){
        _id = _numberOfTransactions++;
        _product = prod;
        _paymentDeadline = deadline;
        _quantity = quant;
        _partner = part;
    }

    String getID(){
        return "" + _id;
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
        return _product.getProductID();
    }
    
    // generically pays for a transaction, val might be different from baseval
    void pay(double val){
        _isPaid = true;
        _paidValue = val;
    }
    
    // For Serialization purposes, the class Transaction is not used inside the system
    public abstract String toString();
}
