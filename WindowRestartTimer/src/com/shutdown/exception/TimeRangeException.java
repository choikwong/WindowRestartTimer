package com.shutdown.exception;
/**
 * throw when the time is exceed the normal range.
 * 
 * @author Raymond
 *
 */
public class TimeRangeException extends Exception {
    /**
     * Default Constructor for TimeRangeException.
     */
    public TimeRangeException() {
        this("Time is not in the range!");
    }
    /**
     * Constructor for TimeRangeException with Exception Message Define.
     * @param message <br> A String of Message 
     */
    public TimeRangeException(String message) {
        super(message);
    }

}
