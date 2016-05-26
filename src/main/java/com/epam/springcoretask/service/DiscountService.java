package com.epam.springcoretask.service;

import com.epam.springcoretask.domain.Event;
import com.epam.springcoretask.domain.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author Yuriy_Tkach
 */
public interface DiscountService {

  /**
   * Getting discount based on some rules for user that buys some number of
   * tickets for the specific date time of the event
   *
   * @param user            User that buys tickets. Can be <code>null</code>
   * @param event           Event that tickets are bought for
   * @param airDateTime     The date and time event will be aired
   * @param numberOfTickets Number of tickets that user buys
   * @return discount value from 0 to 100
   */
  byte getDiscount( User user, Event event, LocalDateTime airDateTime, long numberOfTickets );

}
