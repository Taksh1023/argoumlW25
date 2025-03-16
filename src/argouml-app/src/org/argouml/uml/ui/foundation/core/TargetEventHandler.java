package org.argouml.uml.ui.foundation.core;

import org.argouml.ui.targetmanager.TargetEvent;
import org.argouml.ui.targetmanager.TargetListener;
import org.argouml.ui.targetmanager.TargetManager;

import javax.swing.Action;

/**
 * A reusable event handler to manage target events.
 */
public class TargetEventHandler {
    private final Action targetFollower;

    public TargetEventHandler(Action targetFollower) {
        this.targetFollower = targetFollower;
        initializeListener();
    }

    private void initializeListener() {
        TargetManager.getInstance().addTargetListener(new TargetListener() {
            public void targetAdded(TargetEvent e) {
                setTarget();
            }

            public void targetRemoved(TargetEvent e) {
                setTarget();
            }

            public void targetSet(TargetEvent e) {
                setTarget();
            }
        });
    }

    private void setTarget() {
        targetFollower.setEnabled(targetFollower.isEnabled());
    }
}
