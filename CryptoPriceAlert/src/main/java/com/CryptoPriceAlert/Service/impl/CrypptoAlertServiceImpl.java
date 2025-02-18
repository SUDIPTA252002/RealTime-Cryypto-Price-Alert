package com.CryptoPriceAlert.Service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CryptoPriceAlert.Entities.CryptoAlert;
import com.CryptoPriceAlert.Entities.User;
import com.CryptoPriceAlert.Exceptions.ResourceNotFoundException;
import com.CryptoPriceAlert.Payloads.CryptoAlertDTO;
import com.CryptoPriceAlert.Payloads.UserDTO;
import com.CryptoPriceAlert.Repository.CrytpoAlertRepo;
import com.CryptoPriceAlert.Repository.UserRepo;
import com.CryptoPriceAlert.Service.CryptoAlertService;

@Service
public class CrypptoAlertServiceImpl implements CryptoAlertService
{
    @Autowired
    private CrytpoAlertRepo crytpoAlertRepo;
    
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;
    
    @Override
    public CryptoAlertDTO createAlert(CryptoAlertDTO cryptoAlertDto,Long userId) 
    {

        CryptoAlert cryptoAlert=this.modelMapper.map(cryptoAlertDto,CryptoAlert.class);
        User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","UserID", Long.toString(userId)));
        
        cryptoAlert.setUser(user);
        cryptoAlert.setTriggered(false);

        CryptoAlert savedCryptoAlert=this.crytpoAlertRepo.save(cryptoAlert);
        CryptoAlertDTO savedDto=this.modelMapper.map(savedCryptoAlert,CryptoAlertDTO.class);
        savedDto.setUser(this.modelMapper.map(savedDto,UserDTO.class));

        return savedDto;
    }

    @Override
    public CryptoAlertDTO getAlert(Long userId,String cryptoSymbol) 
    {
        User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","UserID", Long.toString(userId)));
        List<CryptoAlert> alerts=user.getAlerts();
        Long id=alerts.stream()
                        .filter(alert->(alert.getCryptoSymbol().equals(cryptoSymbol)))
                        .map(alert->alert.getId())
                        .findFirst()
                        .orElse(null);

        CryptoAlert cryptoAlert=this.crytpoAlertRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Crypto","id", Long.toString(id)));
        
        CryptoAlertDTO cryptoDto=this.modelMapper.map(cryptoAlert,CryptoAlertDTO.class);
        cryptoDto.setUser(this.modelMapper.map(user,UserDTO.class));

        return cryptoDto;
    }

    @Override
    public void deleteAlert(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteAlert'");
    }

    
}
