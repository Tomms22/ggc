package ggc.core;

import java.util.ArrayList;
import java.util.Collection;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;

import ggc.core.exception.BadEntryException;
import ggc.core.exception.ImportFileException;
import ggc.core.exception.UnavailableFileException;
import ggc.core.exception.MissingFileAssociationException;

/** Façade for access. */
public class WarehouseManager {

  /** Name of file storing current warehouse. */
  private String _filename = "";

  /** The warehouse itself. */
  private Warehouse _warehouse;

  public WarehouseManager() {
    _warehouse = new Warehouse();
  }
  
  public Partner getPartner(String id) {
	  return _warehouse.getPartner(id);
  }
  
  public Collection<Partner> getPartners() {
	  return _warehouse.getPartners();
  }

  public Product getProduct(String id) {
	  return _warehouse.getProduct(id);
  }

  public Collection<Product> getProducts() {
	  return  _warehouse.getProducts();
  }

  public Collection<Batch> getBatches(){
    return _warehouse.getBatches();
  }

  public Collection<Batch> getProductBatches(String productID) {
    return _warehouse.getProductBatches(productID);
  }

  public Collection<Batch> getPartnerBatches(String partnerID) {
    return _warehouse.getPartnerBatches(partnerID);
  }

  public boolean addPartner(String id, String name, String address){
    return _warehouse.addPartner(id, name, address);
  }

  public void addProduct(String id, double price){
    _warehouse.addProduct(new SimpleProduct(id, price));
  }

  public void addBatch(Partner supplier, double price, int stock, String productId) {
    _warehouse.addBatch(new Batch(supplier, price, stock, _warehouse.getProduct(productId)));
  }

  public   Collection<Batch> getBatchesUnderGivenPrice(double price) {
    List<Batch> batchesUnderGivenPrice = new ArrayList<>();
    
    for(Batch batch: _batches)
      if(batch.getPrice() < price)
        batchesUnderGivenPrice.add(batch);
    
    return batchesUnderGivenPrice;
  }

  /**
   * @@throws IOException
   * @@throws FileNotFoundException
   * @@throws MissingFileAssociationException
   */
  public void save() throws IOException, FileNotFoundException, MissingFileAssociationException {
    try (ObjectOutputStream obOut = new ObjectOutputStream(new FileOutputStream(_filename))) {

      obOut.writeObject(_warehouse);
      obOut.writeObject(Warehouse.getDate());
      obOut.writeObject(Transaction.getNumberOfTransactions());

    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
  }

  /**
   * @@param filename
   * @@throws MissingFileAssociationException
   * @@throws IOException
   * @@throws FileNotFoundException
   */
  public void saveAs(String filename) throws MissingFileAssociationException, FileNotFoundException, IOException {
    _filename = filename;
    save();
  }

  /**
   * @@param filename
   * @@throws UnavailableFileException
   */
  public void load(String filename) throws UnavailableFileException, ClassNotFoundException  {
    try (ObjectInputStream objIn = new ObjectInputStream(new FileInputStream(filename))) {

      objIn.readObject();

    } catch (IOException ioe) {
        ioe.printStackTrace();
    }
  }

  /**
   * @param textfile
   * @throws ImportFileException
   */
  public void importFile(String textfile) throws ImportFileException {
    try {
      _warehouse.importFile(textfile, new Parser(this));
    } catch (IOException | BadEntryException e) {
      throw new ImportFileException(textfile, e);
    }
  }

  public void advanceDate(int timeIncrease){
    _warehouse.advanceDate(timeIncrease);
  }

  public int getDate(){
    return Warehouse.getDate();
  }

  public double getAccountableBalance(){
    return _warehouse.getAccountableBalance();
  }

  public double getAvailableBalance(){
    return _warehouse.getAvailableBalance();
  }

  public double getPartnerPayments(String partnerID){
    return _warehouse.getPartnerPayments(partnerID);
  }

  public void doToggleNotification(String partnerID, String product){
    _warehouse.doToggleNotification(partnerID, product);
  }

  public String getPartnerToString(String partnerID){
    return _warehouse.getPartnerToString(partnerID);
  }

  public Collection<Acquisition> getPartnerAcquisitions(String partnerID){
    return _warehouse.getPartnerAcquisitions(partnerID);    
  }

  public Collection<Sale> getPartnerSales(String partnerID){
    return _warehouse.getPartnerSales(partnerID);    
  }

}

