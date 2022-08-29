package com.humanresource.auth;

import com.humanresource.projection.UserProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author umeshkhatiwada13@infodev
 * @project spring-security
 * @created 27/08/2022 - 21:34
 */
public interface UserRepo extends JpaRepository<User, Integer> {
    User findByUsername(String username);

    @Query(value = "SELECT * from user",
            nativeQuery = true)
    List<User> findAllActive();

    @Query(value = "UPDATE user SET is_active= FALSE WHERE id = ?1",
            nativeQuery = true)
    void updateDeletedStatusById(Integer id);

    @Query(value = "select u.id, u.username\n" +
            "from user u \n" +
            "left join employee e on e.user_id = u.id \n" +
            "where e.user_id is null and u.id <> 1",
            nativeQuery = true)
    List<UserProjection> findAllUnassigned();
}
