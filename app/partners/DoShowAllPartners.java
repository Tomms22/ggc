package ggc.app.partners;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.core.WarehouseManager;
import java.util.Collection;

/**
 * Show all partners.
 */
class DoShowAllPartners extends Command<WarehouseManager> {

  DoShowAllPartners(WarehouseManager receiver) {
    super(Label.SHOW_ALL_PARTNERS, receiver);
  }

  @Override
  public void execute(){ /*throws CommandExceptio*/
    try{
      for(Object partner: _receiver.getPartners())
        _display.addLine(partner.toString());
      _display.display();
    } catch(NullPointerException e) {
      _display.popup("null");
    } catch(StackOverflowError e) {
      _display.popup("stackoverflow");
    } catch(Exception e) {
      _display.popup("error");
    }
  }
}
