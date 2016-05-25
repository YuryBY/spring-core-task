package com.epam.springcoretask.dao;

import com.epam.springcoretask.domain.Event;
import com.epam.springcoretask.domain.util.EventStatistic;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yury_Bakhmutski on 5/25/2016.
 */
public class EventStatisticDao {
  Map<Event, EventStatistic> statisticMap = new HashMap<>();

  public EventStatistic loadStatistic( Event event ) {
    return statisticMap.get( event );
  }

  public void saveStatistic( Event event, EventStatistic eventStatistic ) {
    statisticMap.put( event, eventStatistic );
  }

}
