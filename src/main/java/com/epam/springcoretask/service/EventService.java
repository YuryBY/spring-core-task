package com.epam.springcoretask.service;

import com.epam.springcoretask.domain.Event;

/**
 * @author Yuriy_Tkach
 */
public interface EventService extends AbstractDomainObjectService<Event> {

  /**
   * Finding event by name
   *
   * @param name Name of the event
   * @return found event or <code>null</code>
   */
  public Event getByName( String name );

    /*
     * Finding all events that air on specified date range
     * 
     * @param from Start date
     * 
     * @param to End date inclusive
     * 
     * @return Set of events
     */
  // public   Set<Event> getForDateRange(  LocalDate from,
  //   LocalDate to);

    /*
     * Return events from 'now' till the the specified date time
     * 
     * @param to End date time inclusive
     * s
     * @return Set of events
     */
  // public   Set<Event> getNextEvents(  LocalDateTime to);

}
