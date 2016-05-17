package com.epam.springcoretask.service.implementation;

import com.epam.springcoretask.dao.UserDAO;
import com.epam.springcoretask.domain.User;
import com.epam.springcoretask.service.UserService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * Created by Yury_Bakhmutski on 5/16/2016.
 */
public class UserServiceImpl implements UserService {

  private UserDAO userDAO;

  @Override
  public User getUserByEmail( String email ) {
    Map<Long, User> users = userDAO.getUsers();
    for ( Map.Entry<Long, User> entry : users.entrySet() ) {
      User currentUser = entry.getValue();
      if ( currentUser.getEmail().equals( email ) ) {
        return currentUser;
      }
    }
    return null;
  }

  @Override
  public User save( User user ) {
    return userDAO.saveUser( user );
  }

  @Override
  public void remove( User user ) {
    userDAO.removeUser( user );
  }

  @Override
  public User getById( Long id ) {
    return userDAO.getUserById( id );
  }

  @Override
  public Collection<User> getAll() {
    Map<Long, User> users = userDAO.getUsers();
    return new ArrayList( users.values() );
  }

  public void setUserDAO( UserDAO userDAO ) {
    this.userDAO = userDAO;
  }
}
