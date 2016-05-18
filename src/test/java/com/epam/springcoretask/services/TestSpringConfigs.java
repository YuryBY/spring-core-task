package com.epam.springcoretask.services;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.epam.springcoretask.domain.Event;
import com.epam.springcoretask.domain.Ticket;
import com.epam.springcoretask.domain.User;
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


/**
 * Created by Yury_Bakhmutski on 5/17/2016.
 */
@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( "file:src/main/resources/TestScenario-context.xml" )
public class TestSpringConfigs {

  @Autowired AuditoriumServiceImpl auditoriumServiceImpl;

  @Autowired BookingServiceImpl bookingServiceImpl;

  @Autowired EventServiceImpl eventServiceImpl;

  @Autowired UserServiceImpl userServiceImpl;

  Event firstEvent;

  @Before
  public void initEvent() {
    firstEvent = new Event();
    firstEvent.setBasePrice( 0.7 );
    firstEvent.setName( "Movie_1" );
    firstEvent.setRating( EventRating.HIGH );
    firstEvent.setId( 1L );
    LocalDateTime now = LocalDateTime.now();
    firstEvent.addAirDateTime( now );
    firstEvent.addAirDateTime( now.plusDays( 1 ) );
    firstEvent.addAirDateTime( now.plusDays( 2 ) );

    firstEvent.assignAuditorium( now, auditoriumServiceImpl.getByName( "firstName" ) );
    firstEvent.assignAuditorium( now.plusDays( 1 ), auditoriumServiceImpl.getByName( "secondName" ) );
    firstEvent.assignAuditorium( now.plusDays( 2 ), auditoriumServiceImpl.getByName( "thirdName" ) );

    eventServiceImpl.save( firstEvent );

    Event secondEvent = new Event();
    secondEvent.setBasePrice( 1.3 );
    secondEvent.setName( "Movie number 2" );
    secondEvent.setRating( EventRating.MID );
    secondEvent.addAirDateTime( now.plusDays( 3 ) );
    secondEvent.addAirDateTime( now.plusDays( 5 ) );

    secondEvent.assignAuditorium( now.plusDays( 3 ), auditoriumServiceImpl.getByName( "firstName" ) );
    secondEvent.assignAuditorium( now.plusDays( 5 ), auditoriumServiceImpl.getByName( "secondName" ) );

    eventServiceImpl.save( secondEvent );
  }

  @Test
  public void testScenario() {
    assertEquals( 3, auditoriumServiceImpl.getAll().size() ); // we got 2 auditoriums
    assertEquals( 2, eventServiceImpl.getAll().size() ); // 2 registered events
    assertEquals( 2, eventServiceImpl.getByName( "Movie number 2" ).getAuditoriums()
        .size() ); // second event got 2 assigned auditoriums

    User user = new User();
    user.setFirstName( "John" );
    user.setLastName( "Doe" );
    user.setEmail( "John_Doe@gmail.com" );
    Set<Long> seats = new HashSet<Long>();
    seats.add( 2L ); // regular seat(second auditorium)
    seats.add( 15L ); // vip seat(second auditorium)

    // one regular seat, one vip seat, event coef - HIGH, discount - 10%
    // ((1.1 + 1.1*2)*1.2)*0.9 = 3.564
    assertEquals( 3.564, bookingServiceImpl
        .getTicketsPrice( eventServiceImpl.getByName( "Movie number 1" ), LocalDateTime.now().plusDays( 1 ), user,
            seats ), 0.1 );

    userServiceImpl.save( user );
    assertEquals( "Doe", userServiceImpl.getUserByEmail( "John_Doe@gmail.com" ).getLastName() );

    Ticket ticket = new Ticket( user, firstEvent, LocalDateTime.now(), 6 );
    Set<Ticket> setTickets = new HashSet<Ticket>();
    setTickets.add( ticket );
    bookingServiceImpl.addEvent( firstEvent, LocalDateTime.now() );
    bookingServiceImpl.bookTickets( setTickets ); // book 1 ticket for the first event

    assertEquals( 1, bookingServiceImpl.getPurchasedTicketsForEvent( firstEvent, LocalDateTime.now() ).size() );
  }
}
