package com.epam.springcoretask.service.implementation;

import com.epam.springcoretask.domain.Event;
import com.epam.springcoretask.domain.User;
import com.epam.springcoretask.service.DiscountService;
import com.epam.springcoretask.service.implementation.discountstrategy.IDiscountStrategy;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yury_Bakhmutski on 5/16/2016.
 */
public class DiscountServiceImpl implements DiscountService {
  private List<IDiscountStrategy> strategies = new ArrayList<IDiscountStrategy>( 2 );

  @Override
  public byte getDiscount( User user, Event event, LocalDateTime airDateTime, long numberOfTickets ) {
    byte result = 0;
    for ( IDiscountStrategy strategy : strategies ) {
      byte disc = strategy.getDiscount( user, event, airDateTime, numberOfTickets );
      result = ( disc > result ) ? disc : result;
    }
    return result;
  }

  public void addStrategy( IDiscountStrategy strategy ) {
    strategies.add( strategy );
  }

  public void setStrategies( List<IDiscountStrategy> strategies ) {
    this.strategies = strategies;
  }
}
