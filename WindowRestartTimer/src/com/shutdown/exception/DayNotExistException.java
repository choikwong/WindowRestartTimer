package com.shutdown.exception;
/**
 * throw when Day is not Exist in the Map.
 * 
 * @author Raymond
 *
 */
public class DayNotExistException extends Exception {
    /**
     * Default Constructor for DayNotExistException.
     */
    public DayNotExistException() {
        this("Day not find in the Map!");
    }
    /**
     * Constructor for DayNotExistException with Exception Message Define.
     * @param message <br> A String of Message 
     */
    public DayNotExistException(String message) {
        super(message);
    }

}
