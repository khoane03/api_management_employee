package com.dev.l3.repository;

import com.dev.l3.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {
    boolean existsByRefreshToken(String token);

    Optional<Token> findByAccessToken(String token);

    boolean existsByAccessToken(String token);

    Optional<Token> findByRefreshToken(String token);

    Optional<Token> findByUserId(Integer id);
}
