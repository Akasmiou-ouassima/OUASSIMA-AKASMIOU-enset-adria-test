package org.sid.transferservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.transferservice.enums.Etat;
import org.sid.transferservice.model.Wallet;

import java.util.Date;
import java.util.Random;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private double montant;
    private Etat etat;
    private Long sourceWalletId;
    private Long destWalletId;

    @Transient
    private Wallet sourceWallet;

    @Transient
    private Wallet destWallet;



}
