package com.cards.purchase.dto;

import com.cards.purchase.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor @Builder @Data
public class CardUserDto {
    private String username;
    private String password;
    private Role role;
}
