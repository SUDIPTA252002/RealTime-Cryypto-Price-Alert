package com.CryptoPriceAlert.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CryptoPriceAlert.Entities.CryptoAlert;

public interface CrytpoAlertRepo extends JpaRepository<CryptoAlert,Long> 
{
    
}
