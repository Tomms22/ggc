package ggc.core;

import java.util.ArrayList;

// Equivalent to selling target DerivedProduct and buying back its component parts
public class BreakdownSale extends Sale{
	// Gets components from various batches
    ArrayList<Batch> _batches = new ArrayList<>();
    
    // Just like any other Transaction
    BreakdownSale(Product prod, int quant, Partner part){
        super(prod, quant, part, Warehouse.getDate());
    }
    
    // For Serialization purposes
    public String toString(){
        return String.join("| ", super.getID(), super.getPartnerName(),
         super.getProductName(), "" + super.getQuantity(),
          "" + super.getBaseValue(),"" + super.getPaidValue(),
           "" + super.getDeadline()); 
    }
}
