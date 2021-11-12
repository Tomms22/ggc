package ggc.app.lookups;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.core.WarehouseManager;

/**
 * Lookup products cheaper than a given price.
 */
public class DoLookupProductBatchesUnderGivenPrice extends Command<WarehouseManager> {

  public DoLookupProductBatchesUnderGivenPrice(WarehouseManager receiver) {
    super(Label.PRODUCTS_UNDER_PRICE, receiver);
    addStringField("productId", "Registar Produto:");
    // alterar string mensagem
    addRealField(Label.PRODUCTS_UNDER_PRICE, "Produtos abaixo do preço:");
  }

  @Override
  public void execute() throws CommandException {
    String productId = stringField("productId");
    double price = realField(Label.PRODUCTS_UNDER_PRICE);
    
    for(String batchString : _receiver.getBatchesUnderPriceToString(productId, price))
      _display.addLine(batchString);
    _display.display();
  }

}
