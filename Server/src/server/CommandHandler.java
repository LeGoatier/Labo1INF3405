package server;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.File;
public class CommandHandler {
	File currentDirectory = new File("");
	
	public void handleCmd(String input, DataInputStream in, DataOutputStream out) {
		try {
			String[] words = input.split(" ");
			
			String cmd = words[0].toUpperCase();
			String argument = words[1];
	
		    switch (cmd) {
		        case "CD":
		            if (words.length < 2) {
		                out.writeUTF("Utilisation: cd <répertoire>\n");
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
		            receiveFile(argument, out);
		            break;
	
		        case "DOWNLOAD":
		            sendFile(argument, out);
		            break;
	
		        case "DELETE":
		            deleteFile(argument, out);
		            break;
	
		        case "EXIT":
		            exit(out);
		            break;
		            
		        case "ECHO":
					String s = "";
					for(int i = 1; i < words.length; ++i) {
						s.concat(words[i].concat(" "));
						out.writeUTF(s.concat("\n"));
					}
					break;
					
		        default:
		            out.writeUTF("Commande inconnue\n");
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
		        out.writeUTF("Répertoire courant: " + currentDirectory.getName() + "\n");
		    } else {
		        out.writeUTF("cd: répertoire introuvable: " + argument + "\n");
		    }
		}catch(IOException e) {
			System.out.println("IO EXCEPTION");
		}
	}

	private void listFiles(DataOutputStream out) {
        File[] files = currentDirectory.listFiles();
        if (files == null || files.length == 0) {
            out.writeUTF("Aucun fichier dans ce répertoire\n");
        } else {
            for (File f : files) {
                out.write(f.getName() + "\n");
            }
        }
		
	}
}
