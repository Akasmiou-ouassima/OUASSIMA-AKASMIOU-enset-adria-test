package org.sid.transferservice;

import org.sid.transferservice.Repositories.TransferRepository;
import org.sid.transferservice.entities.Transfer;
import org.sid.transferservice.enums.Etat;
import org.sid.transferservice.services.WalletRestClientService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.Date;
import java.util.List;

@SpringBootApplication
@EnableFeignClients
public class TransferServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransferServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(TransferRepository transferRepository, WalletRestClientService walletRestClientService, RepositoryRestConfiguration restConfiguration) {
		restConfiguration.exposeIdsFor(Transfer.class);


			restConfiguration.exposeIdsFor(Transfer.class);

			return args -> {
				// Création des transferts
				Transfer transfer1 = Transfer.builder().montant(100).date(new Date()).etat(Etat.VALIDATED).sourceWalletId(1L).destWalletId(2L).build();
				Transfer transfer2 = Transfer.builder().montant(200).date(new Date()).etat(Etat.PENDING).sourceWalletId(2L).destWalletId(3L).build();
				Transfer transfer3 = Transfer.builder().montant(300).date(new Date()).etat(Etat.REJECTED).sourceWalletId(3L).destWalletId(1L).build();
				Transfer transfer4 = Transfer.builder().montant(300).date(new Date()).etat(Etat.VALIDATED).sourceWalletId(2L).destWalletId(1L).build();


				// Sauvegarde des transferts dans la base de données
				transferRepository.saveAll(List.of(transfer1, transfer2, transfer3,transfer4));


				// Affichage des clients et des transferts


				System.out.println("Transferts :");
				transferRepository.findAll().forEach(System.out::println);

		};
	}
}
