package org.sid.walletservice;

import org.sid.walletservice.entities.Client;
import org.sid.walletservice.entities.Wallet;
import org.sid.walletservice.repositories.ClientRepository;
import org.sid.walletservice.repositories.WalletRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class WalletServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WalletServiceApplication.class, args);
	}
	@Bean
	CommandLineRunner start(ClientRepository clientRepository, WalletRepository walletRepository, RepositoryRestConfiguration restConfiguration) {
		restConfiguration.exposeIdsFor(Wallet.class);
		restConfiguration.exposeIdsFor(Client.class);
		return args -> {
			// Création de clients
			Client client1 = Client.builder().nom("client1").email("client1@gmail.com").build();
			Client client2 = Client.builder().nom("client2").email("client2@gmail.com").build();
			Client client3 = Client.builder().nom("client3").email("client3@gmail.com").build();

			// Sauvegarde des clients dans la base de données
			clientRepository.saveAll(List.of(client1, client2, client3));

			// Création de portefeuilles
			Wallet wallet1 = Wallet.builder().solde(1000).dateCreation(new Date()).devise("DH").client(client1).build();
			Wallet wallet2 = Wallet.builder().solde(2000).dateCreation(new Date()).devise("$").client(client2).build();
			Wallet wallet3 = Wallet.builder().solde(3000).dateCreation(new Date()).devise("$").client(client3).build();

			// Sauvegarde des portefeuilles dans la base de données
			walletRepository.saveAll(List.of(wallet1, wallet2, wallet3));

			// Affichage des clients et des portefeuilles
			System.out.println("Clients :");
			clientRepository.findAll().forEach(System.out::println);

			System.out.println("Portefeuilles :");
			walletRepository.findAll().forEach(System.out::println);
		};
	}
}
