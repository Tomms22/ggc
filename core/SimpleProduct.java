package ggc.core;

// Product without component parts
class SimpleProduct extends Product{
    
    // similar to its superclass
    SimpleProduct(String productId, Double price){
        super(productId, price);
    }

    // Relies on its Batch to get a quantity in it, but not every one in the Warehouse
    int getCurrentStock(){
        int stock = 0;
        for(Batch batch : super.getBatches())
            stock += batch.getStock();
        return stock;
    }

    // for external representation of instance ( productID|price|stock )
    public String toString(){
        return '|' + super.toString() +  '|' + getCurrentStock();  
    }
}
