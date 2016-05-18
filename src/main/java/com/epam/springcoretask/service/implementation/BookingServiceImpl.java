package com.epam.springcoretask.service.implementation;

import com.epam.springcoretask.domain.Auditorium;
import com.epam.springcoretask.domain.Event;
import com.epam.springcoretask.domain.Ticket;
import com.epam.springcoretask.domain.User;
import com.epam.springcoretask.domain.util.EventRating;
import com.epam.springcoretask.exception.AlreadyBookedException;
import com.epam.springcoretask.service.BookingService;

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
  private final double vipSeatsCoeff = 2;
  private final double highRatingCoeff = 1.2;
  private DiscountServiceImpl discount;

  public BookingServiceImpl( DiscountServiceImpl discount) {
    this.discount = discount;
  }

  @Override
  public double getTicketsPrice( Event event, LocalDateTime dateTime, User user, Set<Long> seats ) {
    double basePrice = event.getBasePrice();
    int totalSeatsQuantity = seats.size();
    double ratingCoeff = event.getEventRating().equals( EventRating.HIGH ) ? highRatingCoeff : 1;
    byte discount = this.discount.getDiscount( user, event, dateTime, totalSeatsQuantity );

    Auditorium auditorium = null;

    for ( Map.Entry<LocalDate, Auditorium> entry : event.getDatePlace().entrySet() ) {
      LocalDate date = entry.getKey();
      if ( date.equals( dateTime.toLocalDate() ) ) {
        auditorium = entry.getValue();
      }
    }

    int vipSeatsQuantity = 0;
    Set<Long> vipSeats = auditorium.getVipSeats();

    for ( Long seat : seats ) {
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
    throw new UnsupportedOperationException();
  }

  public void bookTickets(Event event, LocalDateTime dateTime, Set<Ticket> tickets) throws AlreadyBookedException {
    Auditorium auditorium = event.getAuditoriumByDate( dateTime.toLocalDate() );
    Set<Ticket> purchasedTicketsForAuditorium = event.getPurchasedTicketsForAuditorium( auditorium );
    for ( Ticket ticket : tickets ){
      if (!purchasedTicketsForAuditorium.add( ticket ))
        throw new AlreadyBookedException( "Alredy booked: ", ticket );
    }
  }

  @Override
  public Set<Ticket> getPurchasedTicketsForEvent( Event event, LocalDateTime dateTime ) {
    Auditorium auditoriumByDate = event.getAuditoriumByDate( dateTime.toLocalDate() );
    return event.getPurchasedTicketsForAuditorium( auditoriumByDate );
  }

}
