package com.epam.springcoretask.services;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by Yury_Bakhmutski on 5/26/2016.
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan( { "com.epam.springcoretask.service", "com.epam.springcoretask.dao", "com.epam.springcoretask.aspect" } )
public class TestAppConfig {
}
