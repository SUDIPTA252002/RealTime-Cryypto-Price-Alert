package com.CryptoPriceAlert.Service;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CryptoPriceFetcherService
{

    private WebClient webClient;

    @Autowired
    private CryptoPriceCache cryptoCache;

    @Autowired
    private UserService userService;

    // private Set<String> trackedCryptos;

    @Autowired
    public CryptoPriceFetcherService(CryptoPriceCache cryptoCache,UserService userService)
    {
        this.webClient=WebClient.create("https://api.coingecko.com/api/v3");
        this.cryptoCache=cryptoCache;
        this.userService=userService;
    }

    @Scheduled(fixedRate = 3000)
    public void fetchCryptoPricesForUser(Long userId)
    {
        List<String> cryptoIds=this.userService.getCryptoIdsOfUser(userId);

        if(!cryptoIds.isEmpty())
        {

            String response=webClient.get()
                    .uri(uriBuilder->uriBuilder.path("/simple/price")
                        .queryParam("ids",String.join(",",cryptoIds))
                        .queryParam("vs_currenecies","usd")
                        .build())
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

                    parseResponse(userId,response);              
        }
    }

    public void parseResponse(Long userId,String response)
    {
        try
        {
            JsonNode rootNode=new ObjectMapper().readTree(response);
            Iterator<Map.Entry<String,JsonNode>> fields=rootNode.fields();

            if(fields.hasNext())
            {
                Map.Entry<String,JsonNode> field=fields.next();
                String symbol=field.getKey();
                Double price=field.getValue().get("usd").asDouble();

                cryptoCache.updatePrice(symbol, price);

                Double threshold=userService.fetchThresholdPriceOfCrypto(userId, symbol);

                if (threshold != null && price > threshold) 
                {
                    System.out.println("Alert: " + symbol + " price is " + price + ", crossing the threshold of " + threshold);
                    // Add notification logic here (e.g., send an email, push notification, etc.)
                }
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();

        }
    }

}