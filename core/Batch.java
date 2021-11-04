package ggc.core;

/**
 * class Batch used to represent batches of the products being exchanged by the warehouse and its partners
 * 
 * @author Ana Torres 99050 & Tomás Vicente 90916 |grupo 48 L04|
 */
public class Batch {
    // batch's supplier
    private Partner _supplier;

    // batch's price per unit
    private double _pricePerUnit;

    // batch's stock 
    private int _stock;

    // ID of the batch's product
    private String _productID;

    /**
     * Constructor
     * 
     * @param supplier the input value of the batch's supplier
     * @param price the input value of the batch's price per unit
     * @param stock the input value of the batch's total stock
     * @param productID the input value of the batch's product's ID
     */
    Batch(Partner supplier, double price, int stock, String productID){
        _supplier = supplier;
        _pricePerUnit = price;
        _stock = stock;
        _productID = productID;
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
    int removeUnits(int unit){
        int leftoverStock = 0;
        if(_stock - unit < 0){
            leftoverStock = unit - _stock;
            _stock = 0;
        }
        else
            _stock-= unit;
        return leftoverStock;
    }

    /**
	 * Getter of the batch's stock
     * 
	 * @return the batch's stock
	 */
    int getStock(){
        return _stock;
    }

    /**
	 * Changes the batch's price to it's new price
     * 
     * @param newPrice the batch's new price
	 */
    void changePrice(double newPrice){
        _pricePerUnit = newPrice;
    }

    /**
	 * Getter of the batch's product's ID
     * 
	 * @return the batch's product's ID
	 */
    String getProductID(){
        return _productID;
    }

    /**
	 * Getter of the batch's supplier's ID
     * 
	 * @return the batch's supplier's ID
	 */
    String getPartnerID(){
        return _supplier.getName();
    }
    
    /**
     * toString of the batch's information
     * 
     * @return the batch's information in string form ( productID|supplier|price|stock )
     */
	public String toString(){
		return String.join("|", _productID, _supplier.getName(), "" +_pricePerUnit, ""+_stock);
	}
}
