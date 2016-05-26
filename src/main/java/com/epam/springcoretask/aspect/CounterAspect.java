package com.epam.springcoretask.aspect;

import com.epam.springcoretask.dao.EventStatisticDao;
import com.epam.springcoretask.domain.Event;
import com.epam.springcoretask.domain.util.EventStatistic;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by Yury_Bakhmutski on 5/25/2016.
 */
@Aspect
@Component
public class CounterAspect {

  @Resource
  private EventStatisticDao eventStatisticDao;

  @Pointcut("execution(* *.getByName(..))")
  private void getByNameCallsNumber() {}

  @Pointcut("execution(* *.getTicketsPrice(..))")
  private void getTicketsPriceCallsNumber() {}

  @Pointcut("execution(* *.bookTickets(..))")
  private void bookTicketsCallsNumber() {}

  @AfterReturning( pointcut = "getByNameCallsNumber()", returning = "returnedEvent" )
  public void countEventAccess( Event returnedEvent ) {
    Event event = returnedEvent;
    EventStatistic eventStatistic = eventStatisticDao.loadStatistic( event );
    if ( eventStatistic == null ) {
      eventStatistic = new EventStatistic();
    }
    eventStatistic.increaseCallByNameTimes();
    eventStatisticDao.saveStatistic( event, eventStatistic );
  }

  public void printGetByNameCallsNumber(Event event) {
    EventStatistic eventStatistic = eventStatisticDao.loadStatistic( event );
    System.out.println( "Event(" + event + ") statistic is the following: " + eventStatistic );
  }

  @AfterReturning( pointcut = "getTicketsPriceCallsNumber()", returning = "returnedPrice" )
  public void countGetTicketsPriceCalls(JoinPoint joinPoint, Event returnedPrice) {
   throw new UnsupportedOperationException(  );
  }

  @AfterReturning( pointcut = "bookTicketsCallsNumber()" )
  public void countBookedTickets(JoinPoint joinPoint ) {
    throw new UnsupportedOperationException(  );
  }

}
