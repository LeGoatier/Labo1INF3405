package server;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.DataInputStream;
public class CommandHandler {
	public void handleCmd(String input, DataInputStream in, DataOutputStream out) {
		try {
		String[] words = input.split(" ");
		if(input == "upload") {
			//while(true) {
				//in.read();
			//}
		}
		else if (input == "echo") {
			out.writeUTF("\033[95m" + input + "\033[0m");

		}
		}catch(IOException e) {
			System.out.println("IO EXCEPTION");
		}
	}
}
