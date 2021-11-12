package ggc.app.products;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.app.exception.UnknownPartnerKeyException;
import ggc.core.WarehouseManager;
import ggc.core.Batch;

/**
 * Show batches supplied by partner.
 */
class DoShowBatchesByPartner extends Command<WarehouseManager> {

  DoShowBatchesByPartner(WarehouseManager receiver) {
    super(Label.SHOW_BATCHES_SUPPLIED_BY_PARTNER, receiver);
    
    addStringField(Label.SHOW_BATCHES_SUPPLIED_BY_PARTNER, Message.requestPartnerKey());
  }

  @Override
  public final void execute() throws CommandException, UnknownPartnerKeyException {
    String partnerId = stringField(Label.SHOW_BATCHES_SUPPLIED_BY_PARTNER);

    try {
      _receiver.getPartner(partnerId);

      for(Batch batch: _receiver.getPartnerBatches(partnerId))
        _display.addLine(batch.toString());
      _display.display();
    } catch( NullPointerException npe) {
        throw new UnknownPartnerKeyException(partnerId);
    }
  }
}

