package com.cards.purchase.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PurchasedCardResponse {
    private long accessCode;
    private int areaCode;
    private long min;
    private long max;
    private List<Long> numberList = new ArrayList<>();
}
