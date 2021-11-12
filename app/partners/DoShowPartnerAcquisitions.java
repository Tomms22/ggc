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
    addStringField("ID", Message.requestPartnerKey());
  }

  /** 
  * @throws CommandsException, UnknownPartnerException
  */
  @Override
  public void execute() throws CommandException, UnknownPartnerKeyException {
    String id = stringField("ID");

    try{
      if(_receiver.getPartner(id) == null)
        throw new UnknownPartnerKeyException(id);
      for(Object acquisition: _receiver.getPartnerAcquisitions(id))
        _display.addLine(acquisition);
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
