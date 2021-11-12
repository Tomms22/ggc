package ggc.app.partners;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.core.WarehouseManager;
import ggc.app.exception.UnknownPartnerKeyException;


/**
 * Show all transactions for a specific partner.
 */
class DoShowPartnerSales extends Command<WarehouseManager> {

  DoShowPartnerSales(WarehouseManager receiver) {
    super(Label.SHOW_PARTNER_SALES, receiver);
    addStringField("ID", Message.requestPartnerKey());
  }

  /** 
  * @throws CommandsException, UnknownPartnerException
  */
  @Override
  public void execute() throws CommandException {
  String partnerId = stringField("id");

  try{
    for(Object sale: _receiver.getPartnerSales(partnerId))
    _display.addLine(sale);
  _display.display();
  } catch( NullPointerException npe) {
      throw new UnknownPartnerKeyException(partnerId);
  }
}

}
