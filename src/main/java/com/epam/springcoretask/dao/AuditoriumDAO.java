package com.epam.springcoretask.dao;

import com.epam.springcoretask.domain.Auditorium;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

/**
 * Created by Yury_Bakhmutski on 5/16/2016.
 */
@Repository
public class AuditoriumDao {

  @Autowired
  public JdbcTemplate jdbcTemplate;


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

      Auditorium auditorium = parseAuditorium( value );

      StringBuilder builder = new StringBuilder(  );

      Set<Long> vipSeats = auditorium.getVipSeats();
      for( Long a:vipSeats ){
      builder.append( a.toString() );builder.append( ", " );
      }

      jdbcTemplate.update( "INSERT INTO auditoriums (NAME, SEATS_NUMBER, VIP_SEATS) VALUES (?,?,?)",
          auditorium.getName(), auditorium.getNumberOfSeats(), builder.toString());
    }
  }

  public Auditorium getAuditoriumByName( String name ) {

    String query = "SELECT * FROM auditoriums WHERE NAME=?";
    final  Auditorium auditorium = jdbcTemplate.queryForObject( query, new Object[] { 1 }, new RowMapper<Auditorium>() {
      @Override
      public Auditorium mapRow( ResultSet resultSet, int i ) throws SQLException {
        Auditorium auditorium = new Auditorium();
        auditorium.setName( name );
        auditorium.setNumberOfSeats( resultSet.getInt( "SEATS_NUMBER" ) );

        Set<Long> vSeats = new HashSet<Long>();
        String seats = resultSet.getString( "VIP_SEATS" );
        String [] seatsArray = seats.split( "," );
        for(String s:seatsArray){
          vSeats.add( Long.valueOf( s ) );
        }
        auditorium.setVipSeats( vSeats );
        return auditorium;
      }
    } );

    return auditorium;
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

  public Set<Auditorium> getAll() {

    String sql = "SELECT * FROM auditoriums";
    List<Auditorium> listAuditorium = jdbcTemplate.query(sql, new RowMapper<Auditorium>() {

      @Override
      public Auditorium mapRow(ResultSet rs, int rowNum) throws SQLException {
        Auditorium DBAuditorium = new Auditorium();

        DBAuditorium.setName(rs.getString("NAME"));
        DBAuditorium.setNumberOfSeats(rs.getInt("SEATS_NUMBER"));

        Set<Long> vSeats = new HashSet<Long>();
        String[] vipSeatsTokenSplitted = rs.getString("VIP_SEATS").split(",");
        for (int k=0; k<vipSeatsTokenSplitted.length; k++) {
          vSeats.add(Long.valueOf(vipSeatsTokenSplitted[k]));
        }
        DBAuditorium.setVipSeats(vSeats);

        return DBAuditorium;
      }

    });

    Set<Auditorium> setAuditorium = new HashSet<Auditorium>();
    for (Auditorium auditorium : listAuditorium) {
      setAuditorium.add(auditorium);
    }
    return setAuditorium;
  }
}
