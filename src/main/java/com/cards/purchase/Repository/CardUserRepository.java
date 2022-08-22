package com.cards.purchase.Repository;

import com.cards.purchase.model.CardUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardUserRepository extends JpaRepository<CardUser, Long> {
    Optional<CardUser> findCardUserByUsername(String username);
}
