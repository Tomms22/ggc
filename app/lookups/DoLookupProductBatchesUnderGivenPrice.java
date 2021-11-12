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
    addRealField(Label.PRODUCTS_UNDER_PRICE, Message.requestPriceLimit());
  }

  @Override
  public void execute() throws CommandException {
    double price = realField(Label.PRODUCTS_UNDER_PRICE);

    for(Batch batch: _receiver.getProductBatchesUnderGivenPrice(price))
        _display.addLine(batch.toString());
    _display.display();
  }

}
