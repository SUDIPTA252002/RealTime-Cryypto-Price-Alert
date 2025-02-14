package com.CryptoPriceAlert.Entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CryptoPrice 
{
    @Id
    private String cryptoSymbol;
    private Double cuurentPrice;
    private Date lastUpdated;
    
}
