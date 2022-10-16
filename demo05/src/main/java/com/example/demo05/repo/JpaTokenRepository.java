package com.example.demo05.repo;

import com.example.demo05.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Administrator
 */
@Repository
public interface JpaTokenRepository extends JpaRepository<Token, Integer> {

    /**
     * findAllByIdentifier
     * @param identifier
     * @return
     */
    Optional<Token> findAllByIdentifier(String identifier);
}
