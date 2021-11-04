package ggc.app.partners;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.core.WarehouseManager;
import ggc.core.Partner;
import ggc.app.exception.DuplicatePartnerKeyException;
/**
 * Register new partner.
 */
class DoRegisterPartner extends Command<WarehouseManager> {

  DoRegisterPartner(WarehouseManager receiver) {
    super(Label.REGISTER_PARTNER, receiver);
  }

  @Override
  public void execute() throws CommandException {
    String partnerID;
    String partnerName;
    String partnerAddress;

    addStringField(Label.REGISTER_PARTNER, Message.requestPartnerKey());
    partnerID = stringField(Label.REGISTER_PARTNER);      
    
    addStringField(Label.REGISTER_PARTNER, Message.requestPartnerName());
    partnerName = stringField(Label.REGISTER_PARTNER);

    addStringField(Label.REGISTER_PARTNER, Message.requestPartnerAddress());
    partnerAddress = stringField(Label.REGISTER_PARTNER);


    try{
      super.performCommand();
      Partner partner = _receiver.createPartner(partnerID, partnerName, partnerAddress);
      if((_receiver.getPartners()).contains(partner))
        throw new DuplicatePartnerKeyException(partnerID);

    } catch(CommandException e){
      throw e;
    }
  }

}
