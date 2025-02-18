package com.CryptoPriceAlert.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CryptoPriceAlert.Payloads.CryptoAlertDTO;
import com.CryptoPriceAlert.Service.CryptoAlertService;

@RestController
@RequestMapping("/api/crypto-alert")
public class CryptoAlertController 
{

    @Autowired
    private CryptoAlertService cryptoAlertService;
    
    
    @PostMapping("/{userId}/create-crypto-alert")
    public ResponseEntity<CryptoAlertDTO> createAlert(@PathVariable Long userId,@RequestBody CryptoAlertDTO cryptoDto)
    {
        CryptoAlertDTO createdAlert=this.cryptoAlertService.createAlert(cryptoDto, userId);
        return new ResponseEntity<>(createdAlert,HttpStatus.CREATED);
    }

    @GetMapping("/{userId}/get-crypto/{cryptoSymbol}")
    public ResponseEntity<CryptoAlertDTO> getAlert(@PathVariable Long userId,@PathVariable String cryptoSymbol)
    {
        CryptoAlertDTO cryptoDto=this.cryptoAlertService.getAlert(userId, cryptoSymbol);
        return new ResponseEntity<>(cryptoDto,HttpStatus.OK);
    }
}
