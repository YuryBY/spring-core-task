package com.epam.springcoretask.dao;

import com.epam.springcoretask.domain.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yury_Bakhmutski on 5/16/2016.
 */
@Repository
public class UserDao {
  Map<Long, User> users = new HashMap<>();

  public User getUserById( Long id ) {
    return users.get( id );
  }

  public User saveUser( User user ) {
    Long id = user.getId();
    return users.put( id, user );
  }

  public void removeUser( User user ) {
    users.remove( user );
  }

  public Map<Long, User> getUsers() {
    return users;
  }

  public void setUsers( Map<Long, User> users ) {
    this.users = users;
  }

}
