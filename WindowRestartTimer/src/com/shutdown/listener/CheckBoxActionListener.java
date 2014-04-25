package com.shutdown.listener;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JSpinner;

import com.shutdown.ShutdownProgram;
import com.shutdown.ShutdownUserInterface;
import com.shutdown.exception.DayNotExistException;
import com.shutdown.exception.TimeFormatNotMatchException;
import com.shutdown.exception.TimeRangeException;

/**
 * ActionListen for the reaction of day CheckBox is selected or deselected
 * 
 * @author Raymond
 * 
 */
public class CheckBoxActionListener implements ActionListener {

    /**
     * Basic Constructor
     * 
     */
    public CheckBoxActionListener() {

    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
	//DO it when the Source is JCheckBox
	if (arg0.getSource() instanceof JCheckBox) {
	    JCheckBox jcb = (JCheckBox) arg0.getSource();
	    
	    //Try to get a ShutdownUserInterface, the main Interface
	    Object sui = jcb.getParent().getParent().getParent().getParent()
		    .getParent().getParent();
	    //Do it when the Source is ShutdownUserInterface
	    if (sui instanceof ShutdownUserInterface) {
		if (jcb.isSelected()) {
		    //The day is selected
		  //Set the active is true.
		    try {
			ShutdownProgram.GETDATASET().setShutdownDay(
				jcb.getName(), true);
		    } catch (DayNotExistException e) {
			e.printStackTrace();
		    }
		    Component[] c = ((ShutdownUserInterface)sui).getComponents();
		    JPanel timepanel = ((JPanel)((JPanel)((JPanel)((JLayeredPane)((JRootPane)c[0]).getComponents()[1]).getComponents()[0]).getComponents()[0]).getComponents()[4]);
		    
		    String shuttime="";
		    int timeforspinnercounter = 0;
		    // loop a Component in timePanel, form the string of time HH:MM:SS and put it into dataset.
		    for (Component spc : timepanel.getComponents()) {
			if (spc instanceof JSpinner) {
			    //
			    if(((JSpinner) spc).getValue().toString().length()==1)
				shuttime += "0"+((JSpinner) spc).getValue().toString();
			    else
				shuttime += ((JSpinner) spc).getValue().toString();
			    if(timeforspinnercounter <2){
				shuttime += ":";
				timeforspinnercounter++;
			    }
			}
		    }

		   //Set the time for restart.
		    try {
			ShutdownProgram.GETDATASET().setShutdownTime(jcb.getName(), shuttime);
		    } catch (DayNotExistException | TimeFormatNotMatchException
			    | TimeRangeException e) {

			e.printStackTrace();
		    }

		   
		    
		} else {
		    //The day is not selected
		    try {
			//Set the active is false.
			ShutdownProgram.GETDATASET().setShutdownDay(
				jcb.getName(), false);
		    } catch (DayNotExistException e) {
			e.printStackTrace();
		    }
		    
		  
		}
		//AutoSave
		ShutdownProgram.SAVEDATASFILE();
		//update Shutdown
		ShutdownProgram.SHUTDOWNTASK().updateShutdwonTime();
		 //update data on screen.

		((ShutdownUserInterface)sui).updateData();
	    }

	}
    }

}
