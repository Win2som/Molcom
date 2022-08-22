package com.cards.purchase;

import com.cards.purchase.dto.CardCodesDto;
import com.cards.purchase.dto.CardUserDto;
import com.cards.purchase.model.Role;
import com.cards.purchase.service.CardUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class CardPurchaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(CardPurchaseApplication.class, args);
    }



    //Here are my bean components
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }





//    @Bean
//    CommandLineRunner run (CardUserService cardUserService){
//        return args ->{
//
//            Role role = Role.ADMIN;
//            CardUserDto cardUserDto = CardUserDto.builder()
//                    .role(role)
//                    .username("win2som")
//                    .password("12345")
//                    .build();
//
//
//            cardUserService.createCardUser(cardUserDto);
//
//            cardUserService.populateCardCodes(new CardCodesDto("Lagos", 1, 249,2490000,2499999));
//            cardUserService.populateCardCodes(new CardCodesDto("Lagos", 1, 270,2480000,2489999));
//            cardUserService.populateCardCodes(new CardCodesDto("Lagos", 1, 273,2470000,2479999));
//            cardUserService.populateCardCodes(new CardCodesDto("Lagos", 1, 259,2460000,2469999));
//            cardUserService.populateCardCodes(new CardCodesDto("Lagos", 1, 260,2450000,2459999));
//            cardUserService.populateCardCodes(new CardCodesDto("Lagos", 1, 289,2430000,2439999));
//            cardUserService.populateCardCodes(new CardCodesDto("Lagos", 1, 284,2420000,2429999));
//            cardUserService.populateCardCodes(new CardCodesDto("Lagos", 1, 282,2410000,2419999));
//            cardUserService.populateCardCodes(new CardCodesDto("Lagos", 1, 281,2390000,2399999));
//            cardUserService.populateCardCodes(new CardCodesDto("Abuja", 3, 298,2380000,2389999));
//            cardUserService.populateCardCodes(new CardCodesDto("Abuja", 3, 212,2370000,2379999));
//
//        };
//    }
}
