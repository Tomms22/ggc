package ggc.core;

import java.io.Serializable;

class Notification implements Serializable {
    private static final long serialVersionUID = 6L;
    private String _type;
    private Product _product;
    private boolean _isEnabled;

    Notification(String type, Product product){
        _type = type;
        _product = product;
    }

    void toggle(){
        _isEnabled = !_isEnabled;
    }

    public String toString(){
        return String.join("|", _type, _product.getId(), "" + _product.getProductMaxPrice());
    }

    String getProductId(){
        return _product.getId();
    }

}
