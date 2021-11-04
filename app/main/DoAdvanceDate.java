package ggc.app.main;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.app.exception.InvalidDateException;
import ggc.core.WarehouseManager;


/**
 * Advance current date.
 */
class DoAdvanceDate extends Command<WarehouseManager> {

  DoAdvanceDate(WarehouseManager receiver) {
    super(Label.ADVANCE_DATE, receiver);
    // "Número de dias a avançar: "
    addIntegerField(Label.ADVANCE_DATE, Message.requestDaysToAdvance());
  }

  // Advances date through WarehouseManager
  // CommandException thrown by performCommand()
  @Override
  public final void execute() throws CommandException {

    int timeIncrease = integerField(Label.ADVANCE_DATE);

    if (timeIncrease < 0)
      throw new InvalidDateException(timeIncrease);
      
      _receiver.advanceDate(timeIncrease);
      _display.popup(Message.currentDate(_receiver.getDate()));
  }

}
