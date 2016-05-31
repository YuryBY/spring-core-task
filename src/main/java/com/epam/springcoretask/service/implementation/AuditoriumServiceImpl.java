package com.epam.springcoretask.service.implementation;

import com.epam.springcoretask.dao.AuditoriumDao;
import com.epam.springcoretask.domain.Auditorium;
import com.epam.springcoretask.service.AuditoriumService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;

/**
 * Created by Yury_Bakhmutski on 5/16/2016.
 */
@Service
public class AuditoriumServiceImpl implements AuditoriumService {

  @Resource
  AuditoriumDao auditoriumDao;

  public AuditoriumServiceImpl() {
  }

  public AuditoriumServiceImpl( AuditoriumDao auditoriumDao ) {
    this.auditoriumDao = auditoriumDao;
  }

  public void setAuditoriumDao( AuditoriumDao auditoriumDao ) {
    this.auditoriumDao = auditoriumDao;
  }

  @Override
  public Set<Auditorium> getAll() {
    return auditoriumDao.getAll();
  }

  @Override
  public Auditorium getByName( String name ) {
    return auditoriumDao.getAuditoriumByName( name );
  }
}
