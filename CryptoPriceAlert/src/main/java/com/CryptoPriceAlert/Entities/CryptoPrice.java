package com.CryptoPriceAlert.Entities;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CryptoPrice 
{
    private String cryptoSymbol;
    private Double cuurentPrice;
    private Date lastUpdated;
    
}
