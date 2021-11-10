package ggc.core;

import ggc.core.Warehouse;

// Representation of a buying transaction which is instantly paid
class Acquisition extends Transaction{
	
	// Every acquisition is linked with the Product, its price, quantity and Partner of choice
	// It is also instantly paid
	Acquisition(Product prod, int quant, Partner part, double pricePerUnit){
        super(prod, quant, part, Warehouse.getDate());
        super.pay(pricePerUnit*quant);
    }
	
	// For Serialization purposes
    public String toString(){
        return String.join("|", super.getID(), super.getPartnerName(),
         super.getProduct().getProductID(), "" + super.getQuantity(),
          "" + super.getBaseValue(), "" + super.getDeadline());
    }
}
