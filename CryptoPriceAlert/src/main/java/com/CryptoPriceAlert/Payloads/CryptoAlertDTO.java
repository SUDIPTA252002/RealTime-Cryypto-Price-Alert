package com.CryptoPriceAlert.Payloads;
import com.CryptoPriceAlert.Enums.Condition;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CryptoAlertDTO
{
    private Long id;

    private String cryptoSymbol; 
    private Double thresholdPrice; 
    private Condition condition; 

    private boolean isTriggered;

}