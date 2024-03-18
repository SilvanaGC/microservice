package com.company.microservice.repositories;

import com.company.microservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    @Query(value = "select * from LOGIN where email = :emaillUsuario\n" +
            "and contrasena = :password", nativeQuery = true)
    User login(String emaillUsuario, String password);
}