package ggc.core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ggc.core.exception.BadEntryException;

class Parser {
  private WarehouseManager _store;

  Parser(WarehouseManager warehouse) {
    _store = warehouse;
  }

  void parseFile(String filename) throws IOException, BadEntryException {
    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
      String line;

      while ((line = reader.readLine()) != null)
        parseLine(line);
    }
  }

  private void parseLine(String line) throws BadEntryException, BadEntryException {
    String[] components = line.split("\\|");

    switch (components[0]) {
      case "PARTNER":
        parsePartner(components, line);
        break;
      case "BATCH_S":
        parseSimpleProduct(components, line);
        break;
      case "BATCH_M":
        parseAggregateProduct(components, line);
        break;
        
      default:
        throw new BadEntryException("Invalid type element: " + components[0]);
    }
  }

  //PARTNER|id|nome|endereço
  private void parsePartner(String[] components, String line) throws BadEntryException {
    if (components.length != 4)
      throw new BadEntryException("Invalid partner with wrong number of fields (3): " + line);
    
    String id = components[1];
    String name = components[2];
    String address = components[3];

    _store.addPartner(id, name, address);
  }

  //BATCH_S|idProduto|idParceiro|preço|stock-actual
  private void parseSimpleProduct(String[] components, String line) throws BadEntryException {
    if (components.length != 5)
      throw new BadEntryException("Invalid number of fields (4) in simple batch description: " + line);
    
    String productId = components[1];
    String partnerId = components[2];
    double price = Double.parseDouble(components[3]);
    int stock = Integer.parseInt(components[4]);
    
    if(_store.getProduct(productId) == null)
      _store.addProduct(new SimpleProduct(productId));
    
    Product product = _store.getProduct(productId);
    Partner supplier = _store.getPartner(partnerId);

    _store.addBatch(supplier, price, stock, product);
  }
 
  //BATCH_M|idProduto|idParceiro|preço|stock-actual|agravamento|componente-1:quantidade-1#...#componente-n:quantidade-n
  private void parseAggregateProduct(String[] components, String line) throws BadEntryException {
    if (components.length != 7)
      throw new BadEntryException("Invalid number of fields (7) in aggregate batch description: " + line);
    
    String productId = components[1];
    String partnerId = components[2];

    if (_store.getProduct(productId) == null) {
      List<Product> products = new ArrayList<>();
      List<Integer> quantities = new ArrayList<>();
      
      for (String component : components[6].split("#")) {
        String[] recipeComponent = component.split(":");
        products.add(_store.getProduct(recipeComponent[0]));
        quantities.add(Integer.parseInt(recipeComponent[1]));
      }
      
      Recipe recipe = new Recipe(products, quantities);

      _store.addProduct(new AggregateProduct(productId, Double.parseDouble(components[5]), recipe));

    }
    
    Product product = _store.getProduct(productId);
    Partner supplier = _store.getPartner(partnerId);
    double price = Double.parseDouble(components[3]);
    int stock = Integer.parseInt(components[4]);

    _store.addBatch(supplier, price, stock, product);
  }
}