package com.epam.springcoretask.service.implementation;

import com.epam.springcoretask.domain.Auditorium;
import com.epam.springcoretask.domain.Event;
import com.epam.springcoretask.domain.EventRating;
import com.epam.springcoretask.domain.Ticket;
import com.epam.springcoretask.domain.User;
import com.epam.springcoretask.service.BookingService;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Set;

/**
 * Created by Yury_Bakhmutski on 5/16/2016.
 */
public class BookingServiceImpl implements BookingService {
  public final int vipSeatsCoeff = 2;
  public final double highRatedCoeff = 1.2;
  public Map<Map<LocalDateTime,Event>, Set<Ticket>> purchasedTickets;
  DiscountServiceImpl discount;

  public BookingServiceImpl( DiscountServiceImpl discount) {
    purchasedTickets = new HashMap<Map<LocalDateTime,Event>, Set<Ticket>>();
    this.discount = discount;
  }

  @Override
  public double getTicketsPrice(Event event, LocalDateTime dateTime, User user, Set<Long> seats) {
    double basePrice = event.getBasePrice();
    double totalPrice = 0;
    int seatsQuantity = seats.size();

    NavigableSet<LocalDateTime> airDates = event.getAirDates();

    for ( LocalDateTime localDateTime : airDates ) {
      LocalDate localDate = localDateTime.toLocalDate();

      NavigableSet<Ticket> tickets = user.getTickets();
      for ( Ticket ticket : tickets ) {
        //ticket.get

      }

    }
  }

  @Override
  public void bookTickets(Set<Ticket> tickets) {
    for (Ticket ticket : tickets) {
      Set<Ticket> setTickets = getPurchasedTicketsForEvent(ticket.getEvent(), ticket.getDateTime());
      setTickets.add(ticket);
    }
  }

  @Override
  public Set<Ticket> getPurchasedTicketsForEvent(Event event, LocalDateTime dateTime) {
    Set<Map<LocalDateTime, Event>> keySet = purchasedTickets.keySet();
    for (Map<LocalDateTime, Event> map : keySet) {
      Set<LocalDateTime> dateTimeSet = map.keySet();
      for (LocalDateTime localDateTime : dateTimeSet) {
        if(localDateTime.getDayOfYear() == dateTime.getDayOfYear() && ((Event) map.get(localDateTime)).getId() == event.getId() ) {
          return purchasedTickets.get(map);
        }
      }
    }
    return null;
  }

  public void addEvent(Event event, LocalDateTime dateTime) {
    Map<LocalDateTime,Event> map = new HashMap<LocalDateTime,Event>();
    map.put(dateTime, event);
    Set<Ticket> tickets = new HashSet<Ticket>();
    purchasedTickets.put(map, tickets);
  }
}
