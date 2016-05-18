package com.epam.springcoretask.exception;

import com.epam.springcoretask.domain.Ticket;

/**
 * Created by Yury_Bakhmutski on 5/18/2016.
 */
public class AlreadyBookedException extends Exception{

  Ticket ticket;

  public AlreadyBookedException( Ticket ticket ) {
    this.ticket = ticket;
  }

  public AlreadyBookedException( String message, Ticket ticket ) {
    super( message );
    this.ticket = ticket;
  }

  public AlreadyBookedException( String message, Throwable cause, Ticket ticket ) {
    super( message, cause );
    this.ticket = ticket;
  }

  public AlreadyBookedException( Throwable cause, Ticket ticket ) {
    super( cause );
    this.ticket = ticket;
  }

  public AlreadyBookedException( String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace,
      Ticket ticket ) {
    super( message, cause, enableSuppression, writableStackTrace );
    this.ticket = ticket;
  }
}
