package com.epam.springcoretask.service.implementation.discountstrategy;

import com.epam.springcoretask.domain.Event;
import com.epam.springcoretask.domain.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Created by Yury_Bakhmutski on 5/23/2016.
 */
@Component
public class TicketsQuantityStrategy implements String {
  byte discount = 50;

  @Override
  public byte getDiscount( User user, Event event, LocalDateTime airDateTime, long numberOfTickets ) {
    if ( numberOfTickets >= 10 ) {
      return discount;
    } else {
      return default_discount;
    }
  }
}
