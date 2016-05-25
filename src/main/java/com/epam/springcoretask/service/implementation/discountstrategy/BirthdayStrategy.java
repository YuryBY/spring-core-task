package com.epam.springcoretask.service.implementation.discountstrategy;

import com.epam.springcoretask.domain.Event;
import com.epam.springcoretask.domain.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

/**
 * Created by Yury_Bakhmutski on 5/23/2016.
 */
@Component
public class BirthdayStrategy implements IDiscountStrategy {
  byte discount = 5;

  @Override
  public byte getDiscount( User user, Event event, LocalDateTime airDateTime, long numberOfTickets ) {
    Month eventMonth = airDateTime.toLocalDate().getMonth();
    int eventDay = airDateTime.toLocalDate().getDayOfMonth();
    LocalDate birthday = user.getBirthday();
    if ( birthday.getMonth().equals( eventMonth ) && birthday.getDayOfMonth() == eventDay ) {
      return discount;
    } else {
      return default_discount;
    }
  }
}
