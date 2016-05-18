package com.epam.springcoretask.exception;

/**
 * Created by Yury_Bakhmutski on 5/18/2016.
 */
public class EventInitializationException extends Exception {
  public EventInitializationException() {
  }

  public EventInitializationException( String message ) {
    super( message );
  }

  public EventInitializationException( String message, Throwable cause ) {
    super( message, cause );
  }

  public EventInitializationException( Throwable cause ) {
    super( cause );
  }

  public EventInitializationException( String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace ) {
    super( message, cause, enableSuppression, writableStackTrace );
  }
}
