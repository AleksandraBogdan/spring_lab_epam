package com.epam.dao;

import com.epam.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsersDao extends JpaRepository<User, Long> {
    @Query("select * from USER u where u.email = :email")
    User findByEmail(@Param("email") String email);
    @Query("update USER set subscription = :sub where id = :id")
    void update(@Param("id") int id,@Param("sub") String subscription);
}
