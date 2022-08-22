package com.cards.purchase.Repository;

import com.cards.purchase.model.CardCodes;
import com.cards.purchase.model.PurchasedCards;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchasedCardRepository extends JpaRepository<PurchasedCards, Long> {
    PurchasedCards findByCardCodesAndCardNumber(CardCodes cc, long cardNumber);
}
