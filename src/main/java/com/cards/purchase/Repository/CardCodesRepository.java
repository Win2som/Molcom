package com.cards.purchase.Repository;

import com.cards.purchase.model.CardCodes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardCodesRepository extends JpaRepository<CardCodes, Long> {
    Optional<CardCodes> findCardCodesByAccessCode(int accessCode);

    CardCodes findFirstByAreaCodeAndAccessCodeAndRangeStartAndRangeEnd(int areaCode, int accessCode, long rangeStart, long rangeEnd);
}
