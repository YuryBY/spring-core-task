package com.epam.spring.core.loggers;

/**
 * Created by Yury_Bakhmutski on 5/5/2016.
 */
public class ConsoleEventLogger implements EvebtLogger {

  public void logEvent( String msg ) {
    System.out.println(msg);
  }

}
