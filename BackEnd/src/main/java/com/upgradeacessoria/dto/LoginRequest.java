package com.upgradeacessoria.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String senha;
}
