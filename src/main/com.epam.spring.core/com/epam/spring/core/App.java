package com.epam.spring.core;

import com.epam.spring.core.beans.Client;
import com.epam.spring.core.loggers.ConsoleEventLogger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Yury_Bakhmutski on 5/5/2016.
 */
public class App {

  Client client;
  ConsoleEventLogger eventLogger;

  public App( Client client, ConsoleEventLogger eventLogger ) {
    this.client = client;
    this.eventLogger = eventLogger;
  }

  public static void main( String[] args ) {

    ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
    App app = (App) ctx.getBean( "app" );
    app.logEvent( "Some event for 1" );
    app.logEvent( "Some event for 2" );

    //com.epam.spring.core.App app = new com.epam.spring.core.App();
    //app.client = new Client("1", "John Smith");
    //app.eventLogger = new ConsoleEventLogger();
    //app.logEvent( "Some Event For User 1" );
  }

  private void logEvent( String msg ) {
    String message = msg.replaceAll( client.getId(), client.getFullName() );
    eventLogger.logEvent( message );
  }

}
