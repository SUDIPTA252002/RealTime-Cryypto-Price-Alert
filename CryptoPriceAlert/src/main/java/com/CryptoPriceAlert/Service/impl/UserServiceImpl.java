package com.CryptoPriceAlert.Service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CryptoPriceAlert.Entities.CryptoAlert;
import com.CryptoPriceAlert.Entities.User;
import com.CryptoPriceAlert.Exceptions.ResourceNotFoundException;
import com.CryptoPriceAlert.Payloads.CryptoAlertDTO;
import com.CryptoPriceAlert.Payloads.UserDTO;
import com.CryptoPriceAlert.Repository.UserRepo;
import com.CryptoPriceAlert.Service.UserService;

@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    UserRepo userRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public UserDTO createUser(UserDTO userDto) 
    {
        User user=this.modelMapper.map(userDto,User.class);
        User savedUser=this.userRepo.save(user);
        UserDTO savedUserDto=this.modelMapper.map(savedUser,UserDTO.class);
        return savedUserDto;
   }

    @Override
    public UserDTO getUser(Long userId) 
    {
        User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","UserID", Long.toString(userId)));
        UserDTO fetchedUserDto=this.modelMapper.map(user,UserDTO.class);
        List<CryptoAlertDTO> alertDtos=user.getAlerts().
                                            stream()
                                            .map(alert->this.modelMapper.map(alert,CryptoAlertDTO.class))
                                            .collect(Collectors.toList());
        fetchedUserDto.setAlerts(alertDtos);
        return fetchedUserDto;
    }
    
    @Override
    public List<String> getCryptoIdsOfUser(Long userId)
    {
        User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","UserID", Long.toString(userId)));
        List<CryptoAlert> alerts=user.getAlerts();
        List<String> cryptoIds=alerts.stream().map(cryptoAlert->cryptoAlert.getCryptoSymbol()).collect(Collectors.toList());
        return cryptoIds;
    }

    @Override
    public Double fetchThresholdPriceOfCrypto(Long userId, String cryptoSymbol) 
    {
        User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","UserID", Long.toString(userId)));
        List<CryptoAlert> alerts=user.getAlerts();
        Double thresholdPrice=alerts.stream().filter(alert->(alert.getCryptoSymbol().equals(cryptoSymbol)))
                        .map(alert->alert.getThresholdPrice())
                        .findFirst()
                        .orElse(null);
        return thresholdPrice;
    }
}
