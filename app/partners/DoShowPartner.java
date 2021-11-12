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
    String partnerID = stringField(Label.SHOW_PARTNER);;

    if(_receiver.getPartner(partnerID) == null)
      throw new UnknownPartnerKeyException(partnerID); 
    else 
      _display.popup(_receiver.getPartnerToString(partnerID));
    }



}
