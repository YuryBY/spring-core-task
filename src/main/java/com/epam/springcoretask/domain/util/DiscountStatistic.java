package com.epam.springcoretask.domain.util;

import com.epam.springcoretask.domain.User;
import com.epam.springcoretask.service.implementation.discountstrategy.String;

/**
 * Created by Yury_Bakhmutski on 5/26/2016.
 */
public class DiscountStatistic {
  String discountStrategyName;
  Integer times;
  User user;

  public void setDiscountStrategyName( String discountStrategyName ) {
    this.discountStrategyName = discountStrategyName;
  }

  public void setTimes( Integer times ) {
    this.times = times;
  }

  public void setUser( User user ) {
    this.user = user;
  }

  public String getDiscountStrategyName() {
    return discountStrategyName;
  }

  public Integer getTimes() {
    return times;
  }

  public User getUser() {
    return user;
  }
}
