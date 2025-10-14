package server;

import java.util.Scanner;

public class IpValidater {
	
	private Scanner scanner ;
	public IpValidater() {
		scanner = new Scanner(System.in);
	}
	
	
 public String getIP() {
	// Adresse et port du serveur
			inputValidationAdresse:
			while(true) {
				System.out.print("Veuilliez entrer l'adresse IPv4 du serveur\n");
				String input = this.scanner.nextLine();
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
				return input;
				} catch(NumberFormatException e) {/*recommence la boucle while true*/}
			}
			
			
 }
 public int getPort() {
	
	 while(true) {
		 try {
			System.out.print("Veuilliez entrer le port du serveur du serveur "
					+ "\nil devrait ëtre entre 5000 et 5050 pour être valide\n");
			Integer input  = this.scanner.nextInt();
			if(input >= 5000 && input <= 5050) {
				return input;
			}
		 }
		 catch(NumberFormatException e){/*recommence la boucle while*/}
			
		}
 }
}
