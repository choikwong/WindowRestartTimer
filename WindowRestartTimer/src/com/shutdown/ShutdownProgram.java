package com.shutdown;

import java.io.File;
import java.net.URL;
import java.util.Calendar;
import java.util.Map;
import java.util.Timer;
import java.util.concurrent.Callable;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;

public class ShutdownProgram{

    private static ShutdownTask DATASET;
    private static ShutdownSaveHandler SAVEANDLOADHANDLER;
    private static ShutdownTimer SHUTDOWNTASK;
    private static ShutdownUserInterface SHUTDOWNINTERFACE;
    private static Timer t;
    public static void LOADDATASFILE() {
	if(SAVEANDLOADHANDLER==null){
	    SAVEANDLOADHANDLER = new ShutdownSaveHandler(); 
	}
	if(!SAVEANDLOADHANDLER.isFileExisted()){
	    SAVEANDLOADHANDLER.createSavedFile();
	} 
	if(SAVEANDLOADHANDLER.isBlankFile()){
	    SAVEANDLOADHANDLER.saveToFile(GETDATASET().getAllShutdown_Days(),GETDATASET().getAllShutdownTime());
	}else{
	   Map[] m = SAVEANDLOADHANDLER.readFromFile();
	   GETDATASET().setAllShutdown_Days(m[0]);
	   GETDATASET().setAllShutdownTimes(m[1]);
	   
	}
    }
    
    public static void SAVEDATASFILE() {
	if(SAVEANDLOADHANDLER==null){
	    SAVEANDLOADHANDLER = new ShutdownSaveHandler(); 
	}
	if(!SAVEANDLOADHANDLER.isFileExisted()){
	    SAVEANDLOADHANDLER.createSavedFile();
	} 
	SAVEANDLOADHANDLER.saveToFile(GETDATASET().getAllShutdown_Days(),GETDATASET().getAllShutdownTime());
    }
    
    
    public static ShutdownTask GETDATASET() {
	if(DATASET==null){
	    DATASET = new ShutdownTask(); 
	}
	return DATASET;
    }
    
    public static ShutdownTimer SHUTDOWNTASK() {
	if(SHUTDOWNTASK==null){
	    SHUTDOWNTASK = new ShutdownTimer(); 
	}
	return SHUTDOWNTASK;
    }
    
    public static ShutdownUserInterface GETSHUTDOWNINTERFACE() {
	if(SHUTDOWNINTERFACE==null){
	    SHUTDOWNINTERFACE = new ShutdownUserInterface(); 
	}
	return SHUTDOWNINTERFACE;
    }
    
    public static void STARTIMER() {
	if(t==null){
	   t = new Timer();
	}
	t.schedule(SHUTDOWNTASK(), 1000,1000);
    }
    
    public static void STOPTIMER() {
	if(!(t==null)){
	    t.cancel();
	    t=null;
	}
    }
    
    public static void main(String[] args) {
	LOADDATASFILE();
	STARTIMER();
	GETSHUTDOWNINTERFACE();
    }

}
