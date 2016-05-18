package com.epam.springcoretask.service.implementation;

import com.epam.springcoretask.dao.AuditoriumDAO;
import com.epam.springcoretask.domain.Auditorium;
import com.epam.springcoretask.service.AuditoriumService;

import java.util.Set;

/**
 * Created by Yury_Bakhmutski on 5/16/2016.
 */
public class AuditoriumServiceImpl implements AuditoriumService {

  public void setAuditoriumDAO( AuditoriumDAO auditoriumDAO ) {
    this.auditoriumDAO = auditoriumDAO;
  }

  AuditoriumDAO auditoriumDAO;


  @Override
  public Set<Auditorium> getAll() {
    return auditoriumDAO.getAuditoriums();
  }

  @Override
  public Auditorium getByName( String name ) {
    return auditoriumDAO.getAuditoriumByName( name );
  }
}
