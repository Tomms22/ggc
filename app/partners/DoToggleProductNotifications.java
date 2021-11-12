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
    addStringField("partnerId", Message.requestPartnerKey());
    addStringField("productId", Message.requestProductKey());
  }

  @Override
  public void execute() throws CommandException {
    String partnerId = stringField("partnerId");
    String productId = stringField("productId");

    try{
        _receiver.getPartner(partnerId);
        _receiver.doToggleNotification(partnerId, productId);
        try{
          _receiver.getProduct(productId);
        } catch(NullPointerException npe) {
            throw new UnknownProductKeyException(productId);
        }
    } catch(NullPointerException npe) {
        throw new UnknownPartnerKeyException(partnerId);
    }
  }
}
