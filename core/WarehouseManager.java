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

  public Partner createPartner(String id, String name, String address){
    return createPartner(id, name, address);
  }

  public void addPartner(Partner partner){
    _warehouse.addPartner(partner);;
  }
 
  public Product createSimpleProduct(String id, double price){
    return createSimpleProduct(id, price);
  }

  public void addSimpleProduct(SimpleProduct product){
    _warehouse.addProduct(product);;
  }

  public Batch createBatch(Partner supplier, double price, int stock, String productID){
    return createBatch(supplier, price, stock, productID);
  }

  void addBatch(Batch batch){
    addBatch(batch);;
  }

  /**
   * @@throws IOException
   * @@throws FileNotFoundException
   * @@throws MissingFileAssociationException
   */
  public void save() throws IOException, FileNotFoundException, MissingFileAssociationException {
    ArrayList<Partner> parts = new ArrayList<>();
    ArrayList<Product> prods = new ArrayList<>();
      
    try (ObjectOutputStream obOut = 
        new ObjectOutputStream(new FileOutputStream(_filename))) {
      parts.addAll(_warehouse.getPartners());
      prods.addAll(_warehouse.getProducts());
      obOut.writeObject(parts);
      obOut.writeObject(prods);

      obOut.close();
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
    try {
      ObjectInputStream objIn = null;
      objIn = new ObjectInputStream(new FileInputStream(filename));
      objIn.readObject();
      if (objIn != null)
        objIn.close();
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
      _warehouse.importFile(textfile);
    } catch (IOException | BadEntryException | UnavailableFileException e) {
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

}

