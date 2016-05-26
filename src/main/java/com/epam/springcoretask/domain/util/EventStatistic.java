package com.epam.springcoretask.domain.util;

/**
 * Created by Yury_Bakhmutski on 5/25/2016.
 */
public class EventStatistic {
  int callByNameTimes;
  int priceQueriedTimes;
  int ticketBookedTimes;

  public void increaseCallByNameTimes() {
    callByNameTimes++;
  }

  public void increasePriceQueriedTimes() {
    priceQueriedTimes++;
  }

  public void increaseTicketBookedTimes() {
    ticketBookedTimes++;
  }

  @Override
  public String toString() {
    return "EventStatistic{" +
        "callByNameTimes=" + callByNameTimes +
        ", priceQueriedTimes=" + priceQueriedTimes +
        ", ticketBookedTimes=" + ticketBookedTimes +
        '}';
  }
}
