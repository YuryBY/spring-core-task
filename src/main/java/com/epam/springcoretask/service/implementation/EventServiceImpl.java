package com.epam.springcoretask.service.implementation;

import com.epam.springcoretask.dao.EventDao;
import com.epam.springcoretask.domain.Event;
import com.epam.springcoretask.service.EventService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * Created by Yury_Bakhmutski on 5/16/2016.
 */
@Service
public class EventServiceImpl implements EventService {

  @Resource
  private EventDao eventDao;

  @Override
  public Event save( Event event ) {
    return eventDao.saveEvent( event );
  }

  @Override
  public void remove( Event event ) {
    eventDao.removeEvent( event );

  }

  @Override
  public Event getById( Long id ) {
    return eventDao.getEventById( id );
  }

  @Override
  public Collection<Event> getAll() {
    Map<Long, Event> events = eventDao.getEvents();
    return new ArrayList( events.values() );
  }

  @Override
  public Event getByName( String name ) {
    Map<Long, Event> events = eventDao.getEvents();
    for ( Map.Entry<Long, Event> entry : events.entrySet() ) {
      Event currentEvent = entry.getValue();
      if ( currentEvent.getName().equals( name ) ) {
        return currentEvent;
      }
    }
    return null;
  }

  public void setEventDao( EventDao eventDao ) {
    this.eventDao = eventDao;
  }
}
