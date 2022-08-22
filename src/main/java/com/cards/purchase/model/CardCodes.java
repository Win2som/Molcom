package com.cards.purchase.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity @Data @NoArgsConstructor @AllArgsConstructor @Table(name = "card_codes") @Builder
public class CardCodes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String area;
    private int areaCode;
    @Column(unique = true)
    private int accessCode;
    private long rangeStart;
    private long rangeEnd;
    private long totalLines;

}
