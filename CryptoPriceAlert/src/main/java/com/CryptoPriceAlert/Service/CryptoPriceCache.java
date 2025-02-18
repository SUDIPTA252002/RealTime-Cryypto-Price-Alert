package com.CryptoPriceAlert.Service;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.CryptoPriceAlert.Entities.CryptoPrice;


@Component
public class CryptoPriceCache 
{

    ConcurrentHashMap<String,CryptoPrice> priceCache=new ConcurrentHashMap<>();

    public void updatePrice(String symbol,Double price)
    {
        priceCache.put(symbol,new CryptoPrice(symbol,price,new Date()));
    }

    public CryptoPrice getPrice(String symbol)
    {
        return priceCache.get(symbol);
    }

    public ConcurrentHashMap<String,CryptoPrice> getAllPrices()
    {
        return priceCache;
    }
    
}
