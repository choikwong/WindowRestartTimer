package com.shutdown.exception;
/**
 * throw when the times format is not match.
 * 
 * @author Raymond
 *
 */
public class TimeFormatNotMatchException extends Exception {
    /**
     * Default Constructor for TimeFormatNotMatchException.
     */
    public TimeFormatNotMatchException() {
        this("Time Format Not right!");
    }
    /**
     * Constructor for TimeFormatNotMatchException with Exception Message Define.
     * @param message <br> A String of Message 
     */
    public TimeFormatNotMatchException(String message) {
        super(message);
    }
}
