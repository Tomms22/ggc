package ggc.core;

import java.io.Serializable;
import java.util.Collection;
import java.util.ArrayList;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import pt.tecnico.uilib.text.TextInteraction;
import ggc.core.exception.BadEntryException;

/**
 * Class Warehouse implements a warehouse.
 */
public class Warehouse implements Serializable {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202109192006L;

  private ArrayList<Product> _products;
  private ArrayList<Partner> _partners;
  private ArrayList<Batch> _batches;	
  private double _accountableBalance;
  private double _availableBalance;
  private static int _date; 
  private Parser _parser;

  public Warehouse() {
	  _parser = new Parser(this);
    _products = new ArrayList<>();
    _partners = new ArrayList<>();
    _batches = new ArrayList<>();
    _date = 0;
  }
  /**
   * @param txtfile filename to be loaded.
   * @throws IOException
   * @throws BadEntryException
   */
  void importFile(String txtfile) throws IOException, BadEntryException {
	  _parser.parseFile(txtfile);
  }
  
  Partner getPartner(String id) {
    Partner wantedPartner = null;
    for(Partner p: _partners)
      if((p.getID()).equals(id))
        wantedPartner = p;
    return wantedPartner;
  }
  
  Collection<Partner> getPartners() {
	  return _partners;
  }
  
  Product getProduct(String id) {
    Product wantedProduct = null;
    for(Product p: _products)
      if((p.getProductID()).equals(id))
        wantedProduct = p;
    return wantedProduct;
  }

  Collection<Product> getProducts() {
	  return _products;
  }

  Collection<Batch> getBatches(){
    return _batches;
  }

  Collection<Batch> getProductBatches(String productID) {
    ArrayList<Batch> productBatches = new ArrayList<>();
    for(Batch batch: _batches)
      if(productID.equals(batch.getProductID()))
        productBatches.add(batch);
    return productBatches;
  }

  Collection<Batch> getPartnerBatches(String partnerID) {
    ArrayList<Batch> partnerBatches = new ArrayList<>();
    for(Batch batch: _batches)
      if(partnerID.equals(batch.getPartnerID()))
        partnerBatches.add(batch);
    return partnerBatches;
  }

  Partner createPartner(String id, String name, String address){
    return new Partner(id, name, address);
  }

  void addPartner(Partner partner){
    _partners.add(partner);
  }

  Product createSimpleProduct(String id, double price){
    return new SimpleProduct(id, price);
  }

  void addProduct(Product product){
    _products.add(product);
  }

  Batch createBatch(Partner supplier, double price, int stock, String productID){
    return new Batch(supplier, price, stock, productID);
  }

  void addBatch(Batch batch){
    _batches.add(batch);
    getProduct(batch.getProductID()).addBatch(batch);
  }

  double getAccountableBalance(){
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

  // I love lolipops




  

}
