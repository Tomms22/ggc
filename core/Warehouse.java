package ggc.core;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import ggc.core.exception.BadEntryException;

/**
 * Class Warehouse implements a warehouse.
 */
class Warehouse implements Serializable {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202109192006L;

  private Map<String, Product> _products;
  private Map<String, Partner>  _partners;
  private Collection<Batch> _batches;	
  private double _accountableBalance;
  private double _availableBalance;
  private static int _date; 

  Warehouse() {
    _products = new HashMap<>();
    _partners = new HashMap<>();
    _batches = new ArrayList<>();
    _date = 0;
  }
  /**
   * @param txtfile filename to be loaded
   * @param parser parser to be used
   * @throws IOException
   * @throws BadEntryException
   */
  void importFile(String txtfile, Parser parser) throws IOException, BadEntryException {
	  parser.parseFile(txtfile);
  }
  
  Partner getPartner(String partnerId) {
    return _partners.get(partnerId);
  }
  
  Collection<Partner> getPartners() {
	  return _partners.values();
  }
  
  Product getProduct(String productId) {
    return _products.get(productId);
  }

  Collection<Product> getProducts() {
	  return _products.values();
  }

  Collection<Batch> getBatches(){
    return _batches;
  }

  Collection<Batch> getProductBatches(String productId) {
    ArrayList<Batch> productBatches = new ArrayList<>();
    for (Batch batch: _batches)
      if (productId.equals(batch.getProduct().getID()))
        productBatches.add(batch);
    return productBatches;
  }

  Collection<Batch> getPartnerBatches(String partnerId) {
    ArrayList<Batch> partnerBatches = new ArrayList<>();
    for (Batch batch: _batches)
      if (partnerId.equals(batch.getPartner().getID()))
        partnerBatches.add(batch);
    return partnerBatches;
  }


  boolean addPartner(String id, String name, String address){
    Partner p = new Partner(id, name, address);

    if(_partners.get(id) == null){
      _partners.put(id, p);
      return true;
    }
    return false;
  }

  void addProduct(Product product){
    _products.put(product.getID(), product);
  }

  void addBatch(Batch batch){
    _batches.add(batch);
    batch.getProduct().addBatch(batch);
  }

  double getAccountableBalance() {
    return _accountableBalance;
  }

  double getAvailableBalance(){
    return _availableBalance;
  }

  /* methods related to keeping the date */
  
  static int getDate() {
	  return _date;
  }

  void advanceDate(int timeIncrease) {
	  _date += timeIncrease;
  }

  void doToggleNotification(String partnerID, String productId){
    _partners.get(partnerID).toggleNotification(_products.get(productId));
  }

  double getPartnerPayments(String partnerID){
    return _partners.get(partnerID).getPartnerPayments();
  }  

  boolean hasProduct(String productID){
    return (_products.get(productID) != null);
  }

  String getPartnerToString(String partnerID){
    return _partners.get(partnerID).toString();
  }

  Collection<Acquisition> getPartnerAcquisitions(String partnerID){
    return _partners.get(partnerID).getPartnerAcquisitions();
  }
  Collection<Sale> getPartnerSales(String partnerID){
    return _partners.get(partnerID).getPartnerSales();
  }

  ArrayList<String> getBatchesUnderPriceToString(String productId, double price){
    ArrayList<String> res = new ArrayList<>();

    for(Batch b : _batches)
      if(b.getProduct().getID() == productId && b.getTotalPrice() < price)
        res.add(b.toString());

    return res;
  }
}

