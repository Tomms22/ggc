package ggc.core;

import java.io.Serializable;

/**
 * class Batch used to represent batches of the products being exchanged by the warehouse and its partners
 * 
 * @author Ana Torres 99050 & Tomás Vicente 90916 |grupo 48 L04|
 */
class Batch implements Serializable {

    private static final long serialVersionUID = 3L;
    
    // batch's supplier
    private Partner _supplier;

    // batch's price per unit
    private double _pricePerUnit;

    // batch's stock 
    private int _stock;

    // ID of the batch's product
    private Product _product;

    /**
     * Constructor
     * 
     * @param supplier the input value of the batch's supplier
     * @param price the input value of the batch's price per unit
     * @param stock the input value of the batch's total stock
     * @param productID the input value of the batch's product's ID
     */
    Batch(Partner supplier, double price, int stock, Product product){
        _supplier = supplier;
        _pricePerUnit = price;
        _stock = stock;
        _product = product;
    }

    /**
	 * Adds units to the batch
     * 
     * @param unit -> number of units to add to the batch's stock
	 */
    void addUnits(int unit){
        _stock+= unit;
    }

    /**
	 * Removes units from the batch
     * Removes a certain quantity of units from the batch and if the number of units that you want to 
     * take is superior to the current stock, removeUnits(int) will return the stock you couldn't take
     * 
     * @param unit -> number of units to remove from the batch's stock
     * @return stock you coudn't remove (0 if you could remove all you wanted)
	 */
    int removeUnits(int unit) {
        int leftoverStock = 0;
        if(_stock < unit) {
            leftoverStock = unit - _stock;
            _stock = 0;
        }
        else
            _stock -= unit;
        return leftoverStock;
    }

    /**
	 * Getter of the batch's stock
     * 
	 * @return the batch's stock
	 */
    int getStock() {
        return _stock;
    }

    /**
	 * Changes the batch's price to it's new price
     * 
     * @param newPrice the batch's new price
	 */
    void changePrice(double newPrice) {
        _pricePerUnit = newPrice;
    }

    /**
	 * Getter of the batch's product
     * 
	 * @return the batch's product
	 */
    Product getProduct() {
        return _product;
    }

    /**
	 * Getter of the batch's supplier
     * 
	 * @return the batch's supplier
	 */
    Partner getPartner() {
        return _supplier;
    }
    
    /**
     * toString of the batch's information
     * 
     * @return the batch's information in string form ( productID|supplier|price|stock )
     */
	public String toString(){
		return String.join("|", _product.getID(), _supplier.getName(), "" +_pricePerUnit, ""+_stock);
	}

    double getTotalPrice(){
        return _stock * _pricePerUnit;
    }
}
