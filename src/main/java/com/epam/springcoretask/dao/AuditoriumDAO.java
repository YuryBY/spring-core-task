package com.epam.springcoretask.dao;

import com.epam.springcoretask.domain.Auditorium;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 * Created by Yury_Bakhmutski on 5/16/2016.
 */
@Repository
public class AuditoriumDao {
  public Set<Auditorium> getAuditoriums() {
    return auditoriums;
  }

  Set<Auditorium> auditoriums = new HashSet<Auditorium>();

  @PostConstruct
  public void init() {
    Properties prop = new Properties();
    String fileName = "src/main/resources/auditorium.properties";
    InputStream inputStream = null;
    try {
      inputStream = new FileInputStream(fileName);
    } catch ( FileNotFoundException e ) {
      e.printStackTrace();
    }
    if ( inputStream != null ) {
      try {
        prop.load( inputStream );
      } catch ( IOException e ) {
        e.printStackTrace();
      }
    }

    for ( Object currentKey : prop.keySet() ) {
      String value = (String) prop.get( currentKey );
      auditoriums.add( parseAuditorium( value ) );
    }
  }

  public Auditorium getAuditoriumByName( String name ) {
    for ( Auditorium auditorium : auditoriums ) {
      if ( auditorium.getName().equals( name ) ) {
        return auditorium;
      }
    }
    return null;
  }

  private Auditorium parseAuditorium( String value ) {
    Auditorium auditorium = new Auditorium();
    String[] auditoriumInfo = value.split( ";" );
    auditorium.setName( auditoriumInfo[0] );
    auditorium.setNumberOfSeats( Long.valueOf( auditoriumInfo[1].trim() ) );
    String[] vipSeats = auditoriumInfo[2].split( "," );
    Set<Long> seatsSet = new HashSet<>();
    for ( String seat : vipSeats ) {
      seatsSet.add( Long.valueOf( seat.trim() ) );
    }
    auditorium.setVipSeats( seatsSet );
    return auditorium;
  }
}
