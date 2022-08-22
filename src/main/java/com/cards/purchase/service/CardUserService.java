package com.cards.purchase.service;

import com.cards.purchase.dto.CardCodesDto;
import com.cards.purchase.dto.CardUserDto;
import com.cards.purchase.dto.PurchasedCardDto;
import com.cards.purchase.dto.PurchasedCardResponse;
import com.cards.purchase.model.CardCodes;
import com.cards.purchase.model.CardUser;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;

public interface CardUserService {
    ResponseEntity<CardUser> createCardUser(CardUserDto cardUserDto);
    User getUser(String username);

    ResponseEntity<CardCodes> populateCardCodes(CardCodesDto cardCodesDto);

    ResponseEntity<PurchasedCardResponse> purchaseCards(PurchasedCardDto purchase);
}
