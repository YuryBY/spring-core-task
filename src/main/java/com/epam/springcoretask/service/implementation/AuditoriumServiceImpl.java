package com.epam.springcoretask.service.implementation;

import com.epam.springcoretask.dao.AuditoriumDao;
import com.epam.springcoretask.domain.Auditorium;
import com.epam.springcoretask.service.AuditoriumService;

import java.util.Set;

/**
 * Created by Yury_Bakhmutski on 5/16/2016.
 */
public class AuditoriumServiceImpl implements AuditoriumService {

  public void setAuditoriumDao( AuditoriumDao auditoriumDao ) {
    this.auditoriumDao = auditoriumDao;
  }

  AuditoriumDao auditoriumDao;


  @Override
  public Set<Auditorium> getAll() {
    return auditoriumDao.getAuditoriums();
  }

  @Override
  public Auditorium getByName( String name ) {
    return auditoriumDao.getAuditoriumByName( name );
  }
}
