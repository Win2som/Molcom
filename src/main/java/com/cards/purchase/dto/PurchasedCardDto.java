package com.cards.purchase.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PurchasedCardDto {
    private int areaCode;
    private int accessCode;
    private long min;
    private long max;
    private int qty;
}
