package ggc.app.products;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.core.WarehouseManager;
import ggc.app.exception.UnknownProductKeyException;
import ggc.core.Batch;

/**
 * Show all products.
 */
class DoShowBatchesByProduct extends Command<WarehouseManager> {

  DoShowBatchesByProduct(WarehouseManager receiver) {
    super(Label.SHOW_BATCHES_BY_PRODUCT, receiver);

    addStringField(Label.SHOW_BATCHES_BY_PRODUCT, Message.requestProductKey());
  }

  @Override
  public final void execute() throws CommandException {
  String productId = stringField(Label.SHOW_BATCHES_BY_PRODUCT);

  try {
    _receiver.getProduct(productId);

    for(Batch batch: _receiver.getProductBatches(productID))
      _display.addLine(batch.toString());
    _display.display();

  } catch( NullPointerException npe) {
      throw new UnknownProductKeyException(productId);
  }
}
}
