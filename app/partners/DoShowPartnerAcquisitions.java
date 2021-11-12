package ggc.app.partners;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.core.WarehouseManager;
import ggc.app.exception.UnknownPartnerKeyException;

/**
 * Show all transactions for a specific partner.
 */
class DoShowPartnerAcquisitions extends Command<WarehouseManager> {

  DoShowPartnerAcquisitions(WarehouseManager receiver) {
    super(Label.SHOW_PARTNER_ACQUISITIONS, receiver);
    addStringField("id", Message.requestPartnerKey());
  }

  /** 
  * @throws CommandsException, UnknownPartnerException
  */
  @Override
  public void execute() throws CommandException, UnknownPartnerKeyException {
    String partnerId = stringField("id");

    try{
      for(Object acquisition: _receiver.getPartnerAcquisitions(partnerId))
        _display.addLine(acquisition);
      _display.display();
    } catch( NullPointerException npe) {
        throw new UnknownPartnerKeyException(partnerId);
    }
  }
}
