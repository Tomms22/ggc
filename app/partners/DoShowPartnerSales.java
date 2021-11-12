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
    String id = stringField("ID");

    try{
      if(_receiver.getPartner(id) == null)
        throw new UnknownPartnerKeyException(id);
      for(Object sale: _receiver.getPartnerSales(id))
        _display.addLine(sale);
      _display.display();
    } catch(CommandException e){
      throw e;
    } catch(NullPointerException e) {
      _display.popup("null");
    } catch(StackOverflowError e) {
      _display.popup("stackoverflow");
    } catch(Exception e) {
      _display.popup("error");
    }
  }

}
