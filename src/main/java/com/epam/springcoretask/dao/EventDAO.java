package com.epam.springcoretask.dao;

import com.epam.springcoretask.domain.Event;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yury_Bakhmutski on 5/16/2016.
 */
public class EventDao {
  Map<Long, Event> Events = new HashMap<>();

  public Event getEventById( Long id ) {
    return Events.get( id );
  }

  public Event saveEvent( Event Event ) {
    Long id = Event.getId();
    return Events.put( id, Event );
  }

  public void removeEvent( Event Event ) {
    Events.remove( Event );
  }

  public Map<Long, Event> getEvents() {
    return Events;
  }

  public void setEvents( Map<Long, Event> Events ) {
    this.Events = Events;
  }
}
