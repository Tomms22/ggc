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
    return _partners.get(partnerId.toUpperCase());
  }
  
  Collection<Partner> getPartners() {
    TreeMap<String, Partner> sortedPartners = new TreeMap<>();
    sortedPartners.putAll(_partners);
	  return sortedPartners.values();
  }
  
  Product getProduct(String productId) {
    return _products.get(productId.toUpperCase());
  }

  Collection<Product> getProducts() {
	  return _products.values();
  }

  Collection<Batch> getBatches(){
    return _batches;
  }

  Collection<Batch> getProductBatches(String productId) {
    return getProduct(productId).getBatches();
  }

  Collection<Batch> getPartnerBatches(String partnerId) {
    ArrayList<Batch> partnerBatches = new ArrayList<>(); 
    for (Batch batch: _batches) {
      String supplierId = batch.getPartner().getId().toUpperCase();
      if ((partnerId.toUpperCase()).equals(supplierId))
        partnerBatches.add(batch);
    }
    return partnerBatches;
  }


  // devolve true se teve sucesso em adicionar o parceiro e false caso contrario
  boolean addPartner(Partner partner){
    String partnerId = partner.getId().toUpperCase();
    Boolean exists = false;
    if(_partners.containsKey(partnerId))
      exists = true;
    else
      _partners.put(partnerId, partner);
    return !exists;
  }

  void addProduct(Product product){
    _products.put(product.getId().toUpperCase(), product);
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
    _partners.get(partnerID.toUpperCase()).toggleNotification(_products.get(productId.toUpperCase()));
  }

  double getPartnerPayments(String partnerID){
    return _partners.get(partnerID.toUpperCase()).getPartnerPayments();
  }  

  Collection<Acquisition> getPartnerAcquisitions(String partnerID){
    return _partners.get(partnerID.toUpperCase()).getPartnerAcquisitions();
  }
  Collection<Sale> getPartnerSales(String partnerID){
    return _partners.get(partnerID.toUpperCase()).getPartnerSales();
  }
}

