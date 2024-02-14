package com.manufacture.expertservice.repository;


import com.manufacture.expertservice.model.Role;
import com.manufacture.expertservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByUsernameOrEmail(String username, String email);

    List<User> findByIdIn(List<Long> userIds);


    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    List<User> findByRoles_(Optional<Role> optional);


    // @Query(
    //	 value =  "SELECT * FROM USERS INNER JOIN user_roles ON users.id = user_roles.user_id INNER JOIN roles ON user_roles.role_id = roles.id where roles.name='ROLE_EXPERT'"
    //  )
    //	List<User> findAllExperts();

//@Query( "SELECT *   FROM users 
    //	INNER JOIN user_roles  "
    //	+ " ON users.id = user_roles.user_id INNER JOIN roles "
    //	+ " ON user_roles.role_id = roles.id where roles.name='ROLE_EXPERT'" )
//List<User> findAllExperts();
}