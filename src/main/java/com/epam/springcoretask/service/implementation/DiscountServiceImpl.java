package com.epam.springcoretask.service.implementation;

import com.epam.springcoretask.domain.Event;
import com.epam.springcoretask.domain.User;
import com.epam.springcoretask.service.DiscountService;

import java.time.LocalDateTime;

/**
 * Created by Yury_Bakhmutski on 5/16/2016.
 */
public class DiscountServiceImpl implements DiscountService {

  @Override
  public byte getDiscount( User user, Event event, LocalDateTime airDateTime, long numberOfTickets ) {
    if ( user.getBirthday().toLocalDate().equals( airDateTime.toLocalDate() ) ) {
      return new BirthdayStrategy().getDiscount();
    }
    if ( numberOfTickets >= 10 ) {
      return new TicketsQuantityStrategy().getDiscount();
    }
    return 0;
  }
}

interface IDiscountStrategy {
  byte getDiscount();
}

class BirthdayStrategy implements IDiscountStrategy {
  @Override
  public byte getDiscount() {
    return 5;
  }
}

class TicketsQuantityStrategy implements IDiscountStrategy {
  @Override
  public byte getDiscount() {
    return 50;
  }
}

/*  enum DiscountStrategy {
    BIRTHDAY( 0.05 ), TEN_TICKETS( 0.5 ), NONE( 1 );
    private final double coefficient;

    DiscountStrategy( double coefficient ) {
      this.coefficient = coefficient;
    }

  }

  public static void main( String[] args ) {
    System.out.println(DiscountStrategy.BIRTHDAY);
  }*/

