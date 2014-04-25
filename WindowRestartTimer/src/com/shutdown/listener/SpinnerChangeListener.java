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
import com.shutdown.exception.DayNotExistException;
import com.shutdown.exception.TimeFormatNotMatchException;
import com.shutdown.exception.TimeRangeException;

/**
 * Listen for Spinner when correspondence JSpinner is Changed.
 * 
 * @author Raymond
 * 
 */
public class SpinnerChangeListener implements ChangeListener {

    /**
     * Basic Constructor
     * 
     */
    public SpinnerChangeListener() {

    }

    @Override
    public void stateChanged(ChangeEvent arg0) {

	if (arg0.getSource() instanceof JSpinner) {
	    JSpinner jcb = (JSpinner) arg0.getSource();
	    // Try to get a ShutdownUserInterface, the main Interface
	    Object sui = jcb.getParent().getParent().getParent().getParent()
		    .getParent().getParent();
	    // Get a Main Panel
	    JPanel mainpanel = (JPanel) ((JPanel) ((JSpinner) arg0.getSource())
		    .getParent()).getParent();
	    // Get a Time Panel
	    JPanel timepanel = (JPanel) ((JSpinner) arg0.getSource())
		    .getParent();

	    // mainpanel has 4 Components
	    // 1.displaypanel,2.radiopanel,3.daypanel,4.timelabelpanel,5.timepanel

	    if (mainpanel.getComponentCount() == 5) {
		String selectedday = "";
		boolean checkboxisSelected = false;
		// Get Current Selected Day From Radio Button
		for (Component c : ((JPanel) mainpanel.getComponent(1))
			.getComponents()) {
		    if (c instanceof JRadioButton) {
			if (((JRadioButton) c).isSelected()) {
			    selectedday = ((JRadioButton) c).getText();
			}
		    }
		}
		// Get the Day isSelected
		for (Component c : ((JPanel) mainpanel.getComponent(2))
			.getComponents()) {
		    if (c instanceof JCheckBox) {
			if (((JCheckBox) c).getName().equals(selectedday)) {
			    checkboxisSelected = ((JCheckBox) c).isSelected();
			}
		    }
		}
		if (checkboxisSelected) {
		    String shuttime = "";
		    int timeforspinnercounter = 0;
		    // loop a Component in timePanel, form the string of time
		    // HH:MM:SS
		    // and put it into dataset.
		    for (Component spc : timepanel.getComponents()) {
			if (spc instanceof JSpinner) {
			    //
			    if (((JSpinner) spc).getValue().toString().length() == 1)
				shuttime += "0"
					+ ((JSpinner) spc).getValue()
						.toString();
			    else
				shuttime += ((JSpinner) spc).getValue()
					.toString();
			    if (timeforspinnercounter < 2) {
				shuttime += ":";
				timeforspinnercounter++;
			    }
			}
		    }

		    // Set the time for restart.
		    try {
			ShutdownProgram.GETDATASET().setShutdownTime(
				selectedday, shuttime);
		    } catch (DayNotExistException | TimeFormatNotMatchException
			    | TimeRangeException e) {

			e.printStackTrace();
		    }
		    // AutoSave
		    ShutdownProgram.SAVEDATASFILE();
		    // Update Shutdown
		    ShutdownProgram.SHUTDOWNTASK().updateShutdwonTime();
		   // update data on screen.
		    ((ShutdownUserInterface) sui).updateData();
		}
	    }

	}
    }

}
