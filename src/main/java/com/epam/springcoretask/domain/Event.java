package com.epam.springcoretask.domain;

import com.epam.springcoretask.domain.util.EventRating;
import com.epam.springcoretask.exception.EventInitializationException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Event extends DomainObject {

  private String name;
  private double basePrice;
  private EventRating eventRating;
  //one event per one day
  private NavigableMap<LocalDate, Auditorium> datePlace = new TreeMap<>();

  private NavigableMap<Auditorium, Set<Ticket>> placePurchasedTickets = new TreeMap<>();

  public Auditorium getAuditoriumByDate( LocalDate date ) {
    return datePlace.get( date );
  }

  public Set<Ticket> getPurchasedTicketsForAuditorium( Auditorium auditorium ) {
    Set<Ticket> tickets = placePurchasedTickets.get( auditorium );
    return tickets;
  }

  public Set<Ticket> addTicketsForAuditorium( Auditorium auditorium, Set<Ticket> tickets ) {
    return placePurchasedTickets.get( auditorium );
  }

  public String getName() {
    return name;
  }

  public Set<LocalDate> getDates() {
    return datePlace.keySet();
  }

  public double getBasePrice() {
    return basePrice;
  }

  public NavigableMap<LocalDate, Auditorium> getDatePlace() {
    return datePlace;
  }

  public void setName( String name ) {
    this.name = name;
  }

  public void setBasePrice( double basePrice ) {
    this.basePrice = basePrice;
  }

  public void addDatePlace( LocalDate date, Auditorium auditorium ) throws EventInitializationException {
    if ( !datePlace.containsKey( date ) ) {
      datePlace.put( date, auditorium );
    } else {
      throw new EventInitializationException( "Auditorium has been specified" );
    }
  }

  @Override
  public boolean equals( Object o ) {
    if ( this == o )
      return true;
    if ( o == null || getClass() != o.getClass() )
      return false;

    Event event = (Event) o;

    if ( Double.compare( event.basePrice, basePrice ) != 0 )
      return false;
    if ( name != null ? !name.equals( event.name ) : event.name != null )
      return false;
    return !( datePlace != null ? !datePlace.equals( event.datePlace ) : event.datePlace != null );

  }

  @Override
  public int hashCode() {
    int result;
    long temp;
    result = name != null ? name.hashCode() : 0;
    temp = Double.doubleToLongBits( basePrice );
    result = 31 * result + (int) ( temp ^ ( temp >>> 32 ) );
    result = 31 * result + ( datePlace != null ? datePlace.hashCode() : 0 );
    return result;
  }

  @Override
  public String toString() {
    return "Event{" +
        "name='" + name + '\'' +
        '}';
  }

  public NavigableMap<Auditorium, Set<Ticket>> getPlacePurchasedTickets() {
    return placePurchasedTickets;
  }

  public void setPlacePurchasedTickets( NavigableMap<Auditorium, Set<Ticket>> placePurchasedTickets ) {
    this.placePurchasedTickets = placePurchasedTickets;
  }

  public EventRating getEventRating() {
    return eventRating;
  }

  public void setRating( EventRating eventRating ) {
    this.eventRating = eventRating;
  }
}
