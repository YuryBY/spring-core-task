package com.epam.springcoretask.service.implementation;

import com.epam.springcoretask.dao.UserDao;
import com.epam.springcoretask.domain.User;
import com.epam.springcoretask.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * Created by Yury_Bakhmutski on 5/16/2016.
 */
@Service
public class UserServiceImpl implements UserService {

  @Resource
  private UserDao userDao;

  @Override
  public User getUserByEmail( String email ) {
    Map<Long, User> users = userDao.getUsers();
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
    return userDao.saveUser( user );
  }

  @Override
  public void remove( User user ) {
    userDao.removeUser( user );
  }

  @Override
  public User getById( Long id ) {
    return userDao.getUserById( id );
  }

  @Override
  public Collection<User> getAll() {
    Map<Long, User> users = userDao.getUsers();
    return new ArrayList( users.values() );
  }

  public void setUserDao( UserDao userDao ) {
    this.userDao = userDao;
  }
}
