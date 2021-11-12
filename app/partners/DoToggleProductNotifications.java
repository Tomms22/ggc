package ggc.app.partners;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.core.WarehouseManager;
import ggc.app.exception.UnknownPartnerKeyException;
import ggc.app.exception.UnknownProductKeyException;

/**
 * Toggle product-related notifications.
 */
class DoToggleProductNotifications extends Command<WarehouseManager> {

  DoToggleProductNotifications(WarehouseManager receiver) {
    super(Label.TOGGLE_PRODUCT_NOTIFICATIONS, receiver);
    addStringField("partnerID", Message.requestPartnerKey());
    addStringField("productID", Message.requestProductKey());
  }

  @Override
  public void execute() throws CommandException {
    String partnerID = stringField("partnerID");
    String productID = stringField("productID");

    try{
      if(_receiver.getPartner(partnerID) == null)
        throw new UnknownPartnerKeyException(partnerID);
      if(_receiver.getProduct(productID) == null)
        throw new UnknownProductKeyException(productID);
      _receiver.doToggleNotification(partnerID, productID);
    } catch(NullPointerException e) {
      _display.popup("null");
    }
  }

}
