package ggc.core;

import java.util.ArrayList;

/**
 * abstract class Product used to represent the products being exchanged by the warehouse and its partners
 * it's used as a base for the simple and aggregate products
 * 
 * @author Ana Torres 99050 & Tomás Vicente 90916 |grupo 48 L04|
 */
public abstract class Product {
    // ID of the product
    private String _id;

    // list of all the product's batches
    private ArrayList<Batch> _batches;

    // base price of the product
    private double _price;

    /**
     * Constructor
     * 
     * @param productID the input value  of the product's ID
     * @param price the input value of the product's price
     */
    Product(String productID, Double price){
        _id = productID;
        _price = price;
    }

    /**
	 * Getter of the product's ID
     * 
	 * @return the product's ID
	 */
    String getProductID(){
        return _id;
    }

    /**
	 * Getter of the product's base price
     * 
	 * @return the product's price
	 */
    double getProductMaxPrice(){
        return _price;
    }

    /**
	 * Getter of the product's current stock
     * 
	 * @return the product's stock
	 */
    abstract int getCurrentStock();

    /**
	 * Getter of the product's batches
     * 
	 * @return list with all of the product's batches
	 */
    ArrayList<Batch> getBatches(){
        return _batches;
    }

    /**
	 * Adds batch
     * Adds batch to the list of the product's batches
     * 
     * @param batch -> batch to add to the product's batches
	 */
    void addBatch(Batch batch){
        _batches.add(batch);
    }

    /**
     * toString of the product's information
     * 
     * @return the product's information in string form ( productID|price )
     */
    public String toString(){
        return _id + '|' + _price;
    }
}


