package com.CryptoPriceAlert.Payloads;
import com.CryptoPriceAlert.Enums.Condition;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CryptoAlertDTO
{
    private Long id;

    private String cryptoSymbol; 
    private Double thresholdPrice; 
    private Condition condition; 
    private UserDTO user;
    private boolean isTriggered;

}