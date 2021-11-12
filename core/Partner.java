package ggc.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

class Partner implements Serializable {
    private static final long serialVersionUID = 2L;
	private String _name;
    private String _address;
    private String _id;
    private Status _status;
    private double _points;
    private double _acquisitionValue;
    private double _saleValue;
    private double _paidSaleValue;
    private ArrayList<Acquisition> _acquisitions = new ArrayList<>();
    private ArrayList<Sale> _sales = new ArrayList<>();
    private ArrayList<Batch> _batches = new ArrayList<>();
    private ArrayList<Notification> _notifications = new ArrayList<>();
    

    Partner(String id, String name, String address){
        _id = id;
        _name = name;
        _address = address;
        _status = Status.NORMAL;
    }

    String getName(){
        return _name;
    }

    String getID(){
        return _id;
    }

    /**
     * two partners are equal if they have the same ID
     * 
     * @return true if the partners are equal
     */
    public boolean equals(Partner partner){
        return _id.equals(partner.getID());
    }

    // for external representation of instance
    public String toString(){
        String acqValue = "" + Math.round(_acquisitionValue);
        String points = "" + _points;
        String sValue = "" + Math.round(_saleValue);
        String paidSValue = "" + Math.round(_paidSaleValue);
        String status = "" + _status;
        return String.join("|", _id, _name, _address, status, points, acqValue, sValue, paidSValue);
    }

    void addNotification(Notification n){
        _notifications.add(n);
    }

    void toggleNotification(Product product){
        for(Notification n : _notifications)
            if(n.getProductId() == product.getID())
                n.toggle();
    }

    double getPartnerPayments(){
        return _paidSaleValue;
    }

    Collection<Acquisition> getPartnerAcquisitions(){
        return _acquisitions;
    } 
    
    Collection<Sale> getPartnerSales(){
        return _sales;
    }  
    
}

