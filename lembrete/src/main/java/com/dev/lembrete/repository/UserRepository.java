package com.dev.lembrete.repository;

import com.dev.lembrete.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUserName(String username);
    @Query("SELECT userId FROM User WHERE userName = ?1")
    Long findUseridByUsername(String username);
    @Query(value = "INSERT INTO user_roles (role_id, user_id) VALUES (?, ?)", nativeQuery = true)
    void saveUserRole(Long rID, Long uID);
}
