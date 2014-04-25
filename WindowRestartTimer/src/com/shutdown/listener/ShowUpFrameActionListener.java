package com.shutdown.listener;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

/**
 * ActionListen for showup a window,e.g JDialog, JFrame etc..
 * 
 * @author Raymond
 * 
 */
public class ShowUpFrameActionListener implements ActionListener {

    private Window w;

    /**
     * Constructor with necessary of Window reference
     * 
     * @param w
     */
    public ShowUpFrameActionListener(Window w) {
	this.w = w;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	this.w.setVisible(true);
	this.w.setFocusable(true);
	this.w.setOpacity(0.90f);
	// set the window appear on the right bottom corner of screen
	GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment()
		.getDefaultScreenDevice();
	int width = gd.getDisplayMode().getWidth();
	int height = gd.getDisplayMode().getHeight();
	this.w.setLocation(width - this.w.getWidth() - 5,
		height - this.w.getHeight() - 50);

    }
}
