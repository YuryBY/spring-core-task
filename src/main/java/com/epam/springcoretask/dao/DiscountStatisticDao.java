package com.epam.springcoretask.dao;

import com.epam.springcoretask.domain.User;
import com.epam.springcoretask.domain.util.DiscountStatistic;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yury_Bakhmutski on 5/25/2016.
 */
@Repository public class DiscountStatisticDao {
  Map<DiscountStatistic, Integer> statisticMap = new HashMap<>();

  public int loadStatistic( User user ) {
    for ( DiscountStatistic discountStatistic : statisticMap.keySet() )
      if ( discountStatistic.getUser().equals( user ) ) {
        return discountStatistic.getTimes();
      }
    return 0;
  }

  public int loadTotalStatistic() {
    int result = 0;
    for ( DiscountStatistic discountStatistic : statisticMap.keySet() ) {
      result += discountStatistic.getTimes();
    }
    return result;
  }

}
