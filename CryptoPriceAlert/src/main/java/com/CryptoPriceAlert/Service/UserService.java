package com.CryptoPriceAlert.Service;

import com.CryptoPriceAlert.Payloads.UserDTO;

public interface UserService 
{
    UserDTO createUser(UserDTO userDto);
    UserDTO getUser(Long userId);
    
}
