package com.humanresource.service;

import com.humanresource.auth.User;
import com.humanresource.projection.UserProjection;

import java.util.List;

/**
 * @author umeshkhatiwada13@infodev
 * @project spring-security
 * @created 27/08/2022 - 17:44
 */
public interface UserService {

    User findById(Integer id) throws Exception;

    void save(User user);

    boolean delete(Integer id);

    List<User> findAll();

    List<UserProjection> findAllUnassigned();
}
