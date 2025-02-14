package com.CryptoPriceAlert.Entities;

import java.time.LocalDateTime;

import com.CryptoPriceAlert.Enums.Condition;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Crypto-Alert")
public class CryptoAlert 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name="user_Id",nullable = false)
    private User user;
    private String cryptoSymbol; 
    private Double thresholdPrice; 
    private Condition condition; 

    private boolean isTriggered;

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() 
    {
        createdAt = LocalDateTime.now();
    }
    
}
