package com.epam.springcoretask.service.implementation;

import com.epam.springcoretask.domain.Event;
import com.epam.springcoretask.domain.User;
import com.epam.springcoretask.service.DiscountService;
import com.epam.springcoretask.service.implementation.discountstrategy.BirthdayStrategy;
import com.epam.springcoretask.service.implementation.discountstrategy.String;
import com.epam.springcoretask.service.implementation.discountstrategy.TicketsQuantityStrategy;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Yury_Bakhmutski on 5/16/2016.
 */
@Service
public class DiscountServiceImpl implements DiscountService {

  private List<String> strategies
      = Arrays.asList( new BirthdayStrategy(), new TicketsQuantityStrategy() );

  @Override
  public byte getDiscount( User user, Event event, LocalDateTime airDateTime, long numberOfTickets ) {
    byte result = 0;
    for ( String strategy : strategies ) {
      byte disc = strategy.getDiscount( user, event, airDateTime, numberOfTickets );
      result = ( disc > result ) ? disc : result;
    }
    return result;
  }

  public void addStrategy( String strategy ) {
    strategies.add( strategy );
  }

  public void setStrategies( List<String> strategies ) {
    this.strategies = strategies;
  }
}
