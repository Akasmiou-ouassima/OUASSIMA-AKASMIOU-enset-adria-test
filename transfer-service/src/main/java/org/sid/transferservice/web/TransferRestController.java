package org.sid.transferservice.web;

import org.sid.transferservice.Repositories.TransferRepository;
import org.sid.transferservice.entities.Transfer;
import org.sid.transferservice.model.Wallet;
import org.sid.transferservice.services.WalletRestClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransferRestController {
    private TransferRepository transferRepository;
    private WalletRestClientService walletRestClientService;

    public TransferRestController(TransferRepository transferRepository, WalletRestClientService walletRestClientService) {
        this.transferRepository = transferRepository;
        this.walletRestClientService = walletRestClientService;
    }

    @GetMapping("/fullTransfer/{id}")
    public Transfer getOrder(@PathVariable Long id){
        Transfer transfer=transferRepository.findById(id).get();
        Wallet wallet1=walletRestClientService.walletById(transfer.getSourceWalletId());
        transfer.setSourceWallet(wallet1);

        Wallet wallet2=walletRestClientService.walletById(transfer.getDestWalletId());
        transfer.setDestWallet(wallet2);

        return transfer;
    }
}



