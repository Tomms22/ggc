package ggc.app.partners;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.core.WarehouseManager;
import ggc.core.Partner;
import ggc.app.exception.DuplicatePartnerKeyException;
import java.lang.NullPointerException;
/**
 * Register new partner.
 */
class DoRegisterPartner extends Command<WarehouseManager> { 
	DoRegisterPartner(WarehouseManager receiver) {
	    super(Label.REGISTER_PARTNER, receiver);
		addStringField("ID", Message.requestPartnerKey());
		addStringField("name", Message.requestPartnerName());
		addStringField("address", Message.requestPartnerAddress());
  }

	@Override
	public void execute() throws CommandException {
		String partnerID;
	    String partnerName;
	    String partnerAddress;
	    
	    try {
	    	partnerID = stringField("ID");
	    	partnerName = stringField("name");
	    	partnerAddress = stringField("address");
    	
	    	Partner partner = _receiver.createPartner(partnerID, partnerName, partnerAddress);
	    	if((_receiver.getPartners()).contains(partner))
	    		throw new DuplicatePartnerKeyException(partnerID);
	    	_receiver.addPartner(partner);

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
