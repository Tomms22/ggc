package ggc.core;

import java.io.Serializable;

class Notification implements Serializable {
    private static final long serialVersionUID = 6L;
    private String _type;
    private Product _product;

    Notification(String type, Product product){
        _type = type;
        _product = product;
    }

    public String toString(){
        return String.join("|", _type, _product.getId(), "" + _product.getProductMaxPrice());
    }

}
