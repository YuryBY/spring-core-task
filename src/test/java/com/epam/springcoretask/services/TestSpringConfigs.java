package com.epam.springcoretask.services;

import com.epam.springcoretask.domain.Auditorium;
import com.epam.springcoretask.domain.Event;
import com.epam.springcoretask.domain.User;
import com.epam.springcoretask.domain.util.EventRating;
import com.epam.springcoretask.service.implementation.AuditoriumServiceImpl;
import com.epam.springcoretask.service.implementation.BookingServiceImpl;
import com.epam.springcoretask.service.implementation.EventServiceImpl;
import com.epam.springcoretask.service.implementation.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

/**
 * Created by Yury_Bakhmutski on 5/17/2016.
 */
@RunWith( SpringJUnit4ClassRunner.class ) @ContextConfiguration( "file:src/main/resources/spring-context.xml" )
public class TestSpringConfigs {
  @Autowired AuditoriumServiceImpl auditoriumServiceImpl;

  @Autowired BookingServiceImpl bookingServiceImpl;

  @Autowired EventServiceImpl eventServiceImpl;

  @Autowired UserServiceImpl userServiceImpl;

  @Before
  public void initEvent() throws Exception {
    Event kungFuPanda;
    Event zootopia;

    Auditorium bigHall = auditoriumServiceImpl.getByName( "Big Hall" );
    Auditorium smallHall = auditoriumServiceImpl.getByName( "Small Hall" );

    LocalDate firstOfMay = LocalDate.of( 2016, Month.MAY, 1 );
    LocalDate secondOfMay = firstOfMay.plusDays( 1 );
    LocalDate thirdOfMay = firstOfMay.plusDays( 2 );

    kungFuPanda = new Event();
    kungFuPanda.setBasePrice( 2.1 );
    kungFuPanda.setName( "Kung Fu Panda" );
    kungFuPanda.setRating( EventRating.STANDARD );
    kungFuPanda.setId( 1L );

    kungFuPanda.addDatePlace( firstOfMay, smallHall );
    kungFuPanda.addDatePlace( secondOfMay, bigHall );
    kungFuPanda.addDatePlace( thirdOfMay, bigHall );

    eventServiceImpl.save( kungFuPanda );

    zootopia = new Event();
    zootopia.setBasePrice( 2.1 );
    zootopia.setName( "Zootopia" );
    zootopia.setRating( EventRating.HIGH );

    zootopia.addDatePlace( firstOfMay.plusDays( 3 ), smallHall );
    zootopia.addDatePlace( firstOfMay.plusDays( 4 ), bigHall );
    zootopia.addDatePlace( firstOfMay.plusDays( 5 ), bigHall );
    zootopia.addDatePlace( firstOfMay.plusDays( 6 ), bigHall );

    eventServiceImpl.save( zootopia );
  }

  @Test
  public void testSpringInit() {
    assertEquals( 3, auditoriumServiceImpl.getAll().size() );
    assertEquals( 2, eventServiceImpl.getAll().size() );
    Event zootopia = eventServiceImpl.getByName( "Zootopia" );
    Set<LocalDate> dates = zootopia.getDates();
    assertEquals( 4, dates.size() );

    User user = new User();
    user.setFirstName( "John" );
    user.setLastName( "Konor" );
    user.setEmail( "John_Konor@gmail.com" );
    LocalDate thirdOfMay = LocalDate.of( 1985, Month.MAY, 3 );
    user.setBirthday( thirdOfMay );
    Set<Long> seats = new HashSet<Long>();
    seats.add( 2L );
    seats.add( 15L );

    LocalDateTime eventDate = LocalDateTime.of( 2016, Month.MAY, 5, 10, 10, 30 );

    assertEquals( 5.04,
        bookingServiceImpl.getTicketsPrice( zootopia, eventDate, user, seats ),
        0.1 );

    userServiceImpl.save( user );
    assertEquals( "Konor", userServiceImpl.getUserByEmail( "John_Konor@gmail.com" ).getLastName() );

    Auditorium vipHall = auditoriumServiceImpl.getByName( "Vip Hall" );
    HashMap<Auditorium, Integer> auditoriumSeat = new HashMap<Auditorium, Integer>() {{
      put( vipHall, 7 );
    }};

  }
}
