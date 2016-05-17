package com.epam.springcoretask.service.implementation;

import com.epam.springcoretask.dao.EventDAO;
import com.epam.springcoretask.domain.Event;
import com.epam.springcoretask.service.EventService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * Created by Yury_Bakhmutski on 5/16/2016.
 */
public class EventServiceImpl implements EventService {

  private EventDAO eventDAO;

  @Override
  public Event save( Event event ) {
    return eventDAO.saveEvent( event );
  }

  @Override
  public void remove( Event event ) {
    eventDAO.removeEvent( event );

  }

  @Override
  public Event getById( Long id ) {
    return eventDAO.getEventById( id );
  }

  @Override
  public Collection<Event> getAll() {
    Map<Long, Event> events = eventDAO.getEvents();
    return new ArrayList( events.values() );
  }

  @Override
  public Event getByName( String name ) {
    Map<Long, Event> events = eventDAO.getEvents();
    for ( Map.Entry<Long, Event> entry : events.entrySet() ) {
      Event currentEvent = entry.getValue();
      if ( currentEvent.getName().equals( name ) ) {
        return currentEvent;
      }
    }
    return null;
  }

  public void setEventDAO( EventDAO eventDAO ) {
    this.eventDAO = eventDAO;
  }
}
