package com.CryptoPriceAlert.Service;

import java.util.List;

import com.CryptoPriceAlert.Payloads.UserDTO;

public interface UserService 
{
    UserDTO createUser(UserDTO userDto);
    UserDTO getUser(Long userId);
    List<String> getCryptoIdsOfUser(Long userId);
    Double fetchThresholdPriceOfCrypto(Long userId,String cryptoSymbol);
}
