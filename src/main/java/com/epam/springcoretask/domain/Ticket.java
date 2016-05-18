package com.epam.springcoretask.domain;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Ticket extends DomainObject implements Comparable<Ticket> {

  private User user;
  private Event event;
  private LocalDateTime dateTime;
  private Map<Auditorium, Integer> auditoriumSeat = new HashMap<>();

  public Ticket( User user, Event event, LocalDateTime dateTime, Map<Auditorium, Integer> auditoriumSeat ) {
    this.user = user;
    this.event = event;
    this.dateTime = dateTime;
    this.auditoriumSeat = auditoriumSeat;
  }

  public void setUser( User user ) {
    this.user = user;
  }

  public void setEvent( Event event ) {
    this.event = event;
  }

  public void setDateTime( LocalDateTime dateTime ) {
    this.dateTime = dateTime;
  }

  public void setAuditoriumSeat( Map<Auditorium, Integer> auditoriumSeat ) {
    this.auditoriumSeat = auditoriumSeat;
  }

  public User getUser() {
    return user;
  }

  public Event getEvent() {
    return event;
  }

  public LocalDateTime getDateTime() {
    return dateTime;
  }

  public Map<Auditorium, Integer> getAuditoriumSeat() {
    return auditoriumSeat;
  }

  @Override
  public boolean equals( Object o ) {
    if ( this == o )
      return true;
    if ( o == null || getClass() != o.getClass() )
      return false;

    Ticket ticket = (Ticket) o;

    if ( user != null ? !user.equals( ticket.user ) : ticket.user != null )
      return false;
    if ( event != null ? !event.equals( ticket.event ) : ticket.event != null )
      return false;
    if ( dateTime != null ? !dateTime.equals( ticket.dateTime ) : ticket.dateTime != null )
      return false;
    return !( auditoriumSeat != null ? !auditoriumSeat.equals( ticket.auditoriumSeat ) :
        ticket.auditoriumSeat != null );

  }

  @Override
  public int hashCode() {
    int result = user != null ? user.hashCode() : 0;
    result = 31 * result + ( event != null ? event.hashCode() : 0 );
    result = 31 * result + ( dateTime != null ? dateTime.hashCode() : 0 );
    result = 31 * result + ( auditoriumSeat != null ? auditoriumSeat.hashCode() : 0 );
    return result;
  }

  @Override
  public int compareTo( Ticket other ) {
    if ( other == null ) {
      return 1;
    }
    int result = dateTime.compareTo( other.getDateTime() );

    if ( result == 0 ) {
      result = event.getName().compareTo( other.getEvent().getName() );
    }
    if ( result == 0 ) {
      for ( final Auditorium key : auditoriumSeat.keySet() ) {
        if ( other.getAuditoriumSeat().containsKey( key ) ) {
          result = auditoriumSeat.get( key ).compareTo( other.getAuditoriumSeat().get( key ) );
        }
      }
    }
    return result;
  }

}
