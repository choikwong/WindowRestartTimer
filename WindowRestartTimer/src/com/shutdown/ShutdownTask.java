package com.shutdown;

import java.util.HashMap;
import java.util.Map;

import com.shutdown.exception.DayNotExistException;
import com.shutdown.exception.TimeFormatNotMatchException;
import com.shutdown.exception.TimeRangeException;

/**
 * Class is holding the data of shutdown.
 * 
 */

public class ShutdownTask {
    final public static String MONDAY = "Mon";
    final public static String TUESDAY = "Tue";
    final public static String WEDNESDAY = "Wed";
    final public static String THURDAY = "Thu";
    final public static String FRIDAY = "Fri";
    final public static String SATURDAY = "Sat";
    final public static String SUNDAY = "Sun";
    private Map shutdown_Day;
    private Map shutdown_Time;

    /**
     * Default Constructor, Data is Auto inital to start. 
     */
    public ShutdownTask() {
	this.shutdown_Day = new HashMap<String, Boolean>();
	this.shutdown_Time = new HashMap<String, String>();
	initalMap();
    }
    /**
     * Constructor, Data is passing by parameter.
     * @param shutdown_Day <br>
     * HashMap<String, Boolean>
     * @param shutdown_Time <br>
     * HashMap<String, String>
     */
    public ShutdownTask( Map shutdown_Day, Map shutdown_Time) {
	this.shutdown_Day = shutdown_Day;
	this.shutdown_Time = shutdown_Time;
    }

    /**
     * inital a data set.
     */
    private void initalMap() {
	this.shutdown_Day.put("Mon", false);
	this.shutdown_Day.put("Tue", false);
	this.shutdown_Day.put("Wed", false);
	this.shutdown_Day.put("Thu", false);
	this.shutdown_Day.put("Fri", false);
	this.shutdown_Day.put("Sat", false);
	this.shutdown_Day.put("Sun", false);
	this.shutdown_Time.put("Mon", "00:00:00");
	this.shutdown_Time.put("Tue", "00:00:00");
	this.shutdown_Time.put("Wed", "00:00:00");
	this.shutdown_Time.put("Thu", "00:00:00");
	this.shutdown_Time.put("Fri", "00:00:00");
	this.shutdown_Time.put("Sat", "00:00:00");
	this.shutdown_Time.put("Sun", "00:00:00");

    }

    /**
     * Method indicate the shutdown a specify day which the parameter is given.
     * 
     * @param day
     * <br>
     *            is being set form Mon - Sun as String.
     * @return indicating the affect for shutdown.
     */
    public boolean isShutdownDay(String day) {
	return (boolean) this.shutdown_Day.get(day);
    }

    /**
     * Method indicate the shutdown time a specify day which the parameter is
     * given.
     * 
     * @param day
     * <br>
     *            is being set form Mon - Sun as String.
     * @return A time display as HH:MM:SS
     */
    public String getShutdownTime(String day) {
	return (String) this.shutdown_Time.get(day);
    }

    /**
     * This Method is set the existing day to affect with task.
     * 
     * @param day
     * <br>
     *            is being set form Mon - Sun as String.
     * @param b
     * <br>
     *            b is a boolean, which is indicating the shutdown is active.
     * @throws DayNotExistException
     * <br>
     *             when Day is exist in the Map, exception will return.
     */
    public void setShutdownDay(String day, boolean b)
	    throws DayNotExistException {

	if (this.shutdown_Day.containsKey(day)) {
	    this.shutdown_Day.put(day, b);
	    if (!b) {
		try {
		    setShutdownTime(day, "00:00:00");
		} catch (TimeFormatNotMatchException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		} catch (TimeRangeException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
	    }
	} else {
	    throw new DayNotExistException();
	}
    }

    /**
     * Set a Shutdown time for existing day
     * 
     * @param day
     * <br>
     *            is being set form Mon - Sun as String.
     * @param b
     * <br>
     *            b is a String of Time which is form as HH:MM:SS
     * @throws DayNotExistException
     * <br>
     *             when Day is exist in the Map, DayNotExistException will
     *             return.
     * @throws TimeFormatNotMatchException
     *             when b's format is not match as HH:MM:SS,
     *             TimeFormatNotMatchException will return.
     * @throws TimeRangeException
     *             when time is exceeded the normal range, TimeRangeException is
     *             thrown
     */
    public void setShutdownTime(String day, String b)
	    throws DayNotExistException, TimeFormatNotMatchException,
	    TimeRangeException {

	if (this.shutdown_Time.containsKey(day)) {
	    if (b.matches("\\d\\d:\\d\\d:\\d\\d")) {
		String[] times = b.split(":");
		if ((Integer.parseInt(times[0]) >= 0 && Integer.parseInt(times[0]) <= 23)
			&& (Integer.parseInt(times[1]) >= 0 && Integer.parseInt(times[1]) <= 59)
			&& (Integer.parseInt(times[2]) >= 0 && Integer.parseInt(times[2]) <= 59)) {
		    this.shutdown_Time.put(day, b);
		} else {
		    throw new TimeRangeException();
		}

	    } else {
		throw new TimeFormatNotMatchException();
	    }
	} else {
	    throw new DayNotExistException();
	}
    }

    /**
     * Get a set that contain a shutdown list for time.
     * 
     * @return A HashMap<String,String>, day is using for that key, content is
     *         showing as String.
     */
    public Map getAllShutdownTime() {
	return shutdown_Time;
    }

    /**
     * Set a whole set of shutdown time.
     * 
     * @return A HashMap<String,String>, day is using for that key, content is
     *         showing as String as HH:MM:SS.
     */
    public void setAllShutdownTimes(Map shutdown_Time) {
	
	this.shutdown_Time = shutdown_Time;
    }

    /**
     * Get a set that contain a shutdown list for day.
     * 
     * @return A HashMap<String,Boolean>, day is using for that key, content is
     *         showing as boolean.
     */
    public Map getAllShutdown_Days() {
	return shutdown_Day;
    }

    /**
     * Set a whole set of shutdown list.
     * 
     * @return A HashMap<String,Boolean>, day is using for that key, content is
     *         showing as boolean.
     */
    public void setAllShutdown_Days(Map shutdown_Date) {
	this.shutdown_Day = shutdown_Date;
    }

}
