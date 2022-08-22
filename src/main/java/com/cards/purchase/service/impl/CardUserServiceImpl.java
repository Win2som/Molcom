package com.cards.purchase.service.impl;

import com.cards.purchase.Repository.CardCodesRepository;
import com.cards.purchase.Repository.CardUserRepository;
import com.cards.purchase.Repository.PurchasedCardRepository;
import com.cards.purchase.dto.CardCodesDto;
import com.cards.purchase.dto.CardUserDto;
import com.cards.purchase.dto.PurchasedCardDto;
import com.cards.purchase.dto.PurchasedCardResponse;
import com.cards.purchase.model.CardCodes;
import com.cards.purchase.model.CardUser;
import com.cards.purchase.model.PurchasedCards;
import com.cards.purchase.service.CardUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CardUserServiceImpl implements CardUserService, UserDetailsService {
    private final CardUserRepository cardUserRepository;
    private final CardCodesRepository cardCodesRepository;
   private final PurchasedCardRepository purchasedCardRepository;
    private final PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CardUser cardUser = cardUserRepository.findCardUserByUsername(username).orElseThrow(RuntimeException::new);
        log.info("user found in the database: {}", cardUser.getUsername());
        return new User(cardUser.getUsername(), cardUser.getPassword(), List.of(new SimpleGrantedAuthority(cardUser.getRole().name())));
    }
    @Override
    public ResponseEntity<CardUser> createCardUser(CardUserDto cardUserDto) {

        CardUser carduser = cardUserRepository.findCardUserByUsername(cardUserDto.getUsername()).orElse(null);
        if(carduser != null) throw new RuntimeException();
        carduser = CardUser.builder()
                .username(cardUserDto.getUsername())
                .password(encoder.encode(cardUserDto.getPassword()))
                .role(cardUserDto.getRole())
                .build();
        return new ResponseEntity<CardUser>(cardUserRepository.save(carduser), HttpStatus.CREATED);
    }

    @Override
    public User getUser(String username) {
        CardUser cardUser = cardUserRepository.findCardUserByUsername(username).orElseThrow(RuntimeException::new);
        log.info("user found in the database: {}", cardUser.getUsername());
        return new User(cardUser.getUsername(), cardUser.getPassword(), List.of(new SimpleGrantedAuthority(cardUser.getRole().name())));
    }

    @Override
    public ResponseEntity<CardCodes> populateCardCodes(CardCodesDto cardCodesDto) {
        CardCodes cards = cardCodesRepository.findCardCodesByAccessCode(cardCodesDto.getAccessCode()).orElse(null);
        if(cards != null) throw new RuntimeException("Already Exists");

        CardCodes card = CardCodes.builder()
                .area(cardCodesDto.getArea())
                .areaCode(cardCodesDto.getAreaCode())
                .accessCode(cardCodesDto.getAccessCode())
                .rangeStart(cardCodesDto.getRangeStart())
                .rangeEnd(cardCodesDto.getRangeEnd())
                .totalLines(cardCodesDto.getRangeEnd() - cardCodesDto.getRangeStart() + 1)
                .build();

        return ResponseEntity.ok().body(cardCodesRepository.save(card));
    }

    @Override
    public ResponseEntity<PurchasedCardResponse> purchaseCards(PurchasedCardDto purchase) {
        CardCodes cards = cardCodesRepository.findFirstByAreaCodeAndAccessCodeAndRangeStartAndRangeEnd(purchase.getAreaCode(), purchase.getAccessCode(), purchase.getMin(), purchase.getMax());
        if(cards == null) {
            log.info("Its null So Create");
            throw new RuntimeException("There is no Area code for this purchase");
        }

        List<Long> cardNumberList = new ArrayList<>();
        generateRandom(purchase.getMin(), purchase.getMax(), purchase.getQty(), cardNumberList,cards);

        PurchasedCardResponse pcr = PurchasedCardResponse.builder()
                .accessCode(purchase.getAccessCode())
                .max(purchase.getMax())
                .min(purchase.getMin())
                .numberList(cardNumberList)
                .areaCode(purchase.getAreaCode())
                .build();

        return ResponseEntity.ok(pcr);
    }

    public void generateRandom(long min, long max, int qty, List<Long> integerList, CardCodes cc){


        for(int i = 0; i < qty; i++){

            boolean flag = true;
            while(flag){
                long randomLong = (long)Math.floor(Math.random()*(max-min+1)+min);

                PurchasedCards pc = purchasedCardRepository.findByCardCodesAndCardNumber(cc, randomLong );
                if (pc == null){

                    pc = new PurchasedCards();
                    pc.setCardCodes(cc);
                    pc.setCardNumber(randomLong);
                    purchasedCardRepository.save(pc);
                    integerList.add(randomLong);

                    flag = false;
                }
            }


        }


    }




}
