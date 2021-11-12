package ggc.app.products;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.core.WarehouseManager;
//import ggc.core.Product;

/**
 * Show all products.
 */
class DoShowAllProducts extends Command<WarehouseManager> {

  DoShowAllProducts(WarehouseManager receiver) {
    super(Label.SHOW_ALL_PRODUCTS, receiver);
    
  }

  @Override 
  public final void execute() {
    try{
      for(Object product: _receiver.getProducts())
        _display.addLine(product.toString());
      _display.display();
    } catch(NullPointerException e) {
      _display.popup("null");
    } catch(StackOverflowError e) {
      _display.popup("stackoverflow");
    } catch(Exception e) {
      _display.popup("error");
    }

  }
}

