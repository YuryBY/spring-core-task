package com.epam.springcoretask.service;

import com.epam.springcoretask.domain.Auditorium;

import java.util.Set;

/**
 * @author Yuriy_Tkach
 */
public interface AuditoriumService {

  /**
   * Getting all auditoriums from the system
   *
   * @return set of all auditoriums
   */
  public Set<Auditorium> getAll();

  /**
   * Finding auditorium by name
   *
   * @param name Name of the auditorium
   * @return found auditorium or <code>null</code>
   */
  public Auditorium getByName( String name );

}
