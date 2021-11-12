package ggc.core;

// Product without component parts
class SimpleProduct extends Product{
    
    // similar to its superclass
    SimpleProduct(String productId){
        super(productId);
    }

    // for external representation of instance ( productID|price|stock )
    public String toString(){
        return super.toString();  
    }
}
