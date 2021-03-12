package com.dev.lembrete.repository;

import com.dev.lembrete.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface RoleRepository extends JpaRepository<Role, BigInteger>
{
    Role findByRoleName(String role);
}
