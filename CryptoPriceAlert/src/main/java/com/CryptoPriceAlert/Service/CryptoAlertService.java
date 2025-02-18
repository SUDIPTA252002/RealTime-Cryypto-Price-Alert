package com.CryptoPriceAlert.Service;

import com.CryptoPriceAlert.Payloads.CryptoAlertDTO;

public interface CryptoAlertService 
{
    CryptoAlertDTO createAlert(CryptoAlertDTO cryptoAlertDto,Long userId);
    CryptoAlertDTO getAlert(Long userId,String cryptoSymbol);
    void deleteAlert(Long id);
    
}
