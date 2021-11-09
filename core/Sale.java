package ggc.core;

import ggc.core.Warehouse;

// Sale from a Warehouse to a Partner
public abstract class Sale extends Transaction{
	
	// Every Transactions has a quantity of a Product, Partner and a payment deadline
    Sale(Product prod, int quant, Partner part, int deadline){
        super(prod, quant, part, deadline);
    }
    
    // Gives the appropriate Value for the period of payment of a given date
    PaymentPeriods getPaymentPeriod(int deadline, int N){
        int currentDate = Warehouse.getDate();
        int dateDiff = deadline - currentDate;

        if(dateDiff >= N){
            return PaymentPeriods.P1;
        }
        else if(dateDiff > 0 && dateDiff < N){
            return PaymentPeriods.P2;
        }
        else{
            dateDiff = currentDate - deadline;
            if(dateDiff > 0 && dateDiff < N)
                return PaymentPeriods.P3;
            return PaymentPeriods.P4;
        }
    }
    
    // Applies payment for the Sale
    void pay(){
        int deadline = super.getDeadline();
        double _discount;
        // penalty
        int N = 5;
        PaymentPeriods period;

        // N = 5 simpleProduct and N = 3 derivateProduct

        period = getPaymentPeriod(deadline, N);

    }   
}
