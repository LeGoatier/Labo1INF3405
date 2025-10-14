package server;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
public class CommandHandler {
	File currentDirectory = new File(System.getProperty("user.dir"));
	
	public void handleCmd(String input, DataInputStream in, DataOutputStream out) {
		try {
			if(input == null || input == "") {
				out.writeUTF("Commande vide, réessayez");
			}
			
			String[] words = input.split(" ");
			
			String cmd = words[0].toUpperCase();
			
			String argument = words.length > 1 ? words[1] : "";
	
		    switch (cmd) {
		        case "CD":
		            if (words.length < 2) {
		                out.writeUTF("Utilisation: cd <répertoire>");
		            } else {
		                changeDirectory(argument, out);
		            }
		            break;
	
		        case "LS":
		            listFiles(out);
		            break;
		            
		        case "MKDIR":
		        		makeDirectory(argument, out);
		        		break;
	
		        case "UPLOAD":
		            receiveFile(argument, in, out);
		            break;
	
		        case "DOWNLOAD":
		            sendFile(argument, out);
		            break;
	
		        case "DELETE":
		            deleteFile(argument, out);
		            break;
	
		        case "EXIT":
		            out.writeUTF("Bye bye");
		            break;
		            
		        case "ECHO":
					out.writeUTF(input);
					break;
					
		        default:
		            out.writeUTF("Commande inconnue");
		    }			
		}catch(IOException e) {
			System.out.println("IO EXCEPTION");
		}
	}

	

	private void changeDirectory(String argument, DataOutputStream out) {
		try {
			File newDir;
		    
		    if (argument.equals("..")) {
		        newDir = currentDirectory.getParentFile();
		        if (newDir == null) newDir = currentDirectory; // rester à la racine
		    } else {
		        newDir = new File(currentDirectory, argument);
		    }
	
		    if (newDir.exists() && newDir.isDirectory()) {
		    		currentDirectory = newDir.getCanonicalFile(); // normalise le chemin
		        out.writeUTF("Répertoire courant: " + currentDirectory.getName());
		    } else {
		        out.writeUTF("cd: répertoire introuvable: " + argument + "\n");
		    }
		}catch(IOException e) {
			System.out.println("IO EXCEPTION");
		}
	}

	private void listFiles(DataOutputStream out) {
		try {
	        File[] files = currentDirectory.listFiles();
	        if (files == null || files.length == 0) {
	            out.writeUTF("Aucun fichier dans ce répertoire\n");
	        } else {
	        		String s = "";
	            for (File f : files) {
		            	if(f.isDirectory()) s.concat("\033[94m");
		            	else if(f.isFile()) s.concat("\033[92m");
		            	else s.concat("\033[91m");
		            	s.concat(f.getName()).concat("\033[0m\n");
	            }
	            out.writeUTF(s);
	        }
		}catch(IOException e) {
			System.out.println("IO EXCEPTION");
		}
	}
	
	private void receiveFile(String fileName, DataInputStream in, DataOutputStream out) {
		try {
			out.writeUTF("ACK-UPLOAD");
			File file = new File(currentDirectory, fileName);
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(in.readAllBytes());
			fos.close();
		}
		catch (IOException e) {
            e.printStackTrace();
		}
	}
	
	private void sendFile(String fileName, DataOutputStream out) {
		try {
			byte[] data  = Files.readAllBytes(Paths.get(new URI(currentDirectory.getPath().concat("/").concat(fileName))));
			out.write(data);
		}
		catch (IOException e) {
            e.printStackTrace();
		}
		catch(Exception e) {
			
		}
	}
		

	private void makeDirectory(String argument, DataOutputStream out) {
		try {
			File newDir = new File(currentDirectory, argument);
		    if (newDir.exists()) {
		        out.writeUTF("mkdir: le dossier existe déjà");
		    } else if (newDir.mkdir()) {
		        out.writeUTF("Dossier créé : " + argument );
		    } else {
		        out.writeUTF("Impossible de créer le dossier : " + argument);
		    }	
		}catch(IOException e) {
			System.out.println("IO EXCEPTION");
		}
	}
	
	
	private void deleteFile(String argument, DataOutputStream out) {
		try {
			File file = new File(currentDirectory + "/" + argument);
	        if (file.exists() && file.delete()) {
	            out.writeUTF("Fichier supprimé");
	        } else {
	            out.writeUTF("Le fichier n'existe pas");
	        }
		}catch(IOException e) {
			System.out.println("IO EXCEPTION");
		}
	}
}
