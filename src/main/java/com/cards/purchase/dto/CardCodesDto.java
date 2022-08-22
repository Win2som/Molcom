package com.cards.purchase.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CardCodesDto {
    private String area;
    private int areaCode;
    private int accessCode;
    private long rangeStart;
    private long rangeEnd;
}
