package com.epam.springcoretask.aspect;

import com.epam.springcoretask.dao.DiscountStatisticDao;
import com.epam.springcoretask.dao.EventStatisticDao;
import com.epam.springcoretask.domain.util.DiscountStatistic;
import com.epam.springcoretask.service.implementation.discountstrategy.TicketsQuantityStrategy;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by Yury_Bakhmutski on 5/25/2016.
 */
@Aspect
@Component
public class DiscountAspect {

  @Resource
  private DiscountStatisticDao discountStatisticDao;

  @Pointcut("execution(* *.getDiscount(..))")
  private void getByNameCallsNumber() {}
/*
  @AfterReturning(pointcut="getByNameCallsNumber()", returning="retByte")
  public void countEventAccess(JoinPoint joinPoint, Object retVal) {
    DiscountStatistic discountStatistic = new DiscountStatistic();

    TicketsQuantityStrategy

        switch(joinPoint.getTarget().getClass().getSimpleName()){
          case "BirthdayStrategy":
        }


    if("BirthdayStrategy".equals(joinPoint.getTarget().getClass().getSimpleName())) {

    }
  }*/

}
