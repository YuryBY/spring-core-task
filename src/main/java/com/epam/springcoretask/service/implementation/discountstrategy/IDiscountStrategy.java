package com.epam.springcoretask.service.implementation.discountstrategy;

import com.epam.springcoretask.domain.Event;
import com.epam.springcoretask.domain.User;

import java.time.LocalDateTime;

/**
 * Created by Yury_Bakhmutski on 5/23/2016.
 */
public interface IDiscountStrategy {
  byte default_discount = 0;
  byte getDiscount( User user, Event event, LocalDateTime airDateTime, long numberOfTickets );
}
