package com.epam.springcoretask;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jmx.export.annotation.AnnotationMBeanExporter;
import org.springframework.transaction.annotation.AnnotationTransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;

/**
 * Created by Yury_Bakhmutski on 5/25/2016.
 */
@Configuration
@ComponentScan( { "com.epam.springcoretask.service", "com.epam.springcoretask.dao" } )
@EnableAspectJAutoProxy
public class AppConfig {
  @Bean
  public AnnotationMBeanExporter annotationMBeanExporter() {
    return new AnnotationMBeanExporter();
  }

  @Bean
  public TransactionAttributeSource annotationTransactionAttributeSource() {
    return new AnnotationTransactionAttributeSource();
  }

  /*@Bean
  public TransactionInterceptor transactionInterceptor() {
    return new TransactionInterceptor(transactionManager(), annotationTransactionAttributeSource());
  }*/
}
