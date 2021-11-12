package ggc.app.partners;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.core.WarehouseManager;
import ggc.app.exception.DuplicatePartnerKeyException;
import java.lang.NullPointerException;
/**
 * Register new partner.
 */
class DoRegisterPartner extends Command<WarehouseManager> { 
	DoRegisterPartner(WarehouseManager receiver) {
	    super(Label.REGISTER_PARTNER, receiver);
		addStringField("id", Message.requestPartnerKey());
		addStringField("name", Message.requestPartnerName());
		addStringField("address", Message.requestPartnerAddress());
  }

  	/*
	*	Register Partner, @throws DuplicatePartnerKeyException
	*/
	@Override
	public void execute() throws CommandException, DuplicatePartnerKeyException {
		String id = stringField("id"), name = stringField("name"), address = stringField("address");

		if(!_receiver.addPartner(id, name, address))
			throw new DuplicatePartnerKeyException(id);
		_display.popup(_receiver.getPartnerToString(id));

	    }
	}
}
