package com.epam.springcoretask.service.implementation;

import com.epam.springcoretask.domain.Event;
import com.epam.springcoretask.domain.User;
import com.epam.springcoretask.service.DiscountService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

/**
 * Created by Yury_Bakhmutski on 5/16/2016.
 */
public class DiscountServiceImpl implements DiscountService {

  @Override
  public byte getDiscount( User user, Event event, LocalDateTime airDateTime, long numberOfTickets ) {
    Month eventMonth = airDateTime.toLocalDate().getMonth();
    int eventDay = airDateTime.toLocalDate().getDayOfMonth();
    LocalDate birthday = user.getBirthday();
    if ( birthday.getMonth().equals( eventMonth ) && birthday.getDayOfMonth() == eventDay ) {
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

