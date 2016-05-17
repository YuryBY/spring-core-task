package com.epam.springcoretask;

import java.time.LocalDateTime;

/**
 * Created by Yury_Bakhmutski on 5/17/2016.
 */
public class Main {

  public static void main( String[] args ) {

    LocalDateTime localDateTime = LocalDateTime.now();

    System.out.println( localDateTime.getDayOfMonth() );
    System.out.println( localDateTime.getDayOfWeek() );
    System.out.println( localDateTime.getDayOfYear() );

    System.out.println( "localDateTime.toLocalDate(): "+localDateTime.toLocalDate() );

    //System.out.println( localDateTime.toLocalTime(). );

    System.out.println( localDateTime.getDayOfWeek() );
  }
}
