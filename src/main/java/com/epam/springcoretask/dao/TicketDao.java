package com.epam.springcoretask.dao;

import com.epam.springcoretask.domain.Event;
import com.epam.springcoretask.domain.Ticket;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Yury_Bakhmutski on 5/23/2016.
 */
@Repository
public class TicketDao {
  Set<Ticket> tickets = new HashSet();

  public void save( Set<Ticket> tickets ) {
    this.tickets.addAll( tickets );
  }

  public Set<Ticket> getTickets( Event event ) {
    Set<Ticket> result = new HashSet<>();
    for ( Ticket ticket : tickets ) {
      if ( ticket.getEvent().equals( event ) ) {
        result.add( ticket );
      }
    }
    return result;
  }

}
