package ggc.core;

import java.util.ArrayList;
import java.io.Serializable;

/**
 * abstract class Product used to represent the products being exchanged by the warehouse and its partners
 * it's used as a base for the simple and aggregate products
 * 
 * @author Ana Torres 99050 & Tomás Vicente 90916 |grupo 48 L04|
 */
abstract class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    // ID of the product
    private String _id;

    // list of all the product's batches
    private ArrayList<Batch> _batches;


    /**
     * Constructor
     * 
     * @param productId the input value  of the product's ID
     */
    Product(String productId){
        _id = productId;
    }

    /**
	 * Getter of the product's ID
     * 
	 * @return the product's ID
	 */
    String getId(){
        return _id;
    }

    /**
	 * Getter of the product's mx price
     * 
	 * @return the product's price
	 */
    double getProductMaxPrice() {
        double maxPrice = 0;
        double newPrice;
        for(Batch batch: _batches)
            if((newPrice = batch.getPrice()) > maxPrice)
                maxPrice = newPrice;
        return maxPrice;
    }

        /**
	 * Getter of the product's current stock
     * Relies on its Batch to get a quantity in it, but not every one in the Warehouse
     * 
	 * @return the product's stock
	 */
    int getCurrentStock() {
        int stock = 0;
        for(Batch batch : _batches)
            stock += batch.getStock();
        return stock;
        }

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
        return _id + '|' + getProductMaxPrice() + getCurrentStock();
    }
}


