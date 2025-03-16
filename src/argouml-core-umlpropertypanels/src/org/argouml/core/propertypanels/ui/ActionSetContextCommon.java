package org.argouml.core.propertypanels.ui;
import java.awt.event.ActionEvent;
import javax.swing.Action;

import org.argouml.i18n.Translator;
import org.argouml.model.Model;
import org.argouml.ui.UndoableAction;

/**
 * A common action used to set the context of UML elements (both ActivityGraph and StateMachine).
 * This class consolidates the duplicated behavior from the inner classes
 * ActionSetContext and ActionSetContextStateMachine.
 * Reusable in any context combo box models that need to update the context of a UML element.
 */
class ActionSetContextCommon extends UndoableAction {

    private static final long serialVersionUID = -8118983979324112900L;

    /**
     * Constructor sets up the action's name and tooltip.
     */
    protected ActionSetContextCommon() {
        super(Translator.localize("action.set"), null);
        // Setting tooltip description
        putValue(Action.SHORT_DESCRIPTION, Translator.localize("action.set"));
    }

    /**
     * Handles the action event when a new item is selected in the combo box.
     *
     * It compares the current context of the target with the selected item,
     * and if they differ, updates the context using StateMachinesHelper.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        UMLComboBox source = (UMLComboBox) e.getSource();
        Object target = source.getTarget();
        if (Model.getFacade().getContext(target) != source.getSelectedItem()) {
            Model.getStateMachinesHelper().setContext(target, source.getSelectedItem());
        }
    }
}
