package ggc.core;

// Sale paid by a specific metric
public class SaleByCredit extends Sale {
	// Amount paid depends on who and when it was paid
	private double _amountPaid;	
	
	// Generic Transaction
	SaleByCredit(Product p, int q, Partner part, int dl) {
		super(p, q, part, dl);
	}
	
	// For Serialization purposes
	public String toString(){
        return String.join("| ", super.getID(), super.getPartnerName(),
         super.getProductName(), "" + super.getQuantity(),
          "" + super.getBaseValue(),"" + super.getPaidValue(),
           "" + super.getDeadline()); 
    }
}
