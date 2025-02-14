package com.CryptoPriceAlert.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CryptoPriceAlert.Entities.User;

public interface UserRepo extends JpaRepository<User,Long>
{
    
}
