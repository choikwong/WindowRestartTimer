package com.shutdown.listener;

import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.shutdown.ShutdownProgram;
import com.shutdown.ShutdownUserInterface;

/**
 * Listen for Enable JCheckBox when correspondence JRadioButton is selected.
 * 
 * @author Raymond
 * 
 */
public class RadioButtonSwapListener implements ChangeListener {

    /**
     * Basic Constructor
     * 
     */
    public RadioButtonSwapListener() {

    }

    @Override
    public void stateChanged(ChangeEvent arg0) {
	// get current active radio button
	if (arg0.getSource() instanceof JRadioButton) {
	    JRadioButton jrb = (JRadioButton) arg0.getSource();
	    if (jrb.isSelected()) {
		Object sui = jrb.getParent().getParent().getParent()
			.getParent().getParent().getParent();
		if (sui instanceof ShutdownUserInterface) {
		    // updata interface
		    ((ShutdownUserInterface) sui).updateData();
		}
	    }
	}
    }

}
