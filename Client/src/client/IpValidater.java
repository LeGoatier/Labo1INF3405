package client;

import java.util.Scanner;

public class IpValidater {
 public String getIP() {
	// Adresse et port du serveur
			Scanner scanner = new Scanner(System.in);
			inputValidationAdresse:
			while(true) {
				System.out.print("Veulliez entrer l'adresse IPv4 du serveur\n");
				String input = scanner.nextLine();
				String[] bytes = input.split("\\.");
				if(bytes.length != 4)
				{
					System.out.print("L'adresse doit être sur 4 octets\n");
					continue;
				}
				try {
				for(int i = 0; i < 4 ; i++)
				{
					int temp = Integer.parseInt(bytes[i]);
					if(temp < 0 || temp > 255)
					{
						System.out.print("Un octect est entre 0 et 255\n");
						
						continue inputValidationAdresse;
					}
						
				}
				scanner.close();
				return input;
				} catch(NumberFormatException e) {/*recommence la boucle while true*/}
			}
			
			
 }
 public int getPort() {
	 Scanner scanner = new Scanner(System.in);
	 while(true) {
		 try {
			System.out.print("Veulliez entrer le port du serveur du serveur "
					+ "\nil devrait ëtre entre 5000 et 5050 pour être valide\n");
			Integer input  = scanner.nextInt();
			if(input >= 5000 && input <= 5050) {
				scanner.close();
				return input;
			}
		 }
		 catch(NumberFormatException e){/*recommence la boucle while*/}
			
		}
 }
}
