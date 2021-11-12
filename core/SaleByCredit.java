package ggc.core;

// Sale paid by a specific metric
class SaleByCredit extends Sale {
	// Amount paid depends on who and when it was paid
	private double _amountPaid;	
	
	// Generic Transaction
	SaleByCredit(Product product, int quantity, Partner partner, int deadline) {
		super(product, quantity, partner, deadline);
	}
	
	// For Serialization purposes
	public String toString(){
        return String.join("|", "" + super.getId(), super.getPartnerName(),
         super.getProductId(), "" + super.getQuantity(),
          "" + super.getBaseValue(),"" + super.getPaidValue(),
           "" + super.getDeadline()); 
    }
}
