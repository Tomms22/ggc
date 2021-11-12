package ggc.app.partners;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.core.WarehouseManager;
import ggc.app.exception.UnknownPartnerKeyException;

/**
 * Show partner.
 */
class DoShowPartner extends Command<WarehouseManager> {

  DoShowPartner(WarehouseManager receiver) {
    super(Label.SHOW_PARTNER, receiver);
    addStringField(Label.SHOW_PARTNER, Message.requestPartnerKey());
  }

  @Override
  public void execute() throws CommandException {
    String partnerId = stringField(Label.SHOW_PARTNER); 

    try {
      Partner = _receiver.getPartner(partnerId);
      _display.popup(partner.toString());
    } catch( NullPointerException npe) {
        throw new UnknownPartnerKeyException(partnerId);
    }
  }
}
