package com.epam.springcoretask.service.implementation;

import com.epam.springcoretask.dao.TicketDao;
import com.epam.springcoretask.domain.Auditorium;
import com.epam.springcoretask.domain.Event;
import com.epam.springcoretask.domain.Ticket;
import com.epam.springcoretask.domain.User;
import com.epam.springcoretask.domain.util.EventRating;
import com.epam.springcoretask.exception.AlreadyBookedException;
import com.epam.springcoretask.service.BookingService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

/**
 * Created by Yury_Bakhmutski on 5/16/2016.
 */
public class BookingServiceImpl implements BookingService {
  private TicketDao ticketDao;
  private final double vipSeatsCoeff = 2;
  private final double highRatingCoeff = 1.2;
  private DiscountServiceImpl discount;
  private LocalDate key;

  public BookingServiceImpl( DiscountServiceImpl discount ) {
    this.discount = discount;
  }

  @Override
  public double getTicketsPrice( Event event, LocalDateTime dateTime, User user, Set<Integer> seats ) {
    double basePrice = event.getBasePrice();
    int totalSeatsQuantity = seats.size();
    double ratingCoeff = event.getEventRating().equals( EventRating.HIGH ) ? highRatingCoeff : 1;
    byte discount = this.discount.getDiscount( user, event, dateTime, totalSeatsQuantity );

    Auditorium auditorium = null;

    for ( Map.Entry<LocalDate, Auditorium> entry : event.getDatePlace().entrySet() ) {
      key = entry.getKey();
      LocalDate date = key;
      if ( date.equals( dateTime.toLocalDate() ) ) {
        auditorium = entry.getValue();
      }
    }

    int vipSeatsQuantity = 0;
    Set<Long> vipSeats = auditorium.getVipSeats();

    for ( Integer seat : seats ) {
      if ( vipSeats.contains( seat ) ) {
        vipSeatsQuantity++;
      }
    }
    int regularSeatsQuantity = totalSeatsQuantity - vipSeatsQuantity;
    double totalPriceWithoutDiscounts =
        basePrice * ratingCoeff * ( regularSeatsQuantity + vipSeatsQuantity * vipSeatsCoeff );
    double resultPrice = totalPriceWithoutDiscounts - totalPriceWithoutDiscounts * discount / 100;

    return resultPrice;
  }

  @Override
  public void bookTickets( Set<Ticket> tickets ) {
    ticketDao.save(tickets);
  }

  @Override
  public Set<Ticket> getPurchasedTicketsForEvent( Event event, LocalDateTime dateTime ) {
    return ticketDao.getTickets( event );
  }

  public void setTicketDao( TicketDao ticketDao ) {
    this.ticketDao = ticketDao;
  }
}
