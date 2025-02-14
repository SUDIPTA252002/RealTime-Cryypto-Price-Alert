package com.CryptoPriceAlert.Payloads;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UserDTO
{
    private Long Id;
    private String username;
    private String email;
    private boolean isActive; 

    List<CryptoAlertDTO> alerts; 
}